#ifndef Rectangle_H
#define Rectangle_H

class Rectangle {
public:
	int width, height;

	Rectangle();
	Rectangle(int w, int h);
	Rectangle(int length);
	bool isSquare();
};

#endif