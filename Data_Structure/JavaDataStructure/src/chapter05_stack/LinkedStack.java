package chapter05_stack;

public class LinkedStack<T> {

    int currentCount;
    LinkedStackNode top;

    public LinkedStack(){
        currentCount = 0;
    }

    public void push(T data) {
        // 새로운 노드 생성
        LinkedStackNode node = new LinkedStackNode<>(data);

        // 새로운 노드의 이전 노드를 기존 탑 노드로 지정
        node.link = top;

        // 탑 노드를 새로 추가한 노드로 변경
        top = node;

        currentCount++;
    }

    public LinkedStackNode pop() {
        LinkedStackNode node = null;

        // 스택이 비어 있는지 점검
        if (!isLinkedStackEmpty()) {
            // 기존 탑 노드를 반환 노드로 지정
            node = top;
            // 새로운 탑 노드를 기존 탑 노드의 이전 노드로 지정
            top = node.link;
            // 반환 노드의 이전 노드 정보를 초기화
            node.link = null;
            // 연결 리스트 노드 개수 1개 감소
            currentCount--;
        } else {
            System.out.println("스택이 비어 있습니다.");
        }

        return node;
    }

    public LinkedStackNode peek() {
        LinkedStackNode node = null;

        if (!isLinkedStackEmpty()) {
            node = top;
        } else {
            System.out.println("스택이 비어 있습니다.");
        }

        return node;
    }

    public void displayLinkedStack() {
        if (!isLinkedStackEmpty()) {
            LinkedStackNode node = top;
            int i = 1;
            while (node != null) {
                System.out.println("[" + (currentCount - i++) + "] - [" + node.getData() + "]" );
                node = node.link;
            }
        } else {
            System.out.println("스택이 비어 있습니다.");
        }

        System.out.println();
    }

    private boolean isLinkedStackEmpty() {
        return currentCount == 0;
    }

    public static void main(String[] args) {
        LinkedStack<String> linkedStack = new LinkedStack<>();

        linkedStack.push("A");
        linkedStack.push("B");
        linkedStack.push("C");
        linkedStack.push("D");
        linkedStack.push("E");
        linkedStack.displayLinkedStack();

        System.out.println("pop 값 : " + linkedStack.pop().getData());
        linkedStack.displayLinkedStack();

        System.out.println("peek 값 : " + linkedStack.peek().getData());
        linkedStack.displayLinkedStack();
    }
}