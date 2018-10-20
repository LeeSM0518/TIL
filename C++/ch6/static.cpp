#include <iostream>
using namespace std;

class Person {
public:
	double money;
	void addMoney(int money) {
		this->money += money;
	}

	static int sharedMoney;		// 공금
	static void addShared(int n) {
		sharedMoney += n;
	}
};

// static 변수 생성, 전역 공간에 생성
int Person::sharedMoney = 10;	// 10으로 초기화

int main() {
	Person han;
	han.money = 100;	// han의 개인 돈 = 100
	han.sharedMoney = 200; // static 멤버 접근, 공금 = 200

	Person lee;
	lee.money = 150;	// lee의 개인 돈 = 150
	lee.addMoney(200);	// lee의 개인 돈 = 350
	lee.addShared(200);	// static 멤버 접근, 공급 = 400

	cout << han.money << ' ' << lee.money << endl;
	cout << han.sharedMoney << ' ' << lee.sharedMoney << endl;
}