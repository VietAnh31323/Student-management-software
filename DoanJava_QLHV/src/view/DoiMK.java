package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import view.Login;

public class DoiMK extends JFrame {
    private JPasswordField currentPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    private String loggedInUsername;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    public DoiMK(String loggedInUsername) {
        this.loggedInUsername = loggedInUsername;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Thay Đổi Mật Khẩu");
        setSize(527, 424);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();

        JLabel lblCurrentPassword = new JLabel("Mật Khẩu Hiện Tại:");
        lblCurrentPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblCurrentPassword.setBounds(63, 138, 193, 28);
        currentPasswordField = new JPasswordField();
        currentPasswordField.setBounds(256, 138, 193, 28);
        JLabel lblNewPassword = new JLabel("Mật Khẩu Mới:");
        lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewPassword.setBounds(63, 191, 193, 28);
        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(256, 191, 193, 28);
        JLabel lblConfirmPassword = new JLabel("Xác Nhận Mật Khẩu Mới:");
        lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblConfirmPassword.setBounds(63, 244, 193, 28);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(256, 244, 193, 28);
        panel.setLayout(null);

        panel.add(lblCurrentPassword);
        panel.add(currentPasswordField);
        panel.add(lblNewPassword);
        panel.add(newPasswordField);
        panel.add(lblConfirmPassword);
        panel.add(confirmPasswordField);
        getContentPane().add(panel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(0, 139, 139));
        panel_1.setBounds(0, 0, 513, 94);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("THAY ĐỔI MẬT KHẨU");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setBounds(148, 19, 242, 52);
        panel_1.add(lblNewLabel);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setBounds(0, 321, 513, 66);
        panel.add(panel_2);
                panel_2.setLayout(null);        
                JButton btnSave = new JButton("Lưu");
                btnSave.setBounds(204, 11, 103, 39);
                panel_2.add(btnSave);
                btnSave.setBackground(new Color(32, 178, 170));
                btnSave.setForeground(new Color(255, 255, 255));
                btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
                btnSave.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String currentPassword = new String(currentPasswordField.getPassword());
                        String newPassword = new String(newPasswordField.getPassword());
                        String confirmPassword = new String(confirmPasswordField.getPassword());

                        if (currentPassword.equals(getCurrentPasswordFromDatabase()) &&
                            newPassword.equals(confirmPassword)) {
                            saveNewPassword(newPassword);
                            JOptionPane.showMessageDialog(DoiMK.this, "Đã thay đổi mật khẩu thành công!");
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(DoiMK.this, "Mật khẩu hiện tại không đúng hoặc mật khẩu xác nhận không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
    }

    private String getCurrentPasswordFromDatabase() {
        String query = "SELECT password FROM user WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(Login.JDBC_URL, Login.USERNAME, Login.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, loggedInUsername);

            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy lại mật khẩu hiện tại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    private void saveNewPassword(String newPassword) {
        String query = "UPDATE user SET password = ? WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(Login.JDBC_URL, Login.USERNAME, Login.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, loggedInUsername);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu mật khẩu mới: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
