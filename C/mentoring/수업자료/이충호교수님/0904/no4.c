#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
int add(int a, int b);
int main()
{
	int a[3] = { 1,2,3 }, i, sum = 0;
	//printf("input three numbers: ");
	//scanf("%d%d%d", &a[0], &a[1], &a[2]);
	for (i = 0; i < 3; i++)
		sum += a[i];
	printf("sum = %d\n", sum);
	return 0;
}
int add(int a, int b)
{
	return a + b;
}
