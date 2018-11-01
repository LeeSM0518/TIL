# Data Structure

## Circle_Queue

**중요**

> 논리적으로 마지막 위치와 처음 위치를 연결해주기 위해 **mod연산**을 해야한다.



### Circle_Queue.h

```c
#define MAX_QUEUE_SIZE 100
typedef int element;	// 데이터 자료형
typedef struct {	// 큐
	element queue[MAX_QUEUE_SIZE];	// 큐 배열
	int front, rear;	// 프론트, 리어
}QueueType;

void error(char *message);		// 에러 알림
void init(QueueType *q);		// 큐 초기화
int is_empty(QueueType *q);		// 공백 상태 점검
int is_full(QueueType *q);		// 포화 상태 점검
void enqueue(QueueType *q, element item);	// 인큐(삽입)
element dequeue(QueueType *q);	// 디큐(삭제)
element peek(QueueType *q);		// 피트(데이터 반환)
```



### Circle_Queue.c

```c
#include <stdio.h>
#include "Circle_Queue.h"

void error(char *message)	// 에러 함수
{
    // 매개 변수로 메세지를 받고 출력
	fprintf(stderr, "%s\n", message);
}

void init(QueueType *q) {	// 초기화 함수
    // 프론트, 리어 위치 초기화
	q->front = q->rear = 0;
}

int is_empty(QueueType *q) {	// 공백 상태 점검 함수
    // 프론트 위치와 리어 위치가 같으면 빈 큐 이므로 1 출력
	return (q->front == q->rear);
}

int is_full(QueueType *q) {		// 포화 상태 점검 함수
    // 리어에 +1 한 값을 MAX 값으로 mod 연산하였을 때 그 값이 프론트 위치와 같으면 큐가 포화 상태이므로 1 출력
	return ((q->rear + 1) % MAX_QUEUE_SIZE == q->front);
}

void enqueue(QueueType *q, element item) {	// 삽입 함수
    // q의 포화 상태를 점검하고 리어를 +1 하고 MAX를 mod 연산한 값을 저장한다.
    // 바뀐 리어 위치에 데이터를 삽입한다.
	if (is_full(q))
		error("큐가 포화상태입니다.");
	q->rear = (q->rear + 1) % MAX_QUEUE_SIZE;
	q->queue[q->rear] = item;
}

element dequeue(QueueType *q)	// 삭제 함수
{
    // 큐가 비어있는지 점검하고 프론트를 +1 하고 MAX로 mod 연산한 뒤 저장
    // 바뀐 프론트 위치를 반환
	if (is_empty(q))
		error("큐가 공백상태입니다");
	q->front = (q->front + 1) % MAX_QUEUE_SIZE;
	return q->queue[q->front];
}

element peek(QueueType *q)	// 피크 함수
{
    // 큐가 비어있는지 점검하고 프론트를 +1 에 MAX로 mod 연산한 위치의 데이터를 반환 한다.
	if (is_empty(q))
		error("큐가 공백상태입니다");
	return q->queue[(q->front + 1) % MAX_QUEUE_SIZE];
}
```



### Circle_Queue_main.c

```c
#include <stdio.h>
#include "CircleQueue.h"

void main()
{
	QueueType q;	// 큐 생성
	init(&q);		// 큐 초기화
	printf("front = %d rear = %d\n", q.front, q.rear);	// 프론트, 리어 위치 출력
	enqueue(&q, 1);		// 1 데이터 인큐
	enqueue(&q, 2);		// 2 데이터 인큐
	enqueue(&q, 3);		// 3 데이터 인큐
	printf("dequeue() = %d\n", dequeue(&q));	// 디큐
	printf("dequeue() = %d\n", dequeue(&q));	// 디큐
	printf("dequeue() = %d\n", dequeue(&q));	// 디큐
	printf("front = %d rear = %d\n", q.front, q.rear);	// 프론트, 리어 위치 출력
}
```



### 실행 결과

```
front = 0 rear = 0
dequeue() = 1
dequeue() = 2
dequeue() = 3
front = 3 rear = 3
```