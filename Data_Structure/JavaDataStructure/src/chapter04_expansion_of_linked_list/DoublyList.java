package chapter04_expansion_of_linked_list;

public class DoublyList<T> {
    private int currentCount;
    private DoublyListNode<T> headerNode;

    DoublyList() {
        this.currentCount = 0;
        headerNode = new DoublyListNode<>();

        // 처음 리스트를 만들때 headerNode 의 왼쪽과 오른쪽 노드를
        // 자기 자신을 가리키도록 한다.
        headerNode.lLink = this.headerNode;
        headerNode.rLink = this.headerNode;
    }

    public T getNode(final int position) {
        DoublyListNode<T> node = this.headerNode;

        for (int i = 0; i <= position; i++) {
            node = node.rLink;
        }

        return node.data;
    }

    public void addNode(final int position, final T data) {
        DoublyListNode newNode = new DoublyListNode<>(data);
        DoublyListNode preNode = this.headerNode;

        for (int i = 0; i < position; i++) {
            preNode = preNode.rLink;
        }

        newNode.lLink = preNode;
        newNode.rLink = preNode.rLink;

        preNode.rLink = newNode;            // 이전 노드 처리
        newNode.rLink.lLink = newNode;      // 다음 노드 처리

        this.currentCount++;
    }

    public void removeNode(final int position) {
        DoublyListNode preNode = this.headerNode;

        for (int i = 0; i < position; i++) {
            preNode = preNode.rLink;
        }

        DoublyListNode delNode = preNode.rLink;     // 지울 노드 지정
        preNode.rLink = delNode.rLink;              // 이전 노드 처리
        delNode.rLink.lLink = preNode;              // 다음 노드 처리

        this.currentCount--;
    }

    public void displayList() {
        DoublyListNode node = this.headerNode.rLink;

        if (this.currentCount == 0) {
            System.out.println("자료가 없습니다.");
            return;
        } else {
            System.out.println("노드 개수: " + this.currentCount);

            for (int i = 0; i < currentCount; i++) {
                System.out.println("[" + i + "], " + node.data);
                node = node.rLink;
            }
        }
    }

    public static void main(String[] args) {
        DoublyList<Integer> list = new DoublyList<>();

        list.addNode(0, 1);
        list.addNode(1, 2);
        list.addNode(2, 3);

        list.displayList();

        list.removeNode(0);

        list.displayList();
    }

}