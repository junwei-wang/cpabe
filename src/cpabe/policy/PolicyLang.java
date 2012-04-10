/* A Bison parser, made by GNU Bison 2.5.  */

/* Skeleton implementation for Bison LALR(1) parsers in Java

 Copyright (C) 2007-2011 Free Software Foundation, Inc.

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
 part or all of the Bison parser skeleton and distribute that work
 under terms of your choice, so long as that work isn't itself a
 parser generator using the skeleton or a modified version thereof
 as a parser skeleton.  Alternatively, if you modify or redistribute
 the parser skeleton itself, you may (at your option) remove this
 special exception, which will cause the skeleton and the resulting
 Bison output files to be licensed under the GNU General Public
 License without this special exception.

 This special exception was added by the Free Software Foundation in
 version 2.2 of Bison.  */

package cpabe.policy;

/* First part of user declarations.  */

/* "%code imports" blocks.  */

/* Line 33 of lalr1.java  */
/* Line 18 of "PolicyLang.y"  */

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

//import bswabe.BswabePolicy;

/* Line 33 of lalr1.java  */
/* Line 50 of "PolicyLang.java"  */

/**
 * A Bison parser, automatically generated from <tt>PolicyLang.y</tt>.
 * 
 * @author LALR (1) parser skeleton written by Paolo Bonzini.
 */
public class PolicyLang {
	/** Version number for the Bison executable that generated this parser. */
	public static final String bisonVersion = "2.5";

	/** Name of the skeleton that generated this parser. */
	public static final String bisonSkeleton = "lalr1.java";

	/** True if verbose error messages are enabled. */
	public boolean errorVerbose = false;

	/** Token returned by the scanner to signal the end of its input. */
	public static final int EOF = 0;

	/* Tokens. */
	/** Token number, to be returned by the scanner. */
	public static final int TAG = 258;
	/** Token number, to be returned by the scanner. */
	public static final int INTLIT = 259;
	/** Token number, to be returned by the scanner. */
	public static final int OR = 260;
	/** Token number, to be returned by the scanner. */
	public static final int AND = 261;
	/** Token number, to be returned by the scanner. */
	public static final int OF = 262;
	/** Token number, to be returned by the scanner. */
	public static final int LEQ = 263;
	/** Token number, to be returned by the scanner. */
	public static final int GEQ = 264;

	/**
	 * Communication interface between the scanner and the Bison-generated
	 * parser <tt>PolicyLang</tt>.
	 */
	public interface Lexer {

		/**
		 * Method to retrieve the semantic value of the last scanned token.
		 * 
		 * @return the semantic value of the last scanned token.
		 */
		Object getLVal();

		/**
		 * Entry point for the scanner. Returns the token identifier
		 * corresponding to the next token and prepares to return the semantic
		 * value of the token.
		 * 
		 * @return the token identifier corresponding to the next token.
		 */
		int yylex() throws java.io.IOException;

		/**
		 * Entry point for error reporting. Emits an error in a user-defined
		 * way.
		 * 
		 * 
		 * @param s
		 *            The string for the error message.
		 */
		void yyerror(String s);
	}

	/** The object doing lexical analysis for us. */
	private Lexer yylexer;

	/**
	 * Instantiates the Bison-generated parser.
	 * 
	 * @param yylexer
	 *            The scanner that will supply tokens to the parser.
	 */
	public PolicyLang(Lexer yylexer) {
		this.yylexer = yylexer;

	}

	private java.io.PrintStream yyDebugStream = System.err;

	/**
	 * Return the <tt>PrintStream</tt> on which the debugging output is printed.
	 */
	public final java.io.PrintStream getDebugStream() {
		return yyDebugStream;
	}

	/**
	 * Set the <tt>PrintStream</tt> on which the debug output is printed.
	 * 
	 * @param s
	 *            The stream that is used for debugging output.
	 */
	public final void setDebugStream(java.io.PrintStream s) {
		yyDebugStream = s;
	}

	private int yydebug = 0;

	/**
	 * Answer the verbosity of the debugging output; 0 means that all kinds of
	 * output from the parser are suppressed.
	 */
	public final int getDebugLevel() {
		return yydebug;
	}

	/**
	 * Set the verbosity of the debugging output; 0 means that all kinds of
	 * output from the parser are suppressed.
	 * 
	 * @param level
	 *            The verbosity level for debugging output.
	 */
	public final void setDebugLevel(int level) {
		yydebug = level;
	}

	private final int yylex() throws java.io.IOException {
		return yylexer.yylex();
	}

	protected final void yyerror(String s) {
		yylexer.yyerror(s);
	}

	protected final void yycdebug(String s) {
		if (yydebug > 0)
			yyDebugStream.println(s);
	}

