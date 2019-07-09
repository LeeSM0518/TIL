# Chapter 06. 스택

* **스택 특성**
  * **LIFO (Last In First Out)** : 가장 나중에 추가한 자료를 가장 먼저 가져오는 특성
  * **후입선출**



# 1. 스택이란?

* 선형 자료구조이다.



# 2. 스택의 사용 시나리오

* **푸시(push) 연산** : 새로운 자료를 스택에 추가하는 과정
* **팝(pop) 연산** : 스택에서 기존에 저장된 자료를 제거하며 가져오는 과정
* **피크(peek) 연산** : 스택에서 기존에 저장된 자료를 제거하지 않고 단지 자료에 접근하여 값을 가져오는 과정



## 2.1. 푸시 연산

* **새로운 자료를 추가하는 연산이다.**
* 스택에서 **탑은** 항상 최신 자료를 가리키는 **끝이나 맨 위를** 지칭한다.
* **스택의 크기** 는 스택이 저장할 수 있는 **최대 자료의 개수** 이다.
* 스택의 크기를 초과하면 **넘침(overflow)** 가 발생한다.



## 2.2. 팝 연산

* **자료를 가져오는 연산이다.**
* 스택에서 **제거한 뒤 가져온다.**

* 아무런 자료가 없어서 반환하지 못하는 현상을 **부족(underflow)** 라고 한다.



## 2.3. 피크 연산

* **스택의 맨 위 자료를 반환한다**
* 기존 스택에서 자료를 **제거하지 않는다.**



# 3. 스택의 추상 자료형

|           기능           |    메소드     |     입력      |    출력    |            설명            |
| :----------------------: | :-----------: | :-----------: | :--------: | :------------------------: |
|        스택 생성         | createStuck() | 스택의 크기 n | 스택 stack |    빈 스택 stack을 생성    |
|        스택 삭제         | deleteStack() |  스택 stack   |    N/A     |    스택의 메모리를 해제    |
| 자료 추가 가능 여부 판단 |   isFull()    |  스택 stack   | true/false | 스택에 푸시 수행 가능 여부 |
|  빈 스택인지 여부 판단   |   isEmpty()   |  스택 stack   | true/false |     빈 스택인지를 반환     |
|           푸시           |    push()     |  스택 stack   | 성공/실패  |  스택의 맨 위에 자료 추가  |
|            팝            |     pop()     |   자료 data   |    자료    | 맨 위에 자료 제거 후 반환  |
|           피크           |    peek()     |  스택 stack   |    자료    |  스택의 맨 위에 자료 반환  |



# 4. 배열로 구현한 스택

스택을 생성하는 시점에 배열의 크기를 지정해야 한다.



## 4.1. 노드의 구조

```java
package chapter05_stack;

public class StackNode<T> {

    private T data;

    public StackNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
```



## 4.2. 배열 스택의 구조

```java
package chapter05_stack;

public class ArrayStack<T> {

    private int maxCount;
    private int currentCount = 0;
    private StackNode[] stackNodes;

}
```



## 4.3. 스택의 생성

```java
package chapter05_stack;

public class ArrayStack<T> {

    private int maxCount;
    private int currentCount = 0;
    private StackNode[] stackNodes;

    public ArrayStack(int maxCount) {
        this.maxCount = maxCount;
        stackNodes = new StackNode[maxCount];
    }

    public int getMaxCount() {
        return maxCount;
    }

    public int getCurrentCount() {
        return currentCount;
    }
}
```



## 4.4. 푸시 연산

```java
public void push(T data) {
  // 스택이 가득 차있는지 확인
  if (!isArrayStackFull()) {
    // 실제 자료 추가
    stackNodes[currentCount] = new StackNode<>(data);
    // 탑의 위치 변경
    currentCount++;
  } else {
    System.out.println("스택이 가득 찼습니다.");
  }
}

private boolean isArrayStackFull() {
  return currentCount == maxCount;
}
```



## 4.5. 팝과 피크 연산

* **팝 연산**

  ```java
  public StackNode pop() {
    StackNode node = null;
  
    // 스택이 비어 있는지 점검
    if (!isArrayStackEmpty()) {
      // 노드에 반환할 자료 대입
      node = stackNodes[currentCount - 1];
      stackNodes[currentCount - 1] = null;
  
      // 배열의 탑 위치 변경
      currentCount--;
    } else {
      System.out.println("스택이 비어있습니다.");
    }
  
    return node;
  }
  
  private boolean isArrayStackEmpty() {
    return currentCount == 0;
  }
  ```

* **피크 연산**

  ```java
  public StackNode peek() {
    StackNode node = null;
  
    if (!isArrayStackEmpty()) {
      node = stackNodes[currentCount - 1];
    } else {
      System.out.println("스택이 비어있습니다.");
    }
  
    return node;
  }
  ```



## 4.6. 그 외 연산

```java
public void displayArrayStack() {
  for (int i = maxCount - 1; i >= currentCount; i--) {
    System.out.println("[" + i + "] - [Empty]");
  }

  for (int i = currentCount - 1; i >= 0; i--) {
    System.out.println("[" + i + "] - [" + stackNodes[i].getData() + "]");
  }
}
```



**메인 함수**

```java
public static void main(String[] args) {
  ArrayStack<Integer> arrayStack = new ArrayStack<>(10);
  arrayStack.push(1);
  arrayStack.push(2);
  arrayStack.push(3);
  arrayStack.push(4);

  arrayStack.displayArrayStack();

  arrayStack.pop();

  arrayStack.displayArrayStack();

  System.out.println("peek() : " + arrayStack.peek().getData() + "\n");

  arrayStack.displayArrayStack();
}
```

