# Chapter 7. friend and operator overloading



## 7.1  C++ 프렌드 개념

* **프렌드 함수** 

  * 클래스 외부에 작성된 함수를 클래스 내에 **friend 키워드로 선언**하여, 클래스의 멤버 함수와 동일한 접근 자격을 부여할 수 있다.
  * **클래스의 멤버인것처럼** 클래스의 모든 변수나 함수에 접근할 수 있다.
  * **오직 함수만이** 프렌드가 된다.

* **프렌드 함수가 되는 3 가지**

  * **전역 함수** : 클래스 외부에 선언된 함수
  * 다른 클래스의 **멤버 함수** : 다른 클래스의 특정 멤버 함수
  * 다른 **클래스 전체** : 다른 클래스의 모든 멤버 함수

* **프렌드 함수 만들기 예제**

  ```java
  #include <iostream>
  using namespace std;
  
  class Rect;		// Rect 클래스가 선언되기 전에 먼저 참조되는 오류를 막기위함
  bool equals(Rect r, Rect s);	// 함수 선언
  
  class Rect {
  	int width, height;
  public:
  	Rect(int width, int height) {
  		this->width = width;
  		this->height = height;
  	}
  
  	// equals() 함수를 프렌드로 선언
  	friend bool equals(Rect r, Rect s);
  };
  
  bool equals(Rect r, Rect s) {
  	if (r.height == s.height && r.width == s.width) {
  		return true;
  	}
  	else {
  		return false;
  	}
  }
  
  int main() {
  	Rect a(3, 4), b(4, 5);
  
  	if (equals(a, b)) {
  		cout << "equal" << endl;
  	}
  	else {
  		cout << "not equal" << endl;
  	}
  }
  ```

  **실행결과**

  ```
  not equal
  ```



* **다른 클래스의 멤버 함수를 프렌드로 선언 예제**

  ```java
  #include <iostream>
  using namespace std;
  
  class Rect;
  
  // 새로운 RectManager 클래스 선언
  class RectManager {
  public:
  	bool equals(Rect r, Rect s);
  };
  
  class Rect {
  	int width, height;
  public:
  	Rect(int width, int height) {
  		this->width = width;
  		this->height = height;
  	}
  
  	// RectManager 클래스의 equals() 멤버를 프렌드로 선언
  	friend bool RectManager::equals(Rect r, Rect s);
  };
  
  bool RectManager::equals(Rect r, Rect s) {
  	if (r.height == s.height && r.width == s.width) {
  		return true;
  	}
  	else {
  		return false;
  	}
  }
  
  int main() {
  	Rect a(3, 4), b(3, 4);
  	RectManager man;
  
  	if (man.equals(a, b)) {
  		cout << "equal" << endl;
  	}
  	else {
  		cout << "not equal" << endl;
  	}
  }
  ```

  **실행결과**

  ```java
  equal
  ```



* **다른 클래스 전체를 프렌드로 선언**

  ```java
  #include <iostream>
  using namespace std;
  
  class Rect;
  
  // 새로운 RectManager 클래스 선언
  class RectManager {
  public:
  	bool equals(Rect r, Rect s);
  	void copy(Rect& dest, Rect& src);
  };
  
  class Rect {
  	int width, height;
  public:
  	Rect(int width, int height) {
  		this->width = width;
  		this->height = height;
  	}
  
  	// RectManager 클래스의 equals() 멤버를 프렌드로 선언
  	friend RectManager;
  };
  
  bool RectManager::equals(Rect r, Rect s) {
  	if (r.height == s.height && r.width == s.width) {
  		return true;
  	}
  	else {
  		return false;
  	}
  }
  
  void RectManager :: copy(Rect& dest, Rect& src) {
  	dest.width = src.width;
  	dest.height = src.height;
  }
  
  int main() {
  	Rect a(3, 4), b(5, 6);
  	RectManager man;
  
  	man.copy(b, a);		// a를 b에 복사
  
  	if (man.equals(a, b)) {
  		cout << "equal" << endl;
  	}
  	else {
  		cout << "not equal" << endl;
  	}
  }
  ```

  **실행결과**

  ```
  equal
  ```


  ## CHECK TIME

1. 프렌드 함수에 대해서 틀린 것은?

   1. 프렌드 함수로 초대하기 위해 클래스 내에 friend 키워드로 선언한다.
   2. **프렌드 함수는 클래스의 멤버 함수이다. ( X , 클래스 멤버 함수처럼 이용되는 것이다. )**
   3. 프렌드 함수는 연산자 중복에서 이용된다.
   4. 프렌드 함수의 개수는 제한이 없다.

2. 다음 소스에 요구된 선언문을 작성하라.

   ```java
   #include <iostream>
   using namespace std;
   
   class Sample;
   int f(Sample);
   class Test {
   public:
   	void f(Sample);
   	void g(Sample);
   };
   
   class Sample {
   private:
   	int x;
   public:
   	Sample(int x);					
   	friend int f(Sample);			// 함수 f()를 프렌드로 선언하라.
   	friend void Test::f(Sample);	// Test 클래스의 함수 f()를 프렌드로 선언
   	friend Test;					// Test 클래스의 모든 함수 프렌드로 선언
   }
   ```


