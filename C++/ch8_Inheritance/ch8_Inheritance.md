# Chapter 08 Inheritance(상속)

## 8.1 상속의 개념

### 객체 지향 상속

: 부모 유전자를 자식이 물려받는 유전적 상속과 유사하다.



### C++의 클래스 상속

* **상속**

  * **기본 클래스(base class)** : 부모 클래스
  * **파생 클래스(derived class)** : 자식 클래스

* **다중 상속** : 여러개의 클래스를 동시에 상속받는 것



### 상속의 목적과 장점

* **간결한 클래스 작성**
* **클래스 간의 계층적 분류 및 관리의 용이함**
* **클래스 재사용과 확장을 통한 소프트웨어의 생산성 향상**



## 8.2  클래스 상속과 객체

### 상속 선언

```cpp
class Student : public Person{
   파생클래스명 상속접근지정 기본 클래스명
   // Person을 상속받는 Student 선언
    ...
};
```

> **public, private, protected** 중 어떠한 상속 접근 지정자든 가능



### 파생 클래스 객체와 멤버 호출

* **Point 클래스를 상속받는 ColorPoint 클래스 만들기 예제**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Point {
  	int x, y;
  public:
  	void set(int x, int y) {
  		this->x = x;
  		this->y = y;
  	}
  	void showPoint() {
  		cout << "(" << x << "," << y << ")" << endl;
  	}
  };
  
  // ColorPoint가 Point를 상속받음.
  class ColorPoint : public Point {
  	string color;
  public:
  	void setColor(string color) {
  		this->color = color;
  	}
  	void showColorPoint();
  };
  
  void ColorPoint::showColorPoint() {
  	cout << color << ":";
  	showPoint();	// Point의 showPoint() 호출
  }
  
  int main() {
  	Point p;				// 기본 클래스의 객체 생성
  	ColorPoint cp;			// 파생 클래스의 객체 생성
  	cp.set(3, 4);			// 기본 클래스의 멤버 호출
  	cp.setColor("Red");		// 파생 클래스의 멤버 호출
  	cp.showColorPoint();	// 파생 클래스의 멤버 호출
  }
  ```

  **실행결과**

  ```
  Red:(3,4)
  ```



* **위의 예제에서 상속 클래스 선언 및 구현 과정**
  1. 상속 선언
  2. 파생 클래스 객체 생성
  3. 파생 클래스에서 기본 클래스 멤버 접근
  4. 기본 클래스의 private 멤버의 상속과 접근
  5. 클래스 외부에서 파생클래스의 멤버 호출



## 8.3  상속과 객체 포인터

### 업 캐스팅

: 파생 클래스의 객체를 기본 클래스의 포인터로 가리키는 것, **부모 형식에서 자식 형식을 사용하겠다는 것**

```cpp
ColorPoint cp;
ColorPoint *pDer = &cp;
Point* pBase = pDer;
```



### 다운 캐스팅

: 기본 클래스 포인터가 가리키는 객체를 파생 클래스의 포인터로 가리키는 것

```cpp
ColorPoint cp;
ColorPoint *pDer = &cp;
Point* pBase = pDer;	// 업 캐스팅

