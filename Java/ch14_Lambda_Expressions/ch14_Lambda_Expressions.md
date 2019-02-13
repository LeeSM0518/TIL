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

* **예제**

  ```java
  package supplier_functional_interface;
  
  import java.util.function.IntSupplier;
  
  public class SupplierExample {
      public static void main(String[] args) {
          // 람다식
          IntSupplier intSupplier = () -> {
              int num = (int) (Math.random() * 6) + 1;
              return num;
          };
  
          int num = intSupplier.getAsInt();
          System.out.println("눈의 수: " + num);
      }
  }
  ```

  **실행 결과**

  ```
  눈의 수: 3
  ```



### 14.5.3 Function 함수적 인터페이스

: 매개값과 리턴값이 있는 메소드를 가지고 있다.

* **Function 함수적 인터페이스들**

  | 인터페이스명             | 추상 메소드                      | 설명                       |
  | ------------------------ | -------------------------------- | -------------------------- |
  | Function\<T,R>           | R apply(T t)                     | 객체 T를 객체 R로 매핑     |
  | BiFunction\<T,U,R>       | R apply(T t, U u)                | 객체 T와 U를 객체 R로 매핑 |
  | DoubleFunction\<R>       | R apply(double value)            | double을 객체 R로 매핑     |
  | IntFunction\<R>          | R apply(int value)               | int를 객체 R로 매핑        |
  | IntToDoubleFunction      | double applyAsDouble(int value)  | int를 double로 매핑        |
  | IntToLongFunction        | long applyAsLong(int value)      | int를 long으로 매핑        |
  | LongToDoubleFunction     | double applyAsDouble(long value) | long을 double로 매핑       |
  | LongToIntFunction        | int applyAsInt(long value)       | long을 int로 매핑          |
  | ToDoubleBiFunction\<T,U> | double applyAsDouble(T t, U u)   | 객체 T와 U를 double로 매핑 |
  | ToDoubleFunction\<T>     | double applyAsDouble(T t)        | 객체 T를 double로 매핑     |
  | ToIntBiFunction\<T,U>    | int applyAsInt(T t, U u)         | 객체 T와 U를 int로 매핑    |
  | ToIntFunction\<T>        | int applyAsInt(T t)              | 객체 T를 int로 매핑        |
  | ToLongBiFunction\<T,U>   | long applyAsLong(T t, U u)       | 객체 T와 U를 long으로 매핑 |
  | ToLongFunction\<T>       | long applyAsLong(T t)            | 객체 T를 long으로 매핑     |

* **예제**

  Student.java

  ```java
  package function_functional_interface;
  
  public class Student {
      private String name;
      private int englishScore;
      private int mathScore;
  
      public Student(String name, int englishScore, int mathScore) {
          this.name = name;
          this.englishScore = englishScore;
          this.mathScore = mathScore;
      }
  
      public String getName() { return name; }
      public int getEnglishScore() { return englishScore; }
      public int getMathScore() { return mathScore; }
  }
  ```

  FunctionExample1.java

  ```java
  package function_functional_interface;
  
  import java.util.Arrays;
  import java.util.List;
  import java.util.function.Function;
  import java.util.function.ToIntFunction;
  
  public class FunctionExample1 {
      private static List<Student> list = Arrays.asList(
              new Student("홍길동", 90, 96),
              new Student("신용권", 95, 93)
      );
  
      public static void printString(Function<Student, String> function) {
          for(Student student : list) {
              System.out.println(function.apply(student) + " ");
          }
          System.out.println();
      }
  
      public static void printInt(ToIntFunction<Student> function) {
          for(Student student : list) {
              System.out.println(function.applyAsInt(student) + " ");
          }
          System.out.println();
      }
  
      public static void main(String[] args) {
          System.out.println("[학생 이름]");
          printString(t -> t.getName());
  
          System.out.println("[영어 점수]");
          printInt(t -> t.getEnglishScore());
  
          System.out.println("[수학 점수]");
          printInt(t -> t.getMathScore());
      }
  }
  ```

  **실행 결과**

  ```
  [학생 이름]
  홍길동 
  신용권 
  
  [영어 점수]
  90 
  95 
  
  [수학 점수]
  96 
  93
  ```

  FunctionExample2.java

  ```java
  package function_functional_interface;
  
  import java.util.Arrays;
  import java.util.List;
  import java.util.function.ToIntFunction;
  
  public class FunctionExample2 {
      private static List<Student> list = Arrays.asList(
              new Student("홍길동", 90, 96),
              new Student("신용권", 95, 93)
      );
  
      public static double avg(ToIntFunction<Student> function) {
          int sum = 0;
          for(Student student : list) {
              sum += function.applyAsInt(student);
          }
          double avg = (double) sum / list.size();
          return avg;
      }
  
      public static void main(String[] args) {
          double englishAvg = avg( s -> s.getEnglishScore());
          System.out.println("영어 평균 점수: " + englishAvg);
  
          double mathAvg = avg( s -> s.getMathScore());
          System.out.println("수학 평균 점수: " + mathAvg);
      }
  }
  ```

  **실행 결과**

  ```
  영어 평균 점수: 92.5
  수학 평균 점수: 94.5
  ```



