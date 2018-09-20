#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <time.h>
#include <stdlib.h>

int add(int a, int b,int sum)
{
	for (; a <= b; a++)
	{
		sum += a;
	}
	return sum;
}
int main(void)
{
	int a = 3, b = 5;
	int sum = 0;
	sum = add(a, b, sum);
	printf("sum = %d\n",sum);

	
	system("pause");

}