#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <math.h>

typedef struct Point {
	int x;
	int y;
}Point;

double get_distance(Point p1, Point p2);

int main(void)
{
	Point a, b;
	double result;

	printf("x 와 y 입력 : ");
	scanf("%d %d", &a.x, &a.y);

	printf("x 와 y 입력 : ");
	scanf("%d %d", &b.x, &b.y);

	result = get_distance(a, b);

	printf("Point 1 과 Point 2 의 거리는 %f 입니다.\n", result);
}

double get_distance(Point p1, Point p2)
{
	int x, y, z;
	double result;

	x = p2.x - p1.x;
	y = p2.y - p1.y;

	z = x*x + y*y;

	result = sqrt(z);

	return result;
}