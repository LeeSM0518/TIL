package chapter04_expansion_of_linked_list;

public class DoublyListNode<T> {

    T data;
    DoublyListNode<T> lLink;
    DoublyListNode<T> rLink;

    DoublyListNode() {
    }

    DoublyListNode(T data) {
        this.data = data;
    }

    DoublyListNode(DoublyListNode lLink, DoublyListNode rLink) {
        this.lLink = lLink;
        this.rLink = rLink;
    }

    DoublyListNode(T data, DoublyListNode lLink, DoublyListNode rLink) {
        this.data = data;
    }
}
