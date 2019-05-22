package object_secondary_stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializableWriter {
    public static void main(String[] args) throws IOException {
        String current = new java.io.File(".").getCanonicalPath();
        FileOutputStream fos = new FileOutputStream(current + "/Object2.dat");

        // 객체 출력 스트림
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        // 객체 생성 및 값 저장
        ClassA classA = new ClassA();
        classA.field1 = 1;
        classA.field2.field1 = 2;
        classA.field3 = 3;
        classA.field4 = 4;

        // 객체 직렬화
        oos.writeObject(classA);
        oos.flush(); oos.close(); fos.close();
    }
}
