#include <iostream>
using namespace std;

class Circle {
	int radius;
public:
	Circle();
	~Circle();
	void setRadius(int radius);
	double getArea();
};

Circle::Circle()
{
	this->radius = 1;
	cout << "생성 : " << this->radius << endl;
}
Circle::~Circle() {
	cout << "소멸 : " << this->radius << endl;
}
void Circle::setRadius(int radius) {
	this->radius = radius;
	// this->radius 는 객체의 radius
	// int radius 는 매개변수의 radius
}
double Circle::getArea() {
	return 3.14 * radius * radius;
}

int main() {
	cout << "원의 개수?";
	int n, radius;

	cin >> n;
	Circle *pArray = new Circle[n];	
	// n개 만큼의 Circle 객체가 배열로 만들어져서 Array 포인터에 들어가게 된다

	for (int i = 0; i < n; i++) {
		cout << i << "번째 원의 반지름??";
		cin >> radius;
		pArray[i].setRadius(radius);
	}

	int count = 0;
	Circle* p = pArray;
	for (int i = 0; i < n; i++) {
		cout << p->getArea() << "번째 원의 반지름"<< endl;
		if (p->getArea() > 100) {
			count++;
		}
		p++;
	}
	cout << count << endl;

	delete [] pArray;
}