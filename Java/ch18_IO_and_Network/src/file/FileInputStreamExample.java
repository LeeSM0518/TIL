package file;

import java.io.FileInputStream;

public class FileInputStreamExample {
    public static void main(String[] args) {
        try {
            String current = new java.io.File(".").getCanonicalPath();
            System.out.println(current);
            FileInputStream fis = new FileInputStream(
                    current + "/src/file/FileInputStreamExample.java"
            );

            int data;

            // 1byte 씩 읽고 콘솔에 출력
            while ((data = fis.read()) != -1) {
                System.out.write(data);
            }

            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
