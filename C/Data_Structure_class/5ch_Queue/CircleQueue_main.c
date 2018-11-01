#include <stdio.h>
#include "CircleQueue.h"

void main()
{
	QueueType q;
	init(&q);
	printf("front = %d rear = %d\n", q.front, q.rear);
	enqueue(&q, 1);
	enqueue(&q, 2);
	enqueue(&q, 3);
	printf("dequeue() = %d\n", dequeue(&q));
	printf("dequeue() = %d\n", dequeue(&q));
	printf("dequeue() = %d\n", dequeue(&q));
	printf("front = %d rear = %d\n", q.front, q.rear);
}