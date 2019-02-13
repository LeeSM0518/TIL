# Chapter 15. 컬렉션 프레임워크

## 15.1 컬렉션 프레임워크 소개

: 애플리케이션을 개발하다 보면 다수의 객체를 저장해 두고 필요할 때마다 꺼내서 사용하는 경우가 많다. 가장 간단한 방법은 배열을 이용하는 것이다.

* **예시**

  ```java
  // 길이 10인 배열 생성
  Product[] array = new Product[10];
  // 객체 추가
  array[0] = new Product("Model1");
  array[1] = new Product("Model2");
  // 객체 검색
  Product model1 = array[0];
  Product model2 = array[1];
  // 객체 삭제
  array[0] = null;
  array[1] = null;
  ```

* **컬렉션 프레임워크(Collection Framework)** : 객체들을 효율적으로 추가, 삭제, 검색할 수 있도록 java.util 패키지에 컬렉션과 관련된 인터페이스와 클래스들을 포함시켜 놓았다.

  ![1550061731282](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1550061731282.png)

* **컬렉션 정리**

  | 인터페이스 분류 |      | 특징                                                | 구현 클래스                             |
  | --------------- | ---- | --------------------------------------------------- | --------------------------------------- |
  | Collection      | List | - 순서를 유지하고 저장<br />- 중복 저장 가능        | ArrayList, Vector, LinkedList           |
  |                 | Set  | - 순서를 유지하지 않고 저장<br />- 중복 저장 안 됨  | HashSet, TreeSet                        |
  | Map             |      | - 키와 값의 쌍으로 저장<br />- 키는 중복 저장 안 됨 | HashMap, Hashtable, TreeMap, Properties |



## 15.2 List 컬렉션

: 객체를 일렬로 늘어놓은 구조를 가지고 있다. 인덱스로 객체를 검색, 삭제할 수 있는 기능을 제공한다. 그리고 객체 자체를 저장하는 것이 아니라 객체의 번지를 참조한다.

![1550061739111](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1550061739111.png)



* **List 인터페이스의 메소드들**

  |   기능    | 메소드                         | 설명                                             |
  | :-------: | ------------------------------ | ------------------------------------------------ |
  | 객체 추가 | boolean add(E e)               | 주어진 객체를 맨 끝에 추가                       |
  |           | void add(int index, E element) | 주어진 인덱스에 객체를 추가                      |
  |           | E set(int index, E element)    | 주어진 인덱스에 저장된 객체를 주어진 객체로 바꿈 |
  | 객체 검색 | boolean contains(Object o)     | 주어진 객체가 저장되어 있는지 여부               |
  |           | E get(int index)               | 주어진 인덱스에 저장된 객체를 리턴               |
  |           | boolean isEmpty()              | 컬렉션이 비어 있는지 조사                        |
  |           | int size()                     | 저장되어 있는 전체 객체 수를 리턴                |
  | 객체 삭제 | void clear()                   | 저장된 모든 객체를 삭제                          |
  |           | E remove(int index)            | 주어진 인덱스에 저장된 객체를 삭제               |
  |           | boolean remove(Object o)       | 주어진 객체를 삭제                               |

* **예시**

  ```java
  List<String> list = ...;	
  list.add("홍길동");			// 맨끝에 객체 추가
  list.add(1, "신용권");			// 지정된 인덱스에 객체 삽입
  String str = list.get(1);	  // 인덱스로 객체 찾기
  list.remove(0);				  // 인덱스로 객체 삭제
  list.remove("신용권");			// 객체 삭제
  
  // for문 사용
  for(int i=0; i<list.size(); i++) {
      String str = list.get(i);
  }
  for(String str : list) {
  }
  ```



### 15.2.1 ArrayList

: List 인터페이스의 구현 클래스로, 객체가 인덱스로 관리된다. 배열은 생성할 때 크기가 고정되고 사용 중에 크기를 변경할 수 없지만, ArrayList는 저장 용량(capacity)을 초과하면 자동으로 늘어난다.

* **예시**

  ```java
  // ArrayList 생성
  // 기본 생성자로 객체를 생성하면 10개의 초기 용량(capacity)을 가진다.
  List<String> list = new ArrayList<String>();
  
  // String 객체를 30개를 저장할 수 있는 list
  List<String> list = new ArrayList<String>(30);
  ```

  > ArrayList에 객체를 추가하면 인덱스 0부터 차례대로 저장된다.
  >
  > 객체를 삽입하면 해당 인덱스부터 마지막 인덱스까지 모두 1씩 밀려난다. 객체를 제거하면 바로 뒤 인덱스부터 마지막 인덱스까지 모두 앞으로 1씩 당겨진다.
  >
  > 빈번한 객체 삭제와 십입이 일어나면 **LinkedList**를 사용하고 인덱스 검색이나, 맨 마지막에 객체를 추가하는 경우에는 **ArrayList**가 적절하다.

