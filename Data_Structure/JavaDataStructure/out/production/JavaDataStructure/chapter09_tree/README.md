# Chapter 08. 트리

* **트리(tree) 특징**
  * **계층 구조(hierarchical structure)** 로 자료를 저장한다.
  * 계층 구조의 핵심은 **다음 노드는 여러 개 연결될 수 있지만, 이전 노드는 오직 한 개** 이다.
  * 계층 구조 = **부모-자식(parent-child)** 구조
  * 리스트, 스택, 큐는 모두 한 줄로 자료를 저장한다. **(선형 구조)**
  * 반면, 트리는 **비선형(linear) 구조** 이다. 왜냐하면 각 자료의 다음 노드가 1개가 아니라 **여러 개의 자료가 올 수 있기** 때문이다.



# 1. 트리란?

* **정의** : 트리(tree)는 계층 구조를 가지는 **노드(node)와 간선(edge)의 집합이다.**
  * **간선(edge)** : 노드 사이를 연결하는 선, 부모-자식 관계를 정확하게 나타내기 위해서 이러한 간선을 사용한다.



## 1.1. 노드의 종류

트리에서의 노드 종류

* **트리에서의 위치**

| 용어                          | 내용                  |
| ----------------------------- | --------------------- |
| 루트(root) 노드               | 트리의 첫 번째 노드   |
| 단말(leaf 혹은 terminal) 노드 | 자식 노드가 없는 노드 |
| 내부(Internal) 노드           | 자식 노드가 있는 노드 |

* **노드 사이의 관계**

| 용어                  | 내용                                                   |
| --------------------- | ------------------------------------------------------ |
| 부모(parent) 노드     | 부모 노드와 자식 노드는 간선(edge)으로 연결됨          |
| 자식(child) 노드      | ""                                                     |
| 선조(ancestor) 노드   | 루트 노드부터 부모 노드까지의 경로 상에 있는 모든 노드 |
| 후손(descendent) 노드 | 특정 노드의 아래에 있는 모든 노드                      |
| 형제(sibling) 노드    | 같은 부모 노드의 자식 노드                             |

<img src="../capture/스크린샷 2019-06-02 오후 3.05.39.png" width="700">

## 1.2. 노드의 속성

* **트리에서의 노드 속성**

| 용어         | 내용                                                         |
| ------------ | ------------------------------------------------------------ |
| 레벨(level)  | 루트 노드부터의 거리                                         |
| 높이(height) | 루트 노드부터 가장 먼 거리에 있는 자식 노드의 높이에 1을 더한 값 |
| 차수(degree) | 한 노드가 가지는 자식 노드의 개수                            |

<img src="../capture/스크린샷 2019-06-02 오후 3.00.07.png" width="700">

<img src="../capture/스크린샷 2019-06-02 오후 3.02.07.png" width="700">

* **기타 트리 관련 용어**
  * **서브트리(subtree)** : 트리에 속한 노드들의 부분 집합
  * **포레스트(forest)** : 숲 구조 혹은 숲 자료구조라고 한다. **(트리의 집합)**
  * **forest 와 tree 를 구분 짓는 것** : 루트 노드의 개수
  * **이진 트리** : 하나의 노드에 최대 2개까지만 자식 노드를 가질 수 있는 트리

> 트리의 노드와 간선 개수의 관계
>
> 트리에서 노드 개수가 **n개일 때** 간선의 개수는 **n - 1 개**



# 2. 이진 트리

**모든 트리 노드의 차수가 2 이하인 트리**



## 2.1. 이진 트리의 종류

* **트리의 형태는 레벨과 노드 수에 따라 결정된다.**
  * 포화 이진 트리
  * 완전 이진 트리
  * 편향 이진 트리



### 2.1.1. 포화 이진 트리

**포화 이진 트리(full binary tree)** : 모든 레벨의 **노드가 꽉 차있는** 이진 트리

* **포화 이진 트리에서 모든 노드의 개수**
  * $$n = 2^h - 1$$
  * n : 높이 h인 이진 포화 트리의 노드 개수

<img src="../capture/스크린샷 2019-06-02 오후 3.06.33.png" width="700">



### 2.1.2. 완전 이진 트리

**완전 이진 트리(complete binary tree)** : 높이가 h고 노드 개수가 n 개라고 할 때, 레벨 1부터 h - 1 까지는 포화 이진 트리와 마찬가지로 꽉 채워져 있다가 **마지막 레벨 h 에서는 왼쪽에서 오른쪽으로 노드가 채워져 있는** 이진트리

* **완전 이진 트리에서 모든 노드의 개수**
  * $$n < 2^h - 1$$
  * n : 높이 h인 완전 이진 트리의 노드 개수
  * 단, 노드 번호 (n + 1) 번부터 2^h - 1 까지는 공백 노드

<img src="../capture/스크린샷 2019-06-02 오후 3.07.23.png">



### 2.1.3. 편향 이진 트리

**편향 이진 트리(skewed binary tree)** : 같은 높이의 이진 트리 중에서 최소 개수의 노드 개수를 가지면서 **왼쪽 혹은 오른쪽으로만 편향되게 서브트리를 가지는** 이진 트리

