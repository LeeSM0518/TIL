package collect_method;

import java.util.ArrayList;
import java.util.List;

public class MaleStudent {

    // 요소를 저장할 컬렉션
    private List<Student> list;

    public MaleStudent() {
        list = new ArrayList<Student>();
        // 생성자를 호출하는 스레드 이름 출력
        System.out.println("[" + Thread.currentThread().getName()
                + "] MaleStudent()");
    }

    // 요소를 수집하는 메소드
    public void accumulate(Student student) {
        list.add(student);
        // accumulate()를 호출할 스레드 이름 출력
        System.out.println("[" + Thread.currentThread().getName()
         + "] accumulate()");
    }

    // 두 MaleStudent 를 결합하는 메소드
    // (병렬 처리 시에만 호출)
    public void combine(MaleStudent other) {
        list.addAll(other.getList());
        // combine() 호출한 스레드 이름 출력
        System.out.println("[" + Thread.currentThread().getName()
        + "] combine()");
    }

    // 요소가 저장된 컬렉션을 리턴
    public List<Student> getList() {
        return list;
    }
}
