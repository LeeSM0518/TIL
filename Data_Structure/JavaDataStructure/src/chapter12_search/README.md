# Chapter 11. 검색

찾고자 하는 자료를 다른 자료들과 구별시켜주는 키를 **검색 키(search key)라고** 한다.

**검색(search)은** 이러한 검색 키를 가지는 내가 원하는 자료를 찾는 것을 말한다.



**목차**

1. 순차 검색
2. 색인 순차 검색
3. 이진 검색 트리



# 1. 순차 검색

**순차 검색(sequential search)** : 일렬(sequential)로 저장된 자료들을 차례대로 비교하는 검색 방법



## 1.1. 자료가 미리 정렬되지 않은 경우

찾으려는 자료가 실제로 있는지는 마지막 자료까지 비교해 보아야 알 수 있다.

* **검색 성능**
  * 자료 개수 : n
  * 평균 시간 복잡도 : **O(n)**



## 1.2. 자료가 미리 정렬된 경우

이 경우도 첫 번째 자료부터 차례대로 키 값을 비교하지만, 검색 키 값보다 값이 작은 자료가 나온다면 검색이 실패한 경우가 된다.

* **검색 성능**
  * 자료 개수 : n
  * 평균 시간 복잡도 : **O(n)**



## 1.3. 순차 검색의 구현

**정렬되지 않은 배열을 대상으로 순차 검색**

```java
public boolean notSorted(int[] values, int key) {
  int i;
  for (i = 0; i < values.length && values[i] != key; i++);

  return i < values.length;
}
```

**정렬된 배열을 대상으로 순차 검색**

```java
public boolean sorted(int[] values, int key) {
  int i;
  for (i = 0; i < values.length && values[i] < key; i++);

  return i < values.length && values[i] == key;
}
```



# 2. 색인 순차 검색

**색인 순차 검색(index sequential search)** : 자료가 미리 정렬된 경우에 색인 테이블(index table)을 이용하여 검색의 효율을 높이는 검색 방법



## 2.1. 색인과 색인 테이블

**색인(index)** : 특정 키 값에 대한 해당 자료의 위치. 보통 배열에서 원소의 위치 혹은 변수의 주솟값

**색인 테이블(index table)** : 색인들이 모여 있는 테이블

* 기존 자료들이 오름차순으로 정렬되어 있으면 기존 자료들을 가리키는 색인 테이블 또한 **오름차순으로 정렬되어야** 한다.
* 테이블이라 부르는 이유는 저장되는 자료가 **'키-값'** 처럼 **쌍(pair)** 을 이루는 자료이기 때문이다.



## 2.2. 색인 테이블의 사용

### 2.2.1. 색인 테이블 검색

* 색인 순차 검색에서는 먼저 색인 테이블을 검색 키로 검색한다.
* 여기서 색인 테이블에서의 검색은 **검색 범위를 찾기 위한** 검색이다.
* 즉, 정확한 키 값을 찾으려는 것이 아니라 **검색 키값보다 큰 색인을 만나는 것이** 목적이다.



### 2.2.2. 자료 검색

* 앞서 색인 테이블 검색 결과로 얻은 '검색 범위'에서 실제 자료를 찾는 단계이다.
* 색인 테이블 검색은 순차 검색에서 **검색 범위를 감소시킨다.**



### 2.2.3. 검색 성능

* **자료 개수** : n
* **색인 테이블의 인덱스 개수** : m
* **시간 복잡도** : O(m + n/m)



## 2.3. 색인 순차 검색의 구현

* **색인 클래스**

  ```java
  package chapter12_search;
  
  public class IndexType {
  
    int position;
    int key;
  
    public IndexType(int position, int key) {
      this.position = position;
      this.key = key;
    }
  
  }
  ```

* **색인 검색 클래스**

  ```java
  package chapter12_search;
  
  public class IndexSequentialSearch {
  
    private int[] values;							// 자료 배열
    private IndexType[] indexTable;		// 색인 테이블
    private int indexSize;						// 색인 테이블 크기
  
  }
  ```



### 2.3.1. 색인 테이블의 생성

```java
public IndexSequentialSearch(int[] values, int indexSize) {
  this.values = values;
  this.indexSize = indexSize;
  indexTable = new IndexType[indexSize];

  // 색인 1개가 다루어야 하는 평균적인 자료의 개수 저장
  int ratio = (values.length % indexSize == 0) ? 
    values.length / indexSize : values.length / indexSize + 1;

  // 색인 테이블 저장
  for (int i = 0; i < indexSize; i++) {
    indexTable[i] = new IndexType(i * ratio, values[i * ratio]);
  }
}
```



