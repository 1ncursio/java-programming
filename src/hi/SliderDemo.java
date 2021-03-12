package hi;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;

public class SliderDemo extends JFrame implements ChangeListener {
    static final int INIT_VALUE = 30;
    private JButton OKBtn;
    private JSlider slider;
    private JButton btn;

    public SliderDemo() {
        JPanel panel;

        this.setTitle("슬라이더 테스트");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        JLabel label = new JLabel("슬라이더를 움직여보세요", SwingConstants.CENTER);
        panel.add(label);

        slider = new JSlider(0, 30, INIT_VALUE);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(this);
        panel.add(slider);

        btn = new JButton(" ");
        ImageIcon icon = new ImageIcon("dog.jpg");
        btn.setIcon(icon);
        btn.setSize(INIT_VALUE * 10, INIT_VALUE * 10);
        panel.add(btn);

        this.add(panel);
        this.setSize(300, 500);
        this.setVisible(true);

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // TODO Auto-generated method stub
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            int value = source.getValue();
            btn.setSize(value * 10, value * 10);

        }
    }

    public static void main(String[] args) {
        new SliderDemo();
    }
}
