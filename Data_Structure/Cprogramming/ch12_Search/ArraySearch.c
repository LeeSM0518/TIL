#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// 정렬되지 않은 배열의 순차 검색
int sequentialSearchNotSorted(int values[], int size, int key)
{
	int ret = -1;
	int i = 0;
	
	// 배열 values의 처음부터 끝까지 루프를 돌면서 검색 키 값을 가지는 자료를 찾는다.
	for (i = 0; i < size && values[i] != key; i++) {
	}
	if (i < size) {
		ret = i;
	}
	return ret;
}

// 정렬된 배열의 순차 검색
int sequentialSearchAsendingSorted(int values[], int size, int key)
{
	int ret = -1;
	int i = 0;
	
	// 배열 values의 처음부터 끝까지 루프를 돌면서 검색 키 값을 가지는 자료를 찾음
	for (i = 0; i < size && values[i] < key; i++) {

	}

	// 검색이 성공한 경우에 대한 처리
	if(i < size && values[i] == key) {
		ret = i;
	}
	return ret;
}

void showArray(int values[], int size)
{
	int i = 0;

	printf("position, key\n");
	printf("-------------\n");
	for (i = 0; i < size; i++) {
		printf("%d, %d\n", i, values[i]);
	}
}

void showSearchResult(int key, int index) {
	if (index >= 0) {
		printf("키 - %d, 위치 - %d\n", key, index);
	}
	else {
		printf("키 - %d, 검색 실패\n", key);
	}
}

int main(int argc, char *argv[])
{
	int index = 0, count = 0;
	int notSortedArray[] = { 80, 20, 70, 50 };
	int ascSortedArray[] = { 20, 50, 70, 80 };

	// 1. 정렬되어 있지 않은 경우
	count = sizeof(notSortedArray) / sizeof(int);
	showArray(notSortedArray, count);

	// 1-1. 검색 성공의 경우 : 키 값 70
	index = sequentialSearchNotSorted(notSortedArray, count, 70);
	showSearchResult(70, index);

	// 1-2. 검색 실패의 경우 : 키 값 25
	index = sequentialSearchNotSorted(notSortedArray, count , 25);
	showSearchResult(25, index);

	// 2. 정렬되어 있는 경우
	count = sizeof(ascSortedArray) / sizeof(int);
	showArray(ascSortedArray, count);

	// 2-1. 검색 성공의 경우 : 키 값 70
	index = sequentialSearchAsendingSorted(ascSortedArray, count, 70);
	showSearchResult(70, index);

	// 2-2. 검색 실패의 경우 : 키 값 25
	index = sequentialSearchAsendingSorted(ascSortedArray, count ,25);
	showSearchResult(5, index);

	system("pause");
}