package com.vorozco;

public class Token {
    private TokenConstants tokenType;
    private String lexeme;

    public Token(TokenConstants tokenType, String lexeme) {
        this.tokenType = tokenType;
        this.lexeme = lexeme;
    }

    public TokenConstants getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenConstants tokenType) {
        this.tokenType = tokenType;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return "Token -tokenType=" + tokenType +
                ", lexeme='" + lexeme + '\'' +
                '-';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (tokenType != token.tokenType) return false;
        return lexeme.equals(token.lexeme);
    }

    @Override
    public int hashCode() {
        int result = tokenType.hashCode();
        result = 31 * result + lexeme.hashCode();
        return result;
    }
}

