package chapter14_sorting;

public class ShellSort {

  public static int[] insertSort(int[] list, int first, int last, int gap) {
    int[] sorted = list.clone();

    int j;
    for (int i = first + gap; i <= last; i += gap) {
      int key = sorted[i];
      for (j = i - gap; j >= first && key < sorted[j]; j -= gap)
        sorted[j + gap] = sorted[j];
      sorted[j + gap] = key;
    }

    return sorted;
  }

  private static int[] sort(int[] list) {
    int[] sorted = list.clone();

    for (int gap = list.length / 2; gap > 0; gap = gap / 2) {
      if (gap % 2 == 0) gap++;
      for (int i = 0; i < gap; i++) {
        sorted = insertSort(sorted, i, sorted.length - 1, gap);
      }
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