pDer = (ColorPoint*)pBase;	// 다운 캐스팅(반드시 강제 타입 변환)
```



## 8.4  protected 접근 지정

* **멤버의 접근 지젖에 따라 접근 가능 여부**

  | O 접근가능<br />X 접근불가능 | private 멤버 | protected 멤버 | public 멤버 |
  | ---------------------------- | ------------ | -------------- | ----------- |
  | 멤버를 선언한 클래스         | O            | O              | O           |
  | 파생 클래스                  | X            | O              | O           |
  | 다른 클래스나 외부 함수      | X            | X              | O           |

  > **protected**로 지정된 멤버는 파생 클래스에게 접근을 허용



* **protected 멤버에 대한 접근 예제**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Point {
  protected:
  	int x, y;
  public:
  	void set(int x, int y) {
  		this->x = x;
  		this->y = y;
  	}
  	void showPoint() {
  		cout << "(" << x << "," << y << ")" << endl;
  	}
  };
  
  // ColorPoint가 Point를 상속받음
  class ColorPoint : public Point {
  	string color;
  public:
  	void setColor(string color) {
  		this->color = color;
  	}
  	void showColorPoint();
  	bool equals(ColorPoint p);
  };
  
  void ColorPoint::showColorPoint() {
  	cout << color << ":";
  	showPoint();	// Point 클래스의 showPoint() 호출
  }
  
  bool ColorPoint::equals(ColorPoint p) {
  	if (x == p.x && y == p.y && color == p.color) {
  		return true;
  	}
  	else {
  		return false;
  	}
  }
  
  int main() {
  	Point p;
  	p.set(2, 3);
  	// p.x = 5;  
  	// p.y = 5;   
  	// x, y 가 Point의 protected 접근 지정자 이기 때문에
  	// 컴파일 오류가 발생한다
  	p.showPoint();
  
  	ColorPoint cp;
  	// cp.x = 10;
  	// cp.y = 10;
  	// x, y 가 ColorPoint의 private 접근 지정자 이기 때문에
  	// 컴파일 오류가 발생한다.
  	cp.set(3, 4);
  	cp.setColor("Red");
  
  	ColorPoint cp2;
  	cp2.set(3, 4);
  	cp2.setColor("Red");
  	cout << ((cp.equals(cp2)) ? "true" : "false") << endl;
  }
  ```

  **실행결과**

  ```
  (2,3)
  true
  ```


## 8.5  상속과 생성자, 소멸자



### 파생 클래스와 기본 클래스의 생성자 호출 및 실행 관계

: 파생 클래스와 기본 클래스는 각각 **생성자**를 가지고 있다.

* **질문1. 파생 클래스의 객체가 생성될 때 파생 클래스의 생성자와 기본 클래스의 생성자가 모두 실행되는가? 아니면 파생 클래스의 생성자만 실행되는가?**

  : 둘 다 실행된다.



* **질문2. 파생 클래스의 생성자와 기본 클래스의 생성자 중에서 어떤 생성자가 먼저 실행되는가?**

  : 기본 클래스(부모 클래스) 생성자 먼저 실행된다.





### 생성자/소멸자의 실행 순서

* **파생 클래스의 객체가 생성될 때**
  1. 기본 클래스의 생성자가 먼저 실행되고
  2. 파생 클래스의 생성자가 나중에 실행
* **파생 클래스의 객체가 소멸될 때**
  1. 파생 클래스의 소멸자가 먼저 실행되고
  2. 기본 클래스의 소멸자가 나중에 실행





### 파생 클래스에서 기본 클래스 생성자 호출

: 파생 클래스의 각 생성자에 대해 함께 실행될 기본 클래스의 생성자를 명시적으로 지정하지 않으면, 컴파일러는 **묵시적으로 기본 클래스의 기본 생성자가 실행되도록 컴파일한다.**