	private final class YYStack {
		private int[] stateStack = new int[16];

		private Object[] valueStack = new Object[16];

		public int size = 16;
		public int height = -1;

		public final void push(int state, Object value) {
			height++;
			if (size == height) {
				int[] newStateStack = new int[size * 2];
				System.arraycopy(stateStack, 0, newStateStack, 0, height);
				stateStack = newStateStack;

				Object[] newValueStack = new Object[size * 2];
				System.arraycopy(valueStack, 0, newValueStack, 0, height);
				valueStack = newValueStack;

				size *= 2;
			}

			stateStack[height] = state;

			valueStack[height] = value;
		}

		public final void pop() {
			pop(1);
		}

		public final void pop(int num) {
			// Avoid memory leaks... garbage collection is a white lie!
			if (num > 0) {
				java.util.Arrays.fill(valueStack, height - num + 1, height + 1,
						null);

			}
			height -= num;
		}

		public final int stateAt(int i) {
			return stateStack[height - i];
		}

		public final Object valueAt(int i) {
			return valueStack[height - i];
		}

		// Print the state stack on the debug stream.
		public void print(java.io.PrintStream out) {
			out.print("Stack now");

			for (int i = 0; i <= height; i++) {
				out.print(' ');
				out.print(stateStack[i]);
			}
			out.println();
		}
	}

	/**
	 * Returned by a Bison action in order to stop the parsing process and
	 * return success (<tt>true</tt>).
	 */
	public static final int YYACCEPT = 0;

	/**
	 * Returned by a Bison action in order to stop the parsing process and
	 * return failure (<tt>false</tt>).
	 */
	public static final int YYABORT = 1;

	/**
	 * Returned by a Bison action in order to start error recovery without
	 * printing an error message.
	 */
	public static final int YYERROR = 2;

	// Internal return codes that are not supported for user semantic
	// actions.
	private static final int YYERRLAB = 3;
	private static final int YYNEWSTATE = 4;
	private static final int YYDEFAULT = 5;
	private static final int YYREDUCE = 6;
	private static final int YYERRLAB1 = 7;
	private static final int YYRETURN = 8;

	private int yyerrstatus_ = 0;

	/**
	 * Return whether error recovery is being done. In this state, the parser
	 * reads token until it reaches a known state, and then restarts normal
	 * operation.
	 */
	public final boolean recovering() {
		return yyerrstatus_ == 0;
	}

