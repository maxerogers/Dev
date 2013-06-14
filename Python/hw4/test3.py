##Author: Max Rogers
##ID: 107979405
##Assignment: Hw4
##Version: 4, built this one from scratch after class
##Goal:
##	Build a python program that uses TPG to build a parser for a Certain langauge
##	The Language Rules:
##
##
##	Example input:			Expected Output				Is it implemented?
##  1 - 2 + 3				2							
##	1 2 3					SYNTAX ERROR				
##	42 + "Greeen"			SEMANTIC ERROR				
##	1 - (2 + 3)				-4							
##	"Hello" + " " + "World." 'Hello World'				
##	[ 1, 2, 3 ][1]+40		42							
##	3 xor 5					6			

#imports
import sys #to get user console argument
import tpg #the toy parser

class SemanticError(Exception):
    """
    This is the class of the exception that is raised when a semantic error
    occurs.
    """
    
# These are the nodes of our abstract syntax tree.

class Node(object):
    """
    A base class for nodes. Might come in handy in the future.
    """

    def evaluate(self):
        """
        Called on children of Node to evaluate that child.
        """

        raise Exception("Not implemented.")

   ############ LITERALS ##################
class IntLiteral(Node):
    """
    A node representing integer literals.
    """

    def __init__(self, value):
        self.value = int(value)

    def evaluate(self):
        return self.value

class ListLiteral(Node):
    """
    A node representing integer literals.
    """

    def __init__(self, value):
        self.value = value
        print("BARK")
    def evaluate(self):
        print("WOOF")
        return self.value
    ############ LITERALS END ##################
    ############ Child Nodes ##################
class Op2Code(Node):
    """
    A node representing multiplication.
    """

    def __init__(self, op, left, right):

        # The nodes representing the left and right sides of this
        # operation.print(op)
        self.op = op
        self.left = left
        self.right = right
        

    def evaluate(self):
        print(op)
        op = self.op.evaluate()
        left = self.left.evaluate()
        right = self.right.evaluate()
        
        if not isinstance(left, int):
            raise SemanticError()
        if not isinstance(right, int):
            raise SemanticError()
        if not ininstance(op, str):
            raise SemanticError()
        print(op)
        if(op == "+"):
            return left + right
        else:
            return left * right
    
    
class Multiply(Node):
    """
    A node representing multiplication.
    """

    def __init__(self, left, right):

        # The nodes representing the left and right sides of this
        # operation.
        self.left = left
        self.right = right


    def evaluate(self):
        left = self.left.evaluate()
        right = self.right.evaluate()

        if not isinstance(left, int):
            raise SemanticError()
        if not isinstance(right, int):
            raise SemanticError()

        return left * right


class Divide(Node):
    def __init__(self, left, right):
        self.left = left
        self.right = right
    def evaluate(self):
        left = self.left.evaluate()
        right = self.right.evaluate()
        if not isinstance(left, int):
            raise SemanticError()
        if not isinstance(right, int):
            raise SemanticError()
        return int(left / right)

class Add(Node):
    def __init__(self, left, right):
        self.left = left
        self.right = right
    def evaluate(self):
        left = self.left.evaluate()
        right = self.right.evaluate()
        if not isinstance(left, int):
            raise SemanticError()
        if not isinstance(right, int):
            raise SemanticError()
        return left + right

class Sub(Node):
    def __init__(self, left, right):
        self.left = left
        self.right = right
    def evaluate(self):
        left = self.left.evaluate()
        right = self.right.evaluate()
        if not isinstance(left, int):
            raise SemanticError()
        if not isinstance(right, int):
            raise SemanticError()
        return left - right

class getElement(Node):
    def __init__(self, left, right):
        self.left = left
        self.right = right
        print("ROAR")
    def evaluate(self):
        print("RWWARRR")
        left = self.left.evaluate()
        right = self.right.evaluate()
        if not isinstance(left, []):
            raise SemanticError()
        if not isinstance(right, int):
            raise SemanticError()
        return left[right]
        
class Element(Node):
    def __init__(self, value):
        self.value = value
        print("MEOW ")
    def evaluate(self):
        print("HISS")
        value = self.value.evaluate()
        if isinstance(value, int):
            value = int(value)
        elif isinstance(value, str):
            value = str(value)
        else:
            raise SemanticError()
        return value

   ############ End Child Nodes ################## 
    
#This is the language parser
class Language(tpg.Parser):
	"""
    token int "\d+" IntLiteral;
    token list "[A-Z a-z 0-9 \[ \] \, \s+]+" ListLiteral;
    separator space "\s+";
    
    START/a -> expression/a
    ;

    expression/a -> muldiv/a | array/a
    ;
    
    muldiv/a -> parens/a
    ( "\*" parens/b $ a = Multiply(a, b) $
    | "/"  parens/b $ a = Divide(a, b) $
    | '\+'/op expression/b $ a = Add(a,b)$
    | "\-" expression/b $ a = Sub(a,b)$
    )* ;

    parens/a -> "\(" expression/a "\)" | literal/a
    ;

    literal/a -> int/a
    ;
    
    array/a -> pointer/a '\[' expression/b '\]' $ a = getElement(a,b)$
    ;
    
    pointer/a -> List/a
    ;
    
    List/l ->
        '\['                $ l = ListLiteral([])
            int/a           $ Element(a)
            (   ',' int/a   $ Element(a)
            )*
        '\]';
    """
    
def main(argv):
	fileName = argv[1] #first argument
	lang = Language()
	f = open(fileName,"r") #this opens up a read stream with the users console arugment path
	output = "" #the output string to the output.txt file
	for line in f: #loops through the input stream making a string for each \n
		try:
			node = lang(line.rstrip()) #parser the input
			temp = node.evaluate()
			print(temp)
			output += temp #add it to the output string
		except Exception:
			 errorText = str(tpg.exc())
			 if "SyntacticError" in errorText: #an error with syntax
			 	print("SYNTAX ERROR"+"\n")
			 	output += "SYNTAX ERROR"+"\n"
			 if "LexicalError" in errorText: #An error with usage 
			 	print("SEMANTIC ERROR"+"\n")
			 	output += "SEMANTIC ERROR"+"\n"
	f.close() #important to close streams
	fileName = "output.txt" #set output
	f = open(fileName,"w") #open output stream
	f.write(output) #write output
	f.close() #close stream
	
main(sys.argv)