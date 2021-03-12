package hi;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;

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

    class TypePanel extends JPanel implements ChangeListener {
        private JRadioButton combo, potato, bulgogi;
        private ButtonGroup btnGroup;

        public TypePanel() {
            this.setLayout(new GridLayout(3, 1));
            combo = new JRadioButton("콤보", true);
            combo.addChangeListener(this);
            potato = new JRadioButton("포테이토");
            potato.addChangeListener(this);
            bulgogi = new JRadioButton("불고기");
            bulgogi.addChangeListener(this);

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
        public void stateChanged(ChangeEvent e) {
            // TODO Auto-generated method stub
            // System.out.println("타입 called");
            if (e.getSource() == combo) {
                temp1 = 0;
            } else if (e.getSource() == potato) {
                temp1 = 1;
            } else {
                temp1 = 2;
            }
        }

    }

    class ToppingPanel extends JPanel implements ChangeListener {
        private JRadioButton pepper, cheese, peperoni, bacon;
        private ButtonGroup btnGroup;

        public ToppingPanel() {
            this.setLayout(new GridLayout(4, 1));
            pepper = new JRadioButton("피망", true);
            pepper.addChangeListener(this);
            cheese = new JRadioButton("치즈");
            cheese.addChangeListener(this);
            peperoni = new JRadioButton("페퍼로니");
            peperoni.addChangeListener(this);
            bacon = new JRadioButton("베이컨");
            bacon.addChangeListener(this);
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
        public void stateChanged(ChangeEvent e) {
            // System.out.println("토핑 called");
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

    class SizePanel extends JPanel implements ChangeListener {
        private JRadioButton small, medium, large;
        private ButtonGroup btnGroup;

        public SizePanel() {
            this.setLayout(new GridLayout(3, 1));
            small = new JRadioButton("small", true);
            small.addChangeListener(this);
            medium = new JRadioButton("Medium");
            medium.addChangeListener(this);
            large = new JRadioButton("Large");
            large.addChangeListener(this);
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
        public void stateChanged(ChangeEvent e) {
            // System.out.println("사이즈 called");
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
