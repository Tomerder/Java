#include <stdio.h>
#include <stdlib.h>

typedef struct _char{
	char c; 
}Char;


typedef struct B{
	Char* c;
}B;

typedef struct A{
	Char* c;
	B* b;
}A;


void main(){

	A* pa;
	B* pb;
	
	pa=(A*) malloc (sizeof(A));
	pa->c=(Char *) malloc (sizeof (Char));
	pa->b=(B *) malloc (sizeof (B));
	pa->b->c=(Char *) malloc (sizeof (Char));

	pb=&(pa->b);

	pa=(A*) malloc (sizeof(A)); 

}