### 14.5.4 Operator 함수적 인터페이스

: 매개값을 이용해서 연산을 수행한 후 동일한 타입으로 리턴값을 제공하는 역할을 한다.

* **Operator 인터페이스들**

  | 인터페이스명         | 추상 메소드                          | 설명                     |
  | -------------------- | ------------------------------------ | ------------------------ |
  | BinaryOperator\<T>   | T apply(T t, T t)                    | T와 T를 연산한 후 T 리턴 |
  | UnaryOperator\<T>    | T apply(T t)                         | T를 연산한 후 T 리턴     |
  | DoubleBinaryOperator | double applyAsDouble(double, double) | 두 개의 double 연산      |
  | DoubleUnaryOperator  | double applyAsDouble(double)         | 한 개의 double 연산      |
  | IntBinaryOperator    | int applyAsInt(int, int)             | 두 개의 int 연산         |
  | IntUnaryOperator     | int applyAsInt(int)                  | 한 개의 int 연산         |
  | LongBinaryOperator   | long applyAsLong(long,long)          | 두 개의 long 연산        |
  | LongUnaryOperator    | long applyAsLong(long)               | 한 개의 long 연산        |

* **예제**

  ```java
  package operator_functional_interface;
  
  import java.util.function.IntBinaryOperator;
  
  public class OperatorExample {
      private static int[] scores = { 92, 95, 87 };
  
      public static int maxOrMin(IntBinaryOperator operator) {
          int result = scores[0];
          for(int score : scores) {
              result = operator.applyAsInt(result, score);
          }
          return result;
      }
  
      public static void main(String[] args) {
          // 최대값 얻기
          int max = maxOrMin(
                  (a,b) -> {
                      if(a>=b) return a;
                      else return b;
                  }
          );
          System.out.println("최대값: " + max);
  
          // 최소값 얻기
          int min = maxOrMin(
                  (a,b) -> {
                      if(a<=b) return a;
                      else return b;
                  }
          );
          System.out.println("최소값: " + min);
      }
  }
  ```



### 14.5.5 Predicate 함수적 인터페이스

: 매개 변수와 boolean 리턴값이 있는 인터페이스

* **Predicate 함수적 인터페이스들**

  | 인터페이스명      | 추상 메소드                | 설명                   |
  | ----------------- | -------------------------- | ---------------------- |
  | Predicate\<T>     | boolean test(T t)          | 객체 T를 조사          |
  | BiPredicate\<T,U> | boolean test(T t, U u)     | 객체 T와 U를 비교 조사 |
  | DoublePredicate   | boolean test(double value) | double 값을 조사       |
  | IntPredicate      | boolean test(int value)    | int 값을 조사          |
  | LongPredicate     | boolean test(long value)   | long 값을 조사         |

