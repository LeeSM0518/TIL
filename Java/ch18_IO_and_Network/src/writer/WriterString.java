package writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriterString {
    public static void main(String[] args) throws IOException {
        String current = new java.io.File(".").getCanonicalPath();
        Writer writer = new FileWriter(current + "/writer.txt");

        String data = "안녕 자바 프로그램";

        // 문자열 출력
        writer.write(data);

        // 문자열 3부터 4번 까지 출력
        writer.write(data, 3, 4);

        writer.flush();
        writer.close();
    }
}