* **예제**

  ```java
  package arraylist;
  
  import java.util.ArrayList;
  import java.util.List;
  
  public class ArrayListExample {
      public static void main(String[] args) {
          List<String> list = new ArrayList<String>();
  
          // String 객체 5개 저장
          list.add("Java");
          list.add("JDBC");
          list.add("Servlet/JSP");
          list.add(2, "Database");
          list.add("iBATIS");
  
          // 저장된 총 객체 수 얻기
          int size = list.size();
          System.out.println("총 객체수 : " + size);
          System.out.println();
  
          // 2번 인덱스의 객체 얻기
          String skill = list.get(2);
          System.out.println("2: " + skill);
          System.out.println();
  
          // 저장된 총 객체 수만큼 루핑
          for(int i=0; i<list.size(); i++) {
              String str = list.get(i);
              System.out.println(i + ":" + str);
          }
          System.out.println();
  
          // 2번 인덱스 객체(Database) 삭제됨
          // 2번 인덱스 객체(Servlet/JSP) 삭제됨
          list.remove(2);
          list.remove(2);
          list.remove("iBATIS");
  
          // 저장된 총 객체 수만큼 루핑
          for(int i=0; i<list.size(); i++) {
              String str = list.get(i);
              System.out.println(i + ":" + str);
          }
      }
  }
  ```

  **실행 결과**

  ```
  총 객체수 : 5
  
  2: Database
  
  0:Java
  1:JDBC
  2:Database
  3:Servlet/JSP
  4:iBATIS
  
  0:Java
  1:JDBC
  ```

  > 런타임 시 필요에 의해 객체들을 추가하는 것이 일반적이지만, 고정된 객체들로 구성된 List를 생성할 때도 있다.이런 경우에는 Arrays.asList(T...a) 메소드를 사용하는 것이 간편하다.

* **asList() 메소드 예제**

  ```java
  package arraylist;
  
  import java.util.Arrays;
  import java.util.List;
  
  public class ArrayAsListExample {
      public static void main(String[] args) {
          List<String> list1 = Arrays.asList("홍길동", "신용권", "김자바");
          for(String name : list1) {
              System.out.println(name);
          }
  
          List<Integer> list2 = Arrays.asList(1, 2, 3);
          for(int value : list2) {
              System.out.println(value);
          }
      }
  }
  ```



### 15.2.2 Vector

: Vector를 생성하기 위해서는 저장할 객체 타입을 타입 파라미터로 표기하고 기본 생성자를 호출하면 된다.

```java
List<E> list = new Vector<E>();
```

* **ArrayList와 다른점**

  : Vector는 동기화된(synchronized) 메소드로 구성되어 있기 때문에 하나의 스레드가 실행을 완료해야만 다른 스레드를 실행할 수 있다. 그래서 멀티 스레드 환경에서 안전하게 객체를 추가, 삭제할 수 있다.(**Thread Safe**)

* **예제**

  Board.java(**게시물 정보 객체**)

  ```java
  package vector;
  
  public class Board {
      String subject;
      String content;
      String writer;
  
      public Board(String subject, String content, String writer) {
          this.subject = subject;
          this.content = content;
          this.writer = writer;
      }
  }
  ```

  VectorExample.java(**Board 객체를 저장하는 Vector**)

  ```java
  package vector;
  
  import java.util.List;
  import java.util.Vector;
  
  public class VectorExample {
      public static void main(String[] args) {
          List<Board> list = new Vector<Board>();
  
          // Board 객체를 저장
          list.add(new Board("제목1", "내용1", "글쓴이1"));
          list.add(new Board("제목2", "내용2", "글쓴이2"));
          list.add(new Board("제목3", "내용3", "글쓴이3"));
          list.add(new Board("제목4", "내용4", "글쓴이4"));
          list.add(new Board("제목5", "내용5", "글쓴이5"));
  
          // 2번 인덱스 객체(제목3) 삭제(뒤의 인덱스는 1씩 앞으로 당겨짐)
          list.remove(2);
          // 3번 인덱스 객체(제목5) 삭제
          list.remove(3);
  
          for(int i=0; i<list.size(); i++) {
              Board board = list.get(i);
              System.out.println(board.subject + "\t" +
                      board.content + "\t" +
                      board.writer);
          }
      }
  }
  ```

  **실행 결과**

  ```
  제목1	내용1	글쓴이1
  제목2	내용2	글쓴이2
  제목4	내용4	글쓴이4
  ```



### 15.2.3 LinkedList

