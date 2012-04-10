/* A language policy for CPABE */
%language "Java"
%name-prefix "PolicyLang"
%define parser_class_name "PolicyLang"
%define public
%define package "cpabe.policy"

%code {
	public CpabePolicy finalPolicy;
	public static void main(String args[])
	{
//		PolicyLang p = new PolicyLang();
//		String[]
		
	}
}

%code imports {
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
//import bswabe.BswabePolicy;
}


%code {

	private SizedInteger expInt(Integer value, Integer bits) {
		SizedInteger s = new SizedInteger();

		if (bits.intValue() <= 0 || bits.intValue() >= 32) {
			System.out.println("error parsing policy: " + value + "\t" + bits);
			System.exit(0);
		}
		
		s.value = value.intValue();
		s.bits = bits.intValue();

		return s;
	}

	private SizedInteger flexInt(Integer value) {
		SizedInteger s = new SizedInteger();
		
		s.value = value.intValue();
		s.bits = 0;
		
		return s;
	}

	private CpabePolicy leafPolicy(String attr) {
		CpabePolicy p = new CpabePolicy();

		p.k = 1;
		p.attr = attr;
		p.children = new ArrayList();

		return p;
	}


	private CpabePolicy kOf2Policy(int k, CpabePolicy l, CpabePolicy r) {
		CpabePolicy p = new CpabePolicy();

		p.k = k;
		p.attr = null;
		p.children = new ArrayList();
		p.children.add(l);
		p.children.add(r);

		return p;
	}

	private CpabePolicy kOfPolicy(Integer k, ArrayList list) {
		CpabePolicy p = new CpabePolicy();

		if (k.intValue() < 1) {
			System.out
					.println("error parsing policy: trivially satisfied operator \""
							+ k + " of\"");
			System.exit(0);
		} else if (k.intValue() > list.size()) {
			System.out.println("error parsing policy: unsatisfied operator \""
					+ k + " of\" (only " + list.size() + " operator)");
			System.exit(0);
		} else if (list.size() == 1) {
			System.out.println("error parsing policy: identity operator \"" + k
					+ " of\" (only one of operator)");
			System.exit(0);
		}
		p.k = k.intValue();
		p.attr = null;
		p.children = list;

		return p;
	}

	private CpabePolicy eqPolicy(SizedInteger n, String attr) {
		if (n.bits == 0)
			return leafPolicy(attr + "_flexint_" + n.value);
		else
			return leafPolicy(attr + "_expint_" + n.bits + "_" + n.value);
	}

	private CpabePolicy ltPolicy(SizedInteger n, String attr) {
		return cmpPolicy(n, false, attr);
	}

	private CpabePolicy gtPolicy(SizedInteger n, String attr) {
		return cmpPolicy(n, true, attr);
	}

	private CpabePolicy lePolicy(SizedInteger n, String attr) {
		n.value++;
		return cmpPolicy(n, false, attr);
	}

	private CpabePolicy gePolicy(SizedInteger n, String attr) {
		n.value--;
		return cmpPolicy(n, true, attr);
	}


class PolicyLangLexer implements PolicyLang.Lexer {
	StringTokenizer st;

	public PolicyLangLexer (String str) {
		st = new StringTokenizer(str);
	}

	public void yyerror (String s) {
		System.err.println(s);
	}

	Object yylval;

	public Object getVal() {
		return yylval;
	}

	public int yylex() throws IOException {
		int r = 0;
		String str;
		while (st.hasMoreTokens()) {
			str = st.nextToken();
			if (str.equals("&") || str.equalsIgnoreCase("and"))
				r = AND;
			else if (str .equals("|") || str.equalsIgnoreCase("or"))
				r = OR;
			else if (str.equalsIgnoreCase("of"))
				r = OF;
			else if (str .equals("(") || str .equals(")") ||
					str.equals(",") ||
					str.equals("<") || str.equals(">") || 
					str.equals("=") || str.equals("#"))
					r = str.charAt(0);
			else if (str .equals( "<="))
				r = LEQ;
			else if (str .equals( ">="))
				r = GEQ;
			else if (isDigit(str)) {
				yylval = Integer.parseInt(str);
				r = INTLIT;
			} else if (isChars(str)) {
				yylval = str;
				r = TAG;
			}
			else {
				System.out.println("syntax error at " + str);
				System.exit(0);
			}
		}
		
		return r;
	}

	private boolean isDigit(String str) {
		for (int i=0; i<str.length(); i++)
			if (!Character.isDigit(str.charAt(i)))
				return false;
		return true;
	}

	private boolean isChars(String str) {
		if (!Character.isLetter(str.charAt(0)))
			return false;
		for (int i=1; i<str.length(); i++)
			if (!Character.isLetterOrDigit(str.charAt(i)))
				return false;
		return true;
	}

	@Override
	public Object getLVal() {
		// TODO Auto-generated method stub
		return null;
	}
}

class CpabePolicy {
	int k; /* one if leaf, otherwise threshold */
	String attr; /* attribute string if leaf, null otherwise */
	ArrayList children; /* null for leaf */
}

class SizedInteger {
	long	value;
	int bits;
}
}


/* Bison Declaration */
%token <String>				TAG 
%token <Integer>			INTLIT
%type  <SizedInteger>			number
%type  <CpabePolicy>			policy
%type  <ArrayList>			argList

%left OR
%left AND 
%token OF
%token LEQ 
%token GEQ 

%%

/* Grammar follows */
result: policy { finalPolicy = $1 }

number:   INTLIT '#' INTLIT          { $$ = expInt($1, $3); }
        | INTLIT                     { $$ = flexInt($1);    }

policy:   TAG                        { $$ = leafPolicy($1);        }
        | policy OR  policy          { $$ = kOf2Policy(1, $1, $3); }
        | policy AND policy          { $$ = kOf2Policy(2, $1, $3); }
        | INTLIT OF '(' argList ')' { $$ = kOfPolicy($1, $4);     }
        | TAG '=' number             { $$ = eqPolicy($3, $1);      }
        | TAG '<' number             { $$ = ltPolicy($3, $1);      }
        | TAG '>' number             { $$ = gtPolicy($3, $1);      }
        | TAG LEQ number             { $$ = lePolicy($3, $1);      }
        | TAG GEQ number             { $$ = gePolicy($3, $1);      }
        | number '=' TAG             { $$ = eqPolicy($1, $3);      }
        | number '<' TAG             { $$ = gtPolicy($1, $3);      }
        | number '>' TAG             { $$ = ltPolicy($1, $3);      }
        | number LEQ TAG             { $$ = gePolicy($1, $3);      }
        | number GEQ TAG             { $$ = lePolicy($1, $3);      }
        | '(' policy ')'             { $$ = $2;                     }

argList: policy                     { $$ = new ArrayList();
                                       ((ArrayList)$$).add($1); }
        | argList ',' policy        { $$ = $1;
                                       ((ArrayList)$$).add($3); }
;

%%

