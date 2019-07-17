# Chapter 12. 해싱

1. 해싱이란?
2. 추상 자료형
3. 해싱의 구조
4. 해시 함수
5. 충돌 해결책
   1. 선형 조사법
6. 체이닝



# 12.1. 해싱이란?

키 값에 직접 산술적인 연산을 적용하여 항목이 저장되어 있는 테이블의 주소를 계산하여 항목에 접근한다. 이렇게 키 값의 연산에 의해 직접 접근이 가능한 구조를 **해시 테이블(hash table)** 이라 부르며, 해시 테이블을 이용한 탐색을 **해싱(hashing)** 이라 한다.



키들의 비교에 의한 탐색 방법은 정렬이 안되어 있으면 **O(n)** 이고, 정렬이 되어 있으면 **O(log2(n))** 이다. 하지만 해싱은 이론적으로는 **O(1)** 의 시간 안에 탐색을 끝마칠 수도 있다.



# 12.2. 추상 자료형

* **객체**
  * 일련의 (key, value) 쌍의 집합
* **연산**
  * 새로운 항목을 사전에 삽입(add)
  * 탐색 키에 관련된 항목을 삭제(delete)
  * 탐색 키에 관련된 값을 탐색(search)



# 12.3. 해싱의 구조

* 해싱의 기본적인 아이디어는 **n개의 요소를 가지는 배열을 만들어서** 가장 빠르게 자료를 저장하고 꺼내는 것이다.

* 그러면 자료의 삽입과 탐색은 탐색 키를 배열의 인덱스로 생각하고 단지 배열의 특정 요소를 읽거나 쓰면 된다. 이들 연산은 **O(1)** 이다.
* 그러나 현실적으로 탐색 키들이 문자열이거나 매우 큰 숫자이기 때문에 탐색 키를 직접 배열의 인덱스로 사용하기에는 무리가 있으므로 각 탐색키를 **작은 정수로 사상(mapping)** 시키는 함수가 필요하다.
* **해싱이란** 어떤 항목의 탐색 키만을 가지고 바로 항목이 저장되어 있는 **배열의 인덱스를 결정하는 기법이다.**
* **해시 함수(hash function)** 란 탐색 키를 입력으로 받아 **해시 주소(hash address)** 를 생성하고 이 해시 주소가 배열로 구현된 **해시 테이블(hash table)** 의 인덱스가 된다.
* 해시 테이블은 M개의 **버켓(bucket)** 으로 이루어지는 테이블이고 하나의 버켓은 s개의 **슬롯(slot)** 을 가질 수 있다.
* 서로 다른 탐색 키 k1과 k2에 대하여 h(k1) == h(k2) 인 경우를 **충돌(collision)** 이라고 하며, 이러한 키 k1과 k2를 **동의어(synonym)** 이라 한다.
* 이러한 충돌이 버켓에 할당된 슬롯 수보다 많이 발생하게 되면 버켓에 더 이상 항목을 저장할 수 없게 되는 **오버플로우(overflow)** 가 발생한다.

<img src="../capture/스크린샷 2019-07-09 오후 7.31.40.png" width=400>



## 12.3.1. 이상적인 해싱

**알고리즘**

```java
add(key, value) {
  index <- hashFunction(key);
  hashTable[index] <- value;
}

search(key) {
  index <- hashFunction(key);
  return hashTable[index];
}
```



## 12.3.2. 실제의 해싱

탐색 키를 해시 테이블의 크기로 나누어서 그 나머지를 해시 테이블의 주소로 하는 것이다. 정수를 해시 테이블 크기 M으로 나누어서 나머지를 취하면 0에서 M-1까지의 숫자가 생성된다.

> h(k) = k mod M
>
> * k : 탐색키
> * M : 테이블 크기



# 12.4. 해시 함수

**좋은 해시 함수의 조건**

* 충돌이 적어야 한다.
* 해시 함수 값이 해시 테이블의 주소 영역 내에서 고르게 분포되어야 한다.
* 계산이 빨라야 한다.



## 12.4.1. 제산 함수

**제산 함수** : 나머지 연산자(mod)를 사용하여 탐색 키를 해시 테이블의 크기로 나눈 나머지를 해시 주소로 사용하는 방법이다.

> h(k) = k mod M
>
> - k : 탐색키
> - M : 테이블 크기, M은 주로 **소수(prime number)** 로 나누어지지 않는 수로 선택한다. 그리고 음수가 나오면 안된다.
> - 해시 함수의 값의 범위 : 0 ~ (M-1)

* **최종적인 해시 함수**

  ```java
  int hashFunction(int key) {
    int hashIndex = key % M;
    if (hashIndex < 0) hashIndex += M;
    return hashIndex;
  }
  ```