* **예제**

  **Student.java**

  ```java
  package predicate_functional_interface;
  
  public class Student {
      private String name;
      private String sex;
      private int score;
  
      public Student(String name, String sex, int score) {
          this.name = name;
          this.sex = sex;
          this.score = score;
      }
  
      public String getSex() { return sex; }
      public int getScore() { return score; }
  }
  ```

  **PredicateExample.java**

  ```java
  package predicate_functional_interface;
  
  import java.util.Arrays;
  import java.util.List;
  import java.util.function.Predicate;
  
  public class PredicateExample {
      private static List<Student> list = Arrays.asList(
              new Student("홍길동", "남자", 90),
              new Student("김순희", "여자", 90),
              new Student("김자바", "남자", 95),
              new Student("박한나", "여자", 92)
      );
  
      public static double avg(Predicate<Student> predicate) {
          int count = 0, sum = 0;
          for(Student student : list) {
              if(predicate.test(student)) {
                  count++;
                  sum += student.getScore();
              }
          }
          return (double) sum / count;
      }
  
      public static void main(String[] args) {
          // Predicate<Student> 인스턴스 대입
          double maleAvg = avg( t -> t.getSex().equals("남자") );
          System.out.println("남자 평균 점수 : " + maleAvg);
  
          // Predicate<Student> 인스턴스 대입
          double femaleAvg = avg( t -> t.getSex().equals("여자"));
          System.out.println("여자 평균 점수 : " + femaleAvg);
      }
  }
  ```

  **실행 결과**

  ```
  남자 평균 점수 : 92.5
  여자 평균 점수 : 91.0
  ```



### 14.5.6 andThen()과 compose() 디폴트 메소드

* **함수적 인터페이스 성질** : 하나의 추상 메소드를 가지고 있고, 람다식으로 익명 구현 객체를 생성할 수 있는 것.

* **andThen()과 compose() 디폴트 메소드(Consumer, Function, Operator 인터페이스)**

  * 두 개의 함수적 인터페이스를 순차적으로 연결한다.
  * 첫 번째 처리 결과를 두 번째 매개값으로 제공해서 최종 결과값을 얻을 때 사용.

* **andThen() 메소드**

  ```java
  인터페이스AB = 인터페이스A.andThen(인터페이스B);
  최종결과 = 인터페이스AB.method();
  ```

  > 인터페이스AB의 method()를 호출하면 우선 인터페이스A부터 처리하고 결과를 인터페이스B의 매개값으로 제공한다. 인터페이스B는 제공받은 매개값을 가지고 처리한 후 최종 결과를 리턴한다.

* **compose() 메소드**

  ```java
  인터페이스AB = 인터페이스A.compose(인터페이스B);
  최종결과 = 인터페이스AB.method();
  ```

  > 인터페이스AB의 method()를 호출하면 우선 인터페이스B부터 처리하고 결과를 인터페이스A의 매개값으로 제공한다. 인터페이스A는 제공받은 매개값을 가지고 처리한 후 최종 결과를 리턴한다.

* **andThen()과 compose() 디폴트 메소드를 제공 여부**

  | 종류     | 함수적 인터페이스   | andThen() | compose() |
  | -------- | ------------------- | --------- | --------- |
  | Consumer | Consumer\<T>        | O         |           |
  |          | BiConsumer\<T,U>    | O         |           |
  |          | DoubleConsumer      | O         |           |
  |          | IntConsumer         | O         |           |
  |          | LongConsumer        | O         |           |
  | Fuction  | Fuction\<T,R>       | O         | O         |
  |          | BiFuction\<T,U,R>   | O         |           |
  | Operator | BinaryOperator\<T>  | O         |           |
  |          | DoubleUnaryOperator | O         | O         |
  |          | IntUnaryOperator    | O         | O         |
  |          | LongUnaryOperator   | O         | O         |



#### Consumer의 순차적 연결

: andThen() 디폴트 메소드는 함수적 인터페이스의 호출 순서만 정한다.

