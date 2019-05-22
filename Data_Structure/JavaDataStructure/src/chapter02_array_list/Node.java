package chapter02_array_list;

public class Node<T> {
    private T data;

    public Node() {
    }

    public Node(final T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