	private int yyaction(int yyn, YYStack yystack, int yylen) {
		Object yyval;

		/*
		 * If YYLEN is nonzero, implement the default value of the action: `$$ =
		 * $1'. Otherwise, use the top of the stack.
		 * 
		 * Otherwise, the following line sets YYVAL to garbage. This behavior is
		 * undocumented and Bison users should not rely upon it.
		 */
		if (yylen > 0)
			yyval = yystack.valueAt(yylen - 1);
		else
			yyval = yystack.valueAt(0);

		yy_reduce_print(yyn, yystack);

		switch (yyn) {
		case 2:
			if (yyn == 2)

			/* Line 351 of lalr1.java */
			/* Line 229 of "PolicyLang.y" */
			{
				finalPolicy = ((CpabePolicy) (yystack.valueAt(1 - (1))));
			}
			;
			break;

		case 3:
			if (yyn == 3)

			/* Line 351 of lalr1.java */
			/* Line 231 of "PolicyLang.y" */
			{
				yyval = expInt(((Integer) (yystack.valueAt(3 - (1)))),
						((Integer) (yystack.valueAt(3 - (3)))));
			}
			;
			break;

		case 4:
			if (yyn == 4)

			/* Line 351 of lalr1.java */
			/* Line 232 of "PolicyLang.y" */
			{
				yyval = flexInt(((Integer) (yystack.valueAt(1 - (1)))));
			}
			;
			break;

		case 5:
			if (yyn == 5)

			/* Line 351 of lalr1.java */
			/* Line 234 of "PolicyLang.y" */
			{
				yyval = leafPolicy(((String) (yystack.valueAt(1 - (1)))));
			}
			;
			break;

		case 6:
			if (yyn == 6)

			/* Line 351 of lalr1.java */
			/* Line 235 of "PolicyLang.y" */
			{
				yyval = kOf2Policy(1,
						((CpabePolicy) (yystack.valueAt(3 - (1)))),
						((CpabePolicy) (yystack.valueAt(3 - (3)))));
			}
			;
			break;

		case 7:
			if (yyn == 7)

			/* Line 351 of lalr1.java */
			/* Line 236 of "PolicyLang.y" */
			{
				yyval = kOf2Policy(2,
						((CpabePolicy) (yystack.valueAt(3 - (1)))),
						((CpabePolicy) (yystack.valueAt(3 - (3)))));
			}
			;
			break;

		case 8:
			if (yyn == 8)

			/* Line 351 of lalr1.java */
			/* Line 237 of "PolicyLang.y" */
			{
				yyval = kOfPolicy(((Integer) (yystack.valueAt(5 - (1)))),
						((ArrayList) (yystack.valueAt(5 - (4)))));
			}
			;
			break;

		case 9:
			if (yyn == 9)

			/* Line 351 of lalr1.java */
			/* Line 238 of "PolicyLang.y" */
			{
				yyval = eqPolicy(((SizedInteger) (yystack.valueAt(3 - (3)))),
						((String) (yystack.valueAt(3 - (1)))));
			}
			;
			break;

		case 10:
			if (yyn == 10)

			/* Line 351 of lalr1.java */
			/* Line 239 of "PolicyLang.y" */
			{
				yyval = ltPolicy(((SizedInteger) (yystack.valueAt(3 - (3)))),
						((String) (yystack.valueAt(3 - (1)))));
			}
			;
			break;

		case 11:
			if (yyn == 11)

			/* Line 351 of lalr1.java */
			/* Line 240 of "PolicyLang.y" */
			{
				yyval = gtPolicy(((SizedInteger) (yystack.valueAt(3 - (3)))),
						((String) (yystack.valueAt(3 - (1)))));
			}
			;
			break;

		case 12:
			if (yyn == 12)

			/* Line 351 of lalr1.java */
			/* Line 241 of "PolicyLang.y" */
			{
				yyval = lePolicy(((SizedInteger) (yystack.valueAt(3 - (3)))),
						((String) (yystack.valueAt(3 - (1)))));
			}
			;
			break;

		case 13:
			if (yyn == 13)

			/* Line 351 of lalr1.java */
			/* Line 242 of "PolicyLang.y" */
			{
				yyval = gePolicy(((SizedInteger) (yystack.valueAt(3 - (3)))),
						((String) (yystack.valueAt(3 - (1)))));
			}
			;
			break;

		case 14:
			if (yyn == 14)

			/* Line 351 of lalr1.java */
			/* Line 243 of "PolicyLang.y" */
			{
				yyval = eqPolicy(((SizedInteger) (yystack.valueAt(3 - (1)))),
						((String) (yystack.valueAt(3 - (3)))));
			}
			;
			break;

		case 15:
			if (yyn == 15)

			/* Line 351 of lalr1.java */
			/* Line 244 of "PolicyLang.y" */
			{
				yyval = gtPolicy(((SizedInteger) (yystack.valueAt(3 - (1)))),
						((String) (yystack.valueAt(3 - (3)))));
			}
			;
			break;

		case 16:
			if (yyn == 16)

			/* Line 351 of lalr1.java */
			/* Line 245 of "PolicyLang.y" */
			{
				yyval = ltPolicy(((SizedInteger) (yystack.valueAt(3 - (1)))),
						((String) (yystack.valueAt(3 - (3)))));
			}
			;
			break;

		case 17:
			if (yyn == 17)

			/* Line 351 of lalr1.java */
			/* Line 246 of "PolicyLang.y" */
			{
				yyval = gePolicy(((SizedInteger) (yystack.valueAt(3 - (1)))),
						((String) (yystack.valueAt(3 - (3)))));
			}
			;
			break;

		case 18:
			if (yyn == 18)

			/* Line 351 of lalr1.java */
			/* Line 247 of "PolicyLang.y" */
			{
				yyval = lePolicy(((SizedInteger) (yystack.valueAt(3 - (1)))),
						((String) (yystack.valueAt(3 - (3)))));
			}
			;
			break;

		case 19:
			if (yyn == 19)

			/* Line 351 of lalr1.java */
			/* Line 248 of "PolicyLang.y" */
			{
				yyval = ((CpabePolicy) (yystack.valueAt(3 - (2))));
			}
			;
			break;

		case 20:
			if (yyn == 20)

			/* Line 351 of lalr1.java */
			/* Line 250 of "PolicyLang.y" */
			{
				yyval = new ArrayList();
				((ArrayList) yyval).add(((CpabePolicy) (yystack
						.valueAt(1 - (1)))));
			}
			;
			break;

		case 21:
			if (yyn == 21)

			/* Line 351 of lalr1.java */
			/* Line 252 of "PolicyLang.y" */
			{
				yyval = ((ArrayList) (yystack.valueAt(3 - (1))));
				((ArrayList) yyval).add(((CpabePolicy) (yystack
						.valueAt(3 - (3)))));
			}
			;
			break;

		/* Line 351 of lalr1.java */
		/* Line 485 of "PolicyLang.java" */
		default:
			break;
		}

		yy_symbol_print("-> $$ =", yyr1_[yyn], yyval);

		yystack.pop(yylen);
		yylen = 0;

		/* Shift the result of the reduction. */
		yyn = yyr1_[yyn];
		int yystate = yypgoto_[yyn - yyntokens_] + yystack.stateAt(0);
		if (0 <= yystate && yystate <= yylast_
				&& yycheck_[yystate] == yystack.stateAt(0))
			yystate = yytable_[yystate];
		else
			yystate = yydefgoto_[yyn - yyntokens_];

		yystack.push(yystate, yyval);
		return YYNEWSTATE;
	}

