package week4;

import java.awt.*;

import java.awt.event.*;

import java.util.List;

class Member extends Frame implements ActionListener {

    String e_name;

    String e_gender = "남";

    String e_code;

    String e_code2;

    String e_address;

    String e_listDepart = "기획부";

    String e_Hobby = "운동";


    Label name, gender, code, addr, depart, hobby, myinfor, dot;

    TextField tfname, tfcode, tfcode2, tfaddr;

    Choice listDepart;

    Checkbox man, woman, exer, music, movie, compu;

    TextArea taMyinfor;

    Button btn1, btn2, btndel;


    Panel pName, pCode, pAddr, pDepart, pHobby, pMyinfor, pbtn;

    Panel Content, Content2;


    public Member() {

        super("사원등록");


        name = new Label("성                명");

        gender = new Label("성        별");

        code = new Label("주민등록번호");

        addr = new Label("주                소");

        depart = new Label("부      서      명");

        hobby = new Label("취                미");

        myinfor = new Label(" 자 기 소 개");

        dot = new Label("-");


        tfname = new TextField("", 10);

        tfcode = new TextField("", 8);

        tfcode2 = new TextField("", 8);

        tfaddr = new TextField("", 30);


        listDepart = new Choice();

        listDepart.add("기획부");

        listDepart.add("안기부");

        listDepart.add("지아비부");


        CheckboxGroup cbgName = new CheckboxGroup();

        man = new Checkbox("남", cbgName, true);

        woman = new Checkbox("여", cbgName, false);


        exer = new Checkbox("운동", true);

        music = new Checkbox("음악감상", false);

        movie = new Checkbox("영화", false);

        compu = new Checkbox("콤푸타", false);


        taMyinfor = new TextArea("", 10, 45);


        btn1 = new Button("저장");

        btn2 = new Button("종료");

        btndel = new Button("삭제");


        pName = new Panel(new FlowLayout(FlowLayout.LEFT));

        pCode = new Panel(new FlowLayout(FlowLayout.LEFT));

        pAddr = new Panel(new FlowLayout(FlowLayout.LEFT));

        pDepart = new Panel(new FlowLayout(FlowLayout.LEFT));

        pHobby = new Panel(new FlowLayout(FlowLayout.LEFT));

        pMyinfor = new Panel(new FlowLayout(FlowLayout.LEFT));

        pbtn = new Panel();


        Content = new Panel(new BorderLayout());

        Content2 = new Panel(new BorderLayout());


        pName.add(name);

        pName.add(tfname);

        pName.add(gender);

        pName.add(man);

        pName.add(woman);


        pCode.add(code);

        pCode.add(tfcode);

        pCode.add(dot);

        pCode.add(tfcode2);


        pAddr.add(addr);

        pAddr.add(tfaddr);


        pDepart.add(depart);

        pDepart.add(listDepart);


        pHobby.add(hobby);

        pHobby.add(exer);

        pHobby.add(music);

        pHobby.add(movie);

        pMyinfor.setLayout(new BorderLayout());

        pMyinfor.add(myinfor, BorderLayout.NORTH);

        pMyinfor.add(taMyinfor);


        pbtn.add(btn1);

        pbtn.add(btn2);


        Content.add(pName, BorderLayout.NORTH);

        Content.add(pCode, BorderLayout.CENTER);

        Content.add(pAddr, BorderLayout.SOUTH);

        Content2.add(pDepart, BorderLayout.NORTH);

        Content2.add(pHobby, BorderLayout.CENTER);

        Content2.add(pMyinfor, BorderLayout.SOUTH);


        add(Content);

        add(Content2);

        add(pbtn, BorderLayout.SOUTH);


        setLayout(new FlowLayout());

        setSize(400, 500);

        setResizable(false);

        setVisible(true);


        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                System.exit(0);

            }

        });


        man.addItemListener(new ItemListener() {


            @Override

            public void itemStateChanged(ItemEvent e) {

                e_gender = e.getItem().toString();


            }

        });

        woman.addItemListener(new ItemListener() {


            @Override

            public void itemStateChanged(ItemEvent e) {

                e_gender = e.getItem().toString();


            }

        });


        listDepart.addItemListener(new ItemListener() {


            @Override

            public void itemStateChanged(ItemEvent e) {

                e_listDepart = e.getItem().toString();

            }

        });


        exer.addItemListener(new ItemListener() {


            @Override

            public void itemStateChanged(ItemEvent e) {

                if (e.getItem().toString().equals("운동") && e.getStateChange() == 1)

                    e_Hobby = "운동";

            }

        });

        music.addItemListener(new ItemListener() {


            @Override

            public void itemStateChanged(ItemEvent e) {

                if (e.getItem().toString().equals("음악감상") && e.getStateChange() == 1)

                    e_Hobby = "음악감상";

            }

        });

        movie.addItemListener(new ItemListener() {


            @Override

            public void itemStateChanged(ItemEvent e) {

                if (e.getItem().toString().equals("영화") && e.getStateChange() == 1)

                    e_Hobby = "영화";

            }

        });

        compu.addItemListener(new ItemListener() {


            @Override

            public void itemStateChanged(ItemEvent e) {

                if (e.getItem().toString().equals("컴퓨터") && e.getStateChange() == 1)

                    e_Hobby = "컴퓨터";

            }

        });


        btn1.addActionListener(this);


        btn2.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }

        });


        btndel.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                tfname.setText("");

                tfcode.setText("");

                tfcode2.setText("");

                tfaddr.setText("");

                taMyinfor.setText(""); // 질문

            }

        });


    }//member


    @Override // 저장버튼.

    public void actionPerformed(ActionEvent e) {

        e_name = tfname.getText();

        e_code = tfcode.getText();

        e_code2 = tfcode2.getText();

        e_address = tfaddr.getText();


        if (e_name.equals(""))

            taMyinfor.append("이름 값을 입력해주세요.\n");

        else if (e_code.equals(""))

            taMyinfor.append("주민번호 앞자리 를 입력해주세요.\n");

        else if (e_code2.equals(""))

            taMyinfor.append("주민번호 뒷자리 를 입력해주세요.\n");

        else if (e_address.equals(""))

            taMyinfor.append("주소 값을 입력해주세요.\n");


        else {

            taMyinfor.append("이            름 : " + e_name + "\n");

            taMyinfor.append("성            별 : " + e_gender + "\n");

            taMyinfor.append("주민등록번호 : " + e_code + "-" + e_code2 + "\n");

            taMyinfor.append("부    서     명 : " + e_listDepart + "\n");

            taMyinfor.append("주            소 : " + e_address + " " + "\n");

            taMyinfor.append("취            미 : " + e_Hobby + "\n\n");

        }

    }

    public static void main(String[] args) {
        Member member = new Member();
    }

}