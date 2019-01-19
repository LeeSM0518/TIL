# Java 프로젝트

## 참고 클래스

### JFrame

#### setLocation() 메소드

: 자신의 화면에서 해당 윈도우를 나타낼 위치 지정

```java
package project_resources;

import javax.swing.*;

public class SetLocationFrame {
    public static void main(String[] args) {
        // 창의 타이틀
        JFrame frame = new JFrame("Hello World!");
        
        // 창이 나오는 위치
        frame.setLocation(200, 400);
        
        // JFrame의 내용물에 알맞게 윈도우 크기를 조절해준다.
        frame.pack();
        
        // 보이게 하기
        frame.setVisible(true);
    }
}
```

<br>

#### setPreferredSize(), Dimension 클래스

: 윈도우의 크기를 지정할 수 있다.

```java
package project_resources;

import javax.swing.*;
import java.awt.*;

public class SetDimension {
    public static void main(String[] args) {
        // Dimension 객체에 윈도우 창 크기 저장
        Dimension dim = new Dimension(500, 300);

        JFrame frame = new JFrame("Hello World!");
        frame.setLocation(200, 400);
        
        // 윈도우 창 크기 저장시켜주기
        frame.setPreferredSize(dim);
        frame.pack();
        frame.setVisible(true);
    }
}
```

<br>

### JLabel

#### add() 메소드

: 윈도우에 올리기

```java
package project_resources.jlabel;

import javax.swing.*;
import java.awt.*;

public class Add {
    public static void main(String[] args) {
        Dimension dim = new Dimension(200, 100);

        JFrame frame = new JFrame("Hello!");
        frame.setLocation(200, 400);
        frame.setPreferredSize(dim);

        // JLabel 객체에 메시지를 넣어 생성한다.
        JLabel label = new JLabel("Welcome to Java");

        // 라벨을 윈도우에 추가한다.
        frame.add(label);
        frame.pack();
        frame.setVisible(true);
    }
}
```

<br>

#### setText() 메소드

: 문자열을 윈도우에 띄워주는 메소드

```java
package project_resources.jlabel;

import javax.swing.*;
import java.awt.*;

public class SetText {
    public static void main(String[] args) {
        Dimension dim = new Dimension(200, 100);

        JFrame frame = new JFrame("Hello!");
        frame.setLocation(200, 400);

        frame.setPreferredSize(dim);

        JLabel label = new JLabel();
        
        // 텍스트를 저장한다.
        label.setText("set Text Test");
        
        // 텍스트를 띄운다.
        frame.add(label);
        
        
        frame.pack();
        frame.setVisible(true);
    }
}
```

<br>

#### getText() 메소드

: 텍스트를 불러와서 Run에 띄워준다.

```java
package project_resources.jlabel;

import javax.swing.*;
import java.awt.*;

public class GetText {

    public static void main(String[] args) {
        Dimension dim = new Dimension(200, 100);

        JFrame frame = new JFrame("Hello!");
        frame.setLocation(200, 400);

        frame.setPreferredSize(dim);

        JLabel label = new JLabel();

        // 텍스트를 저장한다.
        label.setText("set Text Test");

        // 텍스트를 윈도우에 띄운다.
        frame.add(label);

        frame.pack();
        frame.setVisible(true);

        // 텍스트를 Run에 띄운다.
        System.out.println(label.getText());
    }
}
```

<br>

#### setVerticalAlignment(), setHorizontalAlignment()

: 세로 정렬, 가로 정렬 메소드

```java
package project_resources.jlabel;

import javax.swing.*;
import java.awt.*;

public class GetText {
    public static void main(String[] args) {
        Dimension dimension = new Dimension(200, 100);

        JFrame frame = new JFrame("Hello!");
        frame.setLocation(200, 400);
        frame.setPreferredSize(dimension);

        JLabel label = new JLabel();
        label.setText("setText Test");
        
        // 세로 아래 정렬
        label.setVerticalAlignment(SwingConstants.BOTTOM);
        
        // 가로 오른쪽 정렬
        label.setHorizontalAlignment(SwingConstants.RIGHT);

        frame.add(label);
        frame.pack();
        frame.setVisible(true);
    }
}	
```