	/*
	 * Return YYSTR after stripping away unnecessary quotes and backslashes, so
	 * that it's suitable for yyerror. The heuristic is that double-quoting is
	 * unnecessary unless the string contains an apostrophe, a comma, or
	 * backslash (other than backslash-backslash). YYSTR is taken from yytname.
	 */
	private final String yytnamerr_(String yystr) {
		if (yystr.charAt(0) == '"') {
			StringBuffer yyr = new StringBuffer();
			strip_quotes: for (int i = 1; i < yystr.length(); i++)
				switch (yystr.charAt(i)) {
				case '\'':
				case ',':
					break strip_quotes;

				case '\\':
					if (yystr.charAt(++i) != '\\')
						break strip_quotes;
					/* Fall through. */
				default:
					yyr.append(yystr.charAt(i));
					break;

				case '"':
					return yyr.toString();
				}
		} else if (yystr.equals("$end"))
			return "end of input";

		return yystr;
	}

	/*--------------------------------.
	| Print this symbol on YYOUTPUT.  |
	`--------------------------------*/

	private void yy_symbol_print(String s, int yytype, Object yyvaluep) {
		if (yydebug > 0)
			yycdebug(s + (yytype < yyntokens_ ? " token " : " nterm ")
					+ yytname_[yytype] + " ("
					+ (yyvaluep == null ? "(null)" : yyvaluep.toString()) + ")");
	}

