package check_problem;

import java.util.function.IntBinaryOperator;

public class LambdaExample2 {
    private static int[] scores = { 10, 50, 3 };

    public static int maxOrMin(IntBinaryOperator operator) {
        int result = scores[0];
        for(int score : scores) {
            result = operator.applyAsInt(result, score);
        }
        return result;
    }

    public static void main(String[] args) {
        // 최대값 얻기
        int max = maxOrMin( (x,y) -> (x>y) ? x : y);
        System.out.println("최대값: " + max);

        // 최소값 얻기
        int min = maxOrMin( (x,y) -> (x>y) ? y : x);
        System.out.println("최소값: " + min);
    }
}
