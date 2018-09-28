#include <iostream>
using namespace std;

#include "Circle.h"
#include "Circle.h" // #ifndef Circle_H , #define Circle_H, #endif
// 를 이용해여 오류가 생기지 않는다.

void Circle::getRadius() {
	cout << "반지름은 " << radius << "입니다." << endl;
}

Circle::Circle() {
	radius = 1;
	cout << "반지름 " << radius << " 원 생성" << endl;
}

Circle::Circle(int r) {
	radius = r;
	cout << "반지름 " << radius << " 원 생성" << endl;
}

Circle::~Circle() {
	cout << "반지름 " << radius << " 원 소멸" << endl;
}

inline double Circle::getArea() {
	return 3.14*radius*radius;
}

int main() {
	Circle donut;
	Circle pizza(30);
	pizza.getRadius();
}