# Chapter 10. 그래프

그래프는 객체 사이의 연결 관계(connectivity)를 표현하기에 적합한 자료구조이다. 왜냐하면, 그래프는 객체들 사이의 **다대다 관계도 표현할 수 있기 때문이다.**

<img src="../capture/스크린샷 2019-06-27 오후 2.17.04.png" width=450>



# 1. 그래프란?

그래프는 **노드(node)와 간선(edge)의 집합이다.**

* **노드(node)** : 저장하려는 자료의 단위

* **간선(edge)** : 노드 사이를 연결하는 선



**그래프의 수학적 표현식**

>  G = (V(G), E(G)) : 그래프 G는 노드들의 집합 V(G)와 간선들의 집합 E(G)로 구성된다.
>
> V(G) : 노드들(vertex)
>
> E(G) : 간선들(edge)



## 1.1. 간선의 특성에 따른 그래프의 종류

무방향/방향 그래프



### 1.1.1. 방향 그래프 (directed graph)

두 노드를 연결하는 간선에 방향이 있는 그래프, 다이그래프(digraph) 라고도 불린다.

<img src="../capture/스크린샷 2019-06-27 오후 1.26.09.png" width=400>

> <0, 1> : 0번 노드에서 1번 노드로 이동할 수 있는 간선이 연결되었다는 의미



<img src="../capture/스크린샷 2019-06-27 오후 1.28.13.png">



### 1.1.2. 무방향 그래프 (undirected graph)

두 노드를 연결하는 간선에 방향이 없는 그래프

<img src="../capture/스크린샷 2019-06-27 오후 1.29.29.png" width=400>

> (0, 1) : 0번 노드와 1번 노드를 연결하였다는 의미



### 1.1.3. 가중 그래프 (weighted graph)

노드를 연결하는 간선에 가중치(weight)가 있는 그래프

* **가중치(weight)** : 간선 사이의 비용(cost) 혹은 거리(distance) 등 다양한 의미로 사용될 수 있다.

<img src="../capture/스크린샷 2019-06-27 오후 1.32.58.png">



## 1.2. 구조적 특성에 따른 그래프의 종류

* 완전 그래프
* 부분 그래프
* 다중 그래프



### 1.2.1. 완전 그래프 (complete graph)

그래프 내의 모든 노드가 1:1 간선으로 연결된 그래프이다. 즉, 모든 노드가 서로 연결되어 더 이상 추가될 간선이 없는 그래프이다. 또한 **연결 가능한 최대 간선 수를 가진** 그래프라고 할 수 있다.

* **노드의 개수(n)와 최대 간선 수(m)**
  * **무방향 그래프** : m = n(n - 1) / 2
  * **방향 그래프** : m = n(n - 1)

> 무방향 그래프는 두 노드 사이에 간선이 한 개만 올 수 있고, 방향 그래프는 양방향으로 2개의 간선이 올 수 있다.

<img src="../capture/스크린샷 2019-06-27 오후 1.40.38.png">



### 1.2.2. 부분 그래프 (sub graph)

원래의 그래프에서 일부의 노드나 간선을 제외하여 만든 그래프이다. 

<img src="../capture/스크린샷 2019-06-27 오후 1.43.45.png" width=400>

<img src="../capture/스크린샷 2019-06-27 오후 1.43.27.png" width=400>



### 1.2.3. 다중 그래프 (multi graph)

중복된 간선을 포함하는 그래프이다.

<img src="../capture/스크린샷 2019-06-27 오후 1.45.05.png">



## 1.3. 그래프 관련 용어

* 인접
* 부속
* 차수
* 경로
* 그래프의 동일성
* 루프



### 1.3.1. 인접

두 개의 노드를 연결하는 간선이 존재하는 경우 두 노드는 **인접(adjacent)** 되었다고 한다.

<img src="../capture/스크린샷 2019-06-27 오후 1.52.53.png">



