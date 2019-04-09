package awt;

import java.awt.*;

public class ButtonExam extends Frame {

    Button btn1, btn2, btn3;

    public ButtonExam(String str) {
        // str 타이틀을 가진 프레임 생성
        super(str);

        // 패널 생성
        Panel p = new Panel();

        // 버튼 생성
        btn1 = new Button("가위");
        btn2 = new Button("바위");
        btn3 = new Button("보");

        // 버튼을 패널에 올림
        p.add(btn1);
        p.add(btn2);
        p.add(btn3);

        // 패널을 프레임에 올림
        add(p);

        // 버튼3 사용불가 상태로 설정
        btn3.setEnabled(false);

        // 프레임 크기 설정
        setSize(300, 200);

        // 프레임 시각화
        setVisible(true);
    }

    public static void main(String[] args) {
        new ButtonExam("버튼");
    }

}
