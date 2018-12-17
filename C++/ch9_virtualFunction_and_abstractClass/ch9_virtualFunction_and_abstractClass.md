# Chapter9 virtual function and abstract class(가상 함수와 추상 클래스)



## 9.1  상속 관계에서의 함수 중복

: **함수 중복(오버로딩)**을 통해 기본 클래스의 함수와 동일한 함수를 파생 클래스에 선언할 수 있다.

* **상속 관계에서 함수를 중복하는 경우**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Base {
  public:
  	void f() {
  		cout << "Base::f() called" << endl;
  	}
  };
  
  class Derived : public Base {
  public:
  	void f() {
  		cout << "Derived::f() called" << endl;
  	}
  };
  
  void main() {
  	Derived d, *pDer;
  	pDer = &d;		// 객체 d를 가르킨다.
  	pDer->f();		// Derived의 멤버 f() 호출
  
  	Base* pBase;
  	pBase = pDer;	// 업캐스팅. 객체 d를 가리킨다.
  	pBase->f();		// Base의 멤버 f() 호출
  }
  ```

  **실행결과**

  ```
  Derived::f() called
  Base::f() called
  ```

* **범위 지정 연산자로 Base 멤버 접근 가능**

  ```cpp
  pDer->f();		// Derived의 멤버 f() 호출
  pDer->Base::f()	// Base의 멤버 f() 호출
  ```



## 9.2  가상 함수와 오버라이딩

### 가상 함수와 오버라이딩

* **오버라이딩의 개념**

  : 파생 클래스에게 기본 클래스에 작성된 **가상 함수를 중복 작성하여**, **기본 클래스에 작성된 가상 함수를 무력화시키고**, 객체의 주인 노릇을 하는 것이다. 기본 클래스의 포인터를 이용하든 파생 클래스의 포인터를 이용하든 가상 함수를 호출하면, **파생 클래스에 오버라이딩된 함수가 항상 실행된다.**



* **가상 함수 선언과 오버라이딩**

  **가상 함수(virtual function)** : virtual 키워드로 선언된 멤버 함수이다. virtual은 컴파일러에게 자신에 대한 호출 바인딩을 실행 시간까지 미루도록 지시하는 키워드이다.

  ```cpp
  class Base{
  public:
      virtual void f();	// f()는 가상 함수
  };
  ```

  > 파생 클래스에서 기본 클래스의 **가상 함수와 완전히 동일한 원형의 함수**를 재정의 하는 것을 **'함수 오버라이딩(function overriding)'** 혹은 간단히 **'오버라이딩'**이라 한다.


* **오버로딩과 오버라이딩 예시**

  * **오버로딩(함수 중복)**

    ```cpp
    class Base{
    public:
        void f(){
            cout << "Base" << endl;
        }
    };
    
    class Derived : public Base{
    public:
        void f(){
            cout << "Derived" << endl;
        }
    };
    ```

    > **Derived 객체에는 동등한 호출 기회를 가진 함수 f()가 두 개 존재**

  * **오버라이딩**

    ```cpp
    class Base{
    public:
        virtual void f(){
            cout << "Base" << endl;
        }
    };
    
    class Derived : public Base{
    public:
        virtual void f(){
            cout << "Deried" << endl;
        }
    };
    ```

    > **Derived 객체에는 두 개의 함수 f()가 존재하지만, Base의 f()는 존재감을 잃고, 항상 Derived의 f()가 호출된다.**



### 오버라이딩과 가상 함수 호출

* **오버라이딩 사례**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Base {
  public:
  	virtual void f() {
  		cout << "Base" << endl;
  	}
  };
  
  class Derived : public Base {
  public:
  	virtual void f() {
  		cout << "Derived" << endl;
  	}
  };
  
  void main() {
  	Derived d, *pDer;
  	pDer = &d;		// 객체 d를 가리킨다.
  	pDer->f();		// Derived::f() 호출
  
  	Base b;
  	Base* pBase;
  	pBase = &b;		// Base b를 가리키게 함
  	pBase->f();		// Base b의 f() 호출
  	pBase = pDer;	// 업 캐스팅. 객체 d를 키리킨다.
  	pBase->f();		// 동적 바인딩 발생!! Derived::f() 실행
  }
  ```

  **실행결과**

  ```
  Derived
  Base
  Derived
  ```


* **오버라이딩의 목적**

  : 기본 클래스의 **가상 함수**는 상속받는 파생 클래스에서 구현해야 할 일종의 **함수 인터페이스**를 제공한다. 다시 말하면, 가상 함수는 '하나의 인터페이스에 대해 서로 다른 모양의 구현'이라는 **다형성(polymorphism)**을 실현하는 도구이다.

