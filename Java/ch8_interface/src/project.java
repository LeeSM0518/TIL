abstract class Tool {
    abstract public void repair();
    abstract public void function();
}

interface Computer {
    void powerOn();
    void powerOff();
    default void usb(Mouse a){
        System.out.println(a.name + " 을 연결했습니다.");
    }
    default void usb(KeyBoard a){
        System.out.println(a.name + " 을 연결했습니다.");
    }
    default void blueTooth(Tool tool){
        if(tool instanceof Mouse) {
            System.out.println(((Mouse) tool).name + " 을 블루투스로 연결 했습니다.");
        }
        else if(tool instanceof KeyBoard) {
            System.out.println(((KeyBoard)tool).name + " 을 블루투스로 연결 했습니다.");
        }
    }
}

class DeskTop implements Computer {
    public void powerOn(){
        System.out.println("데스크탑 전원을 킨다.");
    }
    public void powerOff(){
        System.out.println("데스크탑 전원을 끊다");
    }
}

class NoteBook implements Computer {
    public void powerOn(){
        System.out.println("노트북 전원을 킨다.");
    }
    public void powerOff(){
        System.out.println("노트북 전원을 끊다");
    }
}

class Tablet implements Computer {
    public void powerOn(){
        System.out.println("테블릿 전원을 킨다.");
    }
    public void powerOff(){
        System.out.println("테블릿 전원을 끊다");
    }
}

class Mouse extends Tool{
    public String name = "마우스";

    @Override
    public void repair() {
        System.out.println(this.name + " 을 고친다.");
    }

    @Override
    public void function() {
        System.out.println("클릭");
    }
}

class KeyBoard extends Tool{
    public String name = "키보드";

    @Override
    public void repair() {
        System.out.println(this.name + "을 고친다.");
    }

    @Override
    public void function() {
        System.out.println("타자");
    }
}

class test {
    public static void main(String[] args) {
        Computer com = null;
        Tool tool = null;

        DeskTop deskTop = new DeskTop();
        NoteBook noteBook = new NoteBook();
        Tablet tablet = new Tablet();

        Mouse mouse = new Mouse();
        KeyBoard keyBoard = new KeyBoard();

        com = deskTop;
        com.powerOn();
        com.powerOff();
        com.usb(mouse);
        com.usb(keyBoard);

        com = noteBook;
        com.powerOn();
        com.powerOff();
        com.usb(mouse);
        com.usb(keyBoard);

        com = tablet;
        com.powerOn();
        com.powerOff();
        com.blueTooth(mouse);
        com.blueTooth(keyBoard);


        mouse.function();
        mouse.repair();
    }
}
