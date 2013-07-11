def main():
	oper = Oper()
	print(oper.fibonacci(9))
	print(oper.operations)
	oper.operations = 0
	print(oper.betterFibo(9))
	print(oper.operations)

class Oper:
	memo = []
	operations = 0
	def __init__(self, x):
		self.operations = x
	def fibonacci(x):
		operations += 1
		if(x <= 2):
			if(x == 0): return 0
			else: return 1 
		else:
			return fibonacci(x-1) + fibonacci(x-2)
	def betterFibo(x):
		operations += 1
		if(memo[x] != None):
			return memo[x]
		else:
			if(x <= 2):
				if(x == 0):
					return 0
				else:
					return 1
			else:
				memo[x] = betterFibo(x-1) + betterFibo(x-2)
				return memo[x]
main()