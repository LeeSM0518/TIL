// stdafx.h : 자주 사용하지만 자주 변경되지는 않는
// 표준 시스템 포함 파일 또는 프로젝트 관련 포함 파일이
// 들어 있는 포함 파일입니다.
//

#pragma once

#include "targetver.h"
#include <stdio.h>
#include <tchar.h>
#include <Windows.h>
#include <time.h>
#include <stdlib.h>
#include <string.h>
#include <string>
#include <stdio.h>
#include <conio.h>
#include <iostream>
#include <fstream>

using namespace std;
typedef enum { NOCURSOR, SOLIDCURSOR, NORMALCURSOR } CURSOR_TYPE;

// 아스키 코드 기호 상수
#define UP 72	//↑아스키값
#define DOWN 80 //↓아스키값
#define ESC 27
#define ENTER 13

// 글자색 기호 상수
#define BLACK 0						// 검정색
#define BLUE 1 						// 파란색
#define GREEN 2 					// 초록색
#define CYAN 3 						// 하늘색
#define RED 4 						// 빨간색
#define BROWN 6						// 갈색
#define LIGHTBLUE 9					// 파란색 글자
#define LIGHTGREEN 10				// 밝은 초록색
#define LIGHTCYAN 11				// 하늘색 글자
#define LIGHTRED 12					// 밝은 빨간색
#define YELLOW 14					// 노란색
#define WHITE 15					// 흰색 글자


// GAME 기호상수
#define X_MAX 80	// x축 최대
#define Y_MAX 28	// y축 최대
#define WORD_NUM 10	// 단어개수
#define POSITION_GAME_MENU_X 50		// 메뉴 x축
#define POSITION_GAME_MENU_Y 10		// 메뉴 y축
#define WORD_INPUT_POSITION_X 38	// 단어 입력 x축
#define WORD_INPUT_POSITION_Y 28	// 단어 입력 y축
#define CREATER_X 40				// 제작자 x축
#define CREATER_Y 5					// 제작자 y축
#define SCORE_TABLE_X 110			// 점수판 최대 x축
#define SCORE_TABLE_Y 28			// 점수판 최대 y축
#define SCORE_PRINT_X 87			// 점수판 닉네임 등 x축
#define SCORE_PRINT_Y 7				// 점수판 닉네임 등 y축
#define GAME_TABLE_INTRO_SPEED 5	// 게임 인트로 화면 출력 속도
#define TRUE 1						// 단어 일치
#define FALSE 0						// 단어 불일치
#define DROP -1						// 생명이 줄어듬
#define GAME_OVER_X 50				// 게임 오버시 나오는 문자열 x축
#define GAME_OVER_Y 10				// 게임 오버시 나오는 문자열 y축

// Typing Practice 줄인말 : TP
// TP 기호상수
#define TP_TABLE_X 118				// 타자 입력판 외부 X축 최대
#define TP_TABLE_Y 28				// 타자 입력판 외부 y축 최대
#define TP_PRACTICE_START_X 2		// 파일 출력 시작 X
#define TP_PRACTICE_START_Y 2		// 파일 출력 시작 Y

void set_letter_color(int color);	// 글씨 색상 변경
void gotoxy(int x, int y);			// x, y 위치로 커서를 이동
void setcursortype(CURSOR_TYPE c);	// 커서 타입 변경

									// CppManager main Class
int END = 0;
class CppManager {
private:
public:
	int menu;							// 메뉴 번호
	int SCORE = 0;						// 점수
	int game_situation = 1;				// 게임 진행 상황
	void game_over();					// 게임 오버
	void game_clear();					// 게임 클리어
	void menu_intro_and_progress();		// 메뉴 인트로 및 진행
	clock_t start = clock();			// 프로그램 실행 시 시작시간 측정
	float usage_time(float start);	    // 소요 시간 구하는 함수
};

// 초기 인트로 화면 및 메뉴 화면 Class
void setFontColor(int color);
void CopyRight(void);
void outline_out(void);
void PLUS_FIRST_S(void);
void PLUS_SECOND_S(void);
void check_key(void);
void PLUS_FIRST_S(void);
void PLUS_SECOND_S(void);