* **편향 이진 트리의 모든 노드 개수**
  * $$h <= n <= (2^h - 1)$$
  * n : 높이 h인 이진 트리의 노드 개수



<img src="../capture/스크린샷 2019-06-02 오후 3.08.15.png">



# 3. 이진 트리의 추상 자료형

| 기능                    | 메소드              | Input           | Output                  | 설명                                          |
| ----------------------- | ------------------- | --------------- | ----------------------- | --------------------------------------------- |
| 트리 생성               | makeBinTree()       | 자료            | 이진 트리               | 자료를 갖는 루트 노트로 구성된 이진 트리 생성 |
| 루트 노드 반환          | getRootNode()       | 이진 트리       | 루트 노드               | 루트 노드 반환                                |
| 왼쪽 자식 노드 추가     | addLeftChildNode()  | 부모 노드, 자료 | 생성된 왼쪽 자식 노드   | 부모 노드의 왼쪽 자식 노드 추가               |
| 오른쪽 자식 노드 추가   | addRightChildNode() | 부모 노드, 자료 | 생성된 오른쪽 자식 노드 | 부모 노드의 오른쪽 자식 노드 추가             |
| 왼쪽 자식 노드 반환     | getLeftChildNode()  | 부모 노드       | 왼쪽 자식 노드          | 부모 노드의 왼쪽 자식 노드 반환               |
| 오른쪽 자식 노드 반환   | getRightChildNode() | 부모 노드       | 오른쪽 자식 노드        | 부모 노드의 오른쪽 자식 노드 반환             |
| 이진 트리 노드의 데이터 | getData()           | 이진 트리       | 자료                    | 이진 트리의 루트 노드 데이터 반환             |
| 트리 삭제               | deleteBinTree()     | 이진 트리       | N/A                     | 트리 삭제                                     |



# 4. 배열로 구현한 이진 트리

* **배열 인덱스를 사용할 때** : 노드 접근이 편리하다는 장점이 있지만, 빈 노드가 많을 경우에 메모리 낭비가 심하다는 단점이 있다.



# 5. 포인터로 구현한 이진 트리

노드 사이의 연결 정보인 간선(edge)을 포인터로 구현. 참고로, 포인터로 구현한 이진 트리를 줄여서 **연결 이진 트리(linked binary tree)** 라고도 한다.



## 5.1. 연결 이진 트리의 구조

* **노드 구조**

  ```java
  package chapter09_tree;
  
  public class TreeNode<T> {
  
      public T data;                      // 노드가 저장하는 자료
      public TreeNode<T> leftChild;       // 왼쪽 자식 노드
      public TreeNode<T> rightChild;      // 오른쪽 자식 노드
      
      public TreeNode(T data) {
          this.data = data;
      }
  }
  ```

* **트리 구조**

  ```java
  package chapter09_tree;
  
  public class Tree {
  
      TreeNode rootNode;      // 루트 노드
  
  }
  ```



## 5.2. 연결 이진 트리의 생성

```java
package chapter09_tree;

public class Tree {

    private TreeNode rootNode;      // 루트 노드
    
    // 생성자, 루트 노드 생성
    public <T> Tree(T data) {
        rootNode = new TreeNode<>(data);
    }

}
```



## 5.3. 연결 이진 트리의 자식 노드 추가

```java
// 부모의 왼쪽 자식 노드 추가시
public <T> void addLeftChildNode(TreeNode parentNode, T newData) {
  if (parentNode != null && parentNode.leftChild != null) {
    parentNode.leftChild = new TreeNode<>(newData);
  } else {
    System.out.println("에러, 이미 노드가 존재합니다. addLeftChildNode()");
  }
}

// 부모의 오른쪽 자식 노드 추가시
public <T> void rightLeftChildNode(TreeNode parentNode, T newData) {
  if (parentNode != null && parentNode.rightChild != null) {
    parentNode.rightChild = new TreeNode<>(newData);
  } else {
    System.out.println("에러, 이미 노드가 존재합니다. addLeftChildNode()");
  }
}
```



## 5.4. 연결 이진 트리의 그 외 연산들

* **루트 노드 가져오기**

  ```java
  public TreeNode getRootNode() {
    return rootNode;
  }
  ```

* **main 메소드**

  ```java
  public static void main(String[] args) {
    Tree tree = new Tree('A');
    TreeNode[] treeNodes = new TreeNode[]{
      new TreeNode<>('B'),
      new TreeNode<>('C'),
      new TreeNode<>('D'),
      new TreeNode<>('E'),
      new TreeNode<>('F'),
    };
  
    tree.addLeftChildNode(tree.getRootNode(), treeNodes[0]);
    tree.addRightChildNode(tree.getRootNode(), treeNodes[1]);
  
    tree.addLeftChildNode(treeNodes[0], treeNodes[2]);
    tree.addLeftChildNode(treeNodes[2], treeNodes[3]);
    tree.addRightChildNode(treeNodes[2], treeNodes[4]);
  }
  ```



# 6. 이진 트리의 순회