* **컴파일러에 의해 묵시적으로 기본 클래스의 생성자가 선택되는 경우**

  : 기본 클래스의 생성자를 선택하지 않은 경우, 컴파일러는 **기본 클래스의 기본 생성자가 호출**되도록 묵시적으로 선택한다.

  * **예시**

    ```cpp
    int main(){
        B b;
    }
    
    class B : public A{
    public:
        B(){	// A() (기본 클래스의 생성자) 를 호출하도록 컴파일
            cout << "생성자 B" << endl;
        }
    };
    
    class A{
    public:
        A(){
            cout << "매개변수생성자 A" << x << endl;
        }
    };
    ```

    **실행결과**

    ```
    생성자 A
    생성자 B
    ```

    > 컴파일러는 **묵시적으로 기본 클래스의 기본 생성자를 호출**하도록 컴파일함

  * **컴파일 오류 예시**

    ```cpp
    int main(){
        B b;
    }
    
    class B : public A{
    public:
        B(){	// A() (기본 클래스의 생성자) 를 호출하도록 컴파일
            cout << "생성자 B" << endl;
        }
    };
    
    class A{
    public:
        A(int x){	// A() 기본 생성자를 찾을 수 없어서 컴파일 오류 발생
            cout << "매개변수생성자 A" << x << endl;
        }
    };
    ```

    > 파생 클래스의 기본 생성자를 호출하고 파생 클래스의 기본 생성자에서 **기본 클래스의 기본 생성자를 호출했으나 존재하지 않아 컴파일 오류**가 발생한다.

  * **묵시적 호출 다른 예시**

    ```cpp
    int main(){
        B b(5);
    }
    
    class B : public A{
    public:
        B(int x){	// A() (기본 클래스의 생성자) 를 호출하도록 컴파일
            cout << "생성자 B" << endl;
        }
    };
    
    class A{
    public:
        A(){	// 묵시적으로 기본 클래스의 기본 생성자가 호출된다.
            cout << "매개변수생성자 A" << x << endl;
        }
        A(int x){	
            cout << "매개변수생성자 A" << x << endl;
        }
    };
    ```



* **명시적인 기본 클래스의 생성자 선택**

  : 파생 클래스의 생성자를 작성할 때 기본 클래스의 **생성자를 명시적으로 선택하는 방법**



  * **예시**

    ```cpp
    int main(){
        B b(5);
    }
    
    class B : public A{
    public:
        B(){
            cout << "생성자 B" << endl;
        }
        B(int x) : A(x+3){	// 기본 클래스의 생성자를 선택 호출함
            cout << "매개변수생성자 B" << x << endl;
        }
    }
    
    class A{
    public:
        A(){
            cout << "생성자 A" << endl;
        }
        A(int x){	// 파생 클래스의 생성자가 명시적으로 기본 클래스의 생성자를 선택 					호출함
            cout << "매개변수생성자 A" << x << endl;
        }
    }
    ```

* **TV, WideTV, SmartTV의 상속 관계와 생성자 매개 변수 전달 예제**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class TV {
  	int size;
  public:
  	TV() {
  		size = 20;
  	}
  	TV(int size) {
  		this->size = size;
  	}
  	int getSize() {
  		return size;
  	}
  };
  
  class WideTV : public TV {	// TV를 상속받는 WideTV
  	bool videoIn;
  public:
  	WideTV(int size, bool videoIn) : TV(size) {
  		this->videoIn = videoIn;
  	}
  	bool getVideoIn() {
  		return videoIn;
  	}
  };
  
  class SmartTV : public WideTV {	// WideTV를 상속받는 SmartTV
  	string ipAddr;
  public:
  	SmartTV(string ipAddr, int size) : WideTV(size, true) {
  		this->ipAddr = ipAddr;
  	}
  	string getIpAddr() {
  		return ipAddr;
  	}
  };
  
  int main() {
  	SmartTV htv("192.0.0.1", 32);
  	cout << "size=" << htv.getSize() << endl;
  	cout << "videoIn=" << boolalpha << htv.getVideoIn() << endl;
  	cout << "IP=" << htv.getIpAddr() << endl;
  }
  ```

  **실행결과**

  ```
  size=32
  videoIn=true
  IP=192.0.0.1
  ```



## CHECK TIME

1. 다음 클래스 A, B, C에 대해 물음에 답하라.

   ```cpp
   class A{
       int x;
   public:
       A(){
           x = 0;
       }
       A(int x){
           this->x = x;
       }
   };
   
   class B : public A{
       int y;
   public:
       B(int x, int y) : A(x+5){
           this->y = y;
       }
   };
   
   class C : public B{
       int m;
   public:
       C(int x, int y, int z) : B(x,y){
           m = x * y * z;
       }
   };
   ```

   다음의 객체 생성 코드가 실행되면 객체 내의 멤버 변수 x, y, m의 값은 무엇인가?

   1. C c(3, 5, 2);

      ```cpp
      8		// x		
      5		// y
      30		// z
      ```

   2. B b(3, 4);

      ```cpp
      8		// x
      4		// y
      ```




## 8.6  상속의 종류 : public, protected, private 상속

| 상속의 종류 | 상속 내용                                                    |
| ----------- | ------------------------------------------------------------ |
| public      | 기본 클래스의 protected, public 멤버 속성을 **그대로** 계승  |
| private     | 기본 클래스의 protected, public 멤버를 **private**으로 계승<br />- 2차 상속이 되지 않는다. |
| protected   | 기본 클래스의 protected, public 멤버를 **protected**로 계승  |

> **private 멤버는 상속이 되지 않는다.**



* **private 상속 사례**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Base {
  	int a;
  protected:
  	void setA(int a) {
  		this->a = a;
  	}
  public:
  	void showA() {
  		cout << a;
  	}
  };
  
  class Derived : private Base {	// private으로 상속
  	int b;
  protected:
  	void setB(int b) {
  		this->b = b;
  	}
  public:
  	void showB() {
  		cout << b;
  	}
  };
  
  int main() {
  	Derived x;
  	// x.a = 5;			private 멤버는 호출할 수 없다!
  	// x.setA(10);		protected에서 private 멤버로 변경되어 호출 불가!
  	// x.showA();		protected에서 private 멤버로 변경되어 호출 불가!
  	// x.b = 10;		private 멤버는 호출할 수 없다!
  	// x.setB(10);		protected 멤버이기 때문에 main()에서 접근 불가
  	x.showB();	// public은 상관없다.
  }
  ```


