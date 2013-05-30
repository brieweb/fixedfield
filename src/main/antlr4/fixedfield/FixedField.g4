/*
 * Fixed field format parser
 */

grammar FixedField;

dataset : record+;

record : 'record' ID field+ ';';

field: ID 'PIC' picture '.';

picture :  PICX                         # PicX
        |  INT                          # PicInt
        |  PICA                         # PicA
        |  PICAONE '(' (INT|INTONE) ')' # PicACount
        |  INTONE '(' (INT|INTONE) ')'  # PicIntCount
        |  PICXONE '(' (INT|INTONE) ')' # PicXCount
        ;


INTONE :   [0-9] ;

PICXONE : 'X';
PICAONE : 'A';

INT :   [0-9]+ ;
PICX : 'X'+;
PICA : 'A'+;
ID  :   LOWLETTER (LETTER | [0-9])* ;
fragment
LETTER : [a-zA-Z] ;
fragment
LOWLETTER : [a-z];

WS  :   [ \t\n\r]+ -> skip ;

SL_COMMENT
    :   '//' .*? '\n' -> skip
    ;

