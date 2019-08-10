# 자바 8, 9, 10, 11: 무슨 일이 일어나고 있는가?

<br>

# 1.1. 역사의 흐름은 무엇인가?

* **사과 목록을 무게순으로 정렬하는 고전적 코드**

  ```java
  Collection.sort(inventory, new Comparator<Apple>() {
    public int compare(Apple a1, Apple a2) {
      return a1.getWeight().compareTo(a2.getWeight());
    }
  })
  ```

* **자바 8을 이용하여 자연어에 더 가깝게 간단한 방식으로 구현한 코드**

  ```java
  inventory.sort(comparing(Apple::getWeight))
  ```

  > 훨씬 간단하게 구현되는 것을 확인할 수 있다.

<br>

자바 8에서 부터는 병렬 실행을 새롭고 단순한 방식으로 접근할 수 있는 방법을 제공한다. 하지만 이 새로운 기법을 이용하려면 몇 가지 규칙을 지켜야 하는데 그 규칙들을 학습해보도록 하자.

<br>

* **자바 8의 기반이 되는 요구 사항**
  * 간결한 코드, 멀티코어 프로세서의 쉬운 활용 => **함수형 프로그래밍(functional-style programming)**
    * **스트림 API**
    * 메서드에 코드를 전달하는 기법 **(메서드 참조와 람다)** : 새롭고 간결한 방식으로 동작 파라미터화(behavior parameterization) 구현 가능
    * 인터페이스의 **디폴트 메서드**

<br>

# 1.2. 왜 아직도 자바는 변화하는가?

C, C++ 는 프로그래밍 안전성은 부족하지만 작은 런타임 풋프린트(footprint) 덕분에 운영체제와 다양한 임베디드 시스템에서 여전히 인기를 끌고 있다.