* **오버라이딩 예제**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Shape {
  protected:
      // 가상 함수 선언. 파생 클래스에서 재정의할 함수에 대한 인터페이스 역할
  	virtual void draw(){}
  };
  
  class Circle : public Shape {
  protected:
  	virtual void draw() {	// 오버라이딩
  		// Circle을 그린다.
  	}
  };
  
  class Rect : public Shape {	// 오버라이딩
  protected:
  	virtual void draw() {
  		// Rect을 그린다.
  	}
  };
  
  class Line : public Shape {	// 오버라이딩
  protected:
  	virtual void draw() {
  		// Line을 그린다.
  	}
  };
  
  // Shape의 draw()가 호출되지 않고, p가 가리키는 객체 내에 오버라이딩된 draw() 호출
  void paint(Shape* p) {
  	p->draw();
  }
  
  paint(new Circle());	// Circle을 그린다.
  paint(new Rect());		// Rect을 그린다.
  paint(new Line());		// Line을 그린다.
  ```



### 동적 바인딩

* **동적 바인딩 : 오버라이딩된 함수가 무조건 호출**

  : 가상 함수를 호출하는 코드를 컴파일할 때, 컴파일러는 바인딩을 실행 시간에 결정하도록 미루어둔다. 나중에 가상 함수가 호출되면, 실행 중에 객체 내에 오버라딩된 가상 함수를 동적으로 찾아 호출한다.



  : 동적 바인딩은 **실행 시간 바인딩(run-time binding)** 혹은 **늦은 바인딩(late binding)**이라고도 부른다.

* **동적 바인딩이 발생하는 구체적 경우**

  : 파생 클래스의 객체에 대해, 기본 클래스의 포인터로 가상 함수가 호출될 때 일어난다.

  1. 기본 클래스 내의 멤버 함수가 가상 함수 호출
  2. 파생 클래스 내의 멤버 함수가 가상 함수 호출
  3. main()과 같은 외부 함수에서 기본 클래스의 포인터로 가상 함수 호출
  4. 다른 클래스에서 가상 함수 호출

* **paint() 함수에서 Shape의 draw() 호출**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Shape {
  public:
  	void paint() {
  		draw();
  	}
  	virtual void draw() {
  		cout << "Shape::draw() called" << endl;
  	}
  };
  
  int main() {
  	Shape *pShape = new Shape();
  	pShape->paint();
  	delete pShape;
  }
  ```

  **실행결과**

  ```
  Shape::draw() called
  ```

* **paint() 함수에서 동적 바인딩에 의해 Circle의 draw() 호출**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Shape {
  public:
  	void paint() {
  		draw();		// 기본 클래스에서 파생 클래스의 함수를 호출하는 사례
  	}
  	virtual void draw() {
  		cout << "Shape::draw() called" << endl;
  	}
  };
  
  class Circle : public Shape {
  public:
  	virtual void draw() {
  		cout << "Circle::draw() called" << endl;
  	}
  };
  
  int main() {
  	Shape *pShape = new Circle();	// 업 캐스팅
  	pShape->paint();
  	delete pShape;
  }
  ```

  **실행결과**

  ```
  Circle::draw() called
  ```


### C++ 오버라이딩의 특징

* **오버라이딩의 성공 조건과 실패**

  : 가상 함수의 이름과 매개 변수 타입, 개수뿐 아니라 리턴 타입도 일치해야 오버라이딩이 성공한다.

  * **리턴 타입이 달라 오버라이딩이 실패한 사례**

    ```cpp
    #include <iostream>
    #include <string>
    using namespace std;
    
    
    class Base {
    public:
    	virtual void success();
    	virtual void fail();
    	virtual void g(int);
    };
    
    class Derived : public Base {
    public:
    	virtual void success();				// 오버라이딩 성공
    	virtual int fail();					// 오버라이딩 실패, 리턴 타입이 다름
    	virtual void g(int, double);		// 오버로딩 사례
    };
    ```

* **오버라이딩 시 virtual 지시어 생략 가능**

  : 가상 함수의 virtual 속성은 **상속**되는 성질이 있어서, 파생 클래스에서 **virtual 키워드를 생략해도** 자동으로 가상 함수가 된다.

  * **예시**

    ```cpp
    #include <iostream>
    #include <string>
    using namespace std;
    
    class Base {
    public:
    	virtual void f();	// 가상 함수
    };
    
    class Derived :public Base {
    public:
    	virtual void f();	// 가상 함수. virtual 생략 가능
    };
    
    class GrandDerived : public Derived {
    public:
    	void f();	// 가상함수. virtual 생략 가능
    };
    ```

* **상속이 반복되는 경우 가상 함수 호출**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Base {
  public:
  	virtual void f() {	// 가상 함수
  		cout << "Base::f() called" << endl;
  	}
  };
  
  class Derived : public Base {
  public:
  	void f() {	// 오버라이딩
  		cout << "Derived::f() called" << endl;
  	}
  };
  
  class GrandDerived : public Derived {
  public:
  	void f() {	// 오버라이딩
  		cout << "GrandDerived::f() called" << endl;
  	}
  };
  
  int main() {
  	GrandDerived g;
  	Base *bp;
  	Derived *dp;
  	GrandDerived *gp;
  	
  	bp = dp = gp = &g;	// 모든 포인터가 객체 g를 가리킴, 다운 캐스팅
  
  	bp->f();	// Base의 멤버 f() 호출
  	dp->f();	// Derived의 멤버 f() 호출
  	gp->f();	// GrandDerived의 멤버 f() 호출
  }
  ```

  **실행결과**

  ```
  GrandDerived::f() called
  GrandDerived::f() called
  GrandDerived::f() called
  ```

  > **동적 바인딩에 의해 모두 GrandDerived의 함수 f() 호출**



