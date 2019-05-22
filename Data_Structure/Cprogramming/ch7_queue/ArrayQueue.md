# Data Structure

### Chapter 7 . Queue



## ArrayQueue

* **ArrayQueueNode , ArrayQueueType** :  큐의 노드와 큐

  ```c
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
  ```




* **createArrayQueue** :  큐 생성

  ```c
  ArrayQueue* createArrayQueue(int size)
  {
    ArrayQueue *pReturn = NULL;
    pReturn = (ArrayQueue*)malloc(sizeof(ArrayQueue));
    memset(pReturn, 0, sizeof(ArrayQueue));   // 메모리 할당과 0으로 초기화
    pReturn->maxCount = size;
    pReturn->front = -1;
    pReturn->rear = -1;   // 큐 크기 설정, 프런트 와 리어 위치 초기화
    // -1 로 초기화 하는 이유는 큐가 비어있는 상태라는 것을 알려주기 위해서.
  
    // 자료를 저장하는 배열의 메모리 할당, 0으로 초기화
    pReturn->pData = (ArrayQueueNode*)malloc(sizeof(ArrayQueueNode) * size);
    memset(pReturn->pData, 0, sizeof(ArrayQueueNode) * size); 
  
    return pReturn;
  }
  // rear의 마지막 위치 인덱스는 maxCount - 1 이다.
  // currentCount == maxCount 이면 큐가 가득 차 있나는 것을 의미한다.
  ```




* **enqueueAQ** :  인큐 연산

  ```c
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
  ```



*  **isArrayQueueFull** :  큐가 가득차 있는지 점검

  ```c
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
  ```



*  **dequeueAQ** :  디큐 연산

  ```C
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
  ```


* **isArrayQueueEmpty** :  큐가 비어있는지 점검

  ```C
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
  ```



* **peekAQ** :  피크 연산

  ```c
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
  ```



* **deleteArrayQueue** :  큐를 제거

  ```c
  void deleteArrayQueue(ArrayQueue* pQueue)
  {
    if(pQueue != NULL){
      if(pQueue->pData != NULL){
        free(pQueue->pData);
      }
      free(pQueue);
    }
  }
  ```



*  **displayArrayQueue** :  큐를 출력시키는 함수

  ```c
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
  ```


*  **main 함수**

  ```c
  int main(int argc, char* argv[])
  {
    ArrayQueue *pQueue = NULL;
    ArrayQueueNode *pNode = NULL;
  
    pQueue = createArrayQueue(4);
    if(pQueue != NULL){
      enqueueAQ(pQueue, 'A');
      enqueueAQ(pQueue, 'B');
      enqueueAQ(pQueue, 'C');
      enqueueAQ(pQueue, 'D');		// A, B, C, D 를 Enqueue
      displayArrayQueue(pQueue);
  
      pNode = dequeueAQ(pQueue);	// Dequeue
      if(pNode != NULL){
        printf("Dequeue Value = [%c]\n\n", pNode->data);
        free(pNode);
      }
      displayArrayQueue(pQueue);
    
      pNode = peekAQ(pQueue);		// Peek
      if(pNode != NULL){
        printf("Peek Value = [%c]\n\n", pNode->data);
      }
      displayArrayQueue(pQueue);
  
      enqueueAQ(pQueue, 'E');		// Enqueue
      displayArrayQueue(pQueue);
    }
    return 0;
  }
  ```


* **실행결과** 

  ```
  큐의 크기 : 4, 현재 노드 개수 : 4
  [0] - [A]
  [1] - [B]
  [2] - [C]
  [3] - [D]
  
  Dequeue Value = [A]
  
  큐의 크기 : 4, 현재 노드 개수 : 3
  [1] - [B]
  [2] - [C]
  [3] - [D]
  
  Peek Value = [B]
  
  큐의 크기 : 4, 현재 노드 개수 : 3
  [1] - [B]
  [2] - [C]
  [3] - [D]
  
  오류, 큐가 가득 찼습니다, enqueueAQ()
  
  큐의 크기 : 4, 현재 노드 개수 : 3
  [1] - [B]
  [2] - [C]
  [3] - [D]
  ```