## 7.2  연산자 중복

* **연산자 중복(operator overloading)** : 피연산자에 따라 서로 다른 연산을 하도록 동일한 연산자를 중복해서 작성



* **두 색을 섞은 새로운 색 만들기**

  : + 연산자로 두 객체를 혼합하여 새로운 색을 만든다.

  ```java
  Color a(BLUE), b(RED), c;
  c = a + b;		// c = VIOLET. a, b 객체를 합친 객체 c
  ```



* **두 개의 배열 더하기**

  ```java
  // a의 원소는 2,5,9 , b의 원소는 3, 7, 10
  SortedArray a(2, 5, 9), b(3, 7, 10), c;
  c = a + b // c = {2, 3, 5, 7, 9, 10} 정렬된 두 배열을 결합한 새로운 배열 생성
  ```



### 연산자 중복의 특징

1. **C++ 언어에 본래 있는 연산자만 중복 가능**

   : +, - , * , / , == , != , % , && 등 본래있는 연산자만 가능.  %%, ## 은 컴파일 오류

2. **연산자 중복은 피연산자의 타입이 다른 연산을 새로 정의하는 것이다.**

   : 객체 + 수 , 수 + 객체 , 객체 + 객체와 같이 정의해야 한다.

3. **연산자는 함수 형태로 구현**

   : 연산자 함수(operator function)

4. **반드시 클래스와 관계를 가진다.**

   : 반드시 **피 연산자**에 객체를 동반한다. 클래스 멤버 함수로 구현하거나 전역 함수로 구현하여 프랜드 함수로 선언하여 사용한다.

5. **피 연산자의 개수를 바꿀 수 없음**

   : 이항연산자인 +의 피연산자 개수를 바꿀 수 없음.

6. **연산의 우선 순위 변경이 안된다.**

   : 기존 연산자의 우선순위가 변경되지 않음.

7. **모든 연산자가 중복 가능한 것은 아니다.**

   | 중복 불가능한 연산자 |      |      |                  |
   | -------------------- | ---- | ---- | ---------------- |
   | .                    | .*   | ::   | ? : (3항 연산자) |



### 연산자 함수 선언과 연산자 함수 개요

* **연산자 함수 작성 방법**

  1. 클래스의 **멤버 함수**로 구현
  2. **외부 함수**로 구현하고 클래스의 **프렌드 함수로 선언**

* **선언 방법**

  ```cpp
  리턴타입 operator 연산자(매개변수리스트);
  ```



* **외부 함수로 구현하고 클래스에 프렌드 함수로 선언하는 경우**

  ```cpp
  Color operator + (Color op1, Color op2);	// 외부 전역 함수
  bool operator == (Color op1, Color op2);	// 외부 전역 함수
  
  class Color{
      ...
      friend Color operator + (Color op1, Color op2);	// 프렌드 선언
      friend bool operator == (Color op1, Color op2);	// 프렌드 선언
  };
  ```



* **클래스의 멤버 함수로 선언되는 경우**

  ```cpp
  class Color{
      ...
      // 왼쪽 피연산자는 객체 자신이고 오른쪽 피연산자가 op2에 전달
      Color operator + (Color op2);
      
      // 왼쪽 피연산자는 객체 자신이고 오른쪽 피연산자가 op2에 전달
      bool operator == (Color op2);
  };
  ```

  > \+ 나 == 의 **오른쪽 피연산자**만 매개 변수 op2에 전달되고, **왼쪽 피연산자**는 객체 자신이므로 매개 변수에 전달되지 않는다.

  ```cpp
  Color a(BLUE), b(RED), c;
  
  // Color operator + (Color op2)나
  // Color operator + (Color op1, Color op2) 중 구현된 것을 호출한다.
  c = a + b;
  
  // bool operator == (Color op2) 나
  // bool operator == (Color op1, Color op2) 중 구현된 것을 호출한다.
  if(a == b){
      ...
  }
  ```





## CHECK TIME

1. 다음 중 연산자 중복이 불가능한 것은?

   1. **new ( X , 중복 불가 )**
   2. ++
   3. **
   4. [ ]

2. Money 클래스의 두 객체를 더하여 합한 결과를 Money 객체를 리턴하고자 한다. + 연산자를 Money 클래스의 멤버 함수로 선언할 때, + 연산자 함수의 원형은 무엇인가?

   ```cpp
   class Money {
   	Money operator + (Money a);
   };
   ```



   만일 외부함수로 구현한다면 + 연산자 함수의 원형은 무엇인가?

   ```cpp
   Money operator + (Money a, Money b);
   ```

3. 연산자 중복과 가장 거리가 먼 것은?

   1. 연산자 함수
   2. 클래스
   3. 프렌드
   4. **동적 바인딩 ( X )**



## 7.3  이항 연산자 중복