### 1.3.2. 부속

두 개의 노드를 연결하는 간선이 존재하는 경우 이 간선은 두 노드에 각각 **부속(incident)** 되었다고 한다.

<img src="../capture/스크린샷 2019-06-27 오후 1.54.35.png">



### 1.3.3. 차수

노드에 부속된(연결된) 간선의 개수를 **차수(degree)** 라고 한다.

<img src="../capture/스크린샷 2019-06-27 오후 1.57.03.png" width=400>



* 노드로 들어오는 간선의 개수를 **진입차수(in-degree)** 라고 한다.
* 노드에서 나가는 간선의 개수를 **진출차수(out-degree)** 라고 한다.

<img src="../capture/스크린샷 2019-06-27 오후 1.58.11.png" width=400>



### 1.3.4. 경로

그래프의 노드 A에서 노드 B까지의 **경로(path)는** 노드 A에서 노드 B까지 통과하는 길을 말한다. 즉, 노드 A부터 노드 B에 **이르는 간선들의 인접 노드를 순서대로 나열한** 목록을 말한다.

<img src="../capture/스크린샷 2019-06-27 오후 2.00.32.png" width=300>

* 경로를 구성하는 간선의 개수를 **경로 길이(path length)** 라 한다.
* 경로 중에 같은 노드가 존재하지 않는 경우를 **단순 경로(simple path)** 라고 한다.
* 경로의 시작 노드와 마지막 노드가 같은 경로를 **사이클(cycle) 혹은 순환** 이라고 한다.
* 그래프 내의 모든 노드 사이에 경로가 있을 때 그래프가 **연결되었다(connected)** 고 한다.



### 1.3.5. 그래프의 동일성

그래프의 동일성을 판단하는 기준은 노드와 간선의 집합이 서로 같으냐 이다.

<img src="../capture/스크린샷 2019-06-27 오후 2.05.08.png" width=400>

> 노드와 간선의 집합이 서로 같기 때문에 달라보일지 몰라도 서로 같은 그래프이다.



### 1.3.6. 루프

그래프의 한 노드에서 자기 자신으로 이어지는 간선이 있다면 이러한 간선을 **루프(loop)라고** 한다.

<img src="../capture/스크린샷 2019-06-27 오후 2.06.45.png" width=400>



### 1.3.7. 그래프의 종류 정리

| 종류          | 설명                                                        |
| ------------- | ----------------------------------------------------------- |
| 무방향 그래프 | 간선에 방향이 없는 그래프                                   |
| 방향 그래프   | 간선에 방향이 있는 그래프                                   |
| 가중 그래프   | 간선에 가중치가 할당된 그래프                               |
| 완전 그래프   | 연결 가능한 최대 간선 수를 가진 그래프                      |
| 부분 그래프   | 원래의 그래프에서 일부의 노드나 간선을 제외하여 만든 그래프 |
| 다중 그래프   | 중복된 간선을 포함하는 그래프                               |



# 2. 그래프의 추상 자료형

| 기능        | 이름          | Input                | Output | 설명                                               |
| ----------- | ------------- | -------------------- | ------ | -------------------------------------------------- |
| 그래프 생성 | createGraph() | 최대 노드 개수       | 그래프 | n 개의 노드를 가지는 빈 그래프 생성                |
| 그래프 삭제 | deleteGraph() | 그래프               | N/A    | 그래프 모든 간선과 노드 제거                       |
| 간선 추가   | addEdge()     | 그래프, 노드A, 노드B | N/A    | 그래프 노드 A와 노드 B를 연결하는 새로운 간선 추가 |
| 간선 제거   | removeEdge()  | 그래프, 노드A, 노드B | N/A    | 그래프 간선 (A,B) or <A,B> 제거                    |



# 3. 인접 행렬로 구현한 그래프

2차원 배열은 그래프의 간선 정보를 가장 쉽게 저장하는 방법 중 하나이다. 

