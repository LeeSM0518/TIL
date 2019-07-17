package chapter14_sorting;

public class QuickSort {

  int[] sorted;

  QuickSort(int[] list) {
    sorted = list;
  }

  public void sort() {
    recursiveSort(0, sorted.length - 1);
  }

  public void recursiveSort(int left, int right) {
    if (left < right) {
      int q = partition(left, right);
      recursiveSort(left, q - 1);
      recursiveSort(q + 1, right);
    }
  }

  private int partition(int left, int right) {
    int pivot = sorted[left], temp;
    int low = left, high = right + 1;

    do {
      do {
        low++;
      } while (low <= right && sorted[low] < pivot);
      do {
        high--;
      } while (high >= left && sorted[high] > pivot);

      if (low < high) {
        temp = sorted[low];
        sorted[low] = sorted[high];
        sorted[high] = temp;
      }
    } while (low < high);

    temp = sorted[left];
    sorted[left] = sorted[high];
    sorted[high] = temp;

    return high;
  }

  public static void main(String[] args) {
    int[] list = {5, 1, 3, 7, 2, 9};

    QuickSort quickSort = new QuickSort(list);
    quickSort.sort();

    for (int i : quickSort.sorted) {
      System.out.println(i);
    }
  }

}
