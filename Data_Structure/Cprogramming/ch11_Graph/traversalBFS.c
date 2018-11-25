#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "linkedqueue.h"

#define ARRAY_GRAPH_TYPE_DIRECT 0
#define ARRAY_GRAPH_TYPE_UNDIRECT 1

typedef struct ArrayGraphType {
	int graphType;		// 그래프의 종류: 방향 그래프, 무방향 그래프
	int nodeCount;		// 노드 개수
	int **ppEdge;		// 간선 저장을 위한 2차원 배열
}ArrayGraph;

ArrayGraph* createArrayGraph(int graphType, int nodeCount)
{
	int i = 0;
	ArrayGraph *pReturn = NULL;

	// 방향 그래프 구조체 자체의 메모리 할당 및 검증
	pReturn = (ArrayGraph*)malloc(sizeof(ArrayGraph));
	if (pReturn == NULL) {
		return NULL;
	}

	// 노드 개수에 대한 멤버 변수 초기화
	pReturn->graphType = graphType;
	pReturn->nodeCount = nodeCount;

	// 포인터 변수들의 1차원 배열을 위한 메모리 할당 및 검증
	pReturn->ppEdge = (int**)malloc(sizeof(int*) * nodeCount);
	if (pReturn->ppEdge == NULL) {
		return NULL;
	}

	// 각각의 줄별로 메모리 할당 및 검증
	for (i = 0; i < nodeCount; i++) {
		pReturn->ppEdge[i] = (int*)malloc(sizeof(int*)*nodeCount);
		if (pReturn->ppEdge[i] == NULL) {
			return NULL;
		}
		// 각 줄별로 메모리 초기화 : 0으로 초기화
		memset(pReturn->ppEdge[i], 0, sizeof(int) * nodeCount);
	}
	return pReturn;
}

int addEdgeInternalAG(ArrayGraph* pGraph, int fromNode, int toNode)
{
	int ret = 0;
	
	if (pGraph != NULL
		&& checkVertexValid(pGraph, fromNode)
		&& checkVertexValid(pGraph, toNode)) {
		pGraph->ppEdge[fromNode][toNode] = 1;
	}
	else {
		ret = -1;
	}

	return ret;
}

int addEdgeAG(ArrayGraph* pGraph, int fromNode, int toNode) {
	int ret = 0;

	// fromNode와 노드 toNode를 연결하는 간선 추가
	ret = addEdgeInternalAG(pGraph, fromNode, toNode);

	// 만약 현재 그래프가 무방향 그래프라고 한다면 반대 방향인
	// 노드 toNode에서 노드 fromNode를 연결하는 간선 추가
	if (0 == ret && ARRAY_GRAPH_TYPE_UNDIRECT == pGraph->graphType) {
		ret = addEdgeInternalAG(pGraph, toNode, fromNode);
	}
	return ret;
}

int checkVertexValid(ArrayGraph* pGraph, int node) {
	// 그래프가 NULL이 아니고 노드의 번호가 노드 개수보다 작고 0보다는 크거나
	// 같아야 한다.
	if (pGraph != NULL && node < pGraph->nodeCount && node >= 0) {
		return 1;
	}
	else {
		return 0;
	}
}

int removeEdgeInternalAG(ArrayGraph* pGraph, int fromNode, int toNode) {
	int ret = 0;

	// 입력 파라미타 유효 점검 및 간선 제거.
	if (pGraph != NULL
		&& checkVertexValid(pGraph, fromNode)
		&& checkVertexValid(pGraph, toNode)) {
		pGraph->ppEdge[fromNode][toNode] = 0;
	}
	else {
		ret = -1;
	}
	return ret;
}

int removeEdgeAG(ArrayGraph* pGraph, int fromNode, int toNode)
{
	int ret = 0;

	ret = removeEdgeInternalAG(pGraph, fromNode, toNode);
	if (0 == ret && ARRAY_GRAPH_TYPE_UNDIRECT == pGraph->graphType) {
		ret = removeEdgeInternalAG(pGraph, toNode, fromNode);
	}
}

