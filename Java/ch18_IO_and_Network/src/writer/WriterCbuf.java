package writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriterCbuf {
    public static void main(String[] args) throws IOException {
        String current = new java.io.File(".").getCanonicalPath();
        Writer writer = new FileWriter(current + "/test_writer.txt");

        char[] data = "홍길동".toCharArray();

        // 배열 전체 출력
        writer.write(data);
        writer.close();
    }
}