그래프의 구현에서 사용하는 2차원 배열을 **인접 행렬(adjacency matrix)** 라고 한다.



## 3.1. 인접 행렬이란?

**인접 행렬이란** 노드들 사이의 연결 정보를 저장하는 행렬을 말한다. 노드의 연결 정보란 결국 **간선의 정보를** 말한다.

<img src="../capture/스크린샷 2019-06-27 오후 2.53.25.png">

> **행(Row)은** 시작 노드를 의미하고 **열(Column)이** 종료 노드를 뜻한다.



## 3.2. 인접 행렬의 구조

인접 행렬을 이용해 방향 그래프 구현

```java
package chapter11_graph;

public class DirectArrayGraph {
  
  int nodeCount;		// 노드 개수
  int[][] edge;			// 간선 저장을 위한 2차원 배열
  
}
```



## 3.3. 그래프의 생성

```java
public DirectArrayGraph(int nodeCount) {
  this.nodeCount = nodeCount;										// 노드 개수 저장
  this.edge = new int[nodeCount][nodeCount];		// 2차원 배열 생성
}
```



## 3.4. 간선의 추가

```java
public void addEdge(int fromNode, int toNode) {
  // 시작 노드와 종료 노드가 유효한지 점검 후 2차원 배열에 간선 저장
  if (checkVertexValid(fromNode) && checkVertexValid(toNode)) edge[fromNode][toNode] = 1;
}

public boolean checkVertexValid(int node) {
  // 유효 점검
  return node < nodeCount && node >= 0;
}
```



## 3.5. 간선의 제거

```java
public void removeEdge(int fromNode, int toNode) {
  // 시작 노드와 종료 노드가 유효한지 점검 후 2차원 배열에서 간선 제거
  if (checkVertexValid(fromNode) && checkVertexValid(toNode)) edge[fromNode][toNode] = 0;
}
```



## 3.6. 간선 얻기와 기타

* **간선 얻기**

  ```java
  public int getEdge(int fromNode, int toNode) {
    // 유효성 검사 후 유효하면 간선 반환 아니면 0 반환
    return (checkVertexValid(fromNode) && checkVertexValid(toNode)) ?
      edge[fromNode][toNode] : 0;
  }
  ```

* **간선 출력**

  ```java
  public void displayGraph() {
    for (int i = 0; i < nodeCount; i++) {
      for (int j = 0; j < nodeCount; j++) {
        System.out.print(edges[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }
  ```

* **main**

  ```java
  public static void main(String[] args) {
    DirectArrayGraph graph = new DirectArrayGraph(6);
  
    graph.addEdge(0, 1);
    graph.addEdge(1, 2);
    graph.addEdge(2, 0);
    graph.addEdge(2, 3);
    graph.addEdge(3, 2);
    graph.addEdge(3, 4);
    graph.addEdge(4, 5);
    graph.addEdge(5, 3);
  
    graph.displayGraph();
  
    graph.removeEdge(0, 1);
  
    graph.displayGraph();
  }
  ```

* **실행 결과**

  ```
  0 1 0 0 0 0 
  0 0 1 0 0 0 
  1 0 0 1 0 0 
  0 0 1 0 1 0 
  0 0 0 0 0 1 
  0 0 0 1 0 0 
  
  0 0 0 0 0 0 
  0 0 1 0 0 0 
  1 0 0 1 0 0 
  0 0 1 0 1 0 
  0 0 0 0 0 1 
  0 0 0 1 0 0
  ```



## 3.7. 인접 행령의 메모리 낭비

인접 행렬은 그래프의 노드 개수 n이 증가하면 할 수록 낭비되는 메모리 비율은 n의 제곱만큼 증가한다. 왜냐하면 노드 개수 n과 간선의 개수 m은 선형적으로 증가하는 반면, 2차원 배열의 메모리 양은 크기가 **제곱으로** 커지기 때문이다.



