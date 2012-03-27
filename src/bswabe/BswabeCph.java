package bswabe;

import java.util.ArrayList;

import it.unisa.dia.gas.jpbc.Element;

public class BswabeCph {
	Element cs; /* G_T */
	Element c; /* G_1 */
	BswabePolicy p;

	public byte[] bswabeCphSerialize() {
		ArrayList<Byte> arrlist = new ArrayList<Byte>();
		SerializeUtils.serializeElement(arrlist, cs);
		SerializeUtils.serializeElement(arrlist, c);
		SerializeUtils.serializePolicy(arrlist, p);

		Byte[] B = (Byte[]) arrlist.toArray();
		int len = B.length;
		byte[] b = new byte[len];
		for (int i = 0; i < len; i++)
			b[i] = B[i].byteValue();

		return b;
	}

	public void bswabeCphUnserialize(BswabePub pub, byte[] cphBuf) {

	}
}
