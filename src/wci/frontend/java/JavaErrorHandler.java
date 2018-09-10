package wci.frontend.java;

import static wci.frontend.java.JavaErrorCode.TOO_MANY_ERRORS;
import static wci.message.MessageType.SYNTAX_ERROR;

import wci.frontend.Parser;
import wci.frontend.Token;
import wci.frontend.java.JavaErrorCode;
import wci.message.Message;

public class JavaErrorHandler {
	private static final int MAX_ERRORS = 25;

    private static int errorCount = 0;   // count of syntax errors

    /**
     * Getter.
     * @return the syntax error count.
     */
    public int getErrorCount()
    {
        return errorCount;
    }
    
    public void flag(Token token, JavaErrorCode errorCode, Parser parser)
    {
        // Notify the parser's listeners.
        parser.sendMessage(new Message(SYNTAX_ERROR,
                                       new Object[] {token.getLineNumber(),
                                                     token.getPosition(),
                                                     token.getText(),
                                                     errorCode.toString()}));

        if (++errorCount > MAX_ERRORS) {
            abortTranslation(TOO_MANY_ERRORS, parser);
        }
    }

    /**
     * Abort the translation.
     * @param errorCode the error code.
     * @param parser the parser.
     */
    public void abortTranslation(JavaErrorCode errorCode, Parser parser)
    {
        // Notify the parser's listeners and then abort.
        String fatalText = "FATAL ERROR: " + errorCode.toString();
        parser.sendMessage(new Message(SYNTAX_ERROR,
                                       new Object[] {0,
                                                     0,
                                                     "",
                                                     fatalText}));
        System.exit(errorCode.getStatus());
    }
}

