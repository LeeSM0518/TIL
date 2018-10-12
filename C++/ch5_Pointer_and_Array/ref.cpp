#include <iostream>
using namespace std;

int main() {
	cout << "i" << "\t" << "n" << "\t" << "refn" << endl;
	int i = 1;
	int n = 2;
	int &refn = n;
	// 변수를 다른 이름으로 서언 하고 기존 변수를 공유한다.

	n = 4;
	refn++;
	cout << i << "\t" << n << "\t" << refn << endl;

	refn = i;
	refn++;
	cout << i << "\t" << n << "\t" << refn << endl;

	int *p = &refn;
	*p = 20;
	cout << i << "\t" << n << "\t" << refn << endl;
}