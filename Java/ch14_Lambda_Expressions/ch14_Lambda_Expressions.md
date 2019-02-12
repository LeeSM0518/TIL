# Chapter 14. 람다식

## 14.1 람다식이란?

**람다식** : **익명 함수(anonymous function)**를 생성하기 위한 식이다. 람다식을 사용하면 자바 코드가 매우 간결해지고, 컬렉션의 요소를 필터링하거나 매핑해서 원하는 결과를 쉽게 집계할 수 있다. 람다식의 형태는 매개 변수를 가진 코드 블록이지만, 런타임 시에는 **익명 구현 객체**를 생성한다.

* **예시**

  ```java
  // Runnable 익명 구현 객체
  Runnable runnable = new Runnable() {
      public void run() { ... }
  };
  
  // 람다식으로 구현
  // "(매개변수) -> {실행코드}"
  Runnable runnable = () -> { ... };
  ```



## 14.2 람다식 기본 문법

* **람다식 작성법**

  ```java
  (타입 매개변수, ...) -> { 실행문; ... }
  ```

* **예시**

  ```java
  // int 매개 변수 a의 값을 콘솔에 출력
  (int a) -> { System.out.println(a); }
  
  // 람다식에서는 매개 변수의 타입을 일반적으로 언급하지 않는다.
  (a) -> { System.out.println(a) }
  a -> System.out.println(a)
  ```

  

## 14.3 타겟 타입과 함수적 인터페이스

: 항상 클래스의 구성 멤버로 선언하기 때문에 람다식은 단순히 메소드를 선언하는 것이 아니라 이 메소드를 가지고 있는 **객체를 생성해** 낸다.

* **예시**

  ```
  인터페이스 변수 = 람다식;
  ```

  > **람다식은 인터페이스 변수에 대입된다.** 인터페이스의 익명 구현 객체를 생성한다. 여기서 대입될 인터페이스를 람다식의 **타겟 타입(target type)**이라고 한다.



### 14.3.1 함수적 인터페이스(@FunctionalInterface)

* 두 개 이상의 추상 메소드가 선언된 인터페이스는 람다식을 **이용할 수 없다!(하나의 추상 메소드가 선언된 인터페이스만 가능)** 이러한 인터페이스를 **함수적 인터페이스(functional interface)**라고 한다.
* **@FunctionalInterface 어노테이션** : 두 개 이상의 추상 메소드가 선언되지 않도록 컴파일러가 체킹



### 14.3.2 매개 변수와 리턴값이 없는 람다식

* **예제**

  MyFunctionalInterface.java(**함수적 인터페이스**)

  ```java
  package no_parameter_return_lambda;
  
  @FunctionalInterface
  public interface MyFunctionalInterface {
      public void method();
  }
  ```

  MyFunctionalInterfaceExample.java(**람다식**)

  ```java
  package no_parameter_return_lambda;
  
  public class MyFunctionalInterfaceExample {
      public static void main(String[] args) {
          MyFunctionalInterface fi;
  
          fi = () -> {
              String str = "method call1";
              System.out.println(str);
          };
          fi.method();
  
          fi = () -> {
              System.out.println("method call2");
          };
          fi.method();
  
          fi = () ->
              System.out.println("method call3");
          fi.method();
      }
  }
  ```

  **실행 결과**

  ```
  method call1
  method call2
  method call3
  ```



### 14.3.3 매개 변수가 있는 람다식

* **예제**

  MyFunctionalInterface.java

  ```java
  package parameter_lambda;
  
  @FunctionalInterface
  public interface MyFunctionalInterface {
      public void method(int x);
  }
  ```

  MyFunctionalInterfaceExample.java

  ```java
  package parameter_lambda;
  
  public class MyFunctionalInterfaceExample {
      public static void main(String[] args) {
          MyFunctionalInterface fi;
  
          fi = (x) -> {
              int result = x * 5;
              System.out.println(result);
          };
          fi.method(2);
  
          fi = (x) -> {
              System.out.println(x*5);
          };
          fi.method(2);
  
          fi = x -> System.out.println(x*5);
          fi.method(2);
      }
  }
  ```

  **실행 결과**

  ```
  10
  10
  10
  ```



### 14.3.4 리턴값이 있는 람다식

