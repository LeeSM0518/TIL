# Chapter 02. 재귀 호출

재귀 호출의 기본 개념은 **자기 자신을 다시 호출하는 것** 이다.



**재귀 호출이 필요한 이유**

1. 비선형 자료구조의 모든 노드를 한 번씩 방문하는 **탐색(traversal)** 을 위한 알고리즘에서 재귀 호출을 사용한다.

2. **복잡한 문제를 쉽게 해결하기 위한** 문제 해결 기법으로 사용된다. 

   * **예시**

     1. 무척 크고 어려운 문제가 있다.
     2. 재귀 호출 기법을 사용하여 문제가 작아지도록 만든다.
     3. 계속 재귀 호출하여 매우 작은 문제로 만든다.

     > 이처럼 복잡한 문제를 작고 단순한 문제로 나누어서 해결하려는 방법을 **분할 정복(divide and conquer)** 이라고 한다.



# 1. 재귀 호출이란?

재귀 호출의 호출이란? **함수를 실행하는 것이다.**



## 1.1. 재귀 호출의 2가지 조건

**재귀 호출이 무한 루프에 빠지지 않기 위한 2가지 조건**

1. 호출될 때마다 문제의 범위가 줄어들어야 한다.
2. 종료 조건이 있어야 한다.

> 재귀 호출에서 주의해야 할 점은 **무한 루프(infinite loop)** 에 빠지지 않도록 구현해야 한다.



* **문제의 범위를 줄여나가는 예시**

  * **팩토리얼**

    ```java
    int factorial(int n) {
      int ret = 0;
      
      if (n <= 1) {		// 종료 조건 조사
        ret = 1;
      } else {
        // 종료 조건이 아니라면 문제의 범위를 감소시킨 다음 자신을 다시 호출
        ret = n * factorial(n-1);
      }
      
      return ret;
    }
    ```

    > **종료 조건(terminate condition)** : 문제가 더 이상 나눌 수 없는 가장 단위가 되었을 때 재귀 호출이 끝나도록 한다.



## 1.2. 재귀 호출의 호출 과정

* **factorial(3) 호출 예제**

  ```java
  package chapter08_recursive_calls;
  
  public class FactorialClass {
  
      static int factorial(int n) {
          int ret = 0;
  
          if (n <= 1) ret = 1;
          else ret = n * factorial(n-1);
  
          return ret;
      }
  
      public static void main(String[] args) {
          int inputValue = 3;       
          int result = factorial(inputValue);
  
          System.out.println(result);
      }
  
  }
  ```

  > 1. **factorial(3)** : 3 x factorial(2)
  > 2. **factorial(2)** : 2 x factorial(2)
  > 3. **factorial(1)** : 1
  >
  > 이러한 과정으로 재귀 호출이 이뤄지고 값은 3 x 2 x 1 = 6 이 반환된다.



* **재귀 호출의 OS시점**
  * 운영체제에서는 스택(stack)을 이용하여 재귀 호출을 실행한다. 
  * 함수를 호출할 때 함수에서 사용되는 **모든 지역 변수(local variable)** 와 **전달된 인자(parameter)** 등을 저장하는 공간을 **활성 레코드(activation record)** 라고 한다.
  * 운영체제에서는 하나의 함수가 호출되면 호출되는 함수별로 이러한 활성 레코드에 함수 관련 정보를 저장한다.
  * 만약, **도중에 다른 함수가 호출되면** 해당 함수의 활성 레코드로 변경시키는데 이를 **문맥 변경(context switching)** 이라고 한다.
  * 재귀 호출을 실행했을 때 종료 조건을 만나기 전까지 시스템 스택에 활성 레코드를 계속 푸시하여 저장한다. 종료 조건을 만나게 되면 활성 레코드를 다시 팝하여 다음에 실행할 함수를 로딩한다.
* **재귀 호출의 단점**
  1. **상대적으로 속도가 느리다** : 문맥 변경에 추가적인 시간이 필요하다.
  2. **함수 호출 횟수에 제한이 있다** : 운영체제의 스택 크기에 제한이 있다.
     * 재귀 호출의 호출 횟수 초과 시 운영체제에서 오류가 발생 : 스택 오버플로(Stack Overflow)



# 2. 재귀 호출과 반복 호출

**반복 호출** : for 나 while 문 등을 이용하여 반복적으로 명령을 실행하여 문제를 해결하는 방법.

| 구분 | 재귀 호출                     | 반복 호출                                |
| ---- | ----------------------------- | ---------------------------------------- |
| 장점 | 알고리즘이 간결하며 정확하다. | 속도가 빠르고 시스템 메모리 사용이 작다. |



## 2.1. 팩토리얼 함수

**꼬리 재귀 호출(tail recursion)** : 재귀 호출이 함수의 끝에서 이루어지는 방법