### 2.3.2. 색인 순차 검색 함수

```java
public boolean search(int key) {
  boolean success = false;

  // 색인 테이블에서 값 존재 여부 확인
  if (key >= values[0] && key <= values[values.length - 1]) {
    int i, start = 0, end = 0;

    for (i = 0; i < indexSize; i++) {
      if (indexTable[i].key > key) break;
    }

    if (i < indexSize) {
      start = indexTable[i - 1].position;
      end = indexTable[i].position - 1;
    } else {
      start = indexTable[i - 1].position;
      end = values.length - 1;
    }

    success = rangeSearch(start, end, key);
  }

  return success;
}

private boolean rangeSearch(int start, int end, int key) {
  int i;

  for (i = start; i <= end && values[i] < key; i++);

  return i <= end && values[i] == key;
}
```



### 2.3.3. 그 외 함수들

* **자료 배열 출력**

  ```java
  public void showArray() {
    System.out.println("자료 배열");
    System.out.println("================");
    for(int i = 0; i < values.length; i++) {
      System.out.println(i + ", " + values[i]);
    }
    System.out.println();
  }
  ```

* **색인 테이블 출력**

  ```java
  public void showIndexTable() {
    System.out.println("인덱스 테이블");
    System.out.println("위치, 키");
    System.out.println("================");
    for (IndexType index : indexTable) {
      System.out.println(index.position + ", " + index.key);
    }
    System.out.println();
  }
  ```

* **main 메소드**

  ```java
  public static void main(String[] args) {
    int[] values = {10, 20, 50, 60, 70, 80};
    IndexSequentialSearch indexSequentialSearch = new IndexSequentialSearch(values, 3);
  
    indexSequentialSearch.showArray();
    indexSequentialSearch.showIndexTable();
  
    System.out.println("60 검색 = " + indexSequentialSearch.search(60));
    System.out.println("65 검색 = " + indexSequentialSearch.search(65));
  }
  ```

* **실행 결과**

  ```
  자료 배열
  ================
  0, 10
  1, 20
  2, 50
  3, 60
  4, 70
  5, 80
  
  인덱스 테이블
  위치, 키
  ================
  0, 10
  2, 50
  4, 70
  
  60 검색 = true
  65 검색 = false
  ```



### 2.3.4. 검색 성능

**평균 시간 복잡도** : O(m + n/m)

* 색인 순차 검색의 성능 향상은 **최적의 m과 n의** 결정에 있다.
* 너무 큰 m 혹은 적절하지 않은 m과 n의 비율은 성능을 떨어뜨리게 된다.



**사용 환경**

* 원 자료에 새로운 자료를 삽입하거나 제거되었을 때, 원 자료를 가리키는 기존에 생성된 **색인 테이블 또한 변경해 주어야 한다.**
* 그래서 색인 순차 검색은 한번 자료가 추가되면 이후에 **자료의 변경이 없는 경우에 사용된다.**



# 3. 이진 검색

**이진 검색(binary search)** : 미리 정렬된 자료를 대상으로 검색 범위를 반으로 감소시켜 가면서 검색 키를 찾는 검색 방법이다.



## 3.1. 이진 검색의 예

### 3.1.1. 중간 위치 구하기

이진 검색은 검색 범위를 절반씩 줄여가는 검색 방법이므로 검색 범위의 중간을 정해야 한다.

* **중간 위치** : (하한) (시작 위치 + 마지막 위치) / 2
  * 2로 나누었을 때 생긴 소수점은 버린다.



### 3.1.2. 중간 위치에 있는 자료를 검색 키와 비교

중간 위치에 있는 자료가 검색 키 값보다 크면 더 **큰 쪽의 범위를 검색 범위로** 지정하고, 작으면 **더 작은 쪽의 범위를 검색 범위로** 지정한다.



### 3.1.3. 다시 중간 위치 구하기

새롭게 정의된 검색 범위에서 다시 중간 위치를 구하고 검색 키와 비교하는 규칙을 반복하며 검색을 수행한다.



## 3.2. 이진 검색이 실패로 끝난 예

더 이상 검색 범위가 남지 않았을 때, 즉 더 이상 비교할 자료가 없을 때에는 검색 결과가 실패로 끝난다.



## 3.3. 의사 코드

* **입력 파라미터**
  * **values** : 자료의 배열
  * **start** : 배열의 시작 위치
  * **end** : 배열의 마지막 위치
  * **key** : 검색 키

