package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuanLyLichHoc extends JFrame {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
    private DefaultTableModel tableModel;
    private JTable jTable;
    private JTextField textField;
    public QuanLyLichHoc() {
    	getContentPane().setBackground(new Color(0, 139, 139));
        setTitle("Quản Lý Lịch Học");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        jTable = new JTable(tableModel);
        tableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] { "ID", "Môn Học", "Tên Giảng Viên", "Thời Gian", "Địa Diểm", "Lớp" }
            );
            getContentPane().setLayout(null);
        jTable.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setBounds(0, 100, 786, 332);
        getContentPane().add(scrollPane);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 432, 786, 131);
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.setLayout(null);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        JButton btnXuat = new JButton("Save");
        btnXuat.setForeground(new Color(255, 255, 255));
        btnXuat.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnXuat.setBackground(new Color(0, 128, 128));
        btnXuat.setBounds(26, 17, 80, 100);
        buttonPanel.add(btnXuat);
        btnXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDataToFile();
            }
        });
        
        JLabel label = new JLabel("Tìm Kiếm:");
        label.setForeground(new Color(32, 178, 170));
        label.setBackground(new Color(32, 178, 170));
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        label.setBounds(131, 18, 120, 30);
        buttonPanel.add(label);
        
        textField = new JTextField(15);
        textField.setBounds(250, 19, 100, 30);
        buttonPanel.add(textField);
        
        JButton btnTimKiem = new JButton("Tìm Kiếm");
        btnTimKiem.setForeground(new Color(255, 255, 255));
        btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnTimKiem.setBackground(new Color(0, 128, 128));
        btnTimKiem.setBounds(373, 18, 120, 30);
        buttonPanel.add(btnTimKiem);
        btnTimKiem.addActionListener(e -> TimKiem());
        
        JButton btnHuyTimKiem = new JButton("Hủy");
        btnHuyTimKiem.setForeground(new Color(255, 255, 255));
        btnHuyTimKiem.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnHuyTimKiem.setBackground(new Color(0, 128, 128));
        btnHuyTimKiem.setBounds(521, 18, 95, 30);
        buttonPanel.add(btnHuyTimKiem);
        btnHuyTimKiem.addActionListener(e -> Huy());
        
        JButton btnThem = new JButton("Thêm");
        btnThem.setForeground(new Color(255, 255, 255));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    themLichHoc(); 
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnThem.setBackground(new Color(0, 128, 128));
        btnThem.setBounds(190, 70, 80, 30);
        buttonPanel.add(btnThem);
        
        JButton btnSua = new JButton("Sửa");
        btnSua.setForeground(new Color(255, 255, 255));
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnSua.setBackground(new Color(0, 128, 128));
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jTable.getSelectedRow();
                if (selectedRow != -1) {
                    String id = (String) jTable.getValueAt(selectedRow, 0);
                    String tenMonHoc = (String) jTable.getValueAt(selectedRow, 1);
                    String tenGiangVien = (String) jTable.getValueAt(selectedRow, 2);
                    String thoiGian = (String) jTable.getValueAt(selectedRow, 3);
                    String diaDiem = (String) jTable.getValueAt(selectedRow, 4);
                    String Lop = (String) jTable.getValueAt(selectedRow, 5);
                    Sua(id, tenMonHoc, tenGiangVien, thoiGian, diaDiem, Lop);
                } else {
                    JOptionPane.showMessageDialog(QuanLyLichHoc.this, "Vui lòng chọn một dòng để sửa.");
                }
            }
        });
        btnSua.setBounds(339, 70, 80, 30);
        buttonPanel.add(btnSua);
        
        JButton btnQuayLai = new JButton("Quay lại");
        btnQuayLai.setForeground(new Color(255, 255, 255));
        btnQuayLai.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnQuayLai.setBackground(new Color(0, 128, 128));
        btnQuayLai.setBounds(658, 17, 120, 100);
        buttonPanel.add(btnQuayLai);
        
        JButton btnXoa = new JButton("Xóa");
        btnXoa.setForeground(Color.WHITE);
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnXoa.setBackground(new Color(0, 128, 128));
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jTable.getSelectedRow();
                if (selectedRow != -1) {
                    String id = (String) jTable.getValueAt(selectedRow, 0);
                    int option = JOptionPane.showConfirmDialog(QuanLyLichHoc.this,
                            "Bạn có chắc chắn muốn xóa?",
                            "Xác nhận xóa",
                            JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        tableModel.removeRow(selectedRow);
                        xoaLichHocDB(id);
                    }
                } else {
                    JOptionPane.showMessageDialog(QuanLyLichHoc.this, "Vui lòng chọn một dòng để xóa.");
                }
            }
        });
        btnXoa.setBounds(495, 71, 80, 30);
        buttonPanel.add(btnXoa);
        btnQuayLai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LuaChon luaChonFrame = new LuaChon();
                luaChonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                luaChonFrame.setVisible(true);
            }
        });
        
        JLabel lblNewLabel = new JLabel("THÔNG TIN LỊCH HỌC ");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(290, 10, 500, 50);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("@DesignByNhom5");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(333, 59, 150, 13);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 15));
        getContentPane().add(lblNewLabel_1);
        loadUser();
    }
	private void Huy() {
	       try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
	            String selectQuery = "SELECT * FROM lichhoc";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
	                 ResultSet resultSet = preparedStatement.executeQuery()) {

	                displaySearchResult(resultSet);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	private void TimKiem() {
		 String searchKeyword = textField.getText().trim();

	        if (!searchKeyword.isEmpty()) {
	            try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
	                String searchQuery = "SELECT * FROM lichhoc WHERE tenmonhoc LIKE ?";
	                try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
	                    preparedStatement.setString(1, "%" + searchKeyword + "%");

	                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                        displaySearchResult(resultSet);
	                    }
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	}
	private void displaySearchResult(ResultSet resultSet) {
		DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0);

        try {
			while (resultSet.next()) {
			    Vector<String> rowData = new Vector<>();
			    rowData.add(resultSet.getString("id"));
			    rowData.add(resultSet.getString("tenmonhoc"));
			    rowData.add(resultSet.getString("tengiangvien"));
			    rowData.add(resultSet.getString("thoigian"));
			    rowData.add(resultSet.getString("diadiem"));
			    rowData.add(resultSet.getString("Lop"));

			    model.addRow(rowData);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void loadUser() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String selectQuery = "SELECT * FROM lichhoc";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                tableModel.setRowCount(0);
                while (resultSet.next()) {
                    Vector<String> rowData = new Vector<>();
                    rowData.add(resultSet.getString("id"));
                    rowData.add(resultSet.getString("tenmonhoc"));
                    rowData.add(resultSet.getString("tengiangvien"));
                    rowData.add(resultSet.getString("thoigian"));
                    rowData.add(resultSet.getString("diadiem"));
                    rowData.add(resultSet.getString("Lop"));
                    tableModel.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void themLichHoc() throws Exception{
    	JTextField tfID = new JTextField();
        JTextField tfTenMonHoc = new JTextField();
        JTextField tfTenGiangVien = new JTextField();
        JTextField tfThoiGian = new JTextField();
        JTextField tfDiaDiem = new JTextField();
        JTextField tfThongTinLopHoc = new JTextField();

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(10, 5));
        myPanel.add(new JLabel("ID:"));
        myPanel.add(tfID);
        myPanel.add(new JLabel("Tên Môn Học:"));
        myPanel.add(tfTenMonHoc);
        myPanel.add(new JLabel("Tên Giảng Viên:"));
        myPanel.add(tfTenGiangVien);
        myPanel.add(new JLabel("Thời Gian:"));
        myPanel.add(tfThoiGian);
        myPanel.add(new JLabel("Địa Điểm:"));
        myPanel.add(tfDiaDiem);
        myPanel.add(new JLabel("Lớp Học:"));
        myPanel.add(tfThongTinLopHoc);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Nhập thông tin lịch học", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
        	String id = tfID.getText();
            String tenMonHoc = tfTenMonHoc.getText();
            String tenGiangVien = tfTenGiangVien.getText();
            String thoiGian = tfThoiGian.getText();
            String diaDiem = tfDiaDiem.getText();
            String Lop = tfThongTinLopHoc.getText();

            if (!id.isEmpty() &&!tenMonHoc.isEmpty() && !tenGiangVien.isEmpty() && !thoiGian.isEmpty() && !diaDiem.isEmpty() && !Lop.isEmpty()) {
                Vector<String> rowData = new Vector<>();
                rowData.add(id);
                rowData.add(tenMonHoc);
                rowData.add(tenGiangVien);
                rowData.add(thoiGian);
                rowData.add(diaDiem);
                rowData.add(Lop);
                tableModel.addRow(rowData);
                try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                	String insertQuery = "INSERT INTO lichhoc (id, tenmonhoc, tengiangvien, thoigian, diadiem, Lop) VALUES (?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
						preparedStatement.setString(1, id);
                        preparedStatement.setString(2, tenMonHoc);
                        preparedStatement.setString(3, tenGiangVien);
                        preparedStatement.setString(4, thoiGian);
                        preparedStatement.setString(5, diaDiem);
                        preparedStatement.setString(6, Lop);
                        preparedStatement.executeUpdate();
                    }
                } catch (SQLException e) {
                    e.printStackTrace(); 
                }

            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin lịch học.");
            }
        }
        loadUser();
    }
    private void Sua(String id, String tenMonHoc, String tenGiangVien, String thoiGian, String diaDiem, String Lop) {
        JTextField tfTenMonHoc = new JTextField(tenMonHoc);
        JTextField tfTenGiangVien = new JTextField(tenGiangVien);
        JTextField tfThoiGian = new JTextField(thoiGian);
        JTextField tfDiaDiem = new JTextField(diaDiem);
        JTextField tfThongTinLopHoc = new JTextField(Lop);
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(10, 5));
        myPanel.add(new JLabel("Tên Môn Học:"));
        myPanel.add(tfTenMonHoc);
        myPanel.add(new JLabel("Tên Giảng Viên:"));
        myPanel.add(tfTenGiangVien);
        myPanel.add(new JLabel("Thời Gian:"));
        myPanel.add(tfThoiGian);
        myPanel.add(new JLabel("Địa Điểm:"));
        myPanel.add(tfDiaDiem);
        myPanel.add(new JLabel("Lớp Học:"));
        myPanel.add(tfThongTinLopHoc);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Chỉnh sửa thông tin lịch học", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String newTenMonHoc = tfTenMonHoc.getText();
            String newTenGiangVien = tfTenGiangVien.getText();
            String newThoiGian = tfThoiGian.getText();
            String newDiaDiem = tfDiaDiem.getText();
            String newLop = tfThongTinLopHoc.getText();
            suaLichHocDB(id, newTenMonHoc, newTenGiangVien, newThoiGian, newDiaDiem, newLop);
        }
    }
    private void suaLichHocDB(String id, String tenMonHoc, String tenGiangVien, String thoiGian, String diaDiem, String Lop) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            try {
                connection.setAutoCommit(false);
                String updateQuery = "UPDATE lichhoc SET tenmonhoc=?, tengiangvien=?, thoigian=?, diadiem=?, Lop=? WHERE id=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, tenMonHoc);
                    preparedStatement.setString(2, tenGiangVien);
                    preparedStatement.setString(3, thoiGian);
                    preparedStatement.setString(4, diaDiem);
                    preparedStatement.setString(5, Lop);
                    preparedStatement.setString(6, id);

                    int rowsUpdated = preparedStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Update successful!");
                        connection.commit();
                    } else {
                        System.out.println("Update failed. No rows were updated.");
                    }
                }
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadUser();
    }
    private void xoaLichHocDB(String id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String deleteQuery = "DELETE FROM lichhoc WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, id);

                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Delete successful!");
                } else {
                    System.out.println("Delete failed. No rows were deleted.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        SwingUtilities.invokeLater(() -> {
            QuanLyLichHoc quanLyLichHoc = new QuanLyLichHoc();
            quanLyLichHoc.setVisible(true);
        });
    }
}
