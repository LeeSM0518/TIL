package project_resources.jtable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertAndRemove {
    public static void main(String[] args) {
        Dimension dimension = new Dimension(400, 300);

        JFrame frame = new JFrame("Hello");
        frame.setLocation(200, 400);
        frame.setPreferredSize(dimension);

        String header[] = {"학생이름", "국어", "영어", "수학"};
        String contents[][] = {
                {"박영수", "90", "87", "98"},
                {"김영희", "100", "99", "100"},
                {"김철수", "30", "25", "9"}
        };

        // 객체를 생성하여 초기값을 넣고, model 객체를 통해
        // 데이터를 테이블에 입력, 삭제한다.
        DefaultTableModel model = new DefaultTableModel(contents, header);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        // 컴럼 수로 크기 설정
        JTextField nameField = new JTextField(5);
        JTextField subject1 = new JTextField(3);
        JTextField subject2 = new JTextField(3);
        JTextField subject3 = new JTextField(3);

        panel.add(nameField);
        panel.add(subject1);
        panel.add(subject2);
        panel.add(subject3);

        // 버튼 객체 생성
        JButton addbtn = new JButton("추가");

        // 추가 버튼 이벤트 처리
        addbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputStr[] = new String[4];

                inputStr[0] = nameField.getText();
                inputStr[1] = subject1.getText();
                inputStr[2] = subject2.getText();
                inputStr[3] = subject3.getText();
                model.addRow(inputStr);

                nameField.setText("");
                subject1.setText("");
                subject2.setText("");
                subject3.setText("");
            }
        });

        // 삭제 버튼 객체 생성
        JButton cancleBtn = new JButton("삭제");

        // 삭제 버튼 이벤트 처리
        cancleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() == -1) {
                    return;
                } else {
                    if (table.getRowCount() == 1) {
                        frame.setVisible(false);
                        System.exit(1);
                    } else {
                        model.removeRow(table.getSelectedRow());
                    }
                }
            }
        });

        panel.add(addbtn);
        panel.add(cancleBtn);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
}
