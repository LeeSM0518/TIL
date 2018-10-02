#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define SIZE 100

int main(void)
{
  int i, j;
  int box[3][5] = { 0 };

  int max = -1 , min = SIZE + 1 ;
  int sum1[5] = {0};
  int sum2[5] = {0};

  srand((unsigned)time(NULL));
  
  printf("\t숫자\t숫자\t숫자\t숫자\t숫자\t합계\t최대값\t최소값\n");

  for( i = 0 ; i < 3 ; i++){
    for( j = 0 ; j < 5 ; j++){
      box[i][j] = rand() % SIZE + 1;
      printf("\t%d", box[i][j]);

      if(box[i][j] > max)
        max = box[i][j];
      if(box[i][j] < min)
        min = box[i][j];
      sum1[i] += box[i][j];

    }
    printf("\t%d", sum1[i]);
    printf("\t%d", max);
    printf("\t%d",min);

    printf("\n");
  }

  printf("합계\t");
  for( i = 0 ; i < 5 ; i++ ){
    for( j = 0 ; j < 3 ; j ++){
      sum2[i] += box[j][i];
    }
    printf("%d\t", sum2[i]);
  }
  printf("\n");

  max = -1;
  min = SIZE + 1;

  printf("최대값\t");
  for( i = 0 ; i < 5 ; i++ ){
    for( j = 0 ; j < 3 ; j ++){
      if( box[j][i] > max )
        max = box[j][i];
      if( box[j][i] < min )
        min = box[j][i];
    }
    printf("%d\t",max);
    max = - 1;
    min = SIZE + 1;
  }
  printf("\n");
  
  printf("최소값\t");
  for( i = 0 ; i < 5 ; i++ ){
    for( j = 0 ; j < 3 ; j ++){
      if( box[j][i] > max )
        max = box[j][i];
      if( box[j][i] < min )
        min = box[j][i];
    }
    printf("%d\t",min);
    max = - 1;
    min = SIZE + 1;
  }

  

  printf("\n");
}