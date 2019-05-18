package chapter02_array_list;

public class PolyNode<T, R> extends Node {

    private T key;
    private R value;

    public PolyNode(final T key, final R value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public R getData() {
        return value;
    }

}
