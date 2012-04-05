package cpabe.policy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class LangPolicy {
	public static String[] parseAttribute(String s) {
		ArrayList<String> str_arr = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(s);
		String token;
		String res[];
		int len;

		while (st.hasMoreTokens()) {
			token = st.nextToken();
			if (!token.contains("=")) {
				str_arr.add(token);
			}
			// else if (token.contains("#") &&
			// (token.indexOf('#')-token.indexOf('=') > 1)) {
			// String[] split_str3 = new String[3];
			// split_str3 = token.split("[=#]");
			//
			// } else if (token.contains("#") && !token.contains("#")) {
			//
			// }
			else {
				System.out.println("Error");
			}
		}

		Collections.sort(str_arr, new SortByAlphabetic());
		
		len = str_arr.size();
		res = new String[len];
		for (int  i =0;i<len;i++)
			res[i] = str_arr.get(i);
		return res;
	}
	
	
//	private static String parsePoliceLong(String s) {
//		String parsedPolicy = null;
//		
//		return parsedPolicy;
//	}

	static class SortByAlphabetic implements Comparator<String> {
		@Override
		public int compare(String s1, String s2) {
			if (s1.compareTo(s2) >= 0)
				return 1;
			return 0;
		}

	}
}
