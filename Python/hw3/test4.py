##Author:Max Rogers
##ID: 107979405
##Class: CSE307 - Spring 2013 - Paul Fodor
##Assignment: Hw3
##Problem: Build a 6x6 sudoku solving program that returns a solved game after taking 
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

import sys #imports sys so I can read arguments from the console
from time import time,clock #this to calculate how long this takes on a UNIX machine. The time.clock() function behaves different on a Windows-DOS machine

#this is to solve the grid
def solveGrid(grid, peers, squares):
	flag = True
	flagCount = 0
	while(flag): #this will attempt to solve the puzzle the easy way 
		flag = False
		for s in squares: #Continues to sweep through and remove possible element values
			if(len(grid[s]) == 1):
				removeFromPeers(peers,s,grid)
			else:
				flag = True
		if(flagCount > 10000): #if it can't solve it after 10000 iterations it is not going to solve it the easy way
			flag = False
			flagHard = True
			print("Hard version Activated")
		else: 
			flagCount += 1		
		
		
def removeFromPeers(peers,s,grid):
	for p in peers[s]:
		grid[p] = grid[p].replace(grid[s],"")
#This prints the grid in the following format
# 1A,\t1B,\t....,1F\n
# 2A,.....
def printGrid(grid, squares):
	result = ""
	for i in range(0,6):
		for j in range(0,6):
			var = squares[i*6+j]
			result += grid[var]
		#	print(grid[var], end="")
			if(j < 5):
		#		 print(",", end="\t")
				 result += ",\t"
		#print("\n")
		result += "\n"
	#print("#"*20)
	return result
##this was for debugging, it prints a list
def printList(A):
	print("#"*20)
	result = ""
	for i in range(0,len(A)):
		result += A[i]
		result += " "
	print (result)
	print("#"*20)

#this is for debugging dictionaries
def printDict(D):
	print("#"*20)
	print(D.values())
	print("#"*20)
	
##This creates a cross product between two lists	
def	cross(A,B):
	return [a+b for a in A for b in B]

#This builds the grid
def buildGrid(squares):
	result = {}
	for i in range(0,len(squares)):
		result[squares[i]] = 0
	return result
#this builds peers
def buildPeers(rows,cols,squares):
	result = {}
	for s in squares:
		temp = []
		for r in rows:
			if(s[0] != r):
				temp.append(r+s[1])
		for c in cols:
			if(s[1] != c):
				temp.append(s[0]+c)
		x=ord(s[0])
		y=ord(s[1])
		d=ord('D')
		q = x%2
		if(q == 0):
			q = x - 1
		else:
			q = x + 1
		if(y < d):
			d = ord('A')
		for j in range(d,d+3):
			if(j != y):
				temp.append(chr(q)+chr(j))
		
		result[s] = temp
	return result
def main(argv): ##passes an array from the console in as an argument to the rest of the program
	#initialize variables
	fileName = argv[1]
	outputName = "output.txt"	
	rows = "123456"
	cols = "ABCDEF"
	squares = cross(rows,cols)

	grid = {}
	grid = buildGrid(squares)

	peers = {}
	peers = buildPeers(rows,cols,squares)
	
	original = []
	#read in input
	count = 0
	f = open(fileName,"r")#read input file
	for line in f:
		line = line.rstrip()
		temp = line.split(" ")
		for i in range(0,6):
			var = squares[count*6+i]
			value = temp[i]
			if(value == "-"):
				value = rows
			grid[var] = value
			#print(grid[var])
			original.append(value)
		count += 1
	f.close() #close the stream to inputfile
	
	#solve problem
	start = clock() #This is to calculate how long it takes to solve the game
	print("#"*20)
	print(printGrid(grid,squares))
	print("#"*20)
	
	solveGrid(grid,peers, squares)
	
	endTime = clock() - start #current time minus start time = execution time
	print("It took "+str(endTime)+" seconds to solve this puzzle")
	
	print("#"*20)
	print(printGrid(grid,squares))
	print("#"*20)
	#build output
	result = printGrid(grid,squares)
	##write out final product
	f = open(outputName,"w") #"w" means to write to this stream
	f.write(result) 
	f.close()	#important to close streams
	
main(sys.argv) #imports the console argument array