int GetKey();
class VIEW_01 : public CppManager {
public:
	VIEW_01()
	{
		TITLE_DARK();
		TITLE_LIGHT();
		PLUS();
		PLUS_TURN_UP();
	}
	void TITLE_DARK();
	void TITLE_LIGHT(void);
	void PLUS(void);
	void PLUS_FIRST(void);
	void PLUS_SECOND(void);
	void PLUS_TURN_UP();
};
class VIEW_02 : public CppManager {
	int MenuNum;
	int dy = 2;
	int SNum = 1;
public:
	VIEW_02()
	{
		int max = 4;
		Text_MENU();	//메뉴화면 그리기, 외곽 선과 틀
		DrawMenuText(SNum);//메뉴화면 안의 텍스트 초기값.
		PLUS_TURN_UP_S();	//반짝이기
		GetCommand(max, SNum);
	}
	void Text_MENU();
	void PLUS_TURN_UP_S();
	void DrawMenuText(int selectedNum);
	void GetCommand(int max, int &selectedNum);
};


// GAME Class 와 함수들
class Game : public CppManager {
private:
	string word_num[WORD_NUM] = {		// 게임에 나올 단어들
		"int", "char", "double", "float", "long", "iostream" , "fstream", "virtual",
		"private", "public"
	};
	int life = 5;		// 생명
	int SPEED = 50;		// 게임속도
	int LEVEL = 500;	// 레벨 점수
	int _LEVEL_ = 1;	// 레벨
	string LIFE_5 = "♥ ♥ ♥ ♥ ♥";	//  생명
	string LIFE_4 = "♥ ♥ ♥ ♥";
	string LIFE_3 = "♥ ♥ ♥";
	string LIFE_2 = "♥ ♥";
	string LIFE_1 = "♥";

public:
	void game_menu();					// 게임 메뉴 출력
	int print_game_menu();				// 게임 시작 메뉴
	void game_play_intro();				// 게임 화면 출력
	void game_play_table();				// 게임 테이블
	void game_score_table();			// 게임 점수 테이블
	void game_score(int state);			// 게임 점수 출력
	void game_play();					// 게임 작동
	void CREATER_intro();				// 제작자 테이블
};

// TP Class와 함수들
class TypingPractice : public CppManager {
private:
	char ch;					// 문자 하나를 받을 변수
	int line = 0;				// 현재화면 라인 개수
	int line_max = 0;			// 최대 라인 개수
	int sum_size = 0;			// 한 라인 문자열 사이즈의 합
	int x = 2, y = 3;			// x위치와 y좌표 이동 시 사용
	int WPM = 0;				// 타자수
	int progress_situation = 1;	// 프로그램 진행
	double cnt = 0.0;			// 한 라인의 전체 문자중 맞은 문자 수의 합
	string file;				// 파일 이름
	string load_txt[100];		// 파일 라인별로 저장
	string input_word = "";		// 유저가 입력한 타자
public:
	void tp_menu();												// 메뉴 실행 과정 함수
	void tp_input_filename();									// 파일을 사용자에게 입력받음
	void tp_table_out();										// 타자 입력판 외부 테두리
	void tp_table_in();											// 타자 입력판 내부 테두리
	void tp_progress();											// 타자 연습 진행
	void tp_clear();											// 타자 연습 클리어
	void tp_file_upload(TypingPractice *TYP);					// 연습할 타자 파일 불러오기
	void print_text(int j);										// 연습할 텍스트 출력하기
	void print_space();											// 빈공간 텍스트 출력하기
	double get_acc(double cnt, int size, int line);				// 정확도 구하는 함수
	int get_WPM(float start, double cnt, int size, int line);	// WPM. 분당 타자수 구하는 함수
};

// Quiz class
#include <iostream>
#include <Windows.h>
#include <string>
#include <ctime>
#include <conio.h>
using namespace std;

void gotoxy(int x, int y);
void setFontColor(int color);

class POP {
public:
	int situation = 1;
	POP() {
		outline_pop();
		pop_text();
	}
	void outline_pop();
	void pop_text();
	int pop_choose();
};
class Quiz_cnt : public CppManager{
	int cnt;
public:
	Quiz_cnt() { cnt = 0; }
	void cnt_plus() { cnt++; };
	int get_cnt() { return cnt; }
};
class Quiz_Base : public CppManager{

public:
	int cnt;
	Quiz_Base() {
		system("cls");
		outline_out();
		Quiz_outline();
	}
	void outline_out();
	void Quiz_outline();
	int Quiz_ans_num();
	string Quiz_ans_string();
	void Quiz_O();
	void Quiz_X();
	void outline_pop();
	void Quiz_result_text();
};

