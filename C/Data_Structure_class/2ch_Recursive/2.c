#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int fib(int n)
{
	int ret = 0;

	if (n == 3) {
		ret = n;
	}
	else if (n == 1) {
		ret = n;
	}
	else if(n == 2) {
		ret = n;
	}
	else {
		ret = fib(n - 2) + fib(n - 3);
	}
	return ret;
}

int main(void)
{
	int num;
	int result[2];
	int i;

	printf("fibonacci input integer: ");
	scanf("%d", &num);


	for (i = 1; i < num ; i++)
	{
		printf("%d ", fib(i));
	}

	printf("\n");
}
