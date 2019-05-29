package chapter07_queue;

public class LinkedNode<T> extends Node {

    public LinkedNode link;

    public LinkedNode(T data) {
        super(data);
    }

    public LinkedNode(T data, LinkedNode link) {
        super(data);
        this.link = link;
    }
}