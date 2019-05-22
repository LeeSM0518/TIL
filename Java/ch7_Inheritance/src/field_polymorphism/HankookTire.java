package field_polymorphism;

public class HankookTire extends Tire{
    // 필드
    // 생성자
    public HankookTire(String location, int maxRotaion){
        super(location, maxRotaion);
    }

    // 오버라이딩한 메소드
    public boolean roll(){
        ++accumulatedRotation;
        if(accumulatedRotation<maxRotation){
            System.out.println(location + " HankookTire 수명: " +
                    (maxRotation-accumulatedRotation) +"회");
            return true;
        } else {
            System.out.println("*** " + location + " HankookTire 펑크 ***");
            return false;
        }
    }
}