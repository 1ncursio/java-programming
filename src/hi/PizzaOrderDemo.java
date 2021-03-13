package hi;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class PizzaOrderDemo extends JFrame implements ActionListener {
    private int sum, temp1, temp2, temp3;

    private JButton orderBtn, cancelBtn;
    private JPanel orderPanel;
    private JTextField priceField;

    JPanel welcomePanel = new WelcomPanel();

    JPanel typePanel = new TypePanel();
    JPanel toppingPanel = new ToppingPanel();
    JPanel sizePanel = new SizePanel();

    public PizzaOrderDemo() {
        this.setTitle("피자 주문");
        this.setSize(500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        orderBtn = new JButton("주문");
        orderBtn.addActionListener(this);

        cancelBtn = new JButton("취소");
        cancelBtn.addActionListener(this);

        priceField = new JTextField();
        priceField.setEditable(false);
        priceField.setColumns(6);

        orderPanel = new JPanel();
        orderPanel.add(orderBtn);
        orderPanel.add(cancelBtn);
        orderPanel.add(priceField);

        this.add(welcomePanel, BorderLayout.NORTH);
        this.add(orderPanel, BorderLayout.SOUTH);
        this.add(sizePanel, BorderLayout.EAST);
        this.add(typePanel, BorderLayout.WEST);
        this.add(toppingPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == orderBtn) {
            sum = 0;
            switch (temp1) {
            case 0:
                sum += 3000;
                break;
            case 1:
                sum += 4000;
                break;
            case 2:
                sum += 5000;
                break;
            default:
                break;
            }

            switch (temp2) {
            case 0:
                sum += 100;
                break;
            case 1:
                sum += 200;
                break;
            case 2:
                sum += 300;
                break;
            case 3:
                sum += 400;
                break;
            default:
                break;
            }

            switch (temp3) {
            case 0:
                sum += 10000;
                break;
            case 1:
                sum += 12000;
                break;
            case 2:
                sum += 14000;
                break;
            default:
                break;
            }
            priceField.setText(String.valueOf(sum));
            System.out.println("타입:" + temp1 + ", 토핑:" + temp2 + ", 사이즈:" + temp3);
        } else if (e.getSource() == cancelBtn) {
            temp1 = 0;
            temp2 = 0;
            temp3 = 0;
            sum = 0;
            priceField.setText(String.valueOf(sum));
        }
    }

    class WelcomPanel extends JPanel {
        private JLabel message;

        public WelcomPanel() {
            message = new JLabel("자바 피자에 오신 것을 환영합니다.");
            this.add(message);
        }
    }

    class TypePanel extends JPanel implements ActionListener {
        private JRadioButton combo, potato, bulgogi;
        private ButtonGroup btnGroup;

        public TypePanel() {
            this.setLayout(new GridLayout(3, 1));
            combo = new JRadioButton("콤보 3000원", true);
            combo.addActionListener(this);
            potato = new JRadioButton("포테이토 4000원");
            potato.addActionListener(this);
            bulgogi = new JRadioButton("불고기 5000원");
            bulgogi.addActionListener(this);

            btnGroup = new ButtonGroup();
            btnGroup.add(combo);
            btnGroup.add(potato);
            btnGroup.add(bulgogi);

            this.setBorder(BorderFactory.createTitledBorder("종류"));

            this.add(combo);
            this.add(potato);
            this.add(bulgogi);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == combo) {
                temp1 = 0;
            } else if (e.getSource() == potato) {
                temp1 = 1;
            } else {
                temp1 = 2;
            }
        }

    }

    class ToppingPanel extends JPanel implements ActionListener {
        private JRadioButton pepper, cheese, peperoni, bacon;
        private ButtonGroup btnGroup;

        public ToppingPanel() {
            this.setLayout(new GridLayout(4, 1));
            pepper = new JRadioButton("피망 100원", true);
            pepper.addActionListener(this);
            cheese = new JRadioButton("치즈 200원");
            cheese.addActionListener(this);
            peperoni = new JRadioButton("페퍼로니 300원");
            peperoni.addActionListener(this);
            bacon = new JRadioButton("베이컨 400원");
            bacon.addActionListener(this);
            btnGroup = new ButtonGroup();
            btnGroup.add(pepper);
            btnGroup.add(cheese);
            btnGroup.add(peperoni);
            btnGroup.add(bacon);
            this.setBorder(BorderFactory.createTitledBorder("추가토핑"));
            this.add(pepper);
            this.add(cheese);
            this.add(peperoni);
            this.add(bacon);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == pepper) {
                temp2 = 0;
            } else if (e.getSource() == cheese) {
                temp2 = 1;
            } else if (e.getSource() == peperoni) {
                temp2 = 2;
            } else if (e.getSource() == bacon) {
                temp2 = 3;
            }
        }
    }

    class SizePanel extends JPanel implements ActionListener {
        private JRadioButton small, medium, large;
        private ButtonGroup btnGroup;

        public SizePanel() {
            this.setLayout(new GridLayout(3, 1));
            small = new JRadioButton("small 10000원", true);
            small.addActionListener(this);
            medium = new JRadioButton("Medium 12000원");
            medium.addActionListener(this);
            large = new JRadioButton("Large 14000원");
            large.addActionListener(this);
            btnGroup = new ButtonGroup();
            btnGroup.add(small);
            btnGroup.add(medium);
            btnGroup.add(large);
            this.setBorder(BorderFactory.createTitledBorder("크기"));
            this.add(small);
            this.add(medium);
            this.add(large);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == small) {
                temp3 = 0;
            } else if (e.getSource() == medium) {
                temp3 = 1;
            } else if (e.getSource() == large) {
                temp3 = 2;
            }
        }
    }

    public static void main(String[] args) {
        new PizzaOrderDemo();
    }
}
