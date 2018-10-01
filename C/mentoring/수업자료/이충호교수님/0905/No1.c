#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
int main()
{
	int id;
	char name[20];
	printf("Input name id: ");
	scanf("%s%d", name, &id);
	printf("Name: %s\tID: %d\n", name, id);
	return 0;
}