	/**
	 * Parse input from the scanner that was specified at object construction
	 * time. Return whether the end of the input was reached successfully.
	 * 
	 * @return <tt>true</tt> if the parsing succeeds. Note that this does not
	 *         imply that there were no syntax errors.
	 */
	public boolean parse() throws java.io.IOException {
		// / Lookahead and lookahead in internal form.
		int yychar = yyempty_;
		int yytoken = 0;

		/* State. */
		int yyn = 0;
		int yylen = 0;
		int yystate = 0;

		YYStack yystack = new YYStack();

		/* Error handling. */
		int yynerrs_ = 0;

		// / Semantic value of the lookahead.
		Object yylval = null;

		int yyresult;

		yycdebug("Starting parse\n");
		yyerrstatus_ = 0;

		/* Initialize the stack. */
		yystack.push(yystate, yylval);

		int label = YYNEWSTATE;
		for (;;)
			switch (label) {
			/*
			 * New state. Unlike in the C/C++ skeletons, the state is already
			 * pushed when we come here.
			 */
			case YYNEWSTATE:
				yycdebug("Entering state " + yystate + "\n");
				if (yydebug > 0)
					yystack.print(yyDebugStream);

				/* Accept? */
				if (yystate == yyfinal_)
					return true;

				/* Take a decision. First try without lookahead. */
				yyn = yypact_[yystate];
				if (yy_pact_value_is_default_(yyn)) {
					label = YYDEFAULT;
					break;
				}

				/* Read a lookahead token. */
				if (yychar == yyempty_) {
					yycdebug("Reading a token: ");
					yychar = yylex();

					yylval = yylexer.getLVal();
				}

				/* Convert token to internal form. */
				if (yychar <= EOF) {
					yychar = yytoken = EOF;
					yycdebug("Now at end of input.\n");
				} else {
					yytoken = yytranslate_(yychar);
					yy_symbol_print("Next token is", yytoken, yylval);
				}

				/*
				 * If the proper action on seeing token YYTOKEN is to reduce or
				 * to detect an error, take that action.
				 */
				yyn += yytoken;
				if (yyn < 0 || yylast_ < yyn || yycheck_[yyn] != yytoken)
					label = YYDEFAULT;

				/* <= 0 means reduce or error. */
				else if ((yyn = yytable_[yyn]) <= 0) {
					if (yy_table_value_is_error_(yyn))
						label = YYERRLAB;
					else {
						yyn = -yyn;
						label = YYREDUCE;
					}
				}

				else {
					/* Shift the lookahead token. */
					yy_symbol_print("Shifting", yytoken, yylval);

					/* Discard the token being shifted. */
					yychar = yyempty_;

					/*
					 * Count tokens shifted since error; after three, turn off
					 * error status.
					 */
					if (yyerrstatus_ > 0)
						--yyerrstatus_;

					yystate = yyn;
					yystack.push(yystate, yylval);
					label = YYNEWSTATE;
				}
				break;

			/*-----------------------------------------------------------.
			| yydefault -- do the default action for the current state.  |
			`-----------------------------------------------------------*/
			case YYDEFAULT:
				yyn = yydefact_[yystate];
				if (yyn == 0)
					label = YYERRLAB;
				else
					label = YYREDUCE;
				break;

			/*-----------------------------.
			| yyreduce -- Do a reduction.  |
			`-----------------------------*/
			case YYREDUCE:
				yylen = yyr2_[yyn];
				label = yyaction(yyn, yystack, yylen);
				yystate = yystack.stateAt(0);
				break;

			/*------------------------------------.
			| yyerrlab -- here on detecting error |
			`------------------------------------*/
			case YYERRLAB:
				/* If not already recovering from an error, report this error. */
				if (yyerrstatus_ == 0) {
					++yynerrs_;
					if (yychar == yyempty_)
						yytoken = yyempty_;
					yyerror(yysyntax_error(yystate, yytoken));
				}

				if (yyerrstatus_ == 3) {
					/*
					 * If just tried and failed to reuse lookahead token after
					 * an error, discard it.
					 */

					if (yychar <= EOF) {
						/* Return failure if at end of input. */
						if (yychar == EOF)
							return false;
					} else
						yychar = yyempty_;
				}

				/*
				 * Else will try to reuse lookahead token after shifting the
				 * error token.
				 */
				label = YYERRLAB1;
				break;

			/*---------------------------------------------------.
			| errorlab -- error raised explicitly by YYERROR.  |
			`---------------------------------------------------*/
			case YYERROR:

				/*
				 * Do not reclaim the symbols of the rule which action triggered
				 * this YYERROR.
				 */
				yystack.pop(yylen);
				yylen = 0;
				yystate = yystack.stateAt(0);
				label = YYERRLAB1;
				break;

			/*-------------------------------------------------------------.
			| yyerrlab1 -- common code for both syntax error and YYERROR.  |
			`-------------------------------------------------------------*/
			case YYERRLAB1:
				yyerrstatus_ = 3; /* Each real token shifted decrements this. */

				for (;;) {
					yyn = yypact_[yystate];
					if (!yy_pact_value_is_default_(yyn)) {
						yyn += yyterror_;
						if (0 <= yyn && yyn <= yylast_
								&& yycheck_[yyn] == yyterror_) {
							yyn = yytable_[yyn];
							if (0 < yyn)
								break;
						}
					}

					/*
					 * Pop the current state because it cannot handle the error
					 * token.
					 */
					if (yystack.height == 1)
						return false;

					yystack.pop();
					yystate = yystack.stateAt(0);
					if (yydebug > 0)
						yystack.print(yyDebugStream);
				}

				/* Shift the error token. */
				yy_symbol_print("Shifting", yystos_[yyn], yylval);

				yystate = yyn;
				yystack.push(yyn, yylval);
				label = YYNEWSTATE;
				break;

			/* Accept. */
			case YYACCEPT:
				return true;

				/* Abort. */
			case YYABORT:
				return false;
			}
	}

