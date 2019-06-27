package chapter10_heap;

public class Sort {

  public static int[] heapSort (int[] intArray) {
    Heap heap = new Heap(intArray.length);
    int[] returnArray = new int[intArray.length];

    for (int i : intArray) {
      heap.insertMaxHeap(i);
    }

    for (int i = 0; i < returnArray.length; i++) {
      returnArray[i] = heap.removeMaxHeap();
    }

    return returnArray;
  }

  public static void main(String[] args) {
    int[] arr = new int[]{1, 5, 3, 8, 9, 10, 4, 7};
    int[] sortedArr = heapSort(arr);

    for (int i : sortedArr) {
      System.out.println(i);
    }
  }
}
