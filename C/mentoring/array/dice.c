#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define SIZE 6

int main(void)
{
  int freq[SIZE] = { 0 };
  int i;

  srand((unsigned)time(NULL));

  for( i = 0 ; i < 10000 ; i ++){
    ++freq[rand() % 6];
  }

  printf("면\t빈도\n");

  for( i = 0 ; i < SIZE ; i++){
    printf("%3d \t %3d \n", i, freq[i]);
  }

  return 0;
}