* **예제**

  MyFunctionalInterface.java

  ```java
  package return_lambda;
  
  @FunctionalInterface
  public interface MyFunctionalInterface {
      public int method(int x, int y);
  }
  ```

  MyFunctionalInterfaceExample.java

  ```java
  package return_lambda;
  
  import static java.lang.Integer.sum;
  
  public class MyFunctionalInterfaceExample {
      public static void main(String[] args) {
          MyFunctionalInterface fi;
  
          fi = (x, y) -> {
              int result = x + y;
              return result;
          };
          System.out.println(fi.method(2, 5));
  
          fi = (x, y) -> { return x + y; };
          System.out.println(fi.method(2, 5));
  
          fi = (x, y) -> x + y;
          System.out.println(fi.method(2, 5));
  
          fi = (x, y) -> sum(x, y);
          System.out.println(fi.method(2,5));
      }
  }
  ```

  **실행 결과**

  ```
  7
  7
  7
  7
  ```



## 14.4 클래스 멤버와 로컬 변수 사용

: 람다식의 실행 블록에는 클래스의 멤버(필드와 메소드) 및 로컬 변수를 사용할 수 있다.



### 14.4.1 클래스의 멤버 사용

* **멤버 사용 제약 사항**

  * **this 키워드**를 사용할 때에는 주의가 필요하다.

* **예제**

  MyFunctionalInterface.java

  ```java
  package class_member_using;
  
  @FunctionalInterface
  public interface MyFunctionalInterface {
      public void method();
  }
  ```

  UsingThis.java

  ```java
  package class_member_using;
  
  public class UsingThis {
      public int outterField = 10;
  
      class Inner {
          int innerField = 20;
  
          void method() {
              // 람다식
              MyFunctionalInterface fi = () -> {
                  System.out.println("outterField: " + outterField);
  
                  // 바깥 객체의 참조를 얻기 위해서는
                  // 클래스명.this 를 사용
                  System.out.println("outterField: " + UsingThis.this.outterField +
                          "\n");
  
                  System.out.println("innerField: " +
                          innerField);
  
                  // 람다식 내부에서는 this 는
                  // Inner 객체를 참조
                  System.out.println("innerField: " +
                          this.innerField + "\n");
              };
              fi.method();
          }
      }
  }
  ```

  UsingThisExample.java

  ```java
  package class_member_using;
  
  public class UsingThisExample {
      public static void main(String[] args) {
          UsingThis usingThis = new UsingThis();
          UsingThis.Inner inner = usingThis.new Inner();
          inner.method();
      }
  }
  ```

  **실행 결과**

  ```
  outterField: 10
  outterField: 10
  
  innerField: 20
  innerField: 20
  ```

  

### 14.4.2 로컬 변수 사용

: 람다식에서 바깥 클래스의 필드나 메소드는 제한 없이 사용할 수 있으나, 매개 변수 또는 로컬 변수를 사용하면 이 두 변수는 **final 특성을** 가져야 한다. 따라서 람다식 내부 또는 외부에서 변경할 수 없다.

* **예제**

  MyFunctionalInterface.java

  ```java
  package local_variable_using;
  
  @FunctionalInterface
  public interface MyFunctionalInterface {
      public void method();
  }
  ```

  UsingLocalVariable.java

  ```java
  package local_variable_using;
  
  public class UsingLocalVariable {
  
      // arg 는 final 특성을 가짐
      void method(int arg) {
  
          // localVar 는 final 특성을 가짐
          int localVar = 40;
  
          // arg = 31;        // final 특성 때문에 수정 불가
          // localVar = 41;   // final 특성 때문에 수정 불가
  
          // 람다식
          MyFunctionalInterface fi = () -> {
              // 로컬 변수 읽기
              System.out.println("arg: " + arg);
              System.out.println("localVar: " + localVar + '\n');
          };
          fi.method();
      }
  }
  ```

  UsingLocalVariableExample.java

  ```java
  package local_variable_using;
  
  public class UsingLocalVariableExample {
      public static void main(String[] args) {
          UsingLocalVariable ulv = new UsingLocalVariable();
          ulv.method(20);
      }
  }
  ```

  **실행 결과**

  ```
  arg: 20
  localVar: 40
  ```



## 14.5 표준 API의 함수적 인터페이스

: 자바에서 제공되는 표준 API에서 **한 개의 추상 메소드**를 가지는 인터페이스들은 모두 람다식을 이용해서 익명 구현 객체로 표현 가능.

* **예제(Runnable 인터페이스의 run() 람다식)**

  ```java
  package api_functional_interface;
  
  public class RunnableExample {
      public static void main(String[] args) {
          
          // 람다식(스레드가 실행하는 코드, run() 메소드)
          Runnable runnable = () -> {
              for(int i=0; i<10; i++) {
                  System.out.println(i);
              }
          };
  
          Thread thread = new Thread(runnable);
          thread.start();
      }
  }
  ```

  **실행 결과**

  ```
  0
  1
  2
  3
  4
  5
  6
  7
  8
  9
  ```



