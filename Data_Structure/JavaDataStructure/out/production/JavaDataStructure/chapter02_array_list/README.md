# Chapter 03. 배열 리스트

* **리스트**
  * 순서대로 자료를 저장하는 자료구조이다.
  * 스택(stack), 큐(queue), 트리(tree), 그래프(graph) 등 에서 사용된다.



# 1. 리스트란?

* 리스트는 **'한 줄'** 로 저장하는 자료구조이다. 한 줄은 줄을 세울 때 연결되는 **앞 항목과 뒤 항목이 모두 1개** 라는 뜻이다.
* 위와 같이, 여러 개의 자료가 한 줄로 **연결된(sequential)** 구조를 다른 말로 **선형 구조** 라고도 한다.



# 2. 리스트 사용 시나리오

* **리스트 시나리오**
  1. 추가(add)
  2. 값 가져오기(get)
  3. 제거(remove)



## 2.1. 새로운 자료 추가

* **추가할 때 주의할 점**
  * 값을 추가할 때는 값도 중요하지만 **추가할 위치 정보도** 중요하다.



## 2.2. 값 가져오기

* **값 가져올 때 주의할 점**
  * 첫 번째 저장된 자료의 인덱스는 0 이고 인덱스가 position 인 자료는 (position + 1) 번째에 저장된 자료를 가져와야 한다.



## 2.3. 기존 자료의 제거

* **제거시 주의할 점**
  * 제거할 자료의 위치 정보가 필요하고 제거할 자료의 위치에 기존 데이터들이 덮어 씌워져야 한다.



# 3. 리스트의 추상 자료형

## 3.1. 자료, 자료형

* **자료** : 프로그램에서 처리되는 대상으로 특정 값(value)

* **자료형** : 자료(data) 와 이 자료를 처리하기 위한 명령 혹은 연산(operation) 이 합쳐진 것.

  


## 3.2. 추상 자료형

**추상 자료형** : 추상적으로 정의된 자료형이다.

* **리스트의 추상 자료형**

| 이름                          | 함수             | 입력                      | 출력           | 설명                                                 |
| ----------------------------- | ---------------- | ------------------------- | -------------- | ---------------------------------------------------- |
| 리스트 생성                   | createList( )    | -                         | 리스트 list    | 빈 리스트 생성                                       |
| 자료 추가                     | addListData()    | 리스트, 추가할 위치, 자료 | 성공/실패 여부 | 자료를 리스트의 위치에 추가                          |
| 자료 반환                     | getListData()    | 리스트, 자료 위치         | 자료           | 리스트의 위치에 있는 자료 값을 반환                  |
| 자료 개수 반환                | getListLength()  | 리스트, 자료 위치         | 자료의 개수    | 리스트의 위치에 있는 자료를 제거                     |
| 자료 제거                     | removeListData() | 리스트, 자료 위치         | 성공/실패 여부 | 리스트의 위치에 있는 자료를 제거                     |
| 모든 자료 제거(리스트 초기화) | clearList()      | 리스트                    | N/A            | 리스트의 모든 자료를 제거                            |
| 리스트 삭제                   | deleteList()     | 리스트                    | N/A            | 리스트의 모든 자료를 삭제하고 리스트의 메모리를 해제 |



# 4. 배열 리스트란?

* **배열 리스트** : 배열을 사용해서 구현된 리스트
  * **단점** : 최대 저장 개수에 따른 제약이 있다.



## 4.1. 추상 자료형

위의 리스트 추상 자료형과 비슷하지만 배열을 만들때 **최대 원소 개수 n이** 입력으로 필요하다는 것만 다르다.



## 4.2. 노드의 구조

* **노드(node)** : 배열 리스트에서 자료를 저장하는 단위

  * double 이나 char 형과 같이 다른 자료형의 원소를 동시에 저장하고 싶을 때 필요하다.

  * **코드**

    ```java
    package chapter02_array_list;
    
    public class Node {
        int data;
    
        public Node(int data) {
            this.data = data;
        }
    }
    ```



