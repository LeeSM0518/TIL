#ifndef _EXPRLINKEDSTACK_
#define _EXPRLINKEDSTACK_

typedef enum PrecedenceType { lparen, rparen, multiply, divide, plus, minus, operand }Precedence;
// lparen : ( , rparen : ) , multiply : * , divide : / , plus : + , minus : - , operand : 피연산자
// 토큰의 종류를 미리 정의

typedef struct ExprTokenType{
  double value; // 피연산자
  Precedence type;  // 연산자
} ExprToken;

typedef struct LinkedStackNodeType
{
  ExprToken data;   // linkedstack.h 와 다른점
  struct LinkedStackNodeType* pLink;
}LinkedStackNode;

typedef struct LinkedStackType
{
  int currentCount;
  LinkedStackNode* pTop;
}LinkedStack;

LinkedStack* createLinkedStack();
int pushLS(LinkedStack* pStack, ExprToken data);  // 변경된 부분
int isLinkedStackEmpty(LinkedStack* pStack);
LinkedStackNode* popLS(LinkedStack* pStack);
LinkedStackNode* peekLS(LinkedStack* pStack);
void deleteLinkedStack(LinkedStack* pStack);
void displayLinkedStack(LinkedStack* pStack);

#endif