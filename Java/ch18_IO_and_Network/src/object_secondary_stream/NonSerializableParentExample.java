package object_secondary_stream;

import java.io.*;

public class NonSerializableParentExample {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String current = new java.io.File(".").getCanonicalPath();
        FileOutputStream fos = new FileOutputStream(current + "/Object4.dat");

        // 객체 출력 스트림
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        // 객체 생성 및 값 저장
        Child child = new Child();
        child.field1 = "홍길동";
        child.field2 = "홍삼원";

        // 객체 직렬화
        oos.writeObject(child);
        oos.flush(); oos.close(); fos.close();

        FileInputStream fis = new FileInputStream(current + "/Object4.dat");

        // 객체 입력 스트림
        ObjectInputStream ois = new ObjectInputStream(fis);

        Child v = (Child) ois.readObject();

        System.out.println("filed1: " + v.field1);
        System.out.println("field2: " + v.field2);
        ois.close(); fis.close();
    }
}