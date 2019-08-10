package linked_list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedListExample {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new LinkedList<String>();

        long startTime;
        long endTime;

        startTime = System.nanoTime();
        for(int i=0; i<10; i++) {
            list1.add(0, String.valueOf(i));
        }
        endTime = System.nanoTime();

        System.out.println("ArrayList 걸린시간: \t" +
                (endTime-startTime) + " ns");

        startTime = System.nanoTime();
        for(int i=0; i<10; i++) {
            list2.add(0, String.valueOf(i));
        }
        endTime = System.nanoTime();

        System.out.println("LinkedList 걸린시간: \t" +
                (endTime-startTime) + " ns");

        startTime = System.nanoTime();
        for(int i=0; i<10; i++) {
            list1.add(String.valueOf(i));
        }
        endTime = System.nanoTime();

        System.out.println("ArrayList 걸린시간: \t" +
                (endTime-startTime) + " ns");

        startTime = System.nanoTime();
        for(int i=0; i<10; i++) {
            list2.add(String.valueOf(i));
        }
        endTime = System.nanoTime();

        System.out.println("LinkedList 걸린시간: \t" +
                (endTime-startTime) + " ns");
    }
}