# 4. 인접 리스트로 구현한 그래프

인접 리스트는 인접 정보를 저장하는 리스트를 말하는데, 여기서 인접 정보는 노드를 연결하는 간선의 정보를 말한다. 따라서, 인접 리스트는 **간선의 존재 여부 혹은 간선의 가중치 정보등을** 저장하는 리스트이다.



## 4.1. 인접 리스트란?

* 두 노드 사이에 간선이 없는 경우

<img src="../capture/스크린샷 2019-06-27 오후 6.22.54.png">

> 간선이 없을 경우에는 null 값을 가리키고 있다.



* 1개의 간선을 추가하는 경우

<img src="../capture/스크린샷 2019-06-27 오후 6.25.08.png">

> 간선이 존재하면 시작 노드의 연결 리스트로 종료 노드를 가리키게 간다.



* 인접 리스트 예시

<img src="../capture/스크린샷 2019-06-27 오후 6.28.04.png">



> 노드 2의 경우 <2, 0>, <2, 1>, <2, 3> 에 대한 정보가 3번째 연결 리스트의 노드에 3, 1, 0 으로 저장되어있는데, 연결 리스트 내에서 저장되는 종료 노드들의 순서는 정해져 있지 않다.



* **인접 리스트와 인접 행렬 비교**
  * 인접 행렬은 실제 간선의 개수와는 상관없이 **모든 간선의 정보를 2차원 배열에 저장하기 때문에** 메모리 낭비가 심하다.
  * 인접 리스트는 연결되지 않는 노드 사이의 **연결 정보를 저장하지 않기 때문에** 메모리 낭비를 하지 않는다.
  * 인접 리스트는 **희소 그래프(sparse graph)인** 경우에 사용하는 것이 효과적이지만, **밀집 그래프(dense graph)인** 경우 인접 행렬의 메모리 효율성이 낮다.
  * **희소 그래프(sparse graph)** : 노드의 개수는 많은데 비해 상대적으로 간선의 개수가 적은 그래프
  * **밀집 그래프(dense graph)** : 간선의 개수가 많은 그래프



* **그래프 구현 방식에 따른 메모리 효율성**

| 그래프      | 인접 행렬 | 인접 리스트 |
| ----------- | --------- | ----------- |
| 희소 그래프 | 낮음      | 높음        |
| 밀집 그래프 | 높음      | 낮음        |



* **그래프 구현 방식에 따른 자료 접근 시간**

| 인접 행렬 | 인접 리스트 |
| --------- | ----------- |
| O(1)      | O(n)        |

> 간선의 개수가 작거나 간선 접근이 빠르지 않아도 되는 경우에는 **인접 리스트를** 사용해야 하고, 간선의 개수가 많거나 간선 정보에 빠른 접근이 필요할 때에는 인접 행렬을 사용해야 한다.



## 4.2. 인접 리스트의 구조

방향 그래프의 인접 리스트 구현

* **노드의 구조**

```java
package chapter11_graph;

public class LinkedNode {

  int data;
  LinkedNode link;

  public LinkedNode(int data) {
    this.data = data;
  }

}
```

* **그래프 구조**

```java
package chapter11_graph;

import java.util.LinkedList;
import java.util.List;

public class DirectLinkedGraph {

  int nodeCount;					// 노드 개수
	List[] adjEdge;		// 간선 저장을 위한 연결 리스트(포인터)의 배열

}
```



## 4.3. 그래프의 생성

```java
public DirectLinkedGraph(int nodeCount) {
  this.nodeCount = nodeCount;
  this.adjEdge = new LinkedList[nodeCount];		// 간선 배열 생성
  
  // 간선 배열의 각 인덱스마다 연결 리스트 생성
  for (int i = 0; i < nodeCount; i++) {
    adjEdge[i] = new LinkedList();
  }
}
```



