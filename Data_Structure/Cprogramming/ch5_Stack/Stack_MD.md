Data Structure
===

<br>

# 5장 Stack

## 노드의 구조
* **ArrayStackNodeType**
  ```C
  #include <stdio.h>
  #include <stdlib.h>
  #include <string.h>

  typedef struct ArrayStackNodeType{
    char data;  // 자료
  }ArrayStackNode;
  ```
  > 자료를 노드로 한 번 더 감싸는 **이유는 동시에 여러 개의 자료를 저장**할 수 있도록 하기 위해서 이다.

  <br>

## 배열 스택의 구조
* **ArrayStackType**
  ```C
  typedef struct ArrayStackType{
  int maxCount;   // 최대 자료 개수
  int currentCount;   // 현재 저장된 자료의 개수
  ArrayStackNode *pData;    // 자료를 저장하는 1차원 배열
  }ArrayStack;
  ```
  > 배열의 마지막 원소의 인덱스는 **(maxCount - 1)** 이다. <br>
  배열의 현재 저장된 노드 개수는 **(currentCount - 1)** 이다.

  <br>

## 스택의 생성
* **createArrayStack**
  ```C
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
  ```