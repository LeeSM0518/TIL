package chapter14_sorting;

public class SelectionSort {

  public int[] sort(int[] list) {
    int[] sorted = list.clone();

    for (int i = 0; i < list.length - 1; i++) {
      int least = i;

      // 최소값 탐색
      for (int j = i + 1; j < list.length; j++) {
        if (sorted[j] < sorted[least]) least = j;
      }

      // 최소값 위치랑 i 위치랑 값 교체
      int temp = sorted[i];
      sorted[i] = sorted[least];
      sorted[least] = temp;
    }

    return sorted;
  }

  public static void main(String[] args) {
    SelectionSort selectionSort = new SelectionSort();

    int[] list = {5, 1, 3, 7, 2, 9};

    int[] answer = selectionSort.sort(list);

    for (int i : answer) {
      System.out.println(i);
    }
  }

}
