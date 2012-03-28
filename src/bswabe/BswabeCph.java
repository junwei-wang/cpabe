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
		
		/* the way Byte to byte */
		for (int i = 0; i < len; i++)
			b[i] = B[i].byteValue();

		return b;
	}

	public BswabeCph bswabeCphUnserialize(BswabePub pub, byte[] cphBuf) {
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
}
