## Double-ended queue (덱)

**덱(deque)** : double-ended queue의 줄임말로서 **큐의 전단(front)과 후단(rear)에서 모두 삽입과 삭제가 가능한 큐를 의미.**

### Deque.h

```c
#define TRUE 1
#define FALSE 0

typedef int element;		// 요소의 타입

typedef struct DlistNode {	// 노드의 타입
	element data;				// 데이터
	struct DlistNode *llink;	// 왼쪽 링크
	struct DlistNode *rlink;	// 오른쪽 링크
}DlistNode;

typedef struct DequeType{	// 덱의 타입
	DlistNode *head;	// 헤드 포인터
	DlistNode *tail;	// 테일 포인터
}DequeType;

void error(char* message);	// 에러 출력 함수
DlistNode *create_node(DlistNode *llink, element item, DlistNode *rlink);	// 노드 생성 함수
void add_rear(DequeType *dq, element item);		// 리어에 삽입 함수
void add_front(DequeType *dq, element item);	// 프론트에 삽입 함수
element delete_front(DequeType *dq);			// 프론트 제거 함수
element delete_rear(DequeType *dq);				// 리어 제거 함수
void init(DequeType *dq);		// 큐 초기화 함수
int is_empty(DequeType *dq);	// 공백 상태 점검 함수
void display(DequeType *dq);	// 큐 출력 함수
```





### Deque.c

```c
#include <stdio.h>
#include "2.h"

int is_empty(DequeType *dq)		// 공백 점검 함수
{	// 헤드 포인터가 NULL 이면 True 반환
	if (dq->head == NULL)
		return TRUE;
	else
		return FALSE;
}

void init(DequeType *dq)		// 큐 초기화 함수
{	// 헤드 포인터와 테일 포인터 NULL로 초기화
	dq->head = dq->tail = NULL;
}

void error(char *message)		// 에러 출력 함수
{
	printf("%s\n", message);
}

DlistNode *create_node(DlistNode *llink, element item, DlistNode *rlink)	// 노드 생성 함수
{	// 왼쪽 링크와 데이터, 오른쪽 링크를 매개 변수로 받아서 새로운 노드에 저장해준다.
	DlistNode *node = (DlistNode*)malloc(sizeof(DlistNode));
	if (node == NULL)
		error("메모리 할당 오류");
	node->llink = llink;
	node->data = item;
	node->rlink = rlink;
	return node;
}

void add_rear(DequeType *dq, element item)	// 리어에 삽입
{	// 새로운 노드의 왼쪽 링크를 현재 큐의 tail, 데이터를 item, 오른쪽 링크를 NULL로 저장해준다.
    // 큐가 비어있으면 새로운 노드가 큐의 헤드가 된다.
    // 큐가 비어있지 않으면 큐의 tail의 오른쪽 링크가 새로운 노드가 된다.
    // 마지막으로 큐의 tail을 새로운 노드로 지정해준다.
	DlistNode *new_node = create_node(dq->tail, item, NULL);
	if (is_empty(dq))
		dq->head = new_node;
	else
		dq->tail->rlink = new_node;
	dq->tail = new_node;
}

void add_front(DequeType *dq, element item)	// 프론트에 삽입
{	// 새로운 노드의 왼쪽 링크는 NULL, 오른쪽 링크는 head, 데이터는 item으로 생성한다.
    // 큐가 비어있으면 큐의 tail을 새로운 노드로 지정한다.
    // 큐가 비어있지 않으면 현재 헤드의 왼쪽 링크를 새로운 노드로 지정한다.
    // 그후 헤드를 새로운 노드로 지정한다.
	DlistNode *new_node = create_node(NULL, item, dq->head);
	
	if (is_empty(dq))
		dq->tail = new_node;
	else
		dq->head->llink = new_node;
	dq->head = new_node;
}

element delete_front(DequeType *dq)		// 프론트 삭제 함수
{	// 큐가 비어있으면 에러 출력
    // 큐가 비어있지 않으면 제거할 노드에 헤드 노드를 저장하고 item 에는 제거할 노드의 데이터를 저장한다.
    // 그 후 큐의 헤드를 헤드의 오른쪽 노드로 저장한다.
    // 삭제할 노드 메모리 반환
    // 그 후 헤드가 NULL 이면 빈 큐 이므로 tail도 NULL 로 저장
    // 헤드가 NULL이 아니면 바뀐 헤드의 왼쪽 링크를 NULL 로 저장
    // 마지막으로 삭제된 노드의 데이터를 반환
	element item=NULL;
	DlistNode *removed_node;

	if (is_empty(dq))
		error("공백 덱에서 삭제");
	else {
		removed_node = dq->head;
		item = removed_node->data;
		dq->head = dq->head->rlink;
		free(removed_node);
		if (dq->head == NULL)
			dq->tail = NULL;
		else
			dq->head->llink = NULL;
	}
	return item;
}

element delete_rear(DequeType *dq)		// 리어 삭제
{	// 큐가 비어있으면 오류 출력
    // 큐가 비어있지 않으면 삭제할 노드에 큐의 tail 위치의 노드 저장하고 item 에 삭제할 노드 데이터 값 저장. 그 후 삭제할 노드 메모리 반환
    // 연산 후 tail이 NULL이면 빈 큐 이므로 head도 NULL로 저장
    // 빈 큐가 아니면 tail의 오른쪽 링크를 NULL로 저장.
	element item=NULL;
	DlistNode *removed_node;

	if (is_empty(dq))
		error("공백 덱에서의 삭제");
	else {
		removed_node = dq->tail;
		item = removed_node->data;
		dq->tail = dq->tail->llink;
		free(removed_node);

		if (dq->tail == NULL)
			dq->head = NULL;
		else
			dq->tail->rlink = NULL;
	}
	return item;
}


void display(DequeType *dq)		// 큐 출력 함수
{	// 큐를 하나 생성 후 그곳에 기존 큐의 헤드를 저장하고 큐가 NULL일때 까지 노드의 오른쪽 링크를 순회하여 출력시켜준다.
	DlistNode *p;
	printf("(");
	for (p = dq->head; p != NULL; p = p->rlink) {
		printf("%d ", p->data);
	}
	printf(")\n");
}
```





### Deque_main.c

```c
#include <stdio.h>
#include "2.h"

int main()
{
	DequeType deque;		// 덱 생성

	init(&deque);			// 덱 초기화
	add_front(&deque, 10);	// 프론트에 10 데이터 삽입	10
	add_front(&deque, 20);	// 프론트에 20 데이터 삽입	20 10
	add_rear(&deque, 30);	// 리어에 30 데이터 삽입	20 10 30
	display(&deque);		// 덱 순회출력 	20 10 30

	delete_front(&deque);	// 프론트 삭제	10 30
	delete_rear(&deque);	// 리어 삭제	10
	display(&deque);		// 덱 순회 출력	10
}
```





### 실행 결과

```
(20 10 30 )
(10 )
```

