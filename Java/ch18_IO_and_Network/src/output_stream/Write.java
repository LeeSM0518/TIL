package output_stream;

import java.io.*;

public class Write {
    public static void main(String[] args) throws IOException {
        String current = new java.io.File(".").getCanonicalPath();
        OutputStream os = new FileOutputStream(current + "/test_write.txt");

        byte[] data = "ABC".getBytes();

        for (int i=0; i<data.length; i++) {
            os.write(data[i]);  // A, B, C 를 하나씩 출력
        }
    }
}