<img src="../capture/스크린샷 2019-07-09 오후 7.33.04.png" width=400>



## 12.4.2. 폴딩 함수

**폴딩 함수** : 탐색 키를 여러 부분으로 나누어 모두 더한 값을 해시 주소로 사용한다. 탐색 키를 나누고 더하는 방법에는 **이동 폴딩(shift folding)** 과 **경계 폴딩(boundary folding)** 이 대표적이다.

* **이동 폴딩** : 탐색 키를 여러 부분으로 나눈 값들을 더하여 해시 주소로 사용한다.
* **경계 폴딩** : 탐색 키의 이웃한 부분을 거꾸로 더하여 해시 주소를 얻는다.

<img src="../capture/스크린샷 2019-07-09 오후 7.33.39.png" width=500>



## 12.4.3. 중간 제곱 함수

**중간 제곱 함수** : 탐색 키를 제곱한 다음, 중간의 몇 비트를 취해서 해시 주소를 생성한다.



## 12.4.4. 비트 추출 방법

비트 추출 방법은 해시 테이블의 크기가 **M = 2^k** 일 때, 탐색 키를 이진수로 간주하여 임의의 위치의 k개의 비트를 해시 주소로 사용하는 것이다.



## 12.4.5. 숫자 분석 방법

숫자 분석 방법은 숫자로 구성된 키에서 각각의 위치에 있는 수의 특징을 미리 알고 있을 때 유용하다. 키의 각각의 위치에 있는 숫자 중에서 **편중되지 않은 수들을 해시 테이블의 크기에 적합한 만큼 조합하여** 해시 주소로 사용하는 방법이다.



## 12.4.6. 탐색 키가 문자열일 경우 주의할 점

가장 간단한 방법은 첫 번째 문자의 아스키 코드 값을 해시 주소로 사용하는 것이다. 그러나 "cup" 과 "car" 는 구별이 불가능하다.

충돌을 막기 위해서는 문자열 안의 모든 문자를 골고루 사용해야 한다. 가장 보편적인 방법은 각 문자의 **아스키 코드 값을 모두 더하는 것이다.** 

더 좋은 방법은 문자들의 아스키 코드 값에 위치에 기초한 값을 곱하는 것이다.즉 문자열 s가 n개의 문자를 가지고 있다고 가정하고 s 안의 i번째 문자가 u(i) 라고 하면 해시 주소를 다음과 같이 계산한다.

> u(0)g^(n-1) + u(1)g^(n-2) + … + u(n-2)g + u(n-1)

여기서 g는 양의 정수이므로 계산량을 줄이기 위하여 다음과 같은 **호너의 방법** 을 사용할 수 있다.

> (…((u(0)g + u(1))g + u(2)) + … + u(n-2))g + u(n-1)

이 방법을 함수로 만들어보면 다음과 같다.

```java
int hashFunction(char key) {
  int hashIndex = 0;
  // 오버플로우가 발생하기 전까지
  while (key > 0) {
    hashIndex = g * hashIndex + key++;
  }
  return hashIndex;
}
```



# 12.5. 충돌 해결책

**충돌(collision)** : 서로 다른 탐색 키를 갖는 항목들이 같은 해시 주소를 가지는 현상

* **해결 방법**
  * 충돌이 일어난 항목을 해시 테이블의 다른 위치에 저장한다.
  * 해시 테이블의 하나의 위치가 여러 개의 항목을 저장할 수 있도록 해시 테이블의 구조를 변경한다.



## 12.5.1. 선형 조사법

**선형 조사법** : 특정 버켓에서 충돌이 발생하면 해시 테이블에서 비어 있는 버켓을 찾는 방법이다.

* 해시 테이블에서 비어 있는 공간을 찾는 것을 **조사(probing)** 라고 하며 여러 가지 방법의 조사가 가능하다.



**구현**

* **해시 테이블 구조와 생성**

```java
package chapter13_hashing;

public class Hashing {

  private String[] hashTable;				// 해시 테이블
  private final int TABLE_SIZE;			// 테이블 크기

  // 해시 테이블 생성
  public Hashing(int size) {
    TABLE_SIZE = size;
    hashTable = new String[TABLE_SIZE];
  }
  
}
```

* **문자열 값 아스키 코드로 변환 메소드**

```java
private int transform(String str) {
  int sum = 0;

  // 문자 하나하나의 아스키 코드 값을 구해서 sum에 더한다
  for (int i = 0; i < str.length(); i++) {
    sum += str.charAt(i);
  }

  return sum;
}
```

* **해시 함수**

