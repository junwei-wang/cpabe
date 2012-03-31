import it.unisa.dia.gas.jpbc.Element;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import bswabe.Bswabe;
import bswabe.BswabeCph;
import bswabe.BswabeMsk;
import bswabe.BswabePrv;
import bswabe.BswabePub;
import bswabe.SerializeUtils;

public class Cpabe {

	/**
	 * @param args
	 * @author Junwei Wang(wakemecn@gmail.com)
	 */

	public void setup(String pubfile, String mskfile) throws IOException,
			ClassNotFoundException {
		byte[] msk_byte;
		BswabePub pub = new BswabePub();
		BswabeMsk msk = new BswabeMsk();
		Bswabe.setup(pub, msk);

		msk_byte = SerializeUtils.serializeBswabeMsk(msk);
		Common.spitFile(mskfile, msk_byte);
	}

	public void setup() throws IOException, ClassNotFoundException {
		setup("pub_key", "master_key");
	}

	public void setupSpecfyPub(String pubfile) throws IOException,
			ClassNotFoundException {
		setup(pubfile, "master_key");
	}

	public void setupSpecfyMsk(String mskfile) throws IOException,
			ClassNotFoundException {
		setup("pub_key", mskfile);
	}

	public BswabePrv keygen(BswabePub pub, String mskfile, String attr_str)
			throws NoSuchAlgorithmException, IOException {
		BswabeMsk msk;
		byte[] msk_byte;

		msk_byte = Common.suckFile(mskfile);
		msk = SerializeUtils.unserializeBswabeMsk(pub, msk_byte);
		String[] attr_arr = LangPolicy.parseAttribute(attr_str);
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

	public void dec(BswabePub pub, BswabePrv prv, String encfile, String decfile)
			throws IOException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		int fileLen = 0;
		byte[] aesBuf = null;
		byte[] plt;
		byte[] cphBuf = null;
		BswabeCph cph = null;
		Element m = null;

		Common.readCpabeFile(encfile, cphBuf, fileLen, aesBuf);
		cph = BswabeCph.bswabeCphUnserialize(pub, cphBuf);

		Bswabe.dec(pub, prv, cph, m);

		plt = Common.AES128CbcDecrypt(aesBuf, m);
		/* TODO fileLen && plt.length */
		Common.spitFile(decfile, plt);
	}

}
