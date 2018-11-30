// stdafx.cpp : 표준 포함 파일만 들어 있는 소스 파일입니다.
// cppProject_console.pch는 미리 컴파일된 헤더가 됩니다.
// stdafx.obj에는 미리 컴파일된 형식 정보가 포함됩니다.

#include "stdafx.h"

int game_situation = 1;	// 게임 진행 상황
int SCORE = 0;		// 점수
int life = 5;		// 생명
int SPEED = 50;		// 게임속도
int LEVEL = 500;	// 레벨
string LIFE_5 = "♥ ♥ ♥ ♥ ♥";	//  생명
string LIFE_4 = "♥ ♥ ♥ ♥";
string LIFE_3 = "♥ ♥ ♥";
string LIFE_2 = "♥ ♥";
string LIFE_1 = "♥";

void game_menu() {	// 게임 메뉴 선택 함수
	int menu = 0;
	int ch = 0;
	int k = 0;

	game_play();
}

void set_letter_color(int color) {	// 글씨 
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), color);
}

int print_game_menu() {
	char ch;		// 입력된 키 저장
	int num;		// 메뉴 번호 저장
	int out = 0;	// 메뉴에서 나가기 위함
	setcursortype(NOCURSOR);
	gotoxy(POSITION_GAME_MENU_X, POSITION_GAME_MENU_Y);
	cout <<("1. 게임하기");
	gotoxy(POSITION_GAME_MENU_X, POSITION_GAME_MENU_Y + 2);
	cout <<("2. 나가기");
	num = 1;
	gotoxy(POSITION_GAME_MENU_X - 1, POSITION_GAME_MENU_Y);
	cout <<("▶");
	while (1)
	{
		ch = _getch();
		if (ch == 13) break;	// 엔터 입력시

		if (_kbhit()) {		// 키입력 확인
			ch = _getch();
			if (ch == 72) {	// 방향키 위에 입력
				gotoxy(POSITION_GAME_MENU_X - 1, POSITION_GAME_MENU_Y);
				cout <<("▶");
				gotoxy(POSITION_GAME_MENU_X - 1, POSITION_GAME_MENU_Y + 2);
				cout <<(" ");
				num = 1;
			}
			else if (ch == 80) {	// 방향키 아래 입력
				gotoxy(POSITION_GAME_MENU_X - 1, POSITION_GAME_MENU_Y);
				cout <<(" ");
				gotoxy(POSITION_GAME_MENU_X - 1, POSITION_GAME_MENU_Y + 2);
				cout <<("▶");
				num = 2;
			}

		}
	}
	system("cls");
	return num;
}

void game_play()
{
	int random_word = 0;
	int random_position = 0;
	int word_len = 0;
	int i, j = 1, k = 0;
	int ch;
	int cnt = 0;
	string input_word = "";
	string get;
	setcursortype(NOCURSOR);
	game_play_table();
	game_score_table();
	
	while (1)
	{
		random_word = rand() % WORD_NUM;
		word_len = word_num[random_word].size();
		random_position = rand() % (X_MAX - word_len - 1) + 1;
		gotoxy(random_position, 1);
		cout << word_num[random_word];
		gotoxy(WORD_INPUT_POSITION_X, WORD_INPUT_POSITION_Y - 1);
		while (1)
		{
			if (cnt % SPEED == 0) {
				word_len = word_num[random_word].size();
				if (j == Y_MAX - 3) {
					for (i = 0; i <= word_len; i++) {
						gotoxy(random_position + i, Y_MAX - 3);
						cout << ' ';
					}
					game_score(FALSE);
					game_score(DROP);
					break;
				}
				for (i = 0; i < word_len; i++) {
					gotoxy(random_position + i, j);
					cout << (" ");
				}
				j++;
				gotoxy(random_position, j);
				cout << word_num[random_word];
			}

			if (_kbhit()) {
				ch = _getch();
				if (ch >= 33 && ch <= 126) {
					input_word += char(ch);
					gotoxy(WORD_INPUT_POSITION_X, WORD_INPUT_POSITION_Y - 1);
					cout << input_word;
				}
				else if (ch == 8)
				{
					if (input_word.size() != 0) {
						input_word.pop_back();
						for (i = 0; i <= input_word.size(); i++) {
							gotoxy(WORD_INPUT_POSITION_X + i, WORD_INPUT_POSITION_Y - 1);
							cout << ' ';
						}
						gotoxy(WORD_INPUT_POSITION_X, WORD_INPUT_POSITION_Y - 1);
						cout << input_word;
					}
				}
				else if (ch == 13) {
					if (word_num[random_word] == input_word) {
						for (i = 0; i < word_len; i++) {
							gotoxy(random_position + i, j);
							cout << (" ");
						}
						for (i = 0; i <= input_word.size(); i++) {
							gotoxy(WORD_INPUT_POSITION_X + i, WORD_INPUT_POSITION_Y - 1);
							cout << ' ';
						}
						game_score(TRUE);
						input_word.clear();
						j = 1;
						break;
					}
					else {
						for (i = 0; i <= input_word.size(); i++) {
							gotoxy(WORD_INPUT_POSITION_X + i, WORD_INPUT_POSITION_Y - 1);
							cout << ' ';
						}
						input_word.clear();
					}
				}
			}


			if (SCORE == LEVEL) {
				SPEED -= 1;
				LEVEL += 100;
			}

			if (life == 0) {
				game_situation = 0;
				break;
			}
			if (SPEED == 0) {
				game_situation = 0;
				game_clear();
				break;
			}
			cnt++;
			Sleep(10);
		}
		cnt = 0;
		j = 1;
		if (game_situation == 0) {
			break;
		}
	}

}