class Q07_01 : public virtual Quiz_cnt, public Quiz_Base {
	int ans;
public:
	int user;
	Q07_01() {
		ans = 3;
		setFontColor(15);
		gotoxy(6, 4);
		cout << "1. 다음 중 연산자 중복이 불가능한 것은?";
		gotoxy(8, 6);
		cout << "(1) new (2) ++ (3) ** (4) []";
		user = Quiz_ans_num();
		if (user == ans) {
			Quiz_O();
			cnt_plus();
			Sleep(3000);		//3초 뒤 넘어감
			system("cls");
		}
		else
		{
			Quiz_X();
			gotoxy(23, 6);
			setFontColor(12);
			cout << "(3) **";
			Sleep(5000);		//5초 뒤 넘어감
			system("cls");
		}
	}
};
class Q07_02 : public virtual Quiz_cnt, public Quiz_Base {
	int ans;
public:
	int user;
	Q07_02() {
		ans = 3;
		setFontColor(15);
		gotoxy(6, 4);
		cout << "2. Circle 클래스의 객체 donut, pizza가 있을때, Circle 클래스의 멤버 함수로 연산자 함수를 작성할 수 없는 경우는?";
		gotoxy(8, 6);
		cout << "(1) 3<donut (2) !donut (3) pizza + 3.14 (4) pizza != donut";
		user = Quiz_ans_num();
		if (user == ans) {
			Quiz_O();
			cnt_plus();
			Sleep(3000);		//3초 뒤 넘어감
			system("cls");
		}
		else
		{
			Quiz_X();
			gotoxy(31, 6);
			setFontColor(12);
			cout << "(3) pizza + 3.14";
			Sleep(5000);		//5초 뒤 넘어감
			system("cls");
		}
	}
};
class Q07_03 : public virtual Quiz_cnt, public Quiz_Base {
	int ans;
public:
	int user;
	Q07_03()
	{
		ans = 2;
		setFontColor(15);
		gotoxy(6, 4);
		cout << "3. 프렌드로 선언할 수 있는 종류가 아닌 것은?";
		gotoxy(8, 6);
		cout << "(1) 외부함수 (2) 내부함수 (3) 다른 클래스의 멤버함수 (4) 다른 클래스 전체";
		user = Quiz_ans_num();
		if (user == ans) {
			Quiz_O();
			cnt_plus();
			Sleep(3000);		//3초 뒤 넘어감
		}
		else
		{
			Quiz_X();
			gotoxy(21, 6);
			setFontColor(12);
			cout << "(2) 내부함수";
			Sleep(5000);		//5초 뒤 넘어감
		}
		outline_pop();
		Quiz_result_text();
	}
};
class CHAP07 :public Q07_01, public Q07_02, public Q07_03
{
public:
	CHAP07() { }
	~CHAP07() {
		gotoxy(76, 13);
		setFontColor(11);
		cout << Quiz_cnt::get_cnt();
		if (Quiz_cnt::get_cnt() == 3)
		{
			gotoxy(50, 15);
			setFontColor(14);
			cout << "훌륭합니다! 만점입니다!";

		}
	}

};

