package objects_class.compare_method;

import java.util.Comparator;
import java.util.Objects;

public class CompareExample {
    public static void main(String[] args) {
        // Student 객체 생성
        Student s1 = new Student(1);
        Student s2 = new Student(1);
        Student s3 = new Student(3);

        // result 에 객체를 비교한 결과 저장
        int result = Objects.compare(s1, s2, new StudentComparator());
        System.out.println(result);
        result = Objects.compare(s1, s3, new StudentComparator());
        System.out.println(result);
    }

    // Student 클래스
    static class Student {
        int sno;
        Student(int sno) {
            this.sno = sno;
        }
    }

    // Student 객체를 비교하는 클래스
    static class StudentComparator implements Comparator<Student> {
        public int compare(Student o1, Student o2) {
            /*if (o1.sno < o2.sno) return -1;
            else if (o1.sno == o2.sno) return 0;
            else return 1;*/
            return Integer.compare(o1.sno, o2.sno);
        }
    }
}
