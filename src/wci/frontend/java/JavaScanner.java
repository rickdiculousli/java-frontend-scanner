package wci.frontend.java;

import wci.frontend.*;
import wci.frontend.java.JavaTokenType;
import wci.frontend.java.tokens.*;

import static wci.frontend.Source.EOF;
import static wci.frontend.Source.EOL;
import static wci.frontend.java.JavaTokenType.*;
import static wci.frontend.java.JavaErrorCode.*;


public class JavaScanner extends Scanner{

	public JavaScanner(Source source) {
		super(source);

	}

	@Override
	protected Token extractToken() throws Exception {
		
		skipWhiteSpace();
		
		Token token;
		char currentChar = currentChar();
		 // Construct the next token.  The current character determines the
        // token type.
        if (currentChar == EOF) {
            token = new EofToken(source);
        }
        else if (Character.isLetter(currentChar)) {
            token = new JavaWordToken(source);
        }
        else if (Character.isDigit(currentChar)) {
            token = new JavaNumberToken(source);
        }
        else if (currentChar == '\'' || currentChar == '\"') {
            token = new JavaStringToken(source);
        }

        else if (JavaTokenType.SPECIAL_SYMBOLS
                 .containsKey(Character.toString(currentChar))) {
            token = new JavaSpecialSymbolToken(source);
        }
        else {
            token = new JavaErrorToken(source, INVALID_CHARACTER,
                                         Character.toString(currentChar));
            nextChar();  // consume character
        }

        return token;
	}
	
    /**
     * Skip whitespace characters by consuming them.  A comment is whitespace.
     * @throws Exception if an error occurred.
     */
	private void skipWhiteSpace() throws Exception{
		char currentChar = currentChar();
		
		while (Character.isWhitespace(currentChar) || (currentChar == '/')){
			
			// Start of a comment?
			if(currentChar == '/') {
				currentChar = nextChar();
				
				//Is single line comment?
				if(currentChar == '/') {
					do {
						currentChar = nextChar();
					} while ((currentChar != EOL) && (currentChar != EOF));
				}
				
				//Is multiline comment?
				else if(currentChar == '*') {
					boolean end = false;
					do {
						currentChar = nextChar();
						if((currentChar == '*') && (peekChar() == '/')) { //reached the end?
							end = true;
							break;
						}
					} while (currentChar != EOF);
					if(end) {
						currentChar = nextChar(); // consumes '*'
						currentChar = nextChar(); // consumes '/'
					}
				}
			}
			
			// Not a comment.
			else {
				currentChar = nextChar(); //consume whitespace character.
			}
		}
	}
	
	public char peekChar() throws Exception{
		return super.source.peekChar();
	}

}
