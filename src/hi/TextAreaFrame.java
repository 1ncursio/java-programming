package hi;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.*;

public class TextAreaFrame extends JFrame implements ActionListener {
    protected JTextField textField;
    protected JTextArea textArea;

    public TextAreaFrame() {
        setTitle("Text Area Text");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = new JTextField(30);
        textField.addActionListener(this);

        textArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);

        add(textField, BorderLayout.NORTH);
        add(textArea, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String text = textField.getText();
        textArea.append(text + "\n");
        textField.selectAll();
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        new TextAreaFrame();
    }
}
