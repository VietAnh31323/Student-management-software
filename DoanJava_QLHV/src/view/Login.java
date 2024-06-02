package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb";
    static final String USERNAME = "root";
    static final String PASSWORD = "";

    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Đăng nhập");
        setSize(533, 330);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        JLabel lblUsername = new JLabel("Tài Khoản: ");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUsername.setBounds(66, 93, 118, 48);
        usernameField = new JTextField();
        usernameField.setBounds(213, 98, 216, 36);
        JLabel lblPassword = new JLabel("Mật Khẩu:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(66, 151, 118, 57);
        passwordField = new JPasswordField();
        passwordField.setBounds(213, 160, 216, 36);

        JButton btnLogin = new JButton("Đăng Nhập");
        btnLogin.setBackground(new Color(32, 178, 170));
        btnLogin.setForeground(new Color(255, 255, 255));
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnLogin.setBounds(11, 239, 158, 48);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (loginUser(username, password)) {
                    openLuaChonFrame(); // Mở frame lựa chọn nếu đăng nhập thành công
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Sai tài khoản hoặc mật khẩu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnRegister = new JButton("Đăng ký");
        btnRegister.setBackground(new Color(32, 178, 170));
        btnRegister.setForeground(new Color(255, 255, 255));
        btnRegister.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnRegister.setBounds(190, 239, 153, 48);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DangKy registerFrame = new DangKy(Login.this);
                registerFrame.setVisible(true);
            }
        });
        panel.setLayout(null);

        panel.add(lblUsername);
        panel.add(usernameField);
        panel.add(lblPassword);
        panel.add(passwordField);
        panel.add(btnLogin);
        panel.add(btnRegister);

        getContentPane().add(panel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(0, 139, 139));
        panel_1.setBounds(0, 0, 516, 57);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("LOGIN SYSTEM");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(182, 14, 166, 30);
        panel_1.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Tayninh", Font.PLAIN, 20));
        JButton btnChangePassword = new JButton("Đổi Mật Khẩu");
        btnChangePassword.setBackground(new Color(32, 178, 170));
        btnChangePassword.setForeground(new Color(255, 255, 255));
        btnChangePassword.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnChangePassword.setBounds(357, 239, 153, 48);
        btnChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DoiMK thayDoiMatKhauFrame = new DoiMK(tenTaiKhoan());
                thayDoiMatKhauFrame.setVisible(true);
            }
			private String tenTaiKhoan() {
				return usernameField.getText();
			}
        });
        panel.add(btnChangePassword);
    }

    private boolean loginUser(String username, String password) {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Không thể đăng nhập: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    private void openLuaChonFrame() {
        LuaChon luaChonFrame = new LuaChon();
        luaChonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        luaChonFrame.setVisible(true);
        this.setVisible(false); 
    }
    public void setUsername(String username) {
        usernameField.setText(username);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


