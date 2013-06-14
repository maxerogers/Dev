

array1 = [1,2,3]
array2 = [4,5,6]
array3 = [7,8,9]
arrays = []
arrays.insert(0,array1)
arrays.insert(1,array2)
arrays.insert(2,array3)

print arrays[0][0]
print arrays[0][1]
print arrays[0][2]

print arrays[0][0]
print arrays[1][0]
print arrays[2][0]

print "\n\n"
arrays[0].insert(0,10)

print arrays[0][0]
print arrays[0][1]
print arrays[0][2]

print arrays[0][0]
print arrays[1][0]
print arrays[2][0]

print "\n\n"
arrays[0].pop(0)

print arrays[0][0]
print arrays[0][1]
print arrays[0][2]

print arrays[0][0]
print arrays[1][0]
print arrays[2][0]

print "\n\n"

str = "\nlolz\n"

print str
str.replace("\n","")
print str