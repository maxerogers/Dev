import sys
import tpg
class Node(object):
	def evaluate(self):
		raise Exception("Not implemented.")
class IntLiteral(Node):
	def __init__(self, value): self.value = int(value)
	def evaluate(self): return self.value
class Financial(Node):
	def __init__(self, dollars, cents):
		self.dollars = dollars
		self.cents = cents 
	def evaluate(self):
		dollars = self.dollars.evaluate() 
		cents = self.cents.evaluate()
		if not isinstance(dollars, int):
			raise Exception("Dollars not correct."+dollars) 
		if not isinstance(cents, int):
			raise Exception("Dollars not correct."+cents) 
		return dollars + 0.01 * cents
class Parser(tpg.Parser): 
	"""
		token int "\d+" IntLiteral;
		separator space "\s+";
		START/a -> "\$" stars expression/a;
		stars -> ("\*")* ;
		expression/a -> dollars/a "\." cents/b $ a = Financial(a, b) $; 
		dollars/a -> int/a;
		cents/a -> int/a;
	"""
parse = Parser() 
try:
	node = parse("$***99.99")
	print(node.evaluate()) 
except tpg.Error:
	print("SYNTAX ERROR")