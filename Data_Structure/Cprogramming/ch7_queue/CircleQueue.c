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
  pReturn->rear = -1;   // 큐 크기 설정, 프런트 와 리어 위치 초기화
  // -1 로 초기화 하는 이유는 큐가 비어있는 상태라는 것을 알려주기 위함이다.

  // 자료를 저장하는 배열의 메모리 할당, 0으로 초기화
  pReturn->pData = (ArrayQueueNode*)malloc(sizeof(ArrayQueueNode) * size);
  memset(pReturn->pData, 0, sizeof(ArrayQueueNode) * size); 

  return pReturn;
}

int enqueueAQ(ArrayQueue* pQueue, char data)
{
  int ret = 0;

  if(pQueue != NULL){
    if(isArrayQueueFull(pQueue) == 0 ){
      // 선형 큐는 rear 연산을 ++ 로 했으나 
      // 원형 큐는 rear = (rear + 1) % maxCount 로 연산하여 나머지 값으로
      // 배열의 처음과 마지막을 연결시킬 수 있도록 한다.
      pQueue->rear = (pQueue->rear + 1) % pQueue->maxCount;
      pQueue->pData[pQueue->rear].data = data;
      pQueue->currentCount++;
      ret = 1;
    }
    else{
      printf("오류, 큐가 가득 찼습니다, enqueueAQ()\n");
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
        // 선형 큐에서는 front++ 로 연산을 하였으나
        // 원형 큐에서는 (front + 1) % maxCount 연산을 하여
        // 마지막 노드와 처음 노드를 연결한다.
        //pQueue->front++;
        //pReturn->data = pQueue->pData[pQueue->front].data;
        pQueue->front = (pQueue->front + 1) % pQueue->maxCount;
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

int isArrayQueueFull(ArrayQueue* pQueue)
{
  int ret = 0;

  if(pQueue != NULL){
    // 선형 큐와는 다르게 원형 큐는 빈 노드가 낭비가 되지 않기 때문에
    // 현재 저장된 노드 개수 currentCount와
    // 최대 저장 가능 노드 개수 maxCount만 비교한다.
    if(pQueue->currentCount == pQueue->maxCount){
      ret = 1;
    }
  }
  return ret;
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

void displayArrayQueue(ArrayQueue *pQueue)
{
  int i = 0, position = 0;

  if(pQueue != NULL){
    printf("큐의 크기 : %d, 현재 노드 개수 :%d\n",
    pQueue->maxCount, pQueue->currentCount);
  }
  // 원래는 front가 rear보다 앞이 있지만
  // 원형 큐가 한바퀴 돌아서 rear가 front 앞으로 가는 상황이 발생하므로
  // for문을 pQueue->front + pQueue->currentCount 까지 진행 시켜줘야 한다.
  for( i = pQueue->front + 1 ; i <= pQueue->front + pQueue->currentCount; i++){
    // position이 혹시 배열의 크기 pQueue->maxCount를 벗어나는 경우
    // 다시 0으로 재지정 해주기 위해 mod(%) 연산을 이용한다.
    position = i % pQueue->maxCount;
    printf("[%d] - [%c]\n", position, pQueue->pData[position].data);
  }
  printf("\n");
}

int main(int argc, char *argv[])
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
      printf("Dequeue Value - [%c]\n", pNode->data);
      free(pNode);
    }
    displayArrayQueue(pQueue);

    pNode = dequeueAQ(pQueue);
    if(pNode != NULL){
      printf("Dequeue Value - [%c]\n", pNode->data);
      free(pNode);
    }
    displayArrayQueue(pQueue);
  
    pNode = peekAQ(pQueue);
    if(pNode != NULL){
      printf("Peek Value - [%c]\n", pNode->data);
    }
    displayArrayQueue(pQueue);

    enqueueAQ(pQueue,'E');

    displayArrayQueue(pQueue);
  }
  return 0;
}