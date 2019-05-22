package parent_constructor_call;

public class StudentExample {
    public static void main(String[] args){
        Student student = new Student("홍길동", "111111-1111111", 1);
        System.out.println("ssn: " + student.ssn);
        System.out.println("studentNo: " + student.studentNo);
    }
}
