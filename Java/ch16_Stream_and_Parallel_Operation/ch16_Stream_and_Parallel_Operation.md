# Chapter 16. 스트림과 병렬 처리

## 16.1 스트리 소개

: 스트림(Stream)은 컬렉션(배열 포함)의 저장 요소를 하나씩 참조해서 람다식(함수적 스타일)으로 처리할 수 있도록 해주는 반복자이다.



### 16.1.1 반복자 스트림

* **Iterator 반복자**

  ```java
  List<String> list = Arrays.asList("홍길동", "신용권", "감자바");
  Iterator<String> iterator = list.iterator();
  while(iterator.hasNext()) {
      String name = iterator.next();
      System.out.println(name);
  }
  ```

* **Iterator -> Stream**

  ```java
  List<String> list = Arrays.asList("홍길동", "신용권", "감자바");
  Stream<String> stream = list.stream();
  stream.forEach( name -> System.out.println(name) );
  ```

  > forEach() 메소드
  >
  > : Consumer 함수적 인터페이스 타입의 매개값을 가지므로 컬렉션의 요소를 소비할 코드를 람다식으로 기술 가능.

  ```java
  void forEach(Consumer<T> action)
  ```

* **예제**

  ```java
  package iterator_stream;
  
  import java.util.Arrays;
  import java.util.Iterator;
  import java.util.List;
  import java.util.stream.Stream;
  
  public class IteratorVsStreamExample {
      public static void main(String[] args) {
          List<String> list = Arrays.asList("홍길동", "신용권", "감자바");
  
          // Iterator 이용
          Iterator<String> iterator = list.iterator();
          while(iterator.hasNext()) {
              String name = iterator.next();
              System.out.println(name);
          }
  
          System.out.println();
  
          // Stream 이용
          Stream<String> stream = list.stream();
          stream.forEach( name -> System.out.println(name) );
      }
  }
  ```

  **실행 결과**

  ```
  홍길동
  신용권
  감자바
  
  홍길동
  신용권
  감자바
  ```



### 16.1.2 스트림의 특징

1. Iterator와 비슷한 역할을 하는 반복자이다. 
2. 람다식으로 요소 처리 코드를 제공한다.
3. 내부 반복자를 사용하므로 병렬 처리가 쉽다.
4. 중간 처리와 최종 처리 작업을 수행한다.



#### 람다식으로 요소 처리 코드를 제공한다.

: Stream이 제공하는 대부분의 요소 처리 메소드는 함수적 인터페이스 매개 타입을 가지기 때문.

* **예제**

  Student.java(**학생 클래스**)

  ```java
  package characteristic_of_stream;
  
  public class Student {
      private String name;
      private int score;
      
      public Student (String name, int score) {
          this.name = name;
          this.score = score;
      }
      
      public String getName() { return name; }
      public int getScore() { return score; }
  }
  ```

  LambdaExpressionsExample.java(**요소 처리를 위한 람다식**)

  ```java
  package characteristic_of_stream;
  
  import java.util.Arrays;
  import java.util.List;
  import java.util.stream.Stream;
  
  public class LambdaExpressionsExample {
      public static void main(String[] args) {
          List<Student> list = Arrays.asList(
                  new Student("홍길동", 90),
                  new Student("신용권", 92)
          );
  
          // 스트림 얻기
          Stream<Student> stream = list.stream();
  
          // List 컬렉션에서 Student 를 가져와
          // 람다식의 매개값으로 제공
          stream.forEach( s -> {
              String name = s.getName();
              int score = s.getScore();
              System.out.println(name + "-" + score);
          });
      }
  }
  ```

  **실행 결과**

  ```
  홍길동-90
  신용권-92
  ```

  

#### 내부 반복자를 사용하므로 병렬 처리가 쉽다.

* **외부 반복자(external iterator)** : 개발자가 코드로 직접 컬렉션의 요소를 반복해서 가져오는 코드 패턴(**ex) for, while**)

