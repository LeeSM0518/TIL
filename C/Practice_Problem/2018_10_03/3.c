#include <stdio.h>

int main(void)
{
  int i, n;
  int binary[32] = {0};
  // 최대 32 자리 이므로 binary 배열 32개를 만들어주고

  printf("숫자를 입력하세요 : ");
  scanf("%d", &n);

  for( i = 0 ; i < 32 && n > 0 ; i++ ){
    // n을 계속 2로 나눠준 값을 저장하기 때문에
    // n이 0 보다 작아지면 변환이 끝난것 이기 때문에
    // i가 32보다 작고 n 이 0보다 클때만 for문을 반복해준다. 
    
    binary[i] = n % 2;  
    // i번째 자리에 2진수 변환 값을 넣어준다.
    
    n /= 2;
  }

  for( i = 31 ; i >= 0 ; i--)
  // 변환은 i번 째 부터 이지만
  // 2진수를 읽는 방식은 역순이므로
  // 31 부터 0 까지 역순으로 출력시켜준다.
  {
    printf("%d",binary[i]);
  }
  printf("\n");
}
