# ProAlg
## Lexico
### Alfabeto (Σ)
{
    [a-zA-Z0-9] " / ( ) = [ ] + * { } , ; . : - _ > < \s \t \n
}

*Nota: El \s es solo caracter de espacio en blanco para espacio, no se contempla el resto*

#### Anotaciones respecto al alfabeto
**\s \t** y **\n** son simplemente visuales, no tienen valor semántico.
**No hay distinción entre mayúsculas y minúsculas.**
El resto de caracteres pueden aparecer en entornos concretos sin ningún significado semántico especial (literales de texto, comentarios, ...).

### Tokens
Se listan posibilidades (se pueden separar, pueden haber mas o estar a medias), primados por la comodidad de redacción.

#### Identificadores
*Usados para:*
- Nombres de variables
- Tipos
- Campos de registros
- Acciones
- Funciones
- Palabras reservadas

```
identificador --> letra letra_o_cifra*
letra_o_cifra --> letra | cifra
letra --> [A-Z] | [a-z]
```

#### Literales
*Usados para:*
- Definir valores constantes
- Definir tipos básicos (primitivas)

```
literal_entero --> (+ | -)? cifra cifra* ((e | E) cifra cifra*)?
literal_real --> (+ | -)? cifra cifra* (. cifra cifra*)? ((e | E) cifra cifra*)?
        (el punto es de la parte decimal, no el comodin "cualquier caracter")
literal_booleano --> verdadero | falso
literal_caracter --> " <cualquier caracter> "
literal_cadena --> ' <cualquier caracter>* '
        (si aparece el caracter ' debe ir un / por delante para escaparlo)
```

#### Comentarios
Los comentarios deben ir entre { } Dentro no puede aparecer } a menos que este precedido por /, es decir debe estar la cadena /} para poder ser valido.

2 tipos de comentarios:
1. Comentarios informativos (en cualquier parte del programa, sin valor)
2. Comentarios obligatorios
    * Precondición
    * Postcondición

#### Palabras reservadas
Tanto en minúscula como mayúscula como mezcla en cualquier forma. No pueden ser usadas como identificadores (pueden haber mas):
- accion
- algoritmo
- booleano
- cadena
- caracter
- const
- continuar
- de
- dev
- div
- ent
- entero
- e/s
- faccion
- falgoritmo
- falso
- fconst
- ffuncion
- fmientras
- fpara
- fsi
- ftipo
- ftupla
- funcion
- fvar
- hacer
- hasta
- mientras
- mod
- no
- o
- para
- real
- ref
- sal
- si
- tabla
- tipo
- tupla
- var
- verdadero
- y

#### Otros tokens
Agrupaciones de caracteres que tienen una sintaxis y / o semántica especial. Algun ejemplo:

*Simbolos de operadores relacionales y aritméticos*

*Otros*
- Asignacion                        :=
- Composicion secuencial            ;
- Separador                         ,
- Subrango                          ..
- Definicion de tipo de variable    :
- Entonces                          ->
- Si no si                          []
- Creacion de tipo                  =
- Inicio de array                   [
- Fin de array                      ]

## Sintaxis

### Estructura general de un programa
#### Declaraciones
#### Declaraciones de tipos
#### Declaraciones de constantes y variables
#### Expresiones
#### Instrucciones
#### Acciones y funciones