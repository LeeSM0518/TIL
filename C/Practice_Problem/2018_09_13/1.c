#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <time.h>
#include <stdlib.h>

void win(int ran, int user);

int main(void)
{
	srand((unsigned)time(NULL));

	int random = rand() % 3 + 1;
	int ran;
	int user;
	
	printf("선택하시오 (1. 가위, 2.바위, 3. 보): ");
	scanf("%d", &user);

	if (random == user)
		printf("비겼습니다.\n");
	else if (random != user)
	{
		if (random == 1)
		{
			if (user == 2)
				printf("이겼습니다.\n");
			else
				printf("졌습니다.\n");
		}
		if (random == 2)
		{
			if (user == 3)
				printf("이겼습니다.\n");
			else
				printf("졌습니다.\n");
		}
		if (random == 3)
		{
			if (user == 1)
				printf("이겼습니다.\n");
			else
				printf("졌습니다.\n");
		}
	}
	
	system("pause");
	
}

