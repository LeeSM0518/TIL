package chapter03_linked_list;

class LinkedList<T> {

    private int currentCount;
    private Node<T> headerNode;

    LinkedList() {
        this.headerNode = new Node<>();
        this.currentCount = 0;
    }

    T getLinkedListData(int position) {
        try {
            // 헤더 노드 저장
            Node<T> node = this.headerNode;

            // 찾는 위치까지 다음 링크
            for (int i = 0; i <= position; i++) {
                node = node.link;
            }

            // 찾은 위치의 데이터 반환
            return node.data;

        } catch (NullPointerException e) {
            return null;
        }
    }

    void addLinkedListData(int position, T data) {
        Node newNode = new Node<>(data);    // 새롭게 저장할 노드
        Node preNode = this.headerNode;     // 이전 노드

        try {
            // 이전 노드를 삽입할 노드 위치 전까지 이동
            for (int i = 0; i < position; i++) {
                preNode = preNode.link;
            }

            // 추가할 노드의 다음 노드 지정
            newNode.link = preNode.link;
            // 추가할 노드의 이전 노드 지정
            preNode.link = newNode;

            currentCount++;

        } catch (NullPointerException e) {
            System.out.println("자료를 추가할 수 없는 위치입니다.");
        }
    }

    void removeLinkedListData(int position) {
        Node delNode;   // 지울 노드
        Node preNode;   // 지울 노드의 이전 노드

        preNode = this.headerNode;

        try {
            // 지울 노드 이전 까지 이동
            for (int i = 0; i < position; i++) {
                preNode = preNode.link;
            }

            // 지울 노드 저장
            delNode = preNode.link;
            // 이전 노드의 링크를 지울 노드의 다음 링크로 저장
            preNode.link = delNode.link;

            // 메모리 해제
            delNode = null;

            this.currentCount--;

        } catch (NullPointerException e) {
            System.out.println("자료를 제거할 수 없는 위치입니다.");
        }
    }

    void displayList() {
        for (int i = 0; i < this.currentCount; i++) {
            System.out.println("[" + i + "], " + this.getLinkedListData(i));
        }
    }

    void iterateLinkedList() {
        int count = 0;
        Node node = this.headerNode.link;

        while (node != null) {
            System.out.println("[" + count + "], " + node.data);
            count++;
            node = node.link;
        }
        System.out.println("노드 개수: " + count);
    }

    void concatLinkedList(LinkedList listB) {
        if (listB != null) {
            // this 연결리스트의 마지막 노드를 가리킬 노드
            Node node = this.headerNode;

            // 마지막 노드를 가리킬 때까지 루프를 돈다.
            while (node != null && node.link != null) {
                node = node.link;
            }

            // this 의 마지막 노드의 다음 노드로 listB 의 첫 번째 노드로 설정한다.
            node.link = listB.headerNode.link;
            listB = null;
        }
    }

    public int getCurrentCount() {
        return currentCount;
    }

}