void game_play_table() {
	int i;
	for (i = 1; i < X_MAX - 1; i++) {
		gotoxy(i, 0);
		cout << "─";
	}
	gotoxy(i + 1, 0);
	cout << "┐";
	for (i = 1; i < Y_MAX; i++) {
		gotoxy(X_MAX, i);
		cout << "│";
	}
	gotoxy(X_MAX, i);
	cout << "┘";
	for (i = X_MAX - 1; i > 0; i--) {
		gotoxy(i, Y_MAX);
		cout << "─";
	}
	gotoxy(0, Y_MAX);
	cout << "└";
	for (i = Y_MAX - 1; i >= 0; i--) {
		gotoxy(0, i);
		cout << "│";
	}
	gotoxy(0, 0);
	cout << "┌";
	for (i = 1 ; i <= X_MAX -1 ; i++) {
		gotoxy(i, Y_MAX - 2);
		cout << "━";
	}
	set_letter_color(GREEN);
	gotoxy(WORD_INPUT_POSITION_X- 14, WORD_INPUT_POSITION_Y - 1);
	cout << "Input word : ";
	set_letter_color(WHITE);
}

void game_play_intro() {
	int i;
	for (i = 1; i < X_MAX - 1; i++) {
		gotoxy(i, 0);
		cout << "─";
		Sleep(GAME_TABLE_INTRO_SPEED);
	}
	gotoxy(i + 1, 0);
	cout << "┐";
	for (i = 1; i < Y_MAX; i++) {
		gotoxy(X_MAX, i);
		cout << "│";
		Sleep(GAME_TABLE_INTRO_SPEED);
	}
	gotoxy(X_MAX, i);
	cout << "┘";
	for (i = X_MAX - 1; i > 0; i--) {
		gotoxy(i, Y_MAX);
		cout << "─";
		Sleep(GAME_TABLE_INTRO_SPEED);
	}
	gotoxy(0, Y_MAX);
	cout << "└";
	for (i = Y_MAX - 1; i >= 0; i--) {
		gotoxy(0, i);
		cout << "│";
		Sleep(GAME_TABLE_INTRO_SPEED);
	}
	gotoxy(0, 0);
	cout << "┌";
}

void CREATER_intro()
{
	int i;
	setcursortype(NOCURSOR);
	//string print0 = "C Language Manager";

	string print1 = "H a n b a t   U n i v e r s i t y";
	string print2 = "C + +  P r o g r a m m i n g";
	string print3 = "P r o j e c t";
	string print4 = "L e e  S a n g  M i n ★";
	string print5 = "K a n g  S u k  h y u n ★";
	string print6 = "K i m  K y u n g  A h ★";

	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 11);

	for (i = 0; i < print1.size(); i++) {
		gotoxy(CREATER_X + i, CREATER_Y);
		cout << print1[i];
		Sleep(15);
	}
	for (i = 0; i < print2.size(); i++) {
		gotoxy(CREATER_X + i, CREATER_Y + 2);
		cout << print2[i];
		Sleep(15);
	}
	for (i = 0; i < print3.size(); i++) {
		gotoxy(CREATER_X + i, CREATER_Y + 4);
		cout << print3[i];
		Sleep(15);
	}
	for (i = 0; i < print4.size(); i++) {
		gotoxy(CREATER_X + i + 3, CREATER_Y + 8);
		cout << print4[i];
		Sleep(15);
	}
	for (i = 0; i < print5.size(); i++) {
		gotoxy(CREATER_X + i + 3, CREATER_Y + 10);
		cout << print5[i];
		Sleep(15);
	}
	for (i = 0; i < print6.size(); i++) {
		gotoxy(CREATER_X + i + 3, CREATER_Y + 12);
		cout << print6[i];
		Sleep(15);
	}

	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);

}

