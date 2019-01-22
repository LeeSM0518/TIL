# 멀티 스레드 개념

## 12.1 멀티 스레드 개념

### 12.1.1 프로세스와 스레드

* 운영체제에서는 실행 중인 **하나의 애플리케이션을 프로세스(process)**라고 부른다. 사용자가 애플리케이션을 실행하면 운영체제로부터 실행에 필요한 메모리를 할당받는다.

* **멀티 태스킹(multi tasking)** : 두 가지 이상의 작업을 동시에 처리하는 것. 운영체제는 멀티 태스킹을 할 수 있도록 CPU 및 메모리 자원을 프로세스마다 적절히 할당시키고, 병렬로 실행시킨다.
* **멀티 스레드(multi thread)**
  * 운영체제에서 할당받은 자신의 메모리를 가지고 실행하기 때문에 서로 독립적이다.
  * 하나의 프로세스 내부에 생성되기 때문에 하나의 스레드가 예외를 발생시키면 프로세스 자체가 종료될 수 있다.

![1548144112048](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548144112048.png)

<br>

### 12.1.2 메인 스레드

: 모든 자바 애플리케이션은 메인 스레드(main thread)가 main() 메소드를 실행하면서 시작된다.

* **메인 스레드는 필요에 따라 작업 스레드들을 만들어서 병렬로 코드를 실행할 수 있다.**
* 멀티 스레드 애플리케이션에서는 실행 중인 스레드가 하나라도 있으면, 프로세스는 종료되지 않는다.

<br>

## 12.2 작업 스레드 생성과 실행

![1548144400825](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548144400825.png)

: 몇 개의 작업을 병렬로 실행할지 결정하고 각 작업별로 스레드로 생성해야 한다.

<br>

### 12.2.1 Thread 클래스로부터 직접 생성

* **Thread 클래스 생성**

  ```java
  Thread thread = new Thread(Runnable target);
  ```

* **Runnable 구현 클래스**

  ```java
  class Task implements Runnable {
      public void run() {
          스레드가 실행할 코드;
      }
  }
  ```

* **예제1. 메인 스레드만 이용한 경우**

  ```java
  package thread_class_direct_production;
  
  import java.awt.*;
  
  public class BeepPrintExample1 {
      public static void main(String[] args) {
          // Toolkit 객체 얻기
          Toolkit toolkit = Toolkit.getDefaultToolkit();
  
          for(int i=0; i<5; i++) {
              toolkit.beep();     // 비프음 발생
              try {
                  Thread.sleep(500);  // 0.5초간 일시 정지
              } catch (Exception e) {
  
              }
          }
  
          for(int i=0; i<5; i++) {
              System.out.println("띵");
              try {
                  Thread.sleep(500);  // 0.5초간 일시 정지
              } catch (Exception e) {
  
              }
          }
      }
  }
  ```

  > 비프음을 5번 발생시키고 "띵"을 5번 출력시킨다.

  <br>

* **예제2. 메인 스레드와 작업 스레드가 동시에 실행**

  BeepTask.java

  ```java
  package thread_class_direct_production;
  
  import java.awt.*;
  
  // Runnable 구현 클래스를 만든다
  public class BeepTask implements Runnable {
      @Override
      public void run() {
          Toolkit toolkit = Toolkit.getDefaultToolkit();
          for(int i=0; i<5; i++) {
              toolkit.beep();
              try { Thread.sleep(500);} catch (Exception e) {}
          }
      }
  }
  ```

  BeepPrintExample2.java

  ```java
  package thread_class_direct_production;
  
  public class BeepPrintExample2 {
      public static void main(String[] args) {	// 메인 스레드
          Runnable beepTask = new BeepTask();		// Runnable 객체 생성
          Thread thread = new Thread(beepTask);	// beepTask 스레드 선언
          thread.start();		// beepTask 스레드 실행
  
          for(int i=0; i<5; i++) {
              System.out.println("띵");
              try {
                  Thread.sleep(500);
              } catch (Exception e) { }
          }
      }
  }
  ```

  <br>

### 12.2.2 Thread 하위 클래스로부터 생성

: 작업 스레드가 실행할 작업을 Runnable로 만들지 않고, Thread의 하위 클래스로 작업 스레드를 정의하면서 작업 내용을 포함시킬 수도 있다.

