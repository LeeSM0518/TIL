package implement_class;

import static_method_declare.RemoteControl;

public class Television implements RemoteControl {
    // 필드
    private int volume;

    // turnOn() 추상 메소드의 실체 메소드
    public void turnOn(){
        System.out.println("TV를 켭니다.");
    }

    // turnOff() 추상 메소드의 실체 메소드
    public void turnOff(){
        System.out.println("TV를 끕니다.");
    }

    // setVolume() 추상 메소드의 실체 메소드
    // 인터페이스 상수를 이용해서 volume 필드의 값 제한
    public void setVolume(int volume) {
        if(volume > RemoteControl.MAX_VOLUME) {
            this.volume = RemoteControl.MAX_VOLUME;
        } else if (volume < RemoteControl.MIN_VOLUME) {
            this.volume = RemoteControl.MIN_VOLUME;
        } else {
            this.volume = volume;
        }
        System.out.println("현재 TV 볼륨: " + volume);
    }
}