```java
private int hashFunction(String str) {
  // 해시 값 반환
  return transform(str) % TABLE_SIZE;
}
```

* **값 추가**

```java
public void add(String item) {
  int hashValue = hashFunction(item);
  int i = hashValue;

  // 선형 조사법을 통해서 해시 테이블의 빈 위치를 탐색한다.
  while (hashTable[i] != null) {
    if (item.equals(hashTable[i])) {
      System.out.println("탐색 키가 중복되었습니다.");
      return;
    }

    i = (i + 1) % TABLE_SIZE;

    if (i == hashValue) {
      System.out.println("테이블이 가득찼습니다.");
      return;
    }
  }

  hashTable[i] = item;
}
```

* **값 검색**

```java
public void search(String item) {
  int hashValue = hashFunction(item);
  int i = hashValue;

  // 선형 조사법을 통해서 검색 키 값과 동일한 값을 찾는다.
  while (hashTable[i] != null) {
    if (hashTable[i].equals(item)) {
      System.out.println("탐색성공 위치 : " + i);
      return;
    }
    i = (i + 1) % TABLE_SIZE;
    if (i == hashValue) {
      System.out.println("찾는 값이 테이블에 없습니다.");
      return;
    }
  }
  System.out.println("찾는 값이 테이블에 없습니다.");
}
```

* **테이블 출력**

```java
public void print() {
  for (int i = 0; i < TABLE_SIZE; i++) {
    System.out.println("[" + i + "], " + hashTable[i]);
  }
}
```

* **main 메소드**

```java
public static void main(String[] args) {
  Hashing hashing = new Hashing(10);
  Scanner scanner = new Scanner(System.in);

  while (true) {
    System.out.print("원하는 연산을 입력하세요(0=입력, 1=탐색, 2=출력, 3=종료) = ");
    int choice = scanner.nextInt();

    scanner.nextLine();

    if (choice == 0) {
      System.out.print("추가할 키를 입력하세요 = ");
      String item = scanner.nextLine();
      hashing.add(item);
    } else if (choice == 1) {
      System.out.print("검색 키를 입력하세요 = ");
      String item = scanner.nextLine();
      hashing.search(item);
    } else if (choice == 2) {
      hashing.print();
    } else {
      break;
    }
    System.out.println();
  }
}
```

* **실행 결과**

```
원하는 연산을 입력하세요(0=입력, 1=탐색, 2=출력, 3=종료) = 0
추가할 키를 입력하세요 = a

원하는 연산을 입력하세요(0=입력, 1=탐색, 2=출력, 3=종료) = 0
추가할 키를 입력하세요 = b

원하는 연산을 입력하세요(0=입력, 1=탐색, 2=출력, 3=종료) = 0
추가할 키를 입력하세요 = c

원하는 연산을 입력하세요(0=입력, 1=탐색, 2=출력, 3=종료) = 2
[0], null
[1], null
[2], null
[3], null
[4], null
[5], null
[6], null
[7], a
[8], b
[9], c

원하는 연산을 입력하세요(0=입력, 1=탐색, 2=출력, 3=종료) = 1
검색 키를 입력하세요 = a
탐색성공 위치 : 7

원하는 연산을 입력하세요(0=입력, 1=탐색, 2=출력, 3=종료) = 3
```

* **선형 조사법의 문제점**
  * 탐색 키들이 집중되어 저장되는 **군집화** 현상이 발생하게 된다.
  * 최악의 경우에는 집중된 항목들이 **결합(coaliasing)** 하는 현상까지 발생하므로 탐색 시간이 길어지는 단점이 있다.



## 12.5.2. 이차 조사법

**이차 조사법(quadratic probing)** : 선형 조사법과 유사하지만, 다음 조사할 위치를 아래의 식에 의하여 결정한다.

> (h(k) + inc * inc) mod M **for inc = 0, 1, … , M - 1**
>
> **ex) h(k), h(k) + 1, h(k) + 4, h(k) + 9**

* **주의할 점**
  * 테이블 크기는 소수여야 한다.



**구현**

* **값 추가**

```java
public void quadraticAdd(String item) {
  int hashValue = hashFunction(item);
  int i = hashValue;
  int inc = 0;

  while (hashTable[i] != null) {
    if (item.equals(hashTable[i])) {
      System.out.println("탐색 키가 중복되었습니다");
      return;
    }

    // 이차 조사법
    i = (hashValue + inc * inc) % TABLE_SIZE;
    inc += 1;

    if (i == hashValue) {
      System.out.println("테이블이 가득찼습니다.");
      return;
    }
  }

  hashTable[i] = item;
}
```

* **값 검색**

