#include <stdio.h>
#include "3.h"

int main()
{
	int i = 0;
	DequeType deque;
	Block ex[14] = {
		{10,'B'}, {20,'R'}, {35,'G'}, {5,'G'},{20,'R'},{5,'G'},{35,'B'},
		{20,'R'}, {35,'G'}, {5,'G'}, {20,'R'},{5,'G'},{35,'B'},{10,'B'}
	};
	init(&deque);
	for (i = 0; i < 7; i++) {
		add_front(&deque, ex[i]); 
		display(&deque);
		add_rear(&deque, ex[i+7]);
		display(&deque);
	}

	printf("총 점수 : %d\n", deque.result);
	printf("개수 : %d\n", deque.broken);
}

// 총점수 175 + 400 +350 +175 +400
// 개수 