* **예제**

  ```cpp
  class Power{		// 에너지를 표현하는 파워 클래스
      int kick;		// 발로 차는 힘
      int punch;		// 주먹으로 치는힘
  public:
      Power(int kick=0, int punch=0){
          this->kick = kick;
          this->punch = punch;
      }
  };
  ```

### + 연산자 중복

: Power 객체를 더하는 + 연산자를 선언하고 구현하는 과정

* **연산자 착안**

  :  + 의 의미를 결정해야 한다. 여기서는 + 연산을 두 Power 객체의 kick과 punch를 각각 더하는 것으로 정의한다.

  ```cpp
  Power a(3,5), b(4,6), c;
  
  c = a + b;	// 두 개의 파워 개체를 더하는 + 연산
  ```



* **연산자 함수 선언**

  : Power 클래스에 Power 객체를 더하는 + 연산자 함수가 새로 선언한다.

  ```cpp
  class Power{
      Power operator+ (Power op2);	// + 연산자 함수 선언
  };
  ```

  > **operator+ ( )** 를 호출하여 연산자 함수 선언.



* **연산자 함수 구현**

  ```cpp
  Power Power::operator+(Power op2){
      Power tmp;
      tmp.kick = this->kick + op2.kick;
      tmp.punch = this->punch + op2.punch;
      return tmp;
  }
  ```



* **두 개의 Power 객체를 더하는 + 연산자 작성**

  ```cpp
  #include <iostream>
  using namespace std;
  
  class Power {
  	int kick;
  	int punch;
  public:
  	Power(int kick = 0, int punch = 0) {
  		this->kick = kick;
  		this->punch = punch;
  	}
  	void show();
  	Power operator+ (Power op2);	// + 연산자 함수 선언
  };
  
  void Power::show() {
  	cout << "kick= " << kick << ',' << " punch= " << punch << endl;
  }
  
  Power Power::operator+(Power op2) {
  	Power tmp;	// 임시 객체 생성
  	tmp.kick = this->kick + op2.kick;	// kick 더하기
  	tmp.punch = this->punch + op2.punch;// punch 더하기
  	return tmp;	// 더한 결과 리턴
  }
  
  int main() {
  	Power a(3, 5), b(4, 6), c;
  	c = a + b;	// 파워 객체 + 연산
  	a.show();
  	b.show();
  	c.show();
  }
  ```

  **실행결과**

  ```
  kick= 3, punch= 5
  kick= 4, punch= 6
  kick= 7, punch= 11
  ```



### == 연산자 중복

* **연산자 착안**

  : 먼저 == 연산자는 두 개의 Power 클래스를 비교하는 것으로 다음과 같이 사용된다.

  ```cpp
  Power a(3,5), b(3,5);
  if(a == b) cout << "두 파워가 같다.";
  else cout << "두 파워가 같지 않다.";
  ```

* **연산자 함수 선언**

  ```cpp
  class Power{
      ...
  public:
      bool operator== (Power op2);
  };
  ```

* **연산자 함수 코드**

  ```cpp
  bool Power::operator==(Power op2){
      if(kick==op2.kick && punch == op2.punch) return true;
      else return false;
  }
  ```

* **두 개의 Power 객체를 비교하는 == 연산자 작성**

  ```cpp
  #include <iostream>
  using namespace std;
  
  class Power {
  	int kick;
  	int punch;
  public:
  	Power(int kick = 0, int punch = 0) {
  		this->kick = kick;
  		this->punch = punch;
  	}
  	void show();
  	bool operator== (Power op2);	// == 연산자 함수 선언
  };
  
  void Power::show() {
  	cout << "kick= " << kick << ',' << "punch= " << punch << endl;
  }
  
  bool Power :: operator==(Power op2) {
  	if (kick == op2.kick && punch == op2.punch) {
  		return true;
  	}
  	else {
  		return false;
  	}
  }
  
  int main() {
  	Power a(3, 5), b(3, 5);	// 2 개의 동일한 파워 객체 생성
  	a.show();
  	b.show();
  	if (a == b) cout << "두 파워가 같다." << endl;
  	else cout << "두 파워가 다르다." << endl;
  }
  ```

  **실행결과**

  ```
  kick= 3,punch= 5
  kick= 3,punch= 5
  두 파워가 같다.
  ```



### += 연산자 중복

* **연산자 착안**

  ```cpp
  Power a(3,5), b(4,6);
  a += b;
  ```

* **연산자 선언**

  : 아무 값도 리턴할 필요가 없기 때문에 void로 선언. 

  ```cpp
  c = a += b;
  ```

* **연산자 함수 코드**

  ```cpp
  Power Power::operator+= (Power op2) {
      kick = kick + op2.kick;		// this 객체의 kick에 op2의 kick을 더함
      punch = punch + op2.punch;	// this 객체의 punch에 op2의 punch을 더함
      return *this;	// 갱신된 Power 객체 a 리턴
  }
  ```

  > 이 함수에서 주목할 것은 this를 이용하여 += 연산의 결과를 리턴한다는 점이다.
  >
  > **return *this;	// 변경된 객체 a를 리턴한다.**


