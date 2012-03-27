import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import bswabe.BswabeMsk;
import bswabe.BswabePrv;
import bswabe.BswabePub;

public class Demo {
	final static boolean DEBUG = true;
	static String inputfile = "file_dir/input.txt";
	static String encfile = "file_dir/input.cpabe";
	static String decfile = "file_dir/input.txt.new";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BswabePub pub = new BswabePub();
		BswabeMsk msk = new BswabeMsk();
		BswabePrv prv;
		String attr = "sysadmin it_department";
		String policy = "sysadmin";

		Cpabe test = new Cpabe();
		println("//start to setup");
		test.setup(pub, msk);
		println("//end to setup");

		println("//start to keygen");
		prv = test.keygen(pub, msk, attr);
		println("//end to keygen");

		println("//start to enc");
		test.enc(pub, policy, inputfile, encfile);
		println("//end to enc");

		println("//start to dec");
		test.dec(pub, prv, encfile, decfile);
		println("//end to dec");
	}

	private static void println(Object o) {
		if (DEBUG)
			System.out.println(o);
	}
}
