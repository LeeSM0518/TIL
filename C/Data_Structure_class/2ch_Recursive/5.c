#include <stdio.h>

int coe(int n, int k);

int main(void)
{
  int n, k;
  int coefficient;

  printf("n과 k를 입력하세요 : ");
  scanf("%d %d",&n, &k);

  coefficient = coe( n, k);

  printf("이항 계수는 %d 입니다.\n", coefficient);
}

int coe(int n, int k)
{
  if( k==0 || k==n ){
    return 1;
  }
  else return coe(n-1, k-1) + coe(n-1, k);
}