* **두 개의 Power 객체를 더하는 += 연산자 작성 예제**

  ```cpp
  #include <iostream>
  using namespace std;
  
  class Power {
  	int kick;
  	int punch;
  public:
  	Power(int kick = 0, int punch = 0) {
  		this->kick = kick;
  		this->punch = punch;
  	}
  	void show();
  	Power operator+= (Power op2);	// += 연산자 함수 선언
  };
  
  void Power::show() {
  	cout << "kick= " << kick << " , " << "punch= " << punch << endl;
  }
  
  Power Power::operator+=(Power op2) {
  	kick = kick + op2.kick;		// kick 더하기
  	punch = punch + op2.punch;	// punch 더하기
  	return *this;		// 합한 결과
  }
  
  int main() {
  	Power a(3, 5), b(4, 6), c;
  	a.show();
  	b.show();
  	
  	// operator+=() 멤버 함수 호출
  	c = a += b;		// 파워 객체 더하기
  
  	a.show();
  	b.show();
  	c.show();
  }
  ```

  **실행 결과**

  ```
  kick= 3 , punch= 5
  kick= 4 , punch= 6
  kick= 7 , punch= 11
  kick= 4 , punch= 6
  kick= 7 , punch= 11
  ```



### + 연산자 작성 실습 : b = a + 2;

: 객체에 대한 + 연산자를 구현해보라.

```cpp
Power a(3,5), b;
b = a + 2;
```



* **연산자 착안**

  : a + 2의 의미를 a의 kick과 punch 값에 각각 2를 더한 결과를 리턴



* **연산자 함수 선언**

  ```cpp
  class Power{
  public:
      // operator(int) 함수는 더한 결과로 Power 객체를 리턴한다.
      Power operator+ (int op2);	
  };
  ```



* **b = a + 2; 의 + 연산자 작성**

  ```cpp
  #include <iostream>
  using namespace std;
  
  class Power {
  	int kick;
  	int punch;
  public:
  	Power(int kick = 0, int punch = 0) {
  		this->kick = kick;
  		this->punch = punch;
  	}
  	void show();
  	Power operator+ (int op2);	// + 연산자 함수 선언
  };
  
  void Power::show() {
  	cout << "kick= " << kick << " , " << "punch= " << punch << endl;
  }
  
  Power Power::operator+(int op2) {
  	Power tmp;	// 임시 객체 생성
  	tmp.kick = kick + op2;		// kick에 op2 더하기
  	tmp.punch = punch + op2;	// punch에 op2 더하기
  	return tmp;	// 임시 객체 리턴
  }
  
  int main() {
  	Power a(3, 5), b;
  	a.show();
  	b.show();
      
      // operator+(int) 함수 호출
  	b = a + 2;	//  파워 객체와 정수 더하기
  	
      a.show();
  	b.show();
  }
  ```

  **실행결과**

  ```
  
  ```



## CHECK TIME

1. Power 객체 a, b에 대해 다음 연산을 위한 연산자 함수를 Power 클래스의 멤버 함수로 선언하라.

   1. a > 0

      ```cpp
      bool operator> (int op); 
      
      bool Power::operator>(int op) {
      	if (kick > op && punch > op) return true;
      	else return false;
      }
      ```

   2. a & b

      ```cpp
      Power operator& (Power b);
      
      Power Power::operator&(Power b)
      {
      	Power tmp;
      	tmp.kick = kick & b.kick;
      	tmp.punch = punch & b.punch;
      	return tmp;
      }
      ```



## 7.4  단한 연산자 중복

* **피연산자가 하나 뿐인 연산자**
* **단항 연산자 종류**
  * **전위 연산자**     ex )   !op, ~op, ++op , --op
  * **후위 연산자**     ex )   op++ ,  op-- 



### 전위 ++ 연산자 중복

* **연산자 착안**

  ```cpp
  Power a(3,5), b;
  b = ++a;
  ```

* **연산자 함수 선언**

  ```cpp
  class Power{
   public:
      Power operator++ ();
  };
  ```

* **연산자 함수 구현**

  ```cpp
  Power Power::operator++(){
      kick++;
      punch++;
      return *this;	// 변경된 객체 자신 리턴
  }
  ```

* **전위 ++ 연산자 작성**

  ```cpp
  #include <iostream>
  using namespace std;
  
  class Power {
  	int kick;
  	int punch;
  public:
  	Power(int kick = 0, int punch = 0) {
  		this->kick = kick;
  		this->punch = punch;
  	}
  	void show();
  	Power operator++();	// 전위 ++ 연산자 함수 선언
  };
  
  void Power::show() {
  	cout << "kick= " << kick << ", " << "punch= " << punch << endl;
  }
  
  Power Power::operator++() {
  	kick++;
  	punch++;
  	return *this;
  }
  
  int main() {
  	Power a(3, 5), b;
  	a.show();
  	b.show();
  
  	// operator++() 함수 호출
  	b = ++a;
  
  	a.show();
  	b.show();
  }
  ```

  **실행결과**

  ```
  kick= 3, punch= 5
  kick= 0, punch= 0
  kick= 4, punch= 6
  kick= 4, punch= 6
  ```