* **protected 상속 사례**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Base {
  	int a;
  protected:
  	void setA(int a) {
  		this->a = a;
  	}
  public:
  	void showA() {
  		cout << a;
  	}
  };
  
  class Derived : protected Base {	// protected로 상속
  	int b;
  protected:
  	void setB(int b) {
  		this->b = b;
  	}
  public:
  	void showB() {
  		cout << b;
  	}
  };
  
  int main() {
  	Derived x;
  	// x.a = 5;			private 멤버는 호출할 수 없다!
  	// x.setA(10);		protected 멤버로 변경되어 호출 불가!
  	// x.showA();		protected 멤버로 변경되어 호출 불가!
  	// x.b = 10;		private 멤버는 호출할 수 없다!
  	// x.setB(10);		protected 멤버이기 때문에 main()에서 접근 불가
  	x.showB();	// public은 상관없다.
  }
  ```



* **상속이 중첩될 때 접근 지정 사례**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Base {
  	int a;
  protected:
  	void setA(int a) {
  		this->a = a;
  	}
  public:
  	void showA() {
  		cout << a;
  	}
  };
  
  class Derived : private Base {		// private 상속
  	int b;
  protected:
  	void setB(int b) {
  		this->b = b;
  	}
  public: 
  	void showB() {
  		setA(5);		// protected 멤버이기 때문에 접근 허용
  		showA();		// public 멤버이기 때문에 접근 허용
  		cout << b;
  	}
  };
  
  class GrandDerived : private Derived {	// private 상속
  	int c;
  protected:
  	void showAB(int x) {
  		// setA(x);      private 속성으로 변경되어 상속되기 때문에 접근 불가
  		// showA();		 private 속성으로 변경되어 상속되기 때문에 접근 불가
  		setB(x);	// protected 멤버이기 때문에 파생 클래스인 this는 접근허용 
  	}
  };
  ```



## 8.7  다중 상속

: 하나의 파생 클래스가 여러 클래스를 동시에 상속 받는것을 말한다.



### 다중 상속 선언

