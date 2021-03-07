package hi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
class TextPanel extends JPanel {

    JTextField a, b, c;
    double A, B, C;
    JButton button;

    public TextPanel() {
        setBounds(0, 0, 500, 100);
        a = new JTextField("1.0", 10);
        b = new JTextField("-5.0", 10);
        c = new JTextField("6.0", 10);
        add(a);
        add(b);
        add(c);
        button = new JButton("Draw");
        add(button);
    }

    Double getA() {
        return Double.parseDouble(a.getText());
    }

    Double getB() {
        return Double.parseDouble(b.getText());
    }

    Double getC() {
        return Double.parseDouble(c.getText());
    }
}

@SuppressWarnings("serial")
public class FunctionFrame extends JFrame implements ActionListener {
    TextPanel TextPanel = new TextPanel();
    GraphPanel GraphPanel = new GraphPanel();

    public FunctionFrame() {
        this.setTitle("2차함수 그래프");
        this.setSize(500, 400);
        this.add(TextPanel);
        this.add(GraphPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        TextPanel.button.addActionListener(this);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new FunctionFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        GraphPanel.setABC(TextPanel.getA(), TextPanel.getB(), TextPanel.getC());
        GraphPanel.setRepaint();
    }

}

@SuppressWarnings("serial")
class GraphPanel extends JPanel {
    Double A = 0.0, B = 0.0, C = 0.0;
    JPanel test = new JPanel();

    void setRepaint() {
        repaint();
    }

    public GraphPanel() {
        setBounds(0, 100, 500, 300);
    }

    void setABC(Double A, Double B, Double C) {
        this.A = A;
        this.B = B;
        this.C = C;
    }

    @Override
    public void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.drawLine(0, 200, 400, 200);
        g2.drawLine(200, 0, 200, 400);
        g2.setPaint(Color.red);

        for (int i = -200; i < 200; i++) {
            int x = i;
            int y = (int) (A * x * x + B * x + C);
            g2.fillOval(200 + x - 2, 200 - (y - 2), 4, 4);
        }
    }
}