### 오버라이딩과 범위 지정 연산자(::)

: 정적 바인딩 지시, **기본 클래스::가상함수()** 형태로 기본 클래스의 가상 함수를 정적 바인딩으로 호출할 수 있다.

* **범위 지정 연산자를 이용한 기본 클래스의 가상 함수 호출**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Shape {
  public:
  	virtual void draw() {
  		cout << "--Shape--";
  	}
  };
  
  class Circle :public Shape {
  public:
  	int x;
  	virtual void draw() {
  		Shape::draw();		// 기본 클래스의 draw() 호출
  		cout << "Circle" << endl;
  	}
  };
  
  int main() {
  	Circle circle;
  	Shape *pShape = &circle;
  
  	pShape->draw();			// 동적 바인딩 발생. draw()가 virtual 이므로
  	pShape->Shape::draw();	// 정적 바인딩 발생. 범위 지정 연산자로 인해
  }
  ```

  **실행결과**

  ```
  --Shape--Circle
  --Shape--
  ```



### 가상 소멸자

: 기본 클래스의 소멸자를 만들 때 가상 함수로 작성할 것을 권함. 파생 클래스의 객체가 기본 클래스에 대한 포인터로 delete 되는 상황에서도 정상적인 소멸이 되도록 하기 위해서이다.



* **소멸자를 가상 함수로 선언하지 않은 경우**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Base {
  public:
  	~Base();
  };
  
  class Derived :public Base {
  public:
  	~Derived();
  };
  
  int main() {
  	Base *p = new Derived();
  	delete p;	// ~Base() 소멸자만 실행
  }
  ```

  > **~Base()만 실행되고 ~Derived()가 실행되지 않는다.**



* **소멸자를 가상 함수로 선언한 경우**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Base {
  public:
  	virtual ~Base();	// 동적 바인딩으로 Derived의 소멸자로 넘어간다
  };
  
  class Derived :public Base {
  public:
  	// 파생 클래스의 소멸자는 자신의 코드를 실행 후
  	// 기본 클래스의 소멸자를 호출하도록 컴파일 함
  	virtual ~Derived();	
  };
  
  int main() {
  	Base *p = new Derived();
  	delete p;
  }
  ```

  > 1. ~Base() 소멸자 호출
  > 2. ~Derived() 실행(동적 바인딩)
  > 3. ~Base() 실행



* **소멸자를 가상 함수로 선언**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Base {
  public:
  	virtual ~Base() {	// 가상 소멸자
  		cout << "~Base()" << endl;
  	}
  };
  
  class Derived :public Base {
  public:
  	virtual ~Derived() { // 가상 소멸자
  		cout << "~Derived()" << endl;
  	}
  };
  
  int main() {
  	Derived *dp = new Derived();
  	Base *bp = new Derived();
  
  	delete dp;	// Derived의 포인터로 소멸
  	delete bp;	// Base의 포인터로 소멸
  }
  ```

  **실행결과**

  ```
  ~Derived()
  ~Base()
  ~Derived()
  ~Base()
  ```

  > 소멸자를 가상 함수로 선언하면, 객체를 기본 클래스의 포인터로 소멸하든, 파생 클래스의 포인터로 소멸하든 **파생 클래스와 기본 클래스의 소멸자를 모두 실행하는 정상적인 소멸의 과정이 진행된다.** 



