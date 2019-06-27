package chapter10_heap;

public class Heap {

  private int[] nodes;
  private final int MAX_COUNT;
  private int currentCount;

  public Heap(final int maxCount) {
    this.MAX_COUNT = maxCount;
    nodes = new int[maxCount + 1];
  }

  public void insertMaxHeap(final int value) {
    if (heapFull()) return;

    int i = ++currentCount;

    while (i != 1 && value > nodes[i / 2]) {
      nodes[i] = nodes[i / 2];
      i /= 2;
    }

    nodes[i] = value;
  }

  public void insertMinHeap(final int value) {
    if (heapFull()) return;

    int i = ++currentCount;

    while (i != 1 && value < nodes[i / 2]) {
      nodes[i] = nodes[i / 2];
      i /= 2;
    }

    nodes[i] = value;
  }

  public int removeMaxHeap() {
    if (heapEmpty()) return 0;

    int parent = 1, child = 2;
    int returnNode = nodes[parent];
    int lastNodeIndex = currentCount--;

    while (child <= currentCount) {
      if (child < currentCount && nodes[child] < nodes[child + 1]) child++;

      if (nodes[lastNodeIndex] >= nodes[child]) break;

      nodes[parent] = nodes[child];
      parent = child;
      child *= 2;
    }

    nodes[parent] = nodes[lastNodeIndex];
    nodes[lastNodeIndex] = 0;

    return returnNode;
  }

  public int removeMinHeap() {
    if (heapEmpty()) return 0;

    int parent = 1, child = 2;
    int returnNode = nodes[parent];
    int lastNodeIndex = currentCount--;

    while (child <= currentCount) {
      if (child < currentCount && nodes[child] > nodes[child + 1]) child++;

      if (nodes[lastNodeIndex] <= nodes[child]) break;

      nodes[parent] = nodes[child];
      parent = child;
      child *= 2;
    }

    nodes[parent] = nodes[lastNodeIndex];
    nodes[lastNodeIndex] = 0;

    return returnNode;
  }

  public void displayHeap() {
    if (heapEmpty()) {
      System.out.println("히프가 비어있습니다.");
    } else {
      for (int i = 1; i <= currentCount; i++) {
        System.out.println("[" + i + "], " + nodes[i]);
      }
    }
    System.out.println();
  }

  private boolean heapFull() {
    return this.currentCount == MAX_COUNT;
  }

  private boolean heapEmpty() {
    return this.currentCount == 0;
  }

  public int[] getNodes() {
    return nodes;
  }

  public static void main(String[] args) {
    Heap heap = new Heap(5);

    heap.insertMinHeap(3);
    heap.insertMinHeap(2);
    heap.insertMinHeap(1);

    heap.displayHeap();

    System.out.println(heap.removeMinHeap());
    System.out.println();

    heap.displayHeap();
  }

}