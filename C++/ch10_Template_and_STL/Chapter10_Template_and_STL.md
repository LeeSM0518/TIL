# Chapter10  Template_and_STL



## 10.1  일반화와 템플릿

### 함수 중복의 약점

```cpp
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <string>
using namespace std;

void myswap(int& a, int& b) {
	int tmp;
	tmp = a;
	a = b;
	b = tmp;
}

void myswap(double& a, double& b) {
	double tmp;
	tmp = a;
	a = b;
	b = tmp;
}

int main() {
	int a = 4, b = 5;
	myswap(a, b);
	cout << a << '\t' << b << endl;

	double c = 0.3, d = 12.5;
	myswap(c, d);
	cout << c << '\t' << d << endl;
}
```

**실행 결과**

```
5       4
12.5    0.3
```

> void myswap 두 함수는 매개 변수만 다르고 나머지 코드는 동일



### 일반화와 템플릿 선언

* **템플릿(template)** : 본 떠 찍어내기 위해 만들어진 틀, 함수나 클래스 코드를 찍어내듯이 생산할 수 있도록 **일반화(generic)**시키는 도구.



* **중복 함수의 일반화**

  : **template 키워드**를 이용하면, 중복 함수들을 일반화시킨 특별한 함수를 만들 수 있다. 이 함수를 **템플릿 함수(template function)** 혹은 **제네릭 함수(generic function)**이라 한다.

  * **예시**

    ```cpp
    template <class T>
        void myswap(T& a, T& b){
        T tmp;
        tmp = a;
        a = b;
        b = tmp;
    }
    ```



* **템플릿 선언과 제네릭 타입**

  ```cpp
  template <class T>
  template <typename T>
  template <class T1, class T2, class T3>
  template <class T> void myswap(T& a, T& b){
  ...
  }
  ```

  > **제네릭 타입(generic type)**이란 C++의 기본 타입이 아니며, 이들은 일반화시킨 새로운 타입으로 **일반 타입**이라고 부르기도 한다.



### 템플릿으로부터의 구체화

* **구체화** : 중복 함수들을 템플릿화하는 과정의 역과정

  * **예시**

    ```cpp
    int a=4, b=5;
    myswap(a, b); // 제네릭 타입 T에 int를 대입하여 구체화시킨 함수를 생성하고 호출
    ```

    ```cpp
    template <class T> void myswap(T & a, T & b){
        T tmp;
        tmp = a;
        a = b;
        b = tmp;
    }
    
    // T에 int를 대입하여 구체화된 소스 코드 생성
    
    void myswap(int & a, int & b){
        int tmp;
        tmp = a;
        a = b;
        b = tmp;
    }
    ```

* **제네릭 myswap() 함수 만들기**

  ```cpp
  #define _CRT_SECURE_NO_WARNINGS
  #include <iostream>
  #include <string>
  using namespace std;
  
  class Circle {
  	int radius;
  public:
  	Circle(int radius = 1) {
  		this->radius = radius;
  	}
  	int getRadius() {
  		return radius;
  	}
  };
  
  template <class T>
  void myswap(T& a, T& b) {	// 제네릭 함수
  	T tmp;
  	tmp = a;
  	a = b;
  	b = tmp;
  }
  
  int main() {
  	int a = 4, b = 5;
  	myswap(a, b);	// myswap(int& a, int& b) 함수 구체화 및 호출
  	cout << "a= " << a << ", " << "b= " << b << endl;
  
  	double c = 0.3, d = 12.5;
  	myswap(c, d);	// myswap(double& a, double& b) 함수 구체화 및 호출
  	cout << "c= " << c << ", " << "d= " << d << endl;
  
  	Circle donut(5), pizza(20);
  	myswap(donut, pizza); // myswap(Circle &a, Circle &b) 함수 구체화 및 호출
  	cout << "donut반지름= " << donut.getRadius() << ", ";
  	cout << "pizza반지름= " << pizza.getRadius() << endl;
  }
  ```

  **실행 결과**

  ```
  a= 5, b= 4
  c= 12.5, d= 0.3
  donut반지름= 20, pizza반지름= 5
  ```



