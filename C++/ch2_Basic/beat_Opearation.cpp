#include <iostream>
using namespace std;
void main(void)
{
	int a = 3; // 0000 0011
	int b = 1;// 0000 0001
	cout << "a & b = " << (a & b) << endl; // 0000 0001
	cout << "a | b = " << (a | b) << endl; // 0000 0011
	cout << "a ^ b = " << (a ^ b) << endl; // 0000 0010
	cout << "a << b = " << (a << b) << endl; // 0000 0110
	cout << "a >> b = " << (a >> b) << endl; // 0000 0001
	cout << "~a = " << (~a) << endl; // 1111 1100
}