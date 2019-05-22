package whildcard_type;

public class Course<T> {
    private String name;
    private T[] students;

    public Course(String name, int capacity) {
        this.name = name;
        // 타입 파라미터로 배열을 생성하려면
        // new T[n] 형태로 배열을 생성할 수 없고
        // (T[]) (new Object[n]) 으로
        // 생성해야 한다.
        students = (T[]) (new Object[capacity]);
    }

    public String getName() { return name; }
    public T[] getStudents() { return students; }

    public void add(T t) {
        // 배열에 비어있는 부분을 찾아서
        // 수강생을 추가하는 메소드
        for(int i=0; i<students.length; i++) {
            if(students[i] == null) {
                students[i] = t;
                break;
            }
        }
    }
}
