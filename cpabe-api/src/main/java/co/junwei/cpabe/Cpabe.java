package co.junwei.cpabe;
import co.junwei.cpabe.policy.LangPolicy;
import it.unisa.dia.gas.jpbc.Element;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import co.junwei.bswabe.Bswabe;
import co.junwei.bswabe.BswabeCph;
import co.junwei.bswabe.BswabeCphKey;
import co.junwei.bswabe.BswabeElementBoolean;
import co.junwei.bswabe.BswabeMsk;
import co.junwei.bswabe.BswabePrv;
import co.junwei.bswabe.BswabePub;
import co.junwei.bswabe.SerializeUtils;

public class Cpabe {

	/**
	 * @param
	 * @author Junwei Wang(wakemecn@gmail.com)
	 */

	public final int SETUP_PUBLIC = 0;
	public final int SETUP_MASTER = 1;

	public byte[][] setup() {
		BswabePub pub = new BswabePub();
		BswabeMsk msk = new BswabeMsk();
		Bswabe.setup(pub, msk);

		byte[] publicKey = SerializeUtils.serializeBswabePub(pub);
		byte[] masterKey = SerializeUtils.serializeBswabeMsk(msk);

		return new byte[][] { publicKey, masterKey };
	}

	public void setup(String pubfile, String mskfile) throws IOException,
			ClassNotFoundException {
		byte[] pub_byte, msk_byte;
		BswabePub pub = new BswabePub();
		BswabeMsk msk = new BswabeMsk();
		Bswabe.setup(pub, msk);

		/* store BswabePub into mskfile */
		pub_byte = SerializeUtils.serializeBswabePub(pub);
		Common.spitFile(pubfile, pub_byte);

		/* store BswabeMsk into mskfile */
		msk_byte = SerializeUtils.serializeBswabeMsk(msk);
		Common.spitFile(mskfile, msk_byte);
	}

	public byte[] keygen(byte[] publicKey, byte[] masterKey, String attribute) throws NoSuchAlgorithmException {
		BswabePub pub = SerializeUtils.unserializeBswabePub(publicKey);
		BswabeMsk msk = SerializeUtils.unserializeBswabeMsk(pub, masterKey);
		String[] parsedAttribute = LangPolicy.parseAttribute(attribute);
		BswabePrv prv = Bswabe.keygen(pub, msk, parsedAttribute);
		return SerializeUtils.serializeBswabePrv(prv);
	}

	public void keygen(String pubfile, String prvfile, String mskfile,
			String attr_str) throws NoSuchAlgorithmException, IOException {
		BswabePub pub;
		BswabeMsk msk;
		byte[] pub_byte, msk_byte, prv_byte;

		/* get BswabePub from pubfile */
		pub_byte = Common.suckFile(pubfile);
		pub = SerializeUtils.unserializeBswabePub(pub_byte);

		/* get BswabeMsk from mskfile */
		msk_byte = Common.suckFile(mskfile);
		msk = SerializeUtils.unserializeBswabeMsk(pub, msk_byte);

		String[] attr_arr = LangPolicy.parseAttribute(attr_str);
		BswabePrv prv = Bswabe.keygen(pub, msk, attr_arr);

		/* store BswabePrv into prvfile */
		prv_byte = SerializeUtils.serializeBswabePrv(prv);
		Common.spitFile(prvfile, prv_byte);
	}

	private static byte[] packCpabe(byte[] cphBuf, byte[] aesBuf) throws IOException {
		// store data
		// mlen(4byte:int),mbuf,cphlen(4byte),cphbuf,aeslen(4byte),aesBuf
		byte[] mBuf = new byte[0];
		ByteArrayOutputStream stream = Common.writeCpabeData(mBuf, cphBuf, aesBuf);
		byte[] byteArray = stream.toByteArray();
		stream.close();
		return byteArray;
	}