	// Generate an error message.
	private String yysyntax_error(int yystate, int tok) {
		if (errorVerbose) {
			/*
			 * There are many possibilities here to consider: - Assume YYFAIL is
			 * not used. It's too flawed to consider. See
			 * <http://lists.gnu.org/archive
			 * /html/bison-patches/2009-12/msg00024.html> for details. YYERROR
			 * is fine as it does not invoke this function. - If this state is a
			 * consistent state with a default action, then the only way this
			 * function was invoked is if the default action is an error action.
			 * In that case, don't check for expected tokens because there are
			 * none. - The only way there can be no lookahead present (in tok)
			 * is if this state is a consistent state with a default action.
			 * Thus, detecting the absence of a lookahead is sufficient to
			 * determine that there is no unexpected or expected token to
			 * report. In that case, just report a simple "syntax error". -
			 * Don't assume there isn't a lookahead just because this state is a
			 * consistent state with a default action. There might have been a
			 * previous inconsistent state, consistent state with a non-default
			 * action, or user semantic action that manipulated yychar.
			 * (However, yychar is currently out of scope during semantic
			 * actions.) - Of course, the expected token list depends on states
			 * to have correct lookahead information, and it depends on the
			 * parser not to perform extra reductions after fetching a lookahead
			 * from the scanner and before detecting a syntax error. Thus, state
			 * merging (from LALR or IELR) and default reductions corrupt the
			 * expected token list. However, the list is correct for canonical
			 * LR with one exception: it will still contain any token that will
			 * not be accepted due to an error action in a later state.
			 */
			if (tok != yyempty_) {
				// FIXME: This method of building the message is not compatible
				// with internationalization.
				StringBuffer res = new StringBuffer("syntax error, unexpected ");
				res.append(yytnamerr_(yytname_[tok]));
				int yyn = yypact_[yystate];
				if (!yy_pact_value_is_default_(yyn)) {
					/*
					 * Start YYX at -YYN if negative to avoid negative indexes
					 * in YYCHECK. In other words, skip the first -YYN actions
					 * for this state because they are default actions.
					 */
					int yyxbegin = yyn < 0 ? -yyn : 0;
					/* Stay within bounds of both yycheck and yytname. */
					int yychecklim = yylast_ - yyn + 1;
					int yyxend = yychecklim < yyntokens_ ? yychecklim
							: yyntokens_;
					int count = 0;
					for (int x = yyxbegin; x < yyxend; ++x)
						if (yycheck_[x + yyn] == x && x != yyterror_
								&& !yy_table_value_is_error_(yytable_[x + yyn]))
							++count;
					if (count < 5) {
						count = 0;
						for (int x = yyxbegin; x < yyxend; ++x)
							if (yycheck_[x + yyn] == x
									&& x != yyterror_
									&& !yy_table_value_is_error_(yytable_[x
											+ yyn])) {
								res.append(count++ == 0 ? ", expecting "
										: " or ");
								res.append(yytnamerr_(yytname_[x]));
							}
					}
				}
				return res.toString();
			}
		}

		return "syntax error";
	}

	/**
	 * Whether the given <code>yypact_</code> value indicates a defaulted state.
	 * 
	 * @param yyvalue
	 *            the value to check
	 */
	private static boolean yy_pact_value_is_default_(int yyvalue) {
		return yyvalue == yypact_ninf_;
	}

	/**
	 * Whether the given <code>yytable_</code> value indicates a syntax error.
	 * 
	 * @param yyvalue
	 *            the value to check
	 */
	private static boolean yy_table_value_is_error_(int yyvalue) {
		return yyvalue == yytable_ninf_;
	}

	/*
	 * YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
	 * STATE-NUM.
	 */
	private static final byte yypact_ninf_ = -5;
	private static final byte yypact_[] = { -2, -1, -4, -2, 4, 2, 17, 1, 1, 1,
			1, 1, 13, 29, 15, -5, 32, 33, 34, 37, 38, -2, -2, 35, -5, -5, -5,
			-5, -5, -2, -5, -5, -5, -5, -5, -5, -5, 19, -5, 17, 22, -5, -2, 17 };

	/*
	 * YYDEFACT[S] -- default reduction number in state S. Performed when
	 * YYTABLE doesn't specify something else to do. Zero means the default is
	 * an error.
	 */
	private static final byte yydefact_[] = { 0, 5, 4, 0, 0, 0, 2, 0, 0, 0, 0,
			0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 4, 12, 13, 9, 10, 11, 0, 3, 19,
			17, 18, 14, 15, 16, 6, 7, 20, 0, 8, 0, 21 };

	/* YYPGOTO[NTERM-NUM]. */
	private static final byte yypgoto_[] = { -5, -5, 21, -3, -5 };

	/* YYDEFGOTO[NTERM-NUM]. */
	private static final byte yydefgoto_[] = { -1, 4, 5, 6, 40 };

	/*
	 * YYTABLE[YYPACT[STATE-NUM]]. What to do in state STATE-NUM. If positive,
	 * shift that token. If negative, reduce the rule which number is the
	 * opposite. If YYTABLE_NINF_, syntax error.
	 */
	private static final byte yytable_ninf_ = -1;
	private static final byte yytable_[] = { 14, 1, 2, 12, 15, 23, 13, 7, 8, 3,
			16, 17, 9, 10, 11, 18, 19, 20, 37, 38, 21, 22, 21, 22, 29, 22, 39,
			31, 24, 25, 26, 27, 28, 30, 41, 32, 33, 34, 42, 43, 35, 36, 0, 0,
			0, 13 };

	/* YYCHECK. */
	private static final byte yycheck_[] = { 3, 3, 4, 7, 0, 4, 10, 8, 9, 11, 8,
			9, 13, 14, 15, 13, 14, 15, 21, 22, 5, 6, 5, 6, 11, 6, 29, 12, 7, 8,
			9, 10, 11, 4, 12, 3, 3, 3, 16, 42, 3, 3, -1, -1, -1, 10 };