### 템플릿 역할

: 제네릭 함수를 선언하고, 컴파일 시점에 구체화시키기 위한 틀을 만드는 것이다. 템플릿 함수로부터 구체화된 버전의 함수가 컴파일되고 호출된다.



### 구체화 오류

```cpp
template <class T> void myswap(T & a, T & b);

int s=4;
double t=5;
myswap(s, t);	//  컴파일 오류, s와 t의 타입이 같아야 한다.
```



### 템플릿의 장단점과 제네릭 프로그래밍

: **생산성과 유연성**을 높인다. 컴파일러에 따라서 템플릿이 지원되지 않을 수 있기 때문에 **포팅에 취약하다.** 또한 템플릿과 관련된 컴파일 **오류 메시지가 빈약**하여 **디버깅에 많은 어려움이 있다.**



## CHECK TIME

1. 일반화의 역과정을 무엇이라 부른가?

   **구체화**

2. 템플릿 함수로부터 구체화된 버전의 함수를 생성하는 과정은 컴파일 시에 처리되는가, 실행 시에 처리되는가?

   **컴파일 시에 처리된다.**



3. 다음 제네릭 함수 add()가 있다.

   ```cpp
   template <class T>
       T add(T data[], int n){
       T sum = 0;
       for(int i=0; i<n; i++) sum += data[i];
       return sum;
   }
   ```

   다음 코드를 컴파일하면 생성되는 구체화된 버전의 add() 함수의 소스는 무엇인가?

   ```cpp
   double x[] = {1.1, 2.2, 3.3, 4.4, 5.5};
   add(x, 5);	
   ```

   > double add(double data[], int n){
   >
   > ​	double sum = 0;
   >
   > ​	...
   >
   > }



4. 다음 제네릭 함수 sum()으로부터 구체화가 실패하는 경우는?

   ```cpp
   template <class TYPE>
   TYPE sum(TYPE a, TYPE b){
   ...
   }
   ```

   1. int n = sum(2, 10);
   2. sum('a', 'b');
   3. **sum(3.5, 6); (X, 매개 변수 타입이 다르다.)**
   4. sum(0, 1);



### 10.2  다양한 제네릭 함수 만들기

### 하나의 제네릭 타입을 가진 경우

* **두 개의 매개 변수로부터 큰 값 구하기**

  ```CPP
  template <class T>
  T bigger(T a, T b)	// 두 개의 매개 변수 a, b를 비교하여 큰 값 리턴
  ```



* **큰 값을 리턴하는 제네릭 bigger() 함수 만들기 연습**

  ```cpp
  #define _CRT_SECURE_NO_WARNINGS
  #include <iostream>
  #include <string>
  using namespace std;
  
  template <class T>
  T bigger(T a, T b) {
  	if (a > b) return a;
  	else return b;
  }
  
  int main() {
  	int a = 20, b = 50;
  	char c = 'a', d = 'z';
  	cout << "bigger(20, 50) = " << bigger(a, b) << endl;
  	cout << "bigger(c, d) = " << bigger(c, d) << endl;
  }
  ```

  **실행 결과**

  ```
  bigger(20, 50) = 50
  bigger(c, d) = z
  ```



* **배열의 합 구하기**

  ```cpp
  template <class T>
  T add(T data[], int n)
  ```



* **배열의 합을 구하여 리턴하는 제네릭 add() 함수 만들기 연습**

  ```cpp
  #define _CRT_SECURE_NO_WARNINGS
  #include <iostream>
  #include <string>
  using namespace std;
  
  template <class T>
  // 타입 T의 배열 data에서 n개의 원소를 합한 결과 리턴
  T add(T data[], int n) {
  	T sum = 0;
  	for (int i = 0; i < n; i++) {
  		sum += data[i];
  	}
  	// sum 변수의 타입과 리턴 타입이 모두 T로 선언되어 있음
  	return sum;
  }
  
  int main() {
  	int x[] = { 1,2,3,4,5 };
  	double d[] = { 1.2, 2.3, 3.4, 4.5, 5.6, 6.7 };
  
  	cout << "sum of x[] = " << add(x, 5) << endl;
  	cout << "sum of d[] = " << add(d, 6) << endl;
  }
  ```

  **실행 결과**

  ```
  sum of x[] = 15
  sum of d[] = 23.7
  ```



