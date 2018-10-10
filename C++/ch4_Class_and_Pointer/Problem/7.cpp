#include <iostream>
#include <stdlib.h>
#include <string>

using namespace std;

class Person {
	string name_object;		// 이름
	string tel_object;		// 전화번호
public:
	Person() {	// 생성자 선언
		name_object = "NULL";	// 아무값이 주어지지 않으면 NULL로 저장된다.
		tel_object = "NULL";	// 아무값이 주어지지 않으면 NULL로 저장된다.
	};
	string getName() {		// 객체의 이름을 리턴시킨다.
		return name_object;
	}
	string getTel() {		// 객체의 전화번호를 리턴시킨다.
		return tel_object;
	}	
	void set(string name, string tel);
};

void Person::set(string name, string tell)	// 이름과 전화번호를 받아 값을 지정해준다.
{
	name_object = name;		// 이름 설정
	tel_object = tell;		// 전화번호 설정
}

int main() {
	Person person[3];	// 객체 배열 3개 선언

	string name;	// 이름을 임시로 저장
	string tel;		// 전화번호를 임시로 저장

	cout << "이름과 전화 번호를 입력해 주세요" << endl;

	for (int i = 0; i < 3; i++) {
		cout << "사람 " << i + 1 << ">> ";
		cin >> name >> tel;			// 이름과 전화번호를 입력받는다.
		person[i].set(name, tel);	// 입력받은 이름과 전화번호를 i번째 객체에 저장해준다.
	}

	cout << "모든 사람의 이름은 ";
	for (int i = 0; i < 3; i++) {
		cout << person[i].getName() << " ";	// 3개의 객체 배열의 이름들을 출력
	}cout << "\n";

	cout << "전화번호 검색합니다. 이름을 입력하세요 >> ";
	cin >> name;	// 전화번호를 찾을 이름을 저장

	for (int i = 0; i < 3; i++) {
		if (name == person[i].getName()) {	// 찾을 이름과 i번 째 객체의 이름과 비교한다.
			cout << "전화 번호는 " << person[i].getTel() << endl;	// 전화 번호를 출력시키고 for문을 break한다.
			break;
		}
	}

	system("pause");
}
