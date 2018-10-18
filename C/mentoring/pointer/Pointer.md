# **C** Programmin Mentoring



# 포인터



## 이론

### 포인터란?

![1539831409262](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1539831409262.png)





![1539831456395](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1539831456395.png)



![1539831523797](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1539831523797.png)

---









### 간접 참조 연산자 *

> **간접 참조** 란 포인터가 가리키는 주소에 저장된 값을 가져오는 것이다.

![1539831573124](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1539831573124.png)

![1539831619341](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1539831619341.png)



![1539831685125](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1539831685125.png)

![1539831772837](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1539831772837.png)

* **중간점검**

  1. 메모리는 어떤 단위를 기준으로 주소가 매겨지는가?	

  2. 다음의 각 자료형이 차지하는 메모리 공간의 크기를 써라.

     (a) char   (b) short  (c) int  (d) long  (e) float  (f) double

  3. 포인터도 변수인가?

  4. 변수의 주소를 추출하는데 사용되는 연산자는 무엇인가? 

  5. 변수 x의 주소를 추출하여 변수 p에 대입하는 문장을 써라.

  6. 정수형 포인터 p가 가리키는 위치에 25를 저장하는 문자을 써라.



---



### 포인터 사용시 주의할 점![1539832348621](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1539832348621.png)



* **중간점검**
  1. 초기값이 결정되지 않은 포인터에는 어떤 값을 넣어두는 것이 안전한가?



---





















### 포인터 연산

![1539832502202](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1539832502202.png)

![1539831839772](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1539831839772.png)

* **중간점검**

  1. int형 포인터 p가 80번지를 가리키고 있었다면 (p+1)은 몇 번지를 가리키고 있는가?

  2. p가 포인터라고 하면 *p++와  ( *p )++ 의 차이점은 무엇인가?

  3. p가 char형 포인터이고 10번지라고 하면 *(p+3)의 의미는 무엇이고 몇 번지를 가리키고 있는가?



---

### 포인터와 배열

![1539831952268](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1539831952268.png)



![1539832008260](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1539832008260.png)



* **중간점검**
  1. 배열의 첫 번째 원소의 주소를 계산하는 2가지 방법은 ?  
  2. 배열 a[ ] 에서 *a의 의미는 무엇인가? 
  3. 배열의 이름에 다른 변수의 주소를 대입할 수 있는가? 
  4. 포인터를 이용하여 배열의 원소들을 참조할 수 있는가? 
  5. 포인터를 배열의 이름처럼 사용할 수 있는가? 

























### 포인터와 함수

![1539833230706](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1539833230706.png)

* **중간점검**

  1. 함수에 매개 변수로 변수의 복사본이 전달되는 것을                            라고 한다.

  2. 함수에 매개 변수로 변수의 원본이 전달되는 것을                             라고 한다.

  3. 배열을 함수의 매개 변수로 지정하는 경우, 배열의 복사가 일어나는가?



















## 실습

* **2개의 정수의 합과 차를 동시에 반환하는 함수를 작성하고 테스트하라. 포인터 매개 변수를 사용한다.**

  ```c
  #include <stdio.h>
   
  void get_sum_diff(int x, int y, int *p_sum, int *p_diff);
   
  int main() {
      int x, y;
      int sum, diff;
   
      printf("2개의 정수를 입력하시오: ");
      scanf("%d %d", &x, &y);
   
      get_sum_diff(x, y, &sum, &diff);
   
      printf("x + y = %d \n", sum);
      printf("x - y = %d \n", diff);
      
      return 0;
  }
   
  void get_sum_diff(int x, int y, int *p_sum, int *p_diff) {
      *p_sum = x + y;
      *p_diff = x - y;
  }
  ```


* **정수 배열을 받아서 원소들을 난수로 채우는 함수를 작성하고 테스트하라. 난수는 라이브러리 함수인 rand()를 사용하여 생성한다.**

  ```C
  #include <stdio.h>
  #include <stdlib.h>
  #include <time.h>
  
  #define SIZE 10
  
  void array_fill(int *A, int size);
  
  int main() {
  	int arr[SIZE];
  	int i;
  	srand((unsigned)time(NULL));
  
  	array_fill(arr, SIZE);
  	for (i = 0; i<SIZE; i++)
  		printf("%d ", arr[i]);
  	printf("\n");
  
  	return 0;
  }
  
  void array_fill(int *A, int size) {
  	int i;
  	for (i = 0; i<size; i++)
  		*(A+i) = 1 + rand() % 100;
  }
  ```
