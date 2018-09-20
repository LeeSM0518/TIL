#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <string>
using namespace std;

int main() {
	char name[100];
	char *ptr;
	char ptr2[10][10];
	int i = 1;
	int j = 1;
	int max = 0;
	int max_num;

	cout << "5 명의 이름을 ';' 으로 구분하여 입력하세요\n>> ";
	cin.getline(name, 100, '\n');
	
	ptr = strtok(name, ";");
	strcpy(ptr2[0], ptr);

	while (ptr != NULL)
	{
		cout << i << " : " << ptr << endl;
		strcpy(ptr2[j], ptr);
		ptr = strtok(NULL, ";");
		i++;
		j++;
	}

	int len[10];

	for (i = 0; i < 6; i++)
	{
		len[i] = strlen(ptr2[i]);
		if (max < len[i])
			max_num = i;
	}
	cout << "가장 긴 이름은 " << ptr2[max_num] << endl;
}