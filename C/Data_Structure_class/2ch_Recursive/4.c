#include <stdio.h>

double add(double n);
double sum = 0;

int main(void)
{
	double n;
	double result;

	printf("n을 입력: ");
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