	public byte[] encrypt(byte[] publicKey, String policy, byte[] plain) throws Exception {
		BswabePub pub = SerializeUtils.unserializeBswabePub(publicKey);
		BswabeCphKey keyCph = Bswabe.enc(pub, policy);
		BswabeCph cph = keyCph.cph;
		Element element = keyCph.key;
		if (cph == null) {
			throw new RuntimeException("Error happened while encrypting");
		} else {
			byte[] cphBuf = SerializeUtils.bswabeCphSerialize(cph);
			byte[] aesBuf = AESCoder.encrypt(element.toBytes(), plain);
			return packCpabe(cphBuf, aesBuf);
		}
	}

	public void enc(String pubfile, String policy, String inputfile,
			String encfile) throws Exception {
		BswabePub pub;
		BswabeCph cph;
		BswabeCphKey keyCph;
		byte[] plt;
		byte[] cphBuf;
		byte[] aesBuf;
		byte[] pub_byte;
		Element m;

		/* get BswabePub from pubfile */
		pub_byte = Common.suckFile(pubfile);
		pub = SerializeUtils.unserializeBswabePub(pub_byte);

		keyCph = Bswabe.enc(pub, policy);
		cph = keyCph.cph;
		m = keyCph.key;
		System.err.println("m = " + m.toString());

		if (cph == null) {
			System.out.println("Error happed in enc");
			System.exit(0);
		}

		cphBuf = SerializeUtils.bswabeCphSerialize(cph);

		/* read file to encrypted */
		plt = Common.suckFile(inputfile);
		aesBuf = AESCoder.encrypt(m.toBytes(), plt);
		// PrintArr("element: ", m.toBytes());
		Common.writeCpabeFile(encfile, cphBuf, aesBuf);
	}

	private static byte[][] UnpackCpabe(byte[] packed) throws IOException {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(packed);
		return Common.readCpabeData(inputStream);
	}

	public byte[] decode(byte[] publicKey, byte[] privateKey, byte[] encrypted) throws Exception {
		int BUF_AES = 0;
		int BUF_CPH = 1;
		byte[][] tmp = UnpackCpabe(encrypted);
		byte[] aesBuf = tmp[BUF_AES];
		byte[] cphBuf = tmp[BUF_CPH];
		BswabePub pub = SerializeUtils.unserializeBswabePub(publicKey);
		BswabeCph cph = SerializeUtils.bswabeCphUnserialize(pub, cphBuf);
		BswabePrv prv = SerializeUtils.unserializeBswabePrv(pub, privateKey);;
		BswabeElementBoolean beb = Bswabe.dec(pub, prv, cph);

		if (beb.b) {
			return AESCoder.decrypt(beb.e.toBytes(), aesBuf);
		} else {
			throw new RuntimeException("Decrypt error: " + beb.e.toString());
		}
	}

	public void dec(String pubfile, String prvfile, String encfile,
			String decfile) throws Exception {
		byte[] aesBuf, cphBuf;
		byte[] plt;
		byte[] prv_byte;
		byte[] pub_byte;
		byte[][] tmp;
		BswabeCph cph;
		BswabePrv prv;
		BswabePub pub;

		/* get BswabePub from pubfile */
		pub_byte = Common.suckFile(pubfile);
		pub = SerializeUtils.unserializeBswabePub(pub_byte);

		/* read ciphertext */
		tmp = Common.readCpabeFile(encfile);
		aesBuf = tmp[0];
		cphBuf = tmp[1];
		cph = SerializeUtils.bswabeCphUnserialize(pub, cphBuf);

		/* get BswabePrv form prvfile */
		prv_byte = Common.suckFile(prvfile);
		prv = SerializeUtils.unserializeBswabePrv(pub, prv_byte);

		BswabeElementBoolean beb = Bswabe.dec(pub, prv, cph);
		System.err.println("e = " + beb.e.toString());
		if (beb.b) {
			plt = AESCoder.decrypt(beb.e.toBytes(), aesBuf);
			Common.spitFile(decfile, plt);
		} else {
			System.exit(0);
		}
	}

}
