package object_secondary_stream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SerialVersionUIDExample2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String current = new java.io.File(".").getCanonicalPath();
        FileInputStream fis = new FileInputStream(current + "/Object3.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ClassC classC = (ClassC) ois.readObject();
        System.out.println("field1: " + classC.field1);
    }
}
