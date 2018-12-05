# Chapter 06. 클래스

## 6.1.  객체 지향 프로그래밍

: 부품에 해당하는 객체들을 먼저 만들고, 이것들을 하나씩 조립해서 완성된 프로그램을 만드는 기법



### 6.1.1 )  객체란?

: 자신의 속성을 가지고 있고 다른 것과 식별 가능한 것. 속성과 동작으로 구성되어 있다.

![1543936968462](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1543936968462.png)

* **필드(field)** : 객체의 속성                 , **ex) ** 이름,  나이
* **메소드(method)** : 객체의 동작      , **ex) ** 먹다, 달린다
* **객체 모델링** : 필드와 메소드를 정의하는 과정



### 6.1.2 )  객체의 상호작용

: 객체들은 각각 독립적으로 존재하고, 다른 객체와 서로 상호작용 하면서 동작한다.

* **상호작용 수단** : 메소드

* **메소드 호출** : 객체가 다른 객체의 기능을 이용하는 것

  * **메소드 사용법**

    ```java
    리턴값 = 객체.메소드(매개값1, ...);
    ```

    > 객체에 도트( . ) 연산자를 붙인 뒤 메소드 이름을 기술



    **예시**
    
    ```java
    int result = Calcurator.add(10, 20);
    ```


### 6.1.3 )  객체 간의 관계

: 객체는 대부분 다른 객체와 관계를 맺고 있다.

**관계 종류** : 집합 관계, 사용 관계, 상속 관계



![1543936643817](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1543936643817.png)

* **집합 관계** : 부품에 해당
* **상속 관계** : 상위(부모) 객체를 기반으로 하위(자식) 객체를 생성하는 관계         **ex) ** 기계(상위), 자동차(하위)
* **사용 관계** : 객체 간의 상호작용         **ex)**  사람이 자동차릉 사용한다.





### 6.1.4 )  객체 지향 프로그래밍의 특징

:  캡슐화, 상속, 다형성



#### 캡슐화(Encapsulation)

: 객체의 필드, 메소드를 하나로 묶고 실제 구현 내용을 감추는 것.

![1543937048982](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1543937048982.png)

* **캡슐화하는 이유** : 필드와 메소드를 캡슐화하여 보호하기 위해( 외부의 잘못된 사용으로 객체가 손상되지 않도록 함 )

* **접근 제한자( Access Modifier )** : 객체의 필드와 메소드의 사용 범위를 제한함으로써 외부로 부터 보호.



#### 상속(Inheritance)

: 부모 역할의 상위 객체가 자식 역할의 하위 객체에게 필드와 메소드를 제공하는 것

![1543937659150](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1543937659150.png)

* **장점**
  * 상위 객체를 재사용해서 하위 객체를 쉽고 빨리 설계 가능
  * 상위 객체의 수정으로 모든 하위 객체들의 수정 효과로 유지 보수 시간 최소화



#### 다형성(Polymorphism)

: 같은 타입이지만 실행 결과가 다양한 객체를 이용할 수 있는 성질

* **자바의 다형성** 
  * 부모 클래스 또는 인터페이스의 타입 변환이 허용된다.
  * 하나의 타입에 여러 객체를 대입할 수 있다.

![1543938246862](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1543938246862.png)



## 6.2  객체와 클래스

* **클래스** : 객체를 생성하기 위한 필드와 메소드가 정의되어 있다.
  * **인스턴스** : 클래스로부터 만들어진 객체



## 6.3  클래스 선언

* **작성규칙**
  * 하나 이상의 문자로 이루어져야한다.
  * 첫 번째 글자는 숫자가 올 수 없다.
  * '$', '_' 외의 특수 문자는 사용할 수 없다.
  * 자바 키워드는 사용할 수 없다.
  * 관례적으로 클래스 이름이 단일 단어라면 첫 자를 대문자로 하고 나머지는 소문자로 작성.
    서로 다른 단어가 혼합된 이름을 사용한다면 각  단어의 첫 머리 글자는 대문자로 작성.



* **선언 방법**

  ```java
  public class 클래스이름{
      ...
  }
  ```

* **선언 예시**

  ```java
  public class Car{
      ...
  }
  ```

* **주의할 점**

  ```java
  public class Car{
      ...
  }
  class Tire{
      ...
  }
  ```

  > 파일 이름과 동일한 이름의 클래스 선언에만 **public 접근 제한자**를 붙일 수 있다.
  >
  > 그래서 파일 하나당 동일한 이름의 클래스 하나를 선언하는 것이 좋다.



## 6.4 객체 생성과 클래스 변수

* **선언 방법**

  ```java
  new 클래스();
  ```

