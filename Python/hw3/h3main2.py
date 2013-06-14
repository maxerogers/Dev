
##Author:Max Rogers
##Assignment: Hw3
##Problem: Bui/Users/max/Desktop/Programming/Python/hw3/Unit.pyld a 6x6 sudoku solving program that returns a solved game after taking 
##in a path parameter for the input txt file
##
##The format for the input txt file should be 
##
##2 1 - - 4 3
##- - - - - -
##- - 6 2 - -
##- - 3 4 - -
##- - - - - -
##3 4 - - 5 6
##where '-' means empty
##

##Imports
import sys

class SudokuSolver:
	
	def __init__(self,inputGrid):
		self.grid = inputGrid
	
##main function
def main(argv):
	##gets the filename
	fileName = argv[1]
	outputName = "output.txt"
	grid = [] 
	count = 0
	output = ""
	##read input
	f = open(fileName,"r")
	for line in f:
		#print (line)
		line = line.rstrip()
		temp = line.split(" ")
		for i in range(0,6):
			val = -1
			if(temp[i] != '-'):
				grid.append(temp[i])
				#print (i)
			else:
				grid.append(0)	
		count += 1
	#buildSquareGrid
	SSolver = SudokuSolver(grid)
	##write output
	for i in range(0,6):
		result = ""
		for j in range(0,6):
			result += str(grid[i*6+j])
			result += " "
		print(result)
		result += "\n"
		output += result
	f = open(outputName,"w")
	f.write(output)
	f.close()
main(sys.argv)
