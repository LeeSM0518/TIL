#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

#define choice_num 3

typedef struct cable {

	char title[20];
	char content[20];
	char creater[20];
	char day[20];
	char time[20];
	int start_time;
	int end_time;
}TV;

int print_kms(int ch);
int print_menu(int ch);
void check_time(TV *q , int *num);
void check_days(char day[] , TV *q , int *num);
void check_search(char search[] , TV *q , int *num);
void plus(TV *q);
void check_delete(char search[], TV *q, int *num);
void check_adjust(char search[], TV *q, int *num);

int main(void)
{
	int choice[choice_num] = { 0 }, i, j;
	int knum = 0, mnum = 0, snum = 0;
	int *num=NULL;
	char search[20];

	TV *k;
	TV *m;
	TV *s;
	TV *sub;

	k = (TV*)malloc(100 * sizeof(TV));
	m = (TV*)malloc(100 * sizeof(TV));
	s = (TV*)malloc(100 * sizeof(TV));
	sub = (TV*)malloc(100 * sizeof(TV));

	while (choice[0] != 4)
	{
		choice[0] = print_kms(choice[0]);

		while (choice[0] != 4)
		{
			if (choice[0] == 1)
			{
				sub = k;
				num = &knum;
			}
			if (choice[0] == 2)
			{
				sub = m;
				num = &mnum;
			}
			if (choice[0] == 3)
			{
				sub = s;
				num = &snum;
			}

			choice[1] = print_menu(choice[1]);
			if (choice[1] == 1)
			{
				plus(&sub[*num]);
				*num=*num+1;
			}

			if (choice[1] == 2)
			{
				printf("시간별(1) , 요일별(2) : ");
				scanf("%d", &choice[2]);

				if (choice[2] == 1)
					check_time(sub, num);
				else
				{
					printf("요일을 입력 하세요( MON, TUE ... ) : ");
					scanf("%s", search);

					check_days(search, sub, num);
				}
			}

			if (choice[1] == 3)
			{
				printf("검색하실 방송 이름을 입력하세요 : ");
				scanf("%s", search);
				check_search(search, sub, num);

			}

			if (choice[1] == 6)
			{
				break;
			}

			if (choice[1] == 4)
			{
				printf("수정하실 방송 이름을 입력하세요 : ");
				scanf("%s", search);
				
				check_adjust(search, sub, num);
			}

			if (choice[1] == 5)
			{
				printf("삭제하실 방송 이름을 입력하세요 : ");
				scanf("%s", search);

				check_delete(search, sub, num);

				printf("삭제 되었습니다.\n");
			}
		}

	}

	free(k);
	free(m);
	free(s);
	free(sub);
}

int print_menu(int ch)
{
	printf("==================\n");
	printf("\t1. 추가 \n ");
	printf("\t2. 출력 \n ");
	printf("\t3. 검색 \n ");
	printf("\t4. 수정 \n ");
	printf("\t5. 삭제 \n ");
	printf("\t6. 이전으로 \n ");
	printf("==================\n");
	printf("메뉴를 선택하시오 : ");
	scanf("%d", &ch);

	return ch;
}

int print_kms(int ch)
{

	printf("==================\n");
	printf("\t방송사 선택 \n ");
	printf("\t1. KBS \n ");
	printf("\t2. MBC \n ");
	printf("\t3. SBS \n ");
	printf("\t4. 종료 \n ");
	printf("==================\n");
	printf("방송사를 선택하시오 : ");
	scanf("%d", &ch);
	return ch;
}

void plus(TV *q)
{

	printf("\n");
	printf("제목 : ");
	scanf("%s", q->title);
	printf("분야 : ");
	scanf("%s", q->content);
	printf("제작진 : ");
	scanf("%s", q->creater);
	printf("방송하는 요일(MON , TUE , ... ) : ");
	scanf("%s", q->day);
	printf("방송 시간( 1:00 ~24:00  ) : ");
	scanf("%s", q->time);
	
	sscanf(q->time, "%d:%d", &q->start_time, &q->end_time);
}

void check_time(TV *q, int *num)
{
	int i, j=0, least = 0;
	TV *tmp;
	tmp = (TV*)malloc(100 * sizeof(TV));

	int time[2];

	if (num != 0)
	{
		for (i = 0; i < *num; i++)
		{
			least = i;
			

			for (j = i + 1; j < *num; j++)
			{
				time[0] = ((q + j)->start_time) * 100 + (q + j)->end_time;
				time[1] = ((q + least)->start_time) * 100 + (q + least)->end_time;
				if (time[0] < time[1])
					least = j;
			}
			tmp[i] = q[i];
			q[i] = q[least];
			q[least] = tmp[i];

		}

		for (i = 0; i < *num; i++)
		{
			printf("\n");
			printf("제목 : %s\n", (q + i)->title);
			printf("분야 : %s\n", (q + i)->content);
			printf("제작진 : %s\n", (q + i)->creater);
			printf("방송하는 요일 : %s\n", (q + i)->day);
			printf("방송 시간 : %s\n", (q + i)->time);
			printf("\n");
		}
		free(tmp);
	}

	else
		printf("방송이 없습니다. \n");
}

