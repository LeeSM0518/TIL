# Chapter 05. 연결 리스트의 확장

* **단순 연결 리스트 이외에 리스트들**

  <img src="../capture/스크린샷 2019-05-13 오후 9.52.25.png" width="600">

* **단순 원형 연결 리스트**

  * 루프 구조
  * 연결이 단방향 이다.
  * **ex) 운영체제(OS) 에서 시간 할당 문제**

* **이중 연결 리스트**

  * 루프 구조
  * 연결이 양방향 이다.
  * **ex) 되돌리기 기능을 구현할 때**



# 1. 원형 연결 리스트

**원형 연결 리스트(circular linked list)**  : 리스트의 마지막 노드가 첫 번째 노드와 연결된 단순 연결 리스트



## 1.1. 노드의 구조

* **코드**

  ```java
  package chapter04_expansion_of_linked_list;
  
  public class Node<T> {
    
      T data;
      Node<T> link;
    
  }
  ```



## 1.2. 원형 연결 리스트의 구조

* **코드**

  단순 연결 리스트와 구조는 차이가 없다.

  ```java
  package chapter04_expansion_of_linked_list;
  
  public class CircularList<T> {
      
      int currentCount;
      Node<T> headerNode;
      
  }
  ```



## 1.3. 원형 연결 리스트 생성과 값 가져오기

* **코드**

  ```java
  ...
  	CircularList () {
      this.currentCount = 0;
      headerNode = new Node<>();
    }
  
    T getCircularListData(int position) {
      Node<T> node = this.headerNode;
  
      for (int i = 0; i <= position; i++) {
        node = node.link;
      }
  
      return node.data;
    }
  ...
  ```



## 1.4. 새로운 자료의 추가

* **자료를 추가하는 과정**

  1. 새로운 노드의 생성
  2. 다음 노드의 처리
  3. 이전 노드의 처리

  > 만약 추가할려는 노드가 첫 번째 노드이자 마지막 노드이라면 자기 자신과 연결을 해야한다.

* **코드**

  ```java
  void addCircularListData(int position, T data) {
    Node<T> newNode = new Node<>(data);
    Node<T> preNode = this.headerNode;
  
    // 추가할 위치의 이전까지 노드 이동
    for (int i = 0; i < position; i++) {
      preNode = preNode.link;
    }
  
    // 새로운 노드 추가
    newNode.link = preNode.link;
    preNode.link = newNode;
  
    // 만약 첫 번째 노드가 추가되었을 때, 다음 노드를 자기 자신으로 지정
    if (newNode.link == null) {
      newNode.link = newNode;
    }
  }
  ```



## 1.5. 기존 자료의 제거

**주의할 점** : 원형 연결 리스트에서는 마지막 남은 노드를 제거할 때 헤더 노드가 null을 가리키게 강제적으로 지정해야 한다.

* **코드**

  ```java
  void removeCircularListData(int position) {
    Node<T> preNode = this.headerNode;
  
    for (int i = 0; i < position; i++) {
      preNode = preNode.link;
    }
  
    Node<T> delNode = preNode.link;
    preNode.link = delNode.link;
  
    this.currentCount--;
  
    // 만약 현재 노드가 0개 있을 경우, 헤더 노드의 다음 노드를 null 로 지정해준다.
    if (this.currentCount == 0) this.headerNode.link = null;
  
    delNode = null;
  }
  ```



## 1.6. 기타 연산들

* **순회 연산**

  ```java
  void displayCircularList() {
    Node<T> node = this.headerNode.link;
  
    if (this.currentCount == 0) {
      System.out.println("자료가 없습니다.");
      return;
    }
  
    System.out.println("노드 개수: " + this.currentCount);
  
    for (int i = 0; i < this.currentCount; i++) {
      System.out.println("[" + i + "], " + node.data);
      node = node.link;
    }
  }
  ```

* **main 메소드**

  ```java
  public static void main(String[] args) {
    CircularList<String> circularList = new CircularList<>();
  
    circularList.addCircularListData(0, "A");
    circularList.displayCircularList();
    circularList.addCircularListData(0, "B");
    circularList.displayCircularList();
    circularList.addCircularListData(1, "C");
    circularList.displayCircularList();
  
    System.out.println();
  
    circularList.removeCircularListData(2);
    circularList.displayCircularList();
    circularList.removeCircularListData(1);
    circularList.displayCircularList();
    circularList.removeCircularListData(0);
    circularList.displayCircularList();
  }
  ```

  **실행 결과**

  ```
  노드 개수: 1
  [0], A
  노드 개수: 2
  [0], B
  [1], A
  노드 개수: 3
  [0], B
  [1], C
  [2], A
  
  노드 개수: 2
  [0], B
  [1], C
  노드 개수: 1
  [0], B
  자료가 없습니다.
  ```



# 2. 이중 연결 리스트

