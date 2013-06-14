##Author: Max Rogers
##ID: 107979405
##SBU - CSE307 - Spring 2013 - Paul Fodor
##Homework 2
#####################################
#10: Read in input data. 
###CHECK (SEE LINE 20 & 39-70)####
#10: Split lines and cells and save it in a data structure in your program. Represent the data as a list of rows where each row is a list of cells.
######3 CHECK (See LINE 30)#####
#10: Print the number of row and number of columns: 
#######CHECK (SEE LINE 74-76)######
#Exclude rows that are empty or contain only empty cells. 
#######CHECK (SEE LINE 55-63) ######3
#Report and exit if different rows have different number of columns.
###### CHECK (SEE LINE 49 & 64)  #######
#5: Print column headers
###### CHECK (SEE LINE 77-85) ######
#15: For each column, print sorted, unique values preceded with counts: 
####### CHECK SEE LINE 77-96 #####
#Convert numbers from string type to appropriate int or float type. 
####### CHECK 88-92 ######
#Sort using a library function. 
###### CHECK see line 83 ######
#Unique values. 
####### CHECK line 87 #######
#Exact spaces in output as in examples. 
##### CHECK line 53 ######
######################################

#imports
from sys import argv

#initialize variables
script,filename = argv #this method of taking user input is inspired from LearnPythonTheHardWay Exercise 15
cols=[] #stores the data in sortable columns
#rows=[] #stores the data in sortable rows was used for testing purposes but the columns were more useful since they can be sorted
numOfRows=0
numOfCols=0
output = "output.txt"
result = ""
inputFlag = -1

##Now Read Input
txt = open(filename)
for line in txt:
	#print line
	temp = line.split("\t")
	#print temp
	#rows.insert(numOfRows,temp) #phased out
	if(numOfCols == 0): #notes this as the first run
		numOfCols = len(temp)
		for i in range(0,numOfCols):
			cols.insert(i,[])
	if(len(temp) != numOfCols): #after the first run through, this will make sure all tuples have the same number of columns
		inputFlag = numOfRows
	colsFlag = 0
	for i in range(0,len(temp)):
		temp[i] = temp[i].strip()
		cols[i].insert(numOfRows,temp[i]) #adding to sortable columns
		if(temp[i] == ""):
			colsFlag += 1
	numOfRows += 1
	##Checks to see if the last row was empty
	if(colsFlag == numOfCols):
		#print "Found an empty row"
		for i in range(0,len(temp)):
			cols[i].pop(numOfRows-1)
		numOfRows -= 1
	if(inputFlag != -1): #Error Write
		str = "Number of Rows: %d" % numOfRows
		str += "\nCannot determine number of columns \n"
		str += "Error occurs on row %d" % inputFlag
		output = open(output,'w')
		output.write(str)
		output.close()
		exit()

##Building output page
result += "Number of rows: %r" % numOfRows
result += "\nNumber of columns: %r" % numOfCols
result += "\n"
for i in range(0,numOfCols):
	temp2 = "Column %d: "%i
	temp3 = cols[i][0]
	temp2 += temp3 #this is to remove the '' around the element
	temp2 += "\n"
	head = cols[i].pop(0) #pop the head
	cols[i].sort() #need to sort so there proper count
	flagCount = 1
	for j in range(0,numOfRows-1): #now adding the count
		count = cols[i].count(cols[i][j])
		if(flagCount == count):
			temp2 += "%d " % cols[i].count(cols[i][j])
			str = cols[i][j]
			temp2 += str
			temp2 += "\n"
			flagCount = 1
		else:
			flagCount += 1
	cols[i].insert(0,head) #put the head back on
	result += temp2
	
#print rows
#print "\n\n"
#print cols

##Now Write Output
output = open(output,'w')
output.write(result)
output.close()