class Q08_01 : public virtual Quiz_cnt, public Quiz_Base {
	int ans;
public:
	int user;
	Q08_01() {
		ans = 3;
		setFontColor(15);
		gotoxy(6, 4);
		cout << "1. C++언어의 상속으로 인한 장점이 아닌것은?";
		gotoxy(8, 6);
		cout << "(1) 코드의 중복 작성 제거";
		gotoxy(8, 7);
		cout << "(2) 클래스의 간결화";
		gotoxy(8, 8);
		cout << "(3) C언어와의 호환성";
		gotoxy(8, 9);
		cout << "(4) 클래스 관리 용이";
		user = Quiz_ans_num();
		if (user == ans) {
			Quiz_O();
			cnt_plus();
			Sleep(3000);		//3초 뒤 넘어감
			system("cls");
		}
		else
		{
			Quiz_X();
			gotoxy(8, 8);
			setFontColor(12);
			cout << "(3) C언어와의 호환성";
			Sleep(5000);		//5초 뒤 넘어감
			system("cls");
		}
	}
};
class Q08_02 : public virtual Quiz_cnt, public Quiz_Base {
	int ans;
public:
	int user;
	Q08_02() {
		ans = 2;
		setFontColor(15);
		gotoxy(6, 4);
		cout << "2. 기본 클래스 TV와 파생 클래스 color TV가 있을때, 다음 중 업 캐스팅을 찾아라.";
		gotoxy(30, 6);
		cout << "TV *p,tv;";
		gotoxy(30, 7);
		cout << "ColorTV *q,ctv";
		gotoxy(8, 9);
		cout << "(1) p=&tv; (2) p=&ctv; (3) q=(ColorTV*)&tv; (4) q=&ctv;";
		user = Quiz_ans_num();
		if (user == ans) {
			Quiz_O();
			cnt_plus();
			Sleep(3000);		//3초 뒤 넘어감
			system("cls");
		}
		else
		{
			Quiz_X();
			gotoxy(19, 9);
			setFontColor(12);
			cout << "(2) p=&ctv;";
			Sleep(5000);		//5초 뒤 넘어감
			system("cls");
		}
	}
};
class Q08_03 : public virtual Quiz_cnt, public Quiz_Base {
	int ans;
public:
	int user;
	Q08_03()
	{
		ans = 4;
		setFontColor(15);
		gotoxy(6, 4);
		cout << "3. 다음 코드에 대해 컴파일 오류가 발생하는 것은?";
		gotoxy(27, 6);
		cout << "class A{";
		gotoxy(27, 7);
		cout << " public: int w;};";
		gotoxy(27, 8);
		cout << "class B: public A{";
		gotoxy(27, 9);
		cout << " public: int x;};";
		gotoxy(27, 10);
		cout << "class C: private A{";
		gotoxy(27, 11);
		cout << " public: int y;};";
		gotoxy(27, 12);
		cout << "class D: protected B{";
		gotoxy(27, 11);
		cout << " public: int z;};";

		gotoxy(8, 15);
		cout << "(1) A a; a.w=10; (2) B b; b.w=10; (3) C c; c.y=10; (4) D d; d.w=10;";
		user = Quiz_ans_num();
		if (user == ans) {
			Quiz_O();
			cnt_plus();
			Sleep(3000);		//3초 뒤 넘어감
		}
		else
		{
			Quiz_X();
			gotoxy(59, 15);
			setFontColor(12);
			cout << "(4) D d; d.w=10;";
			Sleep(5000);		//5초 뒤 넘어감
		}
		outline_pop();
		Quiz_result_text();
	}
};
class CHAP08 :public Q08_01, public Q08_02, public Q08_03
{
public:
	CHAP08() { }
	~CHAP08() {
		gotoxy(76, 13);
		setFontColor(11);
		cout << Quiz_cnt::get_cnt();
		if (Quiz_cnt::get_cnt() == 3)
		{
			gotoxy(50, 15);
			setFontColor(14);
			cout << "훌륭합니다! 만점입니다!";

		}
	}
};

