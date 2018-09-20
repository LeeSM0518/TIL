#include <iostream>
using namespace std;

int main() {
	int i,j=1;
	for (i = 1; i <= 100; i++) {
		cout << i << "\t";
		if (i / 10 == j) {
			cout << "\n";
			j++;
		}
	}
}