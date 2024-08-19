package MiniJava.scanner.token;

import MiniJava.scanner.type.Type;

public class TokenFacade {
    public Token createToken(Type type, String value) {
        return new Token(type, value);
    }

    public Type determineTokenType(String input) {
        return Token.getTyepFormString(input);
    }
}
