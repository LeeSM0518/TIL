#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct STUDENT{
  char name[30];
  int number;
}STUDENT;

STUDENT print(STUDENT any)
{
  printf("%s\n", any.name);
  printf("%d\n", any.number);
}
// 구조체로 함수를 만드는 이유는 return 값을 구조체 값으로 반환하기 위해서!!

int main(void)
{
  STUDENT s1;
  strcpy(s1.name, "LEE");
  s1.number = 2017;
  print(s1);
}