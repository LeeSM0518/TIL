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

  * **예시**

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

  > 매개 변수의 이름이 너무 짧으면 가독성이 좋지 않기 때문에 가능하면 초기화시킬 필드 이름과 비슷하게 사용할 것

* **this** : 객체 자신

  ```java
  public Korean(String name, String ssn){
      this.name =  name;		// this.필드 = 매개변수
      this.ssn = ssn;
  }
  ```




### 6.7.4 )  생성자 오버로딩( Overloading )

: 매개 변수를 달리하는 생성자를 여러 개를 선언하는 것을 말한다.

* **선언 예시**

  ```java
  public class Car{
      Car(){ ... }
      Car(String model){ ... }
      Car(String model, String color){ ... }
      Car(String model, String color, int maxSpeed){ ... }
  }
  ```

  > **주의할 점** : 선언된 순서가 같을 경우 매개 변수 이름만 바꾸는 것은 생성자 오버로딩이 아니다.
  >
  > **ex)**
  >
  > ```java
  > Car(String model, String color){ ... }
  > Car(String color, String model){ ... } 	// 오버로딩 X
  > ```

* **생성자의 오버로딩 예제**

  **Car.java**

  ```java
  public class Car3 {
      // 필드
      String company = "현대자동차";
      String model;
      String color;
      int maxSpeed;
  
      Car3(){
  
      }
  
      Car3(String model){
          this.model = model;
      }
      Car3(String model, String color){
          this.model = model;
          this.color = color;
      }
      Car3(String model, String color, int maxSpeed){
          this.model = model;
          this.color = color;
          this.maxSpeed = maxSpeed;
      }
  }
  
  ```

  **CarExample.java**

  ```java
  public class Car3Example {
      public static void main(String[] args) {
          Car3 car1 = new Car3();
          System.out.println("car1.company: " + car1.company);
          System.out.println();
  
          Car3 car2 = new Car3("자가용");
          System.out.println("car2.company: " + car2.company);
          System.out.println("car2.model: " + car2.model);
          System.out.println();
  
          Car3 car3 = new Car3("자가용", "빨강");
          System.out.println("car3.company: " + car3.company);
          System.out.println("car3.model: " + car3.model);
          System.out.println("car3.color: " + car3.color);
          System.out.println();
  
          Car3 car4 = new Car3("택시", "검정", 200);
          System.out.println("car4.company: " + car4.company);
          System.out.println("car4.model: " + car4.model);
          System.out.println("car4.color: " + car4.color);
          System.out.println("car4.maxSpeed: " + car4.maxSpeed);
          System.out.println();
      }
  }
  ```

  **실행결과**

  ```
  car1.company: 현대자동차
  
  car2.company: 현대자동차
  car2.model: 자가용
  
  car3.company: 현대자동차
  car3.model: 자가용
  car3.color: 빨강
  
  car4.company: 현대자동차
  car4.model: 택시
  car4.color: 검정
  car4.maxSpeed: 200
  ```


### 6.7.5 )  다른 생성자 호출(this())

: 생성자에서 다른 생성자를 호출할 때에 사용

```java
클래스( [매개 변수 선언, ... ] ){
    this( 매개변수, ... , 값 , ...);	// 클래스의 다른 생성자 호출
    실행문;
}
```



