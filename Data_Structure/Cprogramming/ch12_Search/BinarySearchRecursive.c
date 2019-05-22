#include <stdio.h>
#include <stdlib.h>

int binarySearchRecursive(int values[], int start, int end, int key)
{
	int ret = -1;
	int middle = 0;

	if (start <= end) {
		middle = (start + end) / 2;		// 소수 자리 무시
		if (key == values[middle]) {	// 중간 자료의 값과 키 값이 같다면 검색 성공
			ret = middle;
		}
		else if (key < values[middle]) {	// 검색키가 더 작다면 왼쪽 범위의 자료 검색
			ret = binarySearchRecursive(values, start, middle - 1, key);
		}
		else {	// 검색키가 더 크다면 오른쪽 범위의 자료 검색
			ret = binarySearchRecursive(values, middle + 1, end, key);
		}
	}

	return ret;
}

int main(int argc, const char* argv[])
{
	int key = 0;
	int index = 0;
	int ascSortedArray[] = { 10, 20, 50, 60, 70, 80 };

	key = 60;
	index = binarySearchRecursive(ascSortedArray, 0, 5, key);
	if (index >= 0) {
		printf("키 - %d, 위치 - %d\n", key, index);
	}
	else {
		printf("키 - %d, 검색 실패\n", key);
	}

	key = 65;
	index = binarySearchRecursive(ascSortedArray, 0, 5, key);
	if (index >= 0) {
		printf("키 - %d, 위치 - %d\n", key, index);
	}
	else {
		printf("키 - %d, 검색 실패\n", key);
	}
	
	system("pause");
}