# Chapter 07. 큐

* **큐의 특징**
  * **FIFO(First-In-First-Out)** : 선입선출이라고 하며, 가장 먼저 들어간 자료가 가장 먼저 나온다.



# 1. 큐란?

* 자료를 차례대로 저장하는 선형 자료구조 이다.
* **인큐(enqueue) 연산** : 큐는 새로운 자료를 **맨 뒤인 리어(rear)** 에 추가한다.
* **디큐(dequeue) 연산** : 자료를 가져올때는 **맨 앞인 프론트(front)** 에서 자료를 제거하여 반환한다.
* **피크(peek) 연산** : 자료를 제거하지 않고 해당 자료에 접근만 한다.



# 2. 큐의 추상 자료형

| 기능                     | 메소드        | 입력        | 출력           | 설명                                         |
| ------------------------ | ------------- | ----------- | -------------- | -------------------------------------------- |
| 큐 생성                  | createQueue() | 큐의 크기 n | 큐 q           | 빈 큐 q를 생성                               |
| 큐 삭제                  | deleteQueue() | 큐 q        | N/A            | 큐의 메모리를 해제                           |
| 자료 추가 가능 여부 판단 | isFull()      | 큐 q        | True/False     | 큐가 가득 차 있는지 점검                     |
| 빈 큐인지 판단           | isEmpty()     | 큐 q        | True/False     | 큐가 비어 있는지 점검                        |
| 인큐                     | enqueue()     | 큐 q        | 성공/실패 여부 | 큐의 맨 뒤에 새로운 자료를 추가              |
| 디큐                     | dequeue()     | 자료 data   | 자료           | 큐의 맨 앞에 있는 자료를 제거한 뒤 이를 반환 |
| 피크                     | peek()        | 큐 p        | 자료           | 큐의 맨 앞에 있는 자료를 반환(제거X)         |



# 3. 배열로 구현한 선형 큐

## 3.1. 노드의 구조

```java
package chapter07_queue;

public class Node<T> {
    T data;

    public Node(T data) {
        this.data = data;
    }
}
```



## 3.2. 배열 선형 큐의 구조

```java
package chapter07_queue;

public class ArrayQueue {

    private int maxCount;       // 최대 자료 개수
    private int currentCount;   // 현재 자료 개수
    private int front;          // 프런트 위치
    private int rear;           // 리어 위치
    private Node[] data;       // 데이터 배열

}
```



## 3.3. 큐의 생성

```java
package chapter07_queue;

public class ArrayQueue {

    private int maxCount;       // 최대 자료 개수
    private int currentCount;   // 현재 자료 개수
    private int front;          // 프런트 위치
    private int rear;           // 리어 위치
    private Node[] data;       // 데이터 배열

    public ArrayQueue(final int maxCount) {
        this.maxCount = maxCount;
        currentCount = 0;

        // 프런트와 리어 위치 초기화
        front = -1;
        rear = -1;

        data = new Node[maxCount];
    }

}
```



## 3.4. 인큐 연산

* **핵심 : ** rear 위치
  * 새로운 노드를 추가하면 이 노드의 위치 인덱스는 **rear + 1** 이다.
  * 처음의 rear 값은 **-1** 이다. (큐가 빈상태)
  * rear의 최대 값 : **maxCount - 1**
  * CurrentCount의 최대 값 : **maxCount**

* **코드**

  ```java
  public <T> void enqueue(final T data) {
    if (!isFull()) {                        // 큐가 가득 차 있는지 점검
      rear++;                             // 리어 위치 변경
      nodes[rear] = new Node<>(data);     // 새로운 자료 추가
      currentCount++;                     // 현재 자료 개수 증가
    } else {
      System.out.println("큐가 가득 찼습니다.");
    }
  }
  
  private boolean isFull() {
    return (currentCount == maxCount) || (rear == maxCount - 1);
  }
  ```

  > rear가 maxCount - 1 이 되면 프론트 앞에 빈 노드가 존재해도 더 이상 자료를 추가할 수 없는 메모리 낭비 문제가 발생한다.



## 3.5. 디큐와 피크 연산

* **front의 값은 프런트 노드의 이전 위치 인덱스 이다.**

* **디큐 연산 코드**

  ```java
  public Node dequeue() {
    Node node = null;
  
    if (!isEmpty()) {           // 큐가 비어 있는지 점검
      front++;                // 프론트 위치 변경
      node = nodes[front];    // 반환 노드 복사
      currentCount--;         // 현재 노드 개수 감소
    } else {
      System.out.println("큐가 비어 있습니다.");
    }
  
    return node;
  }
  
  private boolean isEmpty() {
    return currentCount == 0;
  }
  ```

* **피크 연산 코드**

  ```java
  public Node peek() {
    Node node = null;
  
    if (!isEmpty()) {
      node = nodes[front+1];
    } else {
      System.out.println("큐가 비어 있습니다.");
    }
  
    return node;
  }
  ```



