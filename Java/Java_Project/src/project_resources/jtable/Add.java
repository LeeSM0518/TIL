package project_resources.jtable;

import javax.swing.*;
import java.awt.*;

public class Add {
    public static void main(String[] args) {
        Dimension dimension = new Dimension(400, 150);

        JFrame frame = new JFrame("Hello");
        frame.setLocation(200, 400);
        frame.setPreferredSize(dimension);

        // 테이블의 헤더와 내용 정보 정의
         String header[] = {"학생이름", "국어", "영어", "수학"};
         String contents[][] = {
                 {"박영수", "90", "87", "98"},
                 {"김영희", "100", "99", "100"},
                 {"김철수", "30", "25", "9"}
         };

         // 테이블 클래스의 객체 생성
         JTable table = new JTable(contents, header);
         // UI 들이 칸이 부족하면 위 아래로 스크롤을 제공하여 보여준다.
         JScrollPane scrollPane = new JScrollPane(table);

         // 테이블의 1, 1 위치의 값 변경
         table.setValueAt("200", 1, 1);
         // 테이블의 1, 1 위치의 값 가져오기
        System.out.println(table.getValueAt(1, 1));

        // 테이블의 행을 가져온다.
        System.out.println(table.getRowCount());

        // 테이블의 컬럼 개수를 가져온다.
        System.out.println(table.getColumnCount());

        // 컬럼의 0번 째를 호출한다.
        System.out.println(table.getColumnName(0));

         frame.add(scrollPane);
         frame.pack();
         frame.setVisible(true);
    }
}
