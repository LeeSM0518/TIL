package writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriterOffLen {
    public static void main(String[] args) throws IOException {
        String current = new java.io.File(".").getCanonicalPath();
        Writer writer = new FileWriter(current + "/writer.txt", true);

        char[] data = "홍길동".toCharArray();

        // 글자 배열의 1부터 2까지 출력
        writer.write(data, 1, 2);
        writer.close();
    }
}