```java
public class WorkerThread extends Thread {
    @Override
    public void run() {
        // 스레드가 실행할 코드
    }
}

Thread thread = new WorkerThread();
// 익명 객체로 작업 스레드 객체를 생성할 수도 있다.
```

* **예제**

  BeepThread.java(비프음을 들려주는 스레드)

  ```java
  package thread_down_class_production;
  
  import java.awt.*;
  
  public class BeepThread extends Thread {	// Thread 클래스를 상속한다.
      @Override
      public void run() {
          Toolkit toolkit = Toolkit.getDefaultToolkit();
          for(int i=0; i<5; i++) {
              toolkit.beep();
              try { Thread.sleep(500); } catch (Exception e) {}
          }
      }
  }
  ```

  BeepPrintExample.java

  ```java
  package thread_down_class_production;
  
  public class BeepPrintExample {
      public static void main(String[] args) {
          Thread thread = new BeepThread();	// 스레드 생성
          thread.start();		// 스레드 실행
  
          for(int i=0; i<5; i++) {
              System.out.println("띵");
              try { Thread.sleep(500); } catch (Exception e) {}
          }
      }
  }
  ```

<br>

### 12.2.3 스레드의 이름

: 우리가 직접 생성한 스레드는 자동적으로 "Thread-n"이라는 이름으로 설정된다. n은 스레드의 번호를 말한다. Thread 클래스의 **setName() 메소드**를 사용하면 스레드의 이름을 설정할 수 있다. 반대로 스레드의 이름을 알고 싶을 경우에는 **getName() 메소드**를 호출하면 된다. 만약 스레드의 객체의 참조를 가지고 있지 않다면, Thread의 정적 메소드인 currentThread)로 코드를 실행하면 참조를 얻을 수 있다.

* **예제(메인 스레드 이름 출력 및 UserThread 생성 및 시작)**

  ThreadA.java(스레드 A)

  ```java
  package thread_name;
  
  public class ThreadA extends Thread {
      public ThreadA() {
          setName("ThreadA");     // 스레드 이름 설정
      }
  
      public void run() {
          for(int i=0; i<2; i++) {
              System.out.println(getName() + " 가 출력한 내용");
          }
      }
  }
  ```

  <br>

  ThreadB.java(스레드 B)

  ```java
  package thread_name;
  
  public class ThreadB extends Thread {
      // 스레드 이름 설정 안함
      @Override
      public void run() {
          for(int i=0; i<2; i++) {
              System.out.println(getName() + " 가 출력한 내용");
          }
      }
  }
  ```

  <br>

  ThreadNameExample.java(main 스레드)

  ```java
  package thread_name;
  
  public class ThreadNameExample {
      public static void main(String[] args) {
          Thread mainThread = Thread.currentThread();
          System.out.println("프로그램 시작 스레드 이름: " + mainThread.getName());
  
          ThreadA threadA = new ThreadA();
          System.out.println("작업 스레드 이름: " + threadA.getName());
          threadA.start();
  
          ThreadB threadB = new ThreadB();
          System.out.println("작업 스레드 이름: " + threadB.getName());
          threadB.start();
      }
  }
  ```

  **실행 결과**

  ```
  프로그램 시작 스레드 이름: main
  작업 스레드 이름: ThreadA
  작업 스레드 이름: Thread-1
  Thread-1 가 출력한 내용
  Thread-1 가 출력한 내용
  ThreadA 가 출력한 내용
  ThreadA 가 출력한 내용
  ```

  <br>

## 12.3 스레드 우선순위

: 멀티 스레드는 동시성(Concurrency) 또는 병렬성(Parallelism)으로 실행된다.

* **동시성(Concurrency)** : 멀티 작업을 위해 하나의 코어에서 멀티 스레드가 번갈아가며 실행하는 성질

* **병렬성(Parallelism)** : 멀티 작업을 위해 멀티 코어에서 개별 스레드를 동시에 실행하는 성질

  ![1548149222889](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548149222889.png)

* **스레드 스케줄링** : 스레드를 어떤 순서에 의해 동시성으로 실행할 것인가를 결정하는 것

  * **우선순위(Priority) 방식** : 우선순위가 높은 스레드가 실행 상태를 더 많이 가지도록 스케줄링하는 방식
  * **순환 할당 방식(Round-Robin)** : 시간 할당량(Time Slice)을 정해서 하나의 스레드를 정해진 시간만큼 실행하고 다시 다른 스레드를 실행하는 방식