```c
binarySearchRecusive(values, start, end, key) {
  result <- FAIL;
  
  if (start <= end) {										// 재귀 호출의 종료 조건
    middle <- (start + end) / 2;
    if (key == values[middle]) {				// 검색 성공
      result <- middle;
      break;
    } 
    else if (key < values[middle]) {		// 왼쪽 범위 검색
      result <- binarySearchRecursive(values, start, middle - 1, key);
    } 
    else {															// 오른쪽 범위 검색
      result <- binarySearchRecursive(values, middle + 1, end, key);
    }
  }
  return result;
}
```



## 3.4. 이진 검색의 구현

* **이진 검색 코드**

```java
package chapter12_search;

public class BinarySearch {

  public boolean search(int[] values, int key) {
    return recursiveSearch(values, 0, values.length, key);
  }

  private boolean recursiveSearch(int[] values, int start, int end, int key) {
    boolean result = false;

    if (start <= end) {
      int middle = (start + end) / 2;
      if (key == values[middle]) {
        result = true;
      } else if (key < values[middle]) {
        result = recursiveSearch(values, start, middle - 1, key);
      } else {
        result = recursiveSearch(values, middle + 1, end, key);
      }
    }

    return result;
  }

}
```

* **main 메소드**

```java
public static void main(String[] args) {
  BinarySearch binarySearch = new BinarySearch();
  int[] array = {10, 20, 50, 60, 70, 80};

  System.out.println("60 검색 : " + binarySearch.search(array, 60));
  System.out.println("65 검색 : " + binarySearch.search(array, 65));
}
```

* **실행 결과**

```
60 검색 : true
65 검색 : false
```



# 4. 이진 검색 트리

* 검색을 위한 이진 트리이다.



**이진 검색과 이진 검색 트리 비교**

| 분류   | 이진 검색          | 이진 검색 트리     |
| ------ | ------------------ | ------------------ |
| 구분   | 알고리즘의 한 종류 | 자료구조의 한 종류 |
| 대상   | 정렬된 배열        | 이진 트리          |
| 공통점 | 검색               | 검색               |



## 4.1. 이진 검색 트리의 개념

**이진 검색 트리란?** 추가 적인 제약 사항이 있는 이진 트리(binary tree)

<img src="../capture/스크린샷 2019-07-04 오후 4.40.18.png" width=500>



**이진 검색 트리의 제약 사항**

1. 왼쪽 서브트리에 있는 모든 노드의 키는 루트의 키보다 작다.
2. 오른쪽 서브트리에 있는 모든 노드의 키는 루트의 키보다 크다.
3. 왼쪽 서브트리와 오른쪽 서브트리도 모두 이진 검색 트리이다.



## 4.2. 이진 검색 트리의 추상 자료형

| 기능                | 이름                  | Input                  | Output         | 설명                         |
| ------------------- | --------------------- | ---------------------- | -------------- | ---------------------------- |
| 이진 검색 트리 생성 | createBinSearchTree() | -                      | 이진 검색 트리 | 트리 생성                    |
| 자료 추가           | insertData()          | 이진 검색 트리, 키, 값 | 성공 여부      | 새로운 노드 추가             |
| 자료 제거           | deleteData()          | 이진 검색 트리, 키     | 성공 여부      | 기존 노드 제거               |
| 자료 검색           | search()              | 이진 검색 트리, 키     | 트리 노드      | 검색 키에 해당하는 노드 반환 |



## 4.3. 이진 검색 트리에서의 검색 연산

* **이진 검색 트리의 특성**

  * 왼쪽 서브트리의 모든 키 값이 현재 노드의 키 값보다 작다.
  * 오른쪽 서브트리의 모든 키 값이 현재 노등의 키 값보다 크다.

* **검색 연산**

  1. '검색 키' 값과 '현재 노드의 키' 값이 같은 경우: **검색 종료(성공)**
  2. '검색 키' 값이 '현재 노드의 키' 값보다 작은 경우: **왼쪽 서브트리로 이동**
  3. '검색 키' 값이 '현재 노드의 키' 값보다 큰 경우: **오른쪽 서브트리로 이동**

* **의사 코드(pseudo code)**

  ```java
  search( tree, key ) {
    result <- tree.rootNode;
    
    while(result != NULL) {
      if (key == result.key) {
        break;
      }
      else if (key < result.key) {
        result = result.leftChild;
      }
      else {
        result = result.rightChild;
      }
    }
    
    return result;
  }
  ```