* **팩토리얼**

  재귀 호출 방식

  ```java
  int factorial(int n) {
    int ret = 0;
  
    if (n <= 1) ret = 1;
    else ret = n * factorial(n-1);
  
    return ret;
  }
  ```

  반복 호출 방식

  ```java
  int repeatFactorial(int n) {
    int ret = 1;
  
    for(int i = n; i > 1; i--)
      ret = ret * i;
  
    return ret;
  }	
  ```

> 팩토리얼은 꼬리 재귀 호출 방식이다. 즉 재귀 호출의 끝 부분에 단 한번만 있는 경우이다.



## 2.2. 피보나치 수열

* **피보나치 수열 정의**

  <img src="../capture/스크린샷 2019-05-02 오후 8.20.05.png" width="500">

* **코드**

  **재귀 호출 방식**

  ```java
  int recFib (int n) {
    int ret;
  
    if (n == 0)
      ret = 0;
    else  if (n == 1)
      ret = 1;
    else 
      ret = recFib(n-1) + recFib(n-2);
  
    return ret;
  }
  ```

  > 피보나치 수열을 재귀 호출 방식으로 구현하면 입력 파라미터가 증가함에 따라 불필요한 중복 호출의 비중이 높다. 즉, **낭비되는 함수의 호출 횟수가 비약적으로 증가하여** 계산에 걸리는 시간 또한 급격히 증가하게 된다.

  * **시간 복잡도**
    $$
    O(2^n)
    $$
    이기 때문에 알고리즘의 시간 복잡도가 지수적(expontential) 으로 증가한다.

  **반복 호출 방식**

  ```java
  int repFib (int n) {
    int ret;
  
    if (n < 2) {
      ret = n;
    } else {
      int temp = 0, current = 1, last = 0;
  
      for (int i = 2; i <= n; i++) {
        temp = current;
        current += last;
        last = temp;
      }
  
      ret = current;
    }
  
    return ret;
  }
  ```



## 2.3. 하노이의 탑

재귀를 통해서만 문제를 쉽게 해결 할 수 있는 가장 대표적인 예.

* **하노이의 탑 제약 조건**
  1. 한 번에 하나의 원판만 이동할 수 있다.
  2. 맨 위에 있는 원판만 이동할 수 있다.
  3. 크기가 작은 원판 위에 큰 원판이 있을 수 없다.
  4. 중간 막대를 이용할 수 있으나 앞의 3가지 조건을 만족해야 한다.

* **의사 코드(Pseudo Code)**

  ```java
  void hanoi_tower (int n, char from, char to, char temp) {
    if (n == 1) {
      from에서 to로 원판을 옮긴다.
    } else {
      Step-a. from의 맨 밑에 원판을 제외한 나머지 원판들을 temp로 옮긴다.
      Step-b. from에 있는 한 개의 원판을 to로 옮긴다.
      Step-c. temp의 원판들을 to로 옮긴다.
    }
  }
  ```

* **코드**

  ```java
  package chapter08_recursive_calls;
  
  public class hanoiTowerClass {
  
      static void hanoiTower(int n, String from, String to, String temp) {
  
          if (n == 1) {
              System.out.println("원판 1 을 " + from + " 에서 " + to + " 로 옮겼습니다.");
          } else {
              hanoiTower(n - 1, from, to, temp);
              System.out.println("원판 " + n + " 를 " + from + " 에서 " + to + " 로 옮겼습니다.");
              hanoiTower(n - 1, temp, from, to);
          }
  
      }
  
      public static void main(String[] args) {
          String[] abc = new String[3];
          abc[0] = "A";
          abc[1] = "B";
          abc[2] = "C";
  
          hanoiTower(4, abc[0], abc[1], abc[2]);
      }
  
  }
  ```

  **실행 결과**

  ```
  원판 1 을 A 에서 B 로 옮겼습니다.
  원판 2 를 A 에서 B 로 옮겼습니다.
  원판 1 을 C 에서 A 로 옮겼습니다.
  원판 3 를 A 에서 B 로 옮겼습니다.
  원판 1 을 C 에서 A 로 옮겼습니다.
  원판 2 를 C 에서 A 로 옮겼습니다.
  원판 1 을 B 에서 C 로 옮겼습니다.
  원판 4 를 A 에서 B 로 옮겼습니다.
  원판 1 을 C 에서 A 로 옮겼습니다.
  원판 2 를 C 에서 A 로 옮겼습니다.
  원판 1 을 B 에서 C 로 옮겼습니다.
  원판 3 를 C 에서 A 로 옮겼습니다.
  원판 1 을 B 에서 C 로 옮겼습니다.
  원판 2 를 B 에서 C 로 옮겼습니다.
  원판 1 을 A 에서 B 로 옮겼습니다.
  ```

  