class Q09_01 : public virtual Quiz_cnt, public Quiz_Base {
	int ans;
public:
	int user;
	Q09_01() {
		ans = 1;
		setFontColor(15);
		gotoxy(6, 4);
		cout << "1. 호출하는 함수의 결정을 실행 시간에 하도록 컴파일러에게 지시하는 키워드는?";
		gotoxy(8, 6);
		cout << "(1) virtual (2) static (3) public (4) extern";
		user = Quiz_ans_num();
		if (user == ans) {
			Quiz_O();
			cnt_plus();
			Sleep(3000);		//3초 뒤 넘어감
			system("cls");
		}
		else
		{
			Quiz_X();
			gotoxy(8, 6);
			setFontColor(12);
			cout << "(1) virtual";
			Sleep(5000);		//5초 뒤 넘어감
			system("cls");
		}
	}
};
class Q09_02 : public virtual Quiz_cnt, public Quiz_Base {
	int ans;
public:
	int user;
	Q09_02() {
		ans = 2;
		setFontColor(15);
		gotoxy(6, 4);
		cout << "2. 기본 클래스의 가상 함수와 동일한 타입의 함수를 파생 클래스에서 재정의하는 것을 무엇이라고 하는가?";
		gotoxy(8, 6);
		cout << "(1) overloading (2) overriding (3) virtual (4) dynamic binding";
		user = Quiz_ans_num();
		if (user == ans) {
			Quiz_O();
			cnt_plus();
			Sleep(3000);		//3초 뒤 넘어감
			system("cls");
		}
		else
		{
			Quiz_X();
			gotoxy(24, 6);
			setFontColor(12);
			cout << "(2) overriding";
			Sleep(5000);		//5초 뒤 넘어감
			system("cls");
		}
	}
};
class Q09_03 : public virtual Quiz_cnt, public Quiz_Base {
	int ans;
public:
	int user;
	Q09_03()
	{
		ans = 2;
		setFontColor(15);
		gotoxy(6, 4);
		cout << "3. 만일 shpae이 추상 클래스라고 하면 다음 중 옳은 코드는?";
		gotoxy(8, 6);
		cout << "(1) Shape shape;";
		gotoxy(8, 7);
		cout << "(2) Shape *p;";
		gotoxy(8, 8);
		cout << "(3) Shape *p= new Shpae();";
		gotoxy(8, 9);
		cout << "(4) class Circle : public Shpae{};";
		gotoxy(8, 10);
		cout << "    Circle circle;";
		user = Quiz_ans_num();
		if (user == ans) {
			Quiz_O();
			cnt_plus();
			Sleep(3000);		//3초 뒤 넘어감
		}
		else
		{
			Quiz_X();
			gotoxy(8, 7);
			setFontColor(12);
			cout << "(2) Shape *p;";
			Sleep(5000);		//5초 뒤 넘어감
		}
		outline_pop();
		Quiz_result_text();
	}
};
class CHAP09 :public Q09_01, public Q09_02, public Q09_03
{
public:
	CHAP09() { }
	~CHAP09() {
		gotoxy(76, 13);
		setFontColor(11);
		cout << Quiz_cnt::get_cnt();
		if (Quiz_cnt::get_cnt() == 3)
		{
			gotoxy(50, 15);
			setFontColor(14);
			cout << "훌륭합니다! 만점입니다!";

		}
	}

};

class Q10_01 : public virtual Quiz_cnt, public Quiz_Base {
	int ans;
public:
	int user;
	Q10_01() {
		ans = 2;
		setFontColor(15);
		gotoxy(6, 4);
		cout << "1. 구체화의 과정은 누구에 의해 이루어지는가?";
		gotoxy(8, 6);
		cout << "(1) 개발자 (2) 컴파일러 (3) 로더 (4) 운영체제";
		user = Quiz_ans_num();
		if (user == ans) {
			Quiz_O();
			cnt_plus();
			Sleep(3000);		//3초 뒤 넘어감
			system("cls");
		}
		else
		{
			Quiz_X();
			gotoxy(19, 6);
			setFontColor(12);
			cout << "(2) 컴파일러";
			Sleep(5000);		//5초 뒤 넘어감
			system("cls");
		}
	}
};
class Q10_02 : public virtual Quiz_cnt, public Quiz_Base {
	int ans;
public:
	int user;
	Q10_02() {
		ans = 1;
		setFontColor(15);
		gotoxy(6, 4);
		cout << "2. C++의 표준 STL 라이브러리가 작성된 이름 공간은 무엇인가?";
		gotoxy(8, 6);
		cout << "(1) std (2) template (3) stl (4) algorithm";
		user = Quiz_ans_num();
		if (user == ans) {
			Quiz_O();
			cnt_plus();
			Sleep(3000);		//3초 뒤 넘어감
			system("cls");
		}
		else
		{
			Quiz_X();
			gotoxy(8, 6);
			setFontColor(12);
			cout << "(1) std";
			Sleep(5000);		//5초 뒤 넘어감
			system("cls");
		}
	}
};
class Q10_03 : public virtual Quiz_cnt, public Quiz_Base {
	int ans;
public:
	int user;
	Q10_03()
	{
		ans = 3;
		setFontColor(15);
		gotoxy(6, 4);
		cout << "3. 다음 제네릭 함수 sum()으로부터 구체화가 실패하는 경우는?";
		gotoxy(36, 6);
		cout << "template<class TYPE>";
		gotoxy(36, 7);
		cout << "TYPE sum(TYPE a,TYPE b){ . . . }";
		gotoxy(6, 10);
		cout << "(1) int n = sum(2,10); (2) sum('a','b'); (3) sum(3,5,6); (4) sum(0,1);";
		user = Quiz_ans_num();
		if (user == ans) {
			Quiz_O();
			cnt_plus();
			Sleep(3000);		//3초 뒤 넘어감
		}
		else
		{
			Quiz_X();
			gotoxy(47, 10);
			setFontColor(12);
			cout << "(3) sum(3,5,6);";
			Sleep(5000);		//5초 뒤 넘어감
		}
		outline_pop();
		Quiz_result_text();
	}
};
class CHAP10 :public Q10_01, public Q10_02, public Q10_03
{
public:
	CHAP10() { }
	~CHAP10() {
		gotoxy(76, 13);
		setFontColor(11);
		cout << Quiz_cnt::get_cnt();
		if (Quiz_cnt::get_cnt() == 3)
		{
			gotoxy(50, 15);
			setFontColor(14);
			cout << "훌륭합니다! 만점입니다!";

		}
	}

};

