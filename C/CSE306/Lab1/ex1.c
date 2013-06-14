//
// Simple inline asembly example
//
// For JOS lab 1 exercise 1
//

#include <stdio.h>

int main(int argc, char **argv)
{
	int x = 1;
	printf("Hello x=%d\n", x);
	//int y = x + 1;
	//
	// Put in-line assembly here to increment
	// the value of x by 1 using in-line assembly
	asm("inc %%eax \t\n"
        "movl %%eax, %0"
        :"=g"(x) //output
        :"a"(x) //inputs
        : "memory"
        );
    //
    
    
	printf("Hello x = %d after increment\n", x);
	
	if(x == 2)
	{
		printf("OK\n");
	}else{
		printf("ERROR\n");
	}
	return 0;
}