* **다른 생성자를 호출해서 중복 코드 줄이기 예제**

  **Car.java**

  ```java
  public class Car4 {
      // 필드
      String company = "현대자동차";
      String model;
      String color;
      int maxSpeed;
  
      // 생성자
      Car4() {
  
      }
      Car4(String model){
          this(model,"은색", 250);
      }
      Car4(String model, String color){
          this(model, color, 250);
      }
      Car4(String model, String color, int maxSpeed) {
          this.model = model;
          this.color = color;
          this.maxSpeed = maxSpeed;
      }
  
  }
  ```

  **CarExample.java**

  ```java
  public class Car4Example {
      public static void main(String[] args) {
          Car4 car1 = new Car4();
          System.out.println("car1.company: " + car1.company);
          System.out.println();
  
          Car4 car2 = new Car4("자가용");
          System.out.println("car2.company: " + car2.company);
          System.out.println("car2.model: " + car2.model);
          System.out.println();
  
          Car4 car3 = new Car4("자가용", "빨강");
          System.out.println("car3.company: " + car3.company);
          System.out.println("car3.model: " + car3.model);
          System.out.println("car3.color: " + car3.color);
          System.out.println();
  
          Car4 car4 = new Car4("택시", "검정", 200);
          System.out.println("car4.company: " + car4.company);
          System.out.println("car4.model: " + car4.model);
          System.out.println("car4.color: " + car4.color);
          System.out.println("car4.maxSpeed: " + car4.maxSpeed);
          System.out.println();
      }
  }
  ```

  **실행결과**

  ```
  car1.company: 현대자동차
  
  car2.company: 현대자동차
  car2.model: 자가용
  
  car3.company: 현대자동차
  car3.model: 자가용
  car3.color: 빨강
  
  car4.company: 현대자동차
  car4.model: 택시
  car4.color: 검정
  car4.maxSpeed: 200
  ```



## 6.8  메소드

: 객체의 동작에 해당한다. 객체 간의 데이터 전달의 수단. 외부로부터 매개값을 받을 수도 있고, 실행 후 어떤 값을 리턴 할 수도 있다.



### 6.8.1 )  메소드 선언

: 선언부( **메소드 시그니처(signature) ** : 리턴타입, 메소드이름, 매개변수선언 ) 와 실행 블록으로 구성



#### 리턴 타입

: 메소드가 실행 후 리턴하는 값의 타입

* **리턴 타입 예시**

  ```java
  void powerOn(){ ... }					// 리턴 타입 없음
  double divide(int x, int y){ ... }		// 리턴 타입 있음
  
  powerOn();							// 리턴 타입이 없으므로 변수에 저장할 내용 X
  double result = divide(10, 20);		// 리턴 타입이 없으므로 변수에 저장할 내용 O
  int result = divide(10, 20);		// 컴파일 에러 O
  divide(10, 20);						// 컴파일 에러 X
  ```



#### 메소드 이름

* **메소드 이름 규칙**
  * 숫자로 시작하면 안 되고, $와 _를 제외한 특수 문자 사용 X
  * 관례적으로 메소드명은 소문자로 작성
  * 다른 단어가 혼합된 이름이라면 뒤이어 오는 단어의 첫머리 글자는 대문자로 작성



* **메소드 선언 예시**

  ```java
  void run(){ ... }
  void startEngine(){ ... }
  String getName(){ ... }
  int[] getScores(){ ... }
  ```



* **매개 변수 선언**

  : 필요한 데이터를 외부로부터 받기 위함


* **메소드 선언 예제**

  **Calculator.java**

  ```java
  public class Calculator {
  
      // 메소드
      void powerOn(){
          System.out.println("전원을 킵니다");
      }
  
      int plus(int x, int y){
          int result = x + y;
          return result;
      }
      double divide(int x, int y){
          double result = (double)x / (double)y;
          return result;
      }
      void powerOff(){
          System.out.println("전원을 끕니다.");
      }
  }
  ```

  **CarculatorExample.java**

  ```java
  public class CarculatorExample {
      public static void main(String[] args) {
          Calculator myCalc = new Calculator();
          myCalc.powerOn();
  
          int result1 = myCalc.plus(5, 6);
          System.out.println("result1 : " + result1);
  
          byte x = 10;
          byte y = 4;
  
          double result2 = myCalc.divide(x, y);
          System.out.println("result2 : " + result2);
  
          myCalc.powerOff();
      }
  }
  ```

  **실행결과**

  ```
  전원을 킵니다
  result1 : 11
  result2 : 2.5
  전원을 끕니다.
  ```




#### 매개 변수의 수를 모를 경우