## 4.3. 배열 리스트의 구조

* **코드**

  ```java
  package chapter02_array_list;
  
  public class ArrayList {
      int maxCount;           // 최대 자료 개수 : 배열의 크기
      int currentCount;       // 현재 자료 개수
      Node[] nodes;           // 자료 저장을 위한 배열
  
      public ArrayList(int maxCount) {
          this.maxCount = maxCount;
          nodes = new Node[maxCount];
      }
  }
  ```



# 5. 배열 리스트의 구현

## 5.1. 리스트 생성

* **코드**

  ```java
  package chapter02_array_list;
  
  public class ArrayList {
      int maxCount;           
      int currentCount;       
      Node[] nodes;           
  
      public ArrayList(int maxCount) {	// 최대 저장 개수를 받아서 연결 리스트를 생성
          this.maxCount = maxCount;			// 최대 개수 저장
          nodes = new Node[maxCount];		// 최대 개수 만큼 노드 배열 생성
      }
  }
  ```



## 5.2. 새로운 자료의 추가

배열 리스트에서 새로운 원소가 추가될 때 기존의 원소를 옮겨야 한다. 왜냐하면 배열 리스트가 자료를 **순차적(sequential)으로 저장하기 위해서** 이다.

* **코드**

  ```java
  // 새로운 자료 추가
  void addListData(int position, int data) {
    int i = 0;
  
    try {
      // 추가되는 위치와 그 오른쪽에 있는 기존 자료를 모두 오른쪽으로 한 칸씩 이동
      for (i = currentCount - 1; i >= position; i--) {
        nodes[i+1].data = nodes[i].data;
      }
    } catch (NullPointerException ex) {
      nodes[i+1] = new Node(nodes[i].data);
    }
  
    nodes[position] = new Node(data);   // 실제 자료 추가   
    currentCount++;                     // 현재 저장된 자료 개수 1 증가
  }
  ```



## 5.3. 기존 자료의 제거

제거된 자료부터 시작하여 오른쪽 칸에 있는 자료를 왼쪽으로 한 칸씩 옮긴다.

* **코드**

  ```java
  // 기존 자료의 제거
  void removeListData(int position) {
    int i = 0;
  
    // 제거되는 원소의 위치와 그 오른쪽으로 있는 원소를 왼쪽으로 한 칸씩 이동
    for (i = position; i < currentCount - 1; i++) {
      nodes[i].data = nodes[i+1].data;
    }
  
    currentCount--;
  }
  ```



## 5.4. 값 가져오기와 기타 연산

배열 리스트에 저장된 특정 위치의 자료를 가져오는 연산.

```java
// 값 가져오기
int getListData(int position) {
  if (position < currentCount || position >= 0)
    return nodes[position].data;
  else
    System.out.println("값이 존재하지 않는 위치입니다.");
  return 0;
}
```



## 5.5. 최종 구현

```java
package chapter02_array_list;

public class Main {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList(5);

        arrayList.addListData(0, 10);
        arrayList.addListData(0, 20);
        arrayList.addListData(1, 30);


        for(int i=0; i<arrayList.currentCount; i++) {
            System.out.println("위치: " + i + ", 값: " + arrayList.getListData(i));
        }
        System.out.println();

        int value = arrayList.getListData(1);
        System.out.println("위치: 1" + ", 값: " + value );
        System.out.println();

        arrayList.removeListData(0);

        for(int i=0; i<arrayList.currentCount; i++) {
            System.out.println("위치: " + i + ", 값: " + arrayList.getListData(i) );
        }
        System.out.println();
    }
}
```

**실행 결과**

```
위치: 0, 값: 20
위치: 1, 값: 30
위치: 2, 값: 10

위치: 1, 값: 30

위치: 0, 값: 30
위치: 1, 값: 10
```