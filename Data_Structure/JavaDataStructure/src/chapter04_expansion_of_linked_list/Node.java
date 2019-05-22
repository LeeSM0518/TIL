package chapter04_expansion_of_linked_list;

public class Node<T> {

    T data;
    Node<T> link;

    Node() {
    }

    Node(T data) {
        this.data = data;
    }
}