```java
public void quadraticSearch(String item) {
  int hashValue = hashFunction(item);
  int i = hashValue;
  int inc = 0;

  while (hashTable[i] != null) {
    if (item.equals(hashTable[i])) {
      System.out.println("탐색성공 위치 : " + i);
      return;
    }

    // 이차 조사법
    i = (hashValue + inc * inc) % TABLE_SIZE;
    inc += 1;

    if (i == hashValue) {
      System.out.println("찾는 값이 테이블에 없습니다.");
      return;
    }
  }
  System.out.println("찾는 값이 테이블에 없습니다.");
}
```



## 12.5.3. 이중 해싱법

**이중 해싱법(double hashing) 또는 재해싱(rehashing)** : 오버플로우가 발생함에 따라 항목을 저장할 다음 위치를 결정할 때, 원래 해시 함수와 다른 별개의 해시 함수를 이용하는 방법이다. 일반적인 함수 형태는 다음과 같다.

> Step = C - (k mod C)
>
> **C = 테이블 크기인 M 보다 약간 작은 소수**



**구현**

* **값 추가**

```java
public void doubleAdd(String item) {
  int hashValue = hashFunction(item);
  int i = hashValue;

  while (hashTable[i] != null) {
    if (hashTable[i].equals(item)) {
      System.out.println("탐색 키가 중복되었습니다.");
      return;
    }

    i = (i + TABLE_SIZE2) % TABLE_SIZE;
    if (i == hashValue) {
      System.out.println("테이블이 가득찼습니다.");
      return;
    }
  }

  hashTable[i] = item;
}
```

* **값 검색**

```java
public void doubleSearch(String item) {
  int hashValue = hashFunction(item);
  int i = hashValue;

  while (hashTable[i] != null) {
    if (hashTable[i].equals(item)) {
      System.out.println("탐색성공 위치 : " + i);
      return;
    }

    i = (i + TABLE_SIZE2) % TABLE_SIZE;
    if (i == hashValue) {
      System.out.println("찾는 값이 테이블에 없습니다.");
      return;
    }
  }
  System.out.println("찾는 값이 테이블에 없습니다.");
}
```



# 12.6. 체이닝

**체이닝(chaining)** : 오버플로 문제를 연결 리스트로 해결한다. 즉 각 버켓에 고정된 슬롯을 할당하는 것이 아니라, 각 버켓에 삽입과 삭제가 용이한 연결 리스트를 할당한다.



**구현**

* **노드 구조**

```java
package chapter13_hashing;

public class Node {
  public String item;
  public Node link;

  public Node(String item) {
    this.item = item;
  }
}
```

* **체이닝 구조**

```java
public class Chaining {

  public Node[] hashTable;
  public final int TABLE_SIZE;

  public Chaining(int size) {
    hashTable = new Node[size];
    TABLE_SIZE = size;
  }
  
}
```

* **해시 값 구하는 메소드들**

```java
private int transform(String str) {
  int sum = 0;

  for (int i = 0; i < str.length(); i++) {
    sum += str.charAt(i);
  }

  return sum;
}

private int hashFunction(String str) {
  return transform(str) % TABLE_SIZE;
}
```

* **값 추가**

```java
public void add(String item) {
  int hashValue = hashFunction(item);				// 해시 값
  Node newNode = new Node(item);						// 새로운 노드
  Node currentNode = hashTable[hashValue];	// 현재 노드
  Node beforeNode = currentNode;						// 이전 노드

  // 노드를 추가할 위치 탐색
  while (currentNode != null) {
    if (currentNode.item.equals(item)) {
      System.out.println("이미 탐색 키가 저장되어 있음");
      return;
    }
    beforeNode = currentNode;
    currentNode = currentNode.link;
  }

  // 기존의 연결 리스트가 존재하면
  if (beforeNode != null) beforeNode.link = newNode;
  // 존재하지 않으면
  else hashTable[hashValue] = newNode;
}
```

* **값 검색**

```java
// 체이닝을 이용하여 테이블에 저장된 키를 탐색
public void search(String str) {
  int hashValue = hashFunction(str);
  Node node = hashTable[hashValue];

  while (node != null) {
    if (node.item.equals(str)) {
      System.out.println("키 발견 : " + hashValue);
      return;
    }
    node = node.link;
  }

  System.out.println("키를 찾지 못했음");
}
```

* **main 메소드**

```java
public static void main(String[] args) {
  Chaining chain = new Chaining(11);

  chain.add("a");
  chain.add("b");
  chain.add("c");

  chain.search("a");
  chain.search("b");
  chain.search("d");
}
```

* **실행 결과**

```
키 발견 : 9
키 발견 : 10
키를 찾지 못했음
```