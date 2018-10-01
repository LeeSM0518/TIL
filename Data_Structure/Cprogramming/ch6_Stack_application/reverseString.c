#include <stdlib.h>
#include <string.h>
#include "linkedstack.h"
#include <stdio.h>    
#include "linkedstack.c"

char* reverseString(char *pSource)
{
  char* pReturn = NULL;
  int i = 0, size = 0;
  LinkedStack *pStack = NULL;
  LinkedStackNode *pNode = NULL;

  pStack = createLinkedStack();   // 스택 생성
  if(pStack != NULL){
    while(pSource[size] != 0){    // 문자열에서 NULL 문자를 만날때까지.
      pushLS(pStack, pSource[size]);
      size++;
    }     // 스택에 푸시 ( 문자열을 맨 아래부터 하나씩 채운다. )

    pReturn = (char*)malloc(sizeof(char) * (size + 1));
    memset(pReturn, 0, sizeof(char) * (size + 1));
    // 반환되는 문자열을 생성하고 0으로 초기화
    // size 는 위의 while문을 나온 상태의 size 값을 1 증가시킨다. ( size + 1 값은 pSource의 길이가 된다. )
    // : 결과 문자열의 맨 마지막에 NULL 문자를 추가하기 위해서 이다.

    while( i < size){
      pNode = popLS(pStack);
      if(pNode != NULL){
        pReturn[i] = pNode->data;
        free(pNode);  // 반드시 pop을 한 노드는 메모리를 해제해야 한다.
      }
      i++;
    }
    // 스택에서 문자 하나씩 pop 하여 역순으로 문자열을 생성한다.

    deleteLinkedStack(pStack);  // 스택 삭제
  }
  return pReturn;
}

int main(int argc, const char * argv[]){
  char szSource[] = "ABCD";
  char *pszReverse = reverseString(szSource);
  if( pszReverse != NULL){
    printf("[%s] => [%s] \n", szSource, pszReverse);

    free(pszReverse);
  }

  return 0;
}