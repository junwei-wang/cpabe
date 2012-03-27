package bswabe;

import it.unisa.dia.gas.jpbc.Element;

import java.util.ArrayList;

public class SerializeUtils {

	public static void serializeElement(ArrayList<Byte> arrlist, Element e) {
		byte[] arr_e = e.toBytes();
		serializeUint32(arrlist, (long) arr_e.length);
		byteArrListAppend(arrlist, arr_e);
	}

	public static void serializeString(ArrayList<Byte> arrlist, String s) {
		byte[] b = s.getBytes();
		serializeUint32(arrlist, (int) b.length);
		byteArrListAppend(arrlist, b);
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

	private static void serializeUint32(ArrayList<Byte> arrlist, long k) {
		int i;
		byte b;

		for (i = 3; i >= 0; i--) {
			b = (byte) ((k & (0x000000ff << (i * 8))) >> (i * 8));
			arrlist.add(Byte.valueOf(b));
		}
	}

	private static void byteArrListAppend(ArrayList<Byte> arrlist, byte[] b) {
		int len = b.length;
		for (int i = 0; i < len; i++)
			arrlist.add(Byte.valueOf(b[i]));
	}
}
