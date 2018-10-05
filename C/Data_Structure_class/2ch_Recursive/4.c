#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

double add(double n);
double sum = 0;

int main(void)
{
	double n;
	double result;

	printf("nÀ» ÀÔ·Â; ");
	scanf("%lf", &n);

	result = add(n);

	printf("result = %lf\n", result);
}

double add(double n)
{
	if (n == 1)
		return 1;

	else return 1 /n + add(n - 1);
}