## 3.6. 기타 연산들

* **순회 연산**

  ```java
  public void displayQueue() {
    if (!isEmpty()) {
      for (int i = front + 1; i <= rear; i++) {
        System.out.println("[ " + i + " ] - [ " + nodes[i].data + " ]");
      }
    } else {
      System.out.println("큐가 비어 있습니다.");
    }
  }
  ```

* **main 함수**

  ```java
  public static void main(String[] args) {
    ArrayQueue arrayQueue = new ArrayQueue(10);
    arrayQueue.enqueue("A");
    arrayQueue.enqueue("B");
    arrayQueue.enqueue("C");
    arrayQueue.enqueue("D");
    arrayQueue.displayQueue();
  
    arrayQueue.dequeue();
    arrayQueue.displayQueue();
  
    System.out.println("peek: " + arrayQueue.peek().data);
    arrayQueue.displayQueue();
  }
  ```

  **실행 결과**

  ```
  [ 0 ] - [ A ]
  [ 1 ] - [ B ]
  [ 2 ] - [ C ]
  [ 3 ] - [ D ]
  
  [ 1 ] - [ B ]
  [ 2 ] - [ C ]
  [ 3 ] - [ D ]
  
  peek: B
  [ 1 ] - [ B ]
  [ 2 ] - [ C ]
  [ 3 ] - [ D ]
  ```



# 4. 배열로 구현한 원형 큐

* 배열 선형 큐는 배열의 맨 앞에 빈 노드가 있어도 **rear 위치가 maxCount -1** 이면 새로운 노드를 추가하려 할 때 **오버플로우 현상이 발생한다.**
* 배열 원형 큐는 배열의 마지막 노드와 배열의 첫 번째 노드를 **논리적으로 연결해** 배열을 연속하게 한다.
  * **논리적 연결 방법 : rear = (rear + 1) % maxCount**



## 4.1. 배열 원형 큐 구조

```java
public class CircleQueue extends ArrayQueue {

    public CircleQueue(final int maxCount) {
        super(maxCount);
    }

}
```



## 4.2. 인큐 연산

````java
@Override
public <T> void enqueue(final T data) {
  if (!isFull()) {
    rear = (rear + 1) % maxCount;       // 논리적으로 마지막과 처음을 연결
    nodes[rear] = new Node<>(data);
    currentCount++;
  } else {
    System.out.println("큐가 가득 찼습니다.");
  }
}

@Override
boolean isFull() {
  return currentCount == maxCount;
}
````



## 4.3. 디큐 연산

```java
@Override
public Node dequeue() {
  Node node = null;

  if (!isEmpty()) {
    front = (front + 1) % maxCount;     // 논리적으로 마지막과 처음을 연결
    node = nodes[front];
    currentCount--;
  } else {
    System.out.println("큐가 비어 있습니다.");
  }

  return node;
}
```



## 4.4. 기타 연산들

* **순회 연산**

  ```java
  @Override
  public void displayQueue() {
    if (!isEmpty()) {
      for (int i = front + 1; i <= front + currentCount; i++) {
        int position = i % maxCount;
        System.out.println("[ " + position + " ] - [ " + nodes[position].data + " ]" );
      }
    } else {
      System.out.println("큐가 비어 있습니다.");
    }
    System.out.println();
  }
  ```

* **main 함수**

  ```java
  public static void main(String[] args) {
    CircularQueue circularQueue = new CircularQueue(4);
  
    circularQueue.enqueue("A");
    circularQueue.enqueue("B");
    circularQueue.enqueue("C");
    circularQueue.enqueue("D");
    circularQueue.displayQueue();
  
    circularQueue.dequeue();
    circularQueue.displayQueue();
  
    circularQueue.enqueue("E");
    circularQueue.displayQueue();
  
    circularQueue.dequeue();
    circularQueue.displayQueue();
  
    circularQueue.enqueue("F");
    circularQueue.displayQueue();
  }
  ```

  **실행 결과**

  ```java
  [ 0 ] - [ A ]
  [ 1 ] - [ B ]
  [ 2 ] - [ C ]
  [ 3 ] - [ D ]
  
  [ 1 ] - [ B ]
  [ 2 ] - [ C ]
  [ 3 ] - [ D ]
  
  [ 1 ] - [ B ]
  [ 2 ] - [ C ]
  [ 3 ] - [ D ]
  [ 0 ] - [ E ]
  
  [ 2 ] - [ C ]
  [ 3 ] - [ D ]
  [ 0 ] - [ E ]
  
  [ 2 ] - [ C ]
  [ 3 ] - [ D ]
  [ 0 ] - [ E ]
  [ 1 ] - [ F ]
  ```



# 5. 포인터로 구현한 큐

## 5.1. 연결 큐의 구조

