
maxi = 0;
maxj = 0;
for i in range(0,100):
	for j in range(0,100):
		if(2*i+3*j == 400):
			if(i*j > maxi*maxj):
				maxi = i;
				maxj = j;

print("i:"+str(maxi)+" j:"+str(maxj)+"");