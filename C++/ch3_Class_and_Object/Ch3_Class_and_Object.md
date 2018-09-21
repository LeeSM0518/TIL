 Chapter 03 Class and Object (C++)
===

<br/>

# 3.1 객체에 대한이해

## 개념정리


 **클래스** : *객체(Object)* 를 정의하는 틀 혹은 설계도 이다. 클래스에 멤버 변수와 멤버 함수를 선언한다. C++ 객체는 프로그램이 실행되는 동안 실존한느 실체 혹은 *인스턴스(Instance)* 이다.

---

<br/>
<br/>

# 3.2 C++ 클래스 만들기

## 클래스 만들기

- ex) 원을 추상화한 Circle 클래스

    ```c++
    class Circle{   // class [클래스이름]
        public: // 멤버에 대한 접근 지정자
        int radius;     // 멤버 변수
        double getArea();    // 멤버 함수
    };

    double Circle::getArea(){
    return 3.14 * radisu * radius;
    }
    ```

<br/>
<br/>

  ## 클래스 선언부
 -  **클래스**는 class 키워드와 클래스 이름으로 선언한다.

    ```c++
    class Circle {  // Circle 이름의 클래스 선언
        ...
    };  //  반드시 세미콜론(;)으로 종료
    ```

<br/>
<br/>

 ## 클래스 멤버
 
 - **클래스 멤버** : 클래스의 멤버는 변수와 함수로 구성된다. 멤버 변수는 클래스 선언부에서 초기화 될수 없다. 다음의 경우 컴파일 오류가 발생한다.

    ```c++
    class Circle{
    public:
        int radius = 5; // 컴파일 오류. 멤버 변수는 클래스 선언부에서 초기화될 수 없다.
       ...
    };
    ```

    <br/>

    - `멤버 함수` : 원형 형태로 선언되며, 리턴 타입, 매개 변수 리스트 등이 모두 선언되어야 한다.
    ```C++
    double getArea();
    ```

<br/>
<br/>

## 접근 지정자, public
- **public 접근 지정자** : 클래스의 일부 멤버들을 다른 클래스와의 통실을 위해 외부에 공개하기도 한다. *멤버를 외부에 공개* 하는 역할.
    ```C++
    class Circle{
    public:     // 이하의 모든 멤버는 다른 접근 지정자가 선언될 때까지 public 접근 지정
    ...
    };
    ```
    <br/>

    `접근지정자`가 선언되면, 다른 접근 지정자로 선언되 때까지 모든 멤버에 대해 적용된다. `public` 외의 `private, protected` 등 총 3가지 종류가 있다.<br/>       `private` : 외부에서 접근 불가<br/>
    `protected` : 접근 지정은 상속 관계에서 적용


<br/>
<br/>

## 클래스 구현부
- 클래스 선언부에 선언된 멤버 함수의 코드를 구현한다.
```C++
//[함수의 리턴 타입] [클래스 이름] [범위 지정 연산자] [멤버 함수명과 배개변수]
double Circle :: getArea(){
    return 3.14 * radius * radius
}
```
<br/>
---

<br/>
<br/>

# 3.3 객체 생성과 객체 활용

<br/>

## 객체 생성과 활용 예제
- **ex 3-1) Circle 클래스의 객체 생성 및 활용**
    ```c++
    #include <iostream>
    using namespace std;

    class Circle{
    public:
        int radius;
        double getArea;
    };  // Circle 선언부

    double Circle::getAreaa(){
        return 3.14 * radius * radius;
    }   // Circle 구현부

    int main(){
        Circle dount;
        donut.radius = 1;   // donut 객체의 반지름을 1로 설정
        double area = donut.getArea();  // donut 객체의 면적 알아내기
        cout << "donut 면적은 " << area << endl;

        Circle pizza;
        pizza.radius = 30;  // pizza 객체의 반지름을 30으로 설정
        area = pizza.getArea(); // pizza 객체의 면적 알아내기
        cout << "pizza 면적은 " << area << endl;
    }

    ```
    - 실행결과
    ```
    donut 면적은 3.14
    pizza 면적은 2826
    ```

    <br/>
    <br/>

## 객체 생성과 이름
```c++
    int money;  // int 타입의 변수 money 생성

    Circle donut;   // Circle 클래스의 객체 생성. 객체 이름은 donut
    Circle pizza;   // Circle 클래스의 객체 생성, 객체 이름은 pizza
```

<br/>

## 객체의 멤버 접근
```c
객체이름.멤버   // 객체 이름 뒤에 .(점)을 찍고 그 뒤에 멤버를 쓴다.
```
<br/>

- ex)

   ```C++
    donut.radius = 1 ; // donut 객체의 radius 멤버에 1 기록
    double area = donut.getArea();  // donut 객체의 면적 알아내기
    ```
<br/>

  - **ex 3-2 Rectangle 클래스 만들기)**
    ```C++
    #include <iostream>
    using namespace std;

    class Rectangle{    // Rectangle 클래스 선언
    public:
        int width;
        int height;
        int getArea();  // 면적을 계산하여 리턴하는 함수
    };

    int Rectangle::getArea(){   // Rectangle 클래스 구현
        return width * height;
    }

    int main(){
        Rectangle rect;
        rect.widh = 3;
        rect.height = 5;
        cout << "사각형의 면적은 " << rect.getArea() << endl;
    }
    ```
    - 실행결과
    ```
    사각형의 면적은 15
    ```
    <br/>
---
<br/>
<br/>

# 3.4 생성자

## 생성자란?
- 클래스는 객체가 생성될 때 자동으로 생성되는 **생성자(constructor)** 라는 특별한 멤버 함수를 통해 **객체를 초기화** 한다.

<br/>
<br/>

## 생성자의 특징
- **목적은 객체가 생성될 때 필요한 초기 작업을 위함이다.**
- **함수는 오직 한번만 실행된다.**
- **함수의 이름은 클래스 이름과 돌일하게 작성되어야 한다.**
- **함수의 원혀에 리턴 타입을 선언하지 않는다.**
    - 생성자를 잘못 선언하여 컴파일 오류 발생한 사례 
      ```c++
      class Circle{
          ...
          Circle();     // 정상적인 생성자 선언. 리턴 타입 선언하지 않음
          void Circle(int r);   // 컴파일 오류. 생성자는 리턴 타입 없음
          int Circle(double r);     // 컴파일 오류. 생성자는 리턴 타입 없음
      };
      ```
      
      <br/>

    - 생성자 함수는 함수 실행을 종료하기 위해 **return 문**을 사용할 수 있다. 그러나 어떤 값도 리턴하면 안된다.
        ```c++
        Circle::Circle(){
            ...
            return;     // 생성자 함수를 종료하는 정상적인 리턴문
        }

        Circle::Circle(){
            ...
            return 0;   // 컴파일 오류. 생성자 함수는 값을 리턴해서는 안됨.
        }
        ```

        <br/>
- **생성자는 중복 가능하다.**
