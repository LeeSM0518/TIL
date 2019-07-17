package chapter14_sorting;

    import java.util.LinkedList;
    import java.util.Queue;

public class RadixSort {

  private final int BUCKETS = 10;
  private final int DIGITS = 2;
  private Queue[] queues = new Queue[BUCKETS];

  RadixSort() {
    for (int i = 0; i < queues.length; i++) {
      queues[i] = new LinkedList<Integer>();
    }
  }

  public int[] sort(int[] list) {
    int[] sorted = list.clone();
    int factor = 1;

    for (int i = 0; i < DIGITS; i++) {
      for (int j = 0; j < list.length; j++)
        queues[(sorted[j] / factor) % 10].add(sorted[j]);

      for (int k = 0, l = 0; k < BUCKETS; k++) {
        while (!queues[k].isEmpty()) {
          sorted[l++] = (int) queues[k].poll();
        }
      }

      factor *= 10;
    }

    return sorted;
  }

  public static void main(String[] args) {
    int[] list = {51, 12, 33, 74, 26, 95};

    RadixSort radixSort = new RadixSort();

    int[] sorted = radixSort.sort(list);

    for (int i : sorted) {
      System.out.println(i);
    }
  }

}