## 4.4. 간선의 추가

```java
// 노드 유효성 검사
public boolean checkVertexValid(int node) {
  return node >= 0 && node < nodeCount;
}

public void addEdge(int fromNode, int toNode) {
  // 시작 노드와 종료 노드 유효성 검사
  if (checkVertexValid(fromNode) && checkVertexValid(toNode)) {
    // 시작 노드의 연결리스트에 마지막 위치에 종료 노드의 인덱스 값 저장
    adjEdge[fromNode].add(toNode);
  }
}
```



## 4.5. 간선의 제거

```java
public void removeEdge(int fromNode, int toNode) {
  if (checkVertexValid(fromNode) && checkVertexValid(toNode)) {
    // Integer 로 형변환을 해주지 않으면 toNode를 인덱스로 계산하기 때문에 형변환을 해줘야한다.
		adjEdge[fromNode].remove((Integer)toNode);
  }
}
```



## 4.6. 간선 얻기와 기타

* **간선 얻기(간선 존재 여부 확인)**

```java
public boolean getEdge(int fromNode, int toNode) {
  return adjEdge[fromNode].contains(toNode);
}
```

* **간선 정보 출력**

```java
public void displayGraph() {
  for (int i = 0; i < adjEdge.length; i++) {
    for (int j = 0; j < adjEdge.length; j++) {
      // i번째 연결 리스트에 j가 존재하는지 확인하여 존재하면 1을 아니면 0을 출력시킨다.
      if (adjEdge[i].contains(j)) System.out.print("1 ");
      else System.out.print("0 ");
    }
    System.out.println();
  }
  System.out.println();
}
```

* **메인 함수**

```java
public static void main(String[] args) {
    DirectLinkedGraph graph = new DirectLinkedGraph(6);

    graph.addEdge(0, 1);
    graph.addEdge(1, 2);
    graph.addEdge(2, 0);
    graph.addEdge(2, 3);
    graph.addEdge(3, 2);
    graph.addEdge(3, 4);
    graph.addEdge(4, 5);
    graph.addEdge(5, 3);

    graph.displayGraph();

    graph.removeEdge(0, 1);

    graph.displayGraph();
  }
```

* **실행 결과**

```
0 1 0 0 0 0 
0 0 1 0 0 0 
1 0 0 1 0 0 
0 0 1 0 1 0 
0 0 0 0 0 1 
0 0 0 1 0 0 

0 0 0 0 0 0 
0 0 1 0 0 0 
1 0 0 1 0 0 
0 0 1 0 1 0 
0 0 0 0 0 1 
0 0 0 1 0 0
```



# 5. 무방향 그래프의 구현

무방향 그래프는 방향 그래프와 달리 **간선의 대칭성(symmetry)** 이라는 교유한 특성이 있다.

<img src="../capture/스크린샷 2019-06-27 오후 8.03.39.png">

<img src="../capture/스크린샷 2019-06-27 오후 8.04.19.png" width=400>



## 5.1. 무방향 그래프를 지원하는 인접 행렬 기반의 그래프

### 5.1.1. 그래프의 구조

```java
package chapter11_graph;

public class ArrayGraph extends DirectLinkedGraph {

  private final int DIRECT = 0;			// 방향 그래프
  private final int UNDIRECT = 1;		// 무방향 그래프

  private int graphType;	// 그래프 종류
  
}
```



### 5.1.2. 그래프의 생성

```java
public ArrayGraph(int graphType, int nodeCount) {
  super(nodeCount);
  this.graphType = graphType;
}
```



### 5.1.3. 간선의 추가

```java
public void addEdgeAG(int fromNode, int toNode) {
  addEdge(fromNode, toNode);
  // 무방향 그래프면 종료 노드에서 출발 노드로의 간선 추가
  if (graphType == UNDIRECT) addEdge(toNode, fromNode);
}
```



### 5.1.4. 간선의 제거