* **Power 클래스에 ! 연산자 작성**

  ```cpp
  #include <iostream>
  using namespace std;
  
  class Power {
  	int kick;
  	int punch;
  public:
  	Power(int kick = 0, int punch = 0) {
  		this->kick = kick;
  		this->punch = punch;
  	}
  	void show();
  	bool operator!();	// ! 연산자 함수 선언
  };
  
  void Power::show() {
  	cout << "kick= " << kick << ',' << "punch= " << punch << endl;
  }
  
  bool Power::operator!() {
      // kick과 punch가 0일때
  	if (kick == 0 && punch == 0) return true;
  	else return false;
  }
  
  int main() {
  	Power a, b(5, 5);
  	if (!a) cout << "a의 파워가 0이다." << endl;	// ! 연산자 호출
  	else cout << "a의 파워가 0이 아니다." << endl; // ! 연산자 호출
  	if (!b) cout << "b의 파워가 0이다." << endl; // ! 연산자 호출
  	else cout << "b의 파워가 0이 아니다." << endl;	// ! 연산자 호출
  }
  ```

  **실행 결과**

  ```
  a의 파워가 0이다.
  b의 파워가 0이 아니다.
  ```



### 후위 ++ 연산자 중복

```cpp
Power operator++();			// 전위 ++ 연산자 함수
Power operator++(int x);	// 후위 ++ 연산자 함수
```



* **연산자 착안**

  ```cpp
  Power a(3,5), b;
  b = a++;
  ```

* **연산자 함수 선언**

  ```cpp
  class Power{
  public:
      Power operator++ (int x);
  };
  ```

* **연산자 함수 구현**

  ```cpp
  Power Power::operator++(int x){
      Power tmp = *this;		// 증가 이전 객체 상태 저장
      kick++;
      punch++;
      return tmp;				// 증가 이전의 객체 리턴 
  }
  ```

* **후위 ++ 연산자 작성 예제**

  ```cpp
  #include <iostream>
  using namespace std;
  
  class Power {
  	int kick;
  	int punch;
  public:
  	Power(int kick = 0, int punch = 0) {
  		this->kick = kick;
  		this->punch = punch;
  	}
  	void show();
  	Power operator++ (int x);	// 후위 ++ 연산자 함수 선언
  };
  
  void Power::show() {
  	cout << "kick= " << kick << ',' << "punch= " << punch << endl;
  }
  
  Power Power::operator++(int x) {
  	Power tmp = *this;		// 증가 이전 객체 상태 저장
  	kick++;
  	punch++;
  	return tmp;				// 증가 이전 객체 상태 리턴
  }
  
  int main() {
  	Power a(3, 5), b;
  	a.show();
  	b.show();
  	b = a++;	// 후위 ++ 연산자 사용
  	a.show();	// a의 파워는 1 증가됨
  	b.show();	// b는 a가 증가되기 이전 상태를 가짐
  }
  ```

  **실행결과**

  ```
  kick= 3,punch= 5
  kick= 0,punch= 0
  kick= 4,punch= 6
  kick= 3,punch= 5
  ```


## 7.5  프렌드를 이용한 연산자 중복

: 연산자 함수는 클래스 바깥의 **외부 전역 함수로도 작성** 가능하다. 이런 경우, 연산자 함수를 클래스에서 **friend**로 취하여 클래스의 멤버를 자유롭게 접근할 수 있게 한다.

### 2 + a 를 위한 + 연산자 함수 작성

* **+ 연산자를 외부 함수로 작성**

  ```cpp
  Power operator+ (int op1, Power op2){
      Power tmp;
      tmp.kick = op1 + op2.kick;
      tmp.punch = op1 + op2.punch;
      return tmp;
  }
  
  Power a(3,4), b;
  b = 2 + a;
  ```

* **외부 연산자 함수의 프렌드 선언**

  ```cpp
  class Power{
      int kick;
      int punch;
  public:
      friend Power operator+ (int op1, Power op2);	// 프렌드 선언
  }
  
  Power operator+(int op1, Power op2){	// 외부 함수로 구현된 연산자 함수
      ...
  }
  ```

* **2+a를 위한 + 연산자 함수를 프렌드로 작성**

  ```cpp
  #include <iostream>
  using namespace std;
  
  class Power {
  	int kick;
  	int punch;
  public:
  	Power(int kick = 0, int punch = 0) {
  		this->kick = kick;
  		this->punch = punch;
  	}
  	void show();
  	friend Power operator+ (int op1, Power op2);	// 프렌드 선언
  };
  
  void Power::show() {
  	cout << "kick= " << kick << ',' << "punch= " << punch << endl;
  }
  
  Power operator+ (int op1, Power op2) {
  	Power tmp;						// 임시 객체 생성
  	tmp.kick = op1 + op2.kick;		// kick 더하기
  	tmp.punch = op1 + op2.punch;	// punch 더하기
  	return tmp;						// 임시 객체 리턴
  }
  
  int main() {
  	Power a(3, 5), b;
  	a.show();
  	b.show();
  	b = 2 + a;	// 파워 객체 더하기 연산
  	a.show();
  	b.show();
  }
  ```

  **실행결과**

  ```
  kick= 3,punch= 5
  kick= 0,punch= 0
  kick= 3,punch= 5
  kick= 5,punch= 7
  ```



