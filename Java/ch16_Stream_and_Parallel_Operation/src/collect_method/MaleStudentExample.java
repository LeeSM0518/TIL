package collect_method;

import java.util.Arrays;
import java.util.List;

public class MaleStudentExample {
    public static void main(String[] args) {
        List<Student> totalList = Arrays.asList(
                new Student("홍길동", 10,
                        Student.Sex.MALE),
                new Student("김수애", 6,
                        Student.Sex.FEMALE),
                new Student("신용권", 10,
                        Student.Sex.MALE),
                new Student("박수미", 6,
                        Student.Sex.FEMALE)
        );

        // 전체 학생 List 에서 Stream 을 얻는다.
        MaleStudent maleStudent = totalList.stream().parallel()
                // 남학생만 필터링해서 Stream 을 얻는다.
                .filter(s -> s.getSex() == Student.Sex.MALE)
                // 1. MaleStudent 를 공급하는 Supplier 를 얻는다.
                // 2. MaleStudent 와 Student 를 매개값으로 받아서 MaleStudent 의 accumulate() 메소드로 Student 를 수집하는 BiConsumer 를 얻는다.
                // 3. 두 개의 MaleStudent 를 매개값으로 받아 combine() 메소드로 결합하는 BiConsumer 를 얻는다.
                // 4. supplier 가 제공하는 MaleStudent 에 accumulator 가 Student 를 수집해서 최종 처리된 MaleStudent 를 얻는다.
                .collect(MaleStudent::new,
                        MaleStudent::accumulate,
                        MaleStudent::combine);

        maleStudent.getList().stream()
                .forEach(s -> System.out.println(s.getName()));
    }
}
