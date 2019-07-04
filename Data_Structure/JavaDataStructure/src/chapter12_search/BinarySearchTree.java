package chapter12_search;

public class BinarySearchTree {

  private Node rootNode = null;

  public Node search(int key) {
    Node node;

    node = rootNode;

    while (node != null) {
      if (key == node.key) break;
      else if (key < node.key) node = node.leftChild;
      else node = node.rightChild;
    }

    return node;
  }

}