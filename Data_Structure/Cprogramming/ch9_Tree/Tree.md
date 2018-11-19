# 9 ) 트리

**트리** : **나무 자료구조**라 한다.

**계층 구조** : 다음 노드는 여러개 연결될 수 있지만, 이전 노드는 오직 한 개 이다.

> **트리의 개념**
>
> 계층 구조 = 부모-자식 구조 = 다음 노드는 여러 개, 이전 노드는 1개

**비선형 구조** : 다음 노드가 1개가 아니라 여러 개의 자료가 올 수 있기 때문이다.



## 1. 트리란?

**트리(tree)는 계층 구조를 가지는 노드(node)와 간선(edge)의 집합이다.**

**간선** : 노드 사이를 연결하는 선 이다.



### 1.1 ) 노드의 종류

|       구분       | 용어                      | 내용                                                       |
| :--------------: | ------------------------- | ---------------------------------------------------------- |
|                  | **루트(root)** 노드       | 트리의 **첫 번째** 노드                                    |
| 트리에서의 위치  | **단말(leaf)** 노드       | 자식 노드가 **없는** 노드                                  |
|                  | **내부(internal)** 노드   | 자식 노드가 **있는** 노드                                  |
|                  | **부모(parent)** 노드     | 부모 노드와 자식 노드는 **간선**으로 연결되어 있음         |
|                  | **자식(child)** 노드      | 부모 노드와 자식 노드는 **간선**으로 연결되어 있음         |
| 노드 사이의 관계 | **선조(ancetor)** 노드    | 특정 노드부터 부모 노드까지의 **경로 상에 있는 모든 노드** |
|                  | **후손(descendent)** 노드 | 특정 노드의 **아래에 있는 모든 노드**                      |
|                  | **형제(sibling)** 노드    | **같은 부모 노드**의 자식 노드                             |





### 1.2 ) 노드의 속성

|       용어       | 내용                                                         |
| :--------------: | ------------------------------------------------------------ |
| **레벨(level)**  | 루트 노드부터의 **거리**                                     |
| **높이(height)** | 루트 노드부터 가장 먼 거리에 있는 자식 노드의 **높이에 1을 더한 값** |
| **차수(degree)** | 한 노드가 가지는 **자식 노드의 개수**                        |

* **노드들의 부분 집합을 서브트리(subtree)라 한다**
* **포리스트(forest)** : 숲 구조, 숲 자료구조 라 한다. **트리의 집합**이라고 한다





## 2. 이진 트리

**이진 트리(binary tree)는 모든 트리 노드의 차수가 2 이하인 트리**입니다.

![1540974574694](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1540974574694.png)



### 2.1 ) 이진 트리의 종류

3가지 종류 : **포화 이진 트리, 완전 이진 트리, 편향 이진 트리**



#### 2.1.1 ) 포화 이진 트리

: 모든 레벨의 노드가 꽉 차있는 이진 트리를 말합니다.

* **포화 이진 트리의 노드 개수**
  $$
  n = 2^h - 1
  $$





  **n : 높이 h인 이진 포화 트리의 노드 개수**



#### 2.1.2 ) 완전 이진 트리

: 높이가 h고 노드 개수가 n개 라고 하였을 때, 꽉 채워져 있다가 **마지막 레벨 h에서 왼쪽에서 오른쪽으로 노드가 채워져 있는 트리이다.**



* **완전 이진 트리 노드 개수**
  $$
  n<2^h-1
  $$
  **n : 높이 h인 완전 이진 트리의 노드 개수**

  **단, 노드 번호 (n+1) 번 부터 2^h - 1 까지는 공백 노드**



#### 2.1.3 ) 편향 이진 트리

: 같은 높이의 이진 트리 중에서 최소 개수의 노드 개수를 가지면서 **왼쪽 혹은 오른쪽으로만 편향되게 서브트리를 가지는 트리 이다.**

* **노드 개수 n의 범위**
  $$
  h<= n <= (2^h-1)
  $$
  **n : 높이 h인 이진 트리의 노드 개수**





## 6.  이진 트리의 순회

**순회(traversal)** : 트리의 모든 노드를 한 번씩 방문하는 것

**이진 트리 순회 방법** :  전위 순회, 중위 순회, 후위 순회



**이진 트리 순회 관련 용어들**

| 표시 | 내용                 |
| :--: | -------------------- |
|  V   | 현재 노드 방문       |
|  L   | 왼쪽 서브트리로 이동 |
|  R   | 오른쪽 서븥리로 이동 |



* **이진 트리의 순회 종류**

  | 종류                               | 방문 순서 | 설명                                                         |
  | ---------------------------------- | --------- | ------------------------------------------------------------ |
  | **전위 순회**(preorder traversal)  | V-L-R     | **현재 노드 방문** -> 왼쪽 서브트리 이동 -> 오른쪽 서브 트리 방문 |
  | **중위 순회**(inorder traversal)   | L-V-R     | 왼쪽 서브트리 이동 -> **현재 노드 방문** -> 오른쪽 서브트리 이동 |
  | **후위 순회**(postorder traversal) | L-R-V     | 왼쪽 서브트리 이동 -> 오른쪽 서브트리 이동 -> **현재 노드 방문** |



* **레벨 순회(level traversal)** : 같은 레벨의 노드들을 방문한다. ( 형제 노드 방문으로 구성된다. )



### 6.1 ) 전위 순회

: **현재 노드를 가장 먼저 방문 (pre-order)하는 방법이다. ( V-L-R)**

* **주의할 점** : 서브트리로 이동하면 서브트리의 루트 노드가 새로운 현재 노드가 된다는 점이다. **부모 노드의 관점으로 이동해야 한다는 점도 기억해 주세요.**



### 6.2 ) 중위 순회

: **현재 노드를 중간에 방문(in-order)하는 방법이다. ( L-V-R)**

* **주의할 점** : 이동한 서브트리의 루트 노드가 새로운 현재 노드가 되는데, **현재 노드를 먼저 방문하는 것이 아니라 왼쪽 서브트리를 먼저 방문해야 한다는 점이다.**



### 6.3 ) 후위 순회

: **현재 노드를 가장 마지막에 방문(post-order)하는 방법이다.(L-R-V)**

* **주의할 점** : 이동한 서브트리의 루트 노드가 새로운 현재 노드가 되는데, **현재 노드를 먼저 방문하는 것이 아니라 왼쪽 서브트리와 오른쪽 서브트리를 먼저 방문해야 한다는 점이다.**



### 6.4 ) 레벨 순회

: **레벨의 크기에 따라 순회하는 방법이다. (오름차순)**


