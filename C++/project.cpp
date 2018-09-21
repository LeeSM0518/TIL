#include <iostream>
/* 1번
P = &a 는 a의 주소를 P의 값으로 넣어주는 것이고
P = *a 는 포인터인 a의 값을 참조하여 P에 값으로 넣어주는 것이다.

*/

//2번

typedef struct student {
	int number;
	char name[20];
	int grade;
};

using namespace std;
int main()
{
	student s1[10];
	int i;


	for (i = 0; i < 10; i++) {
		cout << i << "번 학생" << endl;
		cout << "학번 : " ;
		cin >> s1[i].number;
		cout << "이름 : " ;
		cin >> s1[i].name;
		cout << "점수 : " ;
		cin >> s1[i].grade;
	}

	int sum = 0;
	int average = 0;

	for (i = 0; i < 10; i++) {
		sum += s1[i].grade;
	}

	average = sum / 10;

	cout << "\n학생들의 전체 총점 : " << sum << endl;
	cout << "평균 점수 : " << average << endl;
}