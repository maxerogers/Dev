'''
Created on feb 05, 2013

@author: max
'''
#import files
import os.path

#main func
def main():
    
    #read in from input.txt
    r = open("input.txt", "r")
    list1 = []
    for line in r:
        list1.append(line)
        print line
    r.close
    #writeout to output.txt
    w = open("output.txt", "w")
    for el1 in list1:
        list2 = el1.split()
        for el2 in list2:
            w.write(el2+"\n")
    w.close
#run 
main()