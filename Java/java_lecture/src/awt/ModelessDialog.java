package awt;

import java.awt.*;

public class ModelessDialog extends Frame {

    public ModelessDialog() {
        // Frame 의 타이틀
        super("다이얼로그 테스트");

        // 다이얼로그 생성
        Dialog d = new Dialog(this, "모덜리스 다이얼로그");
        // frame 위치 크기 설정
        setBounds(0, 0, 400, 400);
        // frame 시각화
        setVisible(true);
        // 다이얼로그 크기 설정
        d.setSize(200, 200);
        // 다이얼로그 시각화
        d.setVisible(true);
    }

    public static void main(String[] args) {
        ModelessDialog modelessDialog = new ModelessDialog();
    }

}
