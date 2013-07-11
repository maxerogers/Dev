#include <iostream>
using namespace std;

static int operations = 0;
static int* memo;

int fibonacci(int x);
int betterFibo(int x);
int main()
{
	memo = (int *) malloc(sizeof(int)*100);
	cout << fibonacci(9) << endl;
	cout << operations<< endl;
	operations = 0;
	cout << betterFibo(9)<< endl;
	cout << operations<< endl;
	return 0;
}

int betterFibo(int x)
{
	operations++;
	if(memo[x] == 0)
	{
		if(x < 2)
			if(x == 0)
				memo[x] = 0;
			else
				memo[x] = 1;
		else
			memo[x] = (betterFibo(x-1)+betterFibo(x-2));
	}
	return memo[x];
}

int fibonacci(int x)
{
	operations++;
	if(x < 2)
		if(x == 0)
			return 0;
		else
			return 1;
	return (fibonacci(x-1) + fibonacci(x-2));
}