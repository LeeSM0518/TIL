/*
1) 노드 i의 부모 노드 인덱스 = i/2 , 단 i > 1
2) 노드 i의 왼쪽 자식 노드 인덱스 = 2 * 1
3) 노드 i의 오른쪽 자식 노드 인덱스 = (2 * i) + 1
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct HeapNodeType {
	int data;
}HeapNode;

typedef struct ArrayHeapType {
	int maxCount;		// 최대 자료 개수
	int currentCount;	// 현재 자료 개수
	HeapNode *pData;	// 노드 저장을 위한 1차원 배열
}ArrayMaxHeap, ArrayMinHeap;


ArrayMaxHeap* createArrayMaxHeap(int maxCount)
{
	ArrayMaxHeap *pReturn = NULL;
	if (maxCount > 0) {
		// 히프 구조체 자체의 메모리 할당
		pReturn = (ArrayMaxHeap*)malloc(sizeof(ArrayMaxHeap));
		pReturn->maxCount = maxCount;
		pReturn->currentCount = 0;		// 구조체 변수 초기화
		pReturn->pData = (HeapNode*)malloc(sizeof(HeapNode)*(maxCount + 1));
		memset(pReturn->pData, 0, sizeof(HeapNode)*(maxCount + 1));
		// 자료 저장을 위한 배열의 메모리 할당과 초기화
	}
	else {
		printf("최대 원소 개수는 0보다 커야 합니다\n");
	}

	return pReturn;
}

int insertMaxHeapAH(ArrayMaxHeap* pHeap, int value)
{
	int i = 0;

	if (pHeap != NULL) {
		// 히프의 크기를 초과하여 저장하는지 점검하여 초과 시 실패값 0을 반환
		if (pHeap->currentCount == pHeap->maxCount) {
			printf("오류, 히프가 가득 찼습니다[%d], insertMaxHeapAH()\n", pHeap->maxCount);
			return 0;
		}
		pHeap->currentCount++;		// 현재 노드 개수를 1 증가시킨다.
		i = pHeap->currentCount;	// 변수 i는 히프 마지막 노드의 위치 인덱스 저장
		while ((i != 1) && (value > pHeap->pData[i / 2].data)) {
			// 부모 노드와 키 값 비교와 위치 교환
			pHeap->pData[i] = pHeap->pData[i / 2];
			i /= 2;
		}
		// 최종적으로 구한 위치에 새로운 값 삽입
		pHeap->pData[i].data = value;
	}
	return i;
}

HeapNode* removeMaxHeapAH(ArrayMaxHeap* pHeap)
{
	HeapNode* pReturn = NULL;
	HeapNode* pTemp = NULL;
	int parent = 1, child = 2;	// 루트 노드(1)와 루트 노드의 왼쪽 자식 노드(2) 인덱스 저장

								// 현재 반환 가능한 노드가 있는지 점검
	if (pHeap != NULL && pHeap->currentCount > 0) {
		// 반환되는 노드에 대한 메모리 할당 및 점검
		pReturn = (HeapNode*)malloc(sizeof(HeapNode));
		if (pReturn == NULL) {
			printf("오류, 메모리 할당, deleteMaxHeapAH()\n");
			return NULL;
		}
		// Step-1) 반환되는 노드의 값(자료)으로 기존 루트 노드의 자료(data)를 대입
		pReturn->data = pHeap->pData[1].data;

		// pTemp 가 히프의 제일 마지막 노드를 가리키게 한다.
		// 히프에 저장된 자료의 개수를 1 감소시킨다.
		pTemp = &(pHeap->pData[pHeap->currentCount]);
		pHeap->currentCount--;

		// 왼쪽 자식 노드보다 오른쪽 자식 노드의 키 값이 더 크다면 오른쪽 자식 노드가
		// 비교 대상이 되도록 위치 인덱스 child를 수정한다.
		while (child <= pHeap->currentCount) {
			if ((child < pHeap->currentCount) && pHeap->pData[child].data <
				pHeap->pData[child + 1].data) {
				child++;
			}

			// 앞서 설정한 히프의 제일 마지막 노드와 현재 노드의 키 값을 비교.
			// 만약, 마지막 노드의 값이 현재 노드보다 더 크거나 같다면, 루프를 빠져나온다.
			// 바로 이 위치에서 삽입하면 된다.
			if (pTemp->data >= pHeap->pData[child].data) {
				break;
			}

			// 만약 그렇지 않다면 현재의 노드를 부모 노드의 위치로 한 칸 이동시키고,
			// 다시 아래 레벨로 이동
			pHeap->pData[parent] = pHeap->pData[child];
			parent = child;
			child *= 2;
		}	// while문 : Step-3 계속) 반복 조건 >> 히프의 제일 마지막 위치에 있는 노드를
			// 만날 때까지 루프

			// while 루프를 빠져나온 다음, 현재 위치 parent에 앞서 설정한
			// 히프의 제일 마지막 노드의 값을 대입
		pHeap->pData[parent] = *pTemp;
	}
	return pReturn;
}

void deleteArrayMaxHeap(ArrayMaxHeap* pHeap)
{
	if (pHeap != NULL) {
		if (pHeap->pData != NULL) {
			free(pHeap->pData);
		}
		free(pHeap);
	}
}

void displayArrayHeap(ArrayMaxHeap* pHeap)
{
	int i = 0;
	if (pHeap != NULL) {
		for (i = 1; i <= pHeap->currentCount; i++) {
			printf("[%d], %d\n", i, pHeap->pData[i].data);
		}
	}
}

int main(int argc, char *argv[])
{
	int maxHeapSize = 20;
	ArrayMaxHeap *pHeap1 = NULL;
	HeapNode *pNode = NULL;

	pHeap1 = createArrayMaxHeap(maxHeapSize);
	if (pHeap1 != NULL) {
		insertMaxHeapAH(pHeap1, 90);
		insertMaxHeapAH(pHeap1, 85);
		insertMaxHeapAH(pHeap1, 80);
		insertMaxHeapAH(pHeap1, 75);
		insertMaxHeapAH(pHeap1, 70);
		insertMaxHeapAH(pHeap1, 65);
		insertMaxHeapAH(pHeap1, 60);
		insertMaxHeapAH(pHeap1, 55);
		insertMaxHeapAH(pHeap1, 50);
		insertMaxHeapAH(pHeap1, 45);
		insertMaxHeapAH(pHeap1, 40);
		insertMaxHeapAH(pHeap1, 35);
		insertMaxHeapAH(pHeap1, 30);

		printf("Max Heap : \n");
		displayArrayHeap(pHeap1);

		insertMaxHeapAH(pHeap1, 99);
		printf("After insertMaxHeapAH() - %d\nMax Heap : \n", 99);
		displayArrayHeap(pHeap1);

		pNode = removeMaxHeapAH(pHeap1);
		if (pNode != NULL) {
			printf("removeMaxHeapAH() - [%d]\n", pNode->data);
			free(pNode);
		}
		printf("Max Heap : \n");
		displayArrayHeap(pHeap1);

		deleteArrayMaxHeap(pHeap1);
	}

	system("pause");
}