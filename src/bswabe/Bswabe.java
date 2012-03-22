package bswabe;

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
		Pairing pairing = PairingFactory.getPairing(params);
		
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
	public static void main(String args[]) {
		setup(null, null);
	}

}
