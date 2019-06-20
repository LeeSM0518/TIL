package chapter10_heap;

public class Heap {

  private int[] nodes;
  private final int MAX_COUNT;
  private int currentCount;

  public Heap(final int maxCount) {
    this.MAX_COUNT = maxCount;
    nodes = new int[maxCount +1];
  }

  public void insertMaxHeap(final int value) {
    if (currentCount == MAX_COUNT) {
      System.out.println("히프가 가득 찼습니다.");
      return;
    }

      int i = ++currentCount;

      while (i != 1 && value > nodes[i/2]) {
        nodes[i] = nodes[i/2];
        i /= 2;
      }

      nodes[i] = value;
  }

  public int removeMaxHeap() {
    if (currentCount == 0) {
      System.out.println("히프가 비어있습니다.");
      return 0;
    }

    int parent = 1, child = 2;
    int returnNode = nodes[parent];
  }

  public int[] getNodes() {
    return nodes;
  }

  public static void main(String[] args) {
    Heap heap = new Heap(5);

    heap.insertMaxHeap(3);
    heap.insertMaxHeap(1);
    heap.insertMaxHeap(2);

    for (int value : heap.getNodes()) {
      System.out.println(value);
    }
  }

}