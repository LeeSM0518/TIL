#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>

void MatrixSum(int A[3][3], int B[3][3], int C[3][3]);
void MatrixSub(int A[3][3], int B[3][3], int C[3][3]);
void MatrixMul(int A[3][3], int B[3][3], int C[3][3]);

int main(void)
{
	int a[3][3], b[3][3], c[3][3] = { 0 };
	int i, j;

	for (i = 0; i<3; i++)
	{
		for (j = 0; j<3; j++)
		{
			printf("A[%d][%d] : ", i, j);
			scanf("%d", &a[i][j]);

		}
	}

	for (i = 0; i<3; i++)
	{
		for (j = 0; j<3; j++)
		{
			printf("B[%d][%d] : ", i, j);
			scanf("%d", &b[i][j]);

		}
	}

	MatrixSum(a, b, c);
	MatrixSub(a, b, c);
	MatrixMul(a, b, c);

	system("pause");
}

void MatrixSum(int A[3][3], int B[3][3], int C[3][3])
{
	int i, j;
	printf("A + B = \n");
	for (i = 0; i<3; i++)
	{
		for (j = 0; j<3; j++)
		{
			C[i][j] += A[i][j] + B[i][j];
			printf("%d\t", C[i][j]);
		}
		printf("\n");
	}

}
void MatrixSub(int A[3][3], int B[3][3], int C[3][3])
{
	int i, j;
	printf("A - B = \n");
	for (i = 0; i<3; i++)
	{
		for (j = 0; j<3; j++)
		{
			C[i][j] = A[i][j] - B[i][j];
			printf("%d\t", C[i][j]);
		}
		printf("\n");
	}
}
void MatrixMul(int A[3][3], int B[3][3], int C[3][3])
{
	int i, j,k;
	int sum = 0;
	printf("A * B = \n");

	for (i = 0; i<3; i++)
	{
		for (j = 0; j<3; j++)
		{
			sum = 0;
			for (k = 0; k < 3; k++)
			{
				sum += A[i][k] * B[k][j];
				
			}C[i][j] = sum;
		}
		
		
		
	}

	for (i = 0; i<3; i++)
	{
		for (j = 0; j<3; j++)
		{
			printf("%d\t", C[i][j]);
		}
		printf("\n");
	}
}

/*
printf("%d\t", C[i][j]);
printf("\n");
*/