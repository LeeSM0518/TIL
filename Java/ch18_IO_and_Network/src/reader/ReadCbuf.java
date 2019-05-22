package reader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReadCbuf {
    public static void main(String[] args) throws IOException {
        String current = new java.io.File(".").getCanonicalPath();
        Reader reader = new FileReader(current + "/test_reader.txt");

        int readCharNo;
        char[] cbuf = new char[2];

        while ((readCharNo = reader.read(cbuf)) != -1) {
            System.out.print(readCharNo + " " + cbuf[0] + " " + cbuf[1] + "\n");
        }
    }
}