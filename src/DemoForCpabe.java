
public class DemoForCpabe {
	final static boolean DEBUG = true;

	static String pubfile = "file_dir/pub_key";
	static String mskfile = "file_dir/master_key";
	static String prvfile = "file_dir/master_key";

	static String inputfile = "file_dir/input.txt";
	static String encfile = "file_dir/input.txt.cpabe";
	static String decfile = "file_dir/input.txt.new";

	public static void main(String[] args) throws Exception {
		String attr = "sysadmin it_department";
		String policy = "sysadmin";

		Cpabe test = new Cpabe();
		println("//start to setup");
		test.setup(pubfile, mskfile);
		println("//end to setup");

		println("//start to keygen");
		test.keygen(pubfile, prvfile, mskfile, attr);
		println("//end to keygen");

		println("//start to enc");
		test.enc(pubfile, policy, inputfile, encfile);
		println("//end to enc");

		println("//start to dec");
		test.dec(pubfile, prvfile, encfile, decfile);
		println("//end to dec");
	}

	private static void println(Object o) {
		if (DEBUG)
			System.out.println(o);
	}
}
