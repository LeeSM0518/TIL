#include <iostream>
using namespace std;

class Circle {
private:
	static int numOfCircles;
	int radius;
public:
	Circle(Circle& c);
	Circle() {
		radius = 1;
		numOfCircles++;
	}
	~Circle() {
		numOfCircles--;
	}
	Circle(int radius) {
		this->radius = radius;
		numOfCircles++;
	}
	double getArea() {
		return 3.14 * radius * radius;
	}
	static int getNumOfCircles() {
		return numOfCircles;
	}
};

int Circle::numOfCircles = 0;

Circle::Circle(Circle& c) {
	this->radius = c.radius;
	cout << "복사 생성자 실행 radius = " << radius << endl;
}

int main() {
	Circle *p = new Circle[10];
	cout << "생존하고 있는 원의 개수 = " << Circle::getNumOfCircles() << endl;
	
	delete[] p;
	cout << "생존하고 있는 원의 개수 = " << Circle::getNumOfCircles() << endl;

	Circle a;
	cout << "생존하고 있는 원의 개수 = " << Circle::getNumOfCircles() << endl;

	Circle b;
	cout << "생존하고 있는 원의 개수 = " << Circle::getNumOfCircles() << endl;
}