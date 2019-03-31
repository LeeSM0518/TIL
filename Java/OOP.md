# Object Oriented Programming(OOP)

# 개요

* 저는 이자바 책을 한번 다 보고, "나는 이제 자바를 잘한다" 라는 자부심을 가지고 자바 프로젝트를 진행하게 되었습니다. 하지만 클래스 설계를 하는 도중에, 클래스는 무슨 기준으로 나누는 것이며 인터페이스는 어떻게 쓰는 것이고, 상속을 하면 어떤 장단점이 있는지 전혀 이해를 못하고 있는 저를 발견할 수 있었습니다. 그래서 저는 Object Oriented Programmsing, 즉 객체 지향 프로그래밍에 대해서 자유주제를 준비하게 되었습니다.



# 절차 지향과 객체 지향?

## 절차 지향

 프로그램을 구현한다는 것은 결국 최종적으로 프로그램을 구성하는 데이터와 데이터를 조작하는 코드를 작성하는 것이다. 데이터를 조작하는 코드를 별도로 분리해서 함수 같은 형태로 만들고, 각 함수나 프로시저(procedure) 들로 데이터를 조작하는 방식이 절차(procedure) 지향 프로그래밍이라고 한다. 

 절차 지향 프로그래밍이 순서에 따라 프로그래밍이라고 착각을 많이 한다. 하지만 함수와 비슷한 프로시저(procedure)를 우리말로 번역했을 때 순서, 절차이기 때문에 절차 지향 프로그래밍이 된 것이다.



* **단점**

  요구사항 추가 전

  ```java
  // 전원 꺼짐/켜짐 처리
  String isOn (Button button) {
    if (button == 1) return "On";
    else if (button == 0) return "Off";
  }
  
  void desktopPower() {
    ...
    power = isOn(button);
    
    if (power.equals("On")) {
      ...
    } else if (power.equals("Off")) {
      ...
    }
  }
  ```

  > 전원의 On/Off 상태에 대한 함수들

  요구사항(대기상태) 추가 후

  ```java
  // 전원 대기 추가
  String isOn (Button button) {
    if (button == 1) return "On";
    else if (button == 0) return "Off";
    else if (button == -1) return "Wait";
  }
  
  void desktopPower() {
    ...
    String power = isOn(button);
    
    if (power.equals("On")) {
      ...
    } else if (power.equals{"Off") {
      ...
    } else if (power.equals("Wait") {
      ...
    }
  }
               
  void noteBookPower() {
  	  ...
    String power = isOn(button);
    
    if (power.equals("On")) {
      ...
    } else if (power.equals{"Off") {
      ...
    } else if (power.equals("Wait") {
      ...
    }
  }
  
  ...
  ```

  >  전원에 대기상태를 추가 하게 되면 desktopPower 라는 함수를 바꿔줘야 한다. 이처럼 수정해야 되는 함수가 1개이면 상관이 없는데 프로그램 규모가 커서 수정해야 되는 함수가 몇 백개가 된다면 코드 수정이 매우 어려워진다. 이처럼 절차 지향은 함수끼리 의존이 심하기 때문에 결합도가 매우 높은 단점이 있다.



## 객체 지향

 데이터 및 데이터와 관련된 함수들을 객체라고 불리는 단위로 묶는다. 객체는 함수를 실행하는데 필요한 만큼의 데이터를 가지며, 이 객체들이 모여 프로그램을 구성한다.

 이처럼 각 객체는 자신만의 데이터와 함수를 갖으며 다른 객체에게 자신만의 기능을 제공한다. 객체가 기능을 제공할 때 함수를 실행하게 되는데, 이때 함수는 자신이 속한 객체의 데이터에만 접근할 수 있으며, 다른 객체에 속한 데이터에는 접근할 수 없다. 

 그래서 객체 지향은 절차 지향보다 설계하는데 더 많은 노력과 시간이 들지만, 요구 사항이 생기면 그 요구 사항에 대한 객체만 수정해주면 되기 때문에 코드 수정이 편리해지는 유연함을 가지고 있다.



# 객체란 무엇인가?

## 객체

1. 자신만의 데이터와 메소드로 구성되어 있으며 다른 것과 식별 가능한 것. 
2. 객체들은 각각 독립적으로 존재하고 다른 객체와 서로 상호 작용하면서 동작한다. 

- **상호작용 수단** : 메소드
- **메소드 호출** : 다른 객체의 기능을 이용하는 것
-  **객체의 핵심은 기능을 제공하는 것이다.**
  - 예를 들어,  소리 크기 제어 객체가 있으면, 이 객체의 기능은 소리 크기 증가, 소리 크기 감소, 음소거 일 것이다. 이 객체를 사용하는 사용자는 소리의 크기가 어떻게 증가하고 감소하는지에 대해서는 알 필요가 없고 소리를 증가, 감소, 음소거 시키는 세 개의 기능을 제공받는 것이 핵심인 것이다.



# 객체 지향 프로그래밍의 특징

## 캡슐화(부품화)

 부품이 제대로된 부품이라면 그것이 어떻게 만들어졌는지 모르는 사람도 그 부품을 사용하는 방법만 알면 쓸 수 있도록 해야한다. 예를 들어 컴퓨터와 모니터를 연결하는 방법만 알면 화면을 표시 할 수 있는 것과 같다. 즉 내부의 동작 방법을 단단한 케이스 안으로 숨기고 사용자에게는 그 부품의 사용 방법만을 노출시키는 것이다. 이러한 컨셉을 캡슐화라고 한다.

 프로그래밍 적으로 설명하자면, 캡슐화는 객체가 내부적으로 기능을 어떻게 구현하는지를 감추는 것이다. 이를 통해 내부의 기능 구현이 변경되더라도 그 기능을 사용하는 코드는 영향을 받지 않도록 만들어준다.



