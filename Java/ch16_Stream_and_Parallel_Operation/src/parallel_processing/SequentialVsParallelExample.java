package parallel_processing;

import java.util.Arrays;
import java.util.List;

public class SequentialVsParallelExample {
    public static void work(int value) {

    }

    public static long testSequential(List<Integer> list) {
        long start = System.nanoTime();
        list.stream().forEach(a -> work(a));
        long end = System.nanoTime();
        long runTime = end - start;
        return runTime;
    }

    public static long testParallel(List<Integer> list) {
        long start = System.nanoTime();
        list.stream().parallel().forEach(a -> work(a));
        long end = System.nanoTime();
        long runTime = end - start;
        return runTime;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(0, 2);

        long timeSequential = testSequential(list);

        long timeParallel = testParallel(list);

        if(timeSequential < timeParallel) {
            System.out.println("성능 테스트 결과: 순차 처리가 더 빠름");
            System.out.println(timeSequential);
            System.out.println(timeParallel);
        } else {
            System.out.println("성능 테스트 결과: 병렬 처리가 더 빠름");
            System.out.println(timeSequential);
            System.out.println(timeParallel);
        }
    }
}
