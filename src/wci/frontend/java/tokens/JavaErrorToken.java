package wci.frontend.java.tokens;

import wci.frontend.*;
import wci.frontend.java.*;

import static wci.frontend.java.JavaTokenType.*;

public class JavaErrorToken extends JavaToken{

	public JavaErrorToken(Source source, JavaErrorCode errorCode, String tokenText) throws Exception {
		super(source);
		
        this.text = tokenText;
        this.type = ERROR;
        this.value = errorCode;
	}
	
	protected void extract() throws Exception{
		
	}

}
