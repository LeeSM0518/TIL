#include <stdio.h>
#include <stdlib.h>
#include "arrayheap.h"

void printArray(int value[], int count);

void heapsort(int values[], int count)
{
	int i = 0;
	ArrayMaxHeap* pHeap = NULL;

	
	pHeap = createArrayMaxHeap(10);	// 최대 히프를 생성
	if (pHeap != NULL) {	// 최대 히프를 검증한다.
		// 전달받은 정수 자료를 최대 히프에 추가한다.
		for (i = 0; i < count; i++) {
			insertMaxHeapAH(pHeap, values[i]);
		}
		// 전달받은 자료의 개수만큼 최대 히프에서 제거 연산을 실행한다.
		for (i = 0; i < count; i++) {
			HeapNode *pNode = removeMaxHeapAH(pHeap);
			if (pNode != NULL) {
				values[i] = pNode->data;
				free(pNode);
			}
		}
		// 생성한 최소 히프를 메모리 해제시킨다.
		deleteArrayMaxHeap(pHeap);
	}
	else {
		printf("오류, createArrayMinHeap() in heapSort()\n");
		return;
	}
}

void printArray(int values[], int count)
{
	int i = 0;
	for (i = 0; i < count; i++) {
		printf("%d ", values[i]);
	}
	printf("\n");
}

int main(int argc, char *argv[])
{
	int values[] = { 10, 50, 70, 80, 60, 20, 40, 30 };

	printf("Before Sort\n");
	printArray(values, 8);

	heapsort(values, 8);

	printf("After Sort\n");
	printArray(values, 8);

	system("pause");
}