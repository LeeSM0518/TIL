#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct SimpleIndexType {
	int position;	// 자료 위치
	int key;		// 키 값
}SimpleIndex;

SimpleIndex* createIndexTable(int values[], int size, int indexSize)
{
	SimpleIndex* pReturn = NULL;
	int i = 0, ratio = 0;

	ratio = size / indexSize;	// 테이블 크기 나누기 색인의 개수
	if (size % indexSize > 0) {	// 단, 나머지가 있는 경우에는 1을 더해준다
		ratio = ratio + 1;
	}

	pReturn = (SimpleIndex*)malloc(sizeof(SimpleIndex)*indexSize);

	for (i = 0; i < indexSize; i++) {
		pReturn[i].position = i * ratio;
		pReturn[i].key = values[i*ratio];
	}

	return pReturn;
}

// 단순 순차 검색
int sequentialRangeSearch(int values[], int start, int end, int key)
{
	int ret = -1;
	int i = 0;
	for (i = start; i <= end && values[i] < key; i++) {

	}
	if (i <= end && values[i] == key) {
		ret = i;
	}
	return ret;
}

// 색인 순차 검색 함수
int sequentialIndexSearch(int values[], int size,
	SimpleIndex indexTable[], int indexSize, int key)
{
	int ret = -1;
	int i = 0;
	int start = 0, end = 0;

	if (key >= values[0] && key <= values[size - 1]) {
		// 색인 테이블 검색을 통해 검색 범위를 정한다
		for (i = 0; i < indexSize; i++) {
			if (indexTable[i].key > key) {
				break;
			}
		}
		// 검색 범위의 처음과 마지막을 저장하는 변수
		if (i < indexSize) {
			// 검색 범위를 정하는 부분
			start = indexTable[i - 1].position;
			end = indexTable[i].position - 1;
		}
		else {
			// 검색 범위가 마지막 색인이 가리키는 자료부터 배열의 끝까지를 검사하면 된다.
			start = indexTable[i - 1].position;
			end = size - 1;
		}

		// 단순 순차 검색
		ret = sequentialRangeSearch(values, start, end, key);
	}
	return ret;
}

// 색인 테이블 내용을 출력
void showIndexTable(SimpleIndex* pIndexTable, int indexSize)
{
	int i = 0;

	printf("인덱스 테이블\n");
	printf("위치, 키\n");
	printf("--------------\n");
	for (i = 0; i < indexSize; i++) {
		printf("%d, %d\n", pIndexTable[i].position, pIndexTable[i].key);
	}
}

// 배열의 내용을 출력
void showArray(int values[], int size) {
	int i = 0;

	printf("위치, 키 값\n");
	printf("-----------\n");
	for (i = 0; i < size; i++) {
		printf("%d, %d\n", i, values[i]);
	}
}

int main(int argc, char *argv[])
{
	SimpleIndex* pIndexTable = NULL;
	int indexSize = 3;
	int index = 0;
	int key = 0;
	int ascSortedValues[] = { 10, 20, 50, 60, 70, 80 };
	int arraySize = 6;

	showArray(ascSortedValues, arraySize);
	pIndexTable = createIndexTable(ascSortedValues, arraySize, indexSize);
	if (pIndexTable != NULL) {
		showIndexTable(pIndexTable, indexSize);

		key = 60;
		index = sequentialIndexSearch(ascSortedValues, arraySize, pIndexTable, indexSize, key);
		if (index >= 0) {
			printf("키 - %d, 위치 - %d\n", key, index);
		}
		else {
			printf("키 - %d, 검색 실패\n", key);
		}

		key = 65;
		index = sequentialIndexSearch(ascSortedValues, arraySize, pIndexTable, indexSize, key);
		if (index >= 0) {
			printf("키 - %d, 위치 - %d\n", key, index);
		}
		else {
			printf("키 - %d, 검색 실패\n", key);
		}

		free(pIndexTable);
	}

	system("pause");
}