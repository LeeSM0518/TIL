package chapter02_array_list;

public class Main {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList(5);

        arrayList.addListData(0, 10);
        arrayList.addListData(1, 20);
        arrayList.addListData(1, 30);


        for(int i=0; i<arrayList.currentCount; i++) {
            System.out.println("위치: " + i + ", 값: " + arrayList.getListData(i));
        }
        System.out.println();

        int value = arrayList.getListData(1);
        System.out.println("위치: 1" + ", 값: " + value );
        System.out.println();

        arrayList.removeListData(0);

        for(int i=0; i<arrayList.currentCount; i++) {
            System.out.println("위치: " + i + ", 값: " + arrayList.getListData(i) );
        }
        System.out.println();
    }
}
