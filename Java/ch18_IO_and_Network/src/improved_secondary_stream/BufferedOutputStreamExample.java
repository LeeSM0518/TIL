package improved_secondary_stream;

import java.io.*;

public class BufferedOutputStreamExample {
    public static void main(String[] args) throws IOException {
        String current = new java.io.File(".").getCanonicalPath();

        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        int data = -1;
        long start = 0;
        long end = 0;

        fis = new FileInputStream(current + "/dukeplug2.gif");
        bis = new BufferedInputStream(fis);

        // FileOutputStream 직접 사용
        fos = new FileOutputStream(current + "/dukeplug3.gif");

        start = System.currentTimeMillis();
        while((data = bis.read()) != -1) {
            fos.write(data);
        }
        fos.flush();
        end = System.currentTimeMillis();

        fos.close(); bis.close(); fis.close();
        System.out.println("사용하지 않았을 때: " + (end-start) + "ms");

        fis = new FileInputStream(current + "/dukeplug2.gif");
        bis = new BufferedInputStream(fis);
        fos = new FileOutputStream(current + "/dukeplug3.gif");

        // BufferedOutputStream 사용
        bos = new BufferedOutputStream(fos);

        start = System.currentTimeMillis();
        while((data = bis.read()) != -1) {
            bos.write(data);
        }
        bos.flush();
        end = System.currentTimeMillis();

        bos.close(); fos.close(); bis.close(); fis.close();
        System.out.println("사용했을 때: " + (end-start) + "ms");

    }
}
