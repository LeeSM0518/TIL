#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct LinkedStackNodeType
{
  char data;    // 자료
  struct LinkedStackNodeType* pLink;  // 연결 정보
}LinkedStackNode;


//pTop 멤버 변수를 이용하여 스택의 푸시와 팝, 피크 연산 등을 효과적으로 구현할 수 있다.
typedef struct LinkedStacType
{
  int currentCount;   // 현재 노드의 개수
  LinkedStackNode* pTop;  // 탑 노드의 포인터
}LinkedStack;


// 스택의 크기가 정해져있지 않기 때문에 isFull()가 필요없다.
// 구조체에 대해 멤버 변수들을 모두 0으로 초기화하여 모든 멤버 변수의 값이 0이 되고 pTop은 NULL로 지정된다.
LinkedStack* createLinkedStack()
{
  LinkedStack *pReturn = NULL;
  pReturn = (LinkedStack*)malloc(sizeof(LinkedStack));
  memset(pReturn, 0, sizeof(LinkedStack));
  return pReturn;
}


// Step-A. 새로운 노드의 생성
// Step-B. 새로운 Top 노드의 이전 노드 설정
// Step-C. 탑 노드 변경
int pushLS(LinkedStack* pStack, char data)
{
  int ret = 0;
  LinkedStackNode *pNode = NULL;

  if(pStack != NULL){
    pNode = (LinkedStackNode*)malloc(sizeof(LinkedStackNode));
    if(pNode != NULL){
      pNode->data = data; 
      // Step-A : 새로운 노드를 생성하고 자료를 지정한다.
      pNode->pLink = pStack->pTop;  // Step-B : 새로 추가한 노드의 이전 노드로 기존 탑 노드를 지정한다.
      pStack->pTop = pNode;   // Step-C : 연결 스택의 탑 노드를 새로 추가한 노드로 변경한다.

      pStack->currentCount++;
      ret = 1;
    }
    else{
      printf("오류, 메모리 할당, pushLS()\n");
    }
  }

  return ret;   // 자료 추가가 성공적으로 이루어지면 지역 변수 ret을 1로 지정하고 반환
}


int isLinkedStackEmpty(LinkedStack* pStack)
{
  int ret = 0;

  if(pStack != NULL){
    if(pStack->currentCount == 0){
      ret = 1;
    }
  }

  return ret;   // 연결 스택이 비어있으면 1로 반환
}


// Step-A : 반환 노드 지정
// Step-B : 탑 노드 변경
// Step-C : 반환 노드의 링크 초기화
// 반환되는 노드의 링크를 NULL로 초기화 시키지 않으면 연결 스택의 다른 노드까지 접근이 가능하기 때문에 문제가 발생할 수 있다.
LinkedStackNode* popLS(LinkedStack* pStack)
{
  LinkedStackNode* pReturn = NULL;
  if( pStack != NULL){
    if(isLinkedStackEmpty(pStack) == 0){  // 유효성 점검, 연결 스택이 비어 있는지 점검
      pReturn = pStack->pTop;   // Step-A : 기존 탑 노드를 반환 노드로 지정
      pStack->pTop = pReturn->pLink;    // Step-B : 새로운 탑 노드를 기존 탑 노드의 이전 노드로 지정
      pReturn->pLink = NULL;    // Step-C : 반환 노드의 이전 노드 정보를 초기화

      pStack->currentCount--;   // 연결 스택의 노드 개수를 1 감소
    }
  }
  
  return pReturn;
}


LinkedStackNode* peekLS(LinkedStack* pStack)
{
  LinkedStackNode* pReturn = NULL;
  if(pStack != NULL){
    if(isLinkedStackEmpty(pStack) == 0 ){ // 유효성 점검, 연결 스택이 비어있는지 점검
      pReturn = pStack->pTop;   // 반환 값 지정, 스택의 탑 노드를 가리키는 포인터 반환
    }
  }
  return pReturn;
}

void deleteLinkedStack(LinkedStack* pStack)
{
  LinkedStackNode* pNode = NULL;
  LinkedStackNode* pDelNode = NULL;

  if(pStack != NULL){
    pNode = pStack->pTop;
    while(pNode != NULL){
      pDelNode = pNode;
      pNode = pNode->pLink;
      free(pDelNode);
    }
    free(pStack);
  }
}

void displayLinkedStack(LinkedStack *pStack)
{
  LinkedStackNode *pNode = NULL;
  int i = 1;
  if(pStack != NULL){
    printf("현재 노드 개수 : %d\n", pStack->currentCount);
    pNode = pStack->pTop;

    while(pNode != NULL){
      printf("[%d]-[%c]\n", pStack->currentCount - i, pNode->data);
      i++;
      pNode = pNode->pLink;
    }
    printf("\n");
  }
}

int main(int argc, char *argv[])
{
  LinkedStack *pStack = NULL;
  LinkedStackNode *pNode = NULL;

  pStack = createLinkedStack();
  if(pStack != NULL){
    pushLS(pStack, 'A');
    pushLS(pStack, 'B');
    pushLS(pStack, 'C');
    pushLS(pStack, 'D');
    displayLinkedStack(pStack);

    pNode = popLS(pStack);
    if(pNode != NULL)
    {
      printf("pop-[%c]\n\n",pNode->data);
      free(pNode);
    }
    displayLinkedStack(pStack);
    pNode = peekLS(pStack);
    
    if(pNode != NULL){
      printf("peek-[%c]\n\n",pNode->data);
    }
    displayLinkedStack(pStack);

    deleteLinkedStack(pStack);
  }

  return 0;
}