void check_days(char day[], TV *q, int *num)
{

	int i = 0, j=0, k,  ch = 0;
	int *count;
	count = &j;
	TV *test;

	test = (TV*)malloc(100 * sizeof(TV));

	for (i = 0; i < *num; i++)
	{
		
		if (strcmp((q + i)->day, day) == 0)
		{
			test[j++] = q[i];
		}
	}
	check_time(test, count);
	free(test);
}

void check_search(char search[], TV *q, int *num)
{

	int i = 0, ch = 0,count=0;
	int len;

	for (i = 0; i < *num; i++)
	{

		if (strcmp((q + i)->title, search)==0)
		{
			ch = 1;
			count++;
		}

		if (ch == 1)
		{
			printf("\n");
			printf("제목 : %s\n", (q + i)->title);
			printf("분야 : %s\n", (q + i)->content);
			printf("제작진 : %s\n", (q + i)->creater);
			printf("방송하는 요일 : %s\n", (q + i)->day);
			printf("방송 시간 : %s\n", (q + i)->time);
			printf("\n");
		}
		ch = 0;
	}

	if (count == 0)
		printf("찾을 수 없습니다.\n");
}

void check_adjust(char search[], TV *q, int *num)
{

	int i = 0, ch = 0, count = 0;
	int choice[5];
	char change[50];
	int change2;

	for (i = 0; i < *num; i++)
	{

		if (strcmp((q + i)->title, search) == 0)
		{
			ch = 1;
			count++;
		}

		if (ch == 1)
		{
			printf("\n");
			printf("제목 : %s\n", (q + i)->title);
			printf("분야 : %s\n", (q + i)->content);
			printf("제작진 : %s\n", (q + i)->creater);
			printf("방송하는 요일 : %s\n", (q + i)->day);
			printf("방송 시간 : %s\n", (q + i)->time);
			printf("\n");

			printf("수정하실 부분을 선택하세요 : ");
			printf("제목(1) , 분야(2) , 제작진(3), 요일(4), 방송시간(5)\n");
			scanf("%d", &choice[0]);

			if (choice[0] == 1)
			{
				printf("바꾸 실 내용을 입력하세요 : ");
				scanf("%s",change);

				strcpy((q + i)->title, change);
			}

			if (choice[0] == 2)
			{
				printf("바꾸 실 내용을 입력하세요 : ");
				scanf("%s", change);

				strcpy((q + i)->content, change);
			}
			if (choice[0] == 3)
			{
				printf("바꾸 실 내용을 입력하세요 : ");
				scanf("%s", change);

				strcpy((q + i)->creater, change);
			}
			if (choice[0] == 4)
			{
				printf("바꾸 실 내용을 입력하세요 : ");
				scanf("%s", change);

				strcpy((q + i)->day, change);
			}
			if (choice[0] == 5)
			{
				printf("바꾸 실 내용을 입력하세요 : ");
				scanf("%s", change);

				strcpy((q + i)->time, change);
			}

			printf("\n");
			printf("제목 : %s\n", (q + i)->title);
			printf("분야 : %s\n", (q + i)->content);
			printf("제작진 : %s\n", (q + i)->creater);
			printf("방송하는 요일 : %s\n", (q + i)->day);
			printf("방송 시간 : %s\n", (q + i)->time);
			printf("\n");
		}
		ch = 0;
	}

	if (count == 0)
		printf("찾을 수 없습니다.\n");
}

void check_delete(char search[], TV *q, int *num)
{
	int i = 0, j ,ch = 0, count = 0;
	int len;

	for (i = 0; i < *num; i++)
	{

		if (strcmp((q + i)->title, search) == 0)
		{
			ch = 1;
			count++;
		}

		if (ch == 1)
		{
			printf("\n");
			printf("제목 : %s\n", (q + i)->title);
			printf("분야 : %s\n", (q + i)->content);
			printf("제작진 : %s\n", (q + i)->creater);
			printf("방송하는 요일 : %s\n", (q + i)->day);
			printf("방송 시간 : %s\n", (q + i)->time);
			printf("\n");
			j = i;
			
			while( (q+j) != NULL)
			{
				q[j] = q[j + 1];
				j++;
				if (j == *num)
				{
					(*num)--;
					break;
				}
			}
		}
		ch = 0;
	}

	if (count == 0)
		printf("찾을 수 없습니다.\n");
}