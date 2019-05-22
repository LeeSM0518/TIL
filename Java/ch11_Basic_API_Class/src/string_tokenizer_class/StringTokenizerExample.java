package string_tokenizer_class;

import java.util.StringTokenizer;

public class StringTokenizerExample {
    public static void main(String[] args) {
        String text = "홍길동/이수홍/박연수";

        // 전체 토큰 수를 얻어 for 문으로 루핑해서 문자열 분리
        StringTokenizer st = new StringTokenizer(text, "/");
        int countTokens = st.countTokens();
        for(int i=0; i<countTokens; i++) {
            String token = st.nextToken();
            System.out.println(token);
        }

        System.out.println();

        // 남아 있는 토큰을 확인하고 while 문으로 루핑해서 문자열 분리
        st = new StringTokenizer(text, "/");
        while( st.hasMoreElements()) {
            String token = st.nextToken();
            System.out.println(token);
        }
    }
}