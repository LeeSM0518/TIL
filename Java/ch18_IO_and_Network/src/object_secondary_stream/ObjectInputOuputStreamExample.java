package object_secondary_stream;

import java.io.*;

public class ObjectInputOuputStreamExample {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String current = new java.io.File(".").getCanonicalPath();
        FileOutputStream fos = new FileOutputStream(current + "/Object.dat");

        // 객체 출력 스트림
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        // 객체 직렬화
        oos.writeObject(new Integer(10));
        oos.writeObject(new Double(3.14));
        oos.writeObject(new int[] {1, 2, 3});
        oos.writeObject(new String("홍길동"));

        oos.flush(); oos.close(); fos.close();

        FileInputStream fis = new FileInputStream(current + "/Object.dat");

        // 객체 입력 스트림
        ObjectInputStream ois = new ObjectInputStream(fis);

        // 객체 역직렬화
        Integer obj1 = (Integer) ois.readObject();
        Double obj2 = (Double) ois.readObject();
        int[] obj3 = (int[]) ois.readObject();
        String obj4 = (String) ois.readObject();

        ois.close(); fis.close();

        System.out.println(obj1);
        System.out.println(obj2);
        System.out.println(obj3[0] + "," + obj3[1] + "," + obj3[2]);
        System.out.println(obj4);

    }
}