* **내부 반복자(internal iterator)** : 컬렉션 내부에서 요소들을 반복시키고, 개발자는 요소당 처리해야 할 코드만 제공하는 코드 패턴

  ![1550545915442](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1550545915442.png)

  > 내부 반복자를 사용해서 컬렉션 내부에서 어떻게 요소를 반복시킬 것인가는 컬렉션에 맡겨두고, 개발자는 요소 처리 코드에만 집중할 수 있다.

* **스트림(stream)** : Iterator 대신 스트림을 이용하면 코드도 간결해지고, 요소의 병렬 처리가 컬렉션 내부에서 처리된다.

  * **병렬(parallel) 처리** : 한 가지 작업을 서브 작업으로 나누고, 서브 작업들을 분리된 스레드에서 병렬적으로 처리하는 것.
  * **병렬 처리 스트림** : 런타임 시 하나의 작업을 서브 작업으로 자동으로 나누고, 서브 작업의 결과를 자동으로 결합해서 최종 결과물을 생성.

* **예제**

  ```java
  package characteristic_of_stream;
  
  import java.util.Arrays;
  import java.util.List;
  import java.util.stream.Stream;
  
  public class ParallelExample {
      public static void main(String[] args) {
          List<String> list = Arrays.asList(
                  "홍길동", "신용권", "감자바",
                  "람다식", "박병렬"
          );
  
          // 순차 처리
          Stream<String> stream = list.stream();
          stream.forEach(ParallelExample :: print);   // 메소드 참조
          System.out.println();
  
          // 병렬 처리
          Stream<String> parallelStream = list.parallelStream();
          parallelStream.forEach(ParallelExample::print); // 메소드 참조
      }
  
      public static void print(String str) {
          System.out.println(str + " : " + Thread.currentThread().getName());
      }
  }
  ```

  **실행 결과**

  ```java
  홍길동 : main			// 순차처리
  신용권 : main
  감자바 : main
  람다식 : main
  박병렬 : main
  
  감자바 : main			// 병렬처리
  신용권 : ForkJoinPool.commonPool-worker-9
  박병렬 : ForkJoinPool.commonPool-worker-2
  람다식 : ForkJoinPool.commonPool-worker-9
  홍길동 : ForkJoinPool.commonPool-worker-11
  ```

  > 병렬 처리 스트림은 main 스레드를 포함해서 ForkJoinPool(스레드풀)의 작업 스레드들이 병렬적으로 요소를 처리하는 것을 볼 수 있다.



#### 스트림은 중간 처리와 최종 처리를 할 수 있다.

: 스트림은 컬렉션의 요소에 대해 중간 처리와 최종 처리를 수행할 수 있는데, 중간 처리에서는 매핑, 필터링, 정렬을 수행하고 최종 처리에서는 반복, 카운팅, 평균, 총합 등의 집계 처리를 수행한다.

![1550562020437](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1550562020437.png)

* **예제**

  ```java
  package characteristic_of_stream;
  
  import java.util.Arrays;
  import java.util.List;
  
  public class MapAndReduceExample {
      public static void main(String[] args) {
          List<Student> studentList = Arrays.asList(
                  new Student("홍길동", 10),
                  new Student("신용권", 20),
                  new Student("유미선", 30)
          );
  
          double avg = studentList.stream()
                  // 중간 처리(학생 객체를 점수로 매핑)
                  .mapToInt(Student::getScore)
                  // 최정 처리(평균 점수)
                  .average()
                  .getAsDouble();
  
          System.out.println("평균 점수: " + avg);
      }
  }
  ```

  **실행 결과**

  ```
  평균 점수: 20.0
  ```



## 16.2 스트림의 종류

: java.util.stream 패키지에는 스트림(stream) API들이 있다. 패키지 내용을 보면 BaseStream 인터페이스를 부모로 해서 자식 인터페이스들이 상속 관계를 이루고 있다.

![1550562386246](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1550562386246.png)

> Stream은 객체 요소를 처리하는 스트림이고, IntStream, LongStream, DoubleStream은 각각 기본 타입인 int, long, double 요소를 처리하는 스트림이다.

