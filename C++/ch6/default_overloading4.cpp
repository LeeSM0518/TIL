#include <iostream>
using namespace std;

class myVector{
	int *p;
	int size;
public:
	myVector(int n = 100) {
		this->p = new int[n];
		this->size = n;
	}
	~myVector() {
		delete[] p;
	}
	void show(myVector *p)
	{
		cout << p->size << endl;
	}
};

int main() {
	myVector *v1, *v2;
	v1 = new myVector();		// 디폴트로 정수 100개의 배열 동적 할당
	v2 = new myVector(1024);	// 정수 1024개의 배열 동적 할당

	delete v1;
	delete v2;
}