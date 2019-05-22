package Inheritance_declare;

public class CellPhone { // 부모 클래스
    // 필드
    String model;
    String color;

    // 생성자

    // 메소드
    void powerOn(){
        System.out.println("전원을 킨다.");
    }
    void powerOff(){
        System.out.println("전원을 끈다.");
    }
    void bell(){
        System.out.println("벨이 울린다.");
    }
    void sendVoice(String message){
        System.out.println("자기: " + message);
    }
    void receiveVoice(String message){
        System.out.println("상대방: " + message);
    }
    void hangUp(){
        System.out.println("전화를 끊는다.");
    }
}
