# 함수 중복의 모호성
* **디폴트 매개 변수로 인한 함수의 모호성**
  ```cpp
  void f(int a, int b);
  void f(int a = 3, int b = 3);
  // 컴파일 에러
  // f(), f(1) 은 상관이 없으나 f(1,2)를 하면 모호한 함수 호출이기 때문에 컴파일 오류

  void f(int a);
  void f(int *a);
  // 모호하지 않다.

  void f(int *a);
  void f(int &a);
  // 모호하다.

  void f(int a, int b);
  void f(int a = 3);
  // 모호하지 않다.

  void f(int a, int b = 2);
  void f(int a = 3);
  // 모호하다.
  // f(1), f(1, 2)와
  // f(), f(1) 가 겹치게 되어 모호하다고 할 수 있다.
  ```

<br>

# Static 멤버 선언
* **EX)**
  ```c
  void f( ) {
      static int a;  // 전역변수 선언
  }
  ```

<br>

* **Static**
  > 생명 주기 : 프로그램이 시작될 때 생성, 프로그램 종료 시 소멸<br>
  사용 범위 : 선언된 범위, 접근 지정에 따름

<br>

* **Static 멤버**
  * 프로그램이 시작할 때 생성
  * 클래스 당 **하나만 생성**
  * 모든 객체들이 공유하는 멤버
  
<br>

* **Static 멤버 선언** <br>
  * **예시1)**
  ```cpp
    #include <iostream>
    using namespace std;

    class Person {
    public:
        double money;
        void addMoney(int money) {
            this->money += money;
        }

        static int sharedMoney;		// 공금
        static void addShared(int n) {
            sharedMoney += n;
        }
    };

    // static 변수 생성, 전역 공간에 생성
    int Person::sharedMoney = 10;	// 10으로 초기화

    int main() {
        Person han;
        han.money = 100;	// han의 개인 돈 = 100
        han.sharedMoney = 200; // static 멤버 접근, 공금 = 200

        Person lee;
        lee.money = 150;	// lee의 개인 돈 = 150
        lee.addMoney(200);	// lee의 개인 돈 = 350
        lee.addShared(200);	// static 멤버 접근, 공급 = 400

        cout << han.money << ' ' << lee.money << endl;
        cout << han.sharedMoney << ' ' << lee.sharedMoney << endl;
    }
  ```

  <br>

  * **예시 2)**
  ```cpp
    #include <iostream>
    using namespace std;

    class Person {
    public:
        double money;
        void addMoney(int money) {
            this->money += money;
        }

        static int sharedMoney;		// 공금
        static void addShared(int n) {
            sharedMoney += n;
        }
    };

    // static 변수 생성, 전역 공간에 생성
    int Person::sharedMoney = 10;	// 10으로 초기화

    int main() {
        Person::addShared(50);
        cout << Person::sharedMoney << endl;

        Person han;
        han.money = 100;
        han.sharedMoney = 200;
        Person::sharedMoney = 300;
        Person::addShared(100);

        cout << han.money << ' ' << Person::sharedMoney << endl;
    }
  ```
  