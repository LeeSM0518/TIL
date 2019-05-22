package improved_secondary_stream;

import java.io.*;

public class BufferedReaderExample {
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        Reader reader = new InputStreamReader(is);

        // 버퍼 스트림 생성
        BufferedReader br = new BufferedReader(reader);

        System.out.print("입력: ");
        String lineString = br.readLine();

        System.out.println("출력: " + lineString);
    }
}