* **이중 연결 리스트(doubly linked list)** 는 노드 사이의 연결이 **양방향** 이다.
* 첫 번째 노드는 **이전 노드가 없기** 때문에 링크가 null 이다.
* 마지막 노드는 **다음 노드가 없기** 때문에 링크가 null 이다.
* **이전 노드로도** 한 번에 접근할 수 있는 장점이 있다
* 노드의 **추가적인 연결 정보를** 저장하기 때문에 메모리 공간을 더 많이 사용한다.



## 2.1. 노드의 구조

* **코드**

  ```java
  package chapter04_expansion_of_linked_list;
  
  public class DoublyListNode<T> {
  
      T data;
      DoublyListNode<T> lLink;		// 이전 노드
      DoublyListNode<T> rLink;		// 다음 노드
  
      DoublyListNode() {
      }
  
      DoublyListNode(T data) {
          this.data = data;
      }
  
      DoublyListNode(T data, DoublyListNode lLink, DoublyListNode rLink) {
          this.data = data;
          this.lLink = lLink;
          this.rLink = rLink;
      }
  }
  ```



## 2.2. 이중 연결 리스트의 구조

* **구조**

  * 헤더 노드의 다음 노드는 첫 번째 노드이고, 이전 노드는 마지막 노드이다.

* **코드**

  ```java
  public class DoublyList<T> {
      private int currentCount;
      private DoublyListNode<T> headerNode;
  }
  ```



## 2.3. 이중 연결 리스트 생성과 값 가져오기

* **리스트 생성 코드**

  ```java
  DoublyList() {
    this.currentCount = 0;
    headerNode = new DoublyListNode<>();
  
    // 처음 리스트를 만들때 headerNode 의 왼쪽과 오른쪽 노드를
    // 자기 자신을 가리키도록 한다.
    headerNode.lLink = this.headerNode;
    headerNode.rLink = this.headerNode;
  }
  ```

> **이중 연결 리스트의 속성**
>
> ```java
> node == node.lLink.rLink == node.rLink.lLink
> ```



* **리스트 값 요청 코드**

  ```java
  public T getData(final int position) {
    DoublyListNode<T> node = this.headerNode;
  
    for (int i = 0 ; i <= position; i++) {
      node = node.rLink;
    }
  
    return node.data;
  }
  ```



## 2.4. 새로운 자료의 추가

링크가 두 개이기 때문에 단순 연결 리스트 보다 추가 과정이 더욱 복잡하다.

### 2.4.1. 새로운 노드의 생성

* **코드**

  ```java
  public void addNode(final int position, final T data) {
    DoublyListNode newNode = new DoublyListNode<>(data);
    DoublyListNode preNode = this.headerNode;
    
    ...
  }
  ```



### 2.4.2. 새로 추가한 노드의 처리

* **코드**

  ```java
  ...
    for (int i = 0; i < position; i++) {
      preNode = preNode.lLink;
    }
  
    newNode.lLink = preNode;
    newNode.rLink = preNode.rLink;
  ...
  ```



### 2.4.3. 기존 노드의 처리

* **과정**
  1. 삽입할 위치의 이전 노드의 오른쪽 링크 처리
  2. 삽입할 위치의 다음 노드의 왼쪽 링크 처리

* **코드**

  ```java
  ...
  	preNode.rLink = newNode;            // 이전 노드 처리
    newNode.rLink.lLink = newNode;      // 다음 노드 처리
  
    this.currentCount++;
  }
  ```



## 2.5. 기존 자료의 제거

단순히 노드의 메모리를 해제하는 것뿐 아니라 제거 노드의 이전과 다음 노드의 링크를 재지정하여, 이중 연결 리스트의 연결 정보에 문제가 없도록 해줘야 한다.



### 2.5.1. 이전 노드와 다음 노드의 처리

* **코드**

  ```java
  public void removeNode(final int position) {
    DoublyListNode preNode = this.headerNode;
  
    for (int i = 0; i < position; i++) {
      preNode = preNode.rLink;
    }
  
    DoublyListNode delNode = preNode.rLink;     // 지울 노드 지정
    preNode.rLink = delNode.rLink;              // 이전 노드 처리
    delNode.rLink.lLink = preNode;              // 다음 노드 처리
  
    this.currentCount--;
  }
  ```



## 2.6. 기타 연산들

* **출력 연산 코드**

  ```java
  public void displayList() {
    DoublyListNode node = this.headerNode.rLink;
  
    if (this.currentCount == 0) {
      System.out.println("자료가 없습니다.");
      return;
    } else {
      System.out.println("노드 개수: " + this.currentCount);
  
      for (int i = 0; i < currentCount; i++) {
        System.out.println("[" + i + "], " + node.data);
        node = node.rLink;
      }
    }
  }
  ```

