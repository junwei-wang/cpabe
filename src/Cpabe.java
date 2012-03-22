import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import bswabe.Bswabe;
import bswabe.BswabeMsk;
import bswabe.BswabePrv;
import bswabe.BswabePub;


public class Cpabe {

	/**
	 * @param args
	 * @author Junwei Wang(wakemecn@gmail.com)
	 */
	
	public Bswabe mybswabe;
		
	public void setup(BswabePub pub, BswabeMsk msk) throws IOException, ClassNotFoundException {
		//BswabePub pub = new BswabePub();
		//BswabeMsk msk = new BswabeMsk();
		Bswabe.setup(pub, msk);
	}
			
	public BswabePrv keygen(BswabePub pub, BswabeMsk msk, String attr_str) throws NoSuchAlgorithmException {
		ArrayList<String> attr_arr = parseAttribute(attr_str);
		BswabePrv prv = Bswabe.keygen(pub, msk, attr_arr);
		return prv;
	}
	
	public void enc() {
		
	}
	
	public void dec() {
		
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		BswabePub pub = new BswabePub();
		BswabeMsk msk = new BswabeMsk();
		BswabePrv prv;
		String attr = "sysadmin it_department";
		
		Cpabe test = new Cpabe();
		test.setup(pub, msk);
		prv = test.keygen(pub, msk, attr);

	}
	
	private ArrayList<String> parseAttribute(String s) {
		ArrayList<String> str_arr = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(s);
		String token = st.nextToken();
		while ((token = st.nextToken()) != null) {
			if (!token.contains("=")) {
				str_arr.add(token);
			} else if (token.contains("#") && (token.indexOf('#')-token.indexOf('=') > 1)) {
				String[] split_str3 = new String[3];
				split_str3 = token.split("[=#]");
				
			} else if () {
				
			}
		}
		return null;
	}
	
}
