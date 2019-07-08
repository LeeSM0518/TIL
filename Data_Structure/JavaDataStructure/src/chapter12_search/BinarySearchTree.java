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

  public void insert(int key, char value) {
    Node parentNode = getParentNode(key);
    if (rootNode == null || parentNode != null) insertNewNode(new Node(key, value), parentNode);
  }

  private void insertNewNode(Node newNode, Node parentNode) {
    if (rootNode == null) rootNode = newNode;
    else {
      if (newNode.key < parentNode.key) parentNode.leftChild = newNode;
      else parentNode.rightChild = newNode;
    }
  }

  private Node getParentNode(int key) {
    Node parentNode = null;
    Node currentNode = rootNode;

    while (currentNode != null) {
      if (key == currentNode.key) return null;
      else if (key < currentNode.key) {
        parentNode = currentNode;
        currentNode = currentNode.leftChild;
      } else {
        parentNode = currentNode;
        currentNode = currentNode.rightChild;
      }
    }

    return parentNode;
  }

  public void remove(int key) {
    Node parentNode = getParentNode(key);
    Node deleteNode = search(key);

    if (deleteNode != null && rootNode != null && parentNode != null) {
    } else if (deleteNode.leftChild == null && deleteNode.rightChild == null) {
      deleteNodeNoChild(parentNode, deleteNode);
    } else if (deleteNode.leftChild != null && deleteNode.rightChild != null) {
      deleteNodeOneChild(parentNode, deleteNode);
    } else {
      deleteNodeTwoChildren(parentNode, deleteNode);
    }
  }

  private void deleteNodeNoChild(Node parentNode, Node deleteNode) {
    if (parentNode != null) {
      if (parentNode.leftChild == deleteNode) {
        parentNode.leftChild = null;
      } else {
        parentNode.rightChild = null;
      }
    } else {
      rootNode = null;
    }
  }

  private void deleteNodeOneChild(Node parentNode, Node deleteNode) {
    Node childNode;

    if (deleteNode.leftChild != null) {
      childNode = deleteNode.leftChild;
    } else {
      childNode = deleteNode.rightChild;
    }

    if (parentNode != null) {
      if (parentNode.leftChild == deleteNode) {
        parentNode.leftChild = childNode;
      } else {
        parentNode.rightChild = childNode;
      }
    } else {
      rootNode = childNode;
    }
  }

  private void deleteNodeTwoChildren(Node parentNode, Node deleteNode) {
    Node predecessor = deleteNode;
    Node successor = deleteNode.leftChild;

    while (successor.rightChild != null) {
      predecessor = successor;
      successor = successor.rightChild;
    }

    predecessor.rightChild = successor.leftChild;
    successor.leftChild = deleteNode.leftChild;
    successor.rightChild = deleteNode.rightChild;

    if (parentNode != null) {
      if (parentNode.leftChild == deleteNode) {
        parentNode.leftChild = successor;
      } else {
        parentNode.rightChild = successor;
      }
    } else {
      rootNode = successor;
    }
  }

  public void display() {
    displayTree(rootNode, 0, '-');
  }

  private void displayTree(Node node, int level, char type) {
    if (node != null) {
      for (int i = 0; i < level; i++) {
        System.out.print(" ");
      }

      System.out.println("-(" + type + ") - key: " + node.key + ", value: " + node.value);

      displayTree(node.leftChild, level + 1, 'L');
      displayTree(node.rightChild, level + 1, 'R');
    }
  }

  public static void main(String[] args) {
    BinarySearchTree binarySearchTree = new BinarySearchTree();

    binarySearchTree.insert(70, 'A');
    binarySearchTree.insert(40, 'B');
    binarySearchTree.insert(90, 'C');
    binarySearchTree.insert(20, 'D');
    binarySearchTree.insert(60, 'E');
    binarySearchTree.insert(80, 'F');
    binarySearchTree.insert(100, 'G');
    binarySearchTree.insert(10, 'H');
    binarySearchTree.insert(30, 'I');
    binarySearchTree.insert(50, 'J');

    binarySearchTree.display();

    System.out.println("키 30 검색 : " + binarySearchTree.search(30).key + ", " + binarySearchTree.search(30).value);
    System.out.println("키 70 검색 : " + binarySearchTree.search(70).key + ", " + binarySearchTree.search(70).value);

    binarySearchTree.remove(70);

    binarySearchTree.display();
  }

}