#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

typedef struct book {
	char creater[30];
	char title[30];
	char code;
}Book;

typedef struct Library {
	Book book_a[50];
	int many_a;

}Library;

int main(void)
{
	Book book[50];

	Library array[3];

	array[0].many_a = 0;
	array[1].many_a = 0;
	array[2].many_a = 0;


	int many;
	int i;
	char code;

	printf("몇 개의 책을 입력하실 것 입니까? ");
	scanf("%d", &many);

	for (i = 0; i < many; i++)
	{
		printf("분류 코드를 입력해주세요 ( a, b, c) :");
		scanf(" %c", &code);

		if (code == 'a')
		{
			printf("저자 : ");
			scanf("%s", book[i].creater);

			printf("제목 : ");
			scanf("%s", book[i].title);

			book[i].code = code;

			array[0].book_a[array[0].many_a] = book[i];

			array[0].many_a++;
		}
		if (code == 'b')
		{
			printf("저자 : ");
			scanf("%s", book[i].creater);

			printf("제목 : ");
			scanf("%s", book[i].title);

			book[i].code = code;

			array[1].book_a[array[1].many_a] = book[i];

			array[1].many_a++;
		}
		if (code == 'c')
		{
			printf("저자 : ");
			scanf("%s", book[i].creater);

			printf("제목 : ");
			scanf("%s", book[i].title);

			book[i].code = code;

			array[2].book_a[array[2].many_a] = book[i];

			array[2].many_a++;
		}
			
		
		
	}

	printf("\n");
	printf("검색하실 분류 코드를 입력해주세요 : ");
	scanf(" %c", &code);
	

	if (code == 'a')
	{
		for (i = 0; i < array[0].many_a; i++) {
			printf("저자 : %s\n", array[0].book_a[i].creater);

			printf("제목 : %s\n", array[0].book_a[i].title);

			printf("분류 코드 : %c\n", array[0].book_a[i].code);

			printf("\n");
		}
	}

	if (code == 'b')
	{
		for (i = 0; i < array[1].many_a; i++) {
			printf("저자 : %s\n", array[1].book_a[i].creater);

			printf("제목 : %s\n", array[1].book_a[i].title);

			printf("분류 코드 : %c\n", array[1].book_a[i].code);

			printf("\n");
		}
	}

	if (code == 'c')
	{
		for (i = 0; i < array[2].many_a; i++) {
			printf("저자 : %s\n", array[2].book_a[i].creater);

			printf("제목 : %s\n", array[2].book_a[i].title);

			printf("분류 코드 : %c\n", array[2].book_a[i].code);

			printf("\n");
		}
	}


}