* **main 코드**

  ```java
  public static void main(String[] args) {
    DoublyList<Integer> list = new DoublyList<>();
  
    list.addNode(0, 1);
    list.addNode(1, 2);
    list.addNode(2, 3);
  
    list.displayList();
  
    list.removeNode(0);
  
    list.displayList();
  }
  ```

  **실행 결과**

  ```
  노드 개수: 3
  [0], 1
  [1], 2
  [2], 3
  노드 개수: 2
  [0], 2
  [1], 3
  ```



# 3. 연결 리스트의 응용: 다항식

```java
package chapter04_expansion_of_linked_list.polylist;

import chapter03_linked_list.LinkedList;
import chapter03_linked_list.Node;

import java.util.Scanner;

public class PolyList extends LinkedList {

    public void addNode(final int position, final double coef, final int degree) {
        Node newNode = new PolyNode(coef, degree);
        Node preNode = this.headerNode;

        addProcess(preNode, newNode, position);
    }

    @Override
    public void iterateList() {
        PolyNode node = (PolyNode) this.headerNode.link;

        while (node != null) {
            System.out.print(node.getCoef() + "x^" + node.getDegree());
            if (node.link != null) {
                System.out.print(" + ");
            }
            node = (PolyNode) node.link;
        }
        System.out.println();
    }

    static public PolyList calculatePolynomials(final PolyList[] polyLists) {
        PolyList resultList = new PolyList();

        PolyNode nodeA = (PolyNode) polyLists[0].headerNode.link;
        PolyNode nodeB = (PolyNode) polyLists[1].headerNode.link;

        int j = 0;
        double coefSum;

        while (nodeA != null && nodeB != null) {
            int degreeA = nodeA.getDegree();
            int degreeB = nodeB.getDegree();

            if (degreeA > degreeB) {
                resultList.addNode(j++, nodeA.getCoef(), degreeA);
                nodeA = (PolyNode) nodeA.link;
            } else if (degreeA == degreeB) {
                coefSum = nodeA.getCoef() + nodeB.getCoef();
                resultList.addNode(j++, coefSum, degreeA);
                nodeA = (PolyNode) nodeA.link;
                nodeB = (PolyNode) nodeB.link;
            } else {
                resultList.addNode(j++, nodeB.getCoef(), degreeB);
                nodeB = (PolyNode) nodeB.link;
            }
        }

        while (nodeA != null) {
            resultList.addNode(j++, nodeA.getCoef(), nodeA.getDegree());
            nodeA = (PolyNode) nodeA.link;
        }

        while (nodeB != null) {
            resultList.addNode(j++, nodeB.getCoef(), nodeB.getDegree());
            nodeB = (PolyNode) nodeB.link;
        }

        return resultList;
    }

    static public PolyList[] inputPolynomial() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("다항식 연산 입력 >> ");
        String poly = scanner.nextLine();
        String[] polyArr = poly.split(" ");

        PolyList[] polyLists = PolyList.insertPoly(polyArr);

        return polyLists;
    }

    static private PolyList[] insertPoly(final String[] polyArr) {

        PolyList frontList = new PolyList();
        PolyList backList = new PolyList();
        PolyList list = frontList;

        for (int i = 0, j = 0; i < polyArr.length ; i += 4, j++) {

            // 다항식 시작이 "-" 일 경우
            if (i == 0 && polyArr[i].equals("-")) {
                polyArr[i+2] = "-".concat(polyArr[i+2]);
                i += 1;
            }

            // 항이 음수일 경우
            if (i > 0 && polyArr[i-1].equals("-")) {
                polyArr[i] = "-".concat(polyArr[i]);
            }


            list.addNode(j, Double.parseDouble(polyArr[i]), Integer.parseInt(polyArr[i+2]));
            try {
                // 같은 차수일 경
                if (polyArr[i+2].equals(polyArr[i+6])) {
                    int sum = Integer.parseInt(polyArr[i]) + Integer.parseInt(polyArr[i+4]);
                    polyArr[i+4] = String.valueOf(sum);
                    list.removeData(j);
                    j--;
                }

                if (Integer.parseInt(polyArr[i+2]) < Integer.parseInt(polyArr[i+6])) {
                    list = backList;
                    j = -1;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }

        PolyList[] lists = new PolyList[] {frontList, backList};

        return lists;
    }

    public static void main(String[] args) {
        PolyList[] polyList;

        while (true) {
            polyList = PolyList.inputPolynomial();

            PolyList result = PolyList.calculatePolynomials(polyList);

            result.iterateList();
        }

    }

}
```

**실행결과**

```
다항식 연산 입력 >> 4 x^ 4 + 3 x^ 4
7.0x^4
다항식 연산 입력 >> - 1 x^ 3 + 1 x^ 2 + 1 x^ 0 + 2 x^ 4 + 3 x^ 3 + 2 x^ 0
2.0x^4 + 2.0x^3 + 1.0x^2 + 3.0x^0
```