### 두 개 이상의 제네릭 타입을 가진 경우

* **예시**

  ```cpp
  template <class T1, class T2>
  void mcopy(T1 src[], T2 dest[], int n)
  ```

* **배열을 복사하는 제네릭 mcopy() 함수 만들기 연습**

  ```cpp
  #define _CRT_SECURE_NO_WARNINGS
  #include <iostream>
  #include <string>
  using namespace std;
  
  template <class T1, class T2>
  // src[]의 n개 원소를 dest[]에 복사
  void mcopy(T1 src[], T2 dest[], int n) {
  	for (int i = 0; i < n; i++) {
  		dest[i] = (T2)src[i];
  	}
  }
  
  int main() {
  	int x[] = { 1,2,3,4,5 };
  	double d[5];
  	char c[5] = { 'H','e','l','l','o' }, e[5];
  
  	mcopy(x, d, 5);
  	mcopy(c, e, 5);
  
  	for (int i = 0; i < 5; i++) cout << d[i] << ' ';
  	cout << endl;
  	for (int i = 0; i < 5; i++) cout << e[i] << ' ';
  	cout << endl;
  }
  ```

  **실행 결과**

  ```
  1 2 3 4 5
  H e l l o
  ```



### 템플릿 함수보다 중복 함수가 우선

                                                                                 ```cpp
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <string>
using namespace std;

template<class T>
void print(T array[], int n) {
​	for (int i = 0; i < n; i++) {
​		cout << array[i] << '\t';
​	}
​	cout << endl;
}

int main() {
​	int x[] = { 1,2,3,4,5 };
​	double d[5] = { 1.1,2.2,3.3,4.4,5.5 };
​	print(x, 5);
​	print(d, 5);

	char c[5] = { 1,2,3,4,5 };
	print(c, 5);
}
​                                                                                 ```

**실행 결과**

```
1       2       3       4       5
1.1     2.2     3.3     4.4     5.5
                            		// 그래픽 기호
```

 

### 템플릿 함수에 디폴트 매개 변수 사용

```cpp
template <class T1, class T2>
void mcopy(T1 src [], T2 dest [], int n=5){
	for(int i=0; i<n; i++)
		dest[i] = (T2)src[i];
}
```

```cpp
int x[] = {1,2,3,4,5};
double d[5];
mcopy(x, d);	// x[]의 원소 5개를 d[]에 복사
```



## CHECK TIME

1. 다음 두 함수를 일반화시킨 제네릭 함수를 작성하라.

   ```cpp
   int add(int x, int y){
       int n = x + y;
       return n;
   }
   double add(double x, double y){
       double d = x + y;
       return d;
   }
   ```

   **정답**

   ```cpp
   template <class T>
       T add(T x, T y){
       T n = x + y;
       return n;
   }
   ```





## 10.3  제네릭 클래스 만들기

### 제네릭 클래스 개요

: template을 이용하면 **제네릭 클래스(generic class)**도 만들 수 있다.



### 제네릭 클래스 선언

* **제네릭 클래스 선언부**

  ```cpp
  template <class T>
  class MyStack {
  	int tos;
  	T data[100];	// T 타입의 배열, 스택에 최대 100개의 원소 저장
  public:
  	MyStack();
  	void push(T element);	// T 타입 원소 element를 data[]에 푸시
  	T pop();	// 스택에 탑에 있는 원소를 data[]에서 팝하여 리턴
  };
  ```



* **제네릭 클래스 구현부**

  ```cpp
  void MyStack<T>::push(T element){
      ...
  }
  template <class T> T MyStack<T>::pop(){	// 한 줄에 선언할 수 있음
      ...
  }
  ```



### 제네릭 클래스 구체화

```cpp
MyStack<int> iStack;		// int 타입을 다루는 스택 객체 생성
MyStack<double> dStack;		// double 타입을 다루는 스택 객체 생성
```

생성된 iStack, dStack 객체들은 다음과 같이 보통 객체처럼 사용하면 된다.

