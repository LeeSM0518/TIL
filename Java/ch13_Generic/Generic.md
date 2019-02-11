# Chapter 13. 제네릭

## 13.1 왜 제네릭을 사용해야 하는가?

* **제네릭** : 클래스와 인터페이스, 그리고 메소드를 정의할 때 타입(type)을 파라미터(parameter)로 사용할 수 있도록 한다.
* **제네릭의 이점**
  * 컴파일 시 강한 타입 체크를 할 수 있다.
  * 타입 변환(casting)을 하지 않는다.



## 13.2 제네릭 타입(class\<T>, interface\<T>)

* **선언 예시**

  ```java
  public class 클래스명<T> { ... }
  public interface 인터페이스명<T> { ... }
  ```

  > 제네릭 타입을 실제 코드에서 사용하려면 타입 파라미터에 구체적인 타입을 지정해야 한다.

* **비제네릭 타입 예제**

  Box.java(Box 클래스)

  ```java
  package generic_type;
  
  public class Box {
      private Object object;
      public void set(Object object) {
          this.object = object;
      }
  
      public Object getObject() {
          return object;
      }
  }
  ```

  Apple.java(Apple 클래스)

  ```java
  package generic_type;
  
  public class Apple {
  }
  ```

  BoxExample.java(비제니릭 타입 이용)

  ```java
  package generic_type;
  
  public class BoxExample {
      public static void main(String[] args) {
          Box box = new Box();
  
          // String -> Object (자동 타입 변환)
          box.set("홍길동");
  
          // Object -> String (강제 타입 변환)
          String name = (String) box.getObject();
  
          // Apple -> Object (자동 타입 변환)
          box.set(new Apple());
  
          // Object -> Apple (강제 타입 변환)
          Apple apple = (Apple) box.getObject();
      }
  }
  ```

* **비제네릭을 제네릭으로 수정 예제**

  Box.java(Box 클래스를 제네릭으로 수정)

  ```java
  package generic_type;
  
  public class Box<T> {
      private T t;
      public void set(T t) {
          this.t = t;
      }
  
      public T get() {
          return t;
      }
  }
  
  // 만약 아래와 같이 Box 객체를 생성하게 된다면
  Box<String> box = new Box<String>();
  
  // Box 클래스의 내부는 아래와 같이 재구성 된다.
  public class Box<String> {
      private String t;
      public void set(String t) {
          this.t = t;
      }
      public String get() {
          return t;
      }
  }
  ```



## 13.3 멀티 타입 파라미터(class<K,V,...>, interface<K,V,...>)

: 제네릭 타입은 두 개 이상의 멀티 타입 파라미터를 사용할 수 있다.

* **예제**

  Product.java(**제네릭 클래스**)

  ```java
  package multi_type_parameter;
  
  // 멀티 타입 파라미터로 T와 M을 갖는 제네릭 클래스 선언
  public class Product<T, M> {
      private T kind;
      private M model;
  
      public T getKind() {
          return this.kind;
      }
      public M getModel() {
          return this.model;
      }
  
      public void setKind(T kind) {
          this.kind = kind;
      }
      public void setModel(M model) {
          this.model = model;
      }
  }
  ```

  ProductExample.java(**제네릭 객체 생성**)

  ```java
  package multi_type_parameter;
  
  public class ProductExample {
      public static void main(String[] args) {
          Product<Tv, String> product1 = new Product<Tv, String>();
          product1.setKind(new Tv());
          product1.setModel("스마트 Tv");
          Tv tv = product1.getKind();
          String tvModel = product1.getModel();
  
          Product<Car, String> product2 = new Product<Car, String>();
          product2.setKind(new Car());
          product2.setModel("디젤");
          Car car = product2.getKind();
          String carModel = product2.getModel();
      }
  }
  ```



## 13.4 제네릭 메소드(<T, R> R method(T t))

: 매개 타입과 리턴 타입으로 타입 파라미터를 갖는 메소드

