#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
int main()
{
	int a[3]; // = { 1,2,3 };
	int i, sum=0;
	printf("Input three nubmers: ");
	for (i = 0; i < 3; i++)
		scanf("%d", &a[i]);
	for (i = 0; i < 3; i++)
		sum += a[i];
	printf("sum=%d\n", sum);
	return 0;
}