```cpp
iStack.push(3);
int n = iStack.pop();

dStack.push(3.5);
double d = dStack.pop();
```

다음과 같이 제네릭 클래스의 포인터를 선언하고 동적으로 객체를 생성할 수도 있다.

```cpp
MyStack<char> *p = new MyStack<char>();
p->push('a');
char c = p->pop();
delete p;
```



* **제네릭 스택 클래스 만들기**

  ```cpp
  #define _CRT_SECURE_NO_WARNINGS
  #include <iostream>
  #include <string>
  using namespace std;
  
  template <class T>
  class MyStack {
  	int tos;
  	T data[100];	// T 타입의 배열.
  public:
  	MyStack();
  	void push(T element);	// T 타입 원소
  	T pop();	// T 타입 data[] 배열에서 리턴
  };
  
  template <class T>
  MyStack<T>::MyStack() {	// 생성자
  	tos = -1;
  }
  
  template <class T>
  void MyStack<T>::push(T element) {
  	if (tos == 99) {
  		cout << "stack full";
  		return;
  	}
  	tos++;
  	data[tos] = element;
  }
  
  template <class T>
  T MyStack<T>::pop() {
  	T retData;
  	if (tos == -1) {
  		cout << "stack empty";
  		return 0;
  	}
  	retData = data[tos--];
  	return retData;
  }
  
  int main() {
  	MyStack<int> iStack;	// int 만 저장할 수 있는 스택
  	iStack.push(3);
  	cout << iStack.pop() << endl;
  
  	MyStack<double> dStack;	// double 만 저장할 수 있는 스택
  	dStack.push(3.5);
  	cout << dStack.pop() << endl;
  
  	MyStack<char> *p = new MyStack<char>();	// char 만 저장할 수 있는 스택
  	p->push('a');
  	cout << p->pop() << endl;
  	delete p;
  }
  ```

  **실행 결과**

  ```
  3
  3.5
  a
  ```



* **제네릭 스택의 제네릭 타입을 포인터나 클래스로 구체화하는 예**

  ```cpp
  #define _CRT_SECURE_NO_WARNINGS
  #include <iostream>
  #include <string>
  using namespace std;
  
  template <class T>
  class MyStack {
  	int tos;
  	T data[100];
  public:
  	MyStack();
  	void push(T element);
  	T pop();
  };
  
  template <class T>
  MyStack<T>::MyStack() {	// 생성자
  	tos = -1;
  }
  
  template <class T>
  void MyStack<T>::push(T element) {
  	if (tos == 99) {
  		cout << "stack full";
  		return;
  	}
  	tos++;
  	data[tos] = element;
  }
  
  template <class T>
  T MyStack<T>::pop() {
  	T retData;
  	if (tos == -1) {
  		cout << "stack empty";
  		return 0;
  	}
  	retData = data[tos--];
  	return retData;
  }
  
  class Point {
  	int x, y;
  public:
  	Point(int x = 0, int y = 0) {
  		this->x = x;
  		this->y = y;
  	}
  	void show() {
  		cout << "(" << x << "," << y << ")" << endl;
  	}
  };
  
  int main() {
  	MyStack<int *> ipStack;	// int* 만 저장하는 스택
  	int *p = new int[3];
  	for (int i = 0; i < 3; i++)p[i] = i * 10;
  	ipStack.push(p);	// 포인터 푸시
  
  	int *q = ipStack.pop();	
  	for (int i = 0; i < 3; i++) cout << q[i] << ' ';
  	cout << endl;
  	delete[] p;
  
  	MyStack<Point> pointStack;	// Point 객체만 저장
  	Point a(2, 3), b;
  	pointStack.push(a);
  	b = pointStack.pop();
  	b.show();
  
  	MyStack<Point*> pStack;		// Point* 객체만 저장
  	pStack.push(new Point(10, 20));
  	Point* pPoint = pStack.pop();
  	pPoint->show();
  
  	MyStack<string> stringStack;	// 문자열만 저장하는 스택
  	string s = "c++";
  	stringStack.push(s);
  	stringStack.push("java");
  	cout << stringStack.pop() << ' ';
  	cout << stringStack.pop() << endl;
  }
  ```

  **실행결과**

  ```
  0 10 20
  (2,3)
  (10,20)
  java c++
  ```



