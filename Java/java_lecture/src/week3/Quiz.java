package week3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Quiz {

    static Scanner scanner = new Scanner(System.in);

    static <T> void add(T country, T city, Map<T,T> map) {
        map.put(country, city);
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();

        add("Korea", "Seoul", map);
        add("일본", "도쿄", map);

        System.out.print("입력: ");
        String country = scanner.nextLine();

        System.out.println("출력: "+ map.get(country));

        System.out.print("입력: ");
        country = scanner.nextLine();

        System.out.println("출력: "+ map.get(country));
    }
}
