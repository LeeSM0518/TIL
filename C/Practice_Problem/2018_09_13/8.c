#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <time.h>
#include <stdlib.h>

void scalar_mult(int a[][3], int scalar);
void transpose(int a[][3], int b[][3]);

int main(void)
{
	int i, j;

	int a[3][3] = {
		{1,2,3},
		{4,5,6},
		{7,8,9}
	};
	int b[3][3] = { 0 };
	
	printf("a = \n");
	
	for (i = 0; i < 3; i++)
	{
		for (j = 0; j < 3; j++)
		{
			printf("%d\t", a[i][j]);
		}
		printf("\n");
	}



	int scalar;

	printf("몇 배를 해줄것인가? ");
	scanf("%d", &scalar);

	scalar_mult(a, scalar);
	printf("\n");

	printf("b = \n");

	for (i = 0; i < 3; i++)
	{
		for (j = 0; j < 3; j++)
		{
			printf("%d\t", b[i][j]);
		}
		printf("\n");
	}

	printf("\n");
	transpose(a, b);

	system("pause");
}

void scalar_mult(int a[][3], int scalar)
{
	int i, j;

	for (i = 0; i < 3; i++)
	{
		for (j = 0; j < 3; j++)
		{
			a[i][j] *= scalar;
			printf("%d\t", a[i][j]);
		}
		printf("\n");
	}
}

void transpose(int a[][3], int b[][3])
{
	int i, j;

	for (i = 0; i < 3; i++)
	{
		for (j = 0; j < 3; j++)
		{
			b[i][j] = a[j][i];
			printf("%d\t", b[i][j]);
		}
		printf("\n");
	}
}