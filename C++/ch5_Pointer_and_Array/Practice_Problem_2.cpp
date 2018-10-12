#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstring>
using namespace std;

class Book {
	char *title;	// 제목 문자열
	int price;		// 가격
public:
	Book(char *title, int price);	// 초기화 생성자
	~Book();
	Book(Book &b);	// 깊은 복사 생성자
	void set(char *title, int price);	// 제목이나 가격을 바꿔주기 위한 함수
	void show() {	// 출력시키는 함수
		cout << title << '.' << price << " 원" << endl;
	}
};

Book::Book(char *title, int price) {
	int len = strlen(title);			// 제목 길이
	this->title = new char[len + 1];	// 제목 길이만큼 배열 선언
	strcpy(this->title, title);			// 제목 저장
	this->price = price;				// 가격 저장
}
Book::~Book() {
	if (title)			// title이 존재할때
		delete[] title;
}

void Book::set(char *title, int price) {	// 제목이나 가격 변경하기 위한 함수
	if (this->title)			// 제목이 존재하면 
		delete[] this->title;	// 메모리 해제
	int len = strlen(title);	// 제목 길이 저장
	this->title = new char[len + 1];	// 제목 배열 저장
	strcpy(this->title, title);	// 제목 변경
	this->price = price;		// 가격 변경
}

Book::Book(Book& b) {
	int len = strlen(b.title);	// b 객체의 제목 길이 복사
	title = new char[len + 1];	// 새로운 객체의 제목 배열 저장
	strcpy(title, b.title);		// b 객체의 제목 복사
	price = b.price;			// b 객체의 가격 복사
}

int main() {
	Book cpp("명품C++", 10000);
	Book java = cpp;			// cpp를 깊은 복사하여 java에 저장
	java.set("명품자바", 12000);	// java 객체 제목, 값 수정하여 저장
	cpp.show();
	java.show();
}