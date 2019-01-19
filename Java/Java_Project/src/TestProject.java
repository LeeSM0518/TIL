/*
 * 아바타와 괴물 게임 만들기
 * 아바타와 괴물이 등장하고 괴물은 터미네이터처럼 끈임없이 아바타를 따라다니는 게임을 만들어보자
 * 아바타는 15 x 15 크기의 "@" 문자열 레이블로 만들고 괴물 역시 "M" 문자열 레이블로 만든다.
 * 아바타는 상하좌우 키를 이용하여 패널상에서 움직이면서 도망가고, 괴물은 자동으로 아바타를 추적하여
 * 따라다닌다. 아바타가 도망치는 속도가 떨어지면 괴물에게 잡히게 된다. 이 게임은 점수를 관리하지
 * 않는다. 오직 'q' 키를 입력했을 때 게임이 종료된다.
 * 아바타는 한 번의 키 입력에 따라 10픽셀씩 이동하며, 괴물은 상하좌우 대각선 방향으로 움직일 수 있고 200ms
 * 마다 한 번 이동하고 그 거리는 5 픽셀이다.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.math.*;

class ManLabel extends JLabel {
    ManLabel () {
        this.setText("@");
        this.setForeground(Color.red);
    }
}
class MonsterLabel extends JLabel {
    MonsterLabel () {
        this.setText("M");
    }
}
class OpenChallenge extends JFrame {
    ManLabel manLabel;
    MonsterLabel monsterLabel;

    int manX, manY;
    int monX, monY;
    int height, width;

    OpenChallenge() {
        setTitle("OpenChallenge");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        manX = this.getWidth() / 2;
        manY = this.getHeight() / 2;

        width = this.getWidth() - 30;
        height = this.getHeight() - 30;

        GamePanel gamePanel = new GamePanel();
        setContentPane(gamePanel);

        setVisible(true);
    }

    class GamePanel extends JPanel {
        Random random = new Random();

        GamePanel() {
            setLayout(null);

            monX = random.nextInt(height) / 10 * 10;
            monY = random.nextInt(width) / 10 * 10;

            manLabel = new ManLabel();
            manLabel.setBounds(manX, manY, 15, 15);
            this.addKeyListener(new ManKeyListener());

            monsterLabel = new MonsterLabel();
            monsterLabel.setBounds(monX, monY, 15, 15);

            MonsterThread monsterTh = new MonsterThread();

            add(manLabel);
            add(monsterLabel);

            this.setFocusable(true);
            this.grabFocus();

            monsterTh.start();
        }
    }

    class MonsterThread extends Thread {
        public void run() {
            while (true) {
                if (monX > manX) {
                    monX -= 5;
                    System.out.println("left");
                }
                if (monX < manX) {
                    monX += 5;
                    System.out.println("right");
                }
                if (monY > manY) {
                    monY -= 5;
                    System.out.println("up");
                }
                if (monY < manY) {
                    monY += 5;
                    System.out.println("down");
                }
                monsterLabel.setLocation(monX, monY);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    class ManKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP)
                manY -= 10;
            if (e.getKeyCode() == KeyEvent.VK_DOWN)
                manY += 10;
            if (e.getKeyCode() == KeyEvent.VK_LEFT)
                manX -= 10;
            if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                manX += 10;
            if (e.getKeyChar() == 'q')
                System.exit(0);
            manLabel.setLocation(manX, manY);
        }
    }

    public static void main(String[] args) {
        new OpenChallenge();
    }
}