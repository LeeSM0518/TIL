# AWT

# Frame

* **Frame(프레임)**

  * 생성자

    | Frame()           | 타이틀이 빈 상태로 생성               |
    | ----------------- | ------------------------------------- |
    | Frame(String str) | str을 타이틀을 지정하여 프레임을 생성 |

  * 주요 메소드

    | Static Frame[] getFrames()                          | 애플리케이션에서 생성한 모든 프레임을 리턴                   |
    | --------------------------------------------------- | ------------------------------------------------------------ |
    | MenuBar getMenuBar()                                | 프레임의 메뉴바를 리턴                                       |
    | Int getState()                                      | 프레임의 상태를 리턴                                         |
    | String getTitle()                                   | 타이틀 바의 문자열을 리턴                                    |
    | void remove(MenuComponent m)                        | 프레임에서 지정한 메뉴를 제거                                |
    | void setMenuBar(MenuBar mb)                         | 프레임의 메뉴바를 지정                                       |
    | void setResizable(Boolean resizable)                | 프레임의 크기를 사용자가 변경할 수 있게 지정                 |
    | void setSize(int width, int height)                 | 이 컴퍼넌트의 사이즈를 width 및 height로 변경                |
    | void setVisible(boolean b)                          | 파라미터 b 값에 응해, 이 컴퍼넌트를 표시하는지 비표시로 하는지 |
    | void setLocation(int x, int y)                      | 이 컴퍼넌트를 새로운 위치로 이동                             |
    | void setBounds(int x, int y, int width, int height) | 위치와 크기를 설정                                           |



## Frame Example

```java
package awt;

import java.awt.*;

public class FrameTest1 {
    public static void main(String[] args) {
      // 프레임 생성
        Frame f = new Frame();
      // 위치 : 100, 100
      // 가로 세로 : 300, 300
        f.setBounds(100, 100, 300, 300);
      // 프레임을 보이도록 한다.
        f.setVisible(true);
    }
}
```



# Panel

- 컴포넌트들을 그룹 별로 묶어서 처리할 때 주로 사용
- 일반적으로 Frame 컴포넌트들을 직접 붙이지 않고 Panel에 그룹별로 붙이고 Panel을 Frame에 다시 붙임

* **생성자**

  | Panel()                     | 디폴트 레이아웃 매니저를 사용해 새로운 Panel 생성 |
  | --------------------------- | ------------------------------------------------- |
  | Panel(LayoutManager layout) | 지정된 레이아웃 매니저를 이용하여 새로운 Panel    |



## Panel Example

```java
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
```



# Dialog

 메인 윈도우 외에 메시지를 출력하거나 사용자로부터 자료를 입력 받을 때 사용하는 컨테이너

* **주요 생성자**

  | Dialog(Dialog owner)                              | 소유하는 Frame이 owner이 Dialog 생성                         |
  | ------------------------------------------------- | ------------------------------------------------------------ |
  | Dialog(Dialog owner, String title)                | title을 가진 Modeless Dialog 생성                            |
  | Dialog(Dialog owner, String title, boolean model) | model이 true 이면 model 다이얼 로그 생성 아니면 modeless 다이얼로그 생성 |
  | Dialog(Frame owner)                               | owner 프레임이 소유하는 Modeless 다이얼로그 생성             |



## Dialog Example

```java
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
```



# AWT Container

* **주요 메소드**

  | Color getBackground()                               | 컴퍼넌트의 배경 색상을 리턴                 |
  | --------------------------------------------------- | ------------------------------------------- |
  | Font getFont()                                      | 컴퍼넌트의 폰트를 리턴                      |
  | Color getForeground()                               | 컴퍼넌트의 전경색을 리턴                    |
  | int getHeight()                                     | 컴퍼넌트의 크기를 리턴                      |
  | Point getLocation                                   | 컴퍼넌트의 위치를 리턴                      |
  | Point getLocationOnScreen()                         | 화면의 좌표 공간 위치 리턴                  |
  | String getName()                                    | 컴퍼넌트의 이름을 리턴                      |
  | Conatiner getParent()                               | 컴퍼넌트의 부모를 리턴                      |
  | Dimension getSize()                                 | 사이즈를 오브젝트로 리턴                    |
  | int getWidth()                                      | 현재의 폭을 리턴                            |
  | int getX()                                          | 원점의 현재의 x좌표를 리턴                  |
  | Int getY()                                          | 원점의 현재의 y좌표를 리턴                  |
  | void setBackground(Color c)                         | 배경색을 설정                               |
  | void setBounds(int x, int y, int width, int height) | 컴퍼넌트를 이동해, 사이즈 변경              |
  | void setEnabled(boolean b)                          | b 값에 의해, 컴퍼넌트를 사용가능이나 불가능 |
  | void setFocusable(boolean focusable)                | 컴퍼넌트의 포커스 상태가 지정된 값으로 설정 |
  | void setFont(Font f)                                | 폰트 설정                                   |
  | void setForeground                                  | 전경색 설정                                 |
  | void setLocation(int x, int y)                      | 위치 설정                                   |
  | void Location(Point p)                              | 위치 설정                                   |
  | void setMaximumSIze(Dimension maximumSize)          | 최대 사이즈 설정                            |
  | void setMinimumSize(Dimension minimumSize)          | 최소 사이즈 설정                            |
  | void setName(String name)                           | 이름 설정                                   |
  | void setSize(Dimension d)                           | 사이즈 설정                                 |
  | void setVisiable(boolean b)                         | 표시 여부 설정                              |



# Button

* **생성자**

  | Button()           | 버튼 기초 생성자       |
  | ------------------ | ---------------------- |
  | Button(String str) | 버튼 이름정하는 생성자 |



## Button Example

```java
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
```



# CheckBox

* **생성자**

  | CheckBox()                                                 | 레이블이 없는 체크 박스 생성                                 |
  | ---------------------------------------------------------- | ------------------------------------------------------------ |
  | CheckBox(String label)                                     | 레이블을 설정한 체크 박스 생성                               |
  | CheckBox(String label, boolean state)                      | 지정한 레이블과 state를 넣어서 생성                          |
  | CheckBox(String label, boolean state, CheckboxGroup group) | 지정된 레이블이 붙은 체크 박스를 지정된 체크 박스 그룹에 구축해 지정된 상태로 생성 |
  | CheckboxGroup                                              | 객체를 생성해서 지정해주면 라디오 버튼이 됩니다.             |



## CheckBox Example

* 