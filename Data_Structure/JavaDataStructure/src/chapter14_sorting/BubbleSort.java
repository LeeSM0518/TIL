package chapter14_sorting;

public class BubbleSort {

  public static int[] sort(int[] list) {
    int[] sorted = list.clone();

    for (int i = list.length - 1; i > 0; i--)
      for (int j = 0; j < i; j++)
        if (sorted[j] > sorted[j + 1]) {
          int temp = sorted[j];
          sorted[j] = sorted[j + 1];
          sorted[j + 1] = temp;
        }

    return sorted;
  }

  public static void main(String[] args) {
    int[] list = {5, 1, 3, 7, 2, 9};

    int[] answer = sort(list);

    for (int i : answer) {
      System.out.println(i);
    }
  }

}
