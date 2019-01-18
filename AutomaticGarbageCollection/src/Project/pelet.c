#include <stdio.h>
#include <stdlib.h>

typedef struct _char{
int counter1;
int counter2;
 char c; 
}Char;


typedef struct B{
int counter1;
int counter2;
 Char* c;
}B;

typedef struct A{
int counter1;
int counter2;
 Char* c;
 B* b;
}A;


void ourNewOgenA( A**  pointer  ) ;
void ourNewPA( A**  pointer  ) ;
void changeOgenA( A**  ogenPointer  ,  A**  newPlaceToPoint  )  ; 
void decreaseAndCheckA(A**  pointer ) ;
void ourNewOgenB( B**  pointer  ) ;
void ourNewPB( B**  pointer  ) ;
void changeOgenB( B**  ogenPointer  ,  B**  newPlaceToPoint  )  ; 
void decreaseAndCheckB(B**  pointer ) ;
void ourNewOgenChar( Char**  pointer  ) ;
void ourNewPChar( Char**  pointer  ) ;
void changeOgenChar( Char**  ogenPointer  ,  Char**  newPlaceToPoint  )  ; 
void decreaseAndCheckChar(Char**  pointer ) ;



void decreaseAndCheckA(A**  pointer ) {
	(*pointer)->counter2--;
	if( ((*pointer)->counter1==0) && ((*pointer)->counter2==0) )  {
			decreaseAndCheckChar(&((*pointer)->c));
			decreaseAndCheckB(&((*pointer)->b));
		free( (*pointer) );
		(*pointer)=NULL;
	}
}
void decreaseAndCheckB(B**  pointer ) {
	(*pointer)->counter2--;
	if( ((*pointer)->counter1==0) && ((*pointer)->counter2==0) )  {
			decreaseAndCheckChar(&((*pointer)->c));
		free( (*pointer) );
		(*pointer)=NULL;
	}
}
void decreaseAndCheckChar(Char**  pointer ) {
	(*pointer)->counter2--;
	if( ((*pointer)->counter1==0) && ((*pointer)->counter2==0) )  {
		free(  (*pointer) );
		(*pointer)=NULL;
	}
}
void changeOgenA( A**  ogenPointer  ,  A**  newPlaceToPoint  )  { 
	if ( (*ogenPointer)==NULL ) {
		(*ogenPointer)= *newPlaceToPoint;
		(*ogenPointer)->counter1++;
			(*ogenPointer)->c=NULL;
			(*ogenPointer)->b=NULL;
		}
	else {
		(*ogenPointer)->counter1-- ;
		if( ((*ogenPointer)->counter1==0) && ((*ogenPointer)->counter2==0) )  {
			decreaseAndCheckChar(&((*ogenPointer)->c));
			decreaseAndCheckB(&((*ogenPointer)->b));
			free(  (*ogenPointer) );
			(*ogenPointer)=NULL;
		}
		(*ogenPointer)= *newPlaceToPoint;
		(*ogenPointer)->counter1++;
	}
}
void changeOgenB( B**  ogenPointer  ,  B**  newPlaceToPoint  )  { 
	if ( (*ogenPointer)==NULL ) {
		(*ogenPointer)= *newPlaceToPoint;
		(*ogenPointer)->counter1++;
			(*ogenPointer)->c=NULL;
		}
	else {
		(*ogenPointer)->counter1-- ;
		if( ((*ogenPointer)->counter1==0) && ((*ogenPointer)->counter2==0) )  {
			decreaseAndCheckChar(&((*ogenPointer)->c));
			free(  (*ogenPointer) );
			(*ogenPointer)=NULL;
		}
		(*ogenPointer)= *newPlaceToPoint;
		(*ogenPointer)->counter1++;
	}
}
void changeOgenChar( Char**  ogenPointer  ,  Char**  newPlaceToPoint  )  { 
	if ( (*ogenPointer)==NULL ) {
		(*ogenPointer)= *newPlaceToPoint;
		(*ogenPointer)->counter1++;
		}
	else {
		(*ogenPointer)->counter1-- ;
		if( ((*ogenPointer)->counter1==0) && ((*ogenPointer)->counter2==0) )  {
			free(  (*ogenPointer) );
			(*ogenPointer)=NULL;
		}
		(*ogenPointer)= *newPlaceToPoint;
		(*ogenPointer)->counter1++;
	}
}
void ourNewOgenA( A**  pointer  ) {
	if ( *pointer== NULL) {
		(*pointer) = ( A*) malloc( sizeof( A ));
		(*pointer)->counter1 = 1;
		(*pointer)->counter2 = 0;
			(*pointer)->c=NULL;
			(*pointer)->b=NULL;
		}
	else {
		(*pointer)->counter1-- ;
		if( ((*pointer)->counter1==0) && ((*pointer)->counter2==0) )  {
			decreaseAndCheckChar(&((*pointer)->c));
			decreaseAndCheckB(&((*pointer)->b));
			free( (*pointer) );
			(*pointer)=NULL;
		}
		(*pointer) = ( A*) malloc( sizeof( A ));
		(*pointer)->counter1 = 1;
		(*pointer)->counter2 = 0;
	}
}
void ourNewOgenB( B**  pointer  ) {
	if ( *pointer== NULL) {
		(*pointer) = ( B*) malloc( sizeof( B ));
		(*pointer)->counter1 = 1;
		(*pointer)->counter2 = 0;
			(*pointer)->c=NULL;
		}
	else {
		(*pointer)->counter1-- ;
		if( ((*pointer)->counter1==0) && ((*pointer)->counter2==0) )  {
			decreaseAndCheckChar(&((*pointer)->c));
			free( (*pointer) );
			(*pointer)=NULL;
		}
		(*pointer) = ( B*) malloc( sizeof( B ));
		(*pointer)->counter1 = 1;
		(*pointer)->counter2 = 0;
	}
}
void ourNewOgenChar( Char**  pointer  ) {
	if ( *pointer== NULL) {
		(*pointer) = ( Char*) malloc( sizeof( Char ));
		(*pointer)->counter1 = 1;
		(*pointer)->counter2 = 0;
		}
	else {
		(*pointer)->counter1-- ;
		if( ((*pointer)->counter1==0) && ((*pointer)->counter2==0) )  {
			free( (*pointer) );
			(*pointer)=NULL;
		}
		(*pointer) = ( Char*) malloc( sizeof( Char ));
		(*pointer)->counter1 = 1;
		(*pointer)->counter2 = 0;
	}
}
void ourNewPA( A**  pointer  ) {
	if ( (*pointer)== NULL) {
		(*pointer) = ( A*) malloc( sizeof( A ));
		(*pointer)->counter1 = 0;
		(*pointer)->counter2 = 1;
			(*pointer)->c=NULL;
			(*pointer)->b=NULL;
		}
	else {
		(*pointer)->counter2-- ;
		if( ((*pointer)->counter1==0) && ((*pointer)->counter2==0) )  {
			decreaseAndCheckChar(&((*pointer)->c));
			decreaseAndCheckB(&((*pointer)->b));
			free( (*pointer) );
			(*pointer)=NULL;
		}
		(*pointer) = ( A*) malloc( sizeof( A ));
		(*pointer)->counter1 = 0;
		(*pointer)->counter2 = 1;
	}
}
void ourNewPB( B**  pointer  ) {
	if ( (*pointer)== NULL) {
		(*pointer) = ( B*) malloc( sizeof( B ));
		(*pointer)->counter1 = 0;
		(*pointer)->counter2 = 1;
			(*pointer)->c=NULL;
		}
	else {
		(*pointer)->counter2-- ;
		if( ((*pointer)->counter1==0) && ((*pointer)->counter2==0) )  {
			decreaseAndCheckChar(&((*pointer)->c));
			free( (*pointer) );
			(*pointer)=NULL;
		}
		(*pointer) = ( B*) malloc( sizeof( B ));
		(*pointer)->counter1 = 0;
		(*pointer)->counter2 = 1;
	}
}
void ourNewPChar( Char**  pointer  ) {
	if ( (*pointer)== NULL) {
		(*pointer) = ( Char*) malloc( sizeof( Char ));
		(*pointer)->counter1 = 0;
		(*pointer)->counter2 = 1;
		}
	else {
		(*pointer)->counter2-- ;
		if( ((*pointer)->counter1==0) && ((*pointer)->counter2==0) )  {
			free( (*pointer) );
			(*pointer)=NULL;
		}
		(*pointer) = ( Char*) malloc( sizeof( Char ));
		(*pointer)->counter1 = 0;
		(*pointer)->counter2 = 1;
	}
}
void main(){

 A* pa=NULL;
 B* pb=NULL;
 
ourNewOgenA(&(pa));

ourNewPChar(&(pa->c));

ourNewPB(&(pa->b));

ourNewPChar(&(pa->b->c));


changeOgenB(&pb,&(pa->b));


ourNewOgenA(&(pa));
 

}


