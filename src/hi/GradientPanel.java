package hi;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GradientPanel extends JPanel {
    ArrayList<Shape> shapeList = new ArrayList<Shape>();

    public GradientPanel() {
        shapeList.add(new Rectangle2D.Float(10, 10, 70, 80));
        shapeList.add(new RoundRectangle2D.Float(110, 10, 70, 80, 20, 20));
        shapeList.add(new Ellipse2D.Float(210, 10, 80, 80));
        shapeList.add(new Arc2D.Float(310, 10, 80, 80, 45, 225, Arc2D.OPEN));
        shapeList.add(new Arc2D.Float(410, 10, 80, 80, 45, 225, Arc2D.OPEN));
        shapeList.add(new Arc2D.Float(510, 10, 80, 80, 45, 225, Arc2D.PIE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // TODO Auto-generated method stub
        Graphics2D g2 = (Graphics2D) g;

        // 안티엘리어싱 설정
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setStroke(new BasicStroke(3));
        GradientPaint gp = new GradientPaint(0, 10, Color.WHITE, 0, 70, Color.RED);

        g2.setPaint(gp);

        for (Shape s : shapeList) {
            g2.fill(s);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new GradientPanel());
        frame.setTitle("그라디언트 패널");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 140);
        frame.setVisible(true);
    }
}
