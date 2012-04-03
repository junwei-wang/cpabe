package bswabe;

import java.util.ArrayList;

import it.unisa.dia.gas.jpbc.Element;

public class BswabeCph {
	/*
	 * A ciphertext. Note that this library only handles encrypting a single
	 * group element, so if you want to encrypt something bigger, you will have
	 * to use that group element as a symmetric key for hybrid encryption (which
	 * you do yourself).
	 */
	Element cs; /* G_T */
	Element c; /* G_1 */
	BswabePolicy p;

	public byte[] bswabeCphSerialize() {
		ArrayList<Byte> arrlist = new ArrayList<Byte>();
		SerializeUtils.serializeElement(arrlist, cs);
		SerializeUtils.serializeElement(arrlist, c);
		SerializeUtils.serializePolicy(arrlist, p);

		return Byte_arr2byte_arr(arrlist);
	}

	public static BswabeCph bswabeCphUnserialize(BswabePub pub, byte[] cphBuf) {
		BswabeCph cph = new BswabeCph();
		int offset = 0;
		int[] offset_arr = new int[1];

		cph.cs = pub.p.getGT().newElement();
		cph.c = pub.p.getG1().newElement();

		offset = SerializeUtils.unserializeElement(cphBuf, offset, cph.cs);
		offset = SerializeUtils.unserializeElement(cphBuf, offset, cph.c);

		offset_arr[0] = offset;
		cph.p = SerializeUtils.unserializePolicy(pub, cphBuf, offset_arr);
		offset = offset_arr[0];

		return cph;
	}

	private static byte[] Byte_arr2byte_arr(ArrayList<Byte> B) {
		int len = B.size();
		byte[] b = new byte[len];

		for (int i = 0; i < len; i++)
			b[i] = B.get(i).byteValue();

		return b;
	}
}
