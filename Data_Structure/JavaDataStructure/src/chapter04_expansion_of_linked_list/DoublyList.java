package chapter04_expansion_of_linked_list;

public class DoublyList<T> {
    int currentCount;
    DoublyListNode<T> headerNode;

    DoublyList() {
        this.currentCount = 0;
        headerNode = new DoublyListNode<>();
        headerNode.lLink = headerNode;
        headerNode.rLink = headerNode;
    }
}
