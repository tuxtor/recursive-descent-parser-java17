package com.vorozco;

%%
%public
%class MyLexer

digit = [0-9]
whitespace = [ \t\n]
%type Token

%eofval{
    return new Token(TokenConstants.EOF, null);

%eofval}
%%

{digit}+ {return new Token(TokenConstants.NUMBER, yytext());}
"(" {return new Token(TokenConstants.LPAREN, yytext()); }
")" { return new Token(TokenConstants.RPAREN, yytext()); }

"+" {return new Token(TokenConstants.PLUS, yytext()); }
"*" {return new Token(TokenConstants.TIMES, yytext()); }

{whitespace}+ {/* ignore*/}
[^]    {throw new Error("Cadena ilegal < "+yytext()+" >"); }
