package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Main extends JPanel implements ActionListener {
    private JTextField inputField = new JTextField(30);
    private JButton searchBtn = new JButton("검색");
    private JButton addBtn = new JButton("추가");
    private static Map<String, String> dict = new HashMap<>();
    private static final String driverClassName = "org.mariadb.jdbc.Driver";
    private String server = "localhost"; // MySQL 서버 주소
    private String database = "scpark"; // MySQL DATABASE 이름
    private String user_name = "root"; //  MySQL 서버 아이디
    private String password = "nodejsbook"; // MySQL 서버 비밀번호

    public Main() {
        this.add(inputField);
        this.add(searchBtn);
        this.add(addBtn);

        searchBtn.addActionListener(this);
        addBtn.addActionListener(this);

        this.setPreferredSize(new Dimension(600, 50));
        buildDictionaryFromDB();
    }

    private void buildDictionaryFromDB() {

        System.out.println("실행댐");

        // 2.연결
        try (Connection con = DriverManager.getConnection("jdbc:mariadb://" + server + "/" + database + "?useSSL=false", user_name, password)) {
            System.out.println("정상적으로 연결되었습니다.");
            String sql = "SELECT * FROM dict";
            PreparedStatement pstmt = con.prepareStatement(sql);
            // Insert, Update, Delete
            // executeUpdate()
            // select
            // executeQuery()
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String key = resultSet.getString("korean");
                String value = resultSet.getString("english");
                dict.put(key, value);
                System.out.printf("%s : %s \n", key, value);
                System.out.println(key + ":" + value);
            }
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Main dictPanel = new Main();
        frame.add(dictPanel);
        frame.setTitle("나의 한영사전");

        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String key = inputField.getText();
        if (key.trim().length() == 0) // 공백만 입력된 경우는 무시.
            return;

        if (e.getSource() == searchBtn) {
            /*
             * 입력된 단어를 추출
             * 그 단어를 key 값으로 가지는 대응되는 맵 엔트리가 있는지 검사 -> dict.get(단어);
             * 그 단어에 대응되는 값이 있으면 JOptionPane.showMessageDialog() 메서드를
             * 호출해서 그 대응되는 값을 보여준다.
             * 없으면 (null이면) JOptionPane.showMessageDialog() 메서드를 호출해서
             * '단어를 찾을 수 없습니다'라고 출력해준다.
             * inputField를 빈 문자열로 설정.
             */
            System.out.println("[" + key + "]");
            String value = dict.get(key);
            if (value != null) { // 대응되는 단어가 있는 경우
                JOptionPane.showMessageDialog(this, value, key,
                        JOptionPane.INFORMATION_MESSAGE);
            } else { // 대응되는 단어가 없는 경우
                JOptionPane.showMessageDialog(this, "단어를 찾을 수 없습니다", key,
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == addBtn) {
            /*
             * 입력된 단어를 추출
             * String value = JOptionPane.showInputDialog();
             *    메서드를 호출해서 추가할 영어 단어를 입력 받는다.
             *  dict.put(입력필드에 입력된 단어, inputDialog에 입력된 단어);
             *    inputField를 빈 문자열로 설정.
             */
            String value = JOptionPane.showInputDialog(this,
                    key + "에 대응되는 영어단어를 입력하세요");
            if (value.trim().length() == 0) return;
            dict.put(key, value);
            // 파일에도 key=value 의 쌍으로 기록해놓자.
            // DB에 <key, value>의 쌍을 하나의 레코드로 저장하자.
            addToDB(key, value);
            JOptionPane.showMessageDialog(this, "[" + value + "] 영어단어가 추가되었습니다",
                    key, JOptionPane.INFORMATION_MESSAGE);
        }
//      inputField.setText("");
    }

    private void addToDB(String key, String value) {
        try (Connection con = DriverManager.getConnection("jdbc:mariadb://" + server + "/" + database + "?useSSL=false", user_name, password)) {
            con.setAutoCommit(false);

            System.out.println("정상적으로 연결되었습니다.");
            String sql = "INSERT INTO dict VALUES (?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, key);
            pstmt.setString(2, value);
            // Insert, Update, Delete
            // executeUpdate()
            // select
            // executeQuery()
            int result = pstmt.executeUpdate();
            con.commit();

            dict.put(key, value);
            System.out.println(result);
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
            e.printStackTrace();
        }

    }
}
