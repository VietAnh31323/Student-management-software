package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.QLHVView;

public class LuaChon extends JFrame {
    public LuaChon() {
        setTitle("Giao Diện Chọn Lựa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(525, 297);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));

        JButton option1Button = new JButton("Thông Tin Học Viên");
        option1Button.setBackground(new Color(255, 255, 255));
        option1Button.setForeground(new Color(32, 178, 170));
        option1Button.setFont(new Font("Tahoma", Font.BOLD, 15));
        option1Button.setBounds(281, 86, 198, 54);

        JButton option2Button = new JButton("Lịch Học");
        option2Button.setBackground(new Color(255, 255, 255));
        option2Button.setForeground(new Color(32, 178, 170));
        option2Button.setFont(new Font("Tahoma", Font.BOLD, 15));
        option2Button.setBounds(20, 86, 198, 54);

        JButton option3Button = new JButton("Quản Lý Điểm");
        option3Button.setBackground(new Color(255, 255, 255));
        option3Button.setForeground(new Color(32, 178, 170));
        option3Button.setFont(new Font("Tahoma", Font.BOLD, 15));
        panel.add(option3Button);
        getContentPane().add(panel);

        setLocationRelativeTo(null);
        option3Button.setBounds(20, 182, 198, 54);

        JButton btnThoat = new JButton("Thoát");
        btnThoat.setForeground(new Color(32, 178, 170));
        btnThoat.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnThoat.setBackground(new Color(255, 255, 255));
        btnThoat.setBounds(281, 182, 198, 54);

        option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(LuaChon.this, "Bạn đã chọn Lựa Chọn Thông tin học viên");
                QLHVView qlhvView = new QLHVView();
                qlhvView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                qlhvView.setLocationRelativeTo(null);
                qlhvView.setVisible(true);
            }
        });

        option2Button.addActionListener(new ActionListener() {
            public void actionPerformed1(ActionEvent e) {
                JOptionPane.showMessageDialog(LuaChon.this, "Bạn đã chọn Lựa Chọn Lịch Học");
            }        
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(LuaChon.this, "Bạn đã chọn Lựa Chọn Lịch Học");
                QuanLyLichHoc quanLyLichHoc = new QuanLyLichHoc();
                quanLyLichHoc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                quanLyLichHoc.setLocationRelativeTo(null);
                quanLyLichHoc.setVisible(true);
            }
        });

        option3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(LuaChon.this, "Bạn đã chọn Lựa Chọn Thông tin Điểm");
                Diem diem = new Diem();
                diem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                diem.setLocationRelativeTo(null);
                diem.setVisible(true);
            }
        });
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(LuaChon.this,
                        "Bạn có chắc chắn muốn thoát khỏi chương trình?",
                        "Xác nhận thoát",
                        JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    Login loginForm = new Login();
                    loginForm.setVisible(true);
                    dispose();
                }
            }
        });

        panel.setLayout(null);
        panel.add(option1Button);
        panel.add(option2Button);
        panel.add(option3Button);
        panel.add(btnThoat);
        getContentPane().add(panel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(0, 139, 139));
        panel_1.setBounds(1, 0, 509, 60);
        panel.add(panel_1);
                panel_1.setLayout(null);
        
                JLabel lblNewLabel = new JLabel("MENU SYSTEM");
                lblNewLabel.setForeground(new Color(255, 255, 255));
                lblNewLabel.setBounds(175, 15, 163, 30);
                panel_1.add(lblNewLabel);
                lblNewLabel.setFont(new Font("Tayninh", Font.PLAIN, 20));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LuaChon luaChon = new LuaChon();
            luaChon.setVisible(true);
        });
    }
}
