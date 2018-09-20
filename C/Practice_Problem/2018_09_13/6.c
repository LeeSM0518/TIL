#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <time.h>
#include <stdlib.h>

int main(void)
{
	int n;
	int i=0,j=1;
	int result=0;
	int sum = 0;

	printf("NÀ» ÀÔ·Â : ");
	scanf("%d", &n);

	while (j!=n+1)
	{
		i = j;
		for (; i <= n; i++)
		{
			sum += i;
			if (sum == n)
			{
				result++;
			}
		}
		sum = 0;

		j++;
	}

	printf("result = %d\n", result);

	system("pause");
}