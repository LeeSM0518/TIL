package mapping;

import java.util.Arrays;
import java.util.stream.IntStream;

public class AsDoubleStreamAndBoxedExample {
    public static void main(String[] args) {
        int[] intArray = { 1, 2, 3, 4, 5 };

        IntStream intStream = Arrays.stream(intArray);
        intStream
                .asDoubleStream()   // DoubleStream 생성
                .forEach(d -> System.out.println(d));

        System.out.println();

        intStream = Arrays.stream(intArray);
        intStream
                .boxed()            // Stream<Integer> 생성
                .forEach(obj -> System.out.println(obj.intValue()));
    }
}