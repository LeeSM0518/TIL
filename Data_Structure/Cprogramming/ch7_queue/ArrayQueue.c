#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct ArrayQueueNodeType{
  char data;
} ArrayQueueNode; // 큐의 노드

typedef struct ArrayQueueType{
  int maxCount;           // 최대 자료 개수
  int currentCount;       // 현재 자료 개수
  int front;              // 프런트 위치
  int rear;               // 리어 위치
  ArrayQueueNode *pData;  // 자료를 저장하는 1차원 배열 포인터 
}ArrayQueue;

int isArrayQueueFull(ArrayQueue* pQueue);
int isArrayQueueEmpty(ArrayQueue* pQueue);

ArrayQueue* createArrayQueue(int size)
{
  ArrayQueue *pReturn = NULL;
  pReturn = (ArrayQueue*)malloc(sizeof(ArrayQueue));
  memset(pReturn, 0, sizeof(ArrayQueue));   // 메모리 할당과 0으로 초기화
  pReturn->maxCount = size;
  pReturn->front = -1;
  pReturn->rear = -1;   // 규 크기 설정, 프런트 와 리어 위치 초기화
  // -1 로 초기화 하는 이유는 큐가 비어있는 상태라는 것을 알려주기 위함이다.

  // 자료를 저장하는 배열의 메모리 할당, 0으로 초기화
  pReturn->pData = (ArrayQueueNode*)malloc(sizeof(ArrayQueueNode) * size);
  memset(pReturn->pData, 0, sizeof(ArrayQueueNode) * size); 

  return pReturn;
}

// rear의 마지막 위치 인덱스는 maxCount - 1 이다.
// currentCount == maxCount 이면 큐가 가득 차 있나는 것을 의미한다.

int enqueueAQ(ArrayQueue* pQueue, char data)
{
  int ret = 0;

  if(pQueue != NULL){
    if(isArrayQueueFull(pQueue) == 0){          // 새로운 자료 추가 가능 여부 점검
      pQueue->rear++;                           // 리어 위치 변경
      pQueue->pData[pQueue->rear].data = data;  // 새로운 자료 추가
      pQueue->currentCount++;                   // 현재 자료 개수 증가
      ret = 1;
    }
    else{
      printf("오류, 큐가 가득 찼습니다, enqueueAQ()\n");
    }
  }

  return ret;
}

int isArrayQueueFull(ArrayQueue* pQueue)
{
  int ret = 0;

  if(pQueue != NULL){
    if(pQueue->currentCount == pQueue->maxCount
    || pQueue->rear == pQueue->maxCount - 1){
      ret = 1;
    }
  }
  return ret;
}

ArrayQueueNode *dequeueAQ(ArrayQueue* pQueue)
{
  ArrayQueueNode *pReturn = NULL;
  if(pQueue != NULL){
    if(isArrayQueueEmpty(pQueue) == 0){
      pReturn = (ArrayQueueNode*)malloc(sizeof(ArrayQueueNode));
      if(pReturn != NULL){
        // 프런트 위치 변경 및 반환 노드 내용 복사
        // 현재 노드 개수 1 감소
        pQueue->front++;
        pReturn->data = pQueue->pData[pQueue->front].data;
        pQueue->currentCount--;
      }
      else{
        printf("오류, 메모리 할당, dequeueAQ()\n");
      }
    }
  }
  return pReturn;
}

int isArrayQueueEmpty(ArrayQueue* pQueue)
{
  int ret = 0;

  if(pQueue != NULL){
    if(pQueue->currentCount == 0){
      ret = 1;
    }
  }
  return ret;
}

ArrayQueueNode *peekAQ(ArrayQueue *pQueue)
{
  ArrayQueueNode *pReturn = NULL;
  if(pQueue != NULL){
    if(isArrayQueueEmpty(pQueue) == 0){
      // pQueue->front + 1 인 이유는 front는 프런트 노드의
      // 이전 위치를 가리키기 때문이다.
      pReturn = &(pQueue->pData[pQueue->front + 1]);
    }
  }
  return pReturn;
}

void deleteArrayQueue(ArrayQueue* pQueue)
{
  if(pQueue != NULL){
    if(pQueue->pData != NULL){
      free(pQueue->pData);
    }
    free(pQueue);
  }
}

void displayArrayQueue(ArrayQueue* pQueue)
{
  int i = 0;

  if(pQueue != NULL){
    printf("큐의 크기 : %d, 현재 노드 개수 : %d\n",
        pQueue->maxCount, pQueue->currentCount);

    for( i = pQueue->front + 1 ; i <= pQueue->rear; i++){
      printf("[%d] - [%c]\n", i, pQueue->pData[i].data);
    }
    printf("\n");
  }
}

int main(int argc, char* argv[])
{
  ArrayQueue *pQueue = NULL;
  ArrayQueueNode *pNode = NULL;

  pQueue = createArrayQueue(4);
  if(pQueue != NULL){
    enqueueAQ(pQueue, 'A');
    enqueueAQ(pQueue, 'B');
    enqueueAQ(pQueue, 'C');
    enqueueAQ(pQueue, 'D');
    displayArrayQueue(pQueue);

    pNode = dequeueAQ(pQueue);
    if(pNode != NULL){
      printf("Dequeue Value = [%c]\n\n", pNode->data);
      free(pNode);
    }
    displayArrayQueue(pQueue);
  
    pNode = peekAQ(pQueue);
    if(pNode != NULL){
      printf("Peek Value = [%c]\n\n", pNode->data);
    }
    displayArrayQueue(pQueue);

    enqueueAQ(pQueue, 'E');
    displayArrayQueue(pQueue);
  }
  return 0;
}