### 두 개 이상의 제네릭 타입을 가진 제네릭 클래스

* **두개의 제네릭 타입을 가진 클래스 만들기**

  ```cpp
  #define _CRT_SECURE_NO_WARNINGS
  #include <iostream>
  #include <string>
  using namespace std;
  
  template <class T1, class T2>
  class GClass {
  	T1 data1;
  	T2 data2;
  public:
  	GClass();
  	void set(T1 a, T2 b);
  	void get(T1 &a, T2 &b);
  };
  
  template <class T1, class T2>
  GClass<T1, T2>::GClass(){
  	data1 = 0;
  	data2 = 0;
  }
  
  template <class T1, class T2>
  void GClass<T1, T2>::set(T1 a, T2 b) {
  	data1 = a;
  	data2 = b;
  }
  
  template <class T1, class T2>
  void GClass<T1, T2>::get(T1 &a, T2 &b) {
  	a = data1;
  	b = data2;
  }
  
  int main() {
  	int a;
  	double b;
  	GClass<int, double> x;
  	x.set(2, 0.5);
  	x.get(a, b);
  	cout << "a= " << a << '\t' << "b= " << b << endl;
  
  	char c;
  	float d;
  	GClass<char, float> y;
  	y.set('m', 12.5);
  	y.get(c, d);
  	cout << "c= " << c << '\t' << "d= " << d << endl;
  }
  ```

  **실행결과**

  ```
  a= 2    b= 0.5
  c= m    d= 12.5
  ```


## CHECK TIME

1. 다음은 제네릭 클래스 TestClass를 만든 예로서 몇 가지 오류를 가지고 있다.

   ```cpp
   template <class T>
   class TestClass {
   	int x;
   public:
   	void set(T a);
   	T get();
   };
   
   void TestClass::set(T a) { x = a; }
   T TestClass::get() { return x; }
   ```

   1. TestClasee를 올바르게 고쳐라.

      ```cpp
      template <class T>
      class TestClass {
      	T x;	// int -> T
      public:
      	void set(T a);
      	T get();
      };
      
      template <class T>	// 이 라인 추가
      void TestClass<T>::set(T a) { x = a; }	// <T> 추가
      
      template <class T>	// 이 라인 추가
      T TestClass<T>::get() { return x; }	// <T> 추가
      ```

   2. 제네릭 타입 T를 int 타입을 구체화시키는 TestClass 객체 tmp를 선언하라.

      ```cpp
      TestClass<int> tmp;
      ```

       



## 10.4  C++ 표준 템플릿 라이브러리(STL)와 활용

* **STL(Standard Template Library)**
  *  **표준 템플릿 라이브러리**
    * C++ 표준 라이브러리 중 하나
    * 제네릭 클래스와 제네릭 함수가 제공됨



* **STL의 구성**
  * **컨테이너** : 템플릿 클래스
  * **iterator** : 반복자, 컨테이너 원소에 대한 포인터
  * **알고리즘** : 복사, 검색, 삭제, 정렬 등의 기능을 구현한 템플릿 함수



### STL과 관련된 헤더 파일과 이름 공간

* **헤더 파일**
  * 컨테이너 클래스를 사용하기 위한 헤더 파일
    * verter 클래스는 **#include \<vector>**
    * list 클래스 **#include \<list>**
  * 알고리즘 함수를 사용하기 위한 헤더 파일
    * 알고리즘 함수에 상관 없이 **#include \<algorithm>**



### vertor 컨테이너 활용

* **특징**
  * **가변 길이 배열**
  * **저장, 삭제, 검색** 용이
  * 인덱스로 접근 가능



