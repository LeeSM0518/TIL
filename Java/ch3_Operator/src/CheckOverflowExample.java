public class CheckOverflowExample {
    public static void main(String[] args) {
        try{
            int result = safeAdd(2000000000, 2000000000);
            System.out.println(result);
        }catch (ArithmeticException e){
            System.out.println("오버플로우가 발생하여 정확하게 계산할 수 없음");
        }
    }

    public static int safeAdd(int left, int right){
        if((right>0)){
            // 왼쪽 값 > int 타입 최대 값 - 오른쪽 값
            if(left > (Integer.MAX_VALUE - right)){
                throw new ArithmeticException("오버플로우 발생");
            }
        } else{
            // 왼쪽 값 < int 타입 최대 값 - 오른쪽 값
            if(left<(Integer.MIN_VALUE - right)){
                throw new ArithmeticException("오버플로우 발생");
            }
        }
        return left + right;
    }
}