**실행 결과**

```
[9] - [Empty]
[8] - [Empty]
[7] - [Empty]
[6] - [Empty]
[5] - [Empty]
[4] - [Empty]
[3] - [4]
[2] - [3]
[1] - [2]
[0] - [1]

[9] - [Empty]
[8] - [Empty]
[7] - [Empty]
[6] - [Empty]
[5] - [Empty]
[4] - [Empty]
[3] - [Empty]
[2] - [3]
[1] - [2]
[0] - [1]

peek() : 3

[9] - [Empty]
[8] - [Empty]
[7] - [Empty]
[6] - [Empty]
[5] - [Empty]
[4] - [Empty]
[3] - [Empty]
[2] - [3]
[1] - [2]
[0] - [1]
```



# 5. 연결로 구현한 연결 스택

* 노드 사이의 연결 정보를 이용해서 구현한 스택을 **연결 리스트로 구현한 스택** 이라고 한다.
* 연결 스택은 **스택의 크기를 미리 지정할 필요가 없다.**
* 배열 스택보다 다소 **복잡하다.**



## 5.1. 노드의 구조

* **자료 + 연결 정보(링크)**

  ```java
  package chapter05_stack;
  
  public class LinkedStackNode<T> extends StackNode{
      
      public LinkedStackNode link;
      
      public LinkedStackNode(T data) {
          this.data = data;
      }
  }
  ```



## 5.2. 연결 스택의 구조

```java
package chapter05_stack;

public class LinkedStack<T> {
    
    int currentCount;
    LinkedStackNode<T> top;
    
}
```

* **연결 스택의 탑 노드 접근에 걸리는 시간 복잡도**

| 연결 스택으로 구현한 경우 | 연결 리스트로 구현한 경우 |
| :-----------------------: | :-----------------------: |
|           O(1)            |           O(n)            |



## 5.3. 연결 스택의 추상 자료형

* **연결 스택이 스택의 추상 자료형과 다른 부분**

| 기능                         | 메소드        | 입력           | 출력           | 설명                        |
| ---------------------------- | ------------- | -------------- | -------------- | --------------------------- |
| 스택 생성                    | createStack() | -              | 스택 stack     | 빈 스택 stack을 생성        |
| ~~자료 추가 가능 여부 판단~~ | ~~isFull()~~  | ~~스택 stack~~ | ~~true/false~~ | ~~스택이 가득 찼는지 점검~~ |

  > isFull( ) 연산이 필요 없어진다.



## 5.4. 스택의 생성

```java
public LinkedStack(){
  currentCount = 0;
}
```



## 5.5. 푸시 연산

```java
public void push(T data) {
  // 새로운 노드 생성
  LinkedStackNode node = new LinkedStackNode<>(data);

  // 새로운 노드의 이전 노드를 기존 탑 노드로 지정
  node.link = top;

  // 탑 노드를 새로 추가한 노드로 변경
  top = node;

  currentCount++;
}
```



## 5.6. 팝과 피크 연산

* **팝 연산**

  ```java
  public LinkedStackNode pop() {
    LinkedStackNode node = null;
  
    // 스택이 비어 있는지 점검
    if (!isLinkedStackEmpty()) {
      // 기존 탑 노드를 반환 노드로 지정
      node = top;
      // 새로운 탑 노드를 기존 탑 노드의 이전 노드로 지정
      top = node.link;
      // 반환 노드의 이전 노드 정보를 초기화
      node.link = null;
      // 연결 리스트 노드 개수 1개 감소
      currentCount--;
    } else {
      System.out.println("스택이 비어 있습니다.");
    }
  
    return node;
  }
  
  private boolean isLinkedStackEmpty() {
    return currentCount == 0;
  }
  ```

* **피크 연산**

  ```java
  public LinkedStackNode peek() {
    LinkedStackNode node = null;
  
    if (!isLinkedStackEmpty()) {
      node = top;
    } else {
      System.out.println("스택이 비어 있습니다.");
    }
  
    return node;
  }
  ```



## 5.7. 그 외 연산

* **순회 연산**

  ```java
  public void displayLinkedStack() {
    if (!isLinkedStackEmpty()) {
      LinkedStackNode node = top;
      int i = 1;
      while (node != null) {
        System.out.println("[" + (currentCount - i++) + "] - [" + node.getData() + "]" );
        node = node.link;
      }
    } else {
      System.out.println("스택이 비어 있습니다.");
    }
  
    System.out.println();
  }
  ```

* **메인 함수**

  ```java
  public static void main(String[] args) {
    LinkedStack<String> linkedStack = new LinkedStack<>();
  
    linkedStack.push("A");
    linkedStack.push("B");
    linkedStack.push("C");
    linkedStack.push("D");
    linkedStack.push("E");
    linkedStack.displayLinkedStack();
  
    System.out.println("pop 값 : " + linkedStack.pop().getData());
    linkedStack.displayLinkedStack();
  
    System.out.println("peek 값 : " + linkedStack.peek().getData());
    linkedStack.displayLinkedStack();
  }
  ```

  **실행 결과**

  ```java
  [4] - [E]
  [3] - [D]
  [2] - [C]
  [1] - [B]
  [0] - [A]
  
  pop 값 : E
  [3] - [D]
  [2] - [C]
  [1] - [B]
  [0] - [A]
  
  peek 값 : D
  [3] - [D]
  [2] - [C]
  [1] - [B]
  [0] - [A]
  ```





