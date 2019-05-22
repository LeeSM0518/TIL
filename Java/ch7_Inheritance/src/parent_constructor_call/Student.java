package parent_constructor_call;

// 부모 생성자가 기본 생성자가 아니기 때문에
// 자식 생성자 맨 윗줄에 super(...)을 써서
// 부모 생성자에 맞게 생성자를 호출해준다.
public class Student extends People{
    public int studentNo;

    public Student(String name, String ssn, int studentNo){
        super(name, ssn);   // 부모 생성자 호출
        this.studentNo = studentNo;
    }
}