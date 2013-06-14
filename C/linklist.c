//Author: Max Rogers
//6/01/2013
//Simple Link List in C

struct node{
	int value;
	node *next;
};

int main()
{
	node *head;
	node *tail;
	node *cursor;
	
	head = (node) malloc(sizeof(node));
	head ->next = 0;
	head ->value = 1;
	cursor = head;
	
	return 0;
}