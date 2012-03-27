import it.unisa.dia.gas.jpbc.Element;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

import bswabe.Bswabe;
import bswabe.BswabeCph;
import bswabe.BswabeMsk;
import bswabe.BswabePrv;
import bswabe.BswabePub;

public class Cpabe {

	/**
	 * @param args
	 * @author Junwei Wang(wakemecn@gmail.com)
	 */

	public void setup(BswabePub pub, BswabeMsk msk) throws IOException,
			ClassNotFoundException {
		// BswabePub pub = new BswabePub();
		// BswabeMsk msk = new BswabeMsk();
		Bswabe.setup(pub, msk);
	}

	public BswabePrv keygen(BswabePub pub, BswabeMsk msk, String attr_str)
			throws NoSuchAlgorithmException {
		ArrayList<String> attr_arr = LangPolicy.parseAttribute(attr_str);
		BswabePrv prv = Bswabe.keygen(pub, msk, attr_arr);
		return prv;
	}

	public void enc(BswabePub pub, String policy, String inputfile,
			String encfile) throws Exception {
		BswabeCph cph;
		int fileLen;
		byte[] plt;
		byte[] cphBuf;
		byte[] aesBuf;

		Element m = null;

		cph = Bswabe.enc(pub, m, policy);
		if (cph == null) {
			System.out.println("Error happed in enc");
			System.exit(0);
		}

		cphBuf = cph.bswabeCphSerialize();

		plt = Common.suckFile(inputfile);
		fileLen = plt.length;
		aesBuf = Common.AES128CbcEncrypt(plt, m);

		Common.writeCpabeFile(encfile, cphBuf, fileLen, aesBuf);

	}

	public void dec(BswabePub pub, BswabePrv prv, String encfile, String decfile) throws IOException {
		int fileLen = 0;
		byte[] aesBuf = null;
		byte[] plt;
		byte[] cphBuf = null;
		BswabeCph cph;
		
		Common.readCpabeFile(encfile, cphBuf, fileLen, aesBuf);
		
	}
	
	public void dec() {

	}

}