: ArrayList와 사용 방법은 똑같지만 내부 구조는 완전 다르다. LinkedList는 인접 참조를 링크해서 체인 처럼 관리한다. 특정 인덱스의 객체를 제거하면 앞뒤 링크만 병경되고 나머지 링크는 변경되지 않는다. 

* **LinkedList 생성**

  ```java
  List<E> list = new LinkedList<E>();
  ```

* **예제(ArrayList와 LinkedList의 실행 성능 비교)**

  ```java
  package linked_list;
  
  import java.util.ArrayList;
  import java.util.LinkedList;
  import java.util.List;
  
  public class LinkedListExample {
      public static void main(String[] args) {
          List<String> list1 = new ArrayList<String>();
          List<String> list2 = new LinkedList<String>();
  
          long startTime;
          long endTime;
  
          startTime = System.nanoTime();
          for(int i=0; i<10000; i++) {
              list1.add(0, String.valueOf(i));
          }
          endTime = System.nanoTime();
  
          System.out.println("ArrayList 걸린시간: " +
                  (endTime-startTime) + " ns");
  
          startTime = System.nanoTime();
          for(int i=0; i<10000; i++) {
              list2.add(0, String.valueOf(i));
          }
          endTime = System.nanoTime();
  
          System.out.println("LinkedList 걸린시간: " +
                  (endTime-startTime) + " ns");
      }
  }
  ```

  **실행 결과**

  ```
  ArrayList 걸린시간: 9391773 ns
  LinkedList 걸린시간: 3547017 ns
  ```



* **ArrayList와 LinkedList 비교**

  | 구분       | 순차적으로 추가/삭제 | 중간에 추가/삭제 | 검색   |
  | ---------- | -------------------- | ---------------- | ------ |
  | ArrayList  | 빠르다               | 느리다           | 빠르다 |
  | LinkedList | 느리다               | 빠르다           | 느리다 |



## 15.3 Set 컬렉션

: 저장 순서가 유지되지 않는다. 또한 객체를 중복해서 저장할 수 없고, 하나의 null만 저장할 수 있다.

![1550065831027](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1550065831027.png)

* **Set 인터페이스의 메소드들**

  | 기능      | 메소드                     | 설명                                                         |
  | --------- | -------------------------- | ------------------------------------------------------------ |
  | 객체 추가 | boolean add(E e)           | 주어진 객체를 저장, 객체가 성공적으로 저장되면 true를 리턴하고 중복 객체면 false를 리턴 |
  | 객체 검색 | boolean contains(Object o) | 주어진 객체가 저장되어 있는지 여부                           |
  |           | boolean isEmpty()          | 컬렉션이 비어 있는지 조사                                    |
  |           | Iterator\<E> iterator()    | 저장된 객체를 한 번씩 가져오는 반복자 리턴                   |
  |           | int size()                 | 저장되어 있는 전체 객체 수 리턴                              |
  | 객체 삭제 | void clear()               | 저장된 모든 객체를 삭제                                      |
  |           | boolean remove(Object o)   | 주어진 객체를 삭제                                           |

* **예시**

  ```java
  Set<String> set = ...;
  set.add("홍길동");		// 객체 추가
  set.add("신용권");
  set.remove("홍길동");	// 객체 삭제
  
  // 전체 객체를 대상으로 한번씩 반복해서 가져오는 반복자(Iterator)
  Set<String> set = ...;
  Iterator<String> iterator = set.iterator();
  ```

* **Iterator 인터페이스 메소드**

  | 리턴 타입 | 메소드명  | 설명                                                         |
  | --------- | --------- | ------------------------------------------------------------ |
  | boolean   | hasNext() | 가져올 객체가 있으면 true를 리턴하고 없으면 false를 리턴한다. |
  | E         | next()    | 컬렉션에서 하나의 객체를 가져온다.                           |
  | void      | remove()  | Set 컬렉션에서 객체를 제거한다.                              |

* **Iterator 사용 예시**

  ```java
  Set<String> set = ...;
  Iterator<String> iterator = set.iterator();
  // 저장된 객체 수만큼 루핑
  while(iterator.hasNext()) {
      // String 객체 하나를 가져옴
      String str = iterator.next();
  }
  
  // 향상된 for문 사용
  Set<String> set = ...;
  for(String str : set) {
      // 저장된 객체 수만큼 루핑
  }
  
  // 객체 제거
  while(iterator.hasNext()) {
      String str = iterator.next();
      if(str.equals("홍길동")) {
          iterator.remove();
      }
  }
  ```



### 15.3.1 HashSet

: Set 인터페이스의 구현 클래스이다.

* **HashSet 생성자**

  ```java
  Set<E> set = new HashSet<E>();
  
  // 예시
  Set<String> set = new HashSet<String>();
  ```

  ![1550067087186](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1550067087186.png)

