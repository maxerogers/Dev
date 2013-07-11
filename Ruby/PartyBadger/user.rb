##This the test version of the User model for ruby testing
def main
	puts "Hello World"
	User.new("Max")
	User.new("John")
end

class User
	@@idCount = 0
	attr_accessor :name, :id
	def initialize(name)
		self.name = name
		@@idCount += 1
		self.id = @@idCount
		puts self.name + " " +self.id.to_s

	end
end

main