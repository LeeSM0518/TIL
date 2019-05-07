//package week7;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//
//public class Quiz {
//    public static void main(String[] args) throws IOException {
//        URL url = new URL("https://post.naver.com/viewer/postView.nhn?volumeNo=19701553&memberNo=6411495");
//        URLConnection urlConnection = url.openConnection();
//        System.out.println(urlConnection.connect());
//    }
//}

package week7;

import java.util.*;

import java.io.*;


public class Quiz {


    public static void main(String[] args) throws IOException {


        String current = new java.io.File(".").getCanonicalPath();
        String filename = current + "/test.txt";

        FileReader fr = new FileReader(filename);

        BufferedReader br = new BufferedReader(fr);

        StreamTokenizer stream = new StreamTokenizer(br);

        FileInputStream fin = new FileInputStream(filename);


        int bytesRead = 0;

        int sum = 0;

        int lineCnt = 0;

        int cnt = 0;

        byte[] buffer = new byte[256];

        StringTokenizer st;

        String str = "";


        try {

//            while ((bytesRead = fin.read(buffer)) >= 0) {
//
//                sum += bytesRead;
//
//            }
//
//            while ((str = br.readLine()) != null) {
//
//                st = new StringTokenizer(str);
//
//                cnt += st.countTokens();
//
//                lineCnt++;
//
//            }

            stream.eolIsSignificant(true);

            while (stream.nextToken() != StreamTokenizer.TT_EOF) {
                switch (stream.ttype) {
                    case StreamTokenizer.TT_WORD:
                        cnt++;
                        break;
                    case StreamTokenizer.TT_EOL:
                        lineCnt++;
                        break;

                }
            }

            System.out.println("------------------------------------------------");

            System.out.println(filename + "의 정보입니다.");

            System.out.println("라인수 : " + lineCnt);

            System.out.println("글자수 :" + cnt);

            System.out.println("바이트수 :" + sum);

            System.out.println("------------------------------------------------");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        br.close();

        fr.close();

    }

}