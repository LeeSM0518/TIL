#include <stdio.h>
int main(void)
{
  int i, j;
  int count = 0;

  for( i = 2 ; i <= 100 ; i++)
  {
    for( j = 1; j <= i ; j++){
    
      if( i % j == 0){    
        // i를 j로 나눴을 때 나머지가 0이면 
        // count를 하나 올려줘서 약수를 찾아준다.
          count++;

        if( count > 2){
          // 만약 count가 2가 넘으면 
          // 소수가 아니므로 다음 수로 넘어간다.
          break;
        }
      }
    }
      if( count == 2){
        // 1과 자기 자신만을 약수로 가지면
        // count가 2 이므로 출력해준다.
        printf("%d ", i);
      }  
    count = 0;
    // count가 계속 증가하는 것을 막기위해 0 으로 다시 초기화 한다.
  }
  printf("\n");
}