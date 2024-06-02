package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class Diem extends JFrame {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private JTable jTable;
    private DefaultTableModel tableModel;
    private JTextField textFieldMSSV;
    private JTextField textFieldLop;
    private JTextField textFieldDiem1;
    private JTextField textFieldSearch;
	private int existingRow;
	private JTextField textFieldDiem2;
	private JTextField textFieldDiem3;
	private int diemKT1;
	private int diemKT2;
	private int diemKT3;

    public Diem() {
        setTitle("Quản Lý Điểm Học Viên");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 536);
        setLocationRelativeTo(null);

     // Khởi tạo tableModel
        tableModel = new DefaultTableModel(
        	    new Object[][] {},
        	    new String[] {"Mã Học Viên", "Lớp", "Điểm Test 1", "Điểm Test 2", "Điểm Test 3", "Điểm Trung Bình", "Đánh Giá"}
        	);

        // Khởi tạo jTable với tableModel
        jTable = new JTable(tableModel);
        jTable.setBackground(new Color(240, 255, 240));

        // Tạo JScrollPane và thêm jTable vào đó
        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setBounds(0, 55, 603, 335);
        jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = jTable.getSelectedRow();
                    if (selectedRow != -1) {
                        hienThiThongTin(selectedRow);
                    }
                }
            }
        });


        // Panel chứa các thành phần
        JPanel mainPanel = new JPanel();
        mainPanel.setForeground(new Color(255, 255, 255));
        mainPanel.setLayout(null);
        mainPanel.add(scrollPane);

        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(0, 388, 786, 111);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setLayout(null);

        JLabel label = new JLabel("Mã số sinh viên:");
        label.setForeground(new Color(255, 255, 255));
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        label.setBounds(10, 10, 143, 23);
        inputPanel.add(label);
        textFieldMSSV = new JTextField();
        textFieldMSSV.setBounds(163, 10, 191, 23);
        inputPanel.add(textFieldMSSV);

        JLabel lblimTest = new JLabel("Điểm Test 1:");
        lblimTest.setForeground(new Color(255, 255, 255));
        lblimTest.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblimTest.setBounds(10, 43, 143, 23);
        inputPanel.add(lblimTest);
        textFieldDiem1 = new JTextField();
        textFieldDiem1.setBounds(163, 43, 191, 23);
        inputPanel.add(textFieldDiem1);

        mainPanel.add(inputPanel);

        // Panel chứa các nút chức năng
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(602, 55, 184, 335);
        buttonPanel.setBackground(new Color(32, 178, 170));

        mainPanel.add(buttonPanel);
                        buttonPanel.setLayout(null);
                
                        JButton buttonSuaDiem = new JButton("Chỉnh Sửa ");
                        buttonSuaDiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
                        buttonSuaDiem.setBounds(18, 56, 159, 36);
                        buttonPanel.add(buttonSuaDiem);
                        buttonSuaDiem.addActionListener(e -> suaDiem(jTable));
                        buttonSuaDiem.setBackground(new Color(255, 255, 255));
                        buttonSuaDiem.setForeground(new Color(0, 139, 139));
                        buttonSuaDiem.setFocusPainted(false);
                
                        JButton buttonXoaDiem = new JButton("Xóa ");
                        buttonXoaDiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
                        buttonXoaDiem.setBounds(18, 10, 159, 36);
                        buttonPanel.add(buttonXoaDiem);
                        buttonXoaDiem.addActionListener(e -> xoaDiem(jTable));
                        buttonXoaDiem.setBackground(new Color(255, 255, 255));
                        buttonXoaDiem.setForeground(new Color(0, 139, 139));
                        buttonXoaDiem.setFocusPainted(false);
                
                        JButton buttonQuayLai = new JButton("Quay Lại");
                        buttonQuayLai.setFont(new Font("Tahoma", Font.PLAIN, 15));
                        buttonQuayLai.setBounds(18, 148, 159, 36);
                        buttonPanel.add(buttonQuayLai);
                        buttonQuayLai.addActionListener(e -> quayLai());
                        buttonQuayLai.setBackground(new Color(255, 255, 255));
                        buttonQuayLai.setForeground(new Color(0, 139, 139));
                        buttonQuayLai.setFocusPainted(false);
        
                JButton buttonThemDiem = new JButton("Thêm ");
                buttonThemDiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
                buttonThemDiem.setBounds(18, 102, 159, 36);
                buttonPanel.add(buttonThemDiem);
                buttonThemDiem.addActionListener(e -> themDiem());
                
                        // Thiết lập màu nền cho các nút
                        buttonThemDiem.setBackground(new Color(255, 255, 255));
                        
                                // Thiết lập màu chữ cho các nút
                                buttonThemDiem.setForeground(new Color(0, 139, 139));
                                
                                        // Thiết lập hiệu ứng cho các nút
                                        buttonThemDiem.setFocusPainted(false);
                                        
                                        textFieldSearch = new JTextField(15);
                                        textFieldSearch.setBounds(21, 226, 156, 26);
                                        buttonPanel.add(textFieldSearch);
                                        
                                        JLabel lblTmKim = new JLabel("Tìm Kiếm");
                                        lblTmKim.setForeground(new Color(255, 255, 255));
                                        lblTmKim.setBounds(64, 194, 76, 22);
                                        buttonPanel.add(lblTmKim);
                                        lblTmKim.setFont(new Font("Tahoma", Font.BOLD, 15));
                                        
                                        JButton btnHuyTimKiem = new JButton("Hủy");
                                        btnHuyTimKiem.setForeground(new Color(0, 139, 139));
                                        btnHuyTimKiem.setBounds(45, 299, 101, 26);
                                        buttonPanel.add(btnHuyTimKiem);
                                        btnHuyTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
                                        btnHuyTimKiem.setBackground(new Color(255, 255, 255));
                                        btnHuyTimKiem.addActionListener(e -> {
                                        	textFieldSearch.setText(""); // Xóa nội dung ô tìm kiếm
                                            jTable.setRowSorter(null); // Bỏ bộ lọc
                                        });
                                        
                                        JButton btnTimKiem = new JButton("Tìm Kiếm");
                                        btnTimKiem.setForeground(new Color(0, 139, 139));
                                        btnTimKiem.setBounds(45, 262, 101, 27);
                                        buttonPanel.add(btnTimKiem);
                                        btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
                                        btnTimKiem.setBackground(new Color(255, 255, 255));
                                        btnTimKiem.addActionListener(e -> {
                                            String searchText = textFieldSearch.getText();
                                            if (!searchText.isEmpty()) {
                                                // Tạo bộ lọc dựa trên mã số sinh viên
                                                RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter(searchText, 0);
                                                
                                                // Áp dụng bộ lọc vào TableRowSorter
                                                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
                                                sorter.setRowFilter(rowFilter);
                                                jTable.setRowSorter(sorter);
                                            } else {
                                                // Nếu ô tìm kiếm trống, hiển thị tất cả các dòng
                                                jTable.setRowSorter(null);
                                            }
                                        });
        // Thiết lập màu nền cho các thành phần
        mainPanel.setBackground(Color.WHITE);
        inputPanel.setBackground(new Color(0, 139, 139));

        // Thiết lập màu chữ cho các thành phần
        Font font = new Font("Arial", Font.PLAIN, 14);
        jTable.setFont(new Font("Arial", Font.PLAIN, 14));
        jTable.getTableHeader().setFont(font);
        textFieldMSSV.setFont(font);
        textFieldDiem1.setFont(font);
        textFieldLop = new JTextField();
        textFieldLop.setBounds(547, 10, 191, 23);  // Đặt vị trí và kích thước phù hợp
        inputPanel.add(textFieldLop);
        
        JLabel label_1_1 = new JLabel("Lớp:");
        label_1_1.setForeground(new Color(255, 255, 255));
        label_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        label_1_1.setBounds(394, 10, 87, 23);
        inputPanel.add(label_1_1);
        
        JLabel lblimTest_3 = new JLabel("Điểm Test 2:");
        lblimTest_3.setForeground(new Color(255, 255, 255));
        lblimTest_3.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblimTest_3.setBounds(394, 44, 143, 23);
        inputPanel.add(lblimTest_3);
        
        textFieldDiem2 = new JTextField();
        textFieldDiem2.setFont(new Font("Arial", Font.PLAIN, 14));
        textFieldDiem2.setBounds(547, 44, 191, 23);
        inputPanel.add(textFieldDiem2);
        
        JLabel lblimTest_1 = new JLabel("Điểm Test 3:");
        lblimTest_1.setForeground(new Color(255, 255, 255));
        lblimTest_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblimTest_1.setBounds(10, 77, 143, 23);
        inputPanel.add(lblimTest_1);
        
        textFieldDiem3 = new JTextField();
        textFieldDiem3.setFont(new Font("Arial", Font.PLAIN, 14));
        textFieldDiem3.setBounds(163, 77, 191, 23);
        inputPanel.add(textFieldDiem3);

        // Thêm mainPanel vào frame
        getContentPane().add(mainPanel);
        
        JLabel lblNewLabel = new JLabel("Thông tin Điểm Học Viên");
        lblNewLabel.setBounds(251, 14, 243, 31);
        lblNewLabel.setForeground(new Color(32, 178, 170));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mainPanel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("@DesignByNhom5");
        lblNewLabel_1.setForeground(new Color(32, 178, 170));
        lblNewLabel_1.setBounds(659, 32, 91, 13);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 10));
        mainPanel.add(lblNewLabel_1);
        
        JButton btnSave = new JButton("Save");
        btnSave.setBounds(33, 23, 85, 21);
        btnSave.setForeground(new Color(255, 255, 255));
        btnSave.setBackground(new Color(32, 178, 170));
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDataToFile();
            }
        });
        mainPanel.add(btnSave);

        // Hiển thị frame
        setVisible(true);
        mainPanel.add(btnSave);
        loadUser();
    }
    private void quayLai() {
    	LuaChon luaChon = new LuaChon();
        luaChon.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        luaChon.setVisible(true);
        this.dispose();

	}
	private void loadUser() {
        tableModel.setRowCount(0);

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String selectQuery = "SELECT * FROM bangdiem";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String maHocVien = resultSet.getString("maHocVien");
                        String lop = resultSet.getString("Lop");
                        double diemKT1 = resultSet.getDouble("diemKT1");
                        double diemKT2 = resultSet.getDouble("diemKT2");
                        double diemKT3 = resultSet.getDouble("diemKT3");
                        double diemTB = resultSet.getDouble("diemTB");
                        String danhGia = resultSet.getString("Danhgia");
                        Object[] rowData = {maHocVien, lop, diemKT1, diemKT2, diemKT3, diemTB, danhGia};
                        tableModel.addRow(rowData);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	private void themDiem() {
	    try {
	        String maHocVien = textFieldMSSV.getText();
	        double diemKT1 = Double.parseDouble(textFieldDiem1.getText());
	        double diemKT2 = Double.parseDouble(textFieldDiem2.getText());
	        double diemKT3 = Double.parseDouble(textFieldDiem3.getText());
	        String lop = textFieldLop.getText();
	        existingRow = -1; 
	        for (int i = 0; i < tableModel.getRowCount(); i++) {
	            if (tableModel.getValueAt(i, 0).equals(maHocVien)) {
	                existingRow = i;
	                break;
	            }
	        }

	        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
	            double diemTB = tinhDiemTrungBinh(diemKT1, diemKT2, diemKT3);
	            DecimalFormat df = new DecimalFormat("#.##");
	            diemTB = Double.parseDouble(df.format(diemTB));
	            String danhGia = (diemTB >= 5.0) ? "Đạt" : "Không Đạt";

	            if (existingRow == -1) {
	                String insertQuery = "INSERT INTO bangdiem (maHocVien, Lop, diemKT1 ,diemKT2 ,diemKT3 , diemTB, Danhgia) VALUES (?,?,?,?,?,?,?)";
	                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
	                    preparedStatement.setString(1, maHocVien);
	                    preparedStatement.setString(2, lop);
	                    preparedStatement.setDouble(3, diemKT1);
	                    preparedStatement.setDouble(4, diemKT2);
	                    preparedStatement.setDouble(5, diemKT3);
	                    preparedStatement.setDouble(6, diemTB);
	                    preparedStatement.setString(7, danhGia);
	                    preparedStatement.executeUpdate();

	                    // Thêm một hàng mới vào tableModel
	                    Object[] rowData = {maHocVien, lop, diemKT1, diemKT2, diemKT3, diemTB, danhGia};
	                    tableModel.addRow(rowData);
	                }
	            } else {
	                String updateQuery = "UPDATE bangdiem SET diemKT1 = ?,diemKT2 = ?,diemKT3 = ?, diemTB = ?, Danhgia = ? WHERE maHocVien = ?";
	                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
	                    preparedStatement.setDouble(1, diemKT1);
	                    preparedStatement.setDouble(2, diemKT2);
	                    preparedStatement.setDouble(3, diemKT3);
	                    preparedStatement.setDouble(4, diemTB);
	                    preparedStatement.setString(5, danhGia);
	                    preparedStatement.setString(6, maHocVien);
	                    preparedStatement.executeUpdate();
	                    tableModel.setValueAt(lop, existingRow, 1);
	                    tableModel.setValueAt(diemKT1, existingRow, 2);
	                    tableModel.setValueAt(diemKT2, existingRow, 3);
	                    tableModel.setValueAt(diemKT3, existingRow, 4);
	                    tableModel.setValueAt(diemTB, existingRow, 5);
	                    tableModel.setValueAt(danhGia, existingRow, 6);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        textFieldMSSV.setText("");
	        textFieldDiem1.setText("");
	        textFieldDiem2.setText("");
	        textFieldDiem3.setText("");
	        textFieldLop.setText("");
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập điểm là một số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}

	private void suaDiem(JTable table) {
	    int selectedRow = table.getSelectedRow();

	    if (selectedRow != -1) {
	        try {
	            String mssvToUpdate = (String) tableModel.getValueAt(selectedRow, 0);
	            double diemMoi1 = Double.parseDouble(textFieldDiem1.getText());
	            double diemMoi2 = Double.parseDouble(textFieldDiem2.getText());
	            double diemMoi3 = Double.parseDouble(textFieldDiem3.getText());
	            boolean updatedInDatabase = capNhatDuLieuTrongCSDL(mssvToUpdate, diemMoi1, diemMoi2, diemMoi3);

	            if (updatedInDatabase) {
	                double diemTB = tinhDiemTrungBinh(diemMoi1, diemMoi2, diemMoi3);
	                String danhGia = (diemTB >= 5.0) ? "Đạt" : "Không Đạt";
	                DecimalFormat df = new DecimalFormat("#.##");
	                diemTB = Double.parseDouble(df.format(diemTB));

	                tableModel.setValueAt(mssvToUpdate, selectedRow, 0);
	                tableModel.setValueAt(textFieldLop.getText(), selectedRow, 1);
	                tableModel.setValueAt(diemMoi1, selectedRow, 2);
	                tableModel.setValueAt(diemMoi2, selectedRow, 3);
	                tableModel.setValueAt(diemMoi3, selectedRow, 4);
	                tableModel.setValueAt(diemTB, selectedRow, 5); // Cập nhật điểm trung bình
	                tableModel.setValueAt(danhGia, selectedRow, 6);

	                JOptionPane.showMessageDialog(this, "Đã sửa điểm thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	                hienThiThongTin(selectedRow);
	            } else {
	                JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            }

	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(this, "Vui lòng nhập điểm là một số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để sửa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}
    private void hienThiThongTin(int selectedRow) {
        if (selectedRow != -1) {
            String maHocVien = (String) tableModel.getValueAt(selectedRow, 0);
            String lop = (String) tableModel.getValueAt(selectedRow, 1);
            double diemKT1 = (double) tableModel.getValueAt(selectedRow, 2);
            double diemKT2 = (double) tableModel.getValueAt(selectedRow, 3);
            double diemKT3 = (double) tableModel.getValueAt(selectedRow, 4);

            textFieldMSSV.setText(maHocVien);
            textFieldLop.setText(lop);
            textFieldDiem1.setText(String.valueOf(diemKT1));
            textFieldDiem2.setText(String.valueOf(diemKT2));
            textFieldDiem3.setText(String.valueOf(diemKT3));
        }
		
	}
    private boolean capNhatDuLieuTrongCSDL(String mssvToUpdate, double diemMoi1, double diemMoi2, double diemMoi3) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String updateQuery = "UPDATE bangdiem SET diemKT1 = ?, diemKT2 = ?, diemKT3 = ?, diemTB = ?, Danhgia = ? WHERE maHocVien = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setDouble(1, diemMoi1);
                preparedStatement.setDouble(2, diemMoi2);
                preparedStatement.setDouble(3, diemMoi3);
                double diemTB = tinhDiemTrungBinh(Arrays.asList(diemMoi1, diemMoi2, diemMoi3));
                DecimalFormat df = new DecimalFormat("#.##");
                diemTB = Double.parseDouble(df.format(diemTB));
                preparedStatement.setDouble(4, diemTB);
                String danhGia = (diemTB >= 5.0) ? "Đạt" : "Không Đạt";
                preparedStatement.setString(5, danhGia);
                preparedStatement.setString(6, mssvToUpdate);
                int affectedRows = preparedStatement.executeUpdate();
                return affectedRows > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private double tinhDiemTrungBinh(List<Double> diemList) {
        double sum = 0.0;
        for (Double diem : diemList) {
            sum += diem;
        }
        return Math.round((sum / diemList.size()) * 100.0) / 100.0;
    }
    private void xoaDiem(JTable table) {
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {
            String mssvToDelete = (String) tableModel.getValueAt(selectedRow, 0);

            boolean deletedFromDatabase = xoaDuLieuTuCSDL(mssvToDelete);

            if (deletedFromDatabase) {
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Đã xóa điểm thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa từ cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean xoaDuLieuTuCSDL(String mssvToDelete) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String deleteQuery = "DELETE FROM bangdiem WHERE maHocVien = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, mssvToDelete);
                int affectedRows = preparedStatement.executeUpdate();

                return affectedRows > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private double tinhDiemTrungBinh(double diemKT1, double diemKT2, double diemKT3) {
        return (diemKT1 + diemKT2 + diemKT3) / 3;
    }
    private void saveDataToFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    writer.write(tableModel.getColumnName(i));
                    if (i < tableModel.getColumnCount() - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();

                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                        writer.write(tableModel.getValueAt(i, j).toString());
                        if (j < tableModel.getColumnCount() - 1) {
                            writer.write(",");
                        }
                    }
                    writer.newLine();
                }

                writer.close();
                JOptionPane.showMessageDialog(this, "Xuất file thành công!");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi xuất file!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Diem();
            }
        });
    }
}

