#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstring>
using namespace std;

class Person {
	char *name;
	int id;
public:
	Person(int id, char* name);
	~Person();
	void changeName(char *name);
	void show() {
		cout << id << '.' << name << endl;
	}
};

Person::Person(int id, char *name) {
	this->id = id;
	int len = strlen(name);
	this->name = new char[len + 1];
	strcpy(this->name, name);
}

Person::~Person() {
	if (name)
		delete[] name;
}

void Person::changeName(char *name) {
	if (strlen(name) > strlen(this->name))
		return;
	strcpy(this->name, name);
}

int main() {
	Person father(1, "Kitae");
	Person daughter(father);

	cout << "dougther 객체 생성 직후 " << endl;
	father.show();
	daughter.show();

	daughter.changeName("Grace");
	cout << "daughter 이름을 Grace로 변견한 수" << endl;
	father.show();
	daughter.show();

	// daughter, father 순으로 소멸, father 가 소멸할 때,
	// 프로그램이 비정상 종료됨.

	// 이유는 두 객체가 같은 메모리 주소를 가지고 있는 멤버변수를 가지고 있으므로
	// daugher 객체와 father 객체의 소멸자가 동일 주소를 두번 반환함.
}