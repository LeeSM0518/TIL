# Chapter 04. 연결 리스트

# 1. 연결리스트

**연결 리스트(linked list)** 는 포인터를 이용하여 자료를 한 줄로 저장하는 자료구조 이다.

**연결 리스트의 저장 단위** : 자료를 저장하는 부분과 자료 사이를 연결하는 부분으로 구성된다.

> **연결 리스트의 노드 = 자료 + 연결 정보(링크)**



## 1.1. 노드의 구조

* **코드**

  ```java
  package chapter03_linked_list;
  
  public class Node<T> {
      T data;
      Node link;
  
      public Node (T data) {
          this.data = data;
      }
  }
  ```

  > 마지막 노드는 다른 노드들과 다르게 링크의 값으로 **null** 이 저장된다.



## 1.2. 연결 리스트의 구조

* **연결 리스트의 멤버 변수**

  * 헤더 노드(header node) : 다음 노드에 대한 링크를 저장하는 노드. 
  * 노드 개수를 저장하는 변수

* **코드**

  ```java
  package chapter03_linked_list;
  
  public class LinkedList<T> {
      private int currentCount;
      private Node<T> headerNode;
  }
  ```



# 2. 연결 리스트의 구현

## 2.1. 리스트 생성

* **코드**

  ```java
  package chapter03_linked_list;
  
  public class LinkedList<T> {
  
      private int currentCount;
      private Node<T> headerNode;
  
      private LinkedList() {
          this.headerNode = new Node<T>();      	// 헤더 노드
          this.currentCount = 0;									// 현재 노드 개수
      }
  }
  ```



## 2.2. 값 가져오기

*  **코드**

  ```java
  private T getLinkedListData(int position) {
    try {
      // 헤더 노드 저장
      Node<T> node = this.headerNode;
  
      // 찾는 위치까지 다음 링크
      for (int i = 0; i <= position; i++) {
        node = node.link;
      }
  
      // 찾은 위치의 데이터 반환
      return node.data;
  
    } catch (NullPointerException e) {
      System.out.println("자료가 없는 위치입니다.");
      return null;
    }
  }
  ```



## 2.3. 새로운 자료의 추가

### 2.3.1. 새로운 노드의 생성

연결 리스트에 새로운 자료를 추가하려면 먼저 새로운 노드를 생성해야 한다.



### 2.3.2. 다음 노드의 처리

새로 추가한 노드의 다음 노드로 **기존 위치의 노드** 를 지정한다.



### 2.3.3. 이전 노드의 처리

새로 추가한 노드의 이전 노드로 원래 추가하려는 위치의 노드의 이전 노드를 지정합니다.



* **연결리스트에 인덱스 position에 새로운 노드를 추가하려는 연산**

| 단계   | 구분               | 설명                                                         |
| ------ | ------------------ | ------------------------------------------------------------ |
| Step-A | 새로운 노드의 생성 | 추가하려는 자료를 노드로 새로 생성한다.                      |
| Step-B | 다음 노드의 처리   | 새로 추가한 노드의 다음 노드로 기존 position 위치의 노드를 지정 |
| Step-C | 이전 노드의 처리   | 위치가 (position - 1) 인 노드의 다음 노드로 새로 추가한 노드를 지정한다. |



* **코드**

  ```java
  private void addLinkedListData(int position, T data) {
    Node newNode = new Node<>(data);    // 새롭게 저장할 노드
    Node preNode = this.headerNode;     // 이전 노드
  
    // 이전 노드를 삽입할 노드 위치 전까지 이동
    for (int i = 0; i < position; i++) {
      preNode = preNode.link;
    }
  
    // 추가할 노드의 다음 노드 지정
    newNode.link = preNode.link;
    // 추가할 노드의 이전 노드 지정
    preNode.link = newNode;
  
    currentCount++;
  }
  ```



## 2.4. 기존 자료의 제거

### 2.4.1. 이전 노드의 처리