* **우선순위 방식**

  : 우선순위는 1에서부터 10까지 부여가 된다. 우선순위를 부여하지 않으면 모든 스레드들은 기본적으로 5의 우선순위를 할당받는다. 만약 우선순위를 변경하고 싶으면 setPriority() 메소드를 이용하면 된다.

<br>

## 12.4 동기화 메소드와 동기화 블록

### 12.4.1 공유 객체를 사용할 때의 주의할 점

: 멀티 스레드의 스레드들이 객체를 공유해서 작업해야 하는 경우에 스레드 A를 사용하던 객체가 스레드 B에 의해 상태가 변경될 수 있기 때문에 주의해야 한다.

* 예제

  **Calculator.java(공유 객체)**

  ```java
  package careful_shared_object;
  
  public class Calculator {
      private int memory;
  
      public int getMemory() {
          return memory;
      }
  
      // 계산기 메모리에 값을 저장하는 메소드
      public void setMemory(int memory) {
          // 매개값을 memory 필드에 저장
          this.memory = memory;
  
          try {
              // 스레드를 2초간 일시 정지
              Thread.sleep(2000);
          } catch(InterruptedException e) {}
          System.out.println(Thread.currentThread().getName() + ": " + this.memory);
      }
  }
  ```

  **User1.java(User1 스레드)**

  ```java
  package careful_shared_object;
  
  public class User1 extends Thread{
      private Calculator calculator;
  
      public void setCalculator(Calculator calculator) {
          this.setName("User1");          // 스레드 이름 User1로 설정
          this.calculator = calculator;   // 공유 객체인 Calculator 을 필드에 저장
      }
  
      @Override
      public void run() {
          calculator.setMemory(100);      // 공유 객체인 Calculator 의 메모리에 100을 저장
      }
  }
  ```

  **User2.java(User2 스레드)**

  ```java
  package careful_shared_object;
  
  public class User2 extends Thread {
      private Calculator calculator;
  
      public void setCalculator(Calculator calculator) {
          this.setName("User2");          // 스레드 이름 User2로 저장
          this.calculator = calculator;   // 공유 객체인 Calculator 을 필드에 저장
      }
  
      @Override
      public void run() {
          calculator.setMemory(50);       // 공유 객체인 Calculator 의 메모리에 50을 저장
      }
  }
  ```

  **MainThreadExample.java(메인 스레드가 실행하는 코드)**

  ```java
  package careful_shared_object;
  
  public class MainThreadExample {
      public static void main(String[] args) {
          Calculator calculator = new Calculator();
  
          User1 user1 = new User1();
          user1.setCalculator(calculator);
          user1.start();	// user1 스레드 실행
  
          User2 user2 = new User2();
          user2.setCalculator(calculator);
          user2.start();	// user2 스레드 실행
      }
  }
  ```

  **실행 결과**

  ```
  User1: 50
  User2: 50
  ```

  > user1은 100, user2은 50 으로 저장되어야 했으나, 둘다 50으로 저장되어 있는 오류를 볼 수 있다.

<br>

### 12.4.2 동기화 메소드 및 동기화 블록

: 멀티 스레드 프로그램에서 단 하나의 스레드만 실행할 수 있는 코드 영역을 **임계 영역(critical section)**이라고 한다. 임계 영역을 지정하기 위해 **동기화(synchronized) 메소드와 동기화 블록**을 사용한다. 동기화 메소드를 만드는 방법은 메소드 선언에 **synchronized 키워드**를 붙이면 된다.

```java
public synchronized void method() {
    임계 영역;	// 단 하나의 스레드만 실행
}
```

* 일부 내용만 임계 영역으로 만들고 싶다면 **동기화(synchronized) 블록**을 만들면 된다.

  ```java
  public void method() {
      // 여러 스레드가 실행 가능 영역
      ...
          synchronized(공유객체) {
          임계 영역;	// 단 하나의 스레드만 실행
      }
      // 여러 스레드가 실행 가능 영역
      ...
  }
  ```

<br>

## 12.5 스레드 상태