### + 연산자를 외부 프렌드 함수로 작성

```cpp
Power operator+ (Power op1, Power op2){
    Power tmp;
    tmp.kick = op1.kick + op2.kick;
    tmp.punch = op1.punch + op2.punch;
    return tmp;
}

Power a(3,4), b(4,5), c;
c = a + b;
```

* **a+b를 위한 + 연산자 함수를 프렌드로 작성**

  ```cpp
  #include <iostream>
  using namespace std;
  
  class Power {
  	int kick;
  	int punch;
  public:
  	Power(int kick = 0, int punch = 0) {
  		this->kick = kick;
  		this->punch = punch;
  	}
  	void show();
  	friend Power operator+ (Power op1, Power op2);	// 프렌드 선언
  };
  
  void Power::show() {
  	cout << "kick= " << kick << ',' << "punch= " << punch << endl;
  }
  
  Power operator+ (Power op1, Power op2) {
  	Power tmp;						// 임시 객체 생성
  	tmp.kick = op1.kick + op2.kick;		// kick 더하기
  	tmp.punch = op1.punch + op2.punch;	// punch 더하기
  	return tmp;						// 임시 객체 리턴
  }
  
  int main() {
  	Power a(3, 5), b(4,6), c;
  	c = a + b;	// 파워 객체 더하기 연산
  	a.show();
  	b.show();
  	c.show();
  }
  ```

  **실행결과**

  ```
  kick= 3,punch= 5
  kick= 4,punch= 6
  kick= 7,punch= 11
  ```



### 단항 연산자 ++를 프렌드로 작성하기

```cpp
Power operator++ (Power& op){
    op.kick++;
    op.punch++;
    return op;
}

Power operator++ (Power& op, int x){
    Power tmp = op;
    op.kick++;
    op.punch++;
    return tmp;
}

Power a(3,5), b;
b = ++a;	// 전위 ++ 연산
b = a++;	// 후위 ++ 연산
```

* **참조 매개 변수 사용**

  ```cpp
  Power operator++ (Power& op);			// 전위 ++ 연산자 함수
  Power operator++ (Power& op, int x);	// 후위 ++ 연산자 함수
  ```

  > **Power& op의 참조 매개 변수**를 사용함으로써 op는 객체 a를 참조하게 되어 op 객체를 변경하면 바로 객체 a가 변경된다.



* **++ 연산자 함수를 프렌드로 작성**

  ```cpp
  #include <iostream>
  using namespace std;
  
  class Power {
  	int kick;
  	int punch;
  public:
  	Power(int kick = 0, int punch = 0) {
  		this->kick = kick;
  		this->punch = punch;
  	}
  	void show();
  	// 전위 ++ 연산자 함수 프렌드 선언
  	friend Power operator++ (Power& op);
  	// 후위 ++ 연산자 함수 프렌드 선언
  	friend Power operator++ (Power& op, int x);
  };
  
  void Power::show() {
  	cout << "kick= " << kick << ',' << "punch= " << punch << endl;
  }
  
  // 전위 ++ 연산자 함수 구현
  Power operator++ (Power& op)
  {
  	op.kick++;
  	op.punch++;
  	return op;		// 연산 결과 리턴
  }
  
  // 후위 ++ 연산자 함수 프렌드 구현
  Power operator++ (Power& op, int x)
  {
  	Power tmp = op;
  	op.kick++;
  	op.punch++;
  	return tmp;		// 변경 이전의 op 리턴
  }
  
  int main() {
  	Power a(3, 5), b;
  	b = ++a;	// 전위 ++ 연산자
  	a.show();
  	b.show();
  
  	b = a++;	// 후위 ++ 연산자
  	a.show();
  	b.show();
  }
  ```

  **실행결과**

  ```
  kick= 4,punch= 6
  kick= 4,punch= 6
  kick= 5,punch= 7
  kick= 4,punch= 6
  ```



## CHECK TIME

> **2 + a 는 + 연산자를 외부 함수로 밖에 만들 수 없다.**



1. Circle 클래스의 객체 donut, pizza가 있을 때, Circle 클래스의 멤버 함수로 연산자 함수를 작성할 수 없는 경우는?

   1. **3 < donut ( X, 할수없다. )**
   2. !donut
   3. pizza + 3.14
   4. pizza != donut

2. 연산자 함수를 작성하는데 있어 friend는 왜 필요한가?

   **외부 전역 연산자 함수를 클래스에서 friend로 취하여 클래스의 멤버를 자유롭게 접근할 수 있게 하기 위해**





# 연습문제

