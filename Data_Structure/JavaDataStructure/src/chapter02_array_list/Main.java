package chapter02_array_list;

public class Main {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList(5);

        arrayList.addNode(0, 10);
        arrayList.addNode(1, 20);
        arrayList.addNode(1, 30);
        arrayList.addNode(3, "ID", "PW");
        arrayList.addNode(4, "ID2", "PW2");


        for (int i = 0; i < arrayList.length(); i++) {
            System.out.println("위치: " + i + ", 값: " + arrayList.getNode(i).getData());
        }
        System.out.println();

        Node value = arrayList.getNode(1);
        System.out.println("위치: 1" + ", 값: " + value.getData());
        System.out.println();

        arrayList.removeNode(0);

        for (int i = 0; i < arrayList.length(); i++) {
            System.out.println("위치: " + i + ", 값: " + arrayList.getNode(i).getData());
        }
        System.out.println();
    }
}
