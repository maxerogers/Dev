import sys
import tpg

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

    def execute(self):
        """
        Executes this node.
        """

        raise SemanticError()


    def evaluate(self):
        """
        Evaluates this node for an r-value.
        """

        raise SemanticError()

    
    def location(self):
        """
        Evaluates this node for a location.
        """

        raise SemanticError()

    
class Variable(Node):
    """
    A node representing access to a variable.
    """

    def __init__(self, name):
        self.name = name

    
class IntLiteral(Node):
    """
    A node representing integer literals.
    """

    def __init__(self, value):
        self.value = int(value)

    def evaluate(self):
        return self.value

    
class StringLiteral(Node):
    """
    A node representing a string literal.
    """

    def __init__(self, value):
        self.value = value[1:-1]

    def evaluate(self):
        return self.value

    
class ListLiteral(Node):
    """
    A node representing a list literal.
    """

    def __init__(self, first):
        self.elements = [ first ]

    def evaluate(self):
        return [ i.evaluate() for i in self.elements ]

    
class Index(Node):
    """
    A node representing array or string indexing.
    """

    def __init__(self, left, right):
        self.left = left
        self.right = right

    def evaluate(self):
        left = self.left.evaluate()
        right = self.right.evaluate()

        if not isinstance(left, (str, list)):
            raise SemanticError()
        if not isinstance(right, int):
            raise SemanticError()

        if right > len(left):
            raise SemanticError()
        
        return left[right]

    
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


class Subtract(Node):
    """
    A node representing subtraction.
    """

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


class Add(Node):
    """
    A node representing addition.
    """

    def __init__(self, left, right):
        self.left = left
        self.right = right

    def evaluate(self):
        left = self.left.evaluate()
        right = self.right.evaluate()

        if isinstance(left, int) and isinstance(right, int):
            return left + right
        if isinstance(left, str) and isinstance(right, str):
            return left + right

        raise SemanticError()


class Xor(Node):
    """
    A node representing xor.
    """

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
        
        return left ^ right


class Compare(Node):
    """
    A node representing the various comparison operators.
    """

    def __init__(self, left, op, right):
        self.left = left
        self.right = right
        self.op = op
        
    def evaluate(self):
        left = self.left.evaluate()
        right = self.right.evaluate()

        if not isinstance(left, int):
            raise SemanticError()
        if not isinstance(right, int):
            raise SemanticError()

        if self.op == "<":
            if left < right:
                return 1
            else:
                return 0
        elif self.op == ">":
            if left > right:
                return 1
            else:
                return 0
        else:
            if left == right:
                return 1
            else:
                return 0


class Not(Node):
    """
    A node representing boolean not.
    """

    def __init__(self, child):
        self.child = child

    def evaluate(self):
        child = self.child.evaluate()

        if not isinstance(child, int):
            raise SemanticError()

        if child:
            return 0
        else:
            return 1


class And(Node):
    """
    A node representing boolean and.
    """

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
        
        if left and right:
            return 1
        else:
            return 0


class Or(Node):
    """
    A node representing boolean or.
    """

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
        
        if left or right:
            return 1
        else:
            return 0

# Statements.
        
class Block(Node):
    """
    A node representing the block statement.
    """

    def __init__(self):
        self.statements = [ ]


class If(Node):
    """
    A node representing the if statement.
    """

    def __init__(self, expression, statement):
        self.expression = expression
        self.statement = statement


class While(Node):
    """
    A node representing the while statement.
    """

    def __init__(self, expression, statement):
        self.expression = expression
        self.statement = statement


class Assign(Node):
    """
    A node representing the assignment statement.
    """
    
    def __init__(self, left, right):
        self.left = left
        self.right = right

    
class Print(Node):
    """
    A node representing the print statement.
    """

    def __init__(self, expression):
        self.expression = expression

    def execute(self):
        print repr(self.expression.evaluate())

        
# This is the TPG Parser that is responsible for turning our language into
# an abstract syntax tree.
class Parser(tpg.Parser):
    """
    token int '\d+' IntLiteral;
    token string '\".*?\"' StringLiteral;
    token variable '[A-Za-z][A-Za-z0-9]*' Variable;
    separator space "\s+";

    
    START/a -> statement/a
    ;

    statement/a ->
    ( "\{" $ a = Block() $ ( statement/b $ a.statements.append(b) $ )* "\}"
    | "if" "\(" expression/e "\)" statement/s $ a = If(e, s) $
    | "while" "\(" expression/e "\)" statement/s $ a = While(e, s) $
    | expression/l "=(?!=)" expression/r ";" $ a = Assign(l, r) $
    | "print" expression/e ";" $ a = Print(e) $
    );
    
    expression/a -> OR/a
    ;

    OR/a -> AND/a ( "or" AND/b $ a = Or(a, b) $ )* ;
    AND/a -> NOT/a ( "and" NOT/b $ a = And(a, b) $ )* ;
    NOT/a -> "not" NOT/a $ a = Not(a) $ | compare/a ;

    compare/a -> xor/a 
    ( "<" xor/b $ a = Compare(a, "<", b) $
    | ">" xor/b $ a = Compare(a, ">", b) $
    | "==" xor/b $ a = Compare(a, "==", b) $
    )* ;
    
    xor/a -> addsub/a ( "xor" addsub/b $ a = Xor(a, b) $ )* ;
        
    addsub/a -> muldiv/a
    ( "\+" muldiv/b $ a = Add(a, b) $
    | "\-"  muldiv/b $ a = Subtract(a, b) $
    )* ;

    muldiv/a -> index/a
    ( "\*" index/b $ a = Multiply(a, b) $
    | "/"  index/b $ a = Divide(a, b) $
    )* ;

    index/a -> parens/a ( "\[" expression/b "\]" $ a = Index(a, b) $ )*;
    
    parens/a -> "\(" expression/a "\)" | literal/a | variable/a
    ;

    literal/a ->
    ( int/a
    | string/a
    | "\[" expression/b $ a = ListLiteral(b) $
      ( "," expression/b $ a.elements.append(b) $ )*
      "\]"
      );
    """

# Make an instance of the parser. This acts like a function.

parse = Parser()

# This is the driver code, that reads in lines, deals with errors, and
# prints the output if no error occurs.

# Open the file containing the input.
f = file(sys.argv[1], "r")
code = f.read()
f.close()


try:
    # Try to parse the expression.
    node = parse(code)

    # Execute the node.
    node.execute()


# If an exception is thrown, print the appropriate error.
except tpg.Error:
    print "SYNTAX ERROR"

    # Uncomment the next line to re-raise the syntax error,
    # displaying where it occurs. Comment it for submission.

    # raise

except SemanticError:
    print "SEMANTIC ERROR"

    # Uncomment the next line to re-raise the semantic error,
    # displaying where it occurs. Comment it for submission.

    # raise

        
