package bswabe;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import it.unisa.dia.gas.jpbc.CurveGenerator;
import it.unisa.dia.gas.jpbc.CurveParameters;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;

public class Bswabe {
	public static void setup(BswabePub pub, BswabeMsk msk) {
		Element alpha;
		
		// TODO initialize the parmas of pairing
		// 找到Curve的定义就知道改怎么办了
		// libbswabe-0.9/core.c：TYPE_A_PARAMS
		int rBits = 160;
		int qBits = 512;
		CurveGenerator generator = new TypeACurveGenerator(rBits, qBits);
		CurveParameters params = generator.generate();
		pub.p = PairingFactory.getPairing(params);
		Pairing pairing = pub.p; 
		
		// TODO confirm the way to get g/h is right
		pub.g = pairing.getG1().newElement();
		pub.h = pairing.getG1().newElement();
		pub.gp = pairing.getG2().newElement();
		pub.g_hat_alpha = pairing.getGT().newElement();
		alpha = pairing.getZr().newElement();
		msk.beta = pairing.getZr().newElement();
		msk.g_alpha = pairing.getG2().newElement();
		
		alpha.setToRandom();
		msk.beta.setToRandom();
		pub.g.setToRandom();
		pub.gp.setToRandom();
		
		msk.g_alpha = pub.gp.powZn(alpha);
		pub.h = pub.g.powZn(msk.beta);
		
		pub.g_hat_alpha = pairing.pairing(pub.g, msk.g_alpha);
	}
	public static BswabePrv keygen(BswabePub pub, BswabeMsk msk, String[] attrs) throws NoSuchAlgorithmException {
		BswabePrv prv = new BswabePrv();
		Element g_r, r, beta_inv;
		Pairing pairing;
		
		/* initialize */
		pairing = pub.p;
		prv.d = pairing.getG2().newElement();
		g_r = pairing.getG2().newElement();
		r = pairing.getZr().newElement();
		beta_inv = pairing.getZr().newElement();
		
		/* compute */
		r.setToRandom();
		g_r = pub.gp.powZn(r);
		
		prv.d = msk.g_alpha.mul(g_r);
		// TODO invert method
		beta_inv = msk.beta.invert();
		prv.d = prv.d.powZn(beta_inv);	
		
		int i, len = attrs.length;
		prv.comps = new ArrayList<BswabePrvComp>();
		for (i=0; i<len; i++){
			BswabePrvComp comp = new BswabePrvComp();
			Element h_rp;
			Element rp;
			
			comp.attr = attrs[i];
			
			comp.d = pairing.getG2().newElement();
			comp.dp = pairing.getG1().newElement();
			h_rp = pairing.getG2().newElement();
			rp = pairing.getZr().newElement();
			
			Utils.elementFromString(h_rp, comp.attr);
			rp.setToRandom();
			
			h_rp = h_rp.powZn(rp);
			
			comp.d = g_r.mul(h_rp);
			comp.dp = pub.g.powZn(rp);
			
			prv.comps.add(comp);
		}
		
		return prv;
	}
 }