1. **실행 대기 상태** : 아직 스케줄링이 되지 않아서 실행을 기다리고 있는 상태

2. **실행(Running) 상태** : 스케줄링으로 선택된 스레드가 비로서 CPU를 점유하고 run() 메소드를 실행

3. **종료 상태** : 실행 상태에서 run() 메소드가 종료되면, 더 이상 실행할 코드가 없기 때문에 스레드의 실행이 멈춤

   ![1548153100811](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548153100811.png)

<br>

* **일시 정지 상태** : 스레드가 실행할 수 없는 상태. WAITINGS, TIMED_WAITING, BLOCKED가 있다. 스레드가 다시 실행 상태로 가기 위해서는 일시 정지 상태에서 실행 대기 상태로 가야한다.

  ![1548153105997](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548153105997.png)

<br>

: 이러한 스레드의 상태를 코드에서 확인하기 위해 getState() 메소드로 호출하면 된다. 스레드 상태에 따라서 Thread.State 열거 상수를 리턴한다.

* **Thread.State 열거 상수**

  | 상태      | 열거 상수                               | 설명                                                         |
  | --------- | --------------------------------------- | ------------------------------------------------------------ |
  | 객체 생성 | NEW                                     | 아직 start() 메소드가 호출되지 않은 상태                     |
  | 실행 대기 | RUNNABLE                                | 실행 상태로 언제든지 갈 수 있는 상태                         |
  | 일시 정지 | WAITING<br />TIMED_WAITING<br />BLOCKED | 다른 스레드가 통지할 때까지 기다리는 상태<br />주어진 시간 동안 기다리는 상태<br />사용하고자 하는 객체의 락이 풀릴 때까지 기다리는 상태 |
  | 종료      | TERMINATED                              | 실행을 마친 상태                                             |

  <br>

* **예제**

  StatePrintThread.java(**타겟 스레드의 상태를 출력하는 스레드**)

  ```java
  package thread_state;
  
  public class StatePrintThread extends Thread{
      private Thread targetThread;
  
      // targetThread: 상태를 조사할 스레드
      public StatePrintThread(Thread targetThread) {
          this.targetThread = targetThread;
      }
  
      public void run() {
          while(true) {
              // 스레드 상태 얻기
              Thread.State state = targetThread.getState();
              System.out.println("타겟 스레드 상태: " + state);
  
              // 객체 생성 상태일 경우 실행 대기 상태로 만듬
              if(state == Thread.State.NEW) {
                  targetThread.start();
              }
  
              // 종료 상태일 경우 while문을 종료함
              if(state == Thread.State.TERMINATED) {
                  break;
              }
  
              try {
                  Thread.sleep(500);
              } catch (Exception e) {}
          }
      }
  }
  ```

  TargetThread.java(**타켓 스레드**)

  ```java
  package thread_state;
  
  public class TargetThread extends Thread{
      @Override
      public void run() {
          for(long i=0; i<1000000000; i++){}
  
          try {
              Thread.sleep(1500);
          } catch (Exception e) {}
  
          for(long i=0; i<1000000000; i++) {}
      }
  }
  ```

  ThreadStateExample.java(**실행 클래스**)

  ```java
  package thread_state;
  
  public class ThreadStateExample {
      public static void main(String[] args) {
          StatePrintThread statePrintThread =
                  new StatePrintThread(new TargetThread());
          statePrintThread.start();
      }
  }
  ```

<br>

## 12.6 스레드 상태 제어

: 스레드의 상태를 변경하는 것을 스레드 상태 제어라고 한다. 스레드 제어를 제대로 하기 위해서는 스레드의 상태 변화를 가져오는 메소드들을 파악하고 있어야 한다.

![1548157357693](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548157357693.png)

> 위 그림에서 취소선을 가진 메소드는 스레드의 안정성을 해친다고 하여 더 이상 사용하지 않도록 권장된 메소드들이다.

