package hi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImageLabelTest extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel label;
    private JButton button;

    public ImageLabelTest() {
        this.setSize(300, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        label = new JLabel("이미지를 보려면 버튼을 누르세요");
        button = new JButton("이미지 보기");
        ImageIcon icon = new ImageIcon("cat.jpg");
        button.setIcon(icon);
        panel.add(label);
        panel.add(button);
        button.addActionListener(this);

        this.add(panel);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        ImageIcon dog = new ImageIcon("dog.jpg");
        File file = new File("dog.jpg");
        System.out.println(file.getAbsolutePath());
        label.setIcon(dog);
        label.setText(null);
        System.out.println("눌러따");
    }

    public static void main(String[] args) {
        new ImageLabelTest();
    }

}