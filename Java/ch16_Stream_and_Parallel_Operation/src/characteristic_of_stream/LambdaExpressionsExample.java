package characteristic_of_stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class LambdaExpressionsExample {
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(
                new Student("홍길동", 90),
                new Student("신용권", 92)
        );

        // 스트림 얻기
        Stream<Student> stream = list.stream();

        // List 컬렉션에서 Student 를 가져와
        // 람다식의 매개값으로 제공
        stream.forEach( s -> {
            String name = s.getName();
            int score = s.getScore();
            System.out.println(name + "-" + score);
        });
    }
}