package wci.frontend.java.tokens;

import wci.frontend.*;
import wci.frontend.java.JavaToken;
import wci.frontend.java.*;

import static wci.frontend.java.JavaTokenType.*;

public class JavaWordToken extends JavaToken{

	 /**
     * Constructor.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
	
	public JavaWordToken(Source source) 
			throws Exception 
	{
	  
		super(source);
		
	}
	    
	    /**
	     * Extract a Java word token from the source.
	     * @throws Exception if an error occurred.
	     */

	    protected void extract()

	            throws Exception

	    {

	        StringBuilder textBuffer = new StringBuilder();

	        char currentChar = currentChar();



	        // Get the word characters (letter or digit).  The scanner has

	        // already determined that the first character is a letter.

	        while (Character.isLetterOrDigit(currentChar)) {

	            textBuffer.append(currentChar);

	            currentChar = nextChar();  // consume character

	        }



	        text = textBuffer.toString();



	        // Is it a reserved word or an identifier?

	        type = (RESERVED_WORDS.contains(text))

	                ? JavaTokenType.valueOf(text.toUpperCase())  // reserved word

	                : IDENTIFIER;                                  // identifier

	    }

}
	