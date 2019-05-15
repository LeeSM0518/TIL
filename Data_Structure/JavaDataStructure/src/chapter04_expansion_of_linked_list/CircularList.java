package chapter04_expansion_of_linked_list;

public class CircularList<T> {

    int currentCount;
    Node<T> headerNode;

    CircularList() {
        this.currentCount = 0;
        headerNode = new Node<>();
    }

    T getCircularListData(int position) {
        Node<T> node = this.headerNode;

        for (int i = 0; i <= position; i++) {
            node = node.link;
        }

        return node.data;
    }

    void addCircularListData(int position, T data) {
        Node<T> newNode = new Node<>(data);
        Node<T> preNode = this.headerNode;

        // 추가할 위치의 이전까지 노드 이동
        for (int i = 0; i < position; i++) {
            preNode = preNode.link;
        }

        // 새로운 노드 추가
        newNode.link = preNode.link;
        preNode.link = newNode;

        currentCount++;

        // 만약 첫 번째 노드가 추가되었을 때, 다음 노드를 자기 자신으로 지정
        if (newNode.link == null) {
            newNode.link = newNode;
        }
    }

    void removeCircularListData(int position) {
        Node<T> preNode = this.headerNode;

        for (int i = 0; i < position; i++) {
            preNode = preNode.link;
        }

        Node<T> delNode = preNode.link;
        preNode.link = delNode.link;

        this.currentCount--;

        // 만약 현재 노드가 0개 있을 경우, 헤더 노드의 다음 노드를 null 로 지정해준다.
        if (this.currentCount == 0) this.headerNode.link = null;

        delNode = null;
    }

    void displayCircularList() {
        Node<T> node = this.headerNode.link;

        if (this.currentCount == 0) {
            System.out.println("자료가 없습니다.");
            return;
        }

        System.out.println("노드 개수: " + this.currentCount);

        for (int i = 0; i < this.currentCount; i++) {
            System.out.println("[" + i + "], " + node.data);
            node = node.link;
        }
    }

    public static void main(String[] args) {
        CircularList<String> circularList = new CircularList<>();

        circularList.addCircularListData(0, "A");
        circularList.displayCircularList();
        circularList.addCircularListData(0, "B");
        circularList.displayCircularList();
        circularList.addCircularListData(1, "C");
        circularList.displayCircularList();

        System.out.println();

        circularList.removeCircularListData(2);
        circularList.displayCircularList();
        circularList.removeCircularListData(1);
        circularList.displayCircularList();
        circularList.removeCircularListData(0);
        circularList.displayCircularList();
    }
}