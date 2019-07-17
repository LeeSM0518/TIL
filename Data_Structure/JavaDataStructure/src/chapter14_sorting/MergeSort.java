package chapter14_sorting;

public class MergeSort {

  private static int[] merge(int[] list, int left, int mid, int right) {
    int[] sorted = list.clone();
    int i = left, j = mid + 1, k = left;

    while (i <= mid && j <= right) {
      if (list[i] <= list[j]) sorted[k++] = list[i++];
      else sorted[k++] = list[j++];
    }

    if (i > mid)
      for (int l = j; l <= right; l++)
        sorted[k++] = sorted[l];
    else
      for (int l = i; l <= mid; l++)
        sorted[k++] = list[l];

    return sorted;
  }

  public static int[] sort(int[] list, int left, int right) {
    int[] sorted = list.clone();

    int mid;
    if (left < right) {
      mid = (left + right) / 2;
      sorted = sort(sorted, left, mid);
      sorted = sort(sorted, mid + 1, right);
      sorted = merge(sorted, left, mid, right);
    }

    return sorted;
  }

  public static void main(String[] args) {
    int[] list = {5, 1, 3, 7, 2, 9};

    int[] answer = sort(list, 0, list.length - 1);

    for (int i : answer) {
      System.out.println(i);
    }
  }

}
