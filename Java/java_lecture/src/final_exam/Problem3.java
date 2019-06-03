package final_exam;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Problem3 {
    public static void main(String[] args) throws IOException {

        String currentDir = new java.io.File(".").getCanonicalPath();

        String fileDir = currentDir + "/data/";

        Queue<Byte> rfile = new LinkedList<>();
        Queue<Byte> gfile = new LinkedList<>();
        Queue<Byte> bfile = new LinkedList<>();

        InputStream fileReader = new FileInputStream(fileDir + "Red.pgm");

        int readByte;

        while ((readByte = fileReader.read()) != -1) {
            rfile.add((byte) readByte);
        }

        fileReader.close();

        fileReader = new FileInputStream(fileDir + "Green.pgm");

        while ((readByte = fileReader.read()) != -1) {
            gfile.add((byte) readByte);
        }

        fileReader = new FileInputStream(fileDir + "Blue.pgm");

        while ((readByte = fileReader.read()) != -1) {
            bfile.add((byte) readByte);
        }

        OutputStream os = new FileOutputStream(fileDir + "RGB.ppm");

        byte[] data = "P6 256 256 255\n".getBytes();
        os.write(data);

        for (int i = 0; i < 15; i++) {
            rfile.poll();
            gfile.poll();
            bfile.poll();
        }

        while (rfile.size() != 0) {
            try {
                os.write(rfile.poll());
            } catch (Exception e) {

            }

            try {
                os.write(gfile.poll());
            } catch (Exception e) {

            }

            try {
                os.write(bfile.poll());
            } catch (Exception e) {

            }

        }

        os.flush();
        os.close();
    }
}
