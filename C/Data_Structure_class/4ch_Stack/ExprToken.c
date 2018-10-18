#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Stack.h"

#define FALSE 0
#define TRUE 1

int check_matching(char *in)
{
	StackType s;
	char ch, open_ch;
	int i, n = strlen(in);
	init(&s);

	for (i = 0; i < n; i++) {
		ch = in[i];
		switch (ch) {
			case'(':
			case'[':
			case'{':
				push(&s, ch);
				break;
			case')':
			case']':
			case'}':
				if (is_empty(&s)) return FALSE;
				else {
					open_ch = pop(&s);
					if ((open_ch == '(' && ch != ')') ||
						(open_ch == '[' && ch != ']') ||
						(open_ch == '{' && ch != '}')) {
						return FALSE;
					}
					break;
				}
		}
	}
	if (!is_empty(&s)) return FALSE;
	return TRUE;
}

int main()
{
	if (check_matching("{A[(i+1)] = 0;}") == 1)
		printf("괄호검사성공\n");
	else
		printf("괄호검사실패\n");
}