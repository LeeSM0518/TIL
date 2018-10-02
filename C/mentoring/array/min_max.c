#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define SIZE 10

int main(void)
{
  int prices[SIZE] = { 0 };
  int i, minimum, maximum;

  printf("------------------------------------\n");
  printf("1  2  3  4  5  6  7  8  9  10\n");
  printf("------------------------------------\n");

  srand((unsigned)time(NULL));

  for( i = 0 ; i < SIZE ; i++){
    prices[i] = (rand() % 100) + 1;
    printf("%d ",prices[i]);
  }
  printf("\n\n");

  minimum = prices[0];
  maximum = prices[0];
  for(i = 1 ; i < SIZE ; i++)
  {
    if(prices[i] < minimum){
      minimum = prices[i];
    }
    if(prices[i] > maximum){
      maximum = prices[i];
    }
  }

  printf("최소값은 %d 입니다.\n", minimum);
  printf("최대값은 %d 입니다.\n", maximum);
}