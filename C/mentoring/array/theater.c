#include <stdio.h>
#define SIZE 10

int seats[SIZE] = { 0 };

void reserve1();
void reserve2(int count);
void print_seat();
int check_full();

int main(void)
{
  char ans1;
  int count, i;
  int check = 0;
  int reserved;

  while(1)
  {
    reserved = SIZE - check;

    printf("좌석을 예약하시겠습니까?(y 또는 n)");
    scanf(" %c", &ans1);

    if(ans1 == 'y')
    {
      print_seat();

      printf("몇 명을 예약하실것 입니까? ");
      scanf("%d", &count);


      if(count == 1 && count <= reserved){   
        reserve1();
      }

      else if(count <= SIZE && count <= reserved)
        reserve2(count);
      
      else{
        printf("자리가 부족합니다.\n");
      }
    }
    
    else if(ans1 == 'n'){
        return 0;
    }
    
    check = check_full();

    if(check == SIZE){
      printf("모든 자리가 예약되었습니다.\n");
      break;
    }
    else
      continue;
  }
  return 0;
}

void reserve1()
{
  int ans2, i;
  printf("몇 번째 좌석을 예약하시겠습니까? ");
  scanf("%d", &ans2);
      

  if(ans2 <= 0 || ans2 > SIZE ){
    printf("1부터 10사이의 숫자를 입력하세요\n");
  }

  if(seats[ans2-1] == 0){
    seats[ans2-1] = 1;
    printf("예약되었습니다.\n");
  }

  print_seat();
}

void reserve2(int count)
{
  int ans2, i;
  for( i = 0 ; i < count ; i++)
  {
    printf("몇 번째 좌석을 예약하시겠습니까? ");
    scanf("%d", &ans2);
      

    if(ans2 <= 0 || ans2 > SIZE ){
      printf("1부터 10사이의 숫자를 입력하세요\n");
    }

    if(seats[ans2-1] == 0){
      seats[ans2-1] = 1;
      printf("예약되었습니다.\n");
    }

    else{
      printf("이미 예약된 자리입니다.\n");
    }
  }

  print_seat();
}

void print_seat()
{
  int i;

  printf("-----------------------------\n");
  printf("1 2 3 4 5 6 7 8 9 10\n");
  printf("-----------------------------\n");

  for(i = 0; i < SIZE ; i++){
    printf("%d ",seats[i]);
  }
  printf("\n");
}

int check_full()
{
  int i;
  int count = 0;

  for(i = 0 ; i < SIZE ; i++){
    if( seats[i] == 1 ){
      count++;
    } 
  }
  
  return count;
}