	/*
	 * STOS_[STATE-NUM] -- The (internal number of the) accessing symbol of
	 * state STATE-NUM.
	 */
	private static final byte yystos_[] = { 0, 3, 4, 11, 18, 19, 20, 8, 9, 13,
			14, 15, 7, 10, 20, 0, 8, 9, 13, 14, 15, 5, 6, 4, 19, 19, 19, 19,
			19, 11, 4, 12, 3, 3, 3, 3, 3, 20, 20, 20, 21, 12, 16, 20 };

	/*
	 * TOKEN_NUMBER_[YYLEX-NUM] -- Internal symbol number corresponding to
	 * YYLEX-NUM.
	 */
	private static final short yytoken_number_[] = { 0, 256, 257, 258, 259,
			260, 261, 262, 263, 264, 35, 40, 41, 61, 60, 62, 44 };

	/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives. */
	private static final byte yyr1_[] = { 0, 17, 18, 19, 19, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 21, 21 };

	/* YYR2[YYN] -- Number of symbols composing right hand side of rule YYN. */
	private static final byte yyr2_[] = { 0, 2, 1, 3, 1, 1, 3, 3, 5, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 1, 3 };

	/*
	 * YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM. First, the
	 * terminals, then, starting at \a yyntokens_, nonterminals.
	 */
	private static final String yytname_[] = { "$end", "error", "$undefined",
			"TAG", "INTLIT", "OR", "AND", "OF", "LEQ", "GEQ", "'#'", "'('",
			"')'", "'='", "'<'", "'>'", "','", "$accept", "result", "number",
			"policy", "argList", null };

	/* YYRHS -- A `-1'-separated list of the rules' RHS. */
	private static final byte yyrhs_[] = { 18, 0, -1, 20, -1, 4, 10, 4, -1, 4,
			-1, 3, -1, 20, 5, 20, -1, 20, 6, 20, -1, 4, 7, 11, 21, 12, -1, 3,
			13, 19, -1, 3, 14, 19, -1, 3, 15, 19, -1, 3, 8, 19, -1, 3, 9, 19,
			-1, 19, 13, 3, -1, 19, 14, 3, -1, 19, 15, 3, -1, 19, 8, 3, -1, 19,
			9, 3, -1, 11, 20, 12, -1, 20, -1, 21, 16, 20, -1 };

	/*
	 * YYPRHS[YYN] -- Index of the first RHS symbol of rule number YYN in YYRHS.
	 */
	private static final byte yyprhs_[] = { 0, 0, 3, 5, 9, 11, 13, 17, 21, 27,
			31, 35, 39, 43, 47, 51, 55, 59, 63, 67, 71, 73 };

	/* YYRLINE[YYN] -- Source line where rule number YYN was defined. */
	private static final short yyrline_[] = { 0, 229, 229, 231, 232, 234, 235,
			236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248,
			250, 252 };

	// Report on the debug stream that the rule yyrule is going to be reduced.
	private void yy_reduce_print(int yyrule, YYStack yystack) {
		if (yydebug == 0)
			return;

		int yylno = yyrline_[yyrule];
		int yynrhs = yyr2_[yyrule];
		/* Print the symbols being reduced, and their result. */
		yycdebug("Reducing stack by rule " + (yyrule - 1) + " (line " + yylno
				+ "), ");

		/* The symbols being reduced. */
		for (int yyi = 0; yyi < yynrhs; yyi++)
			yy_symbol_print("   $" + (yyi + 1) + " =", yyrhs_[yyprhs_[yyrule]
					+ yyi], ((yystack.valueAt(yynrhs - (yyi + 1)))));
	}