* **선언 예시**

  ```java
  public <타입파라미터, ...> 리턴타입 메소드명(매개변수,...) { ... }
  
  // 타입 파라미터 T
  // 리턴 타입 Box<T>
  // 매개 변수 타입 T
  
  public <T> Box<T> boxing(T t) { ... }
  ```

* **호출 예시**

  ```java
  리턴타입 변수 = <구체적타입> 메소드명(매개값);	// 명시적으로 구체적 타입을 지정
  리턴타입 변수 = 메소드명(매개값);			  	// 매개값을 보고 구체적 타입을 추정
  
  Box<Integer> box = <Integer>boxing(100);	// 타입 파라미터를 명시적으로 지정
  Box<Integer> box = boxing(100);				// 타입 파라미터를 추정
  ```

* **예제**

  Util.java(**제네릭 메소드를 가진 클래스**)

  ```java
  package generic_method;
  
  import generic_type.Box;
  
  public class Util {
      public static <T> Box<T> boxing(T t) {
          Box<T> box = new Box<T>();
          box.set(t);
          return box;
      }
  }
  ```

  BoxingMethodExample.java(**제네릭 메소드 호출**)

  ```java
  package generic_method;
  
  import generic_type.Box;
  
  public class BoxingMethodExample {
      public static void main(String[] args) {
          Box<Integer> box1 = Util.<Integer>boxing(100);
          int intValue = box1.get();
  
          Box<String> box2 = Util.boxing("홍길동");
          String strValue = box2.get();
      }
  }
  ```



## 13.5 제한된 타입 파라미터(\<T extends 최상위타입>)

: 타입 파라미터에 지정되는 구체적인 타입을 제한할 필요가 종종 있다. 예를 들어 숫자에 대한 타입에 String 타입이 올 수 없게 한다.

* **선언**

  ```java
  // 타입 파라미터에 지정되는 구체적인 타입은 상위 타입이거나 상위 타입의 하위 또는
  // 구현 클래스만 가능
  public <T extends 상위타입> 리턴타입 메소드(매개변수, ...) { ... }
  ```

  > 상위 타입은 클래스뿐만 아니라 인터페이스도 가능하다. 인터페이스라고 해서 implements를 사용하지 않는다.
  >
  > 주의할 점은 메소드의 중괄호 {} 안에서 타입 파라미터 변수로 사용 가능한 것은 상위 타입의 멤버(필드, 메소드)로 제한된다. 하위 타입에만 있는 필드와 메소드는 사용할 수 없다.

* **예시**

  ```java
  public <T extends Number> int compare(T t1, T t2) {
      double v1 = t1.doubleValue();	// Number의 doubleValue() 메소드 사용
      double v2 = t2.doubleValue();	// Number의 doubleValue() 메소드 사용
      return Double.compare(v1, v2);
  }
  ```

  > doubleValue() 메소드는 숫자를 double 타입으로 변환한다. Double.compare() 메소드는 첫 번째 매개값이 작으면 -1을, 같으면 0을 크면 1을 리턴한다.



## 13.6 와일드카드 타입(<?>, <? extends ...>, <? super ...>)

: 코드에서 ?를 일반적으로 **와일드카드(wildcard)**라고 부른다.

* **와일드카드의 세 가지 형태**

  * **제네릭타입<?>** : Unbounded Wildcards(제한 없음)

    타입 파라미터를 대치하는 구체적인 타입으로 모든 클래스나 인터페이스 타입이 올 수 있다.

  * **제네릭타입<? extends 상위타입>** : Upper Bounded Wildcards(상위 클래스 제한)

    타입 파라미터를 대치하는 구체적인 타입으로 상위 타입이나 하위 타입으로 올 수 있다.

  * **제네릭타입<? super 하위타입>** : Lower Bounded Wildcard(하위 클래스 제한)

    타입 파라미터를 대치하는 구체적인 타입으로 하위 타입이나 상위 타입이 올 수 있다.

