public class Car9Example {
    public static void main(String[] args) {
        Car9 myCar = new Car9();

        // myCar.speed = 50;
        // speed가 private 접근 제한이여서 컴파일 오류

        // 잘못된 속도 변경
        myCar.setSpeed(-50);

        System.out.println("현재 속도: " + myCar.getSpeed());

        // 올바른 속도 변경
        myCar.setSpeed(60);
        System.out.println("현재 속도: " + myCar.getSpeed());

        // 멈춤
        if(!myCar.isStop()){
            myCar.setStop(true);
        }

        System.out.println("현재 속도: " + myCar.getSpeed());
    }
}