* **vertor 컨테이너 활용**

  ```cpp
  #define _CRT_SECURE_NO_WARNINGS
  #include <iostream>
  #include <string>
  #include <vector>
  using namespace std;
  
  int main() {
  	vector<int> v;	// 정수만 삽입 가능한 벡터 생성
  
  	v.push_back(1);
  	v.push_back(2);
  	v.push_back(3);
  
  	for (int i = 0; i < v.size(); i++)
  		cout << v[i] << " ";
  	cout << endl;
  
  	v[0] = 10;		// 벡터의 첫 번째 원소 10으로 변경
  	int m = v[2];	// m에 3 저장
  	v.at(2) = 5;	// 백터의 3 번째 원소를 5로 변경
  
  	for (int i = 0; i < v.size(); i++)
  		cout << v[i] << " ";
  	cout << endl;	
  }
  ```

  **실행결과**

  ```
  1 2 3
  10 2 5
  ```



* **문자열을 저장하는 벡터 만들기 연습**

  ```cpp
  #define _CRT_SECURE_NO_WARNINGS
  #include <iostream>
  #include <string>
  #include <vector>
  using namespace std;
  
  int main() {
  	vector<string> sv;	// 문자열 벡터 생성
  	string name;
  
  	cout << "이름을 5개 입력" << endl;
  	for (int i = 0; i < 5; i++){
  		cout << i + 1 << ">>";
  		getline(cin, name);
  		sv.push_back(name);	// 벡터에 삽입
  	}
  
  	name = sv.at(0);
  	for (int i = 1; i < sv.size(); i++) {	// sv 사이즈
  		if (name < sv[i])
  			name = sv[i];
  	}
  	cout << "사전에서 가장 뒤에 나오는 이름은 " << name << endl;
  }
  ```

  **실행 결과**

  ```
  이름을 5개 입력
  1>>황기태
  2>>이재문
  3>>김남윤
  4>>한원선
  5>>애슐리
  사전에서 가장 뒤에 나오는 이름은 황기태
  ```


### iterator 사용

* **iterator란?**
  * **반복자**
  * 원소들을 하나씩 순차적으로 접근하기 위한 **원소에 대한 포인터**
* **iterator 변수 선언**
  * 구체적인 컨테이너를 지정하여 반복자 변수 생성



* **iterator를 이용하여 vector의 모든 원소들에 2 곱하기**

  ```cpp
  #define _CRT_SECURE_NO_WARNINGS
  #include <iostream>
  #include <string>
  #include <vector>
  using namespace std;
  
  int main() {
  	vector<int> v;
  	v.push_back(1);
  	v.push_back(2);
  	v.push_back(3);
  
  	vector<int>::iterator it;	// 벡터 v의 원소에 대한 포인터 it 선언
  
  	// iterator를 이용하여 모든 원소 탐색
  	for (it = v.begin(); it != v.end(); it++) {
  		int n = *it;
  		n = n * 2;
  		*it = n;
  	}
  
  	for (it = v.begin(); it != v.end(); it++) {
  		cout << *it << ' ';
  	}cout << endl;
  }
  ```

  **실행 결과**

  ```
  2 4 6
  ```


### STL 알고리즘 사용하기

* **알고리즘 함수**
  * 템플릿 함수
  * **전역 함수**
    * STL 컨테이너 클래스의 멤버 함수가 아님
  * iterator와 함께 작동



* **sort() 함수 사례**
  * 두 개의 매개 변수
    * 첫 번째 매개 변수: 정렬을 **시작할 원소의  iterator주소**
    * 두 번째 매개 변수: 정렬 범위의 **마지막 원소 다음의 iterator 주소**



* **sort() 함수를 이용한 vector 소팅**

  ```cpp
  #define _CRT_SECURE_NO_WARNINGS
  #include <iostream>
  #include <string>
  #include <vector>
  #include <algorithm>	// 알고리즘 헤더 파일
  using namespace std;
  
  int main() {
  	vector<int> v;	// 정수 벡터 생성
  
  	cout << "5개의 정수를 입력하세요>> ";
  	for (int i = 0; i < 5; i++) {
  		int n;
  		cin >> n;
  		v.push_back(n);
  	}
  
      // v.begin()에서 v.end() 사이의 값을 오름차순으로 정렬
  	sort(v.begin(), v.end());	// 벡터 소팅
  
  
  	vector<int>::iterator it;	// 백터 내의 원소를 탐색하는 iterator 변수 선언
  
  	for (it = v.begin(); it != v.end(); it++)
  		cout << *it << ' ';
  	cout << endl;
  }
  ```

  **실행 결과**

  ```
  5개의 정수를 입력하세요>> 30 -7 250 6 120
  -7 6 30 120 250
  ```





