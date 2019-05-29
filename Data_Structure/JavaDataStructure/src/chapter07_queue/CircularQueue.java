package chapter07_queue;

public class CircularQueue extends ArrayQueue {

    public CircularQueue(final int maxCount) {
        super(maxCount);
    }

    @Override
    public <T> void enqueue(final T data) {
        if (!isFull()) {
            rear = (rear + 1) % maxCount;       // 논리적으로 마지막과 처음을 연결
            nodes[rear] = new Node<>(data);
            currentCount++;
        } else {
            System.out.println("큐가 가득 찼습니다.");
        }
    }

    @Override
    public Node dequeue() {
        Node node = null;

        if (!isEmpty()) {
            front = (front + 1) % maxCount;     // 논리적으로 마지막과 처음을 연결
            node = nodes[front];
            currentCount--;
        } else {
            System.out.println("큐가 비어 있습니다.");
        }

        return node;
    }

    @Override
    public Node peek() {
        Node node = null;

        if (!isEmpty()) {
            node = nodes[(front + 1) % maxCount];
        } else {
            System.out.println("큐가 비어 있습니다.");
        }

        return node;
    }

    @Override
    public void displayQueue() {
        if (!isEmpty()) {
            for (int i = front + 1; i <= front + currentCount; i++) {
                int position = i % maxCount;
                System.out.println("[ " + position + " ] - [ " + nodes[position].data + " ]" );
            }
        } else {
            System.out.println("큐가 비어 있습니다.");
        }
        System.out.println();
    }

    @Override
    boolean isFull() {
        return currentCount == maxCount;
    }

    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue(4);

        circularQueue.enqueue("A");
        circularQueue.enqueue("B");
        circularQueue.enqueue("C");
        circularQueue.enqueue("D");
        circularQueue.displayQueue();

        circularQueue.dequeue();
        circularQueue.displayQueue();

        circularQueue.enqueue("E");
        circularQueue.displayQueue();

        circularQueue.dequeue();
        circularQueue.displayQueue();

        circularQueue.enqueue("F");
        circularQueue.displayQueue();
    }
}
