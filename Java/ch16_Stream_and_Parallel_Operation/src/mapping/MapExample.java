package mapping;

import java.util.Arrays;
import java.util.List;

public class MapExample {
    public static void main(String[] args) {
        List<Student> studentList = Arrays.asList(
                new Student("홍길동", 10),
                new Student("신용권", 20),
                new Student("유미선", 30)
        );

        long start = System.currentTimeMillis(); //시작하는 시점 계산

        studentList.stream()
                .mapToInt(Student::getScore)
                .forEach(score -> System.out.println(score));

        long end = System.currentTimeMillis(); //프로그램이 끝나는 시점 계산
        System.out.println( "실행 시간 : " + ( end - start )/100000.0);

        start = System.currentTimeMillis(); //시작하는 시점 계산

        studentList.stream()
                .forEach(score -> System.out.println(score.getScore()));

        end = System.currentTimeMillis(); //프로그램이 끝나는 시점 계산
        System.out.println( "실행 시간 : " + ( end - start )/100000.0);
    }
}