<br>

### JButton

#### add() 메소드

: 버튼을 윈도우에 추가한다.

```java
package project_resources.jbutton;

import javax.swing.*;
import java.awt.*;

public class Add {
    public static void main(String[] args) {
        Dimension dimension = new Dimension(400, 100);

        JFrame frame = new JFrame("Hello");
        frame.setLocation(200, 400);
        frame.setPreferredSize(dimension);
        
        // 버튼 객체 생성
        JButton button = new JButton("Test");
        // 버튼 추가
        frame.add(button);

        frame.pack();
        frame.setVisible(true);
    }
}
```

<br>

#### setText()

: **button.setText("hello!");** 로 버튼의 텍스트를 지정해준다.

<br>

#### getText()

: **button.getText()** 로 버튼의 텍스트를 프로그램의 콘솔에 출력시킨다.

<br>

#### setVerticalAlignment(), setHorizontalAlignment()

: 세로 정렬, 가로 정렬 메소드

<br>

#### ActionListener()

: 버튼 클릭의 이벤트 처리

```java
package project_resources.jbutton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerMethod {
    public static void main(String[] args) {
        Dimension dimension = new Dimension(400, 100);

        JFrame frame = new JFrame("Hello");
        frame.setLocation(200, 400);
        frame.setPreferredSize(dimension);

        // Grid 레이아웃 객체 1열, 2행으로 추가
        GridLayout layout = new GridLayout(1, 2);
        frame.setLayout(layout);

        // JLabel 객체의 텍스트를 0으로 생성
        JLabel label = new JLabel("0");
        frame.add(label);

        // JButton 객체의 텍스트를 Click Counter로 생성
        JButton button = new JButton("Click Counter");
        frame.add(button);

        // 버튼 이벤트 처리 메소드
        ActionListener listener = new ActionListener() {
            @Override
            // setText로 글씨를 바꾼다.
            public void actionPerformed(ActionEvent e) {
                label.setText(String.valueOf(Integer.valueOf(label.getText()) + 1));
            }
        };
        button.addActionListener(listener);

        frame.add(button);
        frame.pack();
        frame.setVisible(true);
    }
}
```

<br>

### BorderLayout

: 동, 서, 남, 북, 중앙으로 UI를 배치시킬 수 있는 레이아웃

```java
package project_resources.border_lauyout;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutClass {
    public static void main(String[] args) {
        Dimension dimension = new Dimension(400, 100);

        JFrame frame = new JFrame("Hello");
        frame.setLocation(200, 400);
        frame.setPreferredSize(dimension);

        // 라벨 객체 "북" 내용물 중앙으로 생성, 베경 노란색
        JLabel label1 = new JLabel("북");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setOpaque(true);
        label1.setBackground(Color.YELLOW);

        JLabel label2 = new JLabel("서서서서");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setVerticalAlignment(SwingConstants.CENTER);
        label2.setOpaque(true);
        label2.setBackground(Color.RED);

        JLabel label3 = new JLabel("동동");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setVerticalAlignment(SwingConstants.CENTER);
        label3.setOpaque(true);
        label3.setBackground(Color.BLUE);

        JButton button = new JButton("남");

        JLabel label5 = new JLabel("중앙");

        label5.setHorizontalAlignment(SwingConstants.CENTER);
        label5.setVerticalAlignment(SwingConstants.CENTER);
        label5.setOpaque(true);
        label5.setBackground(Color.WHITE);

        // 동, 서, 남, 북. 가운데 추가
        frame.add(label1, BorderLayout.NORTH);
        frame.add(label2, BorderLayout.WEST);
        frame.add(label3, BorderLayout.EAST);
        frame.add(button, BorderLayout.SOUTH);
        frame.add(label5, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }
}
```



### JPanel

: 보조 프레임. UI의 배치를 할 수 있다.

## 참고 인터페이스

