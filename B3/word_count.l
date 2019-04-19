%{
int nlines,nwords,nchars;
%}

%%
\n {
	nlines++; 
	}
[.]  {nchars++;};
 
[^ \n\t]+ {nwords++, nchars=nchars+yyleng;}

%%
int yywrap(void)  
{
 return 1;
}
int main(int argc, char*argv[]) 
{
	yyin=fopen(argv[1],"r");
 yylex();
 printf("Lines = %d\nChars=%d\nWords=%d",nlines,nchars,nwords);

 return 0;
} 
