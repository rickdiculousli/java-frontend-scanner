package wci.frontend.java;

import java.util.Hashtable;
import java.util.HashSet;

import wci.frontend.TokenType;
import wci.frontend.pascal.PascalTokenType;

public enum JavaTokenType implements TokenType{

//Reserved words
	ABSTRACT, DOUBLE, INT, SUPER,
	BREAK, ELSE, LONG, SWITCH,
	CASE, ENUM, NATIVE,
	CHAR, EXTENDS, RETURN, THIS,
	CLASS, FLOAT, SHORT, THROW,
	CONST, FOR, PACKAGE, VOID,
	CONTINUE, GOTO, PROTECTED, VOLATILE,
	DO, IF, STATIC, WHILE,
	
//Special Symbols
	BETWEEN("~"),
	EXCLAMATION("!"),
	ADD("@"),
	PERCENT("%"),
	UP_ARROW("^"),
	AND("&"),
	STAR("*"),
	MINUS("-"),
	PLUS("+"),
	EQUALS("="), 
	BAR("|"),
	DIVIDE("/"),
	COLON(":"),
	SEMICOLON(";"),
	QUESTION("?"),
	LESS_THAN("<"),
	GREATER_THAN(">"),
	DOT("."),
	COMMA(","),
	QUOTE("'"),
	DOUBLE_QUOTES("\""),
	LEFT_PAREN("("),
	RIGHT_PAREN(")"),
	LEFT_BRACKET("["),
	RIGHT_BRACKET("]"),
	LEFT_BRACE("{"),
	RIGHT_BRACE("}"),
	PLUS_PLUS("++"),
	MIN_MIN("--"),
	LEFT_SHIFT("<<"),
	RIGHT_SHIFT(">>"),
	LESS_EQUALS("<="),
	GREATER_EQUALS(">="), 
	PLUS_EQUALS("+="),
	MIN_EQUALS("-="),
	STAR_EQUALS("*="), 
	DIVIDE_EQUALS("/="),
	EQUALS_EQUALS("=="),
	BAR_EQUALS("|="),
	PERCENT_EQUALS("%="),
	AND_EQUALS("&="),
	UP_EQUALS("^="),
	EXCLAMATION_EQUALS("!="),
	LEFT_SHIFT_EQUALS("<<="),
	RIGHT_SHIFT_EQUALS(">>="),
	DOUBLE_BAR("||"),
	AND_AND("&&"),
	LINE_COMMENT("//"),
	OPEN_BLOCK_COMMENT("/*"),
	CLOSE_BLOCK_COMMENT("*/"),



	
	
	
	IDENTIFIER, INTEGER, REAL, STRING, ERROR, END_OF_FILE;
	
	private static final int FIRST_RESERVED_INDEX = ABSTRACT.ordinal();
    private static final int LAST_RESERVED_INDEX  = WHILE.ordinal();

    private static final int FIRST_SPECIAL_INDEX = BETWEEN.ordinal();
    private static final int LAST_SPECIAL_INDEX  = CLOSE_BLOCK_COMMENT.ordinal();

    private String text;  // token text

    /**
     * Constructor.
     */
    JavaTokenType()
    {
        this.text = this.toString().toLowerCase();
    }

    /**
     * Constructor.
     * @param text the token text.
     */
    JavaTokenType(String text)
    {
        this.text = text;
    }

    /**
     * Getter.
     * @return the token text.
     */
    public String getText()
    {
        return text;
    }

    // Set of lower-cased Java reserved word text strings.
    public static HashSet<String> RESERVED_WORDS = new HashSet<String>();
    static {
        JavaTokenType values[] = JavaTokenType.values();
        for (int i = FIRST_RESERVED_INDEX; i <= LAST_RESERVED_INDEX; ++i) {
            RESERVED_WORDS.add(values[i].getText().toLowerCase());
        }
    }

    // Hash table of Java special symbols.  Each special symbol's text
    // is the key to its Java token type.
    public static Hashtable<String, JavaTokenType> SPECIAL_SYMBOLS =
        new Hashtable<String, JavaTokenType>();
    static {
        JavaTokenType values[] = JavaTokenType.values();
        for (int i = FIRST_SPECIAL_INDEX; i <= LAST_SPECIAL_INDEX; ++i) {
            SPECIAL_SYMBOLS.put(values[i].getText(), values[i]);
        }
    }
}