1. 프렌드에 대한 설명 중 틀린 것은?

   1. **한 클래스의 전체 멤버 함수를 프렌드로 선언할 수 없다. ( X , 한 클래스 전체를 프렌드로 선언할 수 있다. )**
   2. 프렌드 함수는 클래스의 멤버 함수가 아니다.
   3. 프렌드 함수는 클래스의 private 멤버에 대한 접근 권한을 가진다.
   4. 프렌드 함수는 friend 키워드로 클래스 내에 선언된다.

2. 프렌드 함수가 필요한 경우가 아닌 것은?

   1. 멤버는 아니지만 클래스의 private 멤버에 접근해야만 하는 함수를 작성하는 경우
   2. 두 개 이상의 클래스에 대해 private 멤버를 동시에 접근하는 함수를 작성하는 경우
   3. 연산자 중복 시에
   4. **함수 중복 시에 ( X , 오버로딩을 사용하면 된다.)**

3. 다음 클래스 Sample에서 클래스 SampleManager의 모든 멤버 함수를 프렌드로 초대하도록 선언하라.

   ```cpp
   class Sample{
     friend SampleManager  // 빈칸
   };
   ```



4. 클래스 SampleManager에는 다음의 멤버 함수가 있다.

   ```cpp
   bool compare(Sample &a, Sample &b);
   ```

   이 멤버 함수를 다음 클래스 Sample에 프렌드로 초대하도록 선언하라.

   ```cpp
   class Sample{
       friend bool SampleManager::compare(Sample &a, Sample &b);	// 빈칸
   };
   ```



5. 다음 프로그램은 컴파일 오류가 발생한다. 오류의 원인은 무엇인가? 오류를 바람직하게 수정하라.

   ```cpp
   class Student{
       int id;
   public:
       Student(int id){ this->id = id; }
   };
   
   bool isValid(Student s){
       if(s.id > 0) return true;
       else return false;
   }
   ```

   **정답**

   ```cpp
   #include <iostream>
   using namespace std;
   
   class Student {
   	int id;
   public:
   	Student(int id) { this->id = id; }
       
       // 프렌드 함수 선언
   	friend bool isValid(Student s);	
   };
   
   bool isValid(Student s) {
   	if (s.id > 0) return true;
   	else return false;
   }
   ```

   > id가 private이기 때문에 프렌드 함수를 선언하여 Student의 멤버 함수에 접근할 수 있도록 해줘야 한다.



6. 다음 프로그램은 컴파일 오류가 발생한다. 오류의 원인은 무엇인가? 오류를 바람직하게 수정하라.

   ```cpp
   class Student {
   	int id;
   public:
   	Student(int id) { this->id = id; }
   };
   
   class Professor {
   	string name;
   public:
   	Professor(string name) { this->name = name; }
   };
   
   void show(Student s, Professor p) {
   	cout << s.id << p.name;
   }
   ```

   **정답**

   ```cpp
   #include <iostream>
   using namespace std;
   
   // 전방 참조 문제를 해결하기 위함
   class Student;
   class Professor;
   
   class Student {
   	int id;
   public:
   	Student(int id) { this->id = id; }
       // 프렌드 함수 선언
   	friend void show(Student s, Professor p);
   };
   
   class Professor {
   	string name;
   public:
   	Professor(string name) { this->name = name; }
       // 프렌드 함수 선언
   	friend void show(Student s, Professor p);
   };
   
   void show(Student s, Professor p) {
   	cout << s.id << p.name;
   }
   ```

   > 외부 전역 함수가 Student 와 Professor의 private 멤버에 접근하기 위해서 두 개의 클래스 블록 안에 모두 프렌드 함수를 선언해야한다.



7. 다음 프로그램은 컴파일 오류를 가지고 있다. 오류를 지적하고 바람직하게 수정하라.

   ```cpp
   class Food {
   	int price;
   	string name;
   public:
   	Food(string name, int price);
   	void buy();
   };
   
   class Person {
   	int id;
   public:
   	void shopping(Food food) {
   		if (food.price < 1000)
   			food.buy();
   	}
   	int get() { return id; }
   };
   ```

   **정답**

   ```cpp
   class Food {
   	int price;
   	string name;
   public:
   	Food(string name, int price);
   	void buy();
       // < 연산자 중복 선언 및 구현
   	bool operator<(int x) {
   		if (price > x) return true;
   		else return false;
   	}
   };
   
   class Person {
   	int id;
   public:
   	void shopping(Food food) {
           // food.price에서 food로 변경
   		if (food < 1000)
   			food.buy();
   	}
   	int get() { return id; }
   };
   ```



8. 프렌드 선언의 위치에 대한 설명 중 옳은 것은?
   1. 클래스 내의 private 영역에 선언되어야 한다.
   2. 클래스 내의 protected 영억에 선언되어야 한다.
   3. 클래스 내의 public 영역에 선언되어야 한다.
   4. **클래스 내의 아무 영역에 선언되어도 상관없다. ( O )**



