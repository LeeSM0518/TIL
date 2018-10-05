#include <iostream>
#include <string>
using namespace std;

int main() {
	string str;		// 빈 문자열을 가진 스트링 객체
	string address("대전시 유성구 동서대로 125");	
	string copyAddress(address);	
	// address의 문자열을 복사한 스트링 객체 생성

	char text[] = { 'L','o','v','e',' ','C','+','+','\0' };
	string title(text); // "Love C++" 문자열을 가진 스트링 객체 생성

	cout << str << endl;	// 빈 스트링
	cout << address << endl;
	cout << copyAddress << endl;
	cout << title << endl;
}