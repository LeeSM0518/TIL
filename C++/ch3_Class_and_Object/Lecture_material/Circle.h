#ifndef Circle_H
#define Circle_H

class Circle {
private:
	int radius;
public:
	Circle();
	Circle(int r);
	~Circle(); // ¼Ò¸êÀÚ
	double getArea();
	void getRadius();
};

#endif