void game_score_table() {
	int i;
	for (i = X_MAX + 1; i < SCORE_TABLE_X - 1; i++) {
		gotoxy(i, 0);
		cout << "─";
	}
	gotoxy(i + 1, 0);
	cout << "┐";
	for (i = 1; i < SCORE_TABLE_Y; i++) {
		gotoxy(SCORE_TABLE_X, i);
		cout << "│";
	}
	gotoxy(SCORE_TABLE_X, i);
	cout << "┘";
	for (i = SCORE_TABLE_X - 1; i > X_MAX; i--) {
		gotoxy(i, SCORE_TABLE_Y);
		cout << "─";
	}
	gotoxy(0, SCORE_TABLE_Y);
	cout << "└";
	for (i = SCORE_TABLE_Y - 1; i >= 0; i--) {
		gotoxy(0, i);
		cout << "│";
	}
	gotoxy(X_MAX + 1, 0);
	cout << "─";
	gotoxy(SCORE_PRINT_X, SCORE_PRINT_Y);
	cout << "닉네임  ";
	gotoxy(SCORE_PRINT_X, SCORE_PRINT_Y + 5);
	cout << "생명  ";
	set_letter_color(RED);
	gotoxy(SCORE_PRINT_X , SCORE_PRINT_Y + 6);
	cout << LIFE_5;
	set_letter_color(WHITE);
	gotoxy(SCORE_PRINT_X, SCORE_PRINT_Y + 10);
	cout << "점수  ";
	set_letter_color(BLUE);
	gotoxy(SCORE_PRINT_X, SCORE_PRINT_Y + 11);
	cout << SCORE;
	set_letter_color(WHITE);
}

void game_score(int state) {
	int i;
	if (state == FALSE) {
		SCORE -= 100;
		for (i = 0; i <= 15; i++) {
			gotoxy(SCORE_PRINT_X + i, SCORE_PRINT_Y + 11);
			cout << ' ';
		}
		set_letter_color(BLUE);
		gotoxy(SCORE_PRINT_X, SCORE_PRINT_Y + 11);
		cout << SCORE;
		set_letter_color(WHITE);
	}
	else if(state == TRUE) {
		SCORE += 100;
		for (i = 0; i <= 15; i++) {
			gotoxy(SCORE_PRINT_X + i, SCORE_PRINT_Y + 11);
			cout << ' ';
		}
		set_letter_color(BLUE);
		gotoxy(SCORE_PRINT_X, SCORE_PRINT_Y + 11);
		cout << SCORE;
		set_letter_color(WHITE);
	}
	else if (state == DROP) {
		if (life == 5) {
			life -= 1;
			for (i = 0; i <= 15; i++) {
				gotoxy(SCORE_PRINT_X + i, SCORE_PRINT_Y + 6);
				cout << ' ';
			}
			set_letter_color(RED);
			gotoxy(SCORE_PRINT_X, SCORE_PRINT_Y + 6);
			cout << LIFE_4;
			set_letter_color(WHITE);
		}
		else if (life == 4) {
			life -= 1;
			for (i = 0; i <= 15; i++) {
				gotoxy(SCORE_PRINT_X + i, SCORE_PRINT_Y + 6);
				cout << ' ';
			}
			set_letter_color(RED);
			gotoxy(SCORE_PRINT_X, SCORE_PRINT_Y + 6);
			cout << LIFE_3;
			set_letter_color(WHITE);
		}
		else if (life == 3) {
			life -= 1;
			for (i = 0; i <= 15; i++) {
				gotoxy(SCORE_PRINT_X + i, SCORE_PRINT_Y + 6);
				cout << ' ';
			}
			set_letter_color(RED);
			gotoxy(SCORE_PRINT_X, SCORE_PRINT_Y + 6);
			cout << LIFE_2;
			set_letter_color(WHITE);
		}
		else if (life == 2) {
			life -= 1;
			for (i = 0; i <= 15; i++) {
				gotoxy(SCORE_PRINT_X + i, SCORE_PRINT_Y + 6);
				cout << ' ';
			}
			set_letter_color(RED);
			gotoxy(SCORE_PRINT_X, SCORE_PRINT_Y + 6);
			cout << LIFE_1;
			set_letter_color(WHITE);
		}
		else if (life == 1) {
			game_over();
			game_situation = 0;
		}
	}
}

void game_over()
{
	int i;
	system("cls");
	string over = "G  A  M  E    O  V  E  R";
	string over2 = "아 무 키 나  입 력 해 주 세 요";

	for (i = 0; i < over.size(); i++) {
		gotoxy(GAME_OVER_X - 10 + i, GAME_OVER_Y);
		cout << over[i];
		Sleep(50);
	}
	gotoxy(GAME_OVER_X - 13, GAME_OVER_Y + 3);
	cout << over2;
	_getch();
}

void game_clear()
{
	int i;
	system("cls");
	string over = "G  A  M  E    C  L  E  A  R";
	for (i = 0; i < over.size(); i++) {
		gotoxy(POSITION_GAME_MENU_X - 10 + i, POSITION_GAME_MENU_Y);
		cout << over[i];
		Sleep(50);
	}
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

// TODO: 필요한 추가 헤더는
// 이 파일이 아닌 STDAFX.H에서 참조합니다.
