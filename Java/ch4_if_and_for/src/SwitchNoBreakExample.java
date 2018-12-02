public class SwitchNoBreakExample {
    public static void main(String[] args) {
        int time = (int)(Math.random()*4) + 8;
        System.out.println("현재기간 : " + time + " 시");

        switch (time){
            case 8:
                System.out.println("출근합니다.");
            case 9:
                System.out.println("회의를 합니다.");
            case 10:
                System.out.println("업무를 봅니다.");
            default:
                System.out.println("외근을 나갑니다.");
        }
    }
}