* **new 연산자** : 클래스로부터 객체를 생성시키는 연산자

  * new 연산자 뒤에는 클래스(  ) 형태를 가지고 있는 생성자가 온다.
  * new 연산자는 힙 영역에 객체를 생성시킨 후, 객체의 주소를 리턴한다.

* **new 선언 예시**

  ```java
  클래스 변수;
  변수 = new 클래스();		// 객체의 주소를 변수에 저장
  
  클래스 변수 = new 클래스();	// 변수 선언과 객체 생성 동시에 가능
  ```

* **클래스 선언 예제**

  **Student.java**

  ```java
  public class Student {
  }
  ```

  **StudentExample.java**

  ```java
  public class Student {
      public static void main(String[] args) {
          Student s1 = new Student();
          System.out.println("s1 변수가 Student 객체를 참조한다.");
  
          Student s2 = new Student();
          System.out.println("s1 변수가 또 다른 Student 객체를 참조합니다.");
      }
  }
  ```

  **실행 결과**

  ```
  s1 변수가 Student 객체를 참조한다.
  s1 변수가 또 다른 Student 객체를 참조합니다.
  ```

  > * Student 클래스는 하나지만 new 연산자를 사용한 만큼 객체가 메모리에 생성된다.
  >    이러한 객체들은 Student 클래스의 **인스턴스**들이다.
  >
  > * s1 과 s2가 참조하는 Student 객체는 완전히 독립된 서로 다른 객체이다.
  > * **Student 클래스** : 라이브러리용 클래스
  > * **StudentExample 클래스** : 실행 클래스



## 6.5  클래스의 구성 멤버

**구성 멤버** : 필드(Field), 생성자(Constructor), 메소드(Method)

* **클래스**

  ```java
  public class ClassName{
      
      // 필드
      int fieldName;
      
      // 생성자
      ClassName(){
          ...
      }
      
      // 메소드
      void methodName(){
          ...
      }
  }
  ```




### 6.5.1 )  필드

: 객체의 고유 데이터, 상태 정보를 저장하는 곳. 변수(variable)와 비슷하지만 개념이 다르다.

* **변수** : 생성자와 메소드 내에서만 사용되고 생성자와 메소드가 실행 종료되면 자동 소멸
* **필드** : 생성자와 메소드 전체에서 사용되며 객체가 소멸되지 않는 한 객체와 함께 존재



### 6.5.2 )  생성자

: new 연산자로 호출된다. 생성자의 역할은 객체 생성 시 초기화를 담당. 생성자는 메소드와 비슷하게 생겼지만, 클래스 이름으로 되어 있고 리턴 타입이 없다.



### 6.5.3 )  메소드

: 객체의 동작. 객체 간의 데이터 전달의 수단.



## 6.6  필드

: 객체의 고유 데이터, 상태 데이터.



### 6.6.1 )  필드 선언

: 클래스 블록 어디서든 존재할 수 있다. 하지만 생성자와 메소드 중괄호 블록 내부에는 선언될 수 없다. 생성자와 메소드 중괄호 블록 내부에 선언된 것은 모두 로컬 변수가 된다.

* **선언 방법**

  ```java
  타입 빌드 = 초기값;
  ```

* **선언 예시**

  ```java
  String company = "현대자동차";
  String model = "그랜저";
  int maxSpeed = 300;
  int productionYear;
  int currentSpeed;
  boolean engineStart;
  ```

* **기본 초기값**

  * **정수 타입, 실수 타입, 논리 타입** : 대부분이 0
  * **참조 타입 ** : 모두 null



### 6.6.2 )  필드 사용

: 필드값을 읽고, 변경하는 작업

* **사용** : 클래스 내부에서 사용할 경우에는 단순히 필드 이름으로 읽고 변경.  클래스 외부에서 사용할 경우 우선적으로 클래스로부터 객체를 생성한 뒤 필드를 사용해야 한다.



* **필드 사용 예제**

  **Car.java**

  ```java
  public class Car {
      // 필드
      String company = "현대자동차";
      String mode1 = "그랜져";
      String color = "검정";
      int maxSpeed = 350;
      int speed;
  }
  ```

  **CarExample.java**

  ```java
  public class CarExample {
      public static void main(String[] args) {
          // 객체 생성
          Car myCar = new Car();
  
          // 필드값 읽기
          System.out.println("제작회사 : " + myCar.company);
          System.out.println("모델명 : " + myCar.mode1);
          System.out.println("색깔 : " + myCar.company);
          System.out.println("최고속도 : " + myCar.mode1);
          System.out.println("현재속도 : " + myCar.speed);
  
          // 필드값 변경
          myCar.speed = 60;
          System.out.println("수정된 속도 : " + myCar.speed);
      }
  }
  ```

  **실행 결과**

  ```
  제작회사 : 현대자동차
  모델명 : 그랜져
  색깔 : 현대자동차
  최고속도 : 그랜져
  현재속도 : 0
  수정된 속도 : 60
  ```



