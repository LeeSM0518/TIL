public class FindDecimalNumber {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(solution(n));
    }

    public static int solution(int n) {
        int answer = n - 1;
        int i = 0, j= 0, x =0;
        int k[] = new int[n];

        for(i=2; i<=n; i++) {
            if(k[i-1] == 1) {
                answer--;
                continue;
            }
            for(j=2, x=i*j; j<=n && x<=n; j++, x=i*j) {
                if(k[x-1] == 1) {
                    answer--;
                    k[x-1]++;
                    continue;
                }
                else {
                    k[x-1] = 1;
                }
            }
        }
        return answer;
    }
}
