#include "Tower.h"
#include <iostream>
using namespace std;

Tower::Tower() {
	height = 1;
}

Tower::Tower(int length) {
	height = length;
}

Tower::~Tower() {
	return;
}

int Tower::getHeight() {
	return height;
}