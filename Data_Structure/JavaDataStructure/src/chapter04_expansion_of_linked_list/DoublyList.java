package chapter04_expansion_of_linked_list;

public class DoublyList<T> {
    int currentCount;
    DoublyListNode<T> headerNode;

    DoublyList() {
        this.currentCount = 0;
        headerNode = new DoublyListNode<>();

        // 처음 리스트를 만들때 headerNode 의 왼쪽과 오른쪽 노드를
        // 자기 자신을 가리키도록 한다.
        headerNode.lLink = this.headerNode;
        headerNode.rLink = this.headerNode;
    }



}