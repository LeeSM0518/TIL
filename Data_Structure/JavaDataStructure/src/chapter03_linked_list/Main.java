package chapter03_linked_list;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();

        linkedList.addLinkedListData(0, 10);
        linkedList.addLinkedListData(1, 20);
        linkedList.addLinkedListData(1, 30);

        System.out.println("위치: " + 0 + ", 값: " + linkedList.getLinkedListData(0));
        System.out.println("위치: " + 1 + ", 값: " + linkedList.getLinkedListData(1));
        System.out.println("위치: " + 2 + ", 값: " + linkedList.getLinkedListData(2));
        System.out.println();

        int value = linkedList.getLinkedListData(1);
        System.out.println("위치: " + 1 + ", 값: " + value);
        System.out.println();

        linkedList.removeLinkedListData(0);

        System.out.println("위치: " + 0 + ", 값: " + linkedList.getLinkedListData(0));
        System.out.println("위치: " + 1 + ", 값: " + linkedList.getLinkedListData(1));
        System.out.println();

        linkedList.displayList();
        System.out.println();

        linkedList.iterateLinkedList();
        System.out.println();

        LinkedList<Integer> linkedList2 = new LinkedList<>();

        linkedList2.addLinkedListData(0, 30);
        linkedList2.addLinkedListData(1, 40);
        linkedList2.addLinkedListData(2, 50);

        linkedList2.iterateLinkedList();
        System.out.println();

        linkedList.concatLinkedList(linkedList2);
        linkedList.iterateLinkedList();
    }
}