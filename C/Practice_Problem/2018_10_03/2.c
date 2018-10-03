#include <stdio.h>

int main(void)
{
  int i, j;
  int sum = 0;

  for( i = 1 ; i < 1000 ; i++ )
  {
    if( i % 3 == 0 || i % 5 == 0)
    // i가 3의 배수 이거나 5의 배수이면
    // sum에 i를 더해준다.
    {
      sum += i;
    }

  }

  printf("%d\n",sum);
}