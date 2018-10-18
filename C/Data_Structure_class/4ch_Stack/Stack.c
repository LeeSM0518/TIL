#include "Stack.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void init(StackType *s)
{	
	int i;
	for (i = 0; i < MAX_STACK_SIZE; i++)
	{
		s->stack[i] = '0';
	}
	s->top = -1;
}

int is_empty(StackType *s)
{
	return (s->top == -1);
}

int is_full(StackType *s)
{
	return (s->top == MAX_STACK_SIZE - 1);
}

void push(StackType *s, element item)
{
	if (is_full(s)) {
		fprintf(stderr, "스택 포화 에러\n");
		return;
	}
	else s->stack[++(s->top)] = item;
}

element pop(StackType *s)
{
	char p;
	if (is_empty(s)) {
		fprintf(stderr, "스택 공백 에러\n");
		exit(1);
	}
	else
	{
		p = s->stack[(s->top)--];
		s->stack[s->top + 1] = NULL;
		return p;
	}
}

element peek(StackType *s)
{
	if (is_empty(s)) {
		fprintf(stderr, "스택 공백 에러\n");
		exit(1);
	}
	else return s->stack[s->top];
}