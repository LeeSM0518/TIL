package chapter03_linked_list;

public class Node<T> {
    T data;
    public Node<T> link;

    public Node(){}

    public Node (T data) {
        this.data = data;
    }
}