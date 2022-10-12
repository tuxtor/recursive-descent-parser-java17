package com.vorozco;

import java.util.ArrayList;
import java.util.List;

public class RDP {
    private List<Token> tokens = new ArrayList<>();
    private int cursor;

    public RDP(List<Token> tokens) {
        this.tokens = tokens;
        cursor = 0;
    }

    public boolean parse(){
        return E() && cursor == tokens.size();
    }

    private boolean term(TokenConstants tok){
        return tokens.get(cursor++).getTokenType().equals(tok);
    }

    private boolean E1(){ // E -> T
        return T();
    }

    private boolean E2(){ // E -> E + T
        return T() && term(TokenConstants.PLUS) && E();
    }

    private boolean E(){ // E -> E - T
        int cursorSave = cursor;
        //Backtrack
        cursor = cursorSave;
        if(E1()){
            return true;
        }
        //|| - OR
        cursor = cursorSave;
        if(E2()){
            return true;
        }

        return false;
    }

    private boolean T1(){ // T -> int
        return term(TokenConstants.NUMBER);
    }

    private boolean T2(){ // T -> int * T
        return term(TokenConstants.NUMBER) && term(TokenConstants.TIMES) &&  T();
    }

    private boolean T3(){ // T -> ( T )
        return term(TokenConstants.LPAREN) && E() && term(TokenConstants.RPAREN);
    }

    private boolean T(){
        int cursorSave = cursor;
        //Backtrack
        cursor = cursorSave;
        if(T1()){
            return true;
        }
        //|| - OR
        cursor = cursorSave;
        if(T2()){
            return true;
        }
        //|| - OR
        cursor = cursorSave;
        if(T3()){
            return true;
        }

        return false;
    }

}
