#include <Windows.h>
#include <time.h>
#include <stdlib.h>
#include <string.h>
#include <string>
#include <stdio.h>
#include <conio.h>
#include <iostream>
using namespace std;

typedef enum { NOCURSOR, SOLIDCURSOR, NORMALCURSOR } CURSOR_TYPE;
void setcursortype(CURSOR_TYPE c);

#define X_MAX 70	// x축 최대
#define Y_MAX 25	// y축 최대
#define WORD_NUM 5	// 단어개수
#define POSITION_GAME_MENU_X 50	// 메뉴 x축
#define POSITION_GAME_MENU_Y 10		// 메뉴 y축
#define WORD_INPUT_POSITION_X 38	// 단어 입력 x축
#define WORD_INPUT_POSITION_Y 25	// 단어 입력 y축
#define SPEED 10					// 게임 속도

string word_num[WORD_NUM] = {		// 게임에 나올 단어들
	"int", "char" ,"double", "float", "long"
};

void gotoxy(int x, int y);
void game_menu();			// 게임 메뉴 출력
int print_game_menu();		// 게임 시작 메뉴
void game_play();			// 게임 작동
void game_play_intro();		// 게임 화면 출력
void game_play_table();		// 게임 테이블
void game_score();	// 점수 테이블


int main(void)
{
	int num;
	while (1) {
		num = print_game_menu();
		if (num == 1) {

			game_play();
		}
		else {

		}
	}
}

void game_menu() {
	int menu = 0;
	int ch = 0;
	int k = 0;

	game_play();
}

int print_game_menu() {
	char ch;	// 입력된 키 저장
	int num;	// 메뉴 번호 저장
	int out = 0;	// 메뉴에서 나가기 위함
	setcursortype(NOCURSOR);
	gotoxy(POSITION_GAME_MENU_X, POSITION_GAME_MENU_Y);
	printf("1. 게임하기");
	gotoxy(POSITION_GAME_MENU_X, POSITION_GAME_MENU_Y + 2);
	printf("2. 나가기");
	num = 1;
	gotoxy(POSITION_GAME_MENU_X - 1, POSITION_GAME_MENU_Y);
	printf("▶");
	while (1)
	{
		ch = _getch();
		if (ch == 13) break;	// 엔터 입력시

		if (_kbhit()) {		// 키입력 확인
			ch = _getch();
			if (ch == 72) {	// 방향키 위에 입력
				gotoxy(POSITION_GAME_MENU_X - 1, POSITION_GAME_MENU_Y);
				printf("▶");
				gotoxy(POSITION_GAME_MENU_X - 1, POSITION_GAME_MENU_Y + 2);
				printf(" ");
				num = 1;
			}
			else if (ch == 80) {	// 방향키 아래 입력
				gotoxy(POSITION_GAME_MENU_X - 1, POSITION_GAME_MENU_Y);
				printf(" ");
				gotoxy(POSITION_GAME_MENU_X - 1, POSITION_GAME_MENU_Y + 2);
				printf("▶");
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
	game_play_intro();

	while (1)
	{
		random_word = rand() % WORD_NUM;
		word_len = word_num[random_word].size();
		random_position = rand() % (X_MAX - word_len - 1) + 1;
		gotoxy(random_position, 1);
		cout << word_num[random_word];
		game_play_table();
		gotoxy(WORD_INPUT_POSITION_X, WORD_INPUT_POSITION_Y - 1);
		while (1)
		{
			if (cnt % SPEED == 0) {
				word_len = word_num[random_word].size();
				if (j == Y_MAX - 2) {
					system("cls");
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
				if (ch>= 33 && ch<= 126) {
					input_word += char(ch);
					gotoxy(WORD_INPUT_POSITION_X,WORD_INPUT_POSITION_Y - 1);
					cout << input_word;
				}
				else if (ch == 8)
				{
					if (input_word.size() != 0) {
						input_word.pop_back();
						for (i = 0; i <= input_word.size(); i++) {
							gotoxy(WORD_INPUT_POSITION_X + i, WORD_INPUT_POSITION_Y -1 );
							cout << ' ';
						}
						gotoxy(WORD_INPUT_POSITION_X, WORD_INPUT_POSITION_Y - 1);
						cout << input_word;
					}
				}
				else if (ch == 13){
					if (word_num[random_word] == input_word) {
						for (i = 0; i < word_len; i++){
							gotoxy(random_position + i, j);
							cout << (" ");
						}
						for (i = 0; i <= input_word.size(); i++) {
							gotoxy(WORD_INPUT_POSITION_X + i, WORD_INPUT_POSITION_Y - 1);
							cout << ' ';
						}
						input_word.clear();
						j = 1;
						break;
					}
				}
			}

			if (j == Y_MAX - 1) {
				for (i = 0; i < word_len; i++) {
					gotoxy(random_position + i, j);
					cout << (" ");
				}
				break;
			}

			cnt++;
			Sleep(10);
		}
		cnt = 0;
		j = 1;
	}
}

void game_play_table() {
	int i;
	for (i = 0; i < X_MAX - 1; i++) {
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
}

void game_play_intro() {
	int i;
	for (i = 0; i < X_MAX - 1; i++) {
		gotoxy(i, 0);
		cout << "─";
		Sleep(10);
	}
	gotoxy(i + 1, 0);
	cout << "┐";
	for (i = 1; i < Y_MAX; i++) {
		gotoxy(X_MAX, i);
		cout << "│";
		Sleep(10);
	}
	gotoxy(X_MAX, i);
	cout << "┘";
	for (i = X_MAX - 1; i > 0; i--) {
		gotoxy(i, Y_MAX);
		cout << "─";
		Sleep(10);
	}
	gotoxy(0, Y_MAX);
	cout << "└";
	for (i = Y_MAX - 1; i >= 0; i--) {
		gotoxy(0, i);
		cout << "│";
		Sleep(10);
	}
	gotoxy(0, 0);
	cout << "┌";
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