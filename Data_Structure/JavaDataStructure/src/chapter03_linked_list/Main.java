package chapter03_linked_list;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();

        linkedList.addNode(0, 10);
        linkedList.addNode(1, 20);
        linkedList.addNode(1, 30);

        System.out.println("위치: " + 0 + ", 값: " + linkedList.getNode(0));
        System.out.println("위치: " + 1 + ", 값: " + linkedList.getNode(1));
        System.out.println("위치: " + 2 + ", 값: " + linkedList.getNode(2));
        System.out.println();

        Node value = linkedList.getNode(1);
        System.out.println("위치: " + 1 + ", 값: " + value);
        System.out.println();

        linkedList.removeData(0);

        System.out.println("위치: " + 0 + ", 값: " + linkedList.getNode(0));
        System.out.println("위치: " + 1 + ", 값: " + linkedList.getNode(1));
        System.out.println();

        linkedList.displayList();
        System.out.println();

        linkedList.iterateList();
        System.out.println();

        LinkedList<Integer> linkedList2 = new LinkedList<>();

        linkedList2.addNode(0, 30);
        linkedList2.addNode(1, 40);
        linkedList2.addNode(2, 50);

        linkedList2.iterateList();
        System.out.println();

        linkedList.concatList(linkedList2);
        linkedList.iterateList();
    }
}