* **스레드의 상태 변화 메소드**

  | 메소드                                                       | 설명                                                         |
  | ------------------------------------------------------------ | ------------------------------------------------------------ |
  | interrupt()                                                  | 일시 정지 상태의 스레드에서 InterruptedException 예외를 발생시켜, 예외 처리 코드(catch)에서 실행 대기 상태로 가거나 종료 상태로 갈 수 있도록 한다. |
  | notify()<br />notifyAll()                                    | 동기화 블록 내에서 wait() 메소드에 의해 일시 정지 상태에 있는 스레드를 실행 대기 상태로 만든다. |
  | sleep(long millis)<br />sleep(long millis, int nanos)        | 주어진 시간 동안 스레드를 일시 정지 상태로 만든다. 주어진 시간이 지나면 자동적으로 실행 대기 상태가 된다. |
  | join()<br />join(long millis)<br />join(long millis, int nanos) | join() 메소드를 호출한 스레드는 일시 정지 상태가 된다. 실행 대기 상태로 가려면, join() 메소드를 멤버로 가지는 스레드가 종료되거나, 매개값으로 주어진 시간이 지나야 한다. |
  | wait()<br />wait(long millis)<br />wait(long millis, int nanos) | 동기화(synchronized) 블록 내에서 스레드를 일시 정지 상태로 만든다. 매개값으로 주어진 시간이 지나면 자동적으로 실행 대기 상태가 된다. 시간이 주어지지 않으면 notify(), notifyAll() 메소드에 의해 실행 대기 상태로 갈 수 있다. |
  | yield()                                                      | 실행 중에 우선순위가 동일한 다른 스레드에게 실행을 양보하고 실행 대기 상태가 된다. |

<br>

### 12.6.1 주어진 시간동안 일시 정지(sleep())

: 이 메소드를 호출한 스레드는 주어진 시간 동안 일시 정지 상태가 되고, 다시 실행 대기 상태로 돌아간다.

```java
try {
    Thread.sleep(1000);
} catch(InterruptedException e) {
    // interrupt() 메소드가 호출되면 실행
}
```

> 일시 정지 상태에서 주어진 시간이 되기 전에 interrupt() 메소드가 호출되면 InterruptedException이 발생하기 때문에 예외 처리가 필요하다.

<br>

### 12.6.2 다른 스레드에게 실행 양보(yield())

: 다른 스레드에게 실행을 양보하고 자신은 실행 대기 상태로 가는 메소드. 이 메소드를 호출한 스레드는 실행 대기 상태로 돌아가고 동일한 우선순위 또는 높은 우선순위를 갖는 다른 스레드가 실행 기회를 가질 수 있도록 한다.

```java
public void run() {
    while(ture) {
        if(work) {	// 만약 work의 값이 false이면
            System.out.println("ThreadA 작업 내용");
        } else {	// while문을 실행 대기 상태로 바꿔준다.
            Thread.yield();
        }
    }
}
```

<br>

### 12.6.3 다른 스레드의 종료를 기다림(join())

: 예를 들어 계산 작업을 하는 스레드가 모든 계산 작업을 마쳤을 때, 계산 결과값을 받아 이용하는 경우가 이에 해당된다.

* 예제

  **SumThread.java(1부터 100까지 합을 계산하는 스레드)**

  ```java
  package thread_state_control.join_method;
  
  public class SumThread extends Thread {
      private long sum;
  
      public long getSum() {
          return sum;
      }
  
      public void setSum(long sum) {
          this.sum = sum;
      }
  
      public void run() {
          for(int i=1; i<=100; i++) {
              sum += i;
          }
      }
  }
  ```

  **JoinExample.java(다른 스레드가 종료될 때까지 일시 정지 상태 유지)**

  ```java
  package thread_state_control.join_method;
  
  public class JoinExample {
      public static void main(String[] args) {
          SumThread sumThread = new SumThread();
          sumThread.start();
  
          try {
              // sumThread가 종료할 때까지 메인 스레드를 일시 정지 시킴
              sumThread.join();
          } catch (InterruptedException e) { }
  
          System.out.println("1~100 합: " + sumThread.getSum());
      }
  }
  ```

<br>

### 12.6.4 스레드 간 협업(wait(), notify(), notifyAll())

: 공유 객체는 두 스레드가 작업할 내용을 각각 동기화 메소드로 구분해 놓는다. 한 스레드가 작업을 완료하면 **notify() 메소드**를 호출해서 일시 정지 상태에 있는 다른 스레드를 실행 대기 상태로 만들고, 자신은 두 번 작업을 하지 않도록 **wait() 메소드**를 호출하여 일시 정지 상태로 만든다.