package chapter12_search;

public class IndexSequentialSearch {

  private int[] values;
  private IndexType[] indexTable;
  private int indexSize;

  public IndexSequentialSearch(int[] values, int indexSize) {
    this.values = values;
    this.indexSize = indexSize;
    indexTable = new IndexType[indexSize];

    int ratio = (values.length % indexSize == 0) ? values.length / indexSize : values.length / indexSize + 1;

    for (int i = 0; i < indexSize; i++) {
      indexTable[i] = new IndexType(i * ratio, values[i * ratio]);
    }
  }

  public boolean search(int key) {
    boolean success = false;

    if (key >= values[0] && key <= values[values.length - 1]) {
      int i, start, end;

      for (i = 0; i < indexSize; i++) {
        if (indexTable[i].key > key) break;
      }

      if (i < indexSize) {
        start = indexTable[i - 1].position;
        end = indexTable[i].position - 1;
      } else {
        start = indexTable[i - 1].position;
        end = values.length - 1;
      }

      success = rangeSearch(start, end, key);
    }

    return success;
  }

  private boolean rangeSearch(int start, int end, int key) {
    int i;

    for (i = start; i <= end && values[i] < key; i++);

    return i <= end && values[i] == key;
  }

  public void showIndexTable() {
    System.out.println("인덱스 테이블");
    System.out.println("위치, 키");
    System.out.println("================");
    for (IndexType index : indexTable) {
      System.out.println(index.position + ", " + index.key);
    }
    System.out.println();
  }

  public void showArray() {
    System.out.println("자료 배열");
    System.out.println("================");
    for(int i = 0; i < values.length; i++) {
      System.out.println(i + ", " + values[i]);
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int[] values = {10, 20, 50, 60, 70, 80};
    IndexSequentialSearch indexSequentialSearch = new IndexSequentialSearch(values, 3);

    indexSequentialSearch.showArray();
    indexSequentialSearch.showIndexTable();

    System.out.println("60 검색 = " + indexSequentialSearch.search(60));
    System.out.println("65 검색 = " + indexSequentialSearch.search(65));
  }


}
