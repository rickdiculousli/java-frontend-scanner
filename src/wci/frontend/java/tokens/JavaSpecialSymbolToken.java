package wci.frontend.java.tokens;

import wci.frontend.*;

import wci.frontend.java.*;

import static wci.frontend.java.JavaTokenType.*;
import static wci.frontend.java.JavaErrorCode.*;
import static wci.frontend.Source.EOF;

public class JavaSpecialSymbolToken extends JavaToken
{
    /**
     * Constructor.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public JavaSpecialSymbolToken(Source source)
        throws Exception
    {
        super(source);
    }

    /**
     * Extract a Pascal special symbol token from the source.
     * @throws Exception if an error occurred.
     */
    protected void extract()
        throws Exception
    {
        char currentChar = currentChar();

        text = Character.toString(currentChar);
        type = null;

        switch (currentChar) {

            // Single-character special symbols.
            case '~':  case '@':  case ':':  case ';':  case '?':  
            case ',':  case '\'': case '.':  case '\"':  case ')':  
            case '(':  case ']':  case '[': 
            case '}':  case '{': {
                nextChar();  // consume character
                break;
            }
                    
            // % or %=
            case '%': 
            // ^ or ^=
            case '^': 
                // = or ==
            case '=':
                // ! or !=
            case '!':
                //
                {
                    currentChar = nextChar();  // consume '%';

                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();  // consume '='
                    
                }
                break;
            }
                // + or ++ or +=
            case '+': {
                    currentChar = nextChar(); //consume '+'
                    
                    if (currentChar == '=') {
                    text += currentChar;
                    nextChar();  // consume '='
                }
                else if (currentChar == '+') {
                        text += currentChar;
                        nextChar(); //consume '+'
                }
                    break;
            }
            // - or -- or -=
            case '-': {
                    currentChar = nextChar(); // consume '-'    
                    
                    if (currentChar == '=') {
                        text += currentChar;
                        nextChar();  // consume '='
                    }
                    else if (currentChar == '-') {
                        text += currentChar;
                        nextChar(); //consume '+'
                    }
                    break;
            }

            // < or << or <<=
            case '<': {
                currentChar = nextChar();  // consume '<';

                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();  // consume '='
                }
                else if (currentChar == '<') {
                        text += currentChar;
                        currentChar = nextChar(); //consume '<'
                        if (currentChar == '=') {
                        	text += currentChar;
                        	nextChar();
                        }
                        break;
                }
                /**
                 else if (currentChar == '>') {
                    text += currentChar;
                    nextChar();  // consume '>'
                }

                break;
                */
                break;
            }

            // > or >= or >> or >>=
            case '>': {
                currentChar = nextChar();  // consume '>';

                if (currentChar == '=') {
                        text += currentChar;
                    nextChar();  // consume '='
                }
                else if (currentChar == '>') {
                        text += currentChar;
                        currentChar = nextChar(); //consume '>'
                        if (currentChar == '=') {
                        	text += currentChar;
                        	nextChar();
                        }
                }

                break;
            }

            
            // | or || or |=
            case '|': {
                    currentChar = nextChar(); //consume '|'
                    
                    if (currentChar == '=') {
                        text += currentChar();
                        nextChar(); //consume '='
                    }
                    else if (currentChar == '|') {
                        text += currentChar;
                     nextChar(); //consume '|'
                    }
                    break;
                  
            }
            // * or *=
            case '*': {
                    currentChar = nextChar(); //consume '*'
                    
                    if (currentChar == '=') {
                        text += currentChar();
                        nextChar(); //consume '&'
                    }
                    else if (currentChar == '/') {
                        text += currentChar();
                        nextChar(); //consume '/'
                    }
                    break;
            }
            // & or && or &=
            case '&': {
                    currentChar = nextChar(); //consume '&'
                
                    if (currentChar == '&') {
                        text += currentChar();
                        nextChar(); //consume '&'
                    }
                    else if (currentChar == '=') {
                        text += currentChar();
                        nextChar(); //consume '='
                    }
                    break;
            }
            // / or // or /= or /*
            case '/': {
                    currentChar = nextChar(); //consume '&'
            
                    if (currentChar == '/') {
                        text += currentChar();
                        nextChar(); //consume '/'
                    }
                    else if (currentChar == '=') {
                        text += currentChar();
                        nextChar(); //consume '='
                    }
                    else if (currentChar == '*') {
                        text += currentChar();
                        nextChar(); //consume '*'
                    }
                    break;                    
            }
            default: {
                nextChar();  // consume bad character
                type = ERROR;
                value = INVALID_CHARACTER;
            }
        }
        //if the character after symbol doesn't end ==> error
        if(!Character.isWhitespace(currentChar())) {
        	type = ERROR;
            value = INVALID_CHARACTER;
            while(!Character.isWhitespace(currentChar()) && currentChar != EOF) {
            	text += currentChar();
            	nextChar();
            }
        }
        

        // Set the type if it wasn't an error.
        if (type == null) {
            type = SPECIAL_SYMBOLS.get(text);
        }
    }
}
