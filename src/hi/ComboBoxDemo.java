package hi;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.*;
import java.awt.event.*;

public class ComboBoxDemo extends JFrame implements ActionListener {
    private JLabel label;
    private JComboBox<String> animalList;

    public ComboBoxDemo() {
        this.setTitle("콤보 박스");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);

        String[] animals = { "dog", "lion", "tiger" };
        animalList = new JComboBox<>(animals);
        animalList.setSelectedIndex(0);
        animalList.addActionListener(this);

        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        changePicture(animals[animalList.getSelectedIndex()]);

        this.add(animalList, BorderLayout.NORTH);
        this.add(label, BorderLayout.CENTER);
        this.setVisible(true);
    }

    protected void changePicture(String name) {
        ImageIcon icon = new ImageIcon(name + ".gif");
        label.setIcon(icon);

        label.setText(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String name = (String) animalList.getSelectedItem();
        changePicture(name);

    }

    public static void main(String[] args) {
        new ComboBoxDemo();
    }
}
