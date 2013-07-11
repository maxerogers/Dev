

def main
	cCounter = CardCounter.new()
	puts cCounter.factorial(5)
	puts cCounter.operations
end

class CardCounter
	attr_accessor :operations
	def initialize()
		@operations = 0
	end
	def factorial(x)
		if(x <= 1)
			@operations += 1
			1
		else
			@operations += 1
			factorial(x-1) * x
		end
	end
end

main