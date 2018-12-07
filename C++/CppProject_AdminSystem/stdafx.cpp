// stdafx.cpp : 표준 포함 파일만 들어 있는 소스 파일입니다.
// CppProjectIng.pch는 미리 컴파일된 헤더가 됩니다.
// stdafx.obj에는 미리 컴파일된 형식 정보가 포함됩니다.

#include "stdafx.h"

void set_letter_color(int color) {	// 글씨 
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), color);
}

void gotoxy(int x, int y)
{
	COORD CursorPosition = { x, y };
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), CursorPosition);
}

void setcursortype(CURSOR_TYPE c)
{
	CONSOLE_CURSOR_INFO CurInfo;
	switch (c)
	{
	case NOCURSOR:
		CurInfo.dwSize = 1;
		CurInfo.bVisible = FALSE;
		break;
	case SOLIDCURSOR:
		CurInfo.dwSize = 100;
		CurInfo.bVisible = TRUE;
		break;
	case NORMALCURSOR:
		CurInfo.dwSize = 20;
		CurInfo.bVisible = TRUE;
		break;
	}
	SetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE), &CurInfo);
}

// 세입자 정보 저장
void PersonInformation::personSave()
{
	int roomNum;

	gotoxy(INPUT_USER_X, INPUT_USER_Y);
	cout << "세입자의 호수 : ";
	cin >> roomNum;
	cin.ignore();
	roomNum = roomNum % 100 - 1;
	currentPersonState[roomNum] = 1;

	gotoxy(INPUT_USER_X, INPUT_USER_Y + 3);
	cout << "세입자의 이름 : ";
	getline(cin, tetant[roomNum].name);
	gotoxy(INPUT_USER_X, INPUT_USER_Y + 6);
	cout << "세입자의 전화번호 : ";
	getline(cin, tetant[roomNum].tell);
	gotoxy(INPUT_USER_X, INPUT_USER_Y + 9);
	cout << "세입자의 계약기간 : ";
	getline(cin, tetant[roomNum].period);
	gotoxy(INPUT_USER_X, INPUT_USER_Y + 12);
	cout << "저 장 되 었 습 니 다.";
	tetant[roomNum].warningNum = 0;
	countPerson++;
	_getch();

	tetant[roomNum].room[roomNum].monthly = 30;
	tetant[roomNum].room[roomNum].gas = 0;
	tetant[roomNum].room[roomNum].water = 0;
	tetant[roomNum].room[roomNum].elertric = 0;
	tetant[roomNum].room[roomNum].payDay = 10;

	system("cls");
}

// 세입자 정보 제거
void PersonInformation::personRemove()
{
	int situation = 0;
	int count = 0;
	string nameInput;
	int i;
	
	cin.ignore();
	gotoxy(INPUT_USER_X, INPUT_USER_Y);
	cout << "삭제하실 세입자의 이름을 입력해주세요 : ";
	getline(cin, nameInput);

	for (i = 0; i < 6; i++) {
		if (tetant[i].name == nameInput) {
			break;
		}
		else {
			count++;
		}
	}


	tetant[i].name = "X";
	tetant[i].tell = "X";
	tetant[i].period = "X";
	tetant[i].warningNum = -1;

	gotoxy(INPUT_USER_X, INPUT_USER_Y + 3);
	cout << "삭제되었습니다.";
	_getch();
}