	/* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX. */
	private static final byte yytranslate_table_[] = { 0, 2, 2, 2, 2, 2, 2, 2,
			2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
			2, 2, 2, 2, 2, 10, 2, 2, 2, 2, 11, 12, 2, 2, 16, 2, 2, 2, 2, 2, 2,
			2, 2, 2, 2, 2, 2, 2, 2, 2, 14, 13, 15, 2, 2, 2, 2, 2, 2, 2, 2, 2,
			2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
			2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
			2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
			2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
			2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
			2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
			2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
			2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
			2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	private static final byte yytranslate_(int t) {
		if (t >= 0 && t <= yyuser_token_number_max_)
			return yytranslate_table_[t];
		else
			return yyundef_token_;
	}

	private static final int yylast_ = 45;
	private static final int yynnts_ = 5;
	private static final int yyempty_ = -2;
	private static final int yyfinal_ = 15;
	private static final int yyterror_ = 1;
	private static final int yyerrcode_ = 256;
	private static final int yyntokens_ = 17;

	private static final int yyuser_token_number_max_ = 264;
	private static final int yyundef_token_ = 2;

	/* User implementation code. */
	/* Unqualified %code blocks. */

	/* Line 927 of lalr1.java */
	/* Line 8 of "PolicyLang.y" */

	public CpabePolicy finalPolicy;

	public static void main(String args[]) {
		// PolicyLang p = new PolicyLang();
		// String[]

	}

	/* Line 927 of lalr1.java */
	/* Line 26 of "PolicyLang.y" */

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

	private CpabePolicy cmpPolicy(SizedInteger n, boolean gt, String attr) {
		CpabePolicy p = new CpabePolicy();
		String tplate;

		/* some error checking */
		if (gt && (n.value >= (1 << (n.bits > 0 ? n.bits : 31) - 1))) {
			System.out
					.println("error parsing policy: unsatisfiable integer comparison "
							+ attr
							+ " > "
							+ n.value
							+ "\n("
							+ (n.bits > 0 ? n.bits : 31)
							+ "-bits are insufficient to satisfy)");
			System.exit(0);
		} else if (!gt && n.value == 0) {
			System.out
					.println("error parsing policy: unsatisfiable integer comparison "
							+ attr
							+ " < 0 "
							+ "\n(all numerical attributes are unsigned)");
			System.exit(0);
		} else if (!gt && (n.value > (1 << (n.bits > 0 ? n.bits : 31) - 1))) {
			System.out
					.println("error parsing policy: trivially satisfied integer comparison "
							+ attr
							+ " < "
							+ n.value
							+ "\n("
							+ (n.bits > 0 ? n.bits : 31)
							+ "-bits number will satisfy)");
			System.exit(0);
		}

		/* create it */

		/* horrible */
		tplate = n.bits > 0 ? ("_expint" + n.bits + "_")
				: ("_flexint_");

		int bits = n.bits > 0 ? n.bits : (n.value >= (1 << 16) ? 32
				: n.value >= (1 << 8) ? 16 : n.value >= (1 << 4) ? 8
						: n.value >= (1 << 2) ? 4 : 2);
		p = bitMarkerList(gt, attr, tplate, bits, n.value);

		return null;
	}

	private CpabePolicy bitMarkerList(boolean gt, String attr, String tplate,
			int bits, long value) {
		CpabePolicy p = new CpabePolicy();
		int i;

		i = 0;
		while (gt ? (1 << i & value) > 0 : (1 << i & value) <= 0)
			i++;

		p = leafPolicy(bitMarker(attr, tplate, i, gt));
		for (i = i + 1; i < bits; i++)
			if (gt)
				p = kOf2Policy((1 << i & value) > 0 ? 2 : 1, p,
						leafPolicy(bitMarker(attr, tplate, i, gt)));
			else
				p = kOf2Policy((1 << i & value) > 0 ? 1 : 2, p,
						leafPolicy(bitMarker(attr, tplate, i, gt)));
		return p;
	}

	private String bitMarker(String base, String tplate, int bit, boolean gt) {
		String lx;
		String rx;
		String s;
		
		
		
		
		// TODO Auto-generated method stub
		return null;
	}

	class PolicyLangLexer implements PolicyLang.Lexer {
		StringTokenizer st;

		public PolicyLangLexer(String str) {
			st = new StringTokenizer(str);
		}

		public void yyerror(String s) {
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
				else if (str.equals("|") || str.equalsIgnoreCase("or"))
					r = OR;
				else if (str.equalsIgnoreCase("of"))
					r = OF;
				else if (str.equals("(") || str.equals(")") || str.equals(",")
						|| str.equals("<") || str.equals(">")
						|| str.equals("=") || str.equals("#"))
					r = str.charAt(0);
				else if (str.equals("<="))
					r = LEQ;
				else if (str.equals(">="))
					r = GEQ;
				else if (isDigit(str)) {
					yylval = Integer.parseInt(str);
					r = INTLIT;
				} else if (isChars(str)) {
					yylval = str;
					r = TAG;
				} else {
					System.out.println("syntax error at " + str);
					System.exit(0);
				}
			}

			return r;
		}

		private boolean isDigit(String str) {
			for (int i = 0; i < str.length(); i++)
				if (!Character.isDigit(str.charAt(i)))
					return false;
			return true;
		}

		private boolean isChars(String str) {
			if (!Character.isLetter(str.charAt(0)))
				return false;
			for (int i = 1; i < str.length(); i++)
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
		long value;
		int bits;
	}

	/* Line 927 of lalr1.java */
	/* Line 1305 of "PolicyLang.java" */

}

/* Line 931 of lalr1.java */
/* Line 256 of "PolicyLang.y" */