* **스트림 구현 객체**

  | 리턴 타입                                                   | 메소드(매개 변수)                                            | 소스           |
  | ----------------------------------------------------------- | ------------------------------------------------------------ | -------------- |
  | Stream\<T>                                                  | java.util.Collection.stream()<br />java.util.Collection.parallelStream() | 컬렉션         |
  | Stream\<T><br />IntStream<br />LongStream<br />DoubleStream | Arrays.stream(T[]), Stream.of(T[])<br />Arrays.stream(int[]), IntStream.of(int[])<br />Arrays.stream(long[]), LongStream.of(long[])<br />Arrays.stream(double[]), DoubleStream.of(double[]) | 배열           |
  | IntStream                                                   | IntStream.range(int, int)<br />IntStream.rangeClosed(int, int) | int<br />범위  |
  | LongStream                                                  | LongStream.range(long, long)<br />LongStream.rangeClosed(long, long) | long<br />범위 |
  | Stream\<Path>                                               | Files.find(Path, int, BiPredicate, FileVisitOption)<br />Files.list(Path) | 디렉토리       |
  | Stream\<String>                                             | Files.lines(Path, Charset)<br />BufferedReader.lines()       | 파일           |
  | DoubleStream<br />IntStream<br />LongStream                 | Random.doubles(...)<br />Random.ints()<br />Random.longs()   | 랜덤 수        |



### 16.2.1 컬렉션으로부터 스트림 얻기

* **예제(컬렉션으로부터 스트림 얻기)**

  Student.java

  ```java
  package from_stream.to_collection;
  
  public class Student {
      private String name;
      private int score;
  
      public Student (String name, int score) {
          this.name = name;
          this.score = score;
      }
  
      public String getName() {
          return name;
      }
      public int getScore() {
          return score;
      }
  }
  ```

  FromCollectionExample.java

  ```java
  package from_stream.to_collection;
  
  import java.util.Arrays;
  import java.util.List;
  import java.util.stream.Stream;
  
  public class FromCollectionExample {
      public static void main(String[] args) {
          List<Student> studentList = Arrays.asList(
                  new Student("홍길동", 10),
                  new Student("신용권", 20),
                  new Student("유미선", 30)
          );
  
          Stream<Student> stream = studentList.stream();
  
          stream.forEach(s -> System.out.println(s.getName()));
      }
  }
  ```

  **실행 결과**

  ```
  홍길동
  신용권
  유미선
  ```



### 16.2.2 배열로부터 스트림 얻기

* **예제**

  ```java
  package get_stream.from_array;
  
  import java.util.Arrays;
  import java.util.stream.IntStream;
  import java.util.stream.Stream;
  
  public class FromArrayExample {
      public static void main(String[] args) {
          String[] strArray = { "홍길동", "신용권", "김미나" };
          Stream<String> strStream = Arrays.stream(strArray);
          strStream.forEach(a -> System.out.print(a + ','));
          System.out.println();
  
          int[] intArray = { 1, 2, 3, 4, 5 };
          IntStream intStream = Arrays.stream(intArray);
          intStream.forEach(a -> System.out.print(a + ","));
          System.out.println();
      }
  }
  ```

  **실행 결과**

  ```
  홍길동,신용권,김미나,
  1,2,3,4,5,
  ```



### 16.2.3 숫자 범위로부터 스트림 얻기

* **예제**

  ```java
  package get_stream.from_int_range;
  
  import java.util.stream.IntStream;
  
  public class FromIntRangeExample {
      public static int sum;
  
      public static void main(String[] args) {
          IntStream stream = IntStream.rangeClosed(1, 100);
          stream.forEach(a -> sum += a);
          System.out.println("총합: " + sum);
      }
  }  
  ```

  **실행 결과**

  ```
  총합: 5050
  ```

  > **rangeClosed()** : 첫 번째 매개값에서부터 두 번째 매개값까지 순차적으로 제공하는 IntStream을 리턴.
  >
  > **range()** : 기능은 위와 같지만, 두 번째 매개값은 포함하지 않는다.



### 16.2.4 파일로부터 스트림 얻기