* **예제**

  **Address.java**

  ```java
  package andthen_default_method;
  
  public class Address {
      private String country;
      private String city;
  
      public Address(String country, String city) {
          this.country = country;
          this.city = city;
      }
  
      public String getCountry() { return country; }
      public String getCity() { return city; }
  }
  ```

  **Member.java**

  ```java
  package andthen_default_method;
  
  public class Member {
      private String name;
      private String id;
      private Address address;
  
      public Member(String name, String id, Address address) {
          this.name = name;
          this.id = id;
          this.address = address;
      }
  
      public String getName() { return name; }
      public String getId() { return id; }
      public Address getAddress() { return address; }
  }
  ```

  **ConsumerAndThenExample.java**

  ```java
  package andthen_default_method;
  
  import java.util.function.Consumer;
  
  public class ConsumerAndThenExample {
      public static void main(String[] args) {
          // 람다식으로 consumerA 선언
          Consumer<Member> consumerA = (m) -> {
              System.out.println("consumerA: " + m.getName());
          };
  
          // 람다식으로 consumerB 선언
          Consumer<Member> consumerB = (m) -> {
              System.out.println("consumerB: " + m.getId());
          };
  
          // consumerA와 consumerB를 순차적 연결
          Consumer<Member> consumerAB = consumerA.andThen(consumerB);
          
          // consumerAB의 accept 메소드 실행
          consumerAB.accept(new Member("홍길동", "hong", null));
      }
  }
  ```

  **실행 결과**

  ```
  consumerA: 홍길동
  consumerB: hong
  ```



#### Function의 순차적 연결

: Function과 Operator 종류의 함수적 인터페이스는 먼저 실행한 함수적 인터페이스의 결과를 다음 함수적 인터페이스의 매개값으로 넘겨주고, 최종 처리 결과를 리턴한다.

* **예제**

  ```java
  package compose_default_method;
  
  import andthen_default_method.Address;
  import andthen_default_method.Member;
  
  import java.util.function.Function;
  
  public class FunctionAndThenComposeExample {
      public static void main(String[] args) {
          // Function 함수적 인터페이스 선언
          Function<Member, Address> functionA;
          Function<Address, String> functionB;
          Function<Member, String> functionAB;
          String city;
  
          // 주소를 가져오는 람다식 선언
          functionA = (m) -> m.getAddress();
          // 도시를 가져오는 람다식 선언
          functionB = (a) -> a.getCity();
  
          // A와 B를 순차적으로 연결(A 먼저 실행)
          functionAB = functionA.andThen(functionB);
          city = functionAB.apply(
                  new Member("홍길동", "hong", new Address(
                          "한국", "서울"
                  ))
          );
          System.out.println("거주 도시: " + city);
  
          // A와 B를 순차적으로 연결(B 먼저 실행)
          functionAB = functionB.compose(functionA);
          city = functionAB.apply(
                  new Member("홍길동", "hong", new Address(
                          "한국", "서울"
                  ))
          );
          System.out.println("거주 도시: " + city);
      }
  }
  ```



### 14.5.7 and(), or(), negate() 디폴트 메소드와 isEqual() 정적 메소드

: and(), or(), negate()는 디폴트 메소드이다. 이 메소드들은 각각 논리 연산자인 &&, ||, ! 과 대응된다고 볼 수 있다.

* **and(), or(), negate() 디폴트 메소드를 제공하는 Predicate 함수적 인터페이스들**

  | 종류          | 함수적 인터페이스 |
  | ------------- | ----------------- |
  | **Predicate** | Predicate\<T>     |
  |               | BiPredicate\<T,U> |
  |               | DoublePredicate   |
  |               | IntPredicate      |
  |               | LongPredicate     |

