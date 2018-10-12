#include <iostream>
using namespace std;

void swap(int *a, int *b) {
	int tmp;

	tmp = *a;
	*a = *b;
	*b = tmp;
}

int main() {
	int m = 2, n = 9;
	cout << "호출 전 : " << " m = " << m << " n = " << n << endl;
	swap(&m, &n);
	cout << "호출 후 : " << " m = " << m << " n = " << n << endl;
	// swap 함수가 값의 주소를 가지고 왓으므로 
	// 값이 변경된다.
}