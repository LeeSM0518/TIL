package improved_secondary_stream;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BufferedInputStreamExample {
    public static void main(String[] args) throws IOException {
        long start = 0;
        long end = 0;

        String current = new java.io.File(".").getCanonicalPath();
        FileInputStream fis1 = new FileInputStream(current + "/dukeplug.gif");

        start = System.currentTimeMillis();
        while (fis1.read() != -1){}
        end = System.currentTimeMillis();

        System.out.println("사용하지 않았을 때: " + (end-start) + "ms");
        fis1.close();

        FileInputStream fis2 = new FileInputStream(current + "/dukeplug.gif");
        BufferedInputStream bis = new BufferedInputStream(fis2);    // 버퍼 스트림 생성

        start = System.currentTimeMillis();
        while (bis.read() != -1){}
        end = System.currentTimeMillis();

        System.out.println("사용했을 때: " + (end - start) + "ms");

        bis.close();
        fis2.close();
    }
}