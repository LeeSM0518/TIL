package string_class.string_method.index_of_method;

public class StringIndexOfExample {
    public static void main(String[] args) {
        // String 타입 변수 초기화
        String subject = "자바 프로그래밍";

        // 프로그래밍이 시작하는 인덱스를 찾아서 리턴
        int location = subject.indexOf("프로그래밍");
        System.out.println(location);

        // "자바" 라는 문자열이 있는지 조사
        if(subject.indexOf("자바") != -1) {
            System.out.println("자바와 관련된 책이군요.");
        } else {
            System.out.println("자바와 관련없는 책이군요.");
        }
    }
}