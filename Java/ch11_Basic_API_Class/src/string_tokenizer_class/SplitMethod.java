package string_tokenizer_class;

public class SplitMethod {
    public static void main(String[] args) {
        String text = "홍길동&이수홍,박연수,김자바-최명호";

        // 파이프를 제외한 기호들을 구분자로 해서 문자열을 추출한다.
        String[] names = text.split("&|,|-");

        for(String name : names) {
            System.out.println(name);
        }
    }
}
