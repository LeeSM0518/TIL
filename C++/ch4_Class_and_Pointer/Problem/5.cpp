#include <iostream>
#include <stdlib.h>
using namespace std;

class Circle {
	int radius;		// 원의 반지름 값
public:
	void setRadius(int r);	// 반지름을 설정한다.
	double getArea();		// 면적을 구한다.
};

void Circle::setRadius(int r) {
	radius = r;		// 인수로 받은 r을 radius에 저장한다.
}

double Circle::getArea() {
	return 3.14 * radius * radius;	// 객체의 radius의 값으로 면적을 구한다.
}

int main() {
	Circle circle[3];	// 객체 3개를 배열로 선언

	int r;			// 반지름
	double area;	// 면적
	int count = 0;	// 100보다 큰 원을 세주는 변수

	for (int i = 0; i < 3; i++) {
		cout << "원 " << i + 1 << " 의 반지름 >>";
		cin >> r;

		circle[i].setRadius(r);			// 객체의 반지름을 설정해준다.
		area = circle[i].getArea();		// 면적을 구하여 area에 저장한다.

		if (area > 100)		// 만약 area 가 100 보다 크면 count를 증가시킨다.
			count++;
	}

	cout << "면적이 100보다 큰 원은 " << count << " 개 입니다. " << endl;

	system("pause");
}