## 4.4. 이진 검색 트리에서의 추가 연산

* **추가 연산**
  1. 적절한 삽입 위치 찾기 : **부모 노드 찾기**
     * **적절한 삽입 위치** : 이진 검색 트리의 제약 사항을 만족하는 위치
  2. 앞단계에서 찾은 위치에 새로운 노드 추가하기



## 4.5. 이진 검색 트리에서의 제거 연산

* **제거 연산에서의 세 가지 경우**
  1. 제거하려는 노드의 자식 노드가 없는 경우(단말 노드인 경우)
  2. 제거하려는 노드의 자식 노드가 1개인 경우
  3. 제거하려는 노드의 자식 노드가 2개인 경우



## 4.6. 이진 검색 트리의 구현

### 4.6.1. 이진 검색 트리의 구조

* **노드 구조**

  ```java
  package chapter12_search;
  
  public class Node {
  
    public int key;
    public char value;
    public Node leftChild;
    public Node rightChild;
  
    public Node(int key, char value) {
      this.key = key;
      this.value = value;
    }
  
  }
  ```

* **트리 구조**

  ```java
  package chapter12_search;
  
  public class BinarySearchTree {
  
    private Node rootNode = null;
  
  }
  ```



### 4.6.2. 이진 검색 트리에서의 검색 연산

```java
public Node search(int key) {
  Node node;

  node = rootNode;

  while (node != null) {					// 종료 조건 : 더 남은 노드가 없을때
    if (key == node.key) break;		// 검색 키로 자료를 찾은 경우
    else if (key < node.key) node = node.leftChild;		// 검색 키가 현재 키보다 작은 경우
    else node = node.rightChild;	// 검색 키가 현재 키보다 큰 경우
  }

  return node;
}
```



### 4.6.3. 이진 검색 트리에서의 추가 연산

```java
// 적절한 삽입 위치를 찾기 위한 함수
private Node getParentNode(int key) {
  Node parentNode = null;
  Node currentNode = rootNode;

  while (currentNode != null) {
    if (key == currentNode.key) return null;    // 추가하려는 키 값이 존재하면 null 반환
    else if (key < currentNode.key) {						// 추가하려는 키 값이 현재 값 보다 작을 때
      parentNode = currentNode;
      currentNode = currentNode.leftChild;			// 왼쪽 트리로 이동
    } else {																		// 추가하려는 키 값이 현재 값 보다 클 때
      parentNode = currentNode;
      currentNode = currentNode.rightChild;			// 오른쪽 트리로 이동
    }
  }

  return parentNode;
}

public void insert(int key, char value) {
  // 적절한 삽입 위치 찾기: 부모 노드 찾기
  Node parentNode = getParentNode(key);
  
  // 추가하려는 첫 번째 노드이거나 부모 노드가 존재할 때
  if (rootNode == null || parentNode != null) 
    insertNewNode(new Node(key, value), parentNode);
}

private void insertNewNode(Node newNode, Node parentNode) {
  // 루트 노드가 존재하지 않으면 루트 노드에 저장
  if (rootNode == null) rootNode = newNode;
  // 루트 노드가 존재할 때는 찾은 부모 노드의 왼쪽이나 오른쪽에 삽입
  else {
    if (newNode.key < parentNode.key) parentNode.leftChild = newNode;
    else parentNode.rightChild = newNode;
  }
}
```



### 4.6.4. 이진 검색 트리에서의 제거 연산

