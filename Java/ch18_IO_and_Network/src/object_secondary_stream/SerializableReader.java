package object_secondary_stream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SerializableReader {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String current = new java.io.File(".").getCanonicalPath();
        FileInputStream fis = new FileInputStream(current + "/Object2.dat");

        // 객체 입력 스트림
        ObjectInputStream ois = new ObjectInputStream(fis);

        // 객체 역직렬화
        ClassA v = (ClassA) ois.readObject();

        System.out.println("filed1: " + v.field1);
        System.out.println("field2.field1: " + v.field2.field1);
        System.out.println("fiedl3: " + v.field3);
        System.out.println("field4: " + v.field4);
    }
}