* **트리의 순회(traversal)** : 트리의 모든 노드를 한 번씩 방문하는 것
  * **전위 순회**
  * **중위 순회**
  * **후위 순회**

<img src="../capture/스크린샷 2019-06-02 오후 3.45.06.png">



## 6.1. 전위 순회

**전위 순회(preorder traversal)** : 현재 노드를 가장 먼저 방문 **(pre-oredr)** 하는 방법이다.

* **주의할 점**
  * 서브트리로 이동하면 이동한 서브트리의 루트 노드가 새로운 현재 노드가 된다는 점
  * 부모 노드의 관점으로 이동해야 한다는 점



## 6.2. 중위 순회

**중위 순회(inorder traversal)** : 현재 노드를 중간에 하는 방문 **(in-order)** 하는 방법

* **중요한 점**
  * 전위 순회와 달리 현재 노드를 곧장 방문하지 않는다는 점
* **주의할 점**
  * 이동한 서브트리의 루트 노드가 새로운 현재 노드가 되는데, 현재 노드를 먼저 방문하는 것이 아니라 왼쪽 서브트리를 먼저 방문해야 한다는 점



## 6.3. 후위 순회

**후위 순회(postorder traversal)** : 현재 노드를 가장 마지막 **(post-order)** 에 방문하는 방법

* **핵심** : 현재 노드를 방문하기 전에 왼쪽 서브트리와 오른쪽 서브트리가 모두 방문되어야 한다는 점
* **주의할 점**
  * 이동한 서브트리의 루트 노드가 새로운 현재 노드가 되는데, 현재 노드를 먼저 방문하는 것이 아니라 왼쪽 서브트리와 오른쪽 서브트리를 먼저 방문해야 한다는 점



## 6.4. 레벨 순회

**레벨 순회(level traversal)** : 레벨의 크기에 따라 순회하는 방법

* 낮은 레벨의 노드부터 시작하여 높은 레벨의 노드를 차례로 방문한다.
* 단, 같은 레벨이라면 왼쪽 노드에서 오른쪽 노드로 이동한다.



## 6.5. 순회의 구현

* **구현 방법**
  * 재귀에 의한 구현(recursive function)
  * 반복에 의한 구현(iterative function)



### 6.5.1. 재귀 전위 순회

```java
public void preorderTraversalRecursiveTree() {
  System.out.println("재귀 전위 순회");
  preorderTraversalRecursiveTreeNode(getRootNode());
  System.out.println();
}

private void preorderTraversalRecursiveTreeNode(TreeNode node) {
  if (node != null) {
    System.out.println(node.data);                          // V
    preorderTraversalRecursiveTreeNode(node.leftChild);     // L
    preorderTraversalRecursiveTreeNode(node.rightChild);    // R
  }
}
```



### 6.5.2. 재귀 중위 순회

```java
public void inorderTraversalRecursiveTree() {
  System.out.println("재귀 중위 순회");
  inorderTraversalRecursiveTreeNode(this.rootNode);
  System.out.println();
}

private void inorderTraversalRecursiveTreeNode(TreeNode node) {
  if (node != null) {
    inorderTraversalRecursiveTreeNode(node.leftChild);      // L
    System.out.println(node.data);                          // V
    inorderTraversalRecursiveTreeNode(node.rightChild);     // R
  }
}
```



### 6.5.3. 재귀 후위 순회

```java
public void postorderTraversalRecursiveTree() {
  System.out.println("재귀 후위 순회");
  postorderTraversalRecursiveTreeNode(this.rootNode);
  System.out.println();
}

private void postorderTraversalRecursiveTreeNode(TreeNode node) {
  if (node != null) {
    postorderTraversalRecursiveTreeNode(node.leftChild);      // L
    postorderTraversalRecursiveTreeNode(node.rightChild);     // R
    System.out.println(node.data);                            // V
  }
}
```



### 6.5.4. main 메소드

* **Tree 구조**

  <img src="../capture/스크린샷 2019-06-02 오후 4.57.59.png" width="700">

**코드**

```java
public static void main(String[] args) {
  Tree tree = new Tree('A');
  TreeNode[] treeNodes = new TreeNode[]{
    new TreeNode<>('B'),
    new TreeNode<>('C'),
    new TreeNode<>('D'),
    new TreeNode<>('E'),
    new TreeNode<>('F'),
    new TreeNode<>('G')
  };

  tree.addLeftChildNode(tree.getRootNode(), treeNodes[0]);
  tree.addRightChildNode(tree.getRootNode(), treeNodes[1]);

  tree.addLeftChildNode(treeNodes[0], treeNodes[2]);
  tree.addRightChildNode(treeNodes[0], treeNodes[3]);

  tree.addLeftChildNode(treeNodes[1], treeNodes[4]);
  tree.addRightChildNode(treeNodes[1], treeNodes[5]);


  tree.preorderTraversalRecursiveTree();
  tree.inorderTraversalRecursiveTree();
  tree.postorderTraversalRecursiveTree();
}
```



**실행 결과**

```java
재귀 전위 순회
A
B
D
E
C
F
G

재귀 중위 순회
D
B
E
A
F
C
G

재귀 후위 순회
D
E
B
F
G
C
A
```