* **예제**

  ```java
  package get_stream.from_file;
  
  import java.io.BufferedReader;
  import java.io.File;
  import java.io.FileReader;
  import java.io.IOException;
  import java.nio.charset.Charset;
  import java.nio.file.Files;
  import java.nio.file.Path;
  import java.nio.file.Paths;
  import java.util.stream.Stream;
  
  public class FromFileContentExample {
      public static void main(String[] args) throws IOException {
          
          // 파일의 경로 정보를 가지고 있는 Path 객체 생성
          Path path = Paths.get("src/get_stream/from_file/linedata.txt");
          Stream<String> stream;
  
          // Files.lines() 메소드 이용
          // 운영체제의 기본 문자셋
          stream = Files.lines(path, Charset.defaultCharset());
          // 메소드 참조
          stream.forEach( System.out :: println );
          System.out.println();
  
          // BufferedReader 의 lines() 메소드 이용
          File file = path.toFile();
          FileReader fileReader = new FileReader(file);
          BufferedReader br = new BufferedReader(fileReader);
          stream = br.lines();
          stream.forEach( System.out :: println);
      }
  }
  ```

  **실행 결과**

  ```
  a
  bb
  ccc
  dddd
  eeeee
  ffffff
  ggggggg
  
  a
  bb
  ccc
  dddd
  eeeee
  ffffff
  ggggggg
  ```



### 16.2.5 디렉토리로부터 스트림 얻기

* **예제**

  ```java
  package get_stream.from_directory;
  
  import java.io.IOException;
  import java.nio.file.Files;
  import java.nio.file.Path;
  import java.nio.file.Paths;
  import java.util.stream.Stream;
  
  public class FromDirectoryExample {
      public static void main(String[] args) throws IOException {
          Path path = Paths.get("src");
          Stream<Path> stream = Files.list(path);
          // p = 서브 디렉토리 또는 파일에 해당하는 Path 객체
          // p.getFileName() = 서브 디렉토리 이름 또는 파일 이름 리턴
          stream.forEach( p -> System.out.println(p.getFileName()));
      }
  }
  ```

  **실행 결과**

  ```
  characteristic_of_stream
  get_stream
  iterator_stream
  ```



## 16.3 스트림 파이프라인

* **리덕션(Reduction)** : 대량의 데이터를 가공해서 축소하는 것. 예) 데이터의 합계, 평균값, 카운팅, 최대값 등

: 컬렉션의 요소를 리덕션의 결과물로 바로 집계할 수 없을 경우에는 집계하기 좋도록 스트림의 **중간 처리(필터링, 매핑, 정렬, 그룹핑)**가 필요하다.



### 16.3.1 중간 처리와 최종 처리

* **스트림**

  * **중간 처리** : 데이터의 필터링, 매핑, 정렬, 그룹핑 등

  * **최종 처리** : 합계, 평균, 카운팅, 최대값, 최소값 등

    * 위와 같은 처리들을 **파이프라인(pipelines)**으로 해결한다. 파이프라인은 여러 개의 스트림이 연결되어 있는 구조를 말한다.

    ![1550566517478](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1550566517478.png)

* **예제**

  ![1550566527389](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1550566527389.png)

  Member.java

  ```java
  package stream_pipelines;
  
  public class Member {
      public static int MALE = 0;
      public static int FEMALE = 1;
  
      private String name;
      private int sex;
      private int age;
  
      public Member(String name, int sex, int age) {
          this.name = name;
          this.sex = sex;
          this.age = age;
      }
  
      public int getSex() {
          return sex;
      }
      public int getAge() {
          return age;
      }
  }
  ```

  StreamPipelinesExample.java

  ```java
  package stream_pipelines;
  
  import java.util.Arrays;
  import java.util.List;
  
  public class StreamPipelinesExample {
      public static void main(String[] args) {
          List<Member> list = Arrays.asList(
                  new Member("홍길동", Member.MALE, 30),
                  new Member("김나리", Member.FEMALE, 20),
                  new Member("신용권", Member.MALE, 45),
                  new Member("박수미", Member.FEMALE, 27)
          ) ;
  
          double ageAvg = list.stream()
                  .filter(m -> m.getSex() == Member.MALE)
                  .mapToInt(Member::getAge)
                  .average()
                  .getAsDouble();
  
          System.out.println("남자 평균 나이: " + ageAvg);
      }
  }
  ```

  **실행 결과**

  ```
  남자 평균 나이: 37.5
  ```



