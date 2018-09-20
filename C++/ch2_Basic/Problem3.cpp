#include <iostream>
using namespace std;

int main() {
	int i, j ,max;
	cout << "두 수를 입력하라>> ";
	cin >> i >> j;

	max = (i > j) ? i : j;

	cout << "큰 수 = " << max <<endl;
}