package auto_resources_close;

// AutoCloseable 인터페이스의 구현 클래스
public class FileInputStream implements AutoCloseable{
    private String file;

    // 생산자
    public FileInputStream(String file) {
        this.file = file;
    }

    public void read() {
        System.out.println(file + " 을 읽습니다.");
    }

    // 파일을 닫아주는 메소드를 오버라이딩
    @Override
    public void close() throws Exception {
        System.out.println(file + " 을 닫습니다.");
    }
}
