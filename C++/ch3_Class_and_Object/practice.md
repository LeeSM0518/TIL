C++ 실습 공부
===

<br>

# Ch3 Class and Object
*3장 클래스 그리고 객체*

* **Circle_Class.cpp**  : 원형 클래스 만들기
  ```c++
  #include <iostream>
  using namespace std;

  class Circle{
  public:
          int radius;
          double getArea();
  };  // Circle 선언부

  double Circle::getArea(){
    return 3.14 * radius * radius;
  }   // Circle 구현부

  int main(){
    Circle donut;
    donut.radius = 1;
    double area = donut.getArea();
    cout << "donut 면적은 " << area << endl;

    Circle pizza;
    pizza.radius = 30;
    area = pizza.getArea();
    cout << "pizza 면적은 " << area << endl;
  }
  ```
  실행결과
  ```
  donut 면적은 3.14
  pizza 면적은 2826
  ```

  > Class는 **선언부** 와 **구현부** 로 만들어진다.

<br>

---

<br>

* **Constructor_Class.cpp** : 생산자 클래스 만들기
  ```c++
  #include <iostream>
  using namespace std;

  class Circle{
  public:
        int radius;
        Circle();   // 매개 변수가 없는 생산자
        Circle(int r);  // 매개 변수가 1개 있는 생산자
        double getArea();
  }

  Circle::Circe(){   // 매개 변수가 없는 생산자
    radius = 1;
    cout << "반지름 " << radius << " 원 생성" << endl;
  }

  Circle::Circle(int r){  // 매개 변수가 1개 있는 생산자
    radius = r;
    cout << "반지름 " << radius << " 원 생성" << endl;
  }

  double Circle::getArea(){
    return 3.14 * radius * radius;
  }

  int main(){
    Circle donut;
    double area = donut.getArea();
    cout << "donut 면적은 " << area << endl;

    Circle pizza(30);
    area = pizza.getArea();
    cout << "pizza 면적은 " << area << endl;
  }
  ```
  실행결과
  ```
  반지름 1 원 생성
  donut 면적은 3.14
  반지름 30 원 생성
  pizza 면적은 2826
  ```

  > 객체가 생성될 때 필요한 초기 작업을 하기 위해 **생산자** 를 선언 해준다.

<br>

---

<br>

* **Destructor_Class.cpp** : 소멸자 클래스 만들기
  ```c++
  #include <iostream>
  using namespace std;

  class Circle{
  public:
        int radius;

        Circle();
        Circle(int r);
        ~Circle();    // 소멸자 선언
        double getArea();
  };

  Circle::Circle(){
    radius = 1;
    cout << "반지름 " << radius << " 원 생성" << endl;
  }

  Circle::Circle(int r){
    radius = r;
    cout << "반지름 " << radius << " 원 생성" << endl;
  }

  double Circle::getArea(){
    return 3.14 * radius * radius;
  }

  Circle::~Circle(){  // 소멸자 함수
    cout << "반지름 " << radius << " 원 소멸" << endl;
  }

  int main(){
    Circle donut;
    Circle pizza(30);
  }
  ```
  실행결과
  ```
  반지름 1 원 생성
  반지름 30 원 생성
  반지름 30 원 소멸
  반지름 1 원 소멸
  ```

  > 소멸자는 만들어진 순서의 역순으로 메모리를 반환 시킨다.

<br>

---

<br>

* **Global_Classcpp.cpp** : 전역 변수 선언
  ```c++
  #include <iostream>
  using namespace std;

  class Circle {
  public:
    int radius;

    Circle();
    Circle(int r);
    ~Circle();
    double getArea();
  };

  Circle::Circle() {
    radius = 1;
    cout << "반지름 " << radius << " 원 생성" << endl;
  }

  Circle::Circle(int r) {
    radius = r;
    cout << "반지름 " << radius << " 원 생성" << endl;
  }

  Circle::~Circle() {
    cout << "반지름 " << radius << " 원 소멸" << endl;
  }

  double Circle::getArea() {
    return 3.14*radius*radius;
  }

  Circle globalDonut(1000); // 전역변수선언1
  Circle globalPizza(2000); // 전역변수선언2

  void f() {
    Circle fDonut(100);
    Circle fPizza(200);
  } // 객체 생성 함수 선언

  int main() {
    Circle donut;
    Circle pizza(30);
    f();
  }
  ```
  실행결과
  ```
  반지름 1000 원 생성
  반지름 2000 원 생성
  반지름 1 원 생성
  반지름 30 원 생성
  반지름 100 원 생성
  반지름 200 원 생성
  반지름 200 원 소멸
  반지름 100 원 소멸
  반지름 30 원 소멸
  반지름 1 원 소멸
  반지름 2000 원 소멸
  반지름 1000 원 소멸
  ```

  > 전역변수가 가장 먼저 실행되고 그 후 지역 변수가 실행되고 마지막으로 함수가 실행된다. 이것의 역순으로 소멸자가 실행된다.


<br>

---

<br>

