#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <time.h>
#include <stdlib.h>
int main(void)
{
	srand((unsigned)time(NULL));
	int num[10];
	int exam[10][3];
	int max = 0;
	int min = 101;

	int i, j;

	for (i = 0; i < 10; i++)
		for (j = 0; j < 3; j++)
			exam[i][j] = rand() % 100 + 1;

	printf("학번\t시험1\t시험2\t시험3\t\n");
	
	for (i = 0; i < 10; i++)
	{
		for (j = 0; j < 4; j++)
		{
			if (j == 0)
				printf("%d\t", i + 1);
			else
				printf("%d\t", exam[i][j - 1]);

		}
		printf("\n");
	}

	printf("최대값\t");
	for (j = 0; j < 10; j++)
	{
		if (max < exam[j][0])
			max = exam[j][0];

		if (j == 9)
			printf("%d\t", max);
	}
	max = 0;

	for (j = 0; j < 10; j++)
	{
		if (max < exam[j][1])
			max = exam[j][1];

		if (j == 9)
			printf("%d\t", max);
	}
	max = 0;
	for (j = 0; j < 10; j++)
	{
		if (max < exam[j][2])
			max = exam[j][2];

		if (j == 9)
			printf("%d\t", max);
	}
	max = 0;
	printf("\n");
	printf("최소값\t");

	for (j = 0; j < 10; j++)
	{
		if (min > exam[j][0])
			min = exam[j][0];

		if (j == 9)
			printf("%d\t", min);
	}
	min = 101;
	for (j = 0; j < 10; j++)
	{
		if (min > exam[j][1])
			min = exam[j][1];

		if (j == 9)
			printf("%d\t", min);
	}
	min = 101;
	for (j = 0; j < 10; j++)
	{
		if (min > exam[j][2])
			min = exam[j][2];

		if (j == 9)
			printf("%d\t", min);
	}
	min = 101;

	system("pause");
}