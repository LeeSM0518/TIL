#include <stdio.h>
#include <time.h>
#include <stdlib.h>

typedef struct complex{
  int real;
  int imagin;
} complex;

complex complex_add(complex c1, complex c2);
complex complex_sub(complex c1, complex c2);
complex random_input(complex c1);
void print_complex(complex c1);

int main(void)
{
  complex a, b, c;

  srand((unsigned)time(NULL));

  a = random_input(a);
  b = random_input(b);

  printf("a의 복소수\n");
  print_complex(a);
  printf("b의 복소수\n");
  print_complex(b);

  printf("a + b = \n");
  c = complex_add(a, b);
  print_complex(c);

  printf("a - b = \n");
  c = complex_sub(a, b);
  print_complex(c);

}

complex complex_add(complex c1, complex c2)
{
  complex c3;

  c3.real = c1.real + c2.real;
  c3.imagin = c1.imagin + c2.imagin;

  return c3;
}

complex complex_sub(complex c1, complex c2)
{
  {
    complex c3;

    c3.real = c1.real - c2.real;
    c3.imagin = c1.imagin - c2.imagin;

    return c3;
  }
}

complex random_input(complex c1)
{
  c1.imagin = rand() % 10 + 1;
  c1.real = rand() % 10 + 1;

  return c1;
}

void print_complex(complex c1)
{
  printf("%d + %d i \n\n", c1.real, c1.imagin);
}