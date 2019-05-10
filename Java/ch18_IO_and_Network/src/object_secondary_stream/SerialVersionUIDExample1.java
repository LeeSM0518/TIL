package object_secondary_stream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerialVersionUIDExample1 {
    public static void main(String[] args) throws IOException {
        String current = new java.io.File(".").getCanonicalPath();
        FileOutputStream fos = new FileOutputStream(current + "/Object3.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        ClassC classC = new ClassC();
        classC.field1 = 1;

        oos.writeObject(classC);
        oos.flush(); oos.close(); fos.close();
    }
}
