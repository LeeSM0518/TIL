package catch_order;

public class CatchOrderExample {
    public static void main(String[] args) {
        try {
            String data1 = args[0];
            String data2 = args[1];

            int value1 = Integer.parseInt(data1);
            int value2 = Integer.parseInt(data2);

            int result = value1 + value2;

            System.out.println(data1 + "+" + data2 +
                    "=" + result);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("실행 매개값의 수가 부족");
        } catch (Exception e) {
            System.out.println("실행에 문제가 있음");
        } finally {
            System.out.println("다시 실행하세요.");
        }
    }
}
