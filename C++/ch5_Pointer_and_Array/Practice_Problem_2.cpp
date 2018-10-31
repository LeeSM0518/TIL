#include <iostream>
#include <cstring>
using namespace std;

class Book {
	char* title;
	int price;
public:
	Book(char* title, int price);
	~Book();
	Book(Book &c);
	void set(char *title, int price);
	void show() {
		cout << title << '.' << price << " 원" << endl;
	}
};

Book::Book(char* title, int price)
{
	this->price = price;
	int len = strlen(title);
	this->title = new char[len + 1];
	strcpy(this->title, title);
}

Book::~Book() {
	if (title)
		delete[] title;
}

Book::Book(Book &b) {
	int len = strlen(b.title);
	title = new char[len + 1];
	strcpy(title, b.title);
	price = b.price;
}

void Book::set(char *title, int price) {
	if (this->title)
		delete[] this->title;
	int len = strlen(title);
	this->title = new char[len + 1];
	strcpy(this->title, title);
	this->price = price;
}


int main() {
	Book cpp("C++", 10000);
}