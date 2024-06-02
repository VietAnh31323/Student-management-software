package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DangKy extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    private Login loginFrame;

    public DangKy(Login loginFrame) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Đăng Kí tài khoản");
        setSize(486, 295);
        setLocationRelativeTo(loginFrame);
        setResizable(false);

        this.loginFrame = loginFrame;

        JPanel panel = new JPanel();

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUsername.setBounds(50, 91, 118, 42);
        usernameField = new JTextField();
        usernameField.setBounds(205, 91, 236, 42);
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(50, 154, 118, 42);
        passwordField = new JPasswordField();
        passwordField.setBounds(205, 154, 236, 42);

        JButton btnRegister = new JButton("Đăng Ký");
        btnRegister.setForeground(new Color(255, 255, 255));
        btnRegister.setBackground(new Color(32, 178, 170));
        btnRegister.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnRegister.setBounds(183, 206, 124, 42);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (registerUser(username, password)) {
                    JOptionPane.showMessageDialog(DangKy.this, "Tài khoản đăng kí thành công");
                    loginFrame.setUsername(username);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(DangKy.this, "Lỗi đăng kí tài khoản", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.setLayout(null);

        panel.add(lblUsername);
        panel.add(usernameField);
        panel.add(lblPassword);
        panel.add(passwordField);
        panel.add(btnRegister);

        getContentPane().add(panel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(0, 139, 139));
        panel_1.setBounds(0, 0, 472, 77);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("ĐĂNG KÍ TÀI KHOẢN");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setBounds(126, 10, 227, 62);
        panel_1.add(lblNewLabel);
    }

    private boolean registerUser(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "INSERT INTO user (username, password) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                int affectedRows = preparedStatement.executeUpdate();

                return affectedRows > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Không thể đăng kí người dùng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    DangKy frame = new DangKy(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
