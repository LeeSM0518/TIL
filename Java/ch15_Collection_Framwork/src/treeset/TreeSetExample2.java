package treeset;

import java.util.NavigableSet;
import java.util.TreeSet;

public class TreeSetExample2 {
    public static void main(String[] args) {
        TreeSet<Integer> scores = new TreeSet<Integer>();
        scores.add(new Integer(87));
        scores.add(new Integer(98));
        scores.add(new Integer(75));
        scores.add(new Integer(95));
        scores.add(new Integer(80));

        NavigableSet<Integer> descenginSet = scores.descendingSet();
        for(Integer score : descenginSet) {
            System.out.println(score + " ");
        }
        System.out.println();

        NavigableSet<Integer> ascendingSet = descenginSet.descendingSet();

        for(Integer score : ascendingSet) {
            System.out.println(score + " ");
        }
    }
}