: 몇 개의 매개 변수가 입력될지 알 수 없기 때문에 매개 변수의 개수를 결정할 수 없을 것이다. 그러므로 매개 변수를 배열 타입을 선언하여 문제를 해결한다.

* **예시**

  ```java
  int sum1(int[] values){ }
  ```

  > sum1( ) 메소드를 호출할 때 배열을 넘겨줌, 배열의 항목 수는 호출할 때 결정.

  ```java
  int[] values = { 1, 2, 3 };
  int result = sum1(values);
  int result = sum1(new int[] { 1, 2, 3, 4, 5 });
  ```

* **"..." 매개변수**

  : 메소드 호출 시 넘겨준 값의 수에 따라 자동으로 배열이 생성되고 매개값으로 사용

  **예시**

  ```java
  int sum2(int ... values){ }
  ```

  ```java
  int result = sum2(1, 2, 3);
  int result = sum2(1, 2, 3, 4, 5);
  ```

* **매개 변수의 수를 모를 경우 예제**

  ```java
  public class ComputerExample {
      public static void main(String[] args) {
          Computer myCom = new Computer();
  
          // 배열을 매개변수로 전달
          int[] values1 = {1, 2, 3};
          int result1 = myCom.sum1(values1);
          System.out.println("result1: " + result1);
  
          // 배열 5개를 생성하며 매개변수로 전달
          int result2 = myCom.sum1(new int[]{1, 2, 3, 4, 5});
          System.out.println("result2: " + result2);
  
          // ... 매개변수가 1, 2, 3 을 배열로 만든다.
          int result3 = myCom.sum2(1, 2, 3);
          System.out.println("result3: " + result3);
  
          // ... 매개변수가 1, 2, 3, 4, 5 을 배열로 만든다.
          int result4 = myCom.sum2(1, 2, 3, 4, 5);
          System.out.println("result4: " +result4);
      }
  }
  ```

  **실행결과**

  ```
  result1: 6
  result2: 15
  result3: 6
  result4: 15
  ```




### 6.8.2 ) 리턴(return)문



#### 리턴값이 있는 메소드

: 메소드 선언에 리턴 타입이 있는 메소드는 반드시 리턴문을 사용해서 리턴값을 지정해야 한다.

* **자동 타입 변환 리턴 예시**

  ```java
  int plus(int x, int y){
      int result = x + y;
      return result;
  }
  
  int plus(int x, int y){
      byte result = (byte)(x + y);
      return result;	// 리턴 타입이 int 타입이기 때문에 byte 타입에서 int 타입으로 자동 타입 변환 발생
  }
  ```

* **주의할 점**

  : return문 이후에 실행문은 결코 실행되지 않는 점.

  **잘못된 코딩 예시**

  ```java
  int plus(int x, int y){
      int result = x + y;
      return result;
     	System.out.println(result);		// Unreachable code 컴파일 오류 발생
  }
  ```



  **예외의 경우**

  ```java
  boolean isLeftGas(){
      if(gas==0){
          System.out.println("gas가 없습니다.");
          return false;
      }
      System.out.println("gas가 있습니다.");
      return true;
  }
  ```

  > return false; 는 if의 조건문이 성립해야 실행되기 때문에 return문 이후에 실행문이 있어도 에러를 발생시키지 않는다.



#### 리턴값이 없는 메소드(void)

: void 메소드에서 return문을 사용하면 메소드 실행을 강제 종료시킬 수 있다.

* **예시**

  ```java
  void run(){
      while(true){
          if(gas > 0){
              System.out.println("달립니다.(gas잔량: " + gas + ")");
              gas -= 1;
          }else{
              System.out.println("멈춥니다.(gas잔량: " + gas + ")");
              return;		// run() 메소드 실행 종료
          }
      }
  }
  ```



