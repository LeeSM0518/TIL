package print_secondary_stream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamExample {
    public static void main(String[] args) throws IOException {
        String current = new java.io.File(".").getCanonicalPath();
        FileOutputStream fos = new FileOutputStream(current + "/file5.txt");
        PrintStream ps = new PrintStream(fos);

        ps.println("[프린터 보조 스트림]");
        ps.print("마치 ");
        ps.println("프린터가 출력하는 것처럼 ");
        ps.println("데이터를 출력합니다.");

        ps.flush();
        ps.close();
        fos.close();
    }
}
