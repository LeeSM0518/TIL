// 모듈화 프로그래밍
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "exprlinkedstack.h"

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
int pushLS(LinkedStack* pStack, ExprToken data)
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