* **예제**

  ```java
  package and_or_negate_default_method;
  
  import java.util.function.IntPredicate;
  
  public class PredicateAndOrNegateExample {
      public static void main(String[] args) {
          // 2의 배수 검사
          IntPredicate predicateA = a -> a%2 == 0;
  
          // 3의 배수 검사
          IntPredicate predicateB = a -> a%3 == 0;
  
          IntPredicate predicateAB;
          boolean result;
  
          // and()
          predicateAB = predicateA.and(predicateB);
          result = predicateAB.test(9);
          System.out.println("9는 2와 3의 배수입니까? " + result);
  
          // or()
          predicateAB = predicateA.or(predicateB);
          result = predicateAB.test(9);
          System.out.println("9와 2 또는 3의 배수입니까? " + result);
  
          // negate()
          predicateAB = predicateA.negate();
          result = predicateAB.test(9);
          System.out.println("9는 홀수입니까? " + result);
      }
  }
  ```

  **실행 결과**

  ```
  9는 2와 3의 배수입니까? false
  9와 2 또는 3의 배수입니까? true
  9는 홀수입니까? true
  ```

  

* **isEqual() 메소드 예제**

  ```java
  package isequals_method;
  
  import java.util.function.Predicate;
  
  public class PredicateIsEqualExample {
      public static void main(String[] args) {
          Predicate<String> predicate;
  
          predicate = Predicate.isEqual(null);
          System.out.println("null, null : " + predicate.test(null));
  
          predicate = Predicate.isEqual("Java8");
          System.out.println("null, Java8 : " + predicate.test(null));
  
          predicate = Predicate.isEqual(null);
          System.out.println("Java8, null : " + predicate.test("Java8"));
  
          predicate = Predicate.isEqual("Java8");
          System.out.println("Java8, Java8 : " + predicate.test("Java8"));
  
          predicate = Predicate.isEqual("Java8");
          System.out.println("Java7, Java8 : " + predicate.test("Java7"));
      }
  }
  ```

  **실행 결과**

  ```
  null, null : true
  null, Java8 : false
  Java8, null : false
  Java8, Java8 : true
  Java7, Java8 : false
  ```



### 14.5.8 minBy(), maxBy() 정적 메소드

: BinaryOperator\<T> 함수적 인터페이스는 minBy()와 maxBy() 정적 메소드를 제공한다. 

| 리턴 타입          | 정적 메소드                              |
| ------------------ | ---------------------------------------- |
| BinaryOperator\<T> | minBy(Comparator\<? super T> comparator) |
| BinaryOperator\<T> | maxBy(Comparator\<? super T> comparator) |

* **예제**

  ```java
  package minby_maxby_static_method;
  
  import java.util.function.BinaryOperator;
  
  public class OperatorMinByMaxByExample {
      public static void main(String[] args) {
          BinaryOperator<Fruit> binaryOperator;
          Fruit fruit;
  
          binaryOperator = BinaryOperator.minBy( (f1, f2) ->
                  Integer.compare(f1.price, f2.price) );
          fruit = binaryOperator.apply(new Fruit("딸기", 6000),
                  new Fruit("수박", 10000));
          System.out.println(fruit.name);
  
          binaryOperator = BinaryOperator.maxBy( (f1, f2) ->
                  Integer.compare(f1.price, f2.price) );
          fruit = binaryOperator.apply(new Fruit("딸기", 6000),
                  new Fruit("수박", 10000));
          System.out.println(fruit.name);
      }
  }
  ```

  **실행 결과**

  ```
  딸기
  수박
  ```



## 14.6 메소드 참조

: 메소드를 참조해서 매개 변수의 정보 및 리턴 타입을 알아내어, 람다식에서 불필요한 매개 변수를 제거하는 것이 목적이다. 메소드 참조는 정적 또는 인스턴스 메소드를 참조할 수 있고, 생성자 참조도 가능하다.

* **예시**

  ```java
  (left, right) -> Math.max(left, right);
  
  Math :: max;	// 메소드 참조
  
  IntBinaryOperator operator = Math :: max;
  ```



### 14.6.1 정적 메소드와 인스턴스 메소드 참조

* **정적(static) 메소드** : 클래스 이름 뒤에 :: 기호를 붙이고 정적 메소드 이름을 기술하면 된다.

  ```java
  클래스 :: 메소드
  ```

