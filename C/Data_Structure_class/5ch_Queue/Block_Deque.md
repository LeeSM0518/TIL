# Block_Deque

## Block_Deque.h

```c
typedef struct Block {
	int grade;
	char color;
}Block;		// 요소의 타입
```

> **Deque의 element 요소를 Block으로 변경** ( 나머지 그대로)



## Bloc_Deque.c

```c
void add_rear(DequeType *dq, Block item)
{
	DlistNode *new_node = create_node(dq->tail, item, NULL);
	if (is_empty(dq)) {
		dq->head = new_node;
		dq->tail = new_node;
	}
	else {
        // 큐의 리어 블록 색깔이 삽입할려는 색깔과 같을때
		if (dq->tail->data.color == item.color)
		{
			dq->result += dq->tail->data.grade * item.grade;
			delete_rear(dq);
			dq->broken++;
		}
		else {
			dq->tail->rlink = new_node;
			dq->tail = new_node;
		}
	}
	
}

void add_front(DequeType *dq, Block item)
{
	DlistNode *new_node = create_node(NULL, item, dq->head);

	if (is_empty(dq)) {
		dq->tail = new_node;
		dq->head = new_node;
	}
	else {
        // 큐의 프론트 블록 색깔이 삽입할려는 색깔과 같을때
		if(dq->head->data.color==item.color){
			dq->result += dq->head->data.grade *item.grade;
			delete_front(dq);
			dq->broken++;
		}
		else {
			dq->head->llink = new_node;
			dq->head = new_node;
		}
	}
```



## Block_Deque_main.c

```c
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
```



## 실행결과

```
[(10, B) ]
[(10, B) (20, R) ]
[(20, R) (10, B) (20, R) ]
[(20, R) (10, B) (20, R) (35, G) ]
[(35, G) (20, R) (10, B) (20, R) (35, G) ]
[(35, G) (20, R) (10, B) (20, R) ]
[(20, R) (10, B) (20, R) ]
[(20, R) (10, B) ]
[(10, B) ]
[(10, B) (5, G) ]
[(5, G) (10, B) (5, G) ]
[(5, G) (10, B) (5, G) (35, B) ]
[(35, B) (5, G) (10, B) (5, G) (35, B) ]
[(35, B) (5, G) (10, B) (5, G) ]
총 점수 : 1500
개수 : 5
```

