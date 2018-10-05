#include <iostream>
using namespace std;

class Circle {
	int radius;
public:
	Circle();
	Circle(int r);
	~Circle();
	double getArea() { return 3.14 * radius * radius; }
	void setRadius(int r) { radius = r; }
};

Circle::Circle() {
	radius = 1;
	cout << "积己磊 角青 radius = " << radius << endl;
}

Circle::Circle(int r) {
	radius = r;
	cout << "积己磊 角青 radius = " << radius << endl;
}

Circle::~Circle() {
	radius = 1;
	cout << "家戈磊 角青 radius = " << radius << endl;
}

int main() {
	Circle *p, *q;
	p = new Circle;		// 按眉狼 悼利 积己
	q = new Circle(30);	// 按眉狼 悼利 积己

	cout << p->getArea() << endl << q->getArea() << endl;
	delete p;
	delete q;
}