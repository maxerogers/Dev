##Author: Max Rogers
##ID: 107979405
##Assignment: Hw4
##Description:
##Language Interpreter
"""
The Language
* Whitespace: except inside string literals, whitespace (consisting of the space 
and newline characters) must be ignored.
* Data Types: our language has three data types:
** Integer = should be implemented using the equivalent Python integer type.
** Array = can be implemented using a Python list.
** String = should be implemented using the equivalent Python Sting type.
Objects of these types should be printed using Python's repr() function: 
repr(object) returns a string containing a printable representation of an object. 
This is the same value yielded by conversions (reverse quotes).
"""

#System imports
import sys
import tpg
import operator

#compileExport this takes in the imported functions and then uses the parser to 
#compile an appropriate output string to be written out
def compileExport(input):
	export = "LOLZ"
	return export
def make_op(op):
	return {
		'+' : operator.add,
		'-' : 
	}
class TheLanguage(tpg.parser):
	r"""
		separator space '\s+';
		token add_op	'[+-]'	$ make_op
		token mul_op	'[*/%]'	$ make_op
		;
	"""
#main function
def main(argv):
	outputFileName = "output.txt"
	importResult = ""
	export = ""
	print("hello world")
	importFile = argv[1]
	f = open(importFile,"r")
	for line in f:
		importResult += line
	f.close()
	print(importResult)
	export = compileExport(importResult)
	f = open(outputFileName,"w")
	f.write(export)
	f.close()
#run main
main(sys.argv)