* **필드 자동 초기화 예제**

  **FieldInitValue.java**

  ```java
  public class FieldInitValue {
      // 필드
      byte byteField;
      short shortField;
      int intField;
      long longField;
  
      boolean booleanField;
      char charField;
  
      float floatField;
      double doubleField;
  
      int[] arrField;
      String referenceField;
  }
  ```



  **FieldInitValueExample.java**

  ```java
  public class FieldInitValueExample {
      public static void main(String[] args) {
          FieldInitValue fiv = new FieldInitValue();
  
          System.out.println("byteField: " + fiv.byteField);
          System.out.println("shortField: " + fiv.shortField);
          System.out.println("intField: " + fiv.intField);
          System.out.println("longField: " + fiv.longField);
          System.out.println("booleanField: " + fiv.booleanField);
          System.out.println("charField: " + fiv.charField);
          System.out.println("floatField: " + fiv .floatField);
          System.out.println("doubleField: " + fiv.doubleField);
          System.out.println("arrField: " + fiv.arrField);
          System.out.println("referenceField: " + fiv.referenceField);
      }
  }
  ```

  **실행 결과**

  ```
  byteField: 0
  shortField: 0
  intField: 0
  longField: 0
  booleanField: false
  charField:  			// 빈 공백
  floatField: 0.0
  doubleField: 0.0
  arrField: null
  referenceField: null
  ```



## 6.7  생성자

: new 연산자와 같이 사용되어 클래스로부터 객체를 생성할 때 호출되어 객체의 초기화를 담당한다.



### 6.7.1 )  기본 생성자

: 모든 클래스는 생성자가 반드시 존재한다.

* **생성자 선언**

  ```java
  [public] 클래스() { }
  ```

  > **public class**로 선언되면 기본 생성자에서도 public이 붙지만, 클래스가 public 없이 **class로만** 선언되면 기본 생성자에도 public이 붙지 않는다.



### 6.7.2 생성자 선언

* **명시적으로 생성자 선언**

  ```java
  클래스( 매개변수선언, ... ){		// 생성자 블록
      // 객체의 초기화 코드
  }
  ```

  > 리턴 타입이 없고 클래스 이름과 동일하다.
  >
  > **객체 초기화 코드** : 필드에 초기값을 저장하거나 메소드를 호출하여 객체 사용 전에 필요한 준비를 한다.
  >
  > **매개 변수 생성자** : new 연산자로 생성자를 호출할 때 외부의 값을 생성자 블록 내부로 전달하는 역할

* **매개 변수 생성자 예시**

  ```java
  public class Car{
      // 생성자
      Car(String model1, String color, int maxSpeed){
          ...
      }
  }
  ```



* **생성자 선언 예제**

  **Car.java**

  ```java
  public class Car2 {
      String Color;
      int CC;
      // 생성자
      Car2(String color, int cc){
          Color = color;
          CC = cc;
      }
  }
  ```

  **CarExample.java**

  ```java
  public class Car2Example {
      public static void main(String[] args) {
          Car2 myCar = new Car2("검점", 3000);
          // Car myCar = new Car(); ( X , 기본 생성자 호출 불가 )
  
          System.out.println(myCar.Color);
          System.out.println(myCar.CC);
      }
  }
  ```

  **실행 결과**

  ```
  검정
  3000
  ```


### 6.7.3 )  필드 초기화

* **다른 값으로 객체 초기화하는 방법**
  * 필드를 선언할 때 초기값을 주는 방법
  * 생성자에게 초기값을 주는 방법



* **생성자에서 필드 초기화 예제**

  **Korean.java**

  ```java
  public class Korean {
      // 필드
      String nation = "대한민국";
      String name;
      String ssn;
  
      // 생성자
      public Korean(String n, String s){
          name = n;
          ssn = s;
      }
  }
  ```

  **KoreanExample.java**

  ```java
  public class KoreanExample {
      public static void main(String[] args) {
          Korean k1 = new Korean("박자바", "111111-1111111");
          System.out.println("k1.name: " + k1.name);
          System.out.println("k1.ssn: " + k1.ssn);
          System.out.println("k1.nation: " + k1.nation);
  
          Korean k2 = new Korean("김자바", "222222-2222222");
          System.out.println("k2.name : " + k2.name);
          System.out.println("k2.ssn: " + k2.ssn);
          System.out.println("k2.nation: " + k2.nation);
      }
  }
  ```

  **실행 결과**

  ```
  k1.name: 박자바
  k1.ssn: 111111-1111111
  k1.nation: 대한민국
  k2.name : 김자바
  k2.ssn: 222222-2222222
  k2.nation: 대한민국
  ```