### 16.3.2 중간 처리 메소드와 최종 처리 메소드

: 리턴 타입이 스트림이라면 중간 처리 메소드이고, 기본 타입이거나 OptionalXXX 라면 최종 처리 메소드이다. 소속된 인터페이스에서 공통의 의미는 Stream, Instream, LongStream, DoubleStream에서 모두 제공된다는 뜻이다.

* **중간 처리 메소드**

  ![1550648670494](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1550648670494.png)

* **최종 처리 메소드**

  ![1550648687622](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1550648687622.png)



## 16.4 필터링(distinct(), filter())

: 필터링은 중간 처리 기능으로 요소를 걸러내는 역할을 한다.

* **필터링 메소드**

  | 리턴 타입                                                | 메소드(매개 변수)       | 설명        |
  | -------------------------------------------------------- | ----------------------- | ----------- |
  | Stream,<br />IntStream<br />LongStream<br />DoubleStream | distinct()              | 중복 제거   |
  | ``                                                       | filter(Predicate)       | 조건 필터링 |
  | ``                                                       | filter(IntPredicate)    | ``          |
  | ``                                                       | filter(LongPredicate)   | ``          |
  | ``                                                       | filter(DoublePredicate) | ``          |

* **distinct() 메소드**

  ![1550650265499](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1550650265499.png)

* **filter() 메소드**

  : 매개값으로 주어진 Predicate가 true를 리턴하는 요소만 필터링한다.

  ![1550650372949](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1550650372949.png)

* **예제(성이 "신"인 이름만 필터링)**

  ```java
  package filtering;
  
  import java.util.Arrays;
  import java.util.List;
  
  public class FilteringExample {
      public static void main(String[] args) {
          List<String> names = Arrays.asList(
                  "홍길동", "신용권", "감자바", "신용권", "신민철"
          );
  
          names.stream()
                  .distinct() // 중복제거
                  .forEach(n -> System.out.println(n));
          System.out.println();
  
          names.stream()
                  .filter(n -> n.startsWith("신")) // 필터링
                  .forEach(n -> System.out.println(n));
          System.out.println();
  
          // 중복 제거 후 필터링
          names.stream()
                  .distinct()
                  .filter(n -> n.startsWith("신"))
                  .forEach(n -> System.out.println(n));
      }
  }
  ```

  **실행 결과**

  ```
  홍길동
  신용권
  감자바
  신민철
  
  신용권
  신용권
  신민철
  
  신용권
  신민철
  ```



## 16.5 매핑(flatMapXXX(), mapXXX(), asXXXStream(), boxed())

* **매핑(mapping)** : 중간 처리 기능으로 스트림의 요소를 다른 요소로 대체하는 작업을 말한다.

### 16.5.1 flatMapXXX() 메소드

: 요소를 대체하는 복수 개의 요소들로 구성된 새로운 스트림을 리턴한다.

![1550651147595](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1550651147595.png)

* **flatMapXXX() 메소드 종류**

  | 리턴 타입    | 메소드(매개변수)                            | 요소 -> 대체 요소      |
  | ------------ | ------------------------------------------- | ---------------------- |
  | Stream\<R>   | flatMap(Function\<T, Stream\<R>>)           | T -> Stream\<R>        |
  | DoubleStream | flatMap(DoubleFunction\<DoubleStream>)      | double -> DoubleStream |
  | IntStream    | flatMap(IntFunction\<IntStream>)            | int -> IntStream       |
  | LongStream   | flatMap(LongFunction\<LongStream>)          | long -> LongStream     |
  | DoubleStream | flatMapToDouble(Function\<T, DoubleStream>) | T -> DoubleStream      |
  | IntStream    | flatMapToInt(Function<T, IntStream>)        | T -> IntStream         |
  | LongStream   | flatMapToLong(Function<T, LongStream>)      | T -> LongStream        |

