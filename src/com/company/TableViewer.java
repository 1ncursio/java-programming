package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class TableViewer extends JFrame implements ActionListener {
    private JTextField idField, titleField, publisherField, yearField, priceField;
    private JButton previousBtn, nextBtn, insertBtn, finishBtn;
    private Connection con;
    private ResultSet rs;

    public TableViewer() {
        this.setLayout(new GridLayout(0, 2));
        this.add(new JLabel("ID", JLabel.CENTER));
        idField = new JTextField(10);
        this.add(idField);

        this.add(new JLabel("Title", JLabel.CENTER));
        titleField = new JTextField(10);
        this.add(titleField);

        this.add(new JLabel("Publisher", JLabel.CENTER));
        publisherField = new JTextField(10);
        this.add(publisherField);

        this.add(new JLabel("Year", JLabel.CENTER));
        yearField = new JTextField(10);
        this.add(yearField);

        this.add(new JLabel("Price", JLabel.CENTER));
        priceField = new JTextField(10);
        this.add(priceField);

        previousBtn = new JButton("Previous");
        previousBtn.addActionListener(this);
        this.add(previousBtn);

        nextBtn = new JButton("Next");
        nextBtn.addActionListener(this);
        this.add(nextBtn);

        insertBtn = new JButton("Insert");
        insertBtn.addActionListener(this);
        this.add(insertBtn);

        finishBtn = new JButton("Finish");
        finishBtn.addActionListener(this);
        this.add(finishBtn);

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/scpark?useSSL=false", "root", "nodejsbook");
            String sql = "SELECT * FROM books ORDER BY book_id DESC";
            PreparedStatement pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();
            changeField();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("DB 연결 문제있음");
            e.printStackTrace();
            System.exit(0);
        }

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setSize(350, 200);
        this.setTitle("테이블 뷰어");
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TableViewer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == nextBtn) {
                System.out.println("다음 버튼");
                rs.next();
            } else if (e.getSource() == previousBtn) {
                System.out.println("이전 버튼");
                rs.previous();
            } else if (e.getSource() == insertBtn) {
                String sql = "INSERT INTO books (title, publisher, year, price) VALUES(?, ?, ?, ?)";
                /* Statement 객체는 PreparedStatement보다
                * SQL Injection에 취약하므로
                * 가급적 PreparedStatement를 사용할 것 */
                PreparedStatement pstmt = con.prepareStatement(sql);

                String title = titleField.getText();
                String publisher = publisherField.getText();
                String year = yearField.getText();
                int price = Integer.parseInt(priceField.getText());

                pstmt.setString(1, title);
                pstmt.setString(2, publisher);
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
//                Date date = new Date(sdf.parse(year).getTime());

                pstmt.setDate(3, Date.valueOf(year));
                pstmt.setInt(4, price);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "등록 성공!");
                reloading();

                rs.next();
                changeField();
            } else if (e.getSource() == finishBtn) {
                con.close();
                this.dispose();
                System.exit(0);
            }

            changeField();
        } catch (SQLDataException sqlDataException) {
            if (sqlDataException.getMessage() == "Current position is before the first row" || sqlDataException.getMessage() == "Current position is after the last row") {
                idField.setText("");
                titleField.setText("");
                publisherField.setText("");
                yearField.setText("");
                priceField.setText("");
            } else {
                System.out.println(sqlDataException.getMessage());
            }
        } catch (Exception err) {
            err.printStackTrace();
            System.out.println(err.getMessage());
        }

    }

    private void reloading() throws SQLException {
        String sql = "SELECT * FROM books ORDER BY book_id DESC";
        PreparedStatement pstmt = con.prepareStatement(sql);
        rs.close();
        rs = pstmt.executeQuery();
    }

    private void changeField() throws SQLException {
        int bookId = rs.getInt("book_id");
        String title = rs.getString("title");
        String publisher = rs.getString("publisher");
        Date year = rs.getDate("year");
        int price = rs.getInt("price");

        idField.setText(String.valueOf(bookId));
        titleField.setText(title);
        publisherField.setText(publisher);
        yearField.setText(year.toString());
        priceField.setText(String.valueOf(price));
    }
}