int getEdgeAG(ArrayGraph* pGraph, int fromNode, int toNode)
{
	int ret = 0;

	// 입력 파라미타 유효 점검 및 해당 위치 원소 값 반환
	if (pGraph != NULL
		&& checkVertexValid(pGraph, fromNode)
		&& checkVertexValid(pGraph, toNode)) {
		ret = pGraph->ppEdge[fromNode][toNode];
	}
	return ret;
}

void displayGraphAG(ArrayGraph* pGraph)
{
	int i = 0, j = 0;
	int count = 0;

	if (pGraph != NULL) {
		count = pGraph->nodeCount;
		for (i = 0; i < count; i++) {
			for (j = 0; j < count; j++) {
				printf("%d ", getEdgeAG(pGraph, i, j));
			}
			printf("\n");
		}
	}
}

void deleteGraphAG(ArrayGraph* pGraph)
{
	int i = 0;

	if (pGraph != NULL) {
		for (i = 0; i < pGraph->nodeCount; i++) {
			// 각 행별로 반복문을 돌면서 해당 1차 배열의 메모리 해제
			free(pGraph->ppEdge[i]);
		}
		// 1차원 포인터 변수 배열에 대한 메모리 해제
		free(pGraph->ppEdge);
		// 최종적으로 그래프 자체의 메모리 해제
		free(pGraph);
	}
}

void traversalBFS(ArrayGraph* pGraph, int startNodeID)
{
	int i = 0;
	int nodeID = 0;
	LinkedQueue* pQueue = NULL;
	LinkedQueueNode* pQueueNode = NULL;
	int *pVisit = NULL;

	// 그래프가 NULL인지 점검
	if (pGraph == NULL) {
		return;
	}
	// 큐의 생성, 노드 방문 여부를 저장하는 동적 배열 할당
	pQueue = createLinkedQueue();
	pVisit = malloc(sizeof(int)*pGraph->nodeCount);
	
	// 유효성 점검, 큐 배열에 대한 NULL 여부 점검
	if (pQueue == NULL || pVisit == NULL) {
		return;
	}
	// 노드 방문 여부를 모두 '방문 하지 않음 : 0' 으로 초기화
	memset(pVisit, 0, sizeof(int)*pGraph->nodeCount);
	pVisit[startNodeID] = 1;
	enqueueLQ(pQueue, startNodeID);

	// 큐가 공백(empty)상태가 될때까지 while 반복문 실행
	while (isLinkedQueueEmpty(pQueue) == 0) {
		// 큐에서 디큐 연산을 수행하여 꺼낸 노드에 대해 방문 정보를 출력함
		pQueueNode = dequeueLQ(pQueue);
		if (pQueueNode != NULL) {
			nodeID = pQueueNode->data;
			printf("노드 - [%d] 방문\n", nodeID);
			// 디큐한 노드에 인접한 노드들에 대해서 처리함
			for (i = 0; i < pGraph->nodeCount; i++) {
				if (i != nodeID) {	// 종료 노드가 디큐한 노드가 되어서는 안됨.
					// 간선이 존재하는(인접한) 노드인지 점검
					if (0 != getEdgeAG(pGraph, nodeID, i)) {
						if (0 == pVisit[i]) {
							// 방문 처리하고 큐에 인큐함
							pVisit[i] = 1;
							enqueueLQ(pQueue, i);
						}
					}
				}
			}
			free(pQueueNode);
		}
	}

	deleteLinkedQueue(pQueue);
	free(pVisit);
}

int main(int argc, char *argv[])
{
	int nodeCount = 4;
	ArrayGraph *pG1 = createArrayGraph(ARRAY_GRAPH_TYPE_UNDIRECT, nodeCount);

	if (pG1 != NULL) {
		addEdgeAG(pG1, 0, 1);
		addEdgeAG(pG1, 0, 2);
		addEdgeAG(pG1, 1, 3);

		printf("G1 : BFS\n");
		traversalBFS(pG1, 0);

		deleteGraphAG(pG1);
	}

	system("pause");
}