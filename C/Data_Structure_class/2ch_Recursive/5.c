#include <stdio.h>

typedef struct Coef{
  int n;
  int C;
  int k;
}Coef;

Coef com(Coef a);

int coe_rec(int n, int k);
int coe_while(int n, int k);

int main(void)
{
  int n, k;
  int coefficient;

  printf("n과 k를 입력하세요 : ");
  scanf("%d %d",&n, &k);

  coefficient = coe_rec( n, k);

  printf("(순환구조) 이항 계수는 %d 입니다.\n", coefficient);

  coefficient = coe_while(n, k);

  printf("(반복구조) 이항 계수는 %d 입니다.\n", coefficient);

}

int coe_rec(int n, int k)
{
  if( k==0 || k==n ){
    return 1;
  }
  else return coe_rec(n-1, k-1) + coe_rec(n-1, k);
}

Coef com(Coef a)
{
  Coef Return_Coef;
  int x = a.n;
  int sum = 1;
  int i,j;
  
  for( i = 0 ; i < a.k ; i++){
    sum *= x;
    x--;
  }
  for( i = a.k ; i >= 1 ; i--){
    sum /= i;
  }

  Return_Coef = a;
  Return_Coef.C = sum;

  return  Return_Coef;
}

int coe_while(int n, int k)
{
  int sum = 0;
  Coef coe;
  
  if( k > 0 && k < n){
    coe.n = n-1;
    coe.k = k-1;
    sum += com(coe).C;
    coe.k += 1;
    sum += com(coe).C;
    return sum;
  }
  else if( k == 0 || k == n){
    return 1;
  }
}