### 오버로딩(overloading)과 오버라이딩(overriding)

* **오버라이딩** : 기본 클래스에 있는 가상 함수를 이름과 매개 변수 타입, 매개 변수 개수, 리턴 타입등 원형이 완전히 일치하는 멤버 함수를 파생 클래스에서 재정의하는 것

* **오버로딩** : 함수 이름은 같지만, 매개 변수 타입이나 개수가 다른 함수들이 클래스나 외부에 여러 개 중복해서 작성하는 것



| 비교 요소           | 오버로딩                                                     | 오버라이딩                                                   |
| ------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 정의                | 매개 변수 타입이나 개수가 다르지만, 이름이 같은 함수들이 중복 작성되는 것 | 기본 클래스에 선언된 가상 함수를 파생 클래스에서 이름, 매개 변수 타입, 매개 변수 개수, 리턴 타입까지 완벽히 같은 원형으로 재작성 하는 것 |
| 존재                | 외부 함수들 사이, 한 클래스의 멤버들, 상속 관계              | 상속 관계, 가상 함수에서만 적용                              |
| 목적                | 이름이 같은 여러 개의 함수를 중복 작성하여 사용의 편의성 향상 | 기본 클래스에 구현된 가상 함수를 무시하고, 파생 클래스에서 새로운 기능으로 재정의하고자 함 |
| 바인딩              | 정적 바인딩. 컴파일 시에 중복된 함수들의 호출 구분           | 동적 바인딩. 실행 시간에 오버라이딩된 함수를 찾아 실행       |
| 관련 객체 지향 특성 | 다형성                                                       | 다형성                                                       |



## CHECK TIME

1. 다음 코드를 보자.

   ```cpp
   #include <iostream>
   #include <string>
   using namespace std;
   
   class A {
   public:
   	void f() {
   		cout << "A::f()" << endl;
   	}
   	virtual void g() {
   		cout << "A::g()" << endl;
   	}
   	~A() {
   		cout << "~A()" << endl;
   	}
   };
   
   class B : public A {
   public:
   	void f() {
   		cout << "B::f()" << endl;
   	}
   	virtual void g() {
   		cout << "B::g()" << endl;
   	}
   	~B() {
   		cout << "~B()" << endl;
   	}
   };
   
   int main() {
   	B *q = new B();
   	A *p = q;
   }
   ```

   1. main()의 끝에 다음 각 보기를 추가하였을 때, 실행 결과는 무엇이며 동적 바인딩이 발생하는 보기는 어느 것인가?
      1. p->f();  **A::f()**
      2. **p->g(); (O, B::g()가 출력되며 동적 바인딩이 됨을 확인할 수 있다.)**
      3. q->f();  **B::g()**
      4. q->g();  **B::g()**



2. main()의 끝에 다음 각 코드를 별도로 실행한다면 실행 결과는 무엇인가?

   1. delete p;

      ```
      ~A()
      ```

      > 포인터 p는 A의 포인터이기 때문에 A만 소멸하게 된다.



   2. delete q;

      ````
      ~B()
      ~A()
      ````

      > 포인터 q는 B를 동적 할당했기 때문에 동적 바인딩으로 인해 오버라이딩된 함수를 반드시 호출하게 되어 A, B 모두 소멸시킨다.

   3. 클래스 A의 소멸자에 virtual 키워드를 붙였을 때, delete p;와 delete q;의 실행 결과는 어떻게 되는가?

      1. delete p;

         ```
         ~B()
         ~A()
         ```

         > A의 소멸자에 가상 함수를 선언했기 때문에 동적 바인딩으로 인해 오버라이딩된 함수를 반드시 호출하게 되어 A, B 모두 소멸시킨다.



      1. delete q;
    
         ```
         ~B()
         ~A()
         ```
    
         > 포인터 q는 B를 동적 할당했기 때문에 동적 바인딩으로 인해 오버라이딩된 함수를 반드시 호출하게 되어 A, B 모두 소멸시킨다.





## 9.3  가상 함수와 오버라이딩의 활용 사례

: 가상 함수는 파생 클래스에서 재정의할 함수를 알려주는 **인터페이스**의 역할을 한다.



### 가상 함수를 가진 기본 클래스의 목적

![1545066056191](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1545066056191.png)

