%{ #include<stdio.h>
void yyerror(char*);
FILE* yyin;
int yylex(); 
%}
%token NOUN PRONOUN CONJUNCTION PREPOSITION ADVERB VERB ADJECTIVE
%%
sentence: simple{printf("Simple Sentence");}| composite{printf("Composite Sentence");};
simple: subject VERB object;
composite: subject VERB object CONJUNCTION subject VERB object;
subject:NOUN|PRONOUN;
object:NOUN| ADVERB NOUN| PREPOSITION NOUN;
%%
void yyerror(char *s)
{
printf("ERROR:%s",s);
}
int main(int argc, char* argv[]){
	yyin=fopen(argv[1],"r");
	yyparse();
	fclose(yyin);
}

