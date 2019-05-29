package chapter07_queue;

public class ArrayQueue {

    int maxCount;       // 최대 자료 개수
    int currentCount;   // 현재 자료 개수
    int front;          // 프런트 위치
    int rear;           // 리어 위치
    Node[] nodes;       // 데이터 배열

    public ArrayQueue(final int maxCount) {
        this.maxCount = maxCount;
        currentCount = 0;

        // 프런트와 리어 위치 초기화
        front = -1;
        rear = -1;

        nodes = new Node[maxCount];
    }

    public <T> void enqueue(final T data) {

        if (!isFull()) {                        // 큐가 가득 차 있는지 점검
            rear++;                             // 리어 위치 변경
            nodes[rear] = new Node<>(data);     // 새로운 자료 추가
            currentCount++;                     // 현재 자료 개수 증가
        } else {
            System.out.println("큐가 가득 찼습니다.");
        }

    }

    public Node dequeue() {
        Node node = null;

        if (!isEmpty()) {           // 큐가 비어 있는지 점검
            front++;                // 프론트 위치 변경
            node = nodes[front];    // 반환 노드 복사
            currentCount--;         // 현재 노드 개수 감소
        } else {
            System.out.println("큐가 비어 있습니다.");
        }

        return node;
    }

    public Node peek() {
        Node node = null;

        if (!isEmpty()) {
            node = nodes[front+1];
        } else {
            System.out.println("큐가 비어 있습니다.");
        }

        return node;
    }

    public void displayQueue() {
        if (!isEmpty()) {
            for (int i = front + 1; i <= rear; i++) {
                System.out.println("[ " + i + " ] - [ " + nodes[i].data + " ]");
            }
        } else {
            System.out.println("큐가 비어 있습니다.");
        }
        System.out.println();
    }

    boolean isEmpty() {
        return currentCount == 0;
    }

    boolean isFull() {
        return (currentCount == maxCount) || (rear == maxCount - 1);
    }

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(10);
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");
        arrayQueue.enqueue("C");
        arrayQueue.enqueue("D");
        arrayQueue.displayQueue();

        arrayQueue.dequeue();
        arrayQueue.displayQueue();

        System.out.println("peek: " + arrayQueue.peek().data);
        arrayQueue.displayQueue();
    }

}