package bswabe;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import it.unisa.dia.gas.jpbc.Element;

public class BswabeMsk implements Serializable{
	Element beta;					/* Z_r */			
	Element g_alpha;				/* G_2 */
	
	private void writeObject(ObjectOutputStream stream) throws IOException, ClassNotFoundException {
		byte[] b_beta = beta.toBytes();
		beta.setFromBytes(b_beta);
	//	byte[] b_g_alpha = g_alpha.toBytes();
	
		System.out.println(b_beta);
		//stream.writeObject(bytesToObject(b_beta));
		stream.writeObject(b_beta);
		//stream.writeObject(b_g_alpha);
	}
	
	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
	
		byte[] b_beta = objectToBytes(stream.readObject());
		System.out.println(b_beta);
		
		beta.setFromBytes(b_beta);
		
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		BswabeMsk test = new BswabeMsk();
		FileOutputStream buf = new FileOutputStream("test");
		ObjectOutputStream o = new ObjectOutputStream(buf);
		o.writeObject(test);
		o.close();
		
		FileInputStream buf1 = new FileInputStream("test");
		ObjectInputStream in = new ObjectInputStream(buf1);
		in.readObject();
		in.close();
	}
	
	private byte[] objectToBytes(Object obj) throws IOException {
		byte[] bytes;
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bo);
		out.writeObject(obj);
		bytes = bo.toByteArray();
		bo.close();
		out.close();
		return bytes;	
	}
	
	private Object bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {
		Object obj;
		ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
		ObjectInputStream in = new ObjectInputStream(bi);
		obj = in.readObject();
		bi.close();
		in.close();
		return obj;
	}
}
