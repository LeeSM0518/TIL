#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "linkedstack.h"
#include "LinkedStack.c"

int checkBracketMatching(char *pSource)
{
  int ret = 0, i = 0;
  char symbol = '\0' , checkSymbol = '\0';
  LinkedStack *pStack = NULL;
  LinkedStackNode *pNode = NULL;

  pStack = createLinkedStack();   // 스택 생성
  if( pStack != NULL ){
    while( 0 != pSource[i] && 0 == ret ){   
      // 현재 문자가 NULL 문자가 아니고, ret 값이 0이 아니면 수식을 계속 읽어 간다.
      // ret 의 값이 0이 아니라는 것은 수식에 오류가 있다는 뜻이다. ( 오류 처리를 하기 위함. )
      symbol = pSource[i];
      switch(symbol){   // 만약 여는 괄호 (, {, [ 이면 스택에 푸시한다.
        case '(':
        case '[':
        case '{':
          pushLS(pStack, symbol);
          break;
        case ')':   // 만약 닫는 괄호 ), }, ] 이면 스택에 팝한다.
        case ']':
        case '}':
          pNode = popLS(pStack);

          if(pNode == NULL){  
            // 만약 팝된 문자가 없으면 오류가 발생한 경우. ( 닫는 괄호가 여는 괄호보다 많은 경우. )
            ret = 1;
          }

          else{   
            // 팝된 문자 checkSymbol이 앞서 읽어들인 여는 괄호 symbol과 맞는지 점검한다. 만약 쌍이 맞으면 정상이다.
            checkSymbol = pNode->data;
            if((symbol == ')' && checkSymbol == '(')
            || (symbol == ']' && checkSymbol == '[')
            || (symbol == '}' && checkSymbol == '{')){
              // 올바른 경우
            }
            else{   // 만약 괄호의 쌍이 맞지 않으면 오류 발생.
              ret = 2;
            }
            free(pNode);  // 팝된 노드에 대한 메모리 해제
          }
          break;
      }   // switch 끝남
      i++;
    }   // while이 끝남

    if( 0 == ret && isLinkedStackEmpty(pStack) == 0 ){
      // 수식 마지막까지 정상적으로 처리해서 변수 ret 값이 0 인데, 아직 스택에 남아 있는 노드가 있으면 오류다.
      // ( 여는 괄호가 닫는 괄호의 개수보다 많은 경우. )
      ret = 3;
    }
    deleteLinkedStack(pStack);
  }
  return ret;
}

int main(int argc, const char *argv[]){
  int checkResult = 0, i = 0;
  char err[100];
  char szExpressionStr[][50] = {
    "( A + B ) * C",
    "{ ( A + B ) * C } * D",
    "( A + B ) * C )",
    "( ( A + B ) * C ",
    "{ ( A + B } ) * C * D"
  };

  for( i = 0 ; i < sizeof(szExpressionStr)/sizeof(szExpressionStr[0]) ; i++ ){
    checkResult = checkBracketMatching(szExpressionStr[i]);
    if(checkResult == 1 ){
      strcpy(err, "오류, 닫는 괄호가 여는 괄호보다 많음.");
    }
    else if(checkResult == 2 ){
      strcpy(err, "오류, 괄호의 쌍이 맞지 않음.");
    }
    else{
      strcpy(err, "오류, 여는 괄호가 닫는 괄호의 개수보다 많음.");
    }

    if( checkResult == 0){
      printf("SUCCESS, %s \n", szExpressionStr[i]);
    }
    else{
      printf("FAIL-[%s], %s\n", err, szExpressionStr[i]);
    }
  }
  return 0;
}