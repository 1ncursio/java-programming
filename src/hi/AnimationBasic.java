package hi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

@SuppressWarnings("serial")
public class AnimationBasic extends JPanel implements ActionListener{
    private final int WIDTH = 500, HEIGHT = 300;
    private int dirX = 1, dirY = -1;
    private BufferedImage image;
    private Timer timer;
    private int x, y;
    private int imageWidth, imageHeight;
    private final int START_X=0, START_Y=150;

    public AnimationBasic() {
        // 이미지 파일을 읽어서 image 객체로 생성
        // 20ms 마다 이벤트를 발생시키는 timer 객체를 생성하고 timer를 start 시킴
        File file = new File("ship.jpg");
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            //TODO: handle exception
            e.printStackTrace();
            System.exit(1);
        }
        
        imageWidth = image.getWidth();
        imageHeight = image.getHeight();

        x = START_X;
        y = START_Y;

        timer = new Timer(20, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        // x, y 좌표에 이미지를 그린다.
        g.drawImage(image, x, y, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x += dirX * 4;
        y += dirY * 4;
        // x -= 3;
        if (x >= WIDTH - imageWidth) {
            dirX = -1;
        } else if (x <= 0) {
            dirX = 1;
        }

        if (y >= HEIGHT - imageHeight) {
            dirY = -1;
        } else if (y <= 0) {
            dirY = 1;
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new AnimationBasic());
        frame.setTitle("애니메이션 테스트");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }
}
