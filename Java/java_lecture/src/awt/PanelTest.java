package awt;

import java.awt.*;

public class PanelTest {

    Frame frame;

    // 생성자
    public PanelTest(String str) {
        // 패널 생성
        Panel panel1 = new Panel();
        // 프레임 생성
        frame = new Frame();
        // 패널 배경색 설정
        panel1.setBackground(Color.RED);
        // 프레임에 패널 올림
        frame.add(panel1);

        // 프레임 크기
        frame.setSize(300, 300);
        // 프레임 시각화
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // 패널 생성자 호출
        PanelTest panelTest = new PanelTest("패널테스트");
    }
}
