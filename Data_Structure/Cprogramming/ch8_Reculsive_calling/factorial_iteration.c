#include <stdio.h>

int factorial_iter(int n)
{
    int ret = 1;
    int i = 1;

    for( i = n ; i > 1 ; i--){
        ret = ret * i;
    }

    return ret;
}

int main(void)
{
    int num;
    int result;

    printf("factorail interation : ");
    scanf("%d",&num);

    result = factorial_iter(num);

    printf("result = %d\n",result);
}