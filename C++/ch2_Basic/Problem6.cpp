#include <iostream>
#include <string>
using namespace std;

int main() {
	char password[100];
	char check[100];

	cout << "새 암호를 입력하세요>> ";
	cin.getline(password, 100, '\n');

	cout << "새 암호를 다시 한 번 입력하세요>> ";
	cin.getline(check, 100, '\n');

	if (strcmp(password, check) == 0)
	{
		cout << "같습니다." << endl;
	}
	else
	{
		cout << "다릅니다." << endl;
	}
}