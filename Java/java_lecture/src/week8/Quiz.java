package week8;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class Quiz {
    public static void main(String[] args) throws IOException {
        String current = new java.io.File(".").getCanonicalPath();

        FileChannel fc = new RandomAccessFile(current + "/rand.ppm", "rw").getChannel();

        OutputStream os = new FileOutputStream(current + "/rand.ppm");

        byte[] data = "P6 30 30 255\n".getBytes();
        os.write(data);

        StringBuilder line = new StringBuilder();

        for (int i = 0; i < 255; i++) {
            for (int j = 0; j < 255; j++) {
                byte random = (byte) (Math.random() * 255);
                os.write(random);
            }
        }


    }
}