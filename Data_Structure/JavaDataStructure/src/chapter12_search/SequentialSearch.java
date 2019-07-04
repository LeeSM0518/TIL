package chapter12_search;

public class SequentialSearch {

  public boolean notSorted(int[] values, int key) {
    int i;
    for (i = 0; i < values.length && values[i] != key; i++);

    return i < values.length;
  }

  public boolean sorted(int[] values, int key) {
    int i;
    for (i = 0; i < values.length && values[i] < key; i++);

    return i < values.length && values[i] == key;
  }

  public void showArray(int[] values) {
    System.out.println("position, key");
    System.out.println("================");

    for (int i = 0; i < values.length; i++) {
      System.out.println(i + ", " + values[i]);
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int[] notSortedArray = {80, 20, 70, 50};
    int[] sortedArray = {20, 50, 70, 80};
    SequentialSearch sequentialSearch = new SequentialSearch();

    sequentialSearch.showArray(notSortedArray);
    System.out.println("70 검색 : " + sequentialSearch.notSorted(notSortedArray, 70));

    sequentialSearch.showArray(sortedArray);
    System.out.println("70 검색 : " + sequentialSearch.sorted(sortedArray, 70));
  }

}
