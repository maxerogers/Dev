def main 
	fibo = Fibonacci.new()
	puts fibo.generate(9)
	puts fibo.operations
	fibo.operations = 0
	puts fibo.generateBetter(9)
	puts fibo.operations
end

class Fibonacci
	attr_accessor :operations, :memo
	def initialize()
		@operations = 0
		@memo = [0,1,1]
	end

	def generate(x)
		@operations +=1
		if(x < 2)
			if x == 0
				0
			else
				1
			end
		else
			generate(x-1) + generate(x-2)
		end
	end

	def generateBetter(x)
		@operations += 1
		if(@memo[x] != nil)
			@memo[x]
		else
			@memo.push(generateBetter(x-1) + generateBetter(x-2))
			@memo[x]
		end
	end
end

main