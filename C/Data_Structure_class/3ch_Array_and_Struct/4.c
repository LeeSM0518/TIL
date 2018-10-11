#include <stdio.h>
#include <stdlib.h>

#define MAX_TERMS 101

// A = 8 x^3 + 7 x^1 + 1 x^0
// A = 10 x^3 + 3 x^2 + 1 x^0
struct Term{
  float coef; // 계수
  int expon;  // 차수
}terms[MAX_TERMS] = { {4, 3}, {4, 2}, {1, 0}, {2, 3}, {4, 2}, {8, 0} };
int avail = 6;

void displayPolyList()
{
  int i, j;
  printf("A 식 : ");
  for( i = 0 ; i < 3; i++)
  {
    printf("%.1f x^%d ", terms[i].coef, terms[i].expon);
    if( i != 2)
      {
        printf("+ ");
      }
  }

  printf("\n\n");

  printf("B 식 : ");
  for( i = 3 ; i < 6 ; i++)
  {
    printf("%.1f x^%d ", terms[i].coef, terms[i].expon);
    if( i != 5)
      {
        printf("+ ");
      }
  }

  printf("\n\n");
}

void displayPolyList2()
{
  int i, j;

  printf("A + B = ");
  for( i = 6 ; i < MAX_TERMS; i++)
  {
    printf("%.1f x^%d ", terms[i].coef, terms[i].expon);
    if( terms[i].expon != 0)
      {
        printf("+ ");
      }
    if( terms[i].coef == 0 || terms[i].expon == 0)
      break;
  }

  printf("\n");
}

char compare(int a, int b)
{
  if( a > b) return '>';
  else if( a == b) return '=';
  else return '<';
}
// 새로운 항을 다항식에 추가한다.

void attatch(float coef, int expon)
{
  if( avail > MAX_TERMS ){
    fprintf(stderr, "항의 개수가 너무 많음\n");
    exit(1);
  }
  terms[avail].coef = coef;
  terms[avail++].expon = expon;
}
// C = A + B

void poly_add2(int As, int Ae, int Bs, int Be, int *Cs, int *Ce)
{
  float tempcoef;
  *Cs = avail;
  while( As <= Ae && Bs <= Be)
  {
    switch(compare(terms[As].expon, terms[Bs].expon)){
      case '>' :  // A의 차수 > B의 차수
          attatch(terms[As].coef, terms[As].expon);
          As++;
          break;
      case '=' :  // A의 차수 == B의 차수
          tempcoef = terms[As].coef + terms[Bs].coef;
          if(tempcoef)
            attatch(tempcoef, terms[As].expon);
          As++;
          Bs++;
          break;
      case '<' :    // A의 차수 < B의 차수
          attatch(terms[Bs].coef, terms[Bs].expon);
          Bs++;
          break;
    }
  }

  for(; As <= Ae ; As++){
    attatch(terms[As].coef, terms[As].expon);
  }
  for(; Bs <= Be ; Bs++){
    attatch(terms[Bs].coef, terms[Bs].expon);
  }
  *Ce = avail - 1;
}

// terms[MAX_TERMS] = { {8, 3}, {7, 1}, {1, 0}, {10, 3}, {3, 2}, {1, 0} };

int main()
{
  int Cs, Ce;
  displayPolyList();
  poly_add2( 0, 2, 3, 5, &Cs, &Ce);

  displayPolyList2();

}