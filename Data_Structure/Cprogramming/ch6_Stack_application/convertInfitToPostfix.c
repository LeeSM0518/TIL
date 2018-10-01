#include <stdio.h>
#include <stdlib.h>
#include "exprlinkedstack.h"
#include "exprlinkedstack.c"

int inStackPrecedence(Precedence oper);   // (스택 내부 우선) 연산자의 우선순위 값을 반환하는 함수
int outStackPrecedence(Precedence oper);   // (스택 외부 우선) 연산자의 우선순위 값을 반환하는 함수
void printToken(ExprToken element);

void convertInfixToPostfix(ExprToken *pExprTokens, int tokenCount)
{
  // 규칙 1) 피연산자를 만나면 바로 출력한다.
  // 규칙 2) 연산자를 만나면 일단 스택에 저장
  // 규칙 3) 단, 스택에 저장 중인 연산자 중에서 우선순위가 높은 연산자는 팝하여 출력한다.
  // 규칙 4) (주의) 스택의 내부와 외부에서의 연산자 우선순위는 다르다.
  // 규칙 5) 닫는 괄호 연산자 ) 를 만나면 스택에서 여는 괄호 연산자 ( 를 만날 때까지 스택에 저장된 연산자들을 모두 팝하고 이를 출력한다.

  LinkedStack *pStack = NULL;
  LinkedStackNode *pNode = NULL;
  Precedence tokenType, inStackTokenType;
  int i = 0;

  pStack = createLinkedStack();
  if(pStack != NULL){
    // 스택 생성 및 NULL인지 점검
    for( i = 0 ; i < tokenCount ; i++){
      // 토큰 배열의 크기( 토큰 개수 ) 만큼 반복문을 실행하면서 수식을 변환
      tokenType = pExprTokens[i].type;
      switch(tokenType){
        case operand:   // 규칙 1) 피연산자를 만나면 바로 출력
            printf("%4.1f\t",pExprTokens[i].value);
            break;
        case rparen:    
        // 규칙 5) 닫는 괄호 연산자 ) 를 만난 경우 처리, 즉, 여는 괄호 연산자 ( 를 만날 때까지 while 반복문을 실행하면서
        //  스택 내 연산자들을 팝하고 출력한다.
            pNode = popLS(pStack);
            while( pNode != NULL && pNode->data.type != lparen ){
              printToken(pNode->data);
              free(pNode);

              pNode = popLS(pStack);
            }
            if(pNode != NULL) free(pNode);
            break;
        default:
        // 피연산자와 닫는 괄호 연산자 ) 이외의 경우 처리, 스택 내에 저장된 연산자 중 현재 연산자보다 우선순위가 높은 연산자는
        // 팝하여 출력한다.
        // 현재 수식에서 읽은 연산자 = tokenType
        // 스택 내에 있는 연산자 = inStackTokenType
        // 우선순위 비교 결과에 따라서 스택을 pop으로 가져오지 않고 peek으로 확인한다.
        // 비교식에서 수식에서 읽은 연산자는 스택 외부에서의 우선순위를 사용해야 하기 때문에
        // 함수 outStackPrecedence(tokenType)로, 스택 내부에 있는 연산자는 스택 내부에서의 우선순위를 사용해야 하기 때문에
        // 함수 inStackPrecedence(inStackTokenType)로 우선순위를 구합니다.
        // 이렇게 비교한 결과, 스택의 연산자 중에서 현재 수식에서 읽은 연산자보다 우선순위가 높은 연산자들은
        // 모두 팝한 이후에 현재 연산자를 푸시합니다.

            while(isLinkedStackEmpty(pStack) == 0){
              inStackTokenType = peekLS(pStack)->data.type;
              if(outStackPrecedence(tokenType) <= inStackPrecedence(inStackTokenType) ){
                pNode = popLS(pStack);
                if(pNode != NULL){
                  printToken(pNode->data);
                  free(pNode);
                }
              }

              else{
                break;
              }
            }
            pushLS(pStack, pExprTokens[i]);   // 그러고 나서, 현재 연산자를 스택에 푸시한다.
            break;

      } // switch 종료
    } // for문 종료

    while(isLinkedStackEmpty(pStack) == 0){
      // 반복문을 모두 실행하면 이제 스택에 남은 연산자들을 팝하여 출력한다.
      pNode = popLS(pStack);
      if(pNode != NULL){
        printToken(pNode->data);
        free(pNode);
      }
    }
    deleteLinkedStack(pStack);
  }
}

// 여는 괄호 연산자 ( 를 제외하고 다른 연산자들은 스택 내부와 외부 모두 같은 값을 가진다.
// 스택 내부 우선순위 : )   >>   * , /   >>   + , -   >>   (
// 스택 외부 우선순위 : (   >>      )    >>   * , /   >>   + , -
// 우선순위가 다른 이유는 여는 괄호가 스택 외부에서는 괄호로서 우선순위가 가장 높아 발견 즉시 스택에 푸시해야 하는 반면,
// 스택 내부에서의 여는 괄호는 닫는 괄호가 나올 때에만 스택에서 삭제할 수 있기 때문에 우선순위가 가장 낮아야 하기 때문이다.

int inStackPrecedence(Precedence oper)
{   // 스택 내부에서의 우선순위를 반환하는 함수
  switch(oper){
    case lparen:
        return 0;
    case rparen:
        return 4;
    case multiply:
    case divide:
        return 2;
    case plus:
    case minus:
        return 1;
  }
  return -1;
}

int outStackPrecedence(Precedence oper)
{   // 스택 외부에서의 우선순위를 반환하는 함수
  switch(oper){
    case lparen:
        return 5;
    case rparen:
        return 4;
    case multiply:
    case divide:
        return 2;
    case plus:
    case minus:
        return 1;
  }
  return -1;
}

void printToken(ExprToken element)
{   // 토큰의 내용을 출력하는 함수
  switch(element.type){
    case lparen:
        printf("( ");
        break;
    case rparen:
        printf(") ");
        break;
    case plus:
        printf("+ ");
        break;
    case minus:
        printf("- ");
        break;
    case multiply:
        printf("* ");
        break;
    case divide:
        printf("/ ");
        break;
    case operand:
        printf("%4.1f ", element.value);
        break;
  }
}

int main(int argc, const char *argv[])
{
  int i = 0 ;

  // 2 - ( 3 + 4 ) * 5
  ExprToken *pExprTokens = (ExprToken*)malloc(sizeof(ExprToken) * 9);
  pExprTokens[0].type = operand;
  pExprTokens[0].value = 2;
  pExprTokens[1].type = minus;
  pExprTokens[1].value = 0;
  pExprTokens[2].type = lparen;
  pExprTokens[2].value = 0;
  pExprTokens[3].type = operand;
  pExprTokens[3].value = 3;
  pExprTokens[4].type = plus;
  pExprTokens[4].value = 0;
  pExprTokens[5].type = operand;
  pExprTokens[5].value = 4;
  pExprTokens[6].type = rparen;
  pExprTokens[6].value = 0;
  pExprTokens[7].type = multiply;
  pExprTokens[7].value = 0;
  pExprTokens[8].type = operand;
  pExprTokens[8].value = 5;

  printf("Infix Expression : 2.0 - ( 3.0 + 4.0 ) * 5.0\n");
  printf("Postfix Expression : \n");
  convertInfixToPostfix(pExprTokens, 9);

  free(pExprTokens);

  return 0;
}