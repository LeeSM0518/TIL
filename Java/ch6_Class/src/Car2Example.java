public class Car2Example {
    public static void main(String[] args) {
        Car2 myCar = new Car2("검정", 3000);
        // Car myCar = new Car(); ( X , 기본 생성자 호출 불가 )

        System.out.println(myCar.Color);
        System.out.println(myCar.CC);
    }
}