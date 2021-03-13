package hi;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class TextAreaDemo extends JFrame implements ActionListener {
    protected JTextField textField;
    protected JTextArea textArea;

    public TextAreaDemo() {
        this.setTitle("Text Area Text");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = new JTextField(30);
        textField.addActionListener(this);

        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(textField, BorderLayout.NORTH);

        this.pack();
        this.setVisible(true);
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
        new TextAreaDemo();
    }
}