* **예제(컬렉션 요소별로 단어를 뽑아 단어 스트림으로 재생성)**

  ```java
  package mapping;
  
  import java.util.Arrays;
  import java.util.List;
  
  public class FlatMapExample {
      public static void main(String[] args) {
          List<String> inputList1 = Arrays.asList(
                  "java8 lambda", "stream mapping"
          );
          inputList1.stream()
                  .flatMap(data -> Arrays.stream(data.split(" ")))
                  .forEach(word -> System.out.println(word));
  
          System.out.println();
  
          List<String> inputList2 = Arrays.asList("10, 20, 30", "40, 50, 60");
          inputList2.stream()
                  .flatMapToInt(data -> {
                      String[] strArr = data.split(",");
                      int[] intArr = new int[strArr.length];
                      for(int i=0; i<strArr.length; i++) {
                          intArr[i] = Integer.parseInt(strArr[i].trim());
                      }
                      return Arrays.stream(intArr);
                  })
                  .forEach(number -> System.out.println(number));
      }
  }
  ```

  **실행 결과**

  ```
  java8
  lambda
  stream
  mapping
  
  10
  20
  30
  40
  50
  60
  ```



### 16.5.2 mapXXX() 메소드

: 요소를 대체하는 요소로 구성된 새로운 스트림을 리턴한다.

![1550653195258](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1550653195258.png)

* **mapXXX() 메소드 종류**

  ![1550653226040](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1550653226040.png)

* **예제(학생의 점수를 요소로 새로운 스트림을 생성 후 순차적 출력)**

  Student.java

  ```java
  package mapping;
  
  public class Student {
      private String name;
      private int score;
  
      public Student(String name, int score) {
          this.name = name;
          this.score = score;
      }
  
      public String getName() {
          return name;
      }
      public int getScore() {
          return score;
      }
  }
  ```

  MapExample.java

  ```java
  package mapping;
  
  import java.util.Arrays;
  import java.util.List;
  
  public class MapExample {
      public static void main(String[] args) {
          List<Student> studentList = Arrays.asList(
                  new Student("홍길동", 10),
                  new Student("신용권", 20),
                  new Student("유미선", 30)
          );
  
          studentList.stream()
                  .mapToInt(Student::getScore)
                  .forEach(score -> System.out.println(score));
      }
  }
  ```



### 16.5.3 asDoubleStream(), asLongStream(), boxed() 메소드

* **asDoubleStream() 메소드** : IntStream의 int 요소 또는 LongStream의 long 요소를 double 요소로 타입 변환해서 DoubleStream을 생성한다.
* **asLongStream() 메소드** : IntStream의 int 요소를 long 요소로 타입 변환해서 LongStream을 생성한다.
* **boxed() 메소드** : int, long, double 요소를 Integer, Long, Double 요소로 박싱해서 Stream 생성한다.

![1550654023622](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1550654023622.png)

* **예제(int 배열로 부터 IntStream을 얻고 int 요소를 double 요소로 변환)**

  ```java
  package mapping;
  
  import java.util.Arrays;
  import java.util.stream.IntStream;
  
  public class AsDoubleStreamAndBoxedExample {
      public static void main(String[] args) {
          int[] intArray = { 1, 2, 3, 4, 5 };
  
          IntStream intStream = Arrays.stream(intArray);
          intStream
                  .asDoubleStream()   // DoubleStream 생성
                  .forEach(d -> System.out.println(d));
  
          System.out.println();
  
          intStream = Arrays.stream(intArray);
          intStream
                  .boxed()            // Stream<Integer> 생성
                  .forEach(obj -> System.out.println(obj.intValue()));
      }
  }
  ```

  **실행 결과**

  ```
  1.0
  2.0
  3.0
  4.0
  5.0
  
  1
  2
  3
  4
  5
  ```



## 16.6 정렬(sorted())

: 