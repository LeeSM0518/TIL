#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void scalar_mult(int a[][3], int scalar);
void scalar_trans(int a[][3], int b[][3]);
void scalar_plus(int a[][3], int b[][3]);
void print_scalar(int a[][3]);

int main(void)
{
  int i, j, n;
  int a[3][3] = {0};
  int b[3][3] = {0};
  int c[3][3] = {0};

  srand((unsigned)time(NULL));

  for( i = 0 ; i < 3 ; i++)
  {
    for( j = 0 ; j < 3 ; j ++)
    {
      a[i][j] = rand() % 10 + 1;
      b[i][j] = rand() % 10 + 1;
    }
  }

  printf("A 스칼라 : \n");
  print_scalar(a);

  printf("B 스칼라 : \n");
  print_scalar(b);

  printf("C 스칼라 : \n");
  print_scalar(c);

  printf("A 스칼라를 몇배? ");
  scanf("%d", &n);

  scalar_mult(a, n);

  scalar_trans(a, c);

  printf("A 스칼라와 B 스칼라의 합: \n");
  scalar_plus(a, b);
}

void scalar_mult(int a[][3], int scalar)
{
  int i, j;

  for( i = 0 ; i < 3 ; i++)
  {
    for( j = 0 ; j < 3 ; j++)
    {
      a[i][j] *= scalar;
    }
  }
  
  printf("mult 결과\n");
  print_scalar(a);
}

void scalar_trans(int a[][3], int b[][3])
{
  int i, j;

  for( i = 0 ; i < 3 ; i++)
  {
    for( j = 0 ; j < 3 ; j++)
    {
      b[i][j] = a[j][i];
    }
  }

  printf("transpose 결과\n");
  print_scalar(b);
}

void scalar_plus(int a[][3], int b[][3])
{
  int i, j;
  int sum[3][3] = {0};

  for( i = 0 ; i < 3 ; i++)
  {
    for( j = 0 ; j < 3 ; j++)
    {
      sum[i][j] = a[i][j] + b[i][j];
    }
  }

  printf("plus 결과\n");
  print_scalar(sum);
}

void print_scalar(int a[][3])
{
  int i,j;

  for( i = 0 ; i < 3 ; i++)
  {
    for( j = 0 ; j < 3 ; j++)
    {
      printf("%d\t", a[i][j]);
    }
    printf("\n");
  }
  printf("\n");
}