```java
public void remove(int key) {
  Node parentNode = getParentNode(key);  // 제거할 키 값의 부모 노드를 찾는다
  Node deleteNode = search(key);				 // 제거할 키 값을 찾는다.

  // 삭제할 값이 존재할 때
  if (deleteNode != null && rootNode != null && parentNode != null) {
  } 
  // 제거 대상 노드의 자식이 없을 때
  else if (deleteNode.leftChild == null && deleteNode.rightChild == null) {
    deleteNodeNoChild(parentNode, deleteNode);
  } 
  // 제거 대상 노드의 자식이 1개 있을 때
  else if (deleteNode.leftChild != null && deleteNode.rightChild != null) {
    deleteNodeOneChild(parentNode, deleteNode);
  } 
  // 제거 대상 노드의 자식이 2 있을 때
  else {
    deleteNodeTwoChildren(parentNode, deleteNode);
  }
}

// 제거 노드의 자식이 없을 때
private void deleteNodeNoChild(Node parentNode, Node deleteNode) {
  // 부모 노드가 존재할 때
  if (parentNode != null) {
    // 자식을 가리키고 있는 방향에 null을 저장
    if (parentNode.leftChild == deleteNode) {
      parentNode.leftChild = null;
    } else {
      parentNode.rightChild = null;
    }
  } else {
    rootNode = null;
  }
}

// 제거 노드의 자식이 1개 있을 때
private void deleteNodeOneChild(Node parentNode, Node deleteNode) {
  Node childNode;

  // 제거할 노드의 자식이 왼쪽인지 오른쪽인지 찾는다
  if (deleteNode.leftChild != null) {
    childNode = deleteNode.leftChild;
  } else {
    childNode = deleteNode.rightChild;
  }

  // 부모 노드가 존재
  if (parentNode != null) {
    // 부모 노드의 왼쪽 자식이 삭제 노드일때 삭제 노드의 자식 노드로 저장
    if (parentNode.leftChild == deleteNode) {
      parentNode.leftChild = childNode;
    } else {
      parentNode.rightChild = childNode;
    }
  } else {
    rootNode = childNode;
  }
}

// 제거 노드의 자식이 2개 있을 때
private void deleteNodeTwoChildren(Node parentNode, Node deleteNode) {
  Node predecessor = deleteNode;
  Node successor = deleteNode.leftChild;

  // 왼쪽 서브트리에서 가장 큰 키 값을 가지는 노드 찾기
  while (successor.rightChild != null) {
    predecessor = successor;
    successor = successor.rightChild;
  }

  // 대체 노드의 자식 노드를 전임 노드의 자식 노드로 변경
  predecessor.rightChild = successor.leftChild;
  
  // 제거 노드의 자식 노드를 대체 노드의 자식 노드로 변경
  successor.leftChild = deleteNode.leftChild;
  successor.rightChild = deleteNode.rightChild;

  // 대체 노드를 부모 노드의 새로운 자식 노드로 결정
  if (parentNode != null) {
    if (parentNode.leftChild == deleteNode) {
      parentNode.leftChild = successor;
    } else {
      parentNode.rightChild = successor;
    }
  } 

// 부모 노드가 null이면 루트 노드가 제거 되었다는 의미이기 때문에 대체 노드를 이진 탐색 트리의 새로운 루트 노드로 설정
  else {
    rootNode = successor;
  }
}
```



### 4.6.5. 기타

**순회 메소드**

```java
public void display() {
  displayTree(rootNode, 0, '-');
}

private void displayTree(Node node, int level, char type) {
  if (node != null) {
    for (int i = 0; i < level; i++) {
      System.out.print(" ");
    }

    System.out.println("-(" + type + ") - key: " + node.key + ", value: " + node.value);

    displayTree(node.leftChild, level + 1, 'L');
    displayTree(node.rightChild, level + 1, 'R');
  }
}
```



**main 메소드**

```java
public static void main(String[] args) {
  BinarySearchTree binarySearchTree = new BinarySearchTree();

  binarySearchTree.insert(70, 'A');
  binarySearchTree.insert(40, 'B');
  binarySearchTree.insert(90, 'C');
  binarySearchTree.insert(20, 'D');
  binarySearchTree.insert(60, 'E');
  binarySearchTree.insert(80, 'F');
  binarySearchTree.insert(100, 'G');
  binarySearchTree.insert(10, 'H');
  binarySearchTree.insert(30, 'I');
  binarySearchTree.insert(50, 'J');

  binarySearchTree.display();

  System.out.println("키 30 검색 : " + binarySearchTree.search(30).key + ", " + binarySearchTree.search(30).value);
  System.out.println("키 70 검색 : " + binarySearchTree.search(70).key + ", " + binarySearchTree.search(70).value);

  binarySearchTree.remove(70);

  binarySearchTree.display();
}
```

**실행 결과**

```java
-(-) - key: 70, value: A
 -(L) - key: 40, value: B
  -(L) - key: 20, value: D
   -(L) - key: 10, value: H
   -(R) - key: 30, value: I
  -(R) - key: 60, value: E
   -(L) - key: 50, value: J
 -(R) - key: 90, value: C
  -(L) - key: 80, value: F
  -(R) - key: 100, value: G

키 30 검색 : 30, I
키 70 검색 : 70, A

-(-) - key: 40, value: B
 -(L) - key: 20, value: D
  -(L) - key: 10, value: H
  -(R) - key: 30, value: I
 -(R) - key: 60, value: E
  -(L) - key: 50, value: J
```

