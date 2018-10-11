#include <stdio.h>
#define MAX(a,b) (((a)>(b)) ? (a) : (b) )
#define MAX_DEGREE 101

typedef struct{                 // 다항식 구조체 타입 선언
  int degree;                   // 다항식의 차수
  float coef[MAX_DEGREE];       // 다항식의 계수
}polynomial;

void displayPolyList(polynomial pList)
{
  int i, j;

  for( i = pList.degree, j = 0 ; i >= 0 ; i--, j++)
  {
    if( pList.coef[j] != 0){
      printf("%.1f x^%d ", pList.coef[j], i);
      if( i != 0)
      {
        printf("+ ");
      }
    }
    
  }
  printf("\n");
}

polynomial poly_add1(polynomial A, polynomial B)
{
  // C = A + B 
  polynomial C;                         // 결과 다항식
  int Apos = 0, Bpos = 0, Cpos = 0;     // 배열 인덱스 변수
  int degree_a = A.degree;
  int degree_b = B.degree;
  C.degree = MAX(A.degree, B.degree);   // 결과 다항식 차수

  while( Apos <= A.degree && Bpos <= B.degree){
    if(degree_a > degree_b){              // A항 차수 > B항 차수
      C.coef[Cpos++] = A.coef[Apos++];
      degree_a--;
    }
    else if(degree_a == degree_b){        // A항 차수 == B항 차수
      C.coef[Cpos++] = A.coef[Apos++] + B.coef[Bpos++];
      degree_a--;
      degree_b--;
    }
    else{   // B항 차수 > A항 차수
      C.coef[Cpos++] = B.coef[Bpos++];
      degree_b--;
    }
  }
  return C;
}

int main()
{
  polynomial a = { 3, {6, 0, 0, 9}};
  displayPolyList(a);

  polynomial b = { 3, {0, 8, 0, 0}};
  displayPolyList(b);
  
  polynomial c;
  c = poly_add1(a,b);
  displayPolyList(c);
}