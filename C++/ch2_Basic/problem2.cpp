#include <iostream>
using namespace std;

int main() {
	int i, j = 1;
	for (i = 1; i <= 9; i++) {
		for(j=1;j<=9;j++){
			cout << j << "x" << i << "=" << j*i << "\t";
			if (j == 9)
				cout << "\n";
		}
	}
}