# 연습문제

1. 일반화와 템플릿에 대해 잘못 설명한 것은?
   1. 템플릿은 C++에서 일반화를 위한 도구이다.
   2. 템플릿을 이용하여 함수와 클래스를 일반화할 수 있다.
   3. **템플릿을 선언하기 위해 사용하는 키워드는 template이나 generic 이다.(X, generic은 아니다.)**
   4. 제네릭 타입을 선언하기 위해 사용하는 키워드는 class이다



2. 템플릿에 대해 잘못 말한 것은?
   1. 템플릿을 사용하면 소프트웨어 생산성과 유연성이 높아진다.
   2. 컴파일러에 따라 템플릿을 지원하지 않을 수 있기 때문에 포딩에 취약하다.
   3. **템플릿을 사용하면 컴파일 오류 메시지가 풍부하여 디버깅에 많은 도움을 준다.(X, 오류 메시지가 적다.)**
   4. 제네릭 프로그래밍이라는 새로운 프로그래밍 패러다임을 가져왔다.



3. 다음에서 템플릿 선언을 잘못한 것은?
   1. template \<class T>
   2. **template (class T) (X, 에러)**
   3. template \<typename T>
   4. template \<typename T1, typename T2>



4. 구체화의 과정은 누구에 의해 이루어지는가?
   1. 개발자
   2. **컴파일러(O)**
   3. 로더
   4. 운영체제



5. 다음 두 함수를 일반화한 제네릭 함수를 작성하라.

   ```cpp
   bool equal(int a, int b){
       if(a == b) return true;
       else return false;
   }
   
   bool equal(char a, char b){
       if(a == b) return true;
       else return false;
   }
   ```

   **정답**

   ```cpp
   template<class T>
   bool equal(T a, T b){
       if(a == b) return true;
       else return false;
   }
   ```



6. 다음 두 함수들을 일반화한 제네릭 함수를 작성하라.

   ```cpp
   void insert(int a, int b[], int index){
       b[index] = a;
   }
   void insert(char a, char *b, int index){
       *(b+index) = a;
   }
   ```

   **정답**

   ```cpp
   template <class T>
       void insert(T a, T b[], int index){
       b[index] = a;
   }
   ```



7. 다음 제네릭 함수 선언에서 잘못된 부분을 바르게 고쳐라.

   ```cpp
   template <typename T> int max(T x, T y){
       if(x > y) return x;
       else return y;
   }
   ```

   **정답**

   ```cpp
   template <typename T> T max(T x, T y){
       if(x > y) return x;
       else return y;
   }
   ```




8. 다음 제네릭 클래스의 선언에서 잘못된 부분을 바르게 고쳐라.

   ```cpp
   template <class TYPE>
   TYPE equals(TYPE x, int y){
   	if(x == y) return true;
   	else return false;
   }
   ```

   **정답**

   ```cpp
   template <class TYPE>
   bool equals(TYPE x, TYPE y){
   	if(x == y) return true;
   	else return false;
   }
   ```



9. 다음 제네릭 함수가 있다.

   ```cpp
   template <class T> T avg(T *p, int n){
       int k;
       T sum=0;
       for(k=0; k<n; k++){
           sum += p[k];
       }
       return sum/n;
   }
   ```

   아래의 호출을 컴파일하여 생성되는 구체화된 버전의 avg() 함수의 소스 코드는 무엇인가?

   1. ```cpp
      int a[] = {1,2,3,4,5};
      
      cout << avg(a, 5);
      ```

      **정답**

      ```cpp
      int avg(int *p, int n){
          int k;
          int sum=0;
          for(k=0; k<n; k++) sum ++ p[k];
          return sum/n;
      }
      ```

   2. ```cpp
      double d[] = {3.5, 6.7, 7.8};
      cout << avg(d, 3);
      ```

      **정답**

      ```cpp
      double avg(double *p, int n){
          int k;
          double sum=0;
          for(k=0; k<n; k++) sum += p[k];
          return sum/n;
      }
      ```