* **예제**

  Person.java(**상위 클래스**)

  ```java
  package whildcard_type;
  
  public class Person {
      private String name;
  
      public Person(String name) {
          this.name = name;
      }
  
      public String getName() {
          return name;
      }
  }
  ```

  Worker.java(**하위 클래스**)

  ```java
  package whildcard_type;
  
  public class Worker extends Person{
      private String name;
  
      public Worker(String name) {
          super(name);
          this.name = name;
      }
  
      @Override
      public String getName() {
          return name;
      }
  }
  ```

  Student.java(**하위 클래스**)

  ```java
  package whildcard_type;
  
  public class Student extends Person {
      private String name;
  
      public Student(String name) {
          super(name);
          this.name = name;
      }
  
      @Override
      public String getName() {
          return name;
      }
  }
  ```

  HighStudent.java(**Student 하위 클래스**)

  ```java
  package whildcard_type;
  
  public class HighStudent extends Student{
      private String name;
  
      public HighStudent(String name) {
          super(name);
          this.name = name;
      }
  
      public String getName() {
          return name;
      }
  }
  ```

  Course.java(**제네릭 타입**)

  ```java
  package whildcard_type;
  
  public class Course<T> {
      private String name;
      private T[] students;
  
      public Course(String name, int capacity) {
          this.name = name;
          // 타입 파라미터로 배열을 생성하려면
          // new T[n] 형태로 배열을 생성할 수 없고
          // (T[]) (new Object[n]) 으로
          // 생성해야 한다.
          students = (T[]) (new Object[capacity]);
      }
  
      public String getName() { return name; }
      public T[] getStudents() { return students; }
  
      public void add(T t) {
          // 배열에 비어있는 부분을 찾아서
          // 수강생을 추가하는 메소드
          for(int i=0; i<students.length; i++) {
              if(students[i] == null) {
                  students[i] = t;
                  break;
              }
          }
      }
  }
  ```

  >**Course<?>**
  >
  >: 수강생은 모든 타입(Person, Worker, Student, HighStudent)이 될 수 있다.
  >
  >**Course<? extends Student>**
  >
  >: 수강생은 Student와 HighStudent만 될 수 있다.
  >
  >**Course<? super Worker>**
  >
  >: 수강생은 Worker와 Person만 될 수 있다.

  WildCardExample.java(**와일드카드 타입 매개 변수**)

  ```java
  package whildcard_type;
  
  import java.util.Arrays;
  
  public class WildCardExample {
      // 모든 과정
      public static void registerCourse(Course<?> course) {
          System.out.println(course.getName() + " 수강생: " +
                  Arrays.toString(course.getStudents()));
      }
  
      // 학생 과정
      public static void registerCourseStudent(Course<? extends Student> course) {
          System.out.println(course.getName() + " 수강생: " +
                  Arrays.toString(course.getStudents()));
      }
  
      // 직장인과 일반인 과정
      public static void registerCourseWorker(Course<? super Worker> course) {
          System.out.println(course.getName() + " 수강생: " +
                  Arrays.toString(course.getStudents()));
      }
  
      public static void main(String[] args) {
          Course<Person> personCourse = new Course<Person>("일반인과정", 5);
          personCourse.add(new Person("일반인"));
          personCourse.add(new Worker("직장인"));
          personCourse.add(new Student("학생"));
          personCourse.add(new HighStudent("고등학생"));
          Course<Worker> workerCourse = new Course<Worker>("직장인과정", 5);
          workerCourse.add(new Worker("직장인"));
          Course<Student> studentCourse = new Course<Student>("학생과정", 5);
          studentCourse.add(new Student("학생"));
          studentCourse.add(new HighStudent("고등학생"));
          Course<HighStudent> highStudentCourse =
                  new Course<HighStudent>("고등학생과정", 5);
          highStudentCourse.add(new HighStudent("고등학생"));
  
          registerCourse(personCourse);
          registerCourse(workerCourse);
          registerCourse(studentCourse);
          registerCourse(highStudentCourse);
          System.out.println();
  
  //        registerCourseStudent(personCourse);
  //        registerCourseStudent(workerCourse);
          registerCourseStudent(studentCourse);
          registerCourseStudent(highStudentCourse);
          System.out.println();
  
          registerCourseWorker(personCourse);
          registerCourseWorker(workerCourse);
  //        registerCourseWorker(studentCourse);
  //        registerCourseWorker(highStudentCourse);
      }
  }
  ```



