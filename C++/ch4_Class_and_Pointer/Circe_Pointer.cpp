#include <iostream>
using namespace std;

class Circle {
	int radius;
public:
	Circle() {
		radius = 1;
	}
	Circle(int r) {
		radius = r;
	}
	double getArea();
};

double Circle::getArea() {
	return 3.14 * radius * radius;
}

int main() {
	Circle donut;
	Circle pizza(30);

	cout << donut.getArea() << endl;

	Circle *p;
	p = &donut;		// 객체 포인터 p에 donut 객체 주소를 저장
	cout << p->getArea() << endl;	// 포인터 p인 donut 메서드 실행	
	cout << (*p).getArea() << endl;	// 포인터 p의 donut 메서드 실행	

	p = &pizza;		// 객체 포인터 p에 pizza 객체 주소를 저장
	cout << p->getArea() << endl;	// 포인터 p의 pizza 메서드 실행
	cout << (*p).getArea() << endl;	// 포인터 p의 pizza 메서드 실행
}