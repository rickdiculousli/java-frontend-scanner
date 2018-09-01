package wci.frontend.java;

import wci.frontend.*;
import wci.frontend.pascal.tokens.*;

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
		return null;
	}
	
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
