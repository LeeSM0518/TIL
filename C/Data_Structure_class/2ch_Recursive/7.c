// 결론은 재귀함수는 자기 자신 함수를 계속 불러오고 반환시키며 시간이 오래걸리지만
// 반복함수는 상수 시간이 걸리므로 재귀 함수 보다 훨씬 빠르다.
#include <stdio.h>
#include <time.h>

int fib(int n)
{
	int ret = 0;

	if (n == 0) {
		ret = 0;
	}
	else if (n == 1) {
		ret = 1;
	}

	else {
		ret = fib(n - 1) + fib(n - 2);
	}

	return ret;
}

int fib_iter(int n) {
	int ret = 0;

	if (n<2) {
		ret = n;
	}

	else {
		int i = 0, temp = 0, current = 1, last = 0;

		for (i = 2; i <= n; i++) {
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
	clock_t start1, start2, end1, end2;
	float time1, time2;

	printf("fibonacci input integer: ");
	scanf("%d", &num);

	start1 = clock();
	result[0] = fib(num);
	end1 = clock();
	time1 = (end1 - start1);
	printf("순환 피보나치 수행 시간 : %f\n", time1);

	start2 = clock();
	result[1] = fib_iter(num);
	end2 = clock();
	time2 = (end2 - start2);
	printf("반복 피보나치 수행 시간 : %f\n", time2);

	printf("fib Reculsive result = %d\nfib iteration result = %d\n", result[0], result[1]);
}