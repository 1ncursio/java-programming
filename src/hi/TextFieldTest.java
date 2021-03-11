package hi;

import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
class TextFieldFrame extends JFrame {
    private JButton button;
    private JTextField text, result;

    public TextFieldFrame() {
        setSize(300, 130);
        setTitle("제곱 계산하기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ButtonListener listener = new ButtonListener(); // 리스너 객체 생성
        JPanel panel = new JPanel();
        panel.add(new JLabel("숫자 입력: ")); // 레이블 생성
        text = new JTextField(15); // 컬럼수가 15인 텍스트 필드 생성
        text.addActionListener(listener); // 텍스트 필드에 리스너 연결
        panel.add(text);
        panel.add(new JLabel("제곱한 값: "));
        result = new JTextField(15); // 결과를 나타낼 텍스트 필드
        result.setEditable(false); // 편집 불가 설정
        panel.add(result);
        button = new JButton("OK");
        button.addActionListener(listener);
        panel.add(button);
        add(panel);
        setVisible(true);
    }

    // 텍스트 필드와 버튼의 액션 이벤트 처리
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button || e.getSource() == text) {
                String name = text.getText();
                int value = Integer.parseInt(name);
                result.setText(" " + value * value);
                text.requestFocus();
            }
        }
    }
}

@SuppressWarnings("serial")
public class TextFieldTest extends JFrame {
    public static void main(String[] args) {
        new TextFieldFrame();
    }
}