10. 다음 두 개의 함수가 있을 때, 질문에 답하여라

    ```cpp
    template <class T> void show(T a){
        cout << a;
    }
    void show(int a){
        cout << "special" << a;
    }
    ```

    1. 이 두 함수가 공존할 수 있는가?

       **예, 템플릿 함수와 보통 함수는 중복하여 공존할 수 있다.**

    2. 만일 1의 답이 예라면, show(3.14);를 호출한 결과는?

       ```
       3.14	// 템플릿 함수를 호출하였음
       ```

    3. 만일 1의 답이 예라면, show(100);을 호출한 결과는?

       ```
       special 100		// 중복된 보통 함수를 호출하였다.
       ```



11. 템플릿에 대한 설명 중 맞는 것은?

    1. 이 기능은 C++에만 있다.
    2. 컴파일러는 템플릿 함수나 클래스를 컴파일하여 일반화된 바이너리 코드를 생성한 후, 필요한 구체화를 실행한다.

    3. **템플릿 함수와 동일한 이름의 함수가 중복되어 있을 때, 중복 함수가 우선적으로 바인딩된다.(O)**
    4. 템플릿 함수를 선언할 때 디폴트 매개 변수를 선언할 수 없다.



12. 템플릿 클래스 Container를 작성하고자 한다.

    ```cpp
    template <class T1> class Container{
     	// 빈칸
    public:
        Container(int n);
        ~Container();
        void set(int index, T value){ p[index] = value; }
        T get(int index);
    }
    ```

    1. 빈칸을 적절하게 채워라.

       ```cpp
       template <class T1> class Container{
        	T *p;
           int size;
       public:
           Container(int n);
           ~Container();
           void set(int index, T value){ p[index] = value; }
           T get(int index);
       }
       ```

    2. 생성자를 작성하라.

       ```cpp
       template <class T> Container<T>::Container(int n){
           p = new T[n];
           size = n;
       }
       ```

    3. 소멸자를 작성하라.

       ```cpp
       template <class T> Container<T>::~Container(){
           delete p[];
       }
       ```

    4. get()을 작성하라.

       ```cpp
       template <class T> T Container<T>::get(int index){
           return p[index];
       }
       ```

    5. char 타입의 문자만 저장 가능한 Container 객체 c를 생성하는 선언문을 작성하라(c의 크기는 26);

       ```cpp
       Container<char> c(26);
       ```

    6. 문제 5에서 생성한 객체 c에 set() 함수를 이용하여 알파벳 'a'~'z'를 삽입하고, get() 함수를 이용하여 반대순으로 화면에 출력하는 main() 함수를 작성하라.

       ```cpp
       #include <iostream>
       using namespace std;
       int main(){
           Container<char> c(26);
           for(int i=25; i>=0; i--)
               cout << c.get(i);
       }
       ```

13. c++의 표준 STL 라이브러리가 작성된 이름 공간은 무엇인가?

    1. **std**
    2. template
    3. stl
    4. algorithm



14. 다음 STL의 각 기능을 사용하기 위해 필요한 헤더 파일은 무엇인가?
    1. vector    -> \<vector>
    2. list      -> \<list>
    3. merge     -> \<algorithm>
    4. search    -> \<algorithm>



15. STL의 vector 클래스를 활용하는 코드이다. 다음 빈칸을 채워라

    ```cpp
    vector<double> v;
    v.push_back(3.1);
    v.push_back(4.1);
    
    for(int i=0; i<v.size(); i++)
        cout << v[i] << endl;
    ```



16. vector\<char> 타입의 객체 v가 함수 print()의 매개 변수로 넘어왔을 때, 반복문을 이용하여 모든 원소를 화면에 출력하고자 한다. 빈칸에 적절한 코드를 삽입하라.

    ```cpp
    void print(vector<char> &v){
        vector<char>::iterator it;	// 빈칸
        for(it=v.begin(); it!=v.end(); it++){
            char c = *it;
            cout << c;
        }
    }
    ```
