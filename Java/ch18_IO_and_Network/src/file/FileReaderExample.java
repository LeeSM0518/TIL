package file;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderExample {
    public static void main(String[] args) throws IOException {
        String current = new java.io.File(".").getCanonicalPath();
        FileReader fr = new FileReader(
                current + "/src/file/FileReaderExample.java"
        );

        int readCharNo;
        char[] cbuf = new char[200];
        while ((readCharNo = fr.read(cbuf)) != -1) {
            String data = new String(cbuf, 0, readCharNo);
            System.out.println(data);
        }
        fr.close();
    }
}