* **return 문 예제**

  **Car.java**

  ```java
  public class Car5 {
      // 필드
      int gas;
  
      // 생성자
  
      // 메소드
      void setGas(int gas){
          this.gas = gas;
      }
  
      boolean isLeftGas(){
          if(gas==0){
              System.out.println("gas가 없습니다.");
              return false;
          }
          System.out.println("gas가 있습니다.");
          return true;
      }
  
      void run(){
          while(true){
              if(gas > 0){
                  System.out.println("달립니다.(gas잔량: " +
                          gas + ")");
                  gas -= 1;
              }else{
                  System.out.println("멈춥니다.(gas잔량: "+
                          gas + ")");
                  return; // 메소드 실행 종료
              }
          }
      }
  }
  ```



  **CarExample.java**

  ```java
  public class Car5Example {
      public static void main(String[] args) {
          Car5 myCar = new Car5();
  
          myCar.setGas(5);
  
          boolean gasState = myCar.isLeftGas();
          if(gasState){
              System.out.println("출발합니다");
              myCar.run();
          }
  
          if(myCar.isLeftGas()){
              System.out.println("gas를 주입할 필요가 없습니다.");
          } else {
              System.out.println("gas를 주입하세요.");
          }
      }
  }
  ```

  **실행결과**

  ```
  gas가 있습니다.
  출발합니다
  달립니다.(gas잔량: 5)
  달립니다.(gas잔량: 4)
  달립니다.(gas잔량: 3)
  달립니다.(gas잔량: 2)
  달립니다.(gas잔량: 1)
  멈춥니다.(gas잔량: 0)
  gas가 없습니다.
  gas를 주입하세요.
  ```


### 6.8.3 )  메소드 호출

: 메소드는 클래스 내, 외부의 호출에 의해 실행된다. 클래스 외부에서 호출할 경우에는 우선 클래스로부터 객체를 생성한 뒤, 참조 변수를 이용해서 메소드를 호출해야 한다.



#### 객체 내부에서 호출

```java
메소드( 매개값, ... );	// 매개 변수의 타입과 수에 맞게 매개값 제공
```

* **예시**

  ```java
  public class ClassName{
      void method1( String p1, int p2 ){
          
      }
      void method2(){
          method1("홍길동", 100);	// method1 을 호출
      }
  }
  ```

* **메소드를 호출하고 리턴값을 받고 싶을 경우**

  ```java
  타입 변수 = 메소드(매개값, ...);
  ```

* **주의할 점**

  ```java
  public class ClassName{
      int method1(int x, int y){
          int result = x + y;
          return result;
      }
      void method2(){
          int result1 = method(10, 20);			// result1 = 30
          double result2 = method1(10, 20);		// result2 = 30.0
      }
  }
  ```

  > 변수 타입이 메소드 리턴 타입과 동일하거나, 타입 변환이 될 수 있어야 한다.



* **클래스 내부에서 메소드 호출 예제**

  **Calculator.java**

  ```java
  public class Calculator2 {
      int plus(int x, int y){
          int result = x + y;
          return result;
      }
  
      double avg(int x, int y){
          double sum = plus(x, y);	// plus 호출 후 double 타입 변환
          double result = sum / 2;
          return result;
      }
  
      void execute(){
          double result = avg(7, 10);		// avg 호출
          println("실행결과: " + result);	 // println 호출
      }
  
      void println(String message){
          System.out.println(message);
      }
  }
  ```

  **CalculatorExample.java**

  ```java
  public class Calculator2Example {
      public static void main(String[] args) {
          Calculator2 myCalc = new Calculator2();
          myCalc.execute();
      }
  }
  ```

  **실행결과**

  ```
  실행결과: 8.5
  ```



#### 객체 외부에서의 호출

: 메소드는 객체에 소속된 멤버이므로 객체가 존재하지 않으면 메소드도 존재하지 않기 때문에 우선 클래스로부터 객체를 생성해야 한다.

```java
클래스 참조변수 = new 클래스(매개값, ...);
```



