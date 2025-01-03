package project_resources;

//import java.applet.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
//import javax.swing.border.*;

class HighLow extends JPanel implements ActionListener, ChangeListener{
    JTabbedPane pane; // JTabbedPane 초기화
    Button button1 = new Button("입력"); // 입력 버튼
    TextField keyb = new TextField(10); // 키보드 입력을 받는 텍스트필드 생성
    TextArea txtStatus = new TextArea(15,55); // 게임 진행 및 결과를 표시할 TextArea 생성
    int num=35, count=0, mynum=0, gamecount=1; // 입력받을 숫자 및 인덱스 변수 생성
    JProgressBar progBar; // 진행율을 표시할 프로그레스 바 생성
    Label progTxt; // 프로그레스바 라벨

    public HighLow(){
        // HighLow 생성자
        JPanel panel;
        pane = new JTabbedPane(); // 탭 생성
        setLayout(new BorderLayout());
        String helpmessage="\n\t1에서 100 사이의 숫자를 입력하여\n\t감추어진 숫자를 찾는 게임 프로그램.\n\n\t여섯 번 만에 찾지 못하면 게임에서 진다.";
        createNumber(); // 게임 시작시 임의의 수 생성
        //프로그레스 바 생성
        progBar = new JProgressBar();
        progBar.setMinimum(0); // 프로그레스 바의 최소값 설정
        progBar.setMaximum(6); // 최대값 성정
        progBar.setValue(0); // 초기값:0으로 설정
        // 프로그레스 바 설명 라벨
        progTxt= new Label("현재 진행율: ");

        //게임 플레이 공간
        panel = new JPanel();
        panel.add(new Label("숫자 입력:"));
        panel.add(keyb);
        keyb.addActionListener(this);
        panel.add(button1);
        button1.addActionListener(this); // 버튼을 눌렀을 때 실행할 ActionListner 연결
        panel.add(txtStatus);
        panel.add(progTxt);
        panel.add(progBar); // 프로그레스 바
        pane.addTab("게임",panel);
        //도움말 공간
        panel = new JPanel();
        TextArea help=new TextArea(helpmessage,15,55); // 도움말 TextArea 출력
        panel.add(new Label("도움말:"));
        panel.add(help);
        pane.addTab("도움말",panel);
        pane.setSelectedIndex(0);

        pane.addChangeListener(this);
        add(pane,"Center");
    }
    // 임의의 수 생성
    public void createNumber(){
        Random r=new Random();
        num = r.nextInt(100)+1;
        System.out.println("생성된 임의의 수:"+num);
        gamecount=1; // 게임 카운터 초기화
        txtStatus.setText("임의의 숫자가 생성되었습니다.\n0부터 100 사이의 숫자를 입력하세요..(횟수 제한:6)\n\n");
    }

    // 숫자 입력받기
    public void inputMynumber() {
        // 정수형으로 받기
        mynum=Integer.parseInt(keyb.getText());
        // 숫자 생성
        //createNumber();
        if(mynum<0 || mynum>100){
            txtStatus.setText(txtStatus.getText()+"<입력하신 "+mynum+"은(는) 입력 범위를 초과했습니다.>\n");
        }
        keyb.setText(""); // 숫자 입력 후 입력 필드를 초기화 합니다.
    }

    //게임 시작
    public void playgame(){
        progBar.setValue(gamecount);

        if(mynum==num){
            txtStatus.setText(txtStatus.getText()+"\n★★★★★빙고★★★★★\n");
            progBar.setValue(6);
            gamecount=7;
        } else{
            if(mynum<num){
                txtStatus.setText(txtStatus.getText()+"["+(7-gamecount)+"] "+mynum+"보다 큽니다.\t["+(mynum+1)+"]~[100]사이의 숫자를 입력하세요.\n");
                gamecount++;
            } else if(mynum>num) {
                txtStatus.setText(txtStatus.getText()+"["+(7-gamecount)+"] "+mynum+"보다 작습니다.\t[0]~["+(mynum-1)+"]사이의 숫자를 입력하세요.\n");
                gamecount++;
            }
        }
        if(gamecount==7){
            txtStatus.setText(txtStatus.getText()+"\n게임이 끝났습니다. 정답은 "+num+" 입니다.\n <아무 숫자나 누르면 게임을 다시 시작합니다>\n");
        }
        if(gamecount==8){
            createNumber();//다시 새로운 숫자 생성
            progBar.setValue(0);
        }
    }

    // 윈도우 사이즈
    public Dimension getPreferredSize(){
        return new Dimension(450, 400);
    }
    // 탭이 전환될 때 효과
    public void stateChanged(ChangeEvent e){
        int curSelIndex = pane.getSelectedIndex();
        String curPaneTitle = pane.getTitleAt(curSelIndex);
        System.out.println("["+curPaneTitle + "]탭이 선택되었습니다");
    }
    //액션 추가
    public void actionPerformed(ActionEvent e) {
        inputMynumber();
        playgame();
    }
    // 메인 함수
    public static void main(String s[]){
        JFrame frame = new JFrame("즐거운 HighLow 게임~!");
        HighLow panel = new HighLow();

        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");

        frame.setSize(panel.getPreferredSize());
        extracted(frame);
        //원래 위의 문장은 frame.show(); 였음.

        frame.setDefaultCloseOperation(
                WindowConstants.DISPOSE_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    private static void extracted(JFrame frame) {
        frame.show();
        //frame.show();
    }
}