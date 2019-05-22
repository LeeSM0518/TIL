package chapter05_stack;

public class LinkedStackNode<T> extends StackNode{

    public LinkedStackNode link = null;

    public LinkedStackNode(T data) {
        this.data = data;
    }
}
