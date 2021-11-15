import java.io.*;
import java_cup.runtime.*;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;

%%
%{
        StringBuffer string = new StringBuffer();
        public Plexer(java.io.Reader in, ComplexSymbolFactory sf){
    	this(in);
    	symbolFactory = sf;
        }
        ComplexSymbolFactory symbolFactory;

      public Symbol symbol(String name, int sym) {
          return symbolFactory.newSymbol(name, sym, new Location(yyline+1,yycolumn+1,yychar), new Location(yyline+1,yycolumn+yylength(),yychar+yylength()));
      }

      public Symbol symbol(String name, int sym, Object val) {
          Location left = new Location(yyline+1,yycolumn+1,yychar);
          Location right= new Location(yyline+1,yycolumn+yylength(), yychar+yylength());
          return symbolFactory.newSymbol(name, sym, left, right,val);
      }
%}
%eofval{
     return symbolFactory.newSymbol("EOF", EOF, new Location(yyline+1,yycolumn+1,yychar), new Location(yyline+1,yycolumn+1,yychar+1));
%eofval}
%cup
%extends sym
%public
%class Plexer
%type Symbol
%line
%column
DoubleNumber = [0-9][0-9]* \. [0-9][0-9]*
Identifier = [_a-zA-Z][_a-zA-Z0-9]*
DecIntegerLiteral = [0-9]+
BoolLiteral = true | false
new_line = \r|\n|\r\n;
white_space = {new_line} | [ \t\f]
BigComment = "/#" ~"#/"
HexNumber = 0[xX][0-9a-fA-F]+
ScientificNumber = {DecIntegerLiteral}+[eE]-?{DecIntegerLiteral}
LineComment = "##" ~\r|"//" ~\n|"//" ~\r\n
NormalCharacter = \'[^\\]\'
SpecialCharacter = \'[\\][^]\'
%state STRING


%%

<YYINITIAL>{
/* keywords */

/* names */

/* bool literal */

/* literals */
{DecIntegerLiteral} { return symbol("intconst",INT_CONST, new Integer(Integer.parseInt(yytext()))); }
{DoubleNumber} { return symbol("realconst",REAL_CONST, new Double(Double.parseDouble(yytext()))); }
{NormalCharacter}                     {return symbol("character",CHAR_CONST,new Character(yytext().charAt(1)));}
{SpecialCharacter}                     {return symbol("character",CHAR_CONST,new Character(yytext().charAt(2)));}
    /* Keywords */
    "begin"                         {return symbol("begin",BEGIN);}
    "bool"                          {return symbol("bool",BOOL);}
    "break"                         {return symbol("break",BREAK);}
    "case"                          {return symbol("case",CASE);}
    "."                          {return symbol("dot",DOT);}
    "char"                          {return symbol("char",CHAR);}
    "const"                         {return symbol("const",CONST);}
    "continue"                      {return symbol("continue",CONTINUE);}
    "default"                       {return symbol("default",DEFAULT);}
    "double"                        {return symbol("double",DOUBLE);}
    "else"                          {return symbol("else",ELSE);}
    "end"                           {return symbol("end",END);}
    "extern"                        {return symbol("extern",EXTERN);}
    "function"                      {return symbol("function",FUNCTION);}
    "float"                         {return symbol("float",FLOAT);}
    "for"                           {return symbol("for",FOR);}
    "foreach"                       {return symbol("foreach",FOREACH);}
    "goto"                          {return symbol("goto",GOTO);}
    "if"                            {return symbol("if",IF);}
    "input"                         {return symbol("input",INPUT);}
    "int"                           {return symbol("int",INT, new Integer( INT_TYPE ));}
    "long"                          {return symbol("long",LONG);}
    "output"                        {return symbol("output",OUTPUT);}
    "return"                        {return symbol("return",RETURN);}
    "record"                        {return symbol("record",RECORD);}
    "sizeof"                        {return symbol("sizeof",SIZEOF);}
    "static"                        {return symbol("static",STATIC);}
    "string"                        {return symbol("string",STRING);}
    "switch"                        {return symbol("switch",SWITCH);}
    "repeat"                          {return symbol("repeat",REPEAT);}
    "void"                          {return symbol("void",VOID);}
    "until"                          {return symbol("until",UNTIL);}
    "in"                          {return symbol("in",IN);}
    "of"                          {return symbol("of",OF);}



    /* Seprators & Operators */
    "=="                            {return symbol("isequal", ISEQUAL);}
    "!="                            {return symbol("notEqual", NOTEQUAL);}
    "<="                            {return symbol("leq", LEQ);}
    "<"                             {return symbol("less", LESS);}
    ">"                             {return symbol("greater", GREATER);}
    ">="                            {return symbol("geq", GEQ);}
    "="                             {return symbol("equal", EQUAL);}
    "not"                           {return symbol("not", NOT);}
    "~"                             {return symbol("tilde", TILDE);}
    "-"								{return symbol("minus", MINUS);}
    "!"								{return symbol("exclam", EXCLAM);}
    "&"                             {return symbol("ampersand", AMPERSAND);}
    "and"                           {return symbol("and", AND);}
    "|"                             {return symbol("pipe", PIPE);}
    "or"                            {return symbol("or", OR);}
    "^"                             {return symbol("caret", CARET);}
    "*"                             {return symbol("times", TIMES);}
    "+"                             {return symbol("plus", PLUS);}
    "/"                             {return symbol("slash", SLASH);}
    "%"                             {return symbol("mod", MOD);}
    "{"                             {return symbol("lbrace", LBRACE);}
    "}"                             {return symbol("rbrace", RBRACE);}
    "("                             {return symbol("lpar", LPAR);}
    ")"                             {return symbol("rpar", RPAR);}
    ","                             {return symbol("comma", COMMA);}
    ":"                             {return symbol("colon", COLON);}
    ";"                             { return symbol("semicolon", SEMICOLON);}
    "["                             {return symbol("lbrac", LBRAC);}
    "]"                             {return symbol("rbrac", RBRAC);}
    "++"                             {return symbol("plusplus", PLUSPLUS);}
    "--"                             {return symbol("minusminus", MINUSMINUS);}



{white_space}     { /* ignore */ }
{BoolLiteral} { return symbol("boolconst",BOOL_CONST, new Boolean(Boolean.parseBoolean(yytext()))); }

{LineComment}          {/**/}
{BigComment}           {/**/}
\"                              {string.setLength(0); yybegin(STRING);}
{Identifier}           {  return symbol("Identifier",ID, yytext()); }

}

<STRING> {
  \"                             { yybegin(YYINITIAL);
      return symbol("StringConst",STRING_CONST,string.toString()); }
  [^\n\r\"\\]+                   { string.append( yytext() ); }
  \\t                            { string.append('\t'); }
  \\n                            { string.append('\n'); }

  \\r                            { string.append('\r'); }
  \\\"                           { string.append('\"'); }
  \\                             { string.append('\\'); }
}