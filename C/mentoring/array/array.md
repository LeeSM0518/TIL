C Programming Mentoring
===
<br>

# 배열

## 주사위 던지기

  ```c
  #include <stdio.h>
  #include <stdlib.h>   // 랜덤함수를 써주기 위해
  #include <time.h>   // 지속적으로 랜덤 값을 변경시켜 주기 위해

  #define SIZE 6    // 전역 변수 선언

  int main(void)
  {
    int freq[SIZE] = { 0 };   // 배열을 다 0으로 초기화
    int i; 

    srand((unsigned)time(NULL));    // 랜덤 함수를 지속적으로 값을 변경 시켜주기 위해

    for( i = 0 ; i < 10000 ; i ++){
      ++freq[rand() % 6];
    }

    printf("면\t빈도\n");

    for( i = 0 ; i < SIZE ; i++){
      printf("%3d \t %3d \n", i, freq[i]);
    }
  

    return 0;
  }
  ```