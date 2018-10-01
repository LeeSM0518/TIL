#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
int add(int a, int b);
int main()
{
	printf("answer = %d\n", add(3, 4));
	return 0;
}
int add(int a, int b)
{
	return a + b;
}
