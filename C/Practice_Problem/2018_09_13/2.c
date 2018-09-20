#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <time.h>
#include <stdlib.h>
int main(void)
{
	char input;

	printf("문자를 입력하시오 : ");
	scanf("%c", &input);

	if (input == 'R')
		printf("Rectangle\n");
	else if (input == 'T')
		printf("Triangle\n");
	else if (input == 'C')
		printf("Circle\n");
	else
	{
		printf("Unknown");
	}
	system("pause");
}