* **코드 정리**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Shape {
  	Shape* next;
  protected:
  	virtual void draw();
  public:
  	Shape() {
  		next = NULL;
  	}
  	virtual ~Shape(){}
  	void paint();
  	Shape* add(Shape* p);
  	Shape* getNext() {
  		return next;
  	}
  };
  
  void Shape::paint() {
  	draw();
  }
  
  void Shape::draw() {
  	cout << "--Shape--" << endl;
  }
  
  Shape* Shape::add(Shape *p) {
  	this->next = p;
  	return p;
  }
  
  class Circle : public Shape {
  protected:
  	virtual void draw() {
  		cout << "Circle" << endl;
  	}
  };
  
  class Rect : public Shape {
  protected:
  	virtual void draw() {
  		cout << "Rectangle" << endl;
  	}
  };
  
  class Line : public Shape {
  protected:
  	virtual void draw() {
  		cout << "Line" << endl;
  	}
  };
  ```



### 가상 함수 오버라이딩

* **파생 클래스마다 다르게 구현하는 다형성**

  : 파생 클래스들은 가상 함수인 draw() 함수를 재정의 함으로써 어떤 경우에도 **자신이 만든 draw() 함수가 호출되는 것을 보장**받는다.

  ```cpp
  void Circle::draw(){ cout << "Circle" << endl; }
  void Rect::draw(){ cout << "Rectangle" << endl; }
  void Line::draw(){ cout << "Line" << endl; }
  ```



### 동적 바인딩 실행

* **파생 클래스의 가상 함수실행**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Shape {
  	Shape* next;
  protected:
  	virtual void draw();
  public:
  	Shape() {
  		next = NULL;
  	}
  	virtual ~Shape(){}
  	void paint();
  	Shape* add(Shape* p);
  	Shape* getNext() {
  		return next;
  	}
  };
  
  void Shape::paint() {
  	draw();
  }
  
  void Shape::draw() {
  	cout << "--Shape--" << endl;
  }
  
  Shape* Shape::add(Shape *p) {
  	this->next = p;
  	return p;
  }
  
  class Circle : public Shape {
  protected:
  	virtual void draw() {
  		cout << "Circle" << endl;
  	}
  };
  
  class Rect : public Shape {
  protected:
  	virtual void draw() {
  		cout << "Rectangle" << endl;
  	}
  };
  
  class Line : public Shape {
  protected:
  	virtual void draw() {
  		cout << "Line" << endl;
  	}
  };
  
  int main() {
  	Shape *pStart = NULL;
  	Shape *pLast;
  
  	pStart = new Circle();		// 처음에 원 도형을 생성한다.
  	pLast = pStart;
  
  	pLast = pLast->add(new Rect());		// 사각형 객체 생성
  	pLast = pLast->add(new Circle());	// 원 객체 생성
  	pLast = pLast->add(new Line());		// 선 객체 생성
  	pLast = pLast->add(new Rect());		// 사각형 객체 생성
  
  	// 현재 연결된 모든 도형을 화면에 그린다.
  	Shape* p = pStart;
  	while (p != NULL) {
  		p->paint();
  		p = p->getNext();
  	}
  
  	// 현재 연결된 모든 도형을 삭제한다.
  	p = pStart;
  	while (p != NULL) {
  		Shape* q = p->getNext();	// 다음 도형 주소 기억
  		delete p;	// 현재 도형 객체 소멸
  		p = q;		// 다음 도형의 주소를 p에 저장
  	}
  }
  ```

  **실행 결과**

  ```
  Circle
  Rectangle
  Circle
  Line
  Rectangle
  ```



### 기본 클래스의 포인터 활용

* **기본 클래스의 포인터로 파생 클래스 접근(업캐스팅)**
  * pStart, pLast, p의 타입이 Shape*
  * 링크드 리스트를 따라 Shape을 상속받은 파생 객체들 접근
  * p->paint()의 간단한 호출로 파생 객체에 오버라이딩된 draw() 함수 호출



## 9.4  추상 클래스

### 순수 가상 함수

: 함수의 **코드가 없고 선언만 있는 가상 함수**. 순수 가상 함수는 멤버 함수의 원형 뒤에 =0; 으로 선언한다

* **예시**

  ```cpp
  class Shape{
  public:
      virtual void drow()=0;	// 순수 가상 함수 선언
  };
  ```



### 추상 클래스(abstract class)

: 최소 하나의 순수 가상 함수를 가진 클래스

* **예시**

  ```cpp
  class Shape{	// Shape는 추상 클래스
  public:
      void paint(){
          draw();		// 순수 가상 함수를 호출할 수 있다.
      }
      virtual void draw()=0;	// 순수 가상 함수
  };
  ```

  > 추상 클래스는 실행 코드가 없는 순수 가상 함수를 가지고 있기 때문에 **불완전한 클래스**이다. 그러므로 응용프로그램에서 **추상 클래스의 인스턴스(객체)를 생성할 수 없다.**
  >
  > ```cpp
  > Shape shape;	// 컴파일 오류
  > Shape *p = new Shape();	// 컴파일 오류
  > Shape *p;	// 추상 클래스의 포인터 선언, 컴파일 오류 X
  > ```



