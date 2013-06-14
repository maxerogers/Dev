import math
import operator
import string
import tpg
import sys

def make_op(op):
	return {
			'+': lambda x,y: x+y,
			'-': lambda x,y: x-y,
			'*': lambda x,y: x*y,
			'/': lambda x,y: x/y,		
			'xor': operator.xor,
		}[op]
class TheLanguage(tpg.Parser):
	r"""
		separator space	'\s+' ;
		token number: '\d+' int;
		token add	'[+-]'	make_op ;
		token mul	'[*/%]' make_op ;
		token xor_op	'xor' make_op;
		START/e -> Term/e ;
		Term/t -> Fact/t ( add/op Fact/f $t=op(t,f)$ )* ;
		Fact/f -> Atom/f ( mul/op Atom/a $f=op(f,a)$ )* ;
		Atom/a -> number/a | '\(' Term/a '\)' ;
	"""
		
if tpg.__python__ == 3:
	operator.div = operator.truediv
	raw_input = input

def main(argv):
    print ("HW4 (TPG Parser for 'The Language')")
    lang = TheLanguage()
    fileName = argv[1]
    export = ""
    outputFileName = "output.txt"
    f = open(fileName,"r")
    for line in f:
        try:
            export += str(lang(line)) + "\n"
        except Exception:
            errorText = str(tpg.exc())
            if "SyntacticError" in errorText:
                export += "SYNTAX ERROR"+"\n"
            if "LexicalError" in errorText:
                export += "SEMANTIC ERROR"+"\n"
    f.close()
    f = open(outputFileName, "w")
    f.write(export)
    f.close()
main(sys.argv)

#Test Input
#1 - 2 +3 WORKS
#1 2 3 need to add syntax error catch
#42 + "Green" Semantic error catch
#1-(2+3) WORKS
#"Hello"+""+"World."
#[1.2,3][1] + 40 (Add Array support)
#3 xor 5

