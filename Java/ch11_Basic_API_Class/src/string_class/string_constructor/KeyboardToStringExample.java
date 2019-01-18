package string_class.string_constructor;

import java.io.IOException;
import java.nio.charset.Charset;

public class KeyboardToStringExample {
    public static void main(String[] args) throws IOException {
        // byte 배열 객체 생성
        byte[] bytes = new byte[100];

        // 입력을 받아 bytes 배열에 저장 및
        // bytes 배열의 길이 저장
        System.out.print("입력: ");
        int readByteNo = System.in.read(bytes);

        // bytes 배열을 String 으로 변환하여 String 객체를 생성한다.
        String str = new String(bytes, 0, readByteNo-1);
        System.out.println(str);
    }
}