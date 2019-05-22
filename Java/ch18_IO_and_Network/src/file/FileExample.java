package file;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileExample {
    public static void main(String[] args) throws IOException {
        String current = new java.io.File(".").getCanonicalPath();

        // 파일 및 디렉토리 객체 생성
        File dir = new File(current + "/Dir");
        File file1 = new File(current + "/file1.txt");
        File file2 = new File(current + "/file2.txt");

        // 파일 및 디렉토리 존재 여부 확인
        if (!dir.exists()) dir.mkdirs();
        if (!file1.exists()) file1.createNewFile();
        if (!file2.exists()) file2.createNewFile();

        File temp = new File(current);
        // 날짜 form 생성
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd   a  HH:mm");
        File[] contents = temp.listFiles();
        System.out.println("날짜            시간     형태  크기          이름");
        System.out.println("--------------------------------------------------------------");
        for (File file : contents) {
            // 파일 수정날짜
            System.out.print(sdf.format(new Date(file.lastModified())));
            if (file.isDirectory()) {
                // 디렉토리 이름
                System.out.print("\t<DIR>\t\t\t\t" + file.getName());
            } else {
                // 파일 이름
                System.out.print("\t\t\t" + file.length() + "\t\t\t" + file.getName());
            }
            System.out.println();
        }
    }
}