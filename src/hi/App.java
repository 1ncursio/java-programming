package hi;

import java.awt.*;

import java.awt.geom.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App extends JFrame{

  public App(String title){

    super(title);

  }

  class MyPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
      // TODO Auto-generated method stub
      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
  
      Rectangle2D rect1=new Rectangle2D.Double(50.0, 100.0, 100.0, 100.0);
  
      Ellipse2D ellipse1=new Ellipse2D.Double(200.0, 100.0, 100.0, 100.0);
  
  
      g2.draw(rect1);
  
      g2.fill(ellipse1);
      // super.paintComponent(g);
    }
  }

  public static void main(String[] args){

    JFrame frame=new App("평면 도형");
    MyPanel = new JPanel();

    frame.setSize(350,250);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


  }

}