이전 노드의 다음 노드는 삭제 대상이 되는 노드이므로 이전 노드의 삭제 대상에 연결된 링크를 끊고 삭제 노드의 다음 링크로 이어준다.



### 2.4.2. 제거 노드의 메모리 해제

삭제 대상이 되는 노드 자체의 메모리를 해제하면 된다.



* **연결 리스트에서 인덱스 position에 있는 노드를 제거하는 연산**

| 단계   | 구분                    | 설명                                                         |
| ------ | ----------------------- | ------------------------------------------------------------ |
| Step-A | 이전 노드의 처리        | 인덱스가 (position - 1) 인 노드의 다음 노드로 인덱스가 (position + 1)인 노드를 지정한다. |
| Step-B | 제거 노드의 메모리 해제 | 제거하려는 노드의 메모리를 해제한다.                         |



* **코드**

  ```java
  private void removeLinkedListData(int position) {
    Node delNode;   // 지울 노드
    Node preNode;   // 지울 노드의 이전 노드
  
    preNode = this.headerNode;
  
    try {
      // 지울 노드 이전 까지 이동
      for(int i = 0; i < position; i++) {
        preNode = preNode.link;
      }
  
      // 지울 노드 저장
      delNode = preNode.link;
      // 이전 노드의 링크를 지울 노드의 다음 링크로 저장
      preNode.link = delNode.link;
  
      // 메모리 해제
      delNode = null;
  
      this.currentCount--;
    } catch (NullPointerException e) {
      System.out.println("자료를 제거할 수 없는 위치입니다.");
    }
  }
  ```

  

## 2.5. 기타 연산들

* **현재 노드 개수 가져오기**

  ```java
  public int getCurrentCount() {
    return currentCount;
  }
  ```

  

* **Main**

  ```java
  package chapter03_linked_list;
  
  public class Main {
      public static void main(String[] args) {
          LinkedList<Integer> linkedList = new LinkedList<>();
  
          linkedList.addLinkedListData(0, 10);
          linkedList.addLinkedListData(1, 20);
          linkedList.addLinkedListData(1, 30);
  
          System.out.println("위치: " + 0 + ", 값: " + linkedList.getLinkedListData(0));
          System.out.println("위치: " + 1 + ", 값: " + linkedList.getLinkedListData(1));
          System.out.println("위치: " + 2 + ", 값: " + linkedList.getLinkedListData(2));
          System.out.println();
  
          int value = linkedList.getLinkedListData(1);
          System.out.println("위치: " + 1 + ", 값: " + value);
          System.out.println();
  
          linkedList.removeLinkedListData(0);
  
          System.out.println("위치: " + 0 + ", 값: " + linkedList.getLinkedListData(0));
          System.out.println("위치: " + 1 + ", 값: " + linkedList.getLinkedListData(1));
      }
  }
  ```

  **실행 결과**

  ```
  위치: 0, 값: 10
  위치: 1, 값: 30
  위치: 2, 값: 20
  
  위치: 1, 값: 30
  
  위치: 0, 값: 30
  위치: 1, 값: 20
  ```

  

## 2.6. 배열 리스트와 연결 리스트의 장단점 비교

| 구분 | 구현 방식 | 순차적 저장을 구현한 방식 | 자료 접근 속도 | 구현 복잡도 | 기타 제약 사항      |
| ---- | --------- | ------------------------- | -------------- | ----------- | ------------------- |
| 배열 | 배열      | 물리적 저장 순서가 순차적 | 빠름 O(1)      | 낮음        | 최대 저장 개수 필요 |
| 연결 | 포인터    | 논리적 저장 순서가 순차적 | 느림 O(n)      | 높음        | -                   |

> 자료의 추가와 삭제가 빈번히 발생할 때에는 배열 리스트보다 연결 리스트로 구현하는 것이 바람직하다.



# 3. 연결 리스트 관련 함수들

* **순회(iteration) 함수, 연결(concatenate) 함수** 를 통해서 연속한 자료들에 좀 더 빠르게 접근할 수 있다.



