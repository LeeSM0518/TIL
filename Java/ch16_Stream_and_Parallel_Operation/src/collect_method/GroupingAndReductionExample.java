package collect_method;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingAndReductionExample {
    public static void main(String[] args) {
        List<Student> totalList = Arrays.asList(
                new Student("홍길동", 10,
                        Student.Sex.MALE),
                new Student("김수애", 12,
                        Student.Sex.FEMALE),
                new Student("신용권", 10,
                        Student.Sex.MALE),
                new Student("박수미", 12,
                        Student.Sex.FEMALE)
        );

        // 성별로 평균 점수를 저장하는 Map 얻기
        Map<Student.Sex, Double> mapBySex = totalList.stream()
                .collect(
                        Collectors.groupingBy(
                                Student :: getSex,
                                Collectors.averagingDouble(Student :: getScore)
                        )
                );

        System.out.println("남학생 평균 정수: " +
                mapBySex.get(Student.Sex.MALE));
        System.out.println("여학생 평균 점수: " +
                mapBySex.get(Student.Sex.FEMALE));

        // 성별을 쉼표로 구분한 이름을 저장하는 Map 얻기
        Map<Student.Sex, String> mapByName = totalList.stream()
                .collect(
                        Collectors.groupingBy(
                                Student :: getSex,
                                Collectors.mapping(
                                        Student :: getName,
                                        Collectors.joining(",")
                                )
                        )
                );

        System.out.println("남학생 전체 이름: " +
                mapByName.get(Student.Sex.MALE));
        System.out.println("여학생 전체 이름: " +
                mapByName.get(Student.Sex.FEMALE));
    }
}