* **예제(String 객체를 중복 없이 저장하는 HashSet)**

  ```java
  package hash_set;
  
  import java.util.HashSet;
  import java.util.Iterator;
  import java.util.Set;
  
  public class HashSetExample1 {
      public static void main(String[] args) {
          Set<String> set = new HashSet<String>();
  
          // "Java"는 한 번만 저장됨
          set.add("Java");
          set.add("JDBC");
          set.add("Servlet/JSP");
          set.add("Java");
          set.add("iBATIS");
  
          // 저장된 객체 수 얻기
          int size = set.size();
          System.out.println("총 객체수: " + size);
  
          // 반복자 얻기
          Iterator<String> iterator = set.iterator();
  
          // 객체 수만큼 루핑
          while(iterator.hasNext()) {
              // 한 개의 객체를 가져온다.
              String element = iterator.next();
              System.out.println("\t" + element);
          }
  
          // 두 개의 객체 삭제
          set.remove("JDBC");
          set.remove("iBATIS");
  
          // 저장된 객체 수 얻기
          System.out.println("총 객체수: " + set.size());
  
          // 반복자 얻기
          iterator = set.iterator();
  
          // 객체 수만큼 루핑
          while(iterator.hasNext()) {
              String element = iterator.next();
              System.out.println("\t" + element);
          }
  
          // 모든 객체를 제거하고 비움
          set.clear();
          if(set.isEmpty()) {
              System.out.println("비어 있음");
          }
      }
  }
  ```

* **예제(중복 저장 없이 저장)**

  **Member.java(hashCode()와 equals() 메소드 재정의)**

  ```java
  package hash_set;
  
  public class Member {
      public String name;
      public int age;
  
      public Member(String name, int age) {
          this.name = name;
          this.age = age;
      }
  
      @Override
      public boolean equals(Object obj) {
          if(obj instanceof Member) {
              Member member = (Member) obj;
              return member.name.equals(name) && (member.age == age);
          } else {
              return false;
          }
      }
  
      @Override
      // name 과 age 값이 같으면 동일한
      // hashCode 가 리턴
      public int hashCode() {
          return name.hashCode() + age;
      }
  }
  ```

  **HashSetExample2.java(Member 객체를 중복없이 저장하는 HashSet)**

  ```java
  package hash_set;
  
  import java.util.HashSet;
  import java.util.Set;
  
  public class HashSetExample2 {
      public static void main(String[] args) {
          Set<Member> set = new HashSet<Member>();
  
          // 인스턴스는 다르지만 내부 데이터가
          // 동일하므로 객체 1개만 저장
          set.add(new Member("홍길동", 30));
          set.add(new Member("홍길동", 30));
  
          // 저장된 객체 수 얻기
          System.out.println("총 객체수 : " + set.size());
      }
  }
  ```

  **실행 결과**

  ```
  총 객체수 : 1
  ```



## 15.4 Map 컬렉션

: 키(key)와 값(value)으로 구성된 Entry 객체를 저장하는 구조를 가지고 있다. 여기서 키와 값은 모두 객체이다. 키는 중복될 수 없지만 값은 중복 저장될 수 있다.

* **Map 인터페이스의 메소드들**

  | 기능      | 메소드                              | 설명                                                         |
  | --------- | ----------------------------------- | ------------------------------------------------------------ |
  | 객체 추가 | V put(K key, V value)               | 주어진 키로 값을 저장, 새로운 키일 경우 null을 리턴하고 동일한 키가 있을 경우 값을 대체하고 이전 값을 리턴 |
  | 객체 검색 | boolean containsKey(Object key)     | 주어진 키가 있는지 여부                                      |
  |           | boolean containsValue(Object value) | 주어진 값이 있는지 여부                                      |
  |           | Set\<Map.Entry\<K,V>> entrySet()    | 키와 값의 쌍으로 구성된 모든 Map.Entry 객체를 Set에 담아서 리턴 |
  |           | V get(Object key)                   | 주어진 키가 있는 값을 리턴                                   |
  |           | boolean isEmpty()                   | 컬렉션이 비어 있는지 여부                                    |
  |           | Set\<K> keySet()                    | 모든 키를 Set 객체에 담아서 리턴                             |
  |           | int size()                          | 저장된 키의 총 수를 리턴                                     |
  |           | Collection\<V> values()             | 저장된 모든 값을 Collection에 담아서 리턴                    |
  | 객체 삭제 | void clear()                        | 모든 Map.Entry(키와 값)를 삭제                               |
  |           | V remove(Object key)                | 주어진 키와 일치하는 Map.Entry를 삭제하고 값을 리턴          |

  