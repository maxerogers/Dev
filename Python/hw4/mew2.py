import sys
import tpg
import string 

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
        
class StringLiteral(Node):
    """
    A node representing integer literals.
    """

    def __init__(self, value):
        self.value = str(value)
        self.value = self.value[1:-1]
    def evaluate(self):
        return self.value
    
class IntLiteral(Node):
    """
    A node representing integer literals.
    """

    def __init__(self, value):
        self.value = int(value)

    def evaluate(self):
        return self.value

class Sub(Node):
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

        return left - right

class Add(Node):
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

        return left + right
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
    """
        A node representing division.
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
        
        if right == 0:
            raise SemanticError()

        return left / right
        
class And(Node):
    """
        A node representing division.
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
        
        return left & right
class Or(Node):
    """
    A node representing division.
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
            
        return left | right
        	
class Xor(Node):
    """
    A node representing division.
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

        return left ^ right
class Not(Node):
    """
    A node representing division.
    """

    def __init__(self, x):

        # The nodes representing the left and right sides of this
        # operation.
        self.x = x

    def evaluate(self):
        x = self.x.evaluate()

        if not isinstance(x, int):
            raise SemanticError()

        return not(x)

class ListLiteral(Node):
    """
    A node representing integer literals.
    """

    def __init__(self, value):
        self.value = value
    def evaluate(self):
        return self.value

class getElement(Node):
    """
    A node representing division.
    """

    def __init__(self, left, right):

        # The nodes representing the left and right sides of this
        # operation.
        self.left = left
        self.right = right

    def evaluate(self):
        left = self.left.evaluate()
        right = self.right.evaluate()

        if not isinstance(left, list):
            raise SemanticError()
        elif not ininstance(left, str):
            raise SemanticError()
        if not isinstance(right, int):
            raise SemanticError()

        return left[right]
# This is the TPG Parser that is responsible for turning our language into
# an abstract syntax tree.
class Parser(tpg.Parser):
    """
    token int '\d+' IntLiteral;
    token myString '\"[0-9A-Za-z\s+]+\"' StringLiteral;
    token myIndex     '[0-9 A-Z a-z \, \s+]+' ListLiteral;
    token ELEMENT   '[0-9 A-Z a-z \,\s+]+' ElementLiteral;
    separator space '\s+';
    
    
    START/a -> expression/a 
    ;

    expression/a -> mathLog/a
    ;
    
    array -> pointer/a ( '\[' expression/b '\]' $a = getElement(a,b)$)+
    ;
    
    pointer/a -> LIST/a | myString/a
    ;
    
    LIST/$ListLiteral(a)$ -> 
        '\['                    $a = [] $
            ELEMENT/b           $a.append(b) $
            ( ',' ELEMENT/b     $a.append(b) $
            )*            
        '\]'
        ; 
    
    mathLog/a -> 'not' parens/a $ a = Not(a)$ 
    |parens/a
    ( "\*" parens/b $ a = Multiply(a, b) $
    | "/"  parens/b $ a = Divide(a, b) $
    | "xor"  parens/b $ a = Xor(a, b) $
    | "or" parens/b $ a = Or(a,b) $
    | "and" parens/b $ a = And(a,b) $
    | "\+" expression/b $ a = Add(a,b)$
    | "\-" expression/b $ a = Sub(a,b)$
    )*;

    parens/a -> "\(" expression/a "\)"| "\[" expression/a "\]"literal/a 
    ;

    literal/a -> int/a | myString/a
    ;
    
    """

# Make an instance of the parser. This acts like a function.

parse = Parser()

# This is the driver code, that reads in lines, deals with errors, and
# prints the output if no error occurs.

# Open the file containing the input.
try:
    f = open(sys.argv[1], "r")
except(IndexError, IOError):
    f = open("input1.txt", "r")

# For each line in f,
for l in f:

    try:
        # Try to parse the expression.
        node = parse(l.rstrip())

        # Try to get a result.
        result = node.evaluate()

        # Print the representation of the result.
        print(repr(result))

    # If an exception is thrown, print the appropriate error.
    except tpg.Error:
        print("SYNTAX ERROR")

        # Uncomment the next line to re-raise the syntax error,
        # displaying where it occurs. Comment it for submission.

        # raise
        
    except SemanticError:
        print("SEMANTIC ERROR")

        # Uncomment the next line to re-raise the semantic error,
        # displaying where it occurs. Comment it for submission.

        # raise

f.close()
        
