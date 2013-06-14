##Author: Max Rogers
##ID: 107979405
##Assignment: HW4
##Goal:
##	build a language parser that takes the input txt and generates the correct
## output.txt

#imports
import sys #used for terminal python command arguments
import math #
import operator # 
import string #
import tpg #this is the toy parser package. used to parse the incoming text and assign them the proper commands

#This returns the dictionary entry for the appropriate operation
def makeOp(op):
	return {
		'+': lambda x,y: x+y,
		'-': lambda x,y: x-y,
		'*': lambda x,y: x*y,
		'/': lambda x,y: x/y,
		'%': lambda x,y: x%y
	}[op]

#Here we define the language
class TheLanguage(tpg.parser):
	r"""
	separator spaces: '\s+' ;
	token number: '\d+' int ;
	token add: '[+-]' make_op ;
	token mul: '[*/]' make_op ;
	64
	START/e -> Term/e ;
	Term/t -> Fact/t ( add/op Fact/f $t=op(t,f)$ )* ;
	Fact/f -> Atom/f ( mul/op Atom/a $f=op(f,a)$ )* ;
	Atom/a -> number/a | '\(' Term/a '\)' ;
	"""
##this makes sure the operator.div is treated correctly
if tpg.__python__ == 3:
	operator.div = operator.truediv
	raw_input = input

#main method....its kind of important ^_^
def main(argv):
	fileName = argv[1] #arguement 2 should be the input file path
	lang = TheLanguage()
main(sys.argv) #this loads the arguments into the main method
