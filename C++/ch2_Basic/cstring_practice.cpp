#include <iostream>
#include <cstring>
using namespace std;

int main() {
	char password[11];

	cout << "���α׷��� �����Ϸ��� ��ȣ�� �Է��ϼ���.\n";

	while (true) {
		cout << "��ȣ : ";
		cin >> password;
		
		if (strcmp(password, "C++") == 0) {
			cout << "���α׷��� ���� �����մϴ�\n";
			break;
		}
		else
		{
			cout << "��ȣ�� Ʋ���ϴ�.\n";
		}
	}
}