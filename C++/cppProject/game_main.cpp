// cppProject_console.cpp: 콘솔 응용 프로그램의 진입점을 정의합니다.
//

#include "stdafx.h"

int main(void)
{
	GAME game;
	typing_practice type;
	SetConsoleTitle(TEXT("C++ PROJECT MADE BY KSH,KKA,LSM [ C++ manager ]"));	// 콘솔창 이름 변경하기
	system("mode con: lines=30 cols=120");	//콘솔창 크기 설정
	setcursortype(NOCURSOR);
	type.tp_table_out();
	type.tp_table_in();
	type.tp_progress();
	_getch();
	system("pause");
}