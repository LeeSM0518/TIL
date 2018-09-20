#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <time.h>
#include <stdlib.h>

int main(void)
{
	int i, j, k = 9;

	for (i = 0; i < 9; i++)
	{
		for (j = 0; j <= i; j++)
			printf("*");
		printf("\n");
	}

	for (i = 0; i < 9; i++)
	{
		for (j = 0; j <= i; j++)
		{
			if (j != i)
				printf(" ");
			else
				printf("*");
		}

		printf("\n");
	}

	printf("\n");

	for (i = 0; i < 9; i++)
	{
		for (j = 0; j <= k; j++)
		{
			printf(" ");
			if (j == k)
				printf("*");

		}
		k--;
		printf("\n");
	}

	k = 9;
	for (i = 0; i < 5; i++)
	{

		for (j = 0; j <= k; j++)
		{

			if (j == i)
				printf("*");
			else if (j == k)
				printf("*");
			else
				printf(" ");
			

		}k--;
		printf("\n");
	}

	k = 4;
	for (i = 0; i < 7; i++)
	{

		for (j = 0; j <= k; j++)
		{

			if (j == 5-i)
				printf("*");
			else if (j == k)
				printf("*");
			else
				printf(" ");


		}
		k++;
		printf("\n");
	}
system("pause");
}