## 13.7 제네릭 타입의 상속과 구현

: 제네릭 타입도 다른 타입과 마찬가지로 부모 클래스가 될 수 있다.

* **선언 예시**

  ```java
  // Product<T,M> 제네릭 타입을 상속해서 ChildProduct<T,M> 타입을 정의
  public class ChildProduct<T, M> extends Product<T,M> { ... }
  ```

  **세 가지 타입 파라미터를 가진 자식 제네릭 타입**

  ```java
  public class ChildProduct<T, M, C> extends Product<T, M> { ... }
  ```



# 확인문제

1. 제네릭에 대한 설명으로 틀린 것은 무엇입니까?
   1. 컴파일 시 강한 타입 체크를 할 수 있다.
   2. 타입 변환(casting)을 제거한다.
   3. 제네릭 타입은 타입 파라미터를 가지는 제네릭 클래스와 인터페이스를 말한다.
   4. **제네릭 메소드는 리턴 타입으로 타입 파라미터를 가질 수 없다.(X, 가질 수 있다)**

2. ContainerExample 클래스의 main() 메소드는 Container 제네릭 타입을 사용하고 있습니다. main() 메소드에서 사용하는 방법을 참고해서 Container 제네릭 타입을 선언해보세요.

   **ContainerExample.java(제네릭 타입 이용)**

   ```java
   public class ContainerExample {
       public static void main(String[] args) {
           Container<String> container1 = new Container<String>();
           container1.set("홍길동");
           String str = container1.get();
   
           Container<Integer> container2 = new Container<Integer>();
           container2.set(6);
           int value = container2.get();
       }
   }
   ```

   **Container**

   ```java
   class Container<T> {
       T t;
   
       public void set(T t) {
           this.t = t;
       }
   
       public T get() {
           return t;
       }
   }
   ```

3. ContainerExample 클래스의 main() 메소드는 Container 제네릭 타입을 사용하고 있습니다. main() 메소드에서 사용하는 방법을 참고해서 Container 제네릭 타입을 선언해보세요.

   **ContainerExample.java(제네릭 타입 이용)**

   ```java
   public class ContainerExample2 {
       public static void main(String[] args) {
           Container1<String, String> container1 = new Container1<String, String>();
           container1.set("홍길동", "도적");
           String name1 = container1.getKey();
           String jon = container1.getValue();
           
           Container1<String, Integer> container2 = new Container1<String, Integer>();
           container2.set("홍길동", 35);
           String name2 = container2.getKey();
           int age = container2.getValue();
       }
   }
   ```

   **Container**

   ```java
   class Container1<T, M> {
       T t;
       M m;
   
       public void set(T t, M m) {
           this.t = t;
           this.m = m;
       }
   
       public T getKey() {
           return t;
       }
   
       public M getValue() {
           return m;
       }
   }
   ```

4. Util.getValue() 메소드는 첫 번째 매개값으로 Pair 타입과 하위 타입만 받고, 두 번째 매개값으로 키값을 받습니다. 리턴값은 키값이 일치할 경우 Pair에 저장된 값을 리턴하고, 일치하지 않으면 null을 리턴하도록 getValue() 제네릭 메소드를 정의해보세요.

   **UtilExample.java(제네릭 메소드 호출)**

   ```java
   
   ```

   질문!