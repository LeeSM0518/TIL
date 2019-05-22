# Data Structure

### Chapter 7. Queue



## Circle Queue

* **enqueueAQ** :  인큐 연산

  ```c
  int enqueueAQ(CircleQueue* pQueue, char data)
  {
    int ret = 0;
  
    if(pQueue != NULL){
      if(isCircleQueueFull(pQueue) == 0 ){
        // 선형 큐는 rear 연산을 ++ 로 했으나 
        // 원형 큐는 rear = (rear + 1) % maxCount 로 연산하여 
        // 나머지 값으로 배열의 처음과 마지막을 연결시킬 수 있도록 한다.
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
  ```


* **dequeueAQ** :  디큐 연산

  ```c
  CircleQueueNode *dequeueAQ(CircleQueue* pQueue)
  {
    CircleQueueNode *pReturn = NULL;
    if(pQueue != NULL){
      if(isCircleQueueEmpty(pQueue) == 0){
        pReturn = (CircleQueueNode*)malloc(sizeof(CircleQueueNode));
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
  ```

* **isCircleQueueFull** :  큐가 가득차 있는지 점검

  ```c
  int isCircleQueueFull(CircleQueue* pQueue)
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
  ```



* **displayCircleQueue** :  큐를 출력시켜준다.

  ```c
  void displayCircleQueue(CircleQueue *pQueue)
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
  ```


* **main함수**

  ```c
  int main(int argc, char *argv[])
  {
    CircleQueue *pQueue = NULL;
    CircleQueueNode *pNode = NULL;
  
    pQueue = createCircleQueue(4);
    if(pQueue != NULL){
      enqueueAQ(pQueue, 'A');
      enqueueAQ(pQueue, 'B');
      enqueueAQ(pQueue, 'C');
      enqueueAQ(pQueue, 'D');		// A, B, C, D 노드 삽입
      displayCircleQueue(pQueue);
  
      pNode = dequeueAQ(pQueue);
      if(pNode != NULL){
        printf("Dequeue Value - [%c]\n", pNode->data);
        free(pNode);
      }
      displayCircleQueue(pQueue);
  
      pNode = dequeueAQ(pQueue);
      if(pNode != NULL){
        printf("Dequeue Value - [%c]\n", pNode->data);
        free(pNode);
      }
      displayCircleQueue(pQueue);
    
      pNode = peekAQ(pQueue);
      if(pNode != NULL){
        printf("Peek Value - [%c]\n", pNode->data);
      }
      displayCircleQueue(pQueue);
  
      enqueueAQ(pQueue,'E');
  
      displayCircleQueue(pQueue);
    }
    return 0;
  }
  ```


* **실행결과**:

  ```
  큐의 크기 : 4, 현재 노드 개수 :4
  [0] - [A]
  [1] - [B]
  [2] - [C]
  [3] - [D]
  
  Dequeue Value - [A]
  
  큐의 크기 : 4, 현재 노드 개수 :3
  [1] - [B]
  [2] - [C]
  [3] - [D]
  
  Dequeue Value - [B]
  
  큐의 크기 : 4, 현재 노드 개수 :2
  [2] - [C]
  [3] - [D]
  
  Peek Value - [C]
  
  큐의 크기 : 4, 현재 노드 개수 :2
  [2] - [C]
  [3] - [D]
  
  큐의 크기 : 4, 현재 노드 개수 :3
  [2] - [C]
  [3] - [D]
  [0] - [E]
  ```
