#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void)
{
  int i, n;
  int *binary = NULL;

  printf("숫자를 입력하세요 : ");
  scanf("%d", &n);

  binary = (int*)malloc(sizeof(int) * 32);
  memset(binary, 0, sizeof(int) * 32);
  
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