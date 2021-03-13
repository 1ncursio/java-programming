package hi;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("serial")
public class ComboBoxDemo extends JFrame implements ActionListener {
    private JLabel label;
    private JComboBox<String> animalList;
    private ArrayList<String> animals;

    JPanel comboTextPanel = new ComboTextPanel();

    public ComboBoxDemo() {
        this.setTitle("콤보 박스");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);

        animals = new ArrayList<>(Arrays.asList("dog", "lion", "tiger"));
        // String[] animals = { "dog", "lion", "tiger" };
        animalList = new JComboBox<>(animals.toArray(new String[animals.size()]));
        animalList.setSelectedIndex(0);
        animalList.addActionListener(this);

        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        changePicture(animals.get(animalList.getSelectedIndex()));

        this.add(comboTextPanel, BorderLayout.SOUTH);
        this.add(animalList, BorderLayout.NORTH);
        this.add(label, BorderLayout.CENTER);
        this.setVisible(true);
    }

    protected void changePicture(String name) {
        ImageIcon icon = new ImageIcon(name + ".jpg");
        label.setIcon(icon);

        label.setText(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        System.out.println("액션");
        String name = (String) animalList.getSelectedItem();
        changePicture(name);

    }

    public static void main(String[] args) {
        new ComboBoxDemo();
    }

    class ComboTextPanel extends JPanel implements ActionListener {
        private JTextField textField;

        public ComboTextPanel() {
            this.setLayout(new GridLayout(1, 1));
            textField = new JTextField(30);
            this.add(textField);
            textField.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            String text = textField.getText();
            animalList.addItem(text);
            textField.selectAll();
            System.out.println(animals);
            System.out.println("sdgsdg");
        }

    }
}