* **예시**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class MP3 {
  public:
  	void play();
  	void stop();
  };
  
  class MobilePhone {
  public:
  	bool sendCall();
  	bool receiveCall();
  	bool sendSMS();
  	bool receiveSMS();
  };
  
  class MusicPhone : public MP3, public MobilePhone {	// 다중 상속 선언
  public:
  	void dial();
  };
  ```



### 다중 상속 활용

* **Adder와 Subtractor를 다중 상속받는 Calculator 클래스 작성**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Adder {
  protected:
  	int add(int a, int b) {
  		return a + b;
  	}
  };
  
  class Subtractor {
  protected:
  	int minus(int a, int b) {
  		return a - b;
  	}
  };
  
  // 다중 상속
  class Calculator : public Adder, public Subtractor {
  public:
  	int calc(char op, int a, int b);
  };
  
  int Calculator::calc(char op, int a, int b) {
  	int res = 0;
  	switch (op) {
  	case '+': res = add(a, b); break;		// Adder의 add() 호출
  	case '-': res = minus(a, b); break;		// Subtractor의 minus() 호출
  	}
  	return res;
  }
  
  int main() {
  	Calculator handCalculator;
  	cout << "2 + 4 = " << handCalculator.calc('+', 2, 4) << endl;
  	cout << "100 - 8  = " << handCalculator.calc('-', 100, 8) << endl;
  }
  ```

  **실행결과**

  ```
  2 + 4 = 6
  100 - 8  = 92
  ```



## 8.8  가상 상속

### 다중 상속의 문제점

![1545039208679](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1545039208679.png)

> **m_Top**이 중복되기 때문에 기본 클래스 멤버 접근에 모호한 문제가 발생한다.



### 가상 상속

* **가상 상속 선언**

  : 모호한 접근을 해결하기 위해서 파생 클래스를 선언할 때 기본 클래스 앞에 **virtual 키워드**를 이용하여 **가상 상속**을 선언하면 된다.

* **예시**

  ```cpp
  class In : virtual public BaseIo{	// In 클래스는 BaseIO 클래스를 가상 상속 
      ...
  };
  
  class Out : virtual public BaseIO{	// Out 클래스는 BaseIO 클래스를 가상 상속
      ...
  };
  ```

  > 가상 상속받은 객체는 기본 클래스의 멤버 공간을 오직 한 번만 할당받고, 이미 할당되어 있다면 그 **공간을 공유하도록 지시한다.**



## CHECK TIME

1. 다음 프로그램은 상속으로 인한 모호성을 가지고 있다.

   ```cpp
   class Person{
   public:
       int id;
   };
   class Student : public Person{ };
   class Worker : public Person{ };
   class StudentWorker : public Student, public Worker{
       public : int getId(){ return id; }
   };
   ```

   1. 어떤 라인에서 모호성으로 인해 컴파일 오류가 발생하는가?

      ```cpp
      public : int getId(){ return id; }	// 모호성 오류
      ```

      > **다중 상속으로 인해 Person의 멤버인 id를 2개 가지고 있기 때문에 오류 발생**



   2. 모호성이 없도록 수정하라.

      ```cpp
      #include <iostream>
      #include <string>
      using namespace std;
      
      class Person {
      public:
      	int id;
      };
      class Student : virtual public Person { };
      class Worker : virtual public Person { };
      class StudentWorker : public Student, public Worker {
      	public: int getId() { return id; }
      };
      ```



2. 다중 상속의 모호성은 virtual 키워드를 이용한 가상 상속으로 해결한다. 모호성은 어떤 시점에서 검사되는가? 컴파일 타임인가, 런타임 인가?

   : **컴파일 타임**





# 연습문제

1. 객체 지향 언어의 상속 개념과 가장 가까운 것은?
   1. 오리와 닭은 가축이다.
   2. **스마트폰은 일반 전화기처럼 전화를 할 수 있을 뿐만 아니라 사진도 찍을 수 있다(O)**
   3. 박 군의 집은 아버지가 주신 집이다.
   4. 사람은 원숭이로부터 진화했다.



