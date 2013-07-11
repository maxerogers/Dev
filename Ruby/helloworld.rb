
def main
	puts "hello world from the main function"
	bot = Hellobot.new("hi, I am bot")
	puts bot.botSays
	bot.botSpeak

	gf = Grandfather.new()
	fa = Father.new()
	so = Son.new()

	puts gf.name()
	puts fa.name()
end

class Hellobot
	attr_accessor :status
	def initialize(status)
		self.status = status
	end
	def botSays
		@status
	end
	def botSpeak
		puts @status + " lolz"
	end
end

class Grandfather
	attr_accessor :name
	def initialize()
		self.name = "William"
	end
	def name
		self.name
	end
end

class Father < Grandfather
	def initialize()
		super()
	end
end

class Son < Father
	def initialize()
		self.name = "Max"
	end
end

main