## 상속

 새로운(하위) 클래스가 기존의(상위) 클래스의 멤버를 이용할 수 있게 하는 것이다. 상속을 통해서 기존의 클래스를 상속받은 하위 클래스가 종속 관계를 형성하면서 객체를 조직화할 수 있다. 이로써 상위 객체를 수정하면 하위 객체가 수정이 되므로 유지보수가 쉽다.

* **예시**

  일반 자동차

  ```java
  public class Car {
  	private Tire frontLeftTire;
    private Tire frontRightTire;
    private Tire backLeftTire;
    private Tire backRightTire;
    
    Car() {
      // 타이어 초기화
    }
      
    void drive() {
      frontLeftTire.roll();
      frontRightTire.roll();
      backLeftTire.roll();
      backRightTire.roll();
    }
    
    void stop() {
      // 타이어가 멈춘다.
    }
  }
  ```

  자율 주행 자동차

  ```java
  public class AutonomousCar extends Car {
    boolean dangerDetection;
    
    void autoDrive() {
      if (dangerDetection == true) stop();
      else drive();
    }
  }
  ```

  



## 다형성

 다형성(Polymorphism)은 한 객체가 여러 가지(poly) 모습(morph) 을 갖는다는 것을 의미한다. 여기서 모습이란 타입을 뜻하는데, 즉, 다형성이란 한 객체가 여러 타입을 가질 수 있다는 것을 뜻한다.

* **예시**

  ```java
  class Car{
    
    private Tire frontLeftTire;
    private Tire frontRightTire;
    private Tire backLeftTire;
    private Tire backRightTire;
    
    Car() {
      // 타이어 초기화
    }
      
    void drive() {
      frontLeftTire.roll();
      frontRightTire.roll();
      backLeftTire.roll();
      backRightTire.roll();
    }
    
    ...
      
  }
  
  // Tire를 구현한 HankookTIre
  class HankookTire implements Tire { 
    
  	@Override
    public void roll() {
      // 한국 타이거가 굴러감.
    }
    
  }
  
  // Tire를 구현한 KumhoTire
  class kumhoTire implements Tire { 
    
    @Override
    public void roll() {
      // 금호 타이거가 굴러감.
    }
    
  }
  
  public static void main(String[] args) {
    Car myCar = new Car();
    myCar.setFrontLeftTire(new HankookTire());	// 왼쪽 앞 Tire에 한국 타이어 사용
    myCar.setFrontRightTire(new KumhoTire());		// 오른쪽 앞 Tire에 금호 타이어 사용
    myCar.drive();	// 앞 두개의 Tire가 다른 성능을 가진 메소드를 호출한다.
  }
  ```

  > 타이어 클래스를 한국 타이어와 금호 타이어 클래스가 구현한다. 타이어 타입에 한국 타이어 객체가 대입되기도 하고 금호 타이어 객체가 대입되기도 한다. 그리고 각 타이어 객체마다 오버라이딩된 메소드의 내용이 다르기 때문에 메소드의 실행 결과가 다양하게 나온다.



## 장점

* 소프트웨어의 질을 향상하기 위해 **강한 응집력** 과 **약한 결합력** 을 지향해야 하는데, OOP의 경우 클래스에 **하나의 문제 해결을 위한 데이터를 모아 놓은 데이터형을 사용함으로써 응집력을 강화** 하고, **클래스간에 독립적으로 디자인함으로써 결합력을 약하게** 할 수 있다.



# 객체 지향 프로그래밍 방법

## (1) 프로그래밍의 기능을 구성할 객체 먼저 파악해보자.

* **커피 전문점 주문**
  * 손님
  * 점원
  * 메뉴판
  * 커피 머신
  * 커피



## (2) 필요한 메세지 정리하기

 여기서 메세지란 객체간의 요청을 말한다. 각 객체는 커피 주문이라는 목표를 달성하기 위해 존재하고 협력한다. 이 객체들 끼리 어떤 협력이 필요한지 파악하는 것이 필요하다. 요청을 받은 객체는 자신의 방법에 맞게 요청을 처리하고 요청한 객체가 원하는 형태로 돌려준다. 즉, 이러한 객체들끼리의 협력을 위해서 필요한 요청을 정리한다.

* 커피 메뉴를 선택해줘
* 커피를 주문해줘
* 커피를 제조해줘



## (3) 메세지를 담당할 객체를 정하자

 메세지들이 정리되면 요청 메세지를 누가 보내고, 누가 처리를 할지 정한다.

- 커피 메뉴를 선택해줘 : 손님
- 커피를 주문해줘 : (송신) 손님, (수신) 점원
- 커피를 제조해줘 : (송신) 점원, (수신) 커피 머신



## (4) 객체의 타입화

객체가 협력 관점에서 어떤 역할인지 그리고 그 역할이 어떤 요청을 받을 수 있는지 목록화하는 것이 객체의 타입화이다.

```java
public interface Customer {
  
  public void order(Menu menu, Barista barista);
}
```

