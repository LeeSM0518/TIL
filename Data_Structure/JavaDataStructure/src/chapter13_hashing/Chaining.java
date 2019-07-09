package chapter13_hashing;

public class Chaining {

  public Node[] hashTable;
  public final int TABLE_SIZE;

  public Chaining(int size) {
    hashTable = new Node[size];
    TABLE_SIZE = size;
  }

  private int transform(String str) {
    int sum = 0;

    for (int i = 0; i < str.length(); i++) {
      sum += str.charAt(i);
    }

    return sum;
  }

  private int hashFunction(String str) {
    return transform(str) % TABLE_SIZE;
  }

  public void add(String item) {
    int hashValue = hashFunction(item);
    Node newNode = new Node(item);
    Node currentNode = hashTable[hashValue];
    Node beforeNode = currentNode;

    while (currentNode != null) {
      if (currentNode.item.equals(item)) {
        System.out.println("이미 탐색 키가 저장되어 있음");
        return;
      }
      beforeNode = currentNode;
      currentNode = currentNode.link;
    }

    if (beforeNode != null) beforeNode.link = newNode;
    else hashTable[hashValue] = newNode;
  }

  public void search(String str) {
    int hashValue = hashFunction(str);
    Node node = hashTable[hashValue];

    while (node != null) {
      if (node.item.equals(str)) {
        System.out.println("키 발견 : " + hashValue);
        return;
      }
      node = node.link;
    }

    System.out.println("키를 찾지 못했음");
  }

  public static void main(String[] args) {
    Chaining chain = new Chaining(11);

    chain.add("a");
    chain.add("b");
    chain.add("c");

    chain.search("a");
    chain.search("b");
    chain.search("d");
  }

}
