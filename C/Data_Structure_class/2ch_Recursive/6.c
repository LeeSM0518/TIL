#include <stdio.h>

int Ackermann_Rec(int m, int n);
int Ackermann_while(int m, int n);

int main(void)
{
	int m, n, Ack_rec, Ack_while;
	printf("m과 n을 입력하세요 : ");
	scanf("%d %d", &m, &n);

	Ack_rec = Ackermann_Rec(m, n);
	Ack_while = Ackermann_while(m, n);

	printf("Ackermann_Rec 함수 답 : %d\n", Ack_rec);
	printf("Ackermann_while 함수 답 : %d\n", Ack_while);

}

int Ackermann_Rec(int m, int n)
{
	if (m == 0) return n + 1;
	else if (n == 0) return Ackermann_Rec(m - 1, 1);
	else return Ackermann_Rec(m - 1, Ackermann_Rec(m, n - 1));
}

int Ackermann_while(int m, int n)
{
	int i = 0;
	int Ack[10000];

	Ack[i++] = m;
	Ack[i] = n;

	while (1) {
		if (i == 0) {
			return Ack[i];
		}
		else if (Ack[i - 1] == 0) {
			Ack[i - 1] = Ack[i] + 1;
			i -= 1;
		}
		else if (Ack[i] == 0) {
			Ack[i - 1] = Ack[i - 1] - 1;
			Ack[i] = 1;
		}
		else {
			Ack[i + 1] = Ack[i] - 1;
			Ack[i] = Ack[i - 1];
			Ack[i - 1] -= 1;

			i += 1;
		}
	}
}