#include <stdio.h>
#include "CircleQueue.h"

void error(char *message)	// 에러 함수
{
	fprintf(stderr, "%s\n", message);
}

void init(QueueType *q) {	// 초기화 함수
	q->front = q->rear = 0;
}

int is_empty(QueueType *q) {	// 공백 상태 점검 함수
	return (q->front == q->rear);
}

int is_full(QueueType *q) {		// 포화 상태 점검 함수
	return ((q->rear + 1) % MAX_QUEUE_SIZE == q->front);
}

void enqueue(QueueType *q, element item) {	// 삽입 함수
	if (is_full(q))
		error("큐가 포화상태입니다.");
	q->rear = (q->rear + 1) % MAX_QUEUE_SIZE;
	q->queue[q->rear] = item;
}

element dequeue(QueueType *q)	// 삭제 함수
{
	if (is_empty(q))
		error("큐가 공백상태입니다");
	q->front = (q->front + 1) % MAX_QUEUE_SIZE;
	return q->queue[q->front];
}

element peek(QueueType *q)	// 피크 함수
{
	if (is_empty(q))
		error("큐가 공백상태입니다");
	return q->queue[(q->front + 1) % MAX_QUEUE_SIZE];
}