// 실습 1 ) 참조 매개 변수를 가진 함수 만들기
#include <iostream>
using namespace std;

class Circle {
	int radius;
public:
	Circle() {
		radius = 1;
	}
	Circle(int radius) {
		this->radius = radius;
	}
	void setRadius(int radius) {
		this->radius = radius;
	}
	double getArea() {
		return 3.14 * radius * radius;
	}
};

void readRadius(Circle &c) {	// 매개 변수로 주소를 받는다.
	int radius;
	cout << "정수 값으로 반지름을 입력하세요 >> ";
	cin >> radius;
	c.setRadius(radius);	// 반환을 하지 않아도 매개변수의 값이 변환된다.
}

int main() {
	Circle donut;
	readRadius(donut);
	cout << "donut의 면적 = " << donut.getArea() << endl;
}