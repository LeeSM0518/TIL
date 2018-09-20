#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <string.h>

typedef struct student {
	char name[10];
	int num;
	char phone[15];
}ST;

int main(void)
{
	ST student;
	char name[10];

	printf("학생의 이름은?");
	scanf("%s", name);

	strcpy(student.name, name);

	printf("학번은? ");
	scanf("%d", &student.num);

	printf("연락처?");
	scanf("%s", &student.phone);

	printf("이름 : %s\n", student.name);
	printf("학번 : %d\n", student.num);
	printf("연락처 : %s\n", student.phone);

	system("pause");
}

