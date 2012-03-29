package bswabe;

import it.unisa.dia.gas.jpbc.Element;

import java.util.ArrayList;

public class SerializeUtils {

	public static void serializeElement(ArrayList<Byte> arrlist, Element e) {
		byte[] arr_e = e.toBytes();
		serializeUint32(arrlist, (long) arr_e.length);
		byteArrListAppend(arrlist, arr_e);
	}

	public static int unserializeElement(byte[] arr, int offset, Element e) {
		long len;
		int i;
		byte[] e_byte;

		len = unserializeUint32(arr, offset);
		e_byte = new byte[(int) len];
		offset += 4;
		for (i = 0; i < len; i++)
			e_byte[i] = arr[offset + i];
		e.setFromBytes(e_byte);

		return (int) (offset + len);
	}

	public static void serializePolicy(ArrayList<Byte> arrlist, BswabePolicy p) {
		serializeUint32(arrlist, (long) p.k);
		serializeUint32(arrlist, (long) p.children.length);

		if (p.children.length == 0) {
			serializeString(arrlist, p.attr);
			serializeElement(arrlist, p.c);
			serializeElement(arrlist, p.cp);
		} else {
			for (int i = 0; i < p.children.length; i++)
				serializePolicy(arrlist, p.children[i]);
		}
	}

	public static BswabePolicy unserializePolicy(BswabePub pub, byte[] arr,
			int[] offset) {
		int i;
		int n;
		BswabePolicy p = new BswabePolicy();
		p.k = (int) unserializeUint32(arr, offset[0]);
		offset[0] += 4;
		p.attr = null;

		/* children */
		n = (int) unserializeUint32(arr, offset[0]);
		offset[0] += 4;
		if (n == 0) {
			p.children = null;

			StringBuffer sb = new StringBuffer("");
			offset[0] = unserializeString(arr, offset[0], sb);
			p.attr = sb.substring(0);

			p.c = pub.p.getG1().newElement();
			p.cp = pub.p.getG1().newElement();

			offset[0] = unserializeElement(arr, offset[0], p.c);
			offset[0] = unserializeElement(arr, offset[0], p.cp);
		} else {
			for (i = 0; i < n; i++)
				p.children[i] = unserializePolicy(pub, arr, offset);
		}

		return p;
	}

	public static int unserializeString(byte[] arr, int offset, StringBuffer sb) {
		int i;
		int len;
		byte[] str_byte;

		len = (int) unserializeUint32(arr, offset);
		offset += 4;
		str_byte = new byte[len];
		for (i = 0; i < len; i++)
			str_byte[i] = arr[offset + i];

		sb.append(new String(str_byte));
		return offset + len;
	}

	public static void serializeString(ArrayList<Byte> arrlist, String s) {
		byte[] b = s.getBytes();
		serializeUint32(arrlist, (int) b.length);
		byteArrListAppend(arrlist, b);
	}

	private static void serializeUint32(ArrayList<Byte> arrlist, long k) {
		int i;
		byte b;

		for (i = 3; i >= 0; i--) {
			b = (byte) ((k & (0x000000ff << (i * 8))) >> (i * 8));
			arrlist.add(Byte.valueOf(b));
		}
	}

	/* You have to do offset+=4 yourself */
	private static long unserializeUint32(byte[] arr, int offset) {
		int i;
		long r;

		r = 0;
		for (i = 3; i >= 0; i--)
			r |= (arr[offset++]) << (i * 8);
		return r;
	}

	private static void byteArrListAppend(ArrayList<Byte> arrlist, byte[] b) {
		int len = b.length;
		for (int i = 0; i < len; i++)
			arrlist.add(Byte.valueOf(b[i]));
	}

	private static byte[] serializeBswabePub(BswabePub pub) {
		ArrayList<Byte> arrlist = new ArrayList<Byte>();
		// TODO
		// serializeString(arrlist, pub.);

		return null;

	}

	public static byte[] serializeBswabeMsk(BswabeMsk msk) {
		ArrayList<Byte> arrlist = new ArrayList<Byte>();
		serializeElement(arrlist, msk.beta);
		serializeElement(arrlist, msk.beta);
		int len = arrlist.size();
		byte[] res = new byte[len];
		for (int i = 0; i < len; i++)
			res[i] = arrlist.get(i).byteValue();
		return res;
	}

	public static BswabeMsk unserializeBswabeMsk(BswabePub pub, byte[] b) {
		int offset = 0;
		BswabeMsk msk = new BswabeMsk();

		msk.beta = pub.p.getZr().newElement();
		msk.g_alpha = pub.p.getG2().newElement();

		offset = unserializeElement(b, offset, msk.beta);
		offset = unserializeElement(b, offset, msk.g_alpha);
		
		return msk;
	}

}
