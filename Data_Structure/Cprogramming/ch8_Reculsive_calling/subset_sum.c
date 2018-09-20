#include <stdio.h>
#include <stdlib.h>

int *data;
int *flag;
int sum = 0;
int count = 0;

void subset(int n, int depth, int s)
{
	if (n == depth)
	{
		int i;
		for (i = 0; i<n; i++)
		{
			if (flag[i] == 1)
			{
				sum += data[i];
			}
		}
		if (sum == s)
			count++;
		sum = 0;
		return;
	}
	flag[depth] = 1;
	subset(n, depth + 1, s);
	flag[depth] = 0;
	subset(n, depth + 1, s);

}

int main()
{
	int integer;
	int num;
	int i;

	printf("정수의 개수: ");
	scanf("%d", &num);

	printf("원소들의 합이 될 정수 :");
	scanf("%d", &integer);

	data = (int*)malloc(sizeof(int)*num);
	flag = (int*)calloc(num, sizeof(int));

	for (i = 0; i < num; i++)
	{
		printf("원소 : ");
		scanf("%d", &(*(data+i)));
	}

	subset(num, 0, integer);

	if (integer == 0)
		count--;

	printf("count = %d\n", count);

	free(data);
	free(flag);

}