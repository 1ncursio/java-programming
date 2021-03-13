package hi;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.*;

public class CaptureDemo extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private Robot robot;
    private JPanel panel;
    private JButton button;

    public CaptureDemo() {
        super("화면 캡쳐 프로그램");

        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        button = new JButton("화면 캡쳐");
        button.addActionListener(this);
        panel.add(button);
        this.add(panel, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 300);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            robot = new Robot();
            Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage bufferedImage = robot.createScreenCapture(area);
            ImageIO.write(bufferedImage, "jpg", new File("./화면캡쳐.jpg"));
        } catch (AWTException awtE) {
            awtE.printStackTrace();
        } catch (IOException ioE) {
            ioE.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new CaptureDemo();
    }
}