## 3.1. 연결 리스트의 순회

**순회(iteration)** 란 연결 리스트의 노드를 차례대로 방문하는 것.

* **코드**

  ```java
  void displayList() {
    for (int i = 0; i < this.currentCount; i++) {
      System.out.println("[" + i + "], " + this.getLinkedListData(i) );
    }
  }
  ```

  > 자료의 개수가 n이라고 할때 시간 복잡도
  > $$
  > O(1 + 2 + 3 + ... + n) = O(n(n+1)/2) = O(n^2)
  > $$



* **순회를 수행하는 루프안에서 처리**

  ```java
  void iterateLinkedList() {
    int count = 0;
    Node node = this.headerNode.link;
  
    while (node != null) {
      System.out.println("[" + count + "], " + node.data);
      count++;
      node = node.link;
    }
    System.out.println("노드 개수: " + count);
  }
  ```

  > 시간 복잡도
  > $$
  > O(n)
  > $$



## 3.2. 연결 리스트의 연결

**연결(concatenate) 연산** : 두 개의 연결 리스트를 하나의 연결 리스트로 합치는 연산

* **코드**

  ```java
  void concatLinkedList(LinkedList listB) {
    if (listB != null) {
      // this 연결리스트의 마지막 노드를 가리킬 노드
      Node node = this.headerNode;
  
      // 마지막 노드를 가리킬 때까지 루프를 돈다.
      while (node != null && node.link != null) {
        node = node.link;
      }
  
      // this 의 마지막 노드의 다음 노드로 listB 의 첫 번째 노드로 설정한다.
      node.link = listB.headerNode.link;
      listB = null;
    }
  }
  ```



## 3.3. 프로그램 실행 화면

* **코드**

  ```java
  package chapter03_linked_list;
  
  public class Main {
      public static void main(String[] args) {
          LinkedList<Integer> linkedList = new LinkedList<>();
  
          linkedList.addLinkedListData(0, 10);
          linkedList.addLinkedListData(1, 20);
          linkedList.addLinkedListData(1, 30);
  
          System.out.println("위치: " + 0 + ", 값: " + linkedList.getLinkedListData(0));
          System.out.println("위치: " + 1 + ", 값: " + linkedList.getLinkedListData(1));
          System.out.println("위치: " + 2 + ", 값: " + linkedList.getLinkedListData(2));
          System.out.println();
  
          int value = linkedList.getLinkedListData(1);
          System.out.println("위치: " + 1 + ", 값: " + value);
          System.out.println();
  
          linkedList.removeLinkedListData(0);
  
          System.out.println("위치: " + 0 + ", 값: " + linkedList.getLinkedListData(0));
          System.out.println("위치: " + 1 + ", 값: " + linkedList.getLinkedListData(1));
          System.out.println();
  
          linkedList.displayList();
          System.out.println();
  
          linkedList.iterateLinkedList();
          System.out.println();
  
          LinkedList<Integer> linkedList2 = new LinkedList<>();
  
          linkedList2.addLinkedListData(0, 30);
          linkedList2.addLinkedListData(1, 40);
          linkedList2.addLinkedListData(2, 50);
  
          linkedList2.iterateLinkedList();
          System.out.println();
  
          linkedList.concatLinkedList(linkedList2);
          linkedList.iterateLinkedList();
      }
  }
  ```

  **실행 결과**

  ```
  위치: 0, 값: 10
  위치: 1, 값: 30
  위치: 2, 값: 20
  
  위치: 1, 값: 30
  
  위치: 0, 값: 30
  위치: 1, 값: 20
  
  [0], 30
  [1], 20
  
  [0], 30
  [1], 20
  노드 개수: 2
  
  [0], 30
  [1], 40
  [2], 50
  노드 개수: 3
  
  [0], 30
  [1], 20
  [2], 30
  [3], 40
  [4], 50
  노드 개수: 5
  ```