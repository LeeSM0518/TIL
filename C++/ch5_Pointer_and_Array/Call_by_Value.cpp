#include <iostream>
using namespace std;

void swap(int a, int b) {
	int tmp;

	tmp = a;
	a = b;
	b = tmp;
}

int main() {
	int m = 2, n = 9;
	cout << "호출 전 : " << " m = " << m << " n = " << n << endl;
	swap(m, n);
	cout << "호출 후 : " << " m = " << m << " n = " << n << endl;
	// swap 함수가 값을 복사하고 사용한 뒤 
	// 다시 리턴하지 않앗으므로 값이 그대로 이다.
}