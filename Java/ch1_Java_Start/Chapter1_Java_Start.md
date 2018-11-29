# Chapter 1. 자바 시작하기



## 1 ) Hello 프로그램 소스 분석

* 자바 실행 프로그램은 반드시 **클래스(class) ** 블록과 **main() 메소드(method)** 블록으로 구성되어야 한다.

  - **클래스** : 필드 또는 메소드를 포함하는 블록

  - **메소드** : 어떤 일을 처리하는 실행문들을 모아 놓은 블록

* 메소드 블록은 단독으로 작성될 수 없고 항상 **클래스 블록 내부**에서 작성되어야 한다.



* **클래스 블록**

  ```java
  public class Hello{		// 'Hello' = 클래스 이름
      // 클래스 블록
  }
  ```



* **메소드 블록**

  ```java
  public static void main(String[] args){		// 'main' = 메소드 이름
      // 메소드 블록
      System.out.println("Hello, welcome to the java world!")
  }
  ```


* **Hello프로그램 코드**

  ```java
  public class Hello{
      public static void main(String[] args){
          // 괄호( ) 안에 있는 문자열 출력
          System.out.println("Hello, welcome to the java world!");
      }
  }
  ```

  **실행결과**

  ```
  Hello, welcome to the java world!
  ```





## 2 ) 주석과 실행문

### 2.1 ) 주석

| 주석 기호 |                             설명                             |
| :-------: | :----------------------------------------------------------: |
|    //     |       // 부터 라인 끝까지 주석으로 처리한다. (행 주석)       |
|  /* ~ */  | /* 와 */ 사이에 있는 모든 범위를 주석으로 처리한다. (범위 주석) |

> 문자열 내부에는 주석이 쓰일 수 없다!



### 2.2 ) 실행문과 세미콜론

* **실행문과 세미콜론 예제 코드**

  ```java
  public class RunStatementExample {
      public static void main(String[] args){
          int x = 1;
          int y = 2;
          int result = x + y;
          System.out.println(x + "+" + y + "=" + result);
      }
  }
  ```

  **실행 결과**

  ```
  1+2=3
  ```