9. 다음의 friend 선언과 main( ) 함수의 isZero( ) 의 호출이 옳은지 설명하라.

   ```cpp
   #include <iostream>
   using namespace std;
   
   class Sample {
   public:
   	int x;
   	Sample(int x) { this->x = x; }
   	friend bool isZero(Sample &a) {		// 이곳에 주목
   		if (a.x == 0) return true;
   		else return false;
   	}
   };
   
   int main() {
   	Sample a(5), b(6);
   	bool ret = a.isZero(b);				// 이곳에 주목
   }
   ```

   **정답**

   ```cpp
   #include <iostream>
   using namespace std;
   
   class Sample {
   public:
   	int x;
   	Sample(int x) { this->x = x; }
   	friend bool isZero(Sample &a) {
   		if (a.x == 0) return true;
   		else return false;
   	}
   };
   
   // friend 선언한 함수를 외부 전역에 선언과 구현을 한다.
   bool isZero(Sample &a) {
   	if (a.x == 0) return true;
   	else return false;
   }
   
   int main() {
   	Sample a(5), b(6);
   	bool ret = isZero(b);	// isZero 함수는 객체의 멤버 함수가 아니므로 a.isZero로 호출하면 안된다.
   }
   ```


10. 다음 코드에서 프렌드 선언이 필요한지 설명하라.

    ```cpp
    #include <iostream>
    using namespace std;
    
    class Sample {
    public:
    	int x;
    	Sample(int x) { this->x = x; }
    	friend bool isZero(Sample &a);	// 이곳에 주목
    };
    
    bool isZero(Sample& a) {
    	if (a.x == 0) return true;
    	else return false;
    }
    ```

    > **프렌드 선언이 필요하지 않다.**
    > int x 가 public으로 선언되어 있기 때문에 굳이 프렌드가 필요없이 isZero에서 a의 객체의 x 멤버를 호출할 수 있다.



11. 다음은 어떤 다형성을 보여주고 있는가?

    ```cpp
    int a = 4 << 2;
    cout << 'a';
    ```

    > **<< 연산자의 중복을 보여준다.** 
    >
    > 기본 시프트 연산자(<<)의 본래 의미대로 사용한 사례이며, 문자 'a' 를 출력하도록 << 연산자가 중복 사용된 사례이다.



12. 다음에 보이는 다형성에 일치하는 다른 사례를 하나만 들어라.

    ```
    2 + 3 = 5
    빨강 + 파랑 = 보라
    남자 + 여자 = 결혼
    원래속도 + 100 = 150
    ```



13. 연산자 중복의 특징이 아닌 것은?

    1. 모든 연산자를 중복할 수 있는 것은 아니다.
    2. 연산자 중복을 통해 연사자의 근본 우선순위를 바꿀 수 없다.
    3. 연산자 중복은 반드시 클래스와 관계를 가진다.
    4. **연산자 중복은 어떤 기호를 사용하든지 가능하다. ( X , 연산자 중복이 되지 않는 연산자도 있다. )**

14. 다음에서 멤버 함수로 + 연산자 함수를 작성할 수 없는 경우는?

    ```cpp
    Power a, b;		// Power 클래스에 대해
    ```

    1. a = a + b
    2. b = a + 3
    3. b = a += 3
    4. **b = 3 + a ( X , 멤버 함수로는 작성할 수 없고 외부 전역 함수로는 사용할 수 있다. )**

15. 다음에서 a, b는 Power 클래스의 객체이다. 연산자 함수를 Power 클래스의 멤버 함수로 작성한다고 할 때, 왼쪽의 연산과 오른쪽의 연산자 함수의 선언이 잘못된 것은?

    1. a + b                     Power operator + (Power b);
    2. a == b                   bool  operator  ==  (Power &b);
    3. **a += b                   void  operator  +=  (Power b); ( X , 반환형이 void가 아닌 Power 이어야 한다. )**
    4. !a                          bool  operator  !( );



16. 다음에서 a, b는 Power 클래스의 객체이다. 연산자 함수를 Power 클래스의 프랜드 함수로 작성한다고 할 때, 왼쪽의 연산과 오른쪽의 연산자 함수의 선언이 잘못된 것은?
    1. a + b         Power operator + (Power& a, Power &b);
    2. a == b        bool operator == (Power a, Power b);
    3. **a++           Power operator ++ (Power a, int b); ( X , Power a 가 Power &a가 되어야 한다. )**
    4. a = b         Power operator = (Power &a, Power b);



17. 다음과 같은 Circle 클래스가 있다.

    ```cpp
    class Circle{
        int radius;
    public:
        Circle(int radius = 0) { this->radius = radius; }
        int getRadius() { return radius; }
    };
    ```

    다음과 같이 Circle 객체 a,b에 대한 치환 연산(=)이 제대로 이루어지도록 할 때 치환연산자를 작성할 필요가 있는지 설명하라.

    ```cpp
    Circle a(20), b(30);
    a = b;
    ```

    > **치환 연산자를 작성할 필요가 없다.**
    >
    > 두 객체를 비트 단위로 복사하는 객체의 기본 기본 치환 연산(=)으로도 문제가 발생하지 않는다.