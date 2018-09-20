#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Node {
	char name[10];
	int age;
}Node;

int main(void)
{
	Node min;
	strcpy(min.name, "min");
	min.age = 21;

	printf("name = %s, age = %d\n", min.name, min.age);

}