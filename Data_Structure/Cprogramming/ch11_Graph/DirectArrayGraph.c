#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct DirectArrayGraphType {
	int nodeCount;		// 노드 개수
	int **ppEdge;		// 간선 저장을 위한 2차원 배열
}DirectArrayGraph;

DirectArrayGraph* createDirectArrayGraph(int nodeCount)
{
	int i = 0;
	DirectArrayGraph *pReturn = NULL;

	// 방향 그래프 구조체 자체의 메모리 할당 및 검증
	pReturn = (DirectArrayGraph*)malloc(sizeof(DirectArrayGraph));
	if (pReturn == NULL) {
		return NULL;
	}

	// 노드 개수에 대한 멤버 변수 초기화
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

int addEdgeDAG(DirectArrayGraph* pGraph, int fromNode, int toNode) {
	int ret = 0;

	// 입력 파라미타가 유효한지 점검 및 간선 추가.
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

int checkVertexValid(DirectArrayGraph* pGraph, int node) {
	// 그래프가 NULL이 아니고 노드의 번호가 노드 개수보다 작고 0보다는 크거나
	// 같아야 한다.
	if (pGraph != NULL && node < pGraph->nodeCount && node >= 0) {
		return 1;
	}
	else {
		return 0;
	}
}

int removeEdgeDAG(DirectArrayGraph* pGraph, int fromNode, int toNode)
{
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

int getEdgeDAG(DirectArrayGraph* pGraph, int fromNode, int toNode)
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

void displayGraphDAG(DirectArrayGraph* pGraph)
{
	int i = 0, j = 0;
	int count = 0;

	if (pGraph != NULL) {
		count = pGraph->nodeCount;
		for (i = 0; i < count; i++) {
			for (j = 0; j < count; j++) {
				printf("%d ", getEdgeDAG(pGraph, i, j));
			}
			printf("\n");
		}
	}
}

void deleteGraphDAG(DirectArrayGraph* pGraph)
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

int main(int argc, char *argv[])
{
	int nodeCount = 6;
	DirectArrayGraph *pG2 = createDirectArrayGraph(nodeCount);
	if (pG2 != NULL) {
		addEdgeDAG(pG2, 0, 1);
		addEdgeDAG(pG2, 1, 2);
		addEdgeDAG(pG2, 2, 0);
		addEdgeDAG(pG2, 2, 3);
		addEdgeDAG(pG2, 3, 2);
		addEdgeDAG(pG2, 3, 4);
		addEdgeDAG(pG2, 4, 5);
		addEdgeDAG(pG2, 5, 3);

		printf("G2 : 방향 그래프\n");
		displayGraphDAG(pG2);
		deleteGraphDAG(pG2);
	}
	system("pause");
}