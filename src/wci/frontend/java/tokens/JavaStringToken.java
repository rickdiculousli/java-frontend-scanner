package wci.frontend.java.tokens;

import wci.frontend.*;
import wci.frontend.java.*;

import static wci.frontend.Source.EOL;
import static wci.frontend.Source.EOF;
import static wci.frontend.java.JavaTokenType.*;
import static wci.frontend.java.JavaErrorCode.*;

public class JavaStringToken extends JavaToken{

	public JavaStringToken(Source source) throws Exception {
		super(source);
	}
	
	protected void extract() throws Exception {
		StringBuilder textBuffer = new StringBuilder();
		StringBuilder valueBuffer = new StringBuilder();
		
		//Test that string is a char
		if (currentChar() == '\'') {
			extractCharacter(textBuffer, valueBuffer);
		}
		else {
			extractString(textBuffer, valueBuffer);
		}
	}
	
	
	private void extractCharacter(StringBuilder textBuffer, StringBuilder valueBuffer) throws Exception {
		char currentChar = nextChar();
		textBuffer.append('\'');
		
		//Is regular char?
		if((currentChar != '\\') && (peekChar() == '\'')){
			textBuffer.append(currentChar);
			valueBuffer.append(currentChar);
			currentChar = nextChar();
		}
		//Is escaped char?
		else if (currentChar == '\\') {
			textBuffer.append(currentChar);
			currentChar = nextChar();
			
			if(currentChar == '\'') {
	            textBuffer.append(currentChar);
	            valueBuffer.append('\'');  // consume escaped character
			}
			else if(currentChar == 't') {
	            textBuffer.append(currentChar);
	            valueBuffer.append('\t');
			}
			else if(currentChar == 'n') {
	            textBuffer.append(currentChar);
	            valueBuffer.append('\n');
			}
			else if(currentChar == '\\') {
	            textBuffer.append(currentChar);
	            valueBuffer.append('\\');
			}
			
			currentChar = nextChar();
		}
		
		if (currentChar == '\'') {
            nextChar();  // consume final quote
            textBuffer.append('\'');
            
            type = CHAR;
            value = valueBuffer.toString();
        }
        else {
            type = ERROR;
            value = INVALID_CHARACTER;
        }
		
		text = textBuffer.toString();
	}
	
	private void extractString(StringBuilder textBuffer, StringBuilder valueBuffer) throws Exception {
		char currentChar = nextChar();
		textBuffer.append('\"');
		do {
			if(currentChar == '\\') {
				textBuffer.append(currentChar);
	            currentChar = nextChar();
				if(currentChar == '\"') {
		            textBuffer.append(currentChar);
		            valueBuffer.append('\"');
				}
				else if(currentChar == 't') {
		            textBuffer.append(currentChar);
		            valueBuffer.append('\t');
				}
				else if(currentChar == 'n') {
		            textBuffer.append(currentChar);
		            valueBuffer.append('\n');
				}
				currentChar = nextChar(); 
			}
			else if((currentChar != '\"') && (currentChar != EOF)) {
				 textBuffer.append(currentChar);
	             valueBuffer.append(currentChar);
	             currentChar = nextChar();  // consume character
			}
			
		} while ((currentChar != '\"') && (currentChar != EOF));
		
		if (currentChar == '\"') {
			nextChar();
			textBuffer.append('\"');
			
			type = STRING;
			value = valueBuffer.toString();
		}
		else {
			type = ERROR;
			value = UNEXPECTED_EOF;
		}
		
		text = textBuffer.toString();
	}
}