* **인스턴스 메소드** : 먼저 객체를 생성한 다음 참조 변수 뒤에 :: 기호를 붙이고 인스턴스 메소드 이름을 기술하면 된다.

  ```java
  참조변수 :: 메소드
  ```

* **예제**

  Calculator.java

  ```java
  package static_and_instance_method;
  
  public class Calculator {
      // 정적 메소드
      public static int staticMethod(int x, int y) {
          return x + y;
      }
  
      // 인스턴스 메소드
      public int instanceMethod(int x, int y) {
          return x + y;
      }
  }
  ```

  MethodReferencesExample.java

  ```java
  package static_and_instance_method;
  
  import java.util.function.IntBinaryOperator;
  
  public class MethodReferencesExample {
      public static void main(String[] args) {
          IntBinaryOperator operator;
  
          // 정적 메소드 참조
          operator = (x, y) -> Calculator.staticMethod(x, y);
          System.out.println("결과1: " + operator.applyAsInt(1, 2));
  
          operator = Calculator :: staticMethod;
          System.out.println("결과2: " + operator.applyAsInt(3, 4));
  
          // 인스턴스 메소드 참조
          Calculator obj = new Calculator();
          operator = (x, y) -> obj.instanceMethod(x, y);
          System.out.println("결과3: " + operator.applyAsInt(5, 6));
  
          operator = obj :: instanceMethod;
          System.out.println("결과4: " + operator.applyAsInt(7, 8));
      }
  }
  ```

  **실행 결과**

  ```
  결과1: 3
  결과2: 7
  결과3: 11
  결과4: 15
  ```



### 14.6.2 매개 변수의 메소드 참조

: 람다식에서 제공되는 매개 변수의 메소드를 호출해서 다른 매개 변수를 매개값으로 사용하는 경우도 있다.

```java
(a, b) -> { a.instanceMethod(b); }
```

* **예제**

  ```java
  package method_reference_of_parameter;
  
  import java.util.function.ToIntBiFunction;
  
  public class ArgumentMethodReferenceExample {
      public static void main(String[] args) {
          ToIntBiFunction<String, String> function;
  
          function = (a, b) -> a.compareToIgnoreCase(b);
          print(function.applyAsInt("Java8", "JAVA8"));
  
          function = String::compareToIgnoreCase;
          print(function.applyAsInt("Java8", "JAVA8"));
      }
  
      public static void print(int order) {
          if(order < 0) {
              System.out.println("사전순으로 먼저 옵니다.");
          } else if (order == 0) {
              System.out.println("동일한 문자열 입니다.");
          } else {
              System.out.println("사전순으로 나중에 옵니다.");
          }
      }
  }
  ```



### 14.6.3 생성자 참조

: 단순히 객체를 생성하고 리턴하도록 구성된 람다식은 생성자 참조로 대치할 수 있다.

```java
// 단순히 객체 생성 후 리턴만 하는 람다식
(a, b) -> { return new 클래스(a, b); }

// 생성자 참조 표현
클래스 :: new
```

* **예제**

  Member.java

  ```java
  package constructor_reference;
  
  public class Member {
      private String name;
      private String id;
  
      public Member() {
          System.out.println("Member() 실행");
      }
  
      public Member(String id) {
          System.out.println("Member(String id) 실행");
          this.id = id;
      }
  
      public Member(String name, String id) {
          System.out.println("Member(String name, String id)");
          this.name = name;
          this.id = id;
      }
  
      public String getId() { return id; }
  }
  ```

  ConstructorReferenceExample.java

  ```java
  package constructor_reference;
  
  import java.util.function.BiFunction;
  import java.util.function.Function;
  
  public class ConstructorReferencesExample {
      public static void main(String[] args) {
          // 생성자 참조
          Function<String, Member> function1 = Member :: new;
          Member member1 = function1.apply("angel");
  
          // 생성자 참조
          BiFunction<String, String, Member> function2 = Member::new;
          Member member2 = function2.apply("신천사", "angel");
      }
  }
  ```



# 확인문제