// 자취방 월세 출력
void PersonInformation::roomMonthlyPrint()
{
	this->monthlyIncrease();

	int i, j, k;
	for (i = 0; i < 3; i++)
	{
		cout << "  ";
		for (j = 0; j<17; j++)
		{
			cout << "■";
		}
		cout << "    ";
	}
	for (k = 0; k < 12; k++)
	{
		for (i = 0; i < 3; i++)
		{
			cout << "■";
			for (j = 0; j < 34; j++)
			{
				cout << " ";
			}
			cout << "■" << "  ";
		}
	}
	for (i = 0; i < 3; i++)
	{
		cout << "  ";
		for (j = 0; j < 17; j++)
		{
			cout << "■";
		}
		cout << "    ";
	}
	for (k = 0; k < 12; k++)
	{
		for (i = 0; i < 3; i++)
		{
			cout << "■";
			for (j = 0; j < 34; j++)
			{
				cout << " ";
			}
			cout << "■" << "  ";
		}
	}
	for (i = 0; i < 3; i++)
	{
		cout << "  ";
		for (j = 0; j < 17; j++)
		{
			cout << "■";
		}
		cout << "    ";
	}

	if (this->tetant[0].name != "X") {
		gotoxy(8, 4);//gotoxy좌표 찍히는 위치 예시
		cout << "월세: " << this->tetant[0].room[0].monthly;
		gotoxy(8, 5);
		cout << "가스비: " << this->tetant[0].room[0].gas;
		gotoxy(8, 6);
		cout << "수도세: " << this->tetant[0].room[0].water;
		gotoxy(8, 7);
		cout << "전기세: " << this->tetant[0].room[0].elertric;
		gotoxy(8, 8);
		cout << "납부 남은 기간: "<< this->tetant[0].room[0].payDay;
		gotoxy(18, 2);
		cout << "101호";
	}

	if (this->tetant[1].name != "X") {
		gotoxy(48, 4);
		cout << "월세: " << this->tetant[1].room[1].monthly;
		gotoxy(48, 5);
		cout << "가스비: " << this->tetant[1].room[1].gas;
		gotoxy(48, 6);
		cout << "수도세: " << this->tetant[1].room[1].water;
		gotoxy(48, 7);
		cout << "전기세: " << this->tetant[1].room[1].elertric;
		gotoxy(48, 8);
		cout << "납부 남은 기간: " << this->tetant[1].room[1].payDay;
		gotoxy(58, 2);
		cout << "102호";
	}

	if (this->tetant[2].name != "X") {
		gotoxy(88, 4);
		cout << "월세: " << this->tetant[2].room[2].monthly;
		gotoxy(88, 5);
		cout << "가스비: " << this->tetant[2].room[2].gas;
		gotoxy(88, 6);
		cout << "수도세: " << this->tetant[2].room[2].water;
		gotoxy(88, 7);
		cout << "전기세: " << this->tetant[2].room[2].elertric;
		gotoxy(88, 8);
		cout << "납부 남은 기간: " << this->tetant[2].room[2].payDay;
		gotoxy(98, 2);
		cout << "103호";
	}

	if (this->tetant[3].name != "X") {
		gotoxy(8, 17);
		cout << "월세: " << this->tetant[3].room[3].monthly;
		gotoxy(8, 18);
		cout << "가스비: " << this->tetant[3].room[3].gas;
		gotoxy(8, 19);
		cout << "수도세: " << this->tetant[3].room[3].water;
		gotoxy(8, 20);
		cout << "전기세: " << this->tetant[3].room[3].elertric;
		gotoxy(8, 21);
		cout << "납부 남은 기간: " << this->tetant[3].room[3].payDay;
		gotoxy(18, 15);
		cout << "104호";
	}

	if (this->tetant[4].name != "X") {
		gotoxy(48, 17);
		cout << "월세: " << this->tetant[4].room[4].monthly;
		gotoxy(48, 18);
		cout << "가스비: " << this->tetant[4].room[4].gas;
		gotoxy(48, 19);
		cout << "수도세: " << this->tetant[4].room[4].water;
		gotoxy(48, 20);
		cout << "전기세: " << this->tetant[4].room[4].elertric;
		gotoxy(48, 21);
		cout << "납부 남은 기간: " << this->tetant[4].room[4].payDay;
		gotoxy(58, 15);
		cout << "105호";
	}

	if (this->tetant[5].name != "X") {
		gotoxy(88, 17);
		cout << "월세: " << this->tetant[5].room[5].monthly;
		gotoxy(88, 18);
		cout << "가스비: " << this->tetant[5].room[5].gas;
		gotoxy(88, 19);
		cout << "수도세: " << this->tetant[5].room[5].water;
		gotoxy(88, 20);
		cout << "전기세: " << this->tetant[5].room[5].elertric;
		gotoxy(88, 21);
		cout << "납부 남은 기간: " << this->tetant[5].room[5].payDay;
		gotoxy(98, 15);
		cout << "106호";
	}

	_getch();
}