### 추상 클래스의 목적

* **추상 클래스의 인스턴스를 생성할 목적 아님**
* **상속에서 기본 클래스의 역할을 하기 위함**
  * 순수 가상 함수를 통해 **파생 클래스에서 구현할 함수의 형태(원형)을 보여주는 인터페이스 역할**
  * 추상 클래스의 모든 멤버 함수를 순수 가상 함수로 선언할 필요 없다.



### 추상 클래스의 구현

: 파생 클래스에 추상 클래스의 순수 가상 함수의 코드를 작성하는 것. 파생 클래스가 온전한 클래스가 되려면 상속받은 추상 클래스의 **모든 순수 가상 함수를 오버라이딩하여 구현**하여야 한다.

* **예시**

  ```cpp
  class Shape{	// Shape는 추상 클래스
  public:
      void paint(){
          draw();
      }
      virtual void draw() = 0;	// 순수 가상 함수
  };
  
  class Circle: public Shape{	// Circle은 정상 클래스
      public:
      virtual void draw(){	// 순수 가상 함수 구현
          cout << "Circle";
      }
      string toString(){
          return "Circle 객체";
      }
  };
  ```

  > Circle은 이제 추상 클래스가 아니므로 다음과 같이 객체를 생성할 수 있다.
  >
  > ```cpp
  > Circle circle;	// 정상 코드
  > Circle *p = new Circle();	// 정상 코드
  > ```



