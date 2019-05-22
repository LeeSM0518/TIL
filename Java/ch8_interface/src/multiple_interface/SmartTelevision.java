package multiple_interface;

import static_method_declare.RemoteControl;

public class SmartTelevision implements RemoteControl, Searchable {
    // RemoteControl의 추상 메소드에 대한 실체 메소드
    private int volume;

    public void turnOn() {
        System.out.println("TV를 켭니다.");
    }
    public void turnOff() {
        System.out.println("TV를 끕니다.");
    }
    public void setVolume(int volume) {
        if(volume>RemoteControl.MAX_VOLUME) {
            this.volume = RemoteControl.MAX_VOLUME;
        } else if(volume<RemoteControl.MIN_VOLUME) {
            this.volume = RemoteControl.MIN_VOLUME;
        } else {
            this.volume = volume;
        }
        System.out.println("현재 TV 볼륨: " + volume);
    }

    // Searchable의 추상 메소드에 대한 실체 메소드
    public void search(String url) {
        System.out.println(url + " 을 검색합니다.");
    }
}
 