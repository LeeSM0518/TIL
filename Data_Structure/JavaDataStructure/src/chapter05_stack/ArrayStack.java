package chapter05_stack;

public class ArrayStack<T> {

    private int maxCount;
    private int currentCount = 0;
    private StackNode[] stackNodes;

    public ArrayStack(final int maxCount) {
        this.maxCount = maxCount;
        stackNodes = new StackNode[maxCount];
    }

    public void push(final T data) {
        // 스택이 가득 차있는지 점검
        if (!isArrayStackFull()) {
            // 실제 자료 추가
            stackNodes[currentCount] = new StackNode<>(data);
            // 탑의 위치 변경
            currentCount++;
        } else {
            System.out.println("스택이 가득 찼습니다.");
        }
    }

    public StackNode pop() {
        StackNode node = null;

        // 스택이 비어 있는지 점검
        if (!isArrayStackEmpty()) {
            // 노드에 반환할 자료 대입
            node = stackNodes[currentCount - 1];
            stackNodes[currentCount - 1] = null;

            // 배열의 탑 위치 변경
            currentCount--;
        } else {
            System.out.println("스택이 비어있습니다.");
        }

        return node;
    }

    public StackNode peek() {
        StackNode node = null;

        if (!isArrayStackEmpty()) {
            node = stackNodes[currentCount - 1];
        } else {
            System.out.println("스택이 비어있습니다.");
        }

        return node;
    }

    public void displayArrayStack() {
        for (int i = maxCount - 1; i >= currentCount; i--) {
            System.out.println("[" + i + "] - [Empty]");
        }

        for (int i = currentCount - 1; i >= 0; i--) {
            System.out.println("[" + i + "] - [" + stackNodes[i].getData() + "]");
        }

        System.out.println();
    }

    private boolean isArrayStackEmpty() {
        return currentCount == 0;
    }

    private boolean isArrayStackFull() {
        return currentCount == maxCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public static void main(String[] args) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(10);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);

        arrayStack.displayArrayStack();

        arrayStack.pop();

        arrayStack.displayArrayStack();

        System.out.println("peek() : " + arrayStack.peek().getData() + "\n");

        arrayStack.displayArrayStack();
    }
}