class Q12_01 : public virtual Quiz_cnt, public Quiz_Base {
	int ans;
public:
	int user;
	Q12_01() {
		ans = 1;
		setFontColor(15);
		gotoxy(6, 4);
		cout << "1. 다음에서 텍스트 파일이 아닌 것은?";
		gotoxy(8, 6);
		cout << "(1) test.hwp (2) test.cpp (3) test.htm (4) test.c";
		user = Quiz_ans_num();
		if (user == ans) {
			Quiz_O();
			cnt_plus();
			Sleep(3000);		//3초 뒤 넘어감
			system("cls");
		}
		else
		{
			Quiz_X();
			gotoxy(8, 6);
			setFontColor(12);
			cout << "(1) test.hwp";
			Sleep(5000);		//5초 뒤 넘어감
			system("cls");
		}
	}
};
class Q12_02 : public virtual Quiz_cnt, public Quiz_Base {
	int ans;
public:
	int user;
	Q12_02() {
		ans = 3;
		setFontColor(15);
		gotoxy(6, 4);
		cout << "2. EOF는 C++ 입출력 라이브러리에 선언된 상수이다., EOF의 타입과 값은 실제 얼마인가?";
		gotoxy(8, 6);
		cout << "(1) int 타입으로 0 (2) char 타입으로 0 (3) int 타입으로 -1 (4) char 타입으로 -1";
		user = Quiz_ans_num();
		if (user == ans) {
			Quiz_O();
			cnt_plus();
			Sleep(3000);		//3초 뒤 넘어감
			system("cls");
		}
		else
		{
			Quiz_X();
			gotoxy(47, 6);
			setFontColor(12);
			cout << "(3) int 타입으로 -1 ";
			Sleep(5000);		//5초 뒤 넘어감
			system("cls");
		}
	}
};
class Q12_03 : public virtual Quiz_cnt, public Quiz_Base {
	string ans;
public:
	string user;
	Q12_03()
	{
		ans = "fstream";
		setFontColor(15);
		gotoxy(6, 4);
		cout << "3. 다음은 텍스트 파일을 읽기위한 프로그램이다. 다음 빈칸에 들어갈 코드를 입력하라.";
		gotoxy(8, 6);
		cout << "#include <iostream>";
		gotoxy(8, 7);
		cout << "#include <_______>";
		gotoxy(8, 8);
		cout << "using namespace std;";
		gotoxy(8, 10);
		cout << "int main(){";
		gotoxy(8, 11);
		cout << "ifstream fin";
		gotoxy(8, 12);
		cout << "fin.open("")";
		gotoxy(8, 13);
		cout << " . . . . .";

		user = Quiz_ans_string();
		if (user == ans) {
			Quiz_O();
			cnt_plus();
			Sleep(3000);		//3초 뒤 넘어감
		}
		else
		{
			Quiz_X();
			gotoxy(18, 7);
			setFontColor(12);
			cout << "fstream";
			Sleep(5000);		//5초 뒤 넘어감
		}
		outline_pop();
		Quiz_result_text();
	}
};
class CHAP12 :public Q12_01, public Q12_02, public Q12_03
{
public:
	CHAP12() { }
	~CHAP12() {
		gotoxy(76, 13);
		setFontColor(11);
		cout << Quiz_cnt::get_cnt();
		if (Quiz_cnt::get_cnt() == 3)
		{
			gotoxy(50, 15);
			setFontColor(14);
			cout << "훌륭합니다! 만점입니다!";

		}
	}

};

#endif // !GAME_H



// TODO: 프로그램에 필요한 추가 헤더는 여기에서 참조합니다.