2. 객체 지향에서의 상속 개념과 가장 가까운 것은?
   1. **TV와 스마트 TV**
   2. 천 소파와 가죽 소파
   3. CPU와 PC
   4. 자동차 엔진과 변속기



3. C++의 상속 특징 중 틀린 것은?
   1. C++에서는 다중 상속을 허용하지만 다중 상속으로 문제가 발생하는 경우도 있다.
   2. C++에서는 상속의 횟수에 제한이 없다.
   3. **protected 멤버는 클래스 내에서 보호받기 때문에 상속되지 않는다. ( X , 상속될수 있다. )**
   4. 상속의 가장 큰 장점은 소프트웨어의 재사용에 있다.



4. 다음 코드에 대해 컴파일 오류가 발생하는 것은?

   ```cpp
   class A{
       public : int w;
   };
   class B : public A{
       public : int x;
   };
   class C : private A{
       public : int y;
   };
   class D : protected B{
       public : int z;
   };
   ```

   1. A a; a.w = 10;
   2. B b; b.w = 10;
   3. C c; c.y = 10;
   4. **D d; d.w = 10; ( X , 클래스 A의 멤버인 w는 protected로 접근이 변경되어 접근을 할 수 없다. )**



5. 다음 코드에서 컴파일 오류가 발생하는 곳을 있는 대로 골라라.

   ```cpp
   #include <iostream>
   #include <string>
   using namespace std;
   
   class A {
   	int s, x;
   protected:
   	void setX(int x) {
   		this->x = x;
   	}
   	void setS(int s) {
   		this->s = s;
   	}
   };
   
   class B : private A {	// 정상 작동
   	int y;
   public:
   	void setXY(int a, int b, int s) {
   		x = a;		// 컴파일 에러
   		y = b;		// 정상
   		setS(s);	// 정상
   	}
   };
   
   ```

   > **A 클래스를 private으로 상속 받았기 때문에 A의 멤버인 x를 가져올 수 없다.**



6. 다음 코드에서 컴파일 오류가 발생하는 곳을 있는 대로 골라라.

   ```cpp
   #include <iostream>
   #include <string>
   using namespace std;
   
   class A {
   	int x;
   public:
   	void setX(int x) {
   		this->x = x;
   	}
   };
   
   class B : protected A {
   	int y;
   public:
   	void setXY(int x, int y) {
   		setX(x);
   		this->y = y;
   	}
   };
   
   int main()
   {
   	A a;
   	B b;
   	a.x = 3;		// private 멤버이기 때문에 호출 불가, 컴파일 에러 
   	b.y = 3;		// private 멤버이기 때문에 호출 불가, 컴파일 에러
   	a.setX(5);		
   	b.setX(5);		// protected로 A를 상속받았기 때문에 호출 불가, 컴파일 에러
   }
   ```


7. 다음 클래스 A, B와 변수가 선언되어 있을 때 물음에 답하라.

   ```cpp
   class A{
     public : int x;  
   };
   class B : public A{
       public : int y;
   };
   A a, *p;
   B b, *q;
   ```

   1. 업 캐스팅과 다운 캐스팅을 골라라.

      1. p = &a;       
      2. p = &b;        **업 캐스팅**
      3. q = (B*)&a;    **다운 캐스팅**
      4. q = &b;        

   2. 다음 코드는 컴파일 오류는 없지만 실행 중에 오류가 발생한다. 그 이유는 무엇인가?

      ```cpp
      p = &a;
      q = (B*)p;
      q->y = 100;		// 실행 중 오류 발생
      ```

      > q가 클래스 B의 포인터 타입이고 y가 클래스 B의 멤버이므로, q->y=100;은 문법적으로는 문제가 없다. 그러나 **실제 포인터 q가 가리키는 것은 A 타입의 객체**이므로 **q가 가리키는 객체 공간에는 멤버 y가 존재하지 않는다**. q->y=100;의 문장은 할당받지 않는 공간 y에 100을 쓰게 되므로 불법적인 메모리 접근이 되어 실행 중에 **오류가 발생한다.**



