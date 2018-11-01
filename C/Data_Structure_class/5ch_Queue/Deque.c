#include <stdio.h>
#include "Deque.h"

int is_empty(DequeType *dq)
{
	if (dq->head == NULL)
		return TRUE;
	else
		return FALSE;
}

void init(DequeType *dq)
{
	dq->head = dq->tail = NULL;
	dq->result = 0;
	dq->broken = 0;
}

void error(char *message)
{
	printf("%s\n", message);
}

DlistNode *create_node(DlistNode *llink, Block item, DlistNode *rlink)
{
	DlistNode *node = (DlistNode*)malloc(sizeof(DlistNode));
	if (node == NULL)
		error("메모리 할당 오류");
	node->llink = llink;
	node->data = item;
	node->rlink = rlink;
	return node;
}

void add_rear(DequeType *dq, Block item)
{
	DlistNode *new_node = create_node(dq->tail, item, NULL);
	if (is_empty(dq)) {
		dq->head = new_node;
		dq->tail = new_node;
	}
	else {
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
	
}

Block delete_front(DequeType *dq)
{
	Block item;
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
	
}

Block delete_rear(DequeType *dq)
{
	Block item;
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
	
}


void display(DequeType *dq)
{
	DlistNode *p;
	printf("(");
	for (p = dq->head; p != NULL; p = p->rlink) {
		printf(" %d %c", p->data.grade, p->data.color);
	}
	printf(")\n");
}