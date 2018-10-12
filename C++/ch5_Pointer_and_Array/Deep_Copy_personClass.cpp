#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstring>
using namespace std;

class Person {
	char *name;
	int id;
public:
	Person(int id, char* name);		// 생성자
	Person(Person& person);			// 복사 생성자
	~Person();						// 소멸자
	void changeName(char *name);	// 이름을 바꿔줄 함수
	void show() {		// 이름을 출력시켜주는 함수
		cout << id << '.' << name << endl;
	}
};

Person::Person(int id, char* name) {	// 생성자
	this->id = id;			// id 저장
	int len = strlen(name);	// name의 문자 개수 저장
	this->name = new char[len + 1];		// name 문자열 공간 할당
	strcpy(this->name, name);			// name에 문자열 복사
}

Person::Person(Person &person) {		// 복사 생성자
	this->id = person.id;				// id 값 복사
	int len = strlen(person.name);		// person.name의 문자 개수 복사
	this->name = new char[len + 1];		// person.name의 문자 복사를 위한
	strcpy(this->name, person.name);	// person.name의 문자 복사
	cout << "복사 생성자 실행. 원본 객체의 이름 " << this->name << endl;
}

Person::~Person() {
	if (name)			// 만일 name에 동적 할당된 배열이 있으면
		delete[] name;	// 동적 할당 메모리 소멸
}

void Person::changeName(char *name) {		// 이름 변경
	if (strlen(name) > strlen(this->name))	
		// 현재 name에 할당된 메모리 보다 긴 이름으로 바꿀 수 없음.
		return;
	strcpy(this->name, name);
}

int main() {
	Person father(1, "Kitae");		// father 객체 생성
	Person daughter(father);		// daughter 객체 복사 생성. 복사 생성자 호출

	cout << "daughter 객체 생성 직후" << endl;
	father.show();		// father 객체 출력
	daughter.show();	// daughther 객체 출력

	daughter.changeName("Grace");	// 이름 변경
	cout << "daughter 이름을 Grace로 변경한 후" << endl;
	father.show();	
	daughter.show();

	return 0;
}