#include <iostream>
using namespace std;

int main() {
	cout << "주소를 입력하세요 : ";

	char address[100];

	cin.getline(address, 100, '\n');
	// 빈칸이 있어도 <Enter> 키가 입력될 때 까지 하나의 문자열로 인식

	cout << "주소는 " << address << " 입니다.\n";
}