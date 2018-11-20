#include <stdio.h>
#include <stdlib.h>

typedef struct LinkedQueueNodeType
{
	char data;
	struct LinkedQueueNodeType* pLink;
}LinkedQueueNode;

typedef struct LinkedQueueType
{
	int currentCount;         // 현재 노드의 개수
	LinkedQueueNode* pFront;  // Front 노드의 포인터
	LinkedQueueNode* pRear;   // Rear 노드의 포인터
} LinkedQueue;