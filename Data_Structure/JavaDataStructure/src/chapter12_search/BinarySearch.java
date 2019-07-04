package chapter12_search;

public class BinarySearch {

  public boolean search(int[] values, int key) {
    return recursiveSearch(values, 0, values.length, key);
  }

  private boolean recursiveSearch(int[] values, int start, int end, int key) {
    boolean result = false;

    if (start <= end) {
      int middle = (start + end) / 2;
      if (key == values[middle]) {
        result = true;
      } else if (key < values[middle]) {
        result = recursiveSearch(values, start, middle - 1, key);
      } else {
        result = recursiveSearch(values, middle + 1, end, key);
      }
    }

    return result;
  }

  public static void main(String[] args) {
    BinarySearch binarySearch = new BinarySearch();
    int[] array = {10, 20, 50, 60, 70, 80};

    System.out.println("60 검색 : " + binarySearch.search(array, 60));
    System.out.println("65 검색 : " + binarySearch.search(array, 65));
  }

}
