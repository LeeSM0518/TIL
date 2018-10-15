#include <stdio.h>

int add(int n);

int main(void)
{
	int n;
	int result;

	printf("n을 입력: ");
	scanf("%d", &n);

	result = add(n);

	printf("result = %d\n", result);
}

int add(int n)
{
	if (n == 1)
		return 1;
	else return n + add(n - 1);
}
