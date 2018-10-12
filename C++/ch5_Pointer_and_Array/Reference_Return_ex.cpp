#include <iostream>
using namespace std;

char& find(char s[], int index) {
	return s[index];	// 참조 리턴
}

int main() {
	char name[] = "Mike";
	cout << name << endl;

	find(name, 0) = 'S';	// name[0]의 위치를 가져와서 'S' 로 변경
	cout << name << endl;

	char& ref = find(name, 2);	// name[2]의 위치를 가져와서 ref 주소에 넣는다.
	ref = 't';	// name[2]의 주소를 't'로 바꿔준다. name = "Site"
	cout << name << endl;
}