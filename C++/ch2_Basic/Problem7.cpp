#include <iostream>
#include <string>
using namespace std;

int main() {
	char check[100];
	
	while (true) {
		cout << "종료하고싶으면 yes를 입력하세요>> ";
		cin.getline(check, 100, '\n');

		if (strcmp(check, "yes") == 0)
		{
			cout << "종료합니다..." << endl;
			break;
		}
		
	}
}