* **클래스 외부에서 메소드 호출 예제**

  **Car.java**

  ```java
  public class Car6 {
      // 필드
      int speed;
  
      // 생성자
  
      // 메소드
      int getSpeed(){
          return speed;
      }
  
      void keyTurnOn(){
          System.out.println("키를 돌립니다.");
      }
  
      void run(){
          for(int i=10; i<=50; i+= 10){
              speed = i;
              System.out.println("달립니다.(시족:"+
                      speed + "km/h");
          }
      }
  }
  ```

  **CarExample.java**

  ```java
  public class Car6Example {
      public static void main(String[] args) {
          Car6 myCar = new Car6();	// 객체 생성
          myCar.keyTurnOn();			// 객체의 메소드 호출
          myCar.run();				// 객체의 메소드 호출
          int speed = myCar.getSpeed();	// 리턴 값이 있는 메소드 호출	
          System.out.println("현재 속도: " + speed + "km/h");
      }
  }
  ```

  **실행결과**

  ```
  키를 돌립니다.
  달립니다.(시족:10km/h
  달립니다.(시족:20km/h
  달립니다.(시족:30km/h
  달립니다.(시족:40km/h
  달립니다.(시족:50km/h
  현재 속도: 50km/h
  ```



### 6.8.4 ) 메소드 오버로딩

:  클래스 내에 같은 이름의 메소드를 여러 개 선언하는 것. 메소드 오버로딩의 조건은 매개 변수의 타입, 개수, 순서 중 하나가 달라야 한다.

* **메소드 오버로딩 예제**

  **Calculator.java**

  ```java
  public class Calculator3 {
      // 정사각형 넓이
      double areaRectangle(double width){
          return width * width;
      }
  
      // 직사각형 넓이 : 매개 변수의 갯수를 다르게 하여 메소드를 오버로딩 하였다.
      double areaRectangle(double width, double height){
          return width * height;
      }
  }
  ```

  **CalculatorExample.java**

  ```java
  public class Calculator3Example {
      public static void main(String[] args) {
          Calculator3 myCalcu = new Calculator3();
  
          // 정사각형의 넓이 구하기
          double result1 = myCalcu.areaRectangle(10);
  
          // 직사각형의 넓이 구하기
          double result2 = myCalcu.areaRectangle(10, 20);
  
          // 결과 출력
          System.out.println("정사각형의 넓이 = " + result1);
          System.out.println("직사각형의 넓이 = " + result2);
      }
  }
  ```



## 6.9  인스턴스 멤버와 this

* **인스턴스(instance)멤버** :  객체(인스턴스)를 생성한 후 사용할 수 있는 필드와 메소드이다. 인스턴스 필드와 메소드는 객체에 소속된 멤버이기 때문에 객체없이는 사용할 수 없다.



* **인스턴스 멤버와 this 예제 코드**

  **Car.java**

  ```java
  public class Car7 {
      // 필드
      String model;
      int speed;
  
      // 생성자
      Car7(String model){
          this.model = model;
      }
  
      // 메소드
      void setSpeed(int speed){
          this.speed = speed;
      }
  
      void run(){
          for(int i=10; i<=50; i+=10){
              this.setSpeed(i);
              System.out.println(this.model  + " 가 달립니다.(시속: "+
                      this.speed + "km/h");
          }
      }
  }
  ```

  **CarExample.java**

  ```java
  public class Car7Example {
      public static void main(String[] args) {
          Car7 myCar = new Car7("포르쉐");
          Car7 yourCar = new Car7("벤츠");
  
          myCar.run();
          yourCar.run();
      }
  }
  ```

  **실행결과**

  ```
  포르쉐 가 달립니다.(시속: 10km/h
  포르쉐 가 달립니다.(시속: 20km/h
  포르쉐 가 달립니다.(시속: 30km/h
  포르쉐 가 달립니다.(시속: 40km/h
  포르쉐 가 달립니다.(시속: 50km/h
  벤츠 가 달립니다.(시속: 10km/h
  벤츠 가 달립니다.(시속: 20km/h
  벤츠 가 달립니다.(시속: 30km/h
  벤츠 가 달립니다.(시속: 40km/h
  벤츠 가 달립니다.(시속: 50km/h
  ```
