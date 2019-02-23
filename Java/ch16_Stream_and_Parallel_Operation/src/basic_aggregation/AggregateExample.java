package basic_aggregation;

import java.util.Arrays;

public class AggregateExample {
    public static void main(String[] args) {
        long count = Arrays.stream(new int[] { 1, 2, 3, 4, 5 })
                .filter(n -> n%2==0)
                .count();   // 요소 개수
        System.out.println("2의 배수 개수: " + count);

        long sum = Arrays.stream(new int[] {1, 2, 3, 4, 5})
                .filter(n -> n%2==0)
                .sum();     // 요소 총합
        System.out.println("2의 배수의 합: " + sum);

        double avg = Arrays.stream(new int[] {1, 2, 3, 4, 5})
                .filter(n -> n%2==0)
                .average()  // 요소 평균
                .getAsDouble();
        System.out.println("2의 배수의 평균: " + avg);

        int max = Arrays.stream(new int[] {1 ,2 ,3 ,4 ,5 })
                .filter(n -> n%2==0)
                .max()      // 요소 최대값
                .getAsInt();
        System.out.println("최대값: " + max);

        int min = Arrays.stream(new int[] {1, 2, 3, 4, 5})
                .filter(n -> n%2==0)
                .min()      // 요소 최소값
                .getAsInt();
        System.out.println("최소값: " + min);

        int first = Arrays.stream(new int[] {1, 2, 3, 4, 5})
                .filter(n -> n%3==0)
                .findFirst()    // 첫 번재 요소
                .getAsInt();
        System.out.println("첫번째 3의 배수: " + first);
    }
}