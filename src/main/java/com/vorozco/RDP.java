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
        if(cursor >= tokens.size()){
            return false;
        }
        return tokens.get(cursor++).getTokenType().equals(tok);
    }

    private boolean E(){
        return T() && X();
    }


    private boolean X1(){ // X -> + E
        return term(TokenConstants.PLUS) && E();
    }

    private boolean X2(){ // X -> Epsilon
        return true;
    }

    private boolean X(){ // E -> E - T
        int cursorSave = cursor;
        //Backtrack
        cursor = cursorSave;
        if(X1()){
            return true;
        }
        //|| - OR
        cursor = cursorSave;
        if(X2()){
            return true;
        }

        return false;
    }

    private boolean Y1(){ // Y -> * T
        return term(TokenConstants.TIMES) && T();
    }

    private boolean Y2(){ // Y -> Epsilon
        return true;
    }

    private boolean Y(){
        int cursorSave = cursor;
        //Backtrack
        cursor = cursorSave;
        if(Y1()){
            return true;
        }
        //|| - OR
        cursor = cursorSave;
        if(Y2()){
            return true;
        }

        return false;
    }

    private boolean T1(){ // T -> NUMBER Y
        return term(TokenConstants.NUMBER) && Y();
    }
    private boolean T2(){ // T -> ( E )
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

        return false;
    }


}
