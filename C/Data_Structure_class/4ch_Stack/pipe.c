#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Stack.h"

#define FALSE 0
#define TRUE 1

int pipe(char *in)
{
	StackType s;
	int count = 0;
	int sum = 0;
	int j;
	char pipe_raser[MAX_STACK_SIZE];
	char ch, open_ch;
	int i, n = strlen(in);
	init(&s);

	for (i = 0; i < n; i++) {
		ch = in[i];
		switch (ch) {
		case'(':
			push(&s, ch);
			count++;
			break;
		case')':
			if (in[i - 1] == '(' && count == 1) {
				pop(&s);
				count--;
				continue;
			}
			else if (in[i - 1] == '(' && count != 1){
				pop(&s);
				count--;
				sum += count;
			}
			else{
				pop(&s);
				sum += count - 1;
				count--;
			}
			break;
		}
	}
	return sum;
}

int main()
{
	char p[MAX_STACK_SIZE];
	int sum = 0;
	printf("입력하세요 : ");
	scanf("%s", p);

	sum = pipe(&p);
	printf("답 : %d\n", sum);
}
// ()(((()())(())()))(())