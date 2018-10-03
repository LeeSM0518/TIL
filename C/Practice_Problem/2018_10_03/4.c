#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void)
{
  int i, n;
  int *binary = NULL;
  // binary를 포인터로 선언한다.

  printf("숫자를 입력하세요 : ");
  scanf("%d", &n);

  binary = (int*)malloc(sizeof(int) * 32);
  memset(binary, 0, sizeof(int) * 32);
  // 최대 32 자리 이므로 동적메모리를 32개를 할당 시켜주고
  // 32개를 모두 0으로 초기화 시켜준다.

  for( i = 0 ; i < 32 && n > 0 ; i++ ){
    binary[i] = n % 2;
    n /= 2;
  }

  for( i = 31 ; i >= 0 ; i--)
  {
    printf("%d",binary[i]);
  }
  printf("\n");
}