8. 다음 클래스 A, B와 변수가 선언되어 있을 때 물음에 답하라.

   ```cpp
   #include <iostream>
   #include <string>
   using namespace std;
   
   class A {
   public: int w;
   };
   
   class B : public A {
   public: int x;
   };
   
   class C : public A {
   public: int y;
   };
   
   class D : public B {
   public: int z;
   };
   
   A a; B b; C c; D d;
   
   A *ap = &a;
   B *bp = &b;
   C *cp = &c;
   D *dp = &d;
   ```

   1. 업 캐스팅에 해당하지 않는 것은?

      1. ap = bp;
      2. ap = cp;
      3. **bp = cp; (X, 업, 다운 둘다 아님)**
      4. bp = dp;

   2. 객체 d의 멤버를 모두 나열하라.

   : **z, x, w**

   3. 객체 d의 멤버에 대한 접근 중에서 컴파일 오류가 발생하는 것은?
      1. d.x = 10;
      2. dp->x = 10;
      3. **ap = dp; ap->x = 10; (X, 업 캐스팅 후 ap가 가지고 있는 멤버는 A 클래스의 멤버이다.)**
      4. bp = dp; bp->x = 10;

   4.아래 두 라인을 작성하면 두 번째 라인에서 컴파일 오류가 발생한다. 수정하라.

   ```cpp
   ap = dp;	// 컴파일 오류 발생하지 않음. 업 캐스팅
   dp = ap;	// 컴파일 오류 발생. 다운 캐스팅
   ```

   **정답**

   ```cpp
   ap = dp;
   dp = (D*)ap;
   ```



9. 다음 코드에 대한 물음에 답하라.

   ```cpp
   #include <iostream>
   #include <string>
   using namespace std;
   
   class A {
   public:
   	A() {
   		cout << "생성자 A" << endl;
   	}
   	A(int x) {
   		cout << "생성자 A " << x << endl;
   	}
   };
   
   class B : public A {
   public:
   	B() {
   		cout << "생성자 B" << endl;
   	}
   	B(int x) {
   		cout << "생성자 B " << x << endl;
   	}
   	B(int x, int y) : A(x+y+2) {
   		cout << "생성자 B " << x * y * 2 << endl;
   	}
   };
   ```

   다음과 같이 객체 b가 생성될 때 화면에 출력되는 내용은?

   1. B b;

      ```
      생성자 A
      생성자 B
      ```

   2. B b(10);

      ```
      생성자 A
      생성자 B 10
      ```

   3. B b(10, 20);

      ```
      생성자 A 32
      생성자 B 400
      ```



10. 다음 코드에 대해 물음에 답하라.

    ```cpp
    #include <iostream>
    #include <string>
    using namespace std;
    
    class A {
    public:
    	A(int x) {
    		cout << "생성자 A " << x << endl;
    	}
    };
    
    class B : public A {
    public:
    	B() {
    		cout << "생성자 B" << endl;
    	}
    	B(int x) {
    		cout << "생성자 B " << x << endl;
    	}
    };
    ```

    1. 위 코드에서 컴파일 오류가 발생하는 곳은 어디인가? 컴파일 오류의 내용은 무엇인가?

       **B(), B(int x) 부분이 컴파일 오류가 발생한다. 이유는 A의 기본 생성자인 A()가 없기 때문이다.**

    2. 다음 코드와 실행한 결과가 아래와 같도록 생성자 B()를 수정하라.

       **B b;**

       실행결과

       ```
       생성자 A 20
       생성자 B
       ```

       **정답**

       ```cpp
       B(){
           A(20);
           cout << "생성자 B" << endl;
       }
       ```

    3. 다음 코드와 실행한 결과가 아래와 같도록 생성자 B(int x)를 수정하라.

       **B b(15)**

       실행결과

       ```
       생성자 A 35
       생성자 B 15
       ```

       **정답**

       ```cpp
       B(int x) {
       		A(35);
       		cout << "생성자 B " << x << endl;
       	}
       ```

