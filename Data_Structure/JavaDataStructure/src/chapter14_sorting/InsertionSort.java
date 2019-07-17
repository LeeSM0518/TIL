package chapter14_sorting;

public class InsertionSort {

  public int[] sort(int[] list) {
    int[] sorted = list.clone();
    int j;

    for (int i = 1; i < list.length; i++) {
      int key = sorted[i];
      for (j = i - 1; j >= 0 && sorted[j] > key; j--)
        sorted[j + 1] = sorted[j];
      sorted[j + 1] = key;
    }

    return sorted;
  }

  public static void main(String[] args) {
    int[] list = {5, 1, 3, 7, 2, 9};

    InsertionSort insertionSort = new InsertionSort();

    int[] answer = insertionSort.sort(list);

    for (int i : answer) {
      System.out.println(i);
    }
  }

}