* **java.util.function 표준 API 패키지**

  | 종류      | 추상 메소드 특징                                             |
  | --------- | ------------------------------------------------------------ |
  | Consumer  | - 매개값은 있고, 리턴값은 없음                               |
  | Supplier  | - 매개값은 없고, 리턴값은 있음                               |
  | Function  | - 매개값도 있고, 리턴값도 있음<br />- 주로 매개값을 리턴값으로 매핑(타입 변환) |
  | Operator  | - 매개값도 있고, 리턴값도 있음<br />- 주로 매개값을 연산하고 결과를 리턴 |
  | Predicate | - 매개값은 있고, 리턴 타입은 boolean<br />- 매개값을 조사해서 true/false를 리턴 |



### 14.5.1 Consumer 함수적 인터페이스

: 리턴값이 없는 **accept() 메소드**를 가지고 있다. **accept() 메소드**는 단지 매개값을 소비하는 역할만 한다.

| 인터페이스명          | 추상 메소드                    | 설명                           |
| --------------------- | ------------------------------ | ------------------------------ |
| Consumer\<T>          | void accept(T t)               | 객체 T를 받아 소비             |
| BiConsumer\<T,U>      | void accept(T t, U u)          | 객체 T와 U를 받아 소비         |
| DoubleConsumer        | void accept(double value)      | double 값을 받아 소비          |
| intConsumer           | void accept(int value)         | int 값을 받아 소비             |
| LongConsumer          | void accept(long value)        | long 값을 받아 소비            |
| ObjDoubleConsumer\<T> | void accept(T t, double value) | 객체 T와 double 값을 받아 소비 |
| ObjIntConsumer\<T>    | void accept(T t, int value)    | 객체 T와 int 값을 받아 소비    |
| ObjLongConsumer\<T>   | void accept(T t, long value)   | 객체 T와 long 값을 받아 소비   |



* **람다식 예시**

  Consumer\<T> 인터페이스

  ```java
  // 람다식의 t 매개 변수 타입은 String이 된다.
  Consumer<String> consumer = t -> { t를 소비하는 실행문; }
  ```

  BiConsumer\<T,U> 인터페이스

  ```java
  // 람다식의 t와 u 매개 변수 타입은 각각 String이 된다.
  BiConsumer<String, String> consumer = (t, u) -> { t와 u를 소비하는 실행문; }
  ```

  DoubleConsumer 인터페이스

  ```java
  // d는 고정적으로 double 타입이 된다.
  DoubleConsumer consumer = d -> { d를 소비하는 실행문; }
  ```

  ObjIntConsumer\<T> 인터페이스

  ```java
  // 람다식의 t 매개 변수 타입은 String이 되고,
  // i는 고정적으로 int 타입이 된다.
  ObjIntConsumer<String> consumer = (t, i) -> { t와 i를 소비하는 실행문; }
  ```

  

* **예제**

  ```java
  package consumer_functional_interface;
  
  import java.util.function.BiConsumer;
  import java.util.function.Consumer;
  import java.util.function.DoubleConsumer;
  import java.util.function.ObjIntConsumer;
  
  public class ConsumerExample {
      public static void main(String[] args) {
          Consumer<String> consumer =
                  t -> System.out.println(t + "8");
          consumer.accept("java");
  
          BiConsumer<String, String> biConsumer = (t, u) ->
                  System.out.println(t + u);
          biConsumer.accept("Java", "8");
  
          DoubleConsumer doubleConsumer = d ->
                  System.out.println("Java" + d);
          doubleConsumer.accept(8.0);
  
          ObjIntConsumer<String> objIntConsumer = (t, i) ->
                  System.out.println(t + i);
          objIntConsumer.accept("Java", 8);
      }
  }
  ```

  **실행 결과**

  ```
  java8
  Java8
  Java8.0
  Java8
  ```



### 14.5.2 Supplier 함수적 인터페이스

: 매개 변수가 없고 리턴값이 있는 getXXX() 메소드를 가지고 있다. 이 메소드들은 실행 후 호출한 곳으로 **데이터를 리턴(공급)하는 역할**을 한다.

* **Supplier 함수적 인터페이스**

  | 인터페이스명    | 추상 메소드            | 설명              |
  | --------------- | ---------------------- | ----------------- |
  | Supplier\<T>    | T get()                | T 객체를 리턴     |
  | BooleanSupplier | boolean getAsBoolean() | boolean 값을 리턴 |
  | DoubleSupplier  | double getAsDouble()   | double 값을 리턴  |
  | IntSupplier     | int getAsInt()         | int 값을 리턴     |
  | LongSupplier    | long getAsLong()       | long 값을 리턴    |

  