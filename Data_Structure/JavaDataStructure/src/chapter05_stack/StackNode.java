package chapter05_stack;

public class StackNode<T> {

    T data;

    public StackNode() {}

    public StackNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
