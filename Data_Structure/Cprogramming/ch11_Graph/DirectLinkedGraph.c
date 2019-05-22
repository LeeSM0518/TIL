#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "linkedlist.h"

typedef struct DirectLinkedGraphType
{
	int nodeCount;				// 노드 개수
	LinkedList** ppAdjEdge;		// 간선 저장을 위한 연결 리스트(포인터)의 배열
}DirectLinkedGraph;

DirectLinkedGraph* createDirectLinkedGraph(int nodeCount)
{
	int i = 0;
	DirectLinkedGraph *pReturn = NULL;

	// 그래프 구조체에 대한 메모리 할당 및 점검
	if (nodeCount > 0) {
		pReturn = (DirectLinkedGraph*)malloc(sizeof(DirectLinkedGraph));
		if (pReturn == NULL) {
			printf("오류, 메모리 할당(1), in createLinkedGraph()\n");
			return NULL;
		}
		pReturn->nodeCount = nodeCount;
	}
	else {
		printf("오류, 최대 노드 개수는 0 보다 커야합니다\n");
		return NULL;
	}

	// 1차원 배열의 메모리 할당 및 점검
	// : 연결 리스트의 1차원 배열
	pReturn->ppAdjEdge = (LinkedList**)malloc(sizeof(LinkedList*)*nodeCount);
	if (pReturn->ppAdjEdge == NULL) {
		printf("오류, 메모리 할당(3), in createLinkedGraph()\n");
		if (pReturn != NULL) free(pReturn);
		return NULL;
	}

	// 1차원 배열의 각 연결 리스트의 생성 및 점검
	for (i = 0; i < nodeCount; i++) {
		pReturn->ppAdjEdge[i] = createLinkedList();
		if (pReturn->ppAdjEdge[i] == NULL) {
			printf("오류, 메모리 할당(4), in createLinkedGraph()\n");
			if (pReturn->ppAdjEdge != NULL) free(pReturn->ppAdjEdge);
			if (pReturn != NULL) free(pReturn);
			return NULL;
		}
	}
	return pReturn;
}

int checkVertexValid(DirectLinkedGraph* pGraph, int node)
{
	if (pGraph != NULL && node >= 0 && node < pGraph->nodeCount) {
		return 1;
	}
	else {
		return 0;
	}
}

int addEdgeDLG(DirectLinkedGraph* pGraph, int fromNode, int toNode)
{
	int ret = 0;

	// 입력 파라미타 점검 및 연결 리스트를 활용해
	// 특정 위치에 새로운 자료를 추가
	if (pGraph != NULL
		&& checkVertexValid(pGraph, fromNode)
		&& checkVertexValid(pGraph, toNode)) {
		addLinkedListData(pGraph->ppAdjEdge[fromNode], 0, toNode);
	}
	else {
		ret = -1;
	}
	return ret;
}

int removeEdgeDLG(DirectLinkedGraph* pGraph, int fromNode, int toNode)
{
	int ret = -1;
	LinkedList *pList = NULL;
	int i = 0, count = 0;

	// 입력 파라미타 값 점검 및 시작 노드와 종료 노드의 유효성 점검
	if (pGraph != NULL
		&& checkVertexValid(pGraph, fromNode)
		&& checkVertexValid(pGraph, toNode)) {
		// 시작 노드의 인덱스 값으로 해당 연결 리스트 가져오기
		pList = pGraph->ppAdjEdge[fromNode];

		// 연결 리스트에서 제거 대상이 되는 간선이 어느 노드에
		// 저장되었는지 차례로 점검
		count = getLinkedListLength(pList);
		for (i = 0; i < count; i++) {
			if (getLinkedListData(pList, i) == toNode) {
				removeLinkedListData(pList, i);
				ret = 0;
				break;
			}
		}
	}
	else {
		ret = -1;
	}
	return ret;
}

int getEdgeDLG(DirectLinkedGraph* pGraph, int fromNode, int toNode)
{
	int ret = 0;
	LinkedList *pList = NULL;
	int i = 0, count = 0;

	// 입력 파라미타 유효 점검
	if (pGraph != NULL
		&& checkVertexValid(pGraph, fromNode)
		&& checkVertexValid(pGraph, toNode)) {
		// fromNode 를 이용하여 pList 선택
		pList = pGraph->ppAdjEdge[fromNode];
		
		// 저장된 자료의 개수를 구하여 count에 대입
		count = getLinkedListLength(pList);

		// 인덱스의 값이 toNode와 같다면, 종료 노드가 toNode인
		// 간선이 존재한다는 뜻
		for (i = 0; i < count; i++) {
			if (getLinkedListData(pList, i) == toNode) {
				ret = 1;
				break;
			}
		}
	}
	return ret;
}

void displayGraphDLG(DirectLinkedGraph* pGraph)
{
	int i = 0, j = 0, count = 0;

	if (pGraph != NULL) {
		count = pGraph->nodeCount;
		for (i = 0; i < count; i++) {
			for (j = 0; j < count; j++) {
				if (getEdgeDLG(pGraph, i, j)) {
					printf("1 ");
				}
				else {
					printf("0 ");
				}
			}
			printf("\n");
		}
	}
}

void deleteGraphDLG(DirectLinkedGraph* pGraph)
{
	int i = 0;

	if (pGraph != NULL) {
		for (i = 0; i < pGraph->nodeCount; i++) {
			deleteLinkedList(pGraph->ppAdjEdge[i]);
		}
		if (pGraph->ppAdjEdge != NULL) free(pGraph->ppAdjEdge);
		free(pGraph);
	}
}

int main(int argc, char *argv[])
{
	int nodeCount = 6;
	DirectLinkedGraph *pG2 = createDirectLinkedGraph(nodeCount);
	if (pG2 != NULL) {
		addEdgeDLG(pG2, 0, 1);
		addEdgeDLG(pG2, 1, 2);
		addEdgeDLG(pG2, 2, 0);
		addEdgeDLG(pG2, 2, 3);
		addEdgeDLG(pG2, 3, 2);
		addEdgeDLG(pG2, 3, 4);
		addEdgeDLG(pG2, 4, 5);
		addEdgeDLG(pG2, 5, 3);

		printf("G2 : 방향 그래프\n");
		displayGraphDLG(pG2);

		deleteGraphDLG(pG2);
	}
	system("pause");
}