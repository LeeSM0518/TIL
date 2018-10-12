#include <iostream>
#include <string>
using namespace std;

class Book {
	string title;	// 제목
	int price;		// 가격
public:
	Book(string title, int price);		// 제목과 가격 저장
	void set(string title, int price);	// 제목과 가격 변경
	void show(){	// 제목과 가격 출력
		cout << title << '.' << price << " 원" << endl;
	}
};

Book::Book(string title, int price) {
	this->title = title;	// 제목 초기화
	this->price = price;	// 가격 초기화
}

void Book::set(string title, int price) {
	this->title = title;	// 제목 수정
	this->price = price;	// 가격 수정
}

int main() {
	Book cpp("명품C++", 10000);	// cpp 객체 >> 제목 : 명품C++ 가격 : 10000
	Book java = cpp;			// java객체 >> 제목 : 명품C++ 가격 : 10000
	java.set("명품자바", 12000);	// java객체 >> 제목 : 명품자바 가격 : 12000
	cpp.show();		// 제목, 가격 출력
	java.show();	// 제목, 가격 출력
}