* **추상 클래스 구현 연습**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Calculator {	// 추상 클래스
  public:
  	// 두 정수의 합 리턴
  	virtual int add(int a, int b) = 0;			
  
  	// 두 정수의 차 리턴
  	virtual int subtract(int a, int b) = 0;		
  
  	// 배열 a의 평균 리턴. size는 배열의 크기
  	virtual double average(int a[], int size) = 0;	
  };
  
  class GoodCalc :public Calculator {	// 추상 클래스 구현
  public:
  	int add(int a, int b) {
  		return a + b;
  	}
  	int subtract(int a, int b) {
  		return a - b;
  	}
  	double average(int a[], int size) {
  		double sum = 0;
  		for (int i = 0; i < size; i++) {
  			sum += a[i];
  			return sum / size;
  		}
  	}
  };
  
  int main() {
  	int a[] = { 1,2,3,4,5 };
  	Calculator *p = new GoodCalc();
  	cout << p->add(2, 3) << endl;
  	cout << p->subtract(2, 3) << endl;
  	cout << p->average(a, 5) << endl;
  	delete p;
  }
  ```

  **실행 결과**

  ```
  5
  -1
  0.2
  ```



* **추상 클래스를 상속받는 파생 클래스 구현 연습**

  ```cpp
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Calculator {	// 추상 클래스
  	void input() {
  		cout << "정수 2 개를 입력하세요>> ";
  		cin >> a >> b;
  	}
  protected:
  	int a, b;
  	// 순수 가상 함수
  	virtual int calc(int a, int b) = 0;	// 두 정수의 합 리턴
  public:
  	void run() {
  		input();
  		cout << "계산된 값은 " << calc(a, b) << endl;
  	}
  };
  
  class Adder : public Calculator {
  protected:
  	int calc(int a, int b) {	// 순수 가상 함수 구현
  		return a + b;
  	}
  };
  
  class Subtractor : public Calculator {
  protected:
  	int calc(int a, int b) {	// 순수 가상 함수 구현
  		return a - b;
  	}
  };
  
  int main() {
  	Adder adder;
  	Subtractor subtractor;
  
  	adder.run();
  	subtractor.run();
  }
  ```



  **실행 결과**

  ```cpp
  정수 2 개를 입력하세요>> 5 3
  계산된 값은 8
  정수 2 개를 입력하세요>> 5 3
  계산된 값은 2
  ```


## CHECK TIME

1. 다음에서 추상 클래스는?

   1. ```cpp
      class Base{
          virtual void run();
      };
      ```

   2. ```cpp
      abstact class Base{
          virtual void run();
      };
      ```

   3. ```cpp
      class Base{
          virtual void run() = 0;
      };
      ```

   4. ```cpp
      abstact class Base{
        virtual void run() = 0;  
      }
      ```

      > 3번. **Base 클래스에 순수 가상 함수가 1개 이상 존재하기 때문에**

2. 만일 Shape이 추상 클래스라고 하면 다음 중 옳은 코드는?

   1. Shape shape;
   2. **Shape *p; (O)**
   3. Shape *p = new Shape();
   4. class Circle: public Shape{}; Circle circle;



# 연습문제

1. 호출하는 함수의 결정을 실행 시간에 하도록 컴파일러에게 지시하는 키워드는?
   1. **virtual (O, 가상 함수)**
   2. staitc
   3. public
   4. extern



2. 기본 클래스의 가상 함수와 동일한 타입의 함수를 파생 클래스에서 재정의하는 것을 무엇이라고 하는가?
   1. overloading
   2. **overriding (O, 오버라이딩)**
   3. vitual
   4. dynamic binding



3. 다음 중 의미가 다른 하나는?
   1. dynamic binding
   2. late binding
   3. **compile-time binding (X)**
   4. run-time binding



4. 오버로딩, 오버라이딩, 연산자 중복 등은 C++ 언어의 어떤 객체 지향 특성인가?

   **다형성**



5. 다음 코드에 대해 물음에 답하여라.

   ```cpp
   class Base {
   public:
   	void func() {
   		f();
   	}
   	void f() {
   		cout << "Base::f() called" << endl;
   	}
   };
   
   class Derived :public Base {
   public:
   	void f() {
   		cout << "Derived::f() called" << endl;
   	}
   };
   ```

   1. 기본 클래스와 파생 클래스는 무엇인가?

      1. 기본 클래스 : **Base**
      2. 파생 클래스 : **Derived**

   2. 다음 코드가 실행될 때 화면에 출력되는 내용은?

      ```cpp
      Derived der;
      der.f();
      
      Base base;
      Base*p = &base;
      p->f();		// 오버라인딩 된 함수를 찾아서 실행
      p = &der;
      p->f();		// 오버라인딩 된 함수를 찾아서 실행
      p->func();	// 오버라인딩 된 함수를 찾아서 실행
      ```

      **실행 결과**

      ```
      Derived::f() called
      Base::f() called
      Base::f() called
      Base::f() called
      ```


6. 다음 코드에 대해 물음에 답하여라.

   ```cpp
   class A {
   public:
   	void func() {
   		f();
   	}
   	virtual void f() {
   		cout << "A::f() called" << endl;
   	}
   };
   
   class B :public A {
   public:
   	void f() {
   		cout << "B::f() called" << endl;
   	}
   };
   
   class C :public B {
   public:
   	void f() {
   		cout << "C::f() called" << endl;
   	}
   };
   ```

   1. 다음 함수 중 가상 함수를 모두 골라라.

      1. **A의 f() (O)**

      2. **B의 f() (O)**

      3. **C의 f() (O)**

      4. A의 func()

   2. 다음 코드가 실행될 때 출력되는 결과는 무엇인가?

      ```cpp
      C c;
      c.f();
      A* pa;
      B* pb;
      pa = pb = &c;
      pb->f();
      pa->f();
      pa->func();
      ```

      **실행 결과**

      ```
      C::f() called
      C::f() called
      C::f() called
      C::f() called
      ```



7. 다음 빈칸에 적절한 단어를 보기에서 골라 삽입하라.

   ```cpp
   동일한 이름의 변수나 함수가 여러 곳에 선언되었을 때, 가장 `가까운` 범위에 선언된 이름을 사용하는 규칙을 컴퓨터 언어 이론에서 `범위 규칙`이라고 한다. `범위 지정 연산자`를 사용하면 클래스 멤버와 동일한 이름의 외부 함수를 클래스 내에서 호출할 수 있다.
   ```



8. 각 문항에 따라 함수 g()의 빈칸에 적절한 코드는?

   ```cpp
   void f() {
   	cout << "f() called" << endl;
   }
   
   class A {
   public:
   	virtual void f() {
   		cout << "A::f() called" << endl;
   	}
   };
   
   class B :public A {
   public:
   	void g() {
   		[적절한 코드 삽입]
   	}
   	void f() {
   		cout << "B::f() called" << endl;
   	}
   };
   ```

   1. 함수 g()가 외부 함수 f()를 호출한다.

      ```cpp
      ::f();
      ```

   2. 함수 g()가 클래스 A의 멤버 함수 f()를 호출한다.

      ```cpp
      A::f();
      ```

   3. 함수 g()가 자신의 멤버 함수 f()를 호출한다.

      ```cpp
      f();
      ```


9. 생성자와 소멸자를 가상 함수로 선언하는 것에 대한 설명 중 맞는 것은?
   1. 소멸자는 동적 바인딩 되지 않는다.
   2. **소멸자를 가상 함수로 선언하는 것이 바람직하다. (O)**
   3. 소멸자를 가상 함수로 선언해도 동적 바인딩이 일어나지 않는다.
   4. 생성자를 가상 함수로 선언하는 것이 바람직하다.



10. 다음은 Person 클래스와 파생 클래스 Student를 작성한 사례이다.

    ```cpp
    class Person {
    	int id;
    public:
    	Person(int id = 0) {
    		this->id = id;
    	}
    	~Person(){
    		cout << "id=" << id << endl;
    	}
    };
    
    class Student :public Person {
    	char* name;
    public:
    	Student(int id, char* name) :Person(id) {
    		int len = strlen(name);
    		this->name = new char[len + 1];
    		strcpy(this->name, name);
    	}
    	~Student() {
    		cout << "name= " << name << endl;
    		delete[] name;
    	}
    };
    
    int main() {
    	Person *p = new Student(10, "손연재");
    	delete p;
    }
    ```

    1. 다음 코드의 실행 결과는 무엇인가? 실행 결과에 대해 어떤 문제가 있다고 생각되는가?

       ```
       id=10
       ```

       > Person 포인터 객체인 p만 소멸되고 Student 객체는 소멸되지 않아 문제가 발생한다.

    2. Person 클래스나 Student 클래스를 수정하여 문제점을 해결하라.

       ```cpp
       class Person {
       	int id;
       public:
       	Person(int id = 0) {
       		this->id = id;
       	}
       	virtual ~Person(){	
       		cout << "id=" << id << endl;
       	}
       };
       ```

       > Person 소멸자에 virtual(가상 함수)를 붙이면 된다. (=가상 소멸자)



11. 다음 중 순수 가상 함수는?

    ```cpp
    class Shape{
    public:
        void draw()=0;
        virtual void draw();
        virtual void draw()=0;		// 순수 가상 함수이다.
        virtual void draw(){ }
    }
    ```



12. 순수 가상 함수에 대해 잘못 말한 것은?
    1. 순수 가상 함수를 가진 클래스는 무조건 추상 클래스이다.
    2. 순수 가상 함수는 실행이 목적이 아니라, 파생 클래스가 구현해야 할 함수를 알려주기 위한 것이다.
    3. **외부 함수도 순수 가상 함수로 선언 가능하다. (X)**
    4. 순수 가상 함수가 호출되면 동적 바인딩이 일어난다.



13. 다음에서 추상 클래스는?

    1. ```cpp
       class Shape{
           void draw()=0;
       };
       ```

    2. ```cpp
       class Shape{
           virtual void draw()=0;
       };
       ```

    3. ```cpp
       class Shape{
           virtual void draw() {}=0;
       };
       ```

    4. ```cpp
       abstract Shape{
           virtual void draw()=0;
       };
       ```

    > **2번**



14. 다음 코드에 대해 물음에 답하여라.

    ```cpp
    class Shape {
    public:
    	void paint() {
    		draw();
    	}
    	virtual void draw() = 0;
    };
    
    class Circle :public Shape {
    	int radius;
    public:
    	Circle(int radius = 1) {
    		this->radius = radius;
    	}
    	double getArea() {
    		return 3.14*radius*radius;
    	}
    };
    ```

    1. 다음 중 오류가 발생하는 것을 있는 대로 골라라.

       1. **Shape shape; (X, 컴파일 오류, 추상 클래스이기 때문에)**
       2. Shape* p;
       3. **Circle circle(10); (X, 컴파일 오류, Shape의 순수 가상 함수가 재정의 되어있지 않기 때문)**
       4. Circle *pCircle;

    2. 다음 코드의 실행 결과 "반지름=10인 원"이 출력되도록 Shape이나 Circle 클래스를 수정하라.

       ```cpp
       Circle *p = new Circle(10);
       p->paint();
       ```

       **실행 결과**

       ```
       반지름=10인 원
       ```

       **정답**

       ```cpp
       class Circle :public Shape {
       	int radius;
       public:
       	Circle(int radius = 1) {
       		this->radius = radius;
       	}
       	double getArea() {
       		return 3.14*radius*radius;
       	}
       	void draw() {	// 순수 가상 함수를 재정의 해주면 된다.
       		cout << "반지름=" << radius << "인 원" << endl;
       	}
       };
       ```


15. 다음 중 컴파일 시에 바인딩되지 않는 것은?
    1. 중복된 함수 중에서 구분하여 호출
    2. 중복된 연산자 중에서 구분하여 호출
    3. 범위 지정 연산자(::)를 이용한 함수 호출
    4. **순수 가상 함수 호출 (O)**

