#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "exprlinkedstack.h"
#include "exprlinkedstack.c"

void calcExpr(ExprToken* pExprTokens, int tokenCount)
{
  LinkedStack *pStack = NULL;
  LinkedStackNode *pNode1 = NULL, *pNode2 = NULL;
  Precedence tokenType;
  int i = 0;

  if(pExprTokens == NULL){    // 전달된 수식 배열이 NULL이 아닌지 점검
    return;
  }

  pStack = createLinkedStack();
  if(pStack != NULL){   // 스택 생성 및 점검
  // 1번째 규칙 : 스택이 피연산자 인가?
  // 2번째 규칙 : 수식에 토큰이 더 있는가?
  // 3번째 규칙 : 결과가 있는가?
    for( i = 0 ; i < tokenCount ; i++){   // 토큰 배열의 크기( 토큰 개수 ) 만큼 반복문을 실행, 수식 계산
      tokenType = pExprTokens[i].type;

      if(tokenType == operand){   // 피연산자를 만나면 스택에 푸시
        pushLS(pStack, pExprTokens[i]);
      }

      else{ 
        // 연산자를 만났을 때
        pNode2 = popLS(pStack);
        if(pNode2 != NULL){
          pNode1 = popLS(pStack);
          if(pNode1 != NULL){
            // 연산에 필요한 2개의 피연사자를 스택에서 팝한다.

            double op1 = pNode1->data.value;
            double op2 = pNode2->data.value;

            ExprToken newToken;
            newToken.type = operand;
            switch(tokenType){        // 타입에 따라 연산을 하고 새로운 토큰에 저장한다.
              case plus:
                  newToken.value = op1 + op2;
                  break;
              case minus:
                  newToken.value = op1 - op2;
                  break;
              case multiply:
                  newToken.value = op1 * op2;
                  break;
              case divide:
                  newToken.value = op1 / op2;
                  break;
            }
            pushLS(pStack, newToken);   // 연산한 결과는 다시 스택에 푸시한다
            free(pNode1);
          }
          free(pNode2);
        }
      }   // else 문 종료
    }

    pNode1 = popLS(pStack);   // 최종 처리 결괏값을 스택에서 팝한다.
    if(pNode1 != NULL){
      printf("수식 계산 결과는 [%f] 입니다.\n", pNode1->data.value);
      free(pNode1);
    }
    else{   // 만약 팝된 노드가 없으면 수식에 오류가 있는 경우이다.
      printf("오류, 수식에 오류가 있습니다.\n");
    }
    deleteLinkedStack(pStack);
  }
}

int main(int argc, const char *argv[]){
  int i = 0;

  ExprToken *pExprTokens = (ExprToken*)malloc(sizeof(ExprToken) * 7);
  pExprTokens[0].type = operand;
  pExprTokens[0].value = 2;
  pExprTokens[1].type = operand;
  pExprTokens[1].value = 3;
  pExprTokens[2].type = operand;
  pExprTokens[2].value = 4;
  pExprTokens[3].type = plus;
  pExprTokens[3].value = 0;
  pExprTokens[4].type = operand;
  pExprTokens[4].value = 5;
  pExprTokens[5].type = multiply;
  pExprTokens[5].value = 0;
  pExprTokens[6].type = minus;
  pExprTokens[6].value = 0;

  printf("Expression : 2 3 4 + 5 * - \n");
  calcExpr(pExprTokens, 7);

  free(pExprTokens);

  return 0;
}