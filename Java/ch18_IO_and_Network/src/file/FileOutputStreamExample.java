package file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamExample {
    public static void main(String[] args) throws IOException {
        String current = new java.io.File(".").getCanonicalPath();
        String originalFileName = current + "/dukeplug.gif";
        String targetFileName = current + "/dukeplug2.gif";

        FileInputStream fis = new FileInputStream(originalFileName);
        FileOutputStream fos = new FileOutputStream(targetFileName);

        int readByteNo;                     // 읽은 바이트 수가 저장될 변수
        byte[] readBytes = new byte[100];   // 읽은 바이트가 저장되는 배열
        while((readByteNo = fis.read(readBytes)) != -1) {
            // 100 바이트씩 읽어서 readBytes 배열에 저장하고 100을 readByteNo 에 저장.
            fos.write(readBytes, 0, readByteNo);
        }

        fos.flush();
        fos.close();
        fis.close();

        System.out.println("복사가 잘 되었습니다.");
    }
}
