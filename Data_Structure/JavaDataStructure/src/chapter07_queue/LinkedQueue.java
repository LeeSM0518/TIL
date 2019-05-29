package chapter07_queue;

public class LinkedQueue {

    private int currentCount;       // 현재 노드의 개수
    private LinkedNode front;       // Front 노드
    private LinkedNode rear;        // rear 노드

    public LinkedQueue() {
        currentCount = 0;
    }

    public <T> void enqueue(T data) {
        LinkedNode node = new LinkedNode<>(data, null);

        if (!isEmpty()) {
            rear.link = node;
            rear = node;
        } else {
            front = node;
            rear = node;
        }

        currentCount++;
    }

    public LinkedNode dequeue() {
        LinkedNode node = null;

        if (!isEmpty()) {
            node = front;
            front = front.link;
            node.link = null;

            currentCount--;
        } else {
            System.out.println("큐가 비어 있습니다.");
        }

        if (isEmpty()) rear = null;

        return node;
    }

    public LinkedNode peek() {
        LinkedNode node = null;

        if (!isEmpty()) {
            node = front;
        } else {
            System.out.println("큐가 비어 있습니다.");
        }

        return node;
    }

    public void displayQueue() {
        LinkedNode node;

        if (!isEmpty()) {
            int i = 0;
            System.out.println("현재 노드 개수: " + currentCount);
            node = front;
            while (node != null) {
                System.out.println("[" + i++ + "] - [" + node.data + "]");
                node = node.link;
            }
            System.out.println();
        } else {
            System.out.println("큐가 비어 있습니다.");
        }
    }

    boolean isEmpty() {
        return currentCount == 0;
    }

    public static void main(String[] args) {
        LinkedQueue linkedQueue = new LinkedQueue();

        linkedQueue.enqueue("A");
        linkedQueue.enqueue("B");
        linkedQueue.enqueue("C");
        linkedQueue.enqueue("D");
        linkedQueue.displayQueue();

        System.out.println("dequeue: " + linkedQueue.dequeue().data);
        linkedQueue.displayQueue();

        System.out.println("peek: " + linkedQueue.peek().data);
        linkedQueue.displayQueue();

        linkedQueue.enqueue("E");
        linkedQueue.displayQueue();
    }

}