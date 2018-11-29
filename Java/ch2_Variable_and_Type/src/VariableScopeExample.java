public class VariableScopeExample {
    public static void main(String[] args){
        int v1 = 15;
        if(v1 > 10){
            int v2 = v1 - 10;
        }
        // v3 = v1 + v2 + 5 였으나 v2 변수는 사용할 수 없으므로
        // v3 = v1 + 5 로 수정
        int v3 = v1 + 5;
    }
}