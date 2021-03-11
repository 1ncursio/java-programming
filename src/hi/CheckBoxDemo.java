package hi;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CheckBoxDemo extends JPanel implements ItemListener {
    private JCheckBox[] checkBoxes = new JCheckBox[3];
    String[] fruits = { "apple", "grape", "orange" };
    private JLabel[] labels = new JLabel[3];
    ImageIcon[] icons = new ImageIcon[3];

    public CheckBoxDemo() {
        this.setLayout(new GridLayout(0, 4));
        for (int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i] = new JCheckBox(fruits[i]);
            checkBoxes[i].addItemListener(this);
            labels[i] = new JLabel(fruits[i] + ".jpg");
            icons[i] = new ImageIcon(fruits[i] + ".jpg");
        }

        JPanel checkBoxPanel = new JPanel(new GridLayout(0, 1));
        for (int i = 0; i < checkBoxes.length; i++) {
            checkBoxPanel.add(checkBoxes[i]);
        }

        this.add(checkBoxPanel);

        for (int i = 0; i < labels.length; i++) {
            this.add(labels[i]);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        Object source = e.getItemSelectable();
        for (int i = 0; i < checkBoxes.length; i++) {
            if (source == checkBoxes[i]) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    labels[i].setIcon(icons[i]);
                else
                    labels[i].setIcon(null);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CheckBoxDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new CheckBoxDemo());
        frame.setSize(500, 200);
        frame.setVisible(true);
    }

}
