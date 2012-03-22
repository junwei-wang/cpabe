import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import bswabe.Bswabe;
import bswabe.BswabeMsk;
import bswabe.BswabePub;


public class Cpabe {

	/**
	 * @param args
	 * @author Junwei Wang(wakemecn@gmail.com)
	 */
	
	public Bswabe mybswabe;
		
	public void setup() throws IOException, ClassNotFoundException {
		BswabePub pub = new BswabePub();
		BswabeMsk msk = new BswabeMsk();
		Bswabe.setup(pub, msk);
		//System.out.println(pub);
		System.out.println(msk);
		
//		FileOutputStream outputStream = new FileOutputStream("master_key");
//		ObjectOutputStream out = new ObjectOutputStream(outputStream);
//		out.writeObject(msk);
//		out.close();
//		FileInputStream inputStream = new FileInputStream("master_key");
//		ObjectInputStream in = new ObjectInputStream(inputStream);
//		msk = (BswabeMsk) in.readObject();
//		in.close();
		Common.writeIntoFile(msk, "master_key");
		Common.readFromFile(msk, "master_key");
		//System.out.println(pub);
		System.out.println(msk);
			//Common.writeIntoFile(msk, "master_key");
		
		
	}
			
	public void keygen() {
		
	}
	
	public void enc() {
		
	}
	
	public void dec() {
		
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Cpabe test = new Cpabe();
		test.setup();

	}
	
	
	
}
