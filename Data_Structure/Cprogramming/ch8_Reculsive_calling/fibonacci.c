#include <stdio.h>

int fib(int n)
{
    int ret = 0;

    if( n == 0 ){
        ret = 0;
    }
    else if( n == 1){
        ret = 1;
    }

    else{
        ret = fib(n-1) + fib(n-2);
    }

    return ret;
}

int fib_iter(int n){
    int ret = 0;

    if(n<2){
        ret = n;
    }

    else{
        int i = 0, temp = 0, current = 1, last = 0;

        for(i=2; i<=n; i++){
            temp = current;
            current += last;
            last = temp;
        }
        ret = current;
    }
    return ret;
}

int main(void)
{
    int num;
    int result[2];

    printf("fibonacci input integer: ");
    scanf("%d",&num);

    result[0] = fib(num);
    result[1] = fib_iter(num);

    printf("fib Reculsive result = %d\nfib iteration result = %d\n",result[0],result[1]);
}