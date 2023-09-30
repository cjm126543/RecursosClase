/* Dependencias */
%{
#include <stdlib.h>
#include <stdio.h>
%}
/* Region de declaraciones */
CHARS   [a-zA-Z]
DIGIT   [0-9]
BLANK   [ \t\n]
RESRV   (?i:const|de|dev|e\/s|fconst|hacer|ref)
SCOPE   (?i:algoritmo|falgoritmo|accion|faccion|funcion|ffuncion|var|fvar)
DTYPE   (?i:booleano|cadena|caracter|entero|real)
STRCT   (?i:tabla|tupla|tipo|ftupla|ftipo)
INOUT   ([eE][nN][tT]|[sS][aA][lL])
LG_OP   ([yY]|[oO]|[nN][oO])
AR_OP   ([dD][iI][vV]|[mM][oO][dD])
B_FOR   (?i:para|hasta|continuar|fpara)
B_WHL   ([mM][iI][eE][nN][tT][rR][aA][sS]|[fF][mM][iI][eE][nN][tT][rR][aA][sS])
CONDI   ([sS][iI]|[fF][sS][iI])
IDENT   {CHARS}({CHARS}|{DIGIT})*
L_BOL   ([vV][eE][rR][dD][aA][dD][eE][rR][oO]|[fF][aA][lL][sS][oO])
L_CHR   \"{CHARS}\"
L_STR   '{CHARS}*'
L_INT   ("+"|"-")?{DIGIT}{DIGIT}*(("e"|"E"){DIGIT}{DIGIT}*)?
L_FLT   ("+"|"-")?{DIGIT}{DIGIT}*("."{DIGIT}{DIGIT}*)?(("e"|"E"){DIGIT}{DIGIT}*)?
COMMS   \{.*\}
ASIGN   ":="
COMPO   ";"
SEPAR   ","
SUBRG   ".."
DTVAR   ":"
S_THN   "->"
ELSIF   "[]"
CRTYP   "="
STARR   "["
EDARR   "]"

%%
    /* Region de reglas */
{BLANK} /* DO NOTHING */
{RESRV} {
            printf("Palabra reservada: %s\n", yytext);
}
{SCOPE} {
            printf("Alcance encontrado: %s\n", yytext);
}
{DTYPE} {
            printf("Tipo de dato encontrado: %s\n", yytext);
}
{STRCT} {
            printf("Estructura encontrada: %s\n", yytext);
}
{INOUT} {
            printf("Entrada/Salida encontrada: %s\n", yytext);
}
{LG_OP} {
            printf("Operador logico encontrado: %s\n", yytext);
}
{AR_OP} {
            printf("Operador aritmetico encontrado: %s\n", yytext);
}
{B_FOR} {
            printf("Bucle for encontrado: %s\n", yytext);
}
{B_WHL} {
            printf("Bucle while encontrado: %s\n", yytext);
}
{CONDI} {
            printf("Estructura de condicion encontrada: %s\n", yytext);
}
{L_BOL} {
            printf("Literal booleano encontrado: %s\n", yytext);
}
{L_CHR} {
            printf("Literal caracter encontrado: %s\n", yytext);
}
{L_STR} {
            printf("Literal cadena encontrada: %s\n", yytext);
}
{IDENT} {
            printf("Identificador encontrado: %s\n", yytext);
}
{L_INT} {
            printf("Literal entero encontrado: %d\n", atoi(yytext));
}
{L_FLT} {
            printf("Literal flotante encontrado: %f\n", atof(yytext));
}
{COMMS} {
            printf("Comentario encontrado: %s\n", yytext);
}
{ASIGN} {
            printf("Asignacion encontrada: %s\n", yytext);
}
{COMPO} {
            printf("Composicion encontrado: %s\n", yytext);
}
{SEPAR} {
            printf("Separador encontrado: %s\n", yytext);
}
{SUBRG} {
            printf("Subrango encontrado: %s\n", yytext);
}
{DTVAR} {
            printf("Definicion de tipo de variable encontrado: %s\n", yytext);
}
{S_THN} {
            printf("Entonces encontrado: %s\n", yytext);
}
{ELSIF} {
            printf("Si no si encontrado: %s\n", yytext);
}
{CRTYP} {
            printf("Creacion de tipo encontrado: %s\n", yytext);
}
{STARR} {
            printf("Comienzo de array encontrado: %s\n", yytext);
}
{EDARR} {
            printf("Fin de array encontrado: %s\n", yytext);
}

%%
/* Region de funciones auxiliares */
int main() {
    yyin = stdin;
    yylex();
}