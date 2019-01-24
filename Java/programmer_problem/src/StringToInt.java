public class StringToInt {
    public static void main(String[] args) {
        String test = "-1234";
        System.out.println(solution(test));
    }

    public static int solution(String s) {
        int answer = 0;
        answer = Integer.parseInt(s);
        return answer;
    }
}
