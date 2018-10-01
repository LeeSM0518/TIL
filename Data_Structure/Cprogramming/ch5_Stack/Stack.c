#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct ArrayStackNodeType{
  char data;  // 자료
}ArrayStackNode;
// 자료를 노드로 한 번 더 감싸는 이유는
// 동시에 여러 개의 자료를 저장할 수 있도록 하기 위해서 이다.

typedef struct ArrayStackType{
  int maxCount;   // 최대 자료 개수
  int currentCount;   // 현재 저장된 자료의 개수
  ArrayStackNode *pData;    // 자료를 저장하는 1차원 배열
}ArrayStack;
// 배열의 마지막 원소의 인덱스는 (maxCount - 1) 이다
// 배열의 현재 저장된 노드 개수는 (currentCount - 1) 이다.

ArrayStack* createArrayStack(int size)
{
  ArrayStack *pReturn = NULL;
  pReturn = (ArrayStack*)malloc(sizeof(ArrayStack));  // 배열 스택 자체의 메모리 할당
  memset(pReturn, 0, sizeof(ArrayStack));   // 배열 스택 자체의 메모리 초기화
  pReturn->maxCount = size;   // 배열 스택의 최대 자료 개수 지정

  pReturn->pData = (ArrayStackNode*)malloc(sizeof(ArrayStackNode)*size);    // 최대 개수 만큼 자료 메모리 할당
  memset(pReturn->pData, 0, sizeof(ArrayStackNode)*size);   // 최대 개수 만큼 자료 메모리 초기화

  return pReturn;
}

int isArrayStackFull(ArrayStack* pStack)
{
  int ret = 0;
  if(pStack != NULL){
    if(pStack->currentCount == pStack->maxCount){   // 현재의 위치와 탑의 위치를 비교한다
      ret = 1;
    }
  }
  return ret; // 스택이 꽉 차있으면 1 아니면 0을 리턴
}

int pushAS(ArrayStack* pStack, char data)
{
  int ret = 0;

  if(isArrayStackFull(pStack) == 0){  // 유효성 점검, 배열 스택이 가득 차있는지를 판단한다.
    pStack->pData[pStack->currentCount].data = data;  
    // 매개 변수로 받은 pStack의 데이터 배열의 pStack이 Top인 CurrentCount 번째의 데이터에 data를 넣는다.
    pStack->currentCount++; // 현재 개수를 1 올려준다.
    ret = 1;
  }
  else{
    printf("오류, 스택이 가득 찼습니다, \n");
  }
  
  return ret;   // 정상적으로 처리되면 1 아니면 0 을 리턴
}

int isArrayStackEmpty(ArrayStack* pStack)
{
  int ret = 0;

  if(pStack != NULL){
    if( pStack->currentCount == 0){
      ret = 1;
    }
  }
  return ret;   // 현재 스택이 비어있으면 1 아니면 0 을 리턴
}

ArrayStackNode *popAS(ArrayStack* pStack)
{
  ArrayStackNode *pReturn = NULL;

  if( 0 == isArrayStackFull(pStack)){   // 유효성 점검, 배열 스택이 비어 있는지 점검
    pReturn = (ArrayStackNode*)malloc(sizeof(ArrayStackNode));  
    if(pReturn != NULL){    // 반환할 노드에 대해 메모리 할당 및 메모리 점검

      pReturn->data = pStack->pData[pStack->currentCount - 1].data;   // 노드에 반환할 자료를 대입
      pStack->currentCount--;   // 배열 스택의 탑 위치 변경

    }
    else{
      printf("오류, 메모리 할당, popAS()\n");
    }
  }

  return pReturn;
}
// popAS 에서는 노드를 반환하고 제거해야 하기 때문에 현재 자료 개수를 1만큼 감소 시킨다.
// 탑의 위치가 변경되어서, 만약 새로운 자료를 추가하면 기존 (쓰레기) 값을 덮어쓴다. (overwrite)
// popAS()를 호출하는 쪽에서는 반환되는 노드에 대해서 메모리를 해제해야 한다.
// 만약 현재 배열 스택이 비어 있다면 NULL을 반환시킨다.

ArrayStackNode* peekAS(ArrayStack* pStack)
{
  ArrayStackNode* pReturn = NULL;
  if(pStack != NULL){
    if(isArrayStackEmpty(pStack) == 0){   // 유효성 점검, 배열 스택이 비어 있는지 점검
      pReturn = &(pStack->pData[pStack->currentCount - 1]);   // 반환 값 지정, 배열 스택의 탑 노드를 가리키는 포인터 반환.
    }
  }

  return pReturn;
}
// peekAS() 에서는 반환되는 자료가 제거되지 않기 때문에 현재 자료 개수(pStack->currentCount)가 그대로 유지되며 탑 위치도 변경되지 않는다.

void deleteArrayStack(ArrayStack* pStack)
{
  if(pStack != NULL){
    if(pStack->pData != NULL){
      free(pStack->pData);    // 먼저 노드로 이루어진 배열에 대해서 메모리를 해제
    }
    free(pStack);   // 배열 스택 자체의 메모리를 해제
  }
}

void displayArrayStack(ArrayStack* pStack)
{
  int i = 0;
  if(pStack != NULL){
    int size = pStack->maxCount;
    int top = pStack->currentCount;

    printf("스택 크기 : %d, 현재 노드 개수 : %d\n",
            pStack->maxCount,
            pStack->currentCount);
    
    for( i = size - 1; i >= top; i--){
      printf("[%d]-[Empte]\n",i);   // 빈 노드 출력
    }

    for( i = top - 1 ; i >= 0 ; i--){
      printf("[%d]-[%c]\n", i, pStack->pData[i].data);    // 저장하고 있는 자료 출력
    }
    printf("\n");
  }
}

int main(int argc, char *argv[])
{
  ArrayStack *pStack = NULL;
  ArrayStackNode *pNode = NULL;

  pStack = createArrayStack(6);   // 최대 저장 자료 개수 6개
  if(pStack != NULL){
    pushAS(pStack, 'A');
    pushAS(pStack, 'B');
    pushAS(pStack, 'C');
    pushAS(pStack, 'D');
    displayArrayStack(pStack);

    pNode = popAS(pStack);
    if(pNode != NULL){
      printf("Pop 값-[%c]\n\n", pNode->data);
      free(pNode);    // pop을 한 뒤 메모리를 해제해야 하는것에 주의!!
    }

    displayArrayStack(pStack);

    pNode = peekAS(pStack);
    if(pNode != NULL){
      printf("Peek 값 - [%c]\n\n",pNode->data);
    }
    displayArrayStack(pStack);

    deleteArrayStack(pStack);
  }

  return 0;
}