11. 파생 클래스의 객체나 생성되거나 소멸될 때에 관한 설명 중 틀린 것은?

    1. 파생 클래스 객체 생성 시, 파생 클래스와 기본 클래스의 생성자가 모두 실행된다.
    2. 개발자는 파생 클래스의 생성자가 실행될 때 함께 실행될 기본 클래스의 생성자를 선택할 수 있다.
    3. **파생 클래스 객체 소멸 시, 기본 클래스의 소멸자가 먼저 실행되고 파생 클래스의 소멸자가 나중에 실행된다. (X, 파생 클래스의 객체가 먼저 소멸된다.)**
    4. 파생 클래스의 생성자가 실행될 때 함께 실행할 기본 클래스의 생성자를 찾는 과정은 실행 시간이 아닌 컴파일 시에 이루어진다.



12. 다중 상속에 관한 설명 중 틀린 것은?

    1. 자바에는 클래스의 다중 상속이 없다.
    2. 기존 클래스를 재사용하여 프로그래밍의 생산성을 높인다.
    3. 다중 상속으로 인한 모호성이 발생할 수 있다.
    4. **다중 상속으로 인한 모호성은 실행 시간에 발견되므로 동적 바인딩을 통한 가상 상속으로 해결한다. (X, 가상 상속을 통해 다중 상속 모호성을 해결한다.)**

13. 다음 클래스 Rocket과 클래스 Computer를 동시에 상속받아 클래스 Satellite을 선언하라.

    ```cpp
    class Rocket {...};
    class Computer {...};
    ```

    **정답**

    ```cpp
    class Satellite : public Rocket, public Computer{
        ...
    }
    ```

14. 다음 클래스가 있을 때 물음에 답하라.

    ```cpp
    class Pen{...};
    class Eraser {...};
    class Lock {...};
    ```

    1. 클래스 Pen과 클래스 Eraser를 동시에 상속받는 클래스 HiPen을 선언하라.

       ```cpp
       class HiPen : public Pen, public Eraser{
           ...
       }
       ```

    2. 클래스 Pen, Eraser, Lock을 동시에 상속받는 클래스 OmniPen을 선언하라.

       ```cpp
       class OmniPen : public Pen, public Eraser, public Lock{
           ...
       }
       ```

15. 다음 4개의 클래스가 있다.

    ```cpp
    class Vehicle{
        public: int power;
    };
    class Car: public Vehicle{
        public: int color;
    };
    class Airplane: public Vehicle{
        public: int altitude;
    };
    class FlyingCar: public Car, public Airplane{
        public: void go();
    };
    ```

    다음 코드 중에서 컴파일 오류가 발생하는 라인을 발견하고 오류를 수정하라. 컴파일 오류의 원인이 무엇인지 설명하라.

    ```cpp
    FlyingCar fCar;
    fCar.go();
    fCar.altitude = 2000;
    fCar.color = 2000;
    fCar.power = 2000;		// 다중 상속으로 인해 power의 멤버를 호출할 때 모호하기 때문에
    ```

    **정답**

    ```cpp
    ...
    class Car: virtual public Vehicle{...};
    class Airplane: virtual public Vehicle{...};
    ...
    ```

16. 다음 클래스들의 상속 관계가 가진 잠재적 문제점을 지적하고 수정하라.

    ```cpp
    class TV{
        public: int screenSize;
    };
    class ColorTV: public TV{
        public: int color;
    };
    class InternetTV: public TV{
        public: string ipAddr;
    };
    ```

    > **다중 상속으로 인해 TV의 멤버를 호출할 때 모호성이 존재할 수 있다.**
    >
    > 그러므로, 상속시켜줄때 **virtual**을 붙여주어 가상 상속을 해주어야 한다.