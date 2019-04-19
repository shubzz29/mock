%{
	#include<stdio.h>
%}

DIGIT 		[0-9]
NUMBER 		[0-9]+
CHARACTER 	[a-zA-Z]
STRING 		[a-zA-Z]+
START 		main
SPECIFIER 	public|private|protected
TYPE 		int|float|double|char|String|void|class
KEYWORD 	static|new
OPENBRACKET 	"{"|"["|"("
CLOSEBRACKET 	"}"|"]"|")"
HEADER 		import
SEMICOLON 	[;]
STOP 		[.]
PACKAGE 	package
TEXT 		java|javax|util|io
LIBRARY 	{TEXT}"."{TEXT}".""*"
OPERATOR 	"+"|"-"|"*"|"/"|"%"|"="|"++"|"--"
FUNCTION 	print|println
SYSTEM 		System
OUT 		out
QUOTES 		["]
LOOP 		for|do|while
CONDITIONAL 	if|else

%%

{DIGIT}		{printf("\t%s \t\t: Digit \n", yytext);}
{NUMBER}	{printf("\t%s \t\t: Number \n", yytext);}
{CHARACTER}	{printf("\t%s \t\t: Character \n", yytext);}
{START}		{printf("\t%s \t\t: Main \n", yytext);}
{SPECIFIER}	{printf("\t%s \t\t: Access Specifier \n", yytext);}
{TYPE}		{printf("\t%s \t\t: Datatype \n", yytext);}
{KEYWORD}	{printf("\t%s \t\t: Keyword \n", yytext);}
{OPENBRACKET}	{printf("\t%s \t\t: Open Bracket \n", yytext);}
{CLOSEBRACKET}	{printf("\t%s \t\t: Close Bracket \n", yytext);}
{HEADER}	{printf("\t%s \t\t: Import \n", yytext);}
{SEMICOLON}	{printf("\t%s \t\t: Semicolon \n", yytext);}
{PACKAGE}	{printf("\t%s \t\t: Package \n", yytext);}
{LIBRARY}	{printf("\t%s \t\t: Library \n", yytext);}
{OPERATOR}	{printf("\t%s \t\t: Operator \n", yytext);}
{FUNCTION}	{printf("\t%s \t\t: Function \n", yytext);}
{SYSTEM}	{printf("\t%s \t\t: System \n", yytext);}
{OUT}		{printf("\t%s \t\t: Out \n", yytext);}
{STOP}		{printf("\t%s \t\t: Access Object (FULLSTOP) \n", yytext);}
{QUOTES}	{printf("\t%s \t\t: Quotes \n", yytext);}
" ";
{LOOP}		{printf("\t%s \t\t: Loop \n", yytext);}
{CONDITIONAL}	{printf("\t%s \t\t: Conditional \n", yytext);}
{STRING}	{printf("\t%s \t\t: Text \n", yytext);}
["\n"]
["\t"]

%%

int main()
{
	yyin = fopen("index", "r");
	yylex();
	return 0;
}
int yywrap()
{
	return 1;
}
