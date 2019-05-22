# Data Structure (Chapter 7. Queue)



## LinkedQueue

* **LinkedQueueNode, LinkedQueue** :  연결 큐 노드 와 연결 큐 구조체

  ```c
  typedef struct LinkedQueueNodeType
  {
    char data;
    struct LinkedQueueNodeType* pLink;
  }LinkedQueueNode;
  
  typedef struct LinkedQueueType
  {
    int currentCount;         // 현재 노드의 개수
    LinkedQueueNode* pFront;  // Front 노드의 포인터
    LinkedQueueNode* pRear;   // Rear 노드의 포인터
  } LinkedQueue;
  ```


* **createLinkedQueue** :  큐 생성

  ```c
  LinkedQueue* createLinkedQueue()
  {
    LinkedQueue *pReturn =(LinkedQueue*)malloc(sizeof(LinkedQueue));
    memset(pReturn, 0, sizeof(LinkedQueue));
  
    return pReturn;
  }
  ```


* **enqueueLQ** :  인큐 연산

  ```c
  int enqueueLQ(LinkedQueue* pQueue, char data)
  {
    int ret = 0;
    LinkedQueueNode *pNode = NULL;
  
    // Step-A. 새로운 노드의 추가
    // 새로운 노드 메모리 할당과 값 저장
    pNode = (LinkedQueueNode*)malloc(sizeof(LinkedQueueNode));
    pNode->data = data;
    pNode->pLink = NULL;
  
    // Step-B. 기존 리어 노드의 처리
    if(isLinkedQueueEmpty(pQueue) == 0){
      pQueue->pRear->pLink = pNode; // 기존 리어 노드의 다음 노드 지정
      pQueue->pRear= pNode;         // 큐의 리어 노드 링크 변경
    }
  
    // Step-C. 큐의 리어 노드 링크의 처리
    else{
      // 비어있는 스택이면 새로운 노드가
      // 리어와 프런트 둘 다에 해당한다.
      pQueue->pFront = pNode;
      pQueue->pRear = pNode;
    }
    pQueue->currentCount++;
    ret = 1;
  
    return ret;
  }
  ```


* **dequeueLQ**  :  디큐 연산

  ```c
  LinkedQueueNode* dequeueLQ(LinkedQueue* pQueue)
  {
    LinkedQueueNode* pReturn = NULL;
    if(isLinkedQueueEmpty(pQueue) == 0){
      pReturn = pQueue->pFront;   // 반환 값 설정, 기존 프런트 노드
      // Step-A. 큐의 프런트 노드 재지정
      pQueue->pFront = pQueue->pFront->pLink; 
      // Step-B. 반환 노드의 링크 초기화
      pReturn->pLink = NULL;
  
      pQueue->currentCount--;
  
      // 연결 큐에 남은 노드가 더 없음에도 리어는 여전히
      // 큐에서 제거된 기존 노드 pReturn을 가리키고 있기 때문에
      // 연결을 끊어줘야 한다.
      if(isLinkedQueueEmpty(pQueue) == 1){
        pQueue->pRear = NULL;
      }
    }
  
    return pReturn;
  }
  ```


* **peekLQ** : 피크 연산

  ```c
  LinkedQueueNode* peekLQ(LinkedQueue* pQueue)
  {
    LinkedQueueNode* pReturn = NULL;
    if(pQueue != NULL){
      if(isLinkedQueueEmpty(pQueue) == 0){
        // 현재 프런트의 주솟값을 포인터 변수 pReturn에 대입
        pReturn = pQueue->pFront;
      }
    }
    return pReturn;
  }
  ```


* **deleteLinkedQueue** : 큐 삭제 연산

  ```c
  void deleteLinkedQueue(LinkedQueue* pQueue)
  {
    LinkedQueueNode *pNode = NULL;
    LinkedQueueNode *pDelNode = NULL;
  
    if(pQueue != NULL){
      pNode = pQueue->pFront; // 시작노드 = 프런트노드
      // 루프 돌면서 각 노드 메모리 해제
      while(pNode != NULL){
        pDelNode = pNode;
        pNode = pNode->pLink;
        free(pDelNode);
      }
      // 큐 자체의 메모리 해제
      free(pQueue);
    }
  }
  ```


* **isLinkedQueueEmpty** :  큐가 비어있는지 점검

  ```c
  int isLinkedQueueEmpty(LinkedQueue* pQueue)
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

  > 배열 큐와 다르게 메모리를 동적으로 생성하기 때문에 큐가 꽉 차있는지 점검하는 연산은 필요가 없다.



* **displayLinkedQueue** :  큐를 출력시키는 연산

  ```c
  void displayLinkedQueue(LinkedQueue *pQueue)
  {
    LinkedQueueNode *pNode = NULL;
    int i = 0;
    if(pQueue != NULL){
      printf("현재 노드 개수 : %d\n", pQueue->currentCount);
      pNode = pQueue->pFront;
      while(pNode != NULL){
        printf("[%d] - [%c]\n", i, pNode->data);
        i++;
        pNode = pNode->pLink;
      }
    }
  }
  ```


* **main 함수**

  ```c
  int main(int argc, char *argv[])
  {
    LinkedQueue *pQueue = NULL;
    LinkedQueueNode *pNode = NULL;
  
    // 큐 생성
    pQueue = createLinkedQueue();
    if(pQueue != NULL){
      // 큐 초기화 : A, B, C, D (노드 추가)
      enqueueLQ(pQueue, 'A');
      enqueueLQ(pQueue, 'B');
      enqueueLQ(pQueue, 'C');
      enqueueLQ(pQueue, 'D');
      displayLinkedQueue(pQueue);
  	
      // 디큐 연산
      pNode = dequeueLQ(pQueue);
      if(pNode != NULL){
        printf("Dequeue Value - [%c]\n", pNode->data);
        free(pNode);
      }
      displayLinkedQueue(pQueue);
  	
      // peek 연산
      pNode = peekLQ(pQueue);
      if(pNode != NULL){
        printf("Peek Value - [%c]\n", pNode->data);
      }
      displayLinkedQueue(pQueue);
  
      // 인큐 연산
      enqueueLQ(pQueue, 'E');
  
      displayLinkedQueue(pQueue);
    }
    return 0;
  }
  ```


* **실행결과**

  ```c
  현재 노드 개수 : 4
  [0] - [A]
  [1] - [B]
  [2] - [C]
  [3] - [D]
  
  Dequeue Value - [A]
  
  현재 노드 개수 : 3
  [0] - [B]
  [1] - [C]
  [2] - [D]
  
  Peek Value - [B]
  
  현재 노드 개수 : 3
  [0] - [B]
  [1] - [C]
  [2] - [D]
  
  현재 노드 개수 : 4
  [0] - [B]
  [1] - [C]
  [2] - [D]
  [3] - [E]
  ```
