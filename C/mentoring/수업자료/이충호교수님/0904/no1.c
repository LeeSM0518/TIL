#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
int main()
{
	int i;
	char name[20];
	printf("Input your ID: ");
	scanf("%d", &i);
	printf("Your name: ");
	//scanf("%s", name);
	getchar();//fflush();
	gets(name);
	printf("Your name and ID: %s %d\n", name, i);
	return 0;
}
