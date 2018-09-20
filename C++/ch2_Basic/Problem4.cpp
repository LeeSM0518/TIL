#include <iostream>
using namespace std;

int main() {
	double a[5];
	int i;
	double max = 0;
	cout << "5 개의 실수를 입력하라>> ";
	cin >> a[0] >> a[1] >> a[2] >> a[3] >> a[4];
	
	for (i = 0; i < 5; i++)
	{
		if (max < a[i])
			max = a[i];
	}

	cout << "제일 큰 수 = " << max << endl;
}