```java
public void removeEdgeAG(int fromNode, int toNode) {
  removeEdge(fromNode, toNode);
    // 무방향 그래프면 종료 노드에서 출발 노드로의 간선 제거
  if (graphType == UNDIRECT) removeEdge(fromNode, toNode);
}
```



# 6. 그래프 탐색

그래프 탐색(traversal 혹은 search)은 그래프 상의 모든 노드를 한 번식 방문하는 것을 말한다.

* **깊이 우선 탐색 (DFS: depth-first search)**
* **넓이 우선 탐색 (BFS: breadth-first search)**



**깊이-우선과 넓이-우선 탐색 비교**

| 구분           | 다음 노드 선택 기준                  |
| -------------- | ------------------------------------ |
| 깊이-우선 탐색 | 현재 방문 노드 기준으로 더 깊은 노드 |
| 넓이-우선 탐색 | 이전 방문 노드 기준으로 더 넓은 노드 |



## 6.1. 깊이-우선 탐색

깊이 우선 탐색은 다음 방문할 노드로 **현재 방문한 노드와 연결된 노드를 먼저 선택하는 방법이다.** 물론 선택되는 노드는 아직 방문하지 않은 노드여야 한다.

<img src="../capture/스크린샷 2019-06-27 오후 2.20.21.png">

* **코드**

```java
public void traversalDFS(int startNodeIndex) {
  // 탐색을 시작할 노드와 방문 여부를 점검할 배열을 매개 변수로 메소드 호출
  recursiveTraversalDFS(startNodeIndex, new int[getNodeCount()]);
}

private void recursiveTraversalDFS(int startNodeIndex, int[] visit) {
  // 현재 노드 방문 처리
  visit[startNodeIndex] = 1;
  System.out.println("노드-[" + startNodeIndex + "] 방문");
  for (int i = 0; i < getNodeCount(); i++) {
    if (i != startNodeIndex) {
      // 간선이 존재하는지와 기존 방문 여부를 점검
      if (getEdge(startNodeIndex, i) != 0 && visit[i] == 0) {
        recursiveTraversalDFS(i, visit);
      }
    }
  }
}
```



* **깊이-우선 탐색 알고리즘의 성능**

| 구분                        | 성능     |
| --------------------------- | -------- |
| 인접 행렬로 구현한 그래프   | O(n^2)   |
| 인접 리스트로 구현한 그래프 | O(n + m) |

> 노드 개수 = n
>
> 간선의 수 = m



## 6.2. 넓이-우선 탐색

넓이-우선 탐색은 다음 방문할 노드를 선택할 때 현재 방문한 노드가 아니라 **이전에 방문했던 노드와 연결된 노드를 먼저 선택하는 방법이다.** 즉, 이전 방문 노드와 직접 연결된 모든 노드가 먼저 방문되어야 한다.

<img src="../capture/스크린샷 2019-06-27 오후 2.23.23.png">

* **코드**

```java
public void traversalBFS(int startNodeIndex) {
  // 현재 방문 노드를 저장할 큐
  Queue<Integer> queue = new LinkedList<>();
  // 노드 방문 여부를 위한 배열
  int[] visit = new int[getNodeCount()];

	// 시작 노드 방문
  visit[startNodeIndex] = 1;
  queue.add(startNodeIndex);

  // 큐가 공백상태가 될때까지 반복
  while (!queue.isEmpty()) {
    // 큐에서 디큐 연산을 수행하여 꺼낸 노드 방문 정보 출력
    int node = queue.poll();
    System.out.println("노드-[" + node + "] 방문");
    // 디큐한 노드에 인접한 노드들에 대해서 처리
    for (int i = 0; i < getNodeCount(); i++) {
      if (i != node && getEdge(node, i) != 0 && visit[i] == 0) {
        visit[i] = 1;
        queue.add(i);
      }
    }
  }
}
```