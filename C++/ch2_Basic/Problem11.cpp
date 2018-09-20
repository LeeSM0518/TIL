#include <iostream>
using namespace std;

int main() {
	int i, j = 1;
	int sum = 0;

	cout << "끝 수를 입력하세요>> ";
	cin >> j;

	for (i = 1; i <= j; i++) {
		sum += i;

	}

	cout << "1에서 " << j << "까지의 합은 " << sum << "입니다." << endl;

	
}