* **노드 구조**

  ```java
  public class LinkedNode<T> extends Node {
      
      public LinkedNode link;
  
      public LinkedNode(T data) {
          super(data);
      }
  }
  ```

* **큐의 구조**

  ```java
  public class LinkedQueue {
      
      private int currentCount;       // 현재 노드의 개수
      private LinkedNode front;       // Front 노드
      private LinkedNode rear;        // rear 노드
      
  }
  ```

  

## 5.2. 연결 큐가 추상 자료형과 다른 점

| 기능                         | 메소드        | 입력         | 출력           | 설명                         |
| ---------------------------- | ------------- | ------------ | -------------- | ---------------------------- |
| 스택 생성                    | createQueue() | -            | 큐 queue       | 빈 큐 queue를 생성           |
| ~~자료 추가 가능 여부 판단~~ | ~~isFull()~~  | ~~큐 queue~~ | ~~True/False~~ | ~~큐가 가득 차 있는지 점검~~ |



## 5.3. 큐의 생성

```java
package chapter07_queue;

public class LinkedQueue {

    private int currentCount;       // 현재 노드의 개수
    private LinkedNode front;       // Front 노드
    private LinkedNode rear;        // rear 노드

    public LinkedQueue() {
      	front = null;
      	rear = null;
        currentCount = 0;
    }

}
```



## 5.4. 인큐 연산

### 5.4.1. 빈 큐가 아닐 때 인큐 연산

* **인큐 과정**
  1. 새로운 노드 생성
  2. 기존 리어의 다음 노드로 새로 추가한 노드를 지정
  3. 큐의 리어 노드 링크의 변경



### 5.4.2. 빈 큐일 때 인큐 연산

* 빈 큐일 때 인큐를 하면 새로 추가한 노드는 유일한 노드이기 때문에 **프런트 이면서 동시에 리어 이다.**



### 5.4.3. 인큐 연산 코드

```java
public <T> void enqueue(T data) {
  LinkedNode node = new LinkedNode<>(data, null);

  if (!isEmpty()) {
    rear.link = node;
    rear = node;
  } else {
    front = node;
    rear = node;
  }

  currentCount++;
}

boolean isEmpty() {
  return currentCount == 0;
}
```



## 5.5. 디큐와 피크 연산

### 5.5.1. 일반적일 때 디큐 연산

* **디큐 과정**
  1. 큐의 프론트 노드 재지정
  2. 반환 노드의 링크 초기화



### 5.5.2. 노드가 한 개일 때 디큐 연산

* **디큐 과정**
  1. 큐의 프론트를 null로 지정
  2. 큐의 리어를 null로 지정



### 5.5.3. 디큐 연산 코드

```java
public Node dequeue() {
  LinkedNode node;

  if (!isEmpty()) {
    node = front;
    front = front.link;
    node.link = null;

    currentCount--;
  } else {
    System.out.println("큐가 비어 있습니다.");
    return null;
  }

  if (isEmpty()) rear = null;

  return node;
}
```



### 5.5.4. 피크 연산

```java
public LinkedNode peek() {
  LinkedNode node = null;

  if (!isEmpty()) {
    node = front;
  } else {
    System.out.println("큐가 비어 있습니다.");
  }

  return node;
}
```



## 5.6. 기타 연산

* **순회 연산**

  ```java
  public void displayQueue() {
    LinkedNode node;
  
    if (!isEmpty()) {
      int i = 0;
      System.out.println("현재 노드 개수: " + currentCount);
      node = front;
      while (node != null) {
        System.out.println("[" + i++ + "] - [" + node.data + "]");
        node = node.link;
      }
    } else {
      System.out.println("큐가 비어 있습니다.");
    }
  }
  ```

* **main 메소드**

  ```java
  public static void main(String[] args) {
    LinkedQueue linkedQueue = new LinkedQueue();
  
    linkedQueue.enqueue("A");
    linkedQueue.enqueue("B");
    linkedQueue.enqueue("C");
    linkedQueue.enqueue("D");
    linkedQueue.displayQueue();
  
    System.out.println("dequeue: " + linkedQueue.dequeue().data);
    linkedQueue.displayQueue();
  
    System.out.println("peek: " + linkedQueue.peek().data);
    linkedQueue.displayQueue();
  
    linkedQueue.enqueue("E");
    linkedQueue.displayQueue();
  }
  ```

  **실행 결과**

  ```java
  현재 노드 개수: 4
  [0] - [A]
  [1] - [B]
  [2] - [C]
  [3] - [D]
  
  dequeue: A
  현재 노드 개수: 3
  [0] - [B]
  [1] - [C]
  [2] - [D]
  
  peek: B
  현재 노드 개수: 3
  [0] - [B]
  [1] - [C]
  [2] - [D]
  
  현재 노드 개수: 4
  [0] - [B]
  [1] - [C]
  [2] - [D]
  [3] - [E]
  ```

  