// 세입자 정보 출력
void PersonInformation::roomPrint(PersonInformation person)
{
	int i, j, k;
	for (i = 0; i < 3; i++)
	{
		cout << "  ";
		for (j = 0; j<17; j++)
		{
			cout << "■";
		}
		cout << "    ";
	}
	for (k = 0; k < 12; k++)
	{
		for (i = 0; i < 3; i++)
		{
			cout << "■";
			for (j = 0; j < 34; j++)
			{
				cout << " ";
			}
			cout << "■" << "  ";
		}
	}
	for (i = 0; i < 3; i++)
	{
		cout << "  ";
		for (j = 0; j < 17; j++)
		{
			cout << "■";
		}
		cout << "    ";
	}
	for (k = 0; k < 12; k++)
	{
		for (i = 0; i < 3; i++)
		{
			cout << "■";
			for (j = 0; j < 34; j++)
			{
				cout << " ";
			}
			cout << "■" << "  ";
		}
	}
	for (i = 0; i < 3; i++)
	{
		cout << "  ";
		for (j = 0; j < 17; j++)
		{
			cout << "■";
		}
		cout << "    ";
	}

	if (person.tetant[0].name != "X") {
		gotoxy(8, 4);         //gotoxy좌표 찍히는 위치 예시
		cout << ("세입자명: ") << person.tetant[0].name;
		gotoxy(8, 6);
		cout << ("전화번호: ") << person.tetant[0].tell;
		gotoxy(8, 8);
		cout << ("계약기간: ") << person.tetant[0].period;
		gotoxy(18, 2);
		cout << ("101호");
	}

	if (person.tetant[1].name != "X") {
		gotoxy(48, 4);
		cout << ("세입자명: ") << person.tetant[1].name;
		gotoxy(48, 6);
		cout << ("전화번호: ") << person.tetant[1].tell;
		gotoxy(48, 8);
		cout << ("계약기간: ") << person.tetant[1].period;
		gotoxy(58, 2);
		cout << ("102호");
	}

	if (person.tetant[2].name != "X") {
		gotoxy(88, 4);
		cout << ("세입자명: ") << person.tetant[2].name;
		gotoxy(88, 6);
		cout << ("전화번호: ") << person.tetant[2].tell;
		gotoxy(88, 8);
		cout << ("계약기간: ") << person.tetant[2].period;
		gotoxy(98, 2);
		cout << ("103호");
	}
	
	if (person.tetant[3].name != "X") {
		gotoxy(8, 17);
		cout << ("세입자명: ") << person.tetant[3].name;
		gotoxy(8, 19);
		cout << ("전화번호: ") << person.tetant[3].tell;
		gotoxy(8, 21);
		cout << ("계약기간: ") << person.tetant[3].period;
		gotoxy(18, 15);
		cout << ("104호");
	}

	if (person.tetant[4].name != "X") {
		gotoxy(48, 17);
		cout << ("세입자명: ") << person.tetant[4].name;
		gotoxy(48, 19);
		cout << ("전화번호: ") << person.tetant[4].tell;
		gotoxy(48, 21);
		cout << ("계약기간: ") << person.tetant[4].period;
		gotoxy(58, 15);
		cout << ("105호");
	}

	if (person.tetant[5].name != "X") {
		gotoxy(88, 17);
		cout << ("세입자명: ") << person.tetant[5].name;
		gotoxy(88, 19);
		cout << ("전화번호: ") << person.tetant[5].name;
		gotoxy(88, 21);
		cout << ("계약기간: ") << person.tetant[5].name;
		gotoxy(98, 15);
		cout << ("106호");
	}

	_getch();
}

// 메뉴판
int AdminSystem::menu_print()
{
		int choice, i;

		for (i = 0; i < 45; i++)
		{
			cout << "■";
		}
		cout << endl;
		for (i = 1; i<25; i++)
			cout << "■" << endl;
		for (i = 1; i < 25; i++)
		{
			gotoxy(88, i);
			cout << "■" << endl;
		}
		for (i = 1; i < 46; i++)
		{
			gotoxy(i, 46);
			cout << "■";
		}
		cout << endl;

		//C
		gotoxy(15, 4);
		cout << "□□□□□";
		gotoxy(12, 5);
		cout << " □□□";
		gotoxy(11, 6);
		cout << " □□";
		gotoxy(10, 7);
		cout << " □□";
		gotoxy(11, 8);
		cout << " □□";
		gotoxy(12, 9);
		cout << " □□□";
		gotoxy(15, 10);
		cout << "□□□□□";

		//+
		for (i = 3; i < 11; i++)
		{
			gotoxy(40, i);
			cout << "□□";
		}
		gotoxy(34, 6);
		cout << "□□□□□□□□";
		gotoxy(34, 7);
		cout << "□□□□□□□□";

		//+
		for (i = 3; i < 11; i++)
		{
			gotoxy(66, i);
			cout << "□□";
		}
		gotoxy(60, 6);
		cout << "□□□□□□□□";
		gotoxy(60, 7);
		cout << "□□□□□□□□";

		//S
		gotoxy(8, 15);
		cout << " □□□□";
		gotoxy(6, 16);
		cout << " □";
		gotoxy(6, 17);
		cout << " □";
		gotoxy(8, 18);
		cout << " □□□□";
		gotoxy(15, 19);
		cout << " □";
		gotoxy(15, 20);
		cout << " □";
		gotoxy(7, 21);
		cout << " □□□□□";

		//T
		gotoxy(20, 16);
		cout << "□□□□□";
		gotoxy(20, 16);
		cout << "□□□□□";
		for (i = 14; i < 21; i++)
		{
			gotoxy(24, i);
			cout << "□";
		}
		gotoxy(24, 21);
		cout << " □□□";

		//U
		for (i = 16; i < 21; i++)
		{
			gotoxy(32, i);
			cout << " □";
		}
		gotoxy(33, 21);
		cout << " □□□□";
		for (i = 16; i < 21; i++)
		{
			gotoxy(40, i);
			cout << " □";
		}

		//d
		for (i = 13; i < 22; i++)
		{
			gotoxy(54, i);
			cout << "□";
		}
		gotoxy(48, 17);
		cout << "□□□";
		gotoxy(46, 18);
		cout << " □";
		gotoxy(45, 19);
		cout << " □";
		gotoxy(46, 20);
		cout << " □";
		gotoxy(48, 21);
		cout << "□□□";

		//i
		for (i = 14; i < 16; i++)
		{
			gotoxy(61, i);
			cout << "□□";
		}
		for (i = 17; i < 23; i++)
		{
			gotoxy(61, i);
			cout << "□□";
		}

		//o
		gotoxy(72, 16);
		cout << "□□□□";
		gotoxy(70, 17);
		cout << "□□";
		gotoxy(68, 18);
		cout << "□□";
		gotoxy(68, 19);
		cout << "□□";
		gotoxy(70, 20);
		cout << "□□";
		gotoxy(72, 21);
		cout << "□□□□";
		gotoxy(78, 17);
		cout << "□□";
		gotoxy(80, 18);
		cout << "□□";
		gotoxy(80, 19);
		cout << "□□";
		gotoxy(78, 20);
		cout << "□□";

		//메뉴
		gotoxy(96, 10);
		cout << "1. 세입자 정보 확인";
		gotoxy(96, 13);
		cout << "2. 세입자 추가";
		gotoxy(96, 16);
		cout << "3. 세입자 삭제";
		gotoxy(96, 19);
		cout << "4. 공과금 현황";
		gotoxy(96, 22);
		cout << "5. 종료";
		gotoxy(96, 26);
		cout << "메뉴 선택 : ";
		cin >> choice;

		return choice;
}

// 종료
void AdminSystem::_exit_() {
	exit(1);
}

// 전체 진행 과정
void AdminSystem::process()
{
	int num;
	PersonInformation person;

	while (1)
	{
		num = menu_print();
		if (num == 1) {
			system("cls");
			person.roomPrint(person);
		}
		else if (num == 2) {
			system("cls");
			person.personSave();
		}
		else if (num == 3) {
			system("cls");
			person.personRemove();
		}
		else if (num == 4) {
			system("cls");
			person.roomMonthlyPrint();
		}
		else if (num == 5) {
			_exit_();
		}

		system("cls");
	}
}

// 월세 등 증가
void PersonInformation::monthlyIncrease(){
	int i, j;
	int random;

	for (i = 0; i < 6; i++) {
		if (this->tetant[i].name != "X") {
				random = rand() % 100 + 1;
				this->tetant[i].room[i].gas += random;
				random = rand() % 100 + 1;
				this->tetant[i].room[i].water += random;
				random = rand() % 100 + 1;
				this->tetant[i].room[i].elertric += random;
				tetant[i].room[i].payDay -= 1;
		}
	}
	
}