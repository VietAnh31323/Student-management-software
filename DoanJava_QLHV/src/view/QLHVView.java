package view;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.QLHVController;
import model.HocVan;
import model.HocVien;
import model.QLHVModel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.SimpleFormatter;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.JDatePicker;
import java.awt.Color;



public class QLHVView<Action> extends JFrame {

	public JPanel contentPane;
	public QLHVModel model;
	public JTextField textFieldMHV;
	public JTextField textFieldMKHtt;
	public JTextField textFieldMHVtt;
	public JTextField textFieldName;
	public JTextField textFieldAddress;
	public JTextField textFieldPhone;
	public JTextField textFieldMail;
	public JTextField textFieldClass;
	public JTextField textFieldTest1;
	public JTable table;
	public JComboBox comboBoxTrinhDo;
	public JComboBox comboBoxTrinhDott;
	public JRadioButton rdbtnNewRadioButton_Nam;
	public JRadioButton rdbtnNewRadioButton_Nu;
	public ButtonGroup btn_gioiTinh;
	public JDatePicker datePicker;
	
	private static String DB_URL = "jdbc:mysql://localhost:3306/testdb?zeroDateTimeBehavior=convertToNull";
	private static String USER_NAME = "root";
	private static String PASSWORD = "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLHVView frame = new QLHVView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QLHVView() {		
		this.model = new QLHVModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 877, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		
		ActionListener  action = new QLHVController(this);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 845, 22);
		contentPane.add(menuBar);
		
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		JRadioButtonMenuItem menuOpen = new JRadioButtonMenuItem("Open");
		menuOpen.addActionListener(action);
		menuFile.add(menuOpen);
		
		JRadioButtonMenuItem menuSave = new JRadioButtonMenuItem("Save");
		menuSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDataToFile();
            }
        });
		menuFile.add(menuSave);
		
		JSeparator separator = new JSeparator();
		menuFile.add(separator);
		
		JRadioButtonMenuItem menuExit = new JRadioButtonMenuItem("Exit");
		menuExit.addActionListener(action);
		menuFile.add(menuExit);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 139, 139));
		separator_1.setBounds(0, 75, 855, 13);
		contentPane.add(separator_1);
		
		JLabel Label = new JLabel("NHẬP THÔNG TIN HỌC VIÊN");
		Label.setBounds(282, 78, 283, 47);
		Label.setForeground(new Color(32, 178, 170));
		Label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(Label);
		
		JLabel lblMHocVien = new JLabel("Mã Học Viên");
		lblMHocVien.setBounds(53, 32, 87, 24);
		lblMHocVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblMHocVien);
		
		textFieldMHV = new JTextField();
		textFieldMHV.setBounds(150, 32, 112, 24);
		textFieldMHV.setColumns(10);
		contentPane.add(textFieldMHV);
		
		JLabel lblTrinhDo = new JLabel("Trình Độ");
		lblTrinhDo.setBounds(305, 32, 87, 24);
		lblTrinhDo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblTrinhDo);
		
		comboBoxTrinhDo = new JComboBox();
		comboBoxTrinhDo.setBounds(384, 32, 130, 22);
		comboBoxTrinhDo.setForeground(new Color(32, 178, 170));
		comboBoxTrinhDo.setBackground(new Color(255, 255, 255));
		ArrayList<HocVan> listHocVan = HocVan.getDSHocVan();
		for (HocVan hocvan: listHocVan) {
			comboBoxTrinhDo.addItem(hocvan.getTenHocVan());
		}
		contentPane.add(comboBoxTrinhDo);
		
		JButton ButtonTìmKiem = new JButton("Tìm Kiếm");
		ButtonTìmKiem.setBounds(572, 32, 130, 25);
		ButtonTìmKiem.setBackground(new Color(32, 178, 170));
		ButtonTìmKiem.setForeground(new Color(255, 255, 255));
		ButtonTìmKiem.addActionListener(action);
		ButtonTìmKiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(ButtonTìmKiem);
		
		JLabel LabelMKHtt = new JLabel("Mã Khóa học");
		LabelMKHtt.setBounds(10, 133, 87, 24);
		LabelMKHtt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(LabelMKHtt);
		
		textFieldMKHtt = new JTextField();
		textFieldMKHtt.setBounds(120, 133, 189, 24);
		textFieldMKHtt.setColumns(10);
		contentPane.add(textFieldMKHtt);
		
		JLabel LabelMHVtt = new JLabel("Mã Học Viên");
		LabelMHVtt.setBounds(10, 167, 87, 24);
		LabelMHVtt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(LabelMHVtt);
		
		textFieldMHVtt = new JTextField();
		textFieldMHVtt.setBounds(120, 167, 189, 24);
		textFieldMHVtt.setColumns(10);
		contentPane.add(textFieldMHVtt);
		
		JLabel LabelMKH_1_2 = new JLabel("Họ và Tên");
		LabelMKH_1_2.setBounds(10, 201, 87, 24);
		LabelMKH_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(LabelMKH_1_2);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(120, 201, 189, 24);
		textFieldName.setColumns(10);
		contentPane.add(textFieldName);
		
		JLabel LabelDate = new JLabel("Ngày Sinh");
		LabelDate.setBounds(10, 235, 87, 24);
		LabelDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(LabelDate);
		
		JLabel LabelAddress = new JLabel("Địa Chỉ");
		LabelAddress.setBounds(10, 269, 87, 24);
		LabelAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(LabelAddress);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setBounds(120, 272, 189, 24);
		textFieldAddress.setColumns(10);
		contentPane.add(textFieldAddress);
		
		JLabel LabelPhone = new JLabel("Số Điện Thoại");
		LabelPhone.setBounds(10, 303, 105, 24);
		LabelPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(LabelPhone);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setBounds(120, 303, 189, 24);
		textFieldPhone.setColumns(10);
		contentPane.add(textFieldPhone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(348, 133, 87, 24);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblEmail);
		
		textFieldMail = new JTextField();
		textFieldMail.setBounds(445, 133, 190, 24);
		textFieldMail.setColumns(10);
		contentPane.add(textFieldMail);
		
		JLabel lblGioiTnh = new JLabel("Giới Tính");
		lblGioiTnh.setBounds(348, 167, 87, 24);
		lblGioiTnh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblGioiTnh);
		
		JLabel LabelClass = new JLabel("Lớp");
		LabelClass.setBounds(556, 167, 24, 24);
		LabelClass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(LabelClass);
		
		textFieldClass = new JTextField();
		textFieldClass.setBounds(582, 170, 53, 24);
		textFieldClass.setColumns(10);
		contentPane.add(textFieldClass);
		
		JLabel lblimKimTra1 = new JLabel("Điểm đánh giá đầu vào");
		lblimKimTra1.setBounds(422, 247, 167, 24);
		lblimKimTra1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblimKimTra1);
		
		textFieldTest1 = new JTextField();
		textFieldTest1.setBounds(432, 281, 81, 24);
		textFieldTest1.setColumns(10);
		contentPane.add(textFieldTest1);
		
		rdbtnNewRadioButton_Nam = new JRadioButton("Nam");
		rdbtnNewRadioButton_Nam.setBounds(445, 169, 57, 21);
		rdbtnNewRadioButton_Nam.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(rdbtnNewRadioButton_Nam);
		
		rdbtnNewRadioButton_Nu = new JRadioButton("Nữ");
		rdbtnNewRadioButton_Nu.setBounds(504, 169, 45, 21);
		rdbtnNewRadioButton_Nu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(rdbtnNewRadioButton_Nu);
		
		btn_gioiTinh = new ButtonGroup();
		btn_gioiTinh.add(rdbtnNewRadioButton_Nam);
		btn_gioiTinh.add(rdbtnNewRadioButton_Nu);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBounds(680, 258, 112, 21);
		btnXoa.setBackground(new Color(32, 178, 170));
		btnXoa.setForeground(new Color(255, 255, 255));
		btnXoa.addActionListener(action);
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnXoa);
		
		JButton btnCapNhat = new JButton("Cập Nhật");
		btnCapNhat.setBounds(680, 227, 112, 21);
		btnCapNhat.setBackground(new Color(32, 178, 170));
		btnCapNhat.setForeground(new Color(255, 255, 255));
		btnCapNhat.addActionListener(action);
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnCapNhat);
		
		JButton btnLuu = new JButton("Lưu");
		btnLuu.setBounds(680, 193, 112, 21);
		btnLuu.setBackground(new Color(32, 178, 170));
		btnLuu.setForeground(new Color(255, 255, 255));
		btnLuu.addActionListener(action);
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnLuu);
		
		JButton btnHuyBo = new JButton("Hủy Bỏ");
		btnHuyBo.setBounds(680, 293, 112, 21);
		btnHuyBo.setBackground(new Color(32, 178, 170));
		btnHuyBo.setForeground(new Color(255, 255, 255));
		btnHuyBo.addActionListener(action);
		btnHuyBo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnHuyBo);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setBounds(680, 162, 112, 21);
		btnThem.setBackground(new Color(32, 178, 170));
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.addActionListener(action);
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnThem);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(new Color(0, 139, 139));
		separator_1_1.setBounds(0, 337, 865, 13);
		contentPane.add(separator_1_1);
		
		JLabel LabelDsHocVien = new JLabel("Danh sách học viên");
		LabelDsHocVien.setBounds(364, 337, 138, 25);
		LabelDsHocVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(LabelDsHocVien);
		
		table = new JTable();
		table.setForeground(new Color(0, 139, 139));
		table.setBackground(new Color(255, 255, 255));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 Kh\u00F3a H\u1ECDc", "M\u00E3 H\u1ECDc Vi\u00EAn", "H\u1ECD v\u00E0 T\u00EAn", "Ng\u00E0y Sinh", "\u0110\u1ECBa Ch\u1EC9", "S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i", "Email", "Gi\u1EDBi T\u00EDnh", "Tr\u00ECnh \u0110\u1ED9", "L\u1EDBp", "Placenment Test"
			}
		));
		
		table.setRowHeight(25);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 360, 873, 300);
		contentPane.add(scrollPane);
		
		JLabel lblTrinhDott = new JLabel("Trình Độ");
		lblTrinhDott.setBounds(348, 201, 81, 24);
		lblTrinhDott.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblTrinhDott);
		
		comboBoxTrinhDott = new JComboBox();
		comboBoxTrinhDott.setBounds(445, 205, 190, 22);
		comboBoxTrinhDott.setForeground(new Color(32, 178, 170));
		comboBoxTrinhDott.setBackground(new Color(255, 255, 255));
		for (HocVan hocvan: listHocVan) {
			comboBoxTrinhDott.addItem(hocvan.getTenHocVan());
		}
		contentPane.add(comboBoxTrinhDott);
		
		JButton btnHyTmKim = new JButton("Hủy Tìm Kiếm");
		btnHyTmKim.setBounds(715, 32, 130, 27);
		btnHyTmKim.setBackground(new Color(32, 178, 170));
		btnHyTmKim.setForeground(new Color(255, 255, 255));
		btnHyTmKim.addActionListener(action);
		btnHyTmKim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnHyTmKim);
		
		JPanel panelDatePicker = new JPanel();
		panelDatePicker.setBounds(107, 235, 216, 35);
		contentPane.add(panelDatePicker);
		
		datePicker = new JDatePicker();
		datePicker.getButton().setBackground(new Color(255, 255, 255));
		panelDatePicker.add(datePicker);
		
		JButton btnQuayLi = new JButton("Quay Lại");
		btnQuayLi.setBounds(680, 126, 112, 21);
		btnQuayLi.setBackground(new Color(32, 178, 170));
		btnQuayLi.setForeground(new Color(255, 255, 255));
		btnQuayLi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnQuayLi);
		
		JLabel lblimKimTra1_1 = new JLabel("/100");
		lblimKimTra1_1.setBounds(520, 281, 45, 24);
		lblimKimTra1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblimKimTra1_1);
		btnQuayLi.addActionListener(action);
		
		try {
			loadUser();
			for(var hv : this.model.getDsHocVien()) {
				this.ThemHocVienvaoTable(hv);
			}
			
		} catch (Exception e) {
			System.out.println("Error at load user");
			e.printStackTrace();
		}
	}

	public void xoaForm() {
		textFieldMKHtt.setText("");
		textFieldMHVtt.setText("");
		textFieldName.setText("");
		textFieldAddress.setText("");
		textFieldClass.setText("");
		datePicker.getFormattedTextField().setText("");
		textFieldMail.setText("");
		textFieldPhone.setText("");
		textFieldTest1.setText("");
		comboBoxTrinhDott.setSelectedIndex(-1);
		btn_gioiTinh.clearSelection();
		
	}
	public void ThemHocVienvaoTable(HocVien hv) {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		model_table.addRow(new Object[] {hv.getMaKhoaHoc()+"",
				hv.getMaHocVien()+"",
				hv.getTenHocVien()+"",
				hv.getNgaySinh().getDate()+"/"+(hv.getNgaySinh().getMonth()+1)+"/"+(hv.getNgaySinh().getYear()+1900),
				hv.getDiaChi()+"",
				hv.getPhone()+"",
				hv.getEmail()+"",
				(hv.isGioiTinh()?"Nam":"Nữ"),
				hv.getTrinhDo().getTenHocVan(),
				hv.getLop()+"",
				hv.getDiemKT1()+""});
		
	}
	public void themhoacCapNhatHocVien(HocVien hv) {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		if(!this.model.KiemTraTonTai(hv)) {
		this.model.insert(hv); 
		this.ThemHocVienvaoTable(hv);
		}else {
			this.model.update(hv);
			int soLuongDong = model_table.getRowCount();
			for (int i = 0; i < soLuongDong; i++) {
				String id=model_table.getValueAt(i, 1)+"";
				if(id.equals(hv.getMaHocVien()+"")){
					model_table.setValueAt(hv.getMaKhoaHoc()+"",i,0);
					model_table.setValueAt(hv.getMaHocVien()+"",i,1);
					model_table.setValueAt(hv.getTenHocVien()+"",i,2);
					model_table.setValueAt(hv.getNgaySinh().getDate()+"/"+(hv.getNgaySinh().getMonth()+1)+"/"+(hv.getNgaySinh().getYear()+1900)+"",i,3);
					model_table.setValueAt(hv.getDiaChi()+"",i,4);
					model_table.setValueAt(hv.getPhone()+"",i,5);
					model_table.setValueAt(hv.getEmail()+"",i,6);
					model_table.setValueAt((hv.isGioiTinh()?"Nam":"Nữ"),i,7);
					model_table.setValueAt(hv.getTrinhDo().getTenHocVan()+"",i,8);
					model_table.setValueAt(hv.getLop()+"",i,9);
					model_table.setValueAt(hv.getDiemKT1()+"",i,10);
					
				}
			}
		}
		
	}
	
	public HocVien getHocVienDangChon() {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		int i_row = table.getSelectedRow();
		
		String maKhoaHoc = model_table.getValueAt(i_row, 0)+"";
		int maHocVien = Integer.valueOf(model_table.getValueAt(i_row, 1)+"");
		String tenHocVien = model_table.getValueAt(i_row, 2)+"";
		String s_ngaySinh = model_table.getValueAt(i_row, 3)+"";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date ngaySinh;
		try {
			ngaySinh = formatter.parse(s_ngaySinh);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		String diaChi = model_table.getValueAt(i_row, 4)+"";
		String phone = model_table.getValueAt(i_row,5 )+"";
		String email = model_table.getValueAt(i_row,6 )+"";
		String textGioiTinh = model_table.getValueAt(i_row,7 )+"";
		boolean gioiTinh = textGioiTinh.equals("Nam");
		HocVan hocVan = HocVan.getHocVanByTen(model_table.getValueAt(i_row,8)+"");
		String lop = model_table.getValueAt(i_row, 9)+"";
		float diemKT1 = Float.valueOf(model_table.getValueAt(i_row,10 )+"");
		
		HocVien hv = new HocVien(maKhoaHoc, maHocVien, tenHocVien, hocVan, diaChi, ngaySinh, gioiTinh, email, lop, phone, diemKT1);
		return hv;
	}
	
	public void hienthiThongtinThiSinhdachon() {
		HocVien hv = getHocVienDangChon();
		
		this.textFieldMKHtt.setText(hv.getMaKhoaHoc()+"");
		this.textFieldMHVtt.setText(hv.getMaHocVien()+"");
		this.textFieldName.setText(hv.getTenHocVien()+"");
		this.datePicker.getModel().setDate(hv.getNgaySinh().getYear() + 1900, hv.getNgaySinh().getMonth(), hv.getNgaySinh().getDate());
		this.datePicker.getModel().setSelected(true);
		this.textFieldAddress.setText(hv.getDiaChi()+"");
		this.textFieldPhone.setText(hv.getPhone()+"");
		this.textFieldMail.setText(hv.getEmail()+"");
		this.textFieldClass.setText(hv.getLop()+"");
		this.comboBoxTrinhDott.setSelectedItem(hv.getTrinhDo().getTenHocVan());
		if (hv.isGioiTinh()) {
			rdbtnNewRadioButton_Nam.setSelected(true);
		} else {
			rdbtnNewRadioButton_Nu.setSelected(true);
		}
		this.textFieldTest1.setText(hv.getDiemKT1()+"");
	}

	public void thuchienXoa() {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		int i_row = table.getSelectedRow();
		int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hóa học viên không!");
		if(luaChon==JOptionPane.YES_OPTION) {
			HocVien hv = getHocVienDangChon();
			try {
				deleteById(hv.getMaHocVien());
			} catch (Exception e) {
				System.out.println("Error at delete user");
				e.printStackTrace();
			}
			this.model.deleteByMaHV(hv.getMaHocVien());
			model_table.removeRow(i_row);
		}
	}
	public HocVien getHocVienFromForm() {
		String maKhoaHoc = this.textFieldMKHtt.getText();
		int maHocVien = Integer.valueOf(this.textFieldMHVtt.getText());
		String tenHocVien = this.textFieldName.getText();
		int trinhDo = this.comboBoxTrinhDott.getSelectedIndex();
		HocVan hocVan = HocVan.getHocVanById(trinhDo);
		String diaChi = this.textFieldAddress.getText();
		Date ngaySinh = new Date(this.datePicker.getModel().getYear() - 1900, this.datePicker.getModel().getMonth(), this.datePicker.getModel().getDay());
		boolean gioiTinh = true;
		if(this.rdbtnNewRadioButton_Nam.isSelected()) {
			gioiTinh=true;
		}else if(this.rdbtnNewRadioButton_Nu.isSelected()) {
			gioiTinh=false;
		}
		String email = this.textFieldMail.getText();
		String lop = this.textFieldClass.getText();
		String phone = String.valueOf(this.textFieldPhone.getText());
		float diemKT1 = Float.valueOf(this.textFieldTest1.getText());
		return new HocVien(maKhoaHoc, maHocVien, tenHocVien, hocVan, diaChi, ngaySinh, gioiTinh, email, lop, phone, diemKT1);
	}
	
	public void thuchienCapNhatHocVien(HocVien hv) throws Exception {
		Connection conn = null;
		PreparedStatement preStm = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			String sql = "UPDATE QLHV \r\n"
					+ "SET diaChi = ?, diemKT1 = ?, diemKT2 = ?, diemKT3 = ?, email = ?, gioiTinh = ?, lop = ?, maKhoaHoc = ?, \r\n"
					+ "ngaySinh = ?, phone = ?, tenHocVien = ?, trinhDo = ? \r\n"
					+ "WHERE maHocVien = ?";
			var ngaySinh = new java.sql.Date(hv.getNgaySinh().getTime());
			String trinhDo = Integer.toString(hv.getTrinhDo().getMaHocVan());
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, hv.getDiaChi());
			preStm.setFloat(2, hv.getDiemKT1());
			preStm.setString(5, hv.getEmail());
			preStm.setBoolean(6, hv.isGioiTinh());
			preStm.setString(7, hv.getLop());
			preStm.setString(8, hv.getMaKhoaHoc());
			preStm.setDate(9, ngaySinh);
			preStm.setString(10, hv.getPhone());
			preStm.setString(11, hv.getTenHocVien());
			preStm.setString(12, trinhDo);
			preStm.setInt(13, hv.getMaHocVien());
			
			preStm.executeUpdate();
		}finally {
			if(rs != null)
				rs.close();
			if(preStm != null)
				preStm.close();
			if(conn != null)
				conn.close();
		}
	}
	
	public void thuchienThemHocVien() {
		
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			String insertQuery = "INSERT INTO qlhv (maKhoaHoc, maHocVien, tenHocVien, trinhDo, diaChi, ngaySinh, gioiTinh, email, lop, phone, diemKT1) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
			Statement stmt = conn.createStatement();
			String maKhoaHoc = this.textFieldMKHtt.getText();
			int maHocVien = Integer.valueOf(this.textFieldMHVtt.getText());
			String tenHocVien = this.textFieldName.getText();
			int trinhDo = this.comboBoxTrinhDott.getSelectedIndex();
			HocVan hocVan = HocVan.getHocVanById(trinhDo);
			String diaChi = this.textFieldAddress.getText();
			Date ngaySinh = new Date(this.datePicker.getModel().getYear() - 1900, this.datePicker.getModel().getMonth(), this.datePicker.getModel().getDay());
			boolean gioiTinh = true;
			if(this.rdbtnNewRadioButton_Nam.isSelected()) {
				gioiTinh=true;
			}else if(this.rdbtnNewRadioButton_Nu.isSelected()) {
				gioiTinh=false;
			}
			String email = this.textFieldMail.getText();
			String lop = this.textFieldClass.getText();
			String phone = String.valueOf(this.textFieldPhone.getText());
			float diemKT1 = Float.valueOf(this.textFieldTest1.getText());
			HocVien hv = new HocVien(maKhoaHoc, maHocVien, tenHocVien, hocVan, diaChi, ngaySinh, gioiTinh, email, lop, phone, diemKT1);
			this.themhoacCapNhatHocVien(hv);
    		java.util.Date ngaySinh1 = new java.util.Date();
    		java.sql.Date sqlNgaySinh = new java.sql.Date(
    		    ngaySinh.getTime()
    		);

            preparedStatement.setString(1,maKhoaHoc);
            preparedStatement.setInt(2,maHocVien);
            preparedStatement.setString(3, tenHocVien);
            preparedStatement.setInt(4, trinhDo);
            preparedStatement.setString(5, diaChi);
            preparedStatement.setDate(6, sqlNgaySinh);
            preparedStatement.setBoolean(7, gioiTinh);
            preparedStatement.setString(8, email);
            preparedStatement.setString(9, lop);
            preparedStatement.setString(10, phone);
            preparedStatement.setFloat(11, diemKT1);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");

            preparedStatement.close();
            conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	boolean deleteById(int id) throws Exception{
		Connection conn = null;
		PreparedStatement preStm = null;
		
		boolean check = false;
		try {
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			String sql = "DELETE FROM QLHV WHERE maHocVien = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setInt(1, id);
			
			check = preStm.executeUpdate() > 0;
		} finally {
			if(preStm != null)
				preStm.close();
			if(conn != null)
				conn.close();
		}
		
		return check;
	}
	
	void loadUser() throws Exception {
		Connection conn = null;
		PreparedStatement preStm = null;
		ResultSet rs = null;
		
		ArrayList<HocVien> result = new ArrayList<HocVien>();
		try {
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			String sql = "SELECT * FROM QLHV";
			preStm = conn.prepareStatement(sql);
			rs = preStm.executeQuery();
			while(rs.next()) {
				int maTrinhDo = Integer.parseInt(rs.getString("trinhDo"));
				
				var hv = new HocVien(
							rs.getString("maKhoaHoc"),
							rs.getInt("maHocVien"),
							rs.getString("tenHocVien"),
							HocVan.getHocVanById(maTrinhDo),
							rs.getString("diaChi"),
							rs.getDate("ngaySinh"),
							rs.getBoolean("gioiTinh"),
							rs.getString("email"),
							rs.getString("lop"),
							rs.getString("phone"),
							rs.getFloat("diemKT1")
						);
				result.add(hv);
			}
			this.model.setDsHocVien(result);
		}finally {
			if(rs != null)
				rs.close();
			if(preStm != null)
				preStm.close();
			if(conn != null)
				conn.close();
		}
	}
	
	public void thucHienTimKiem() {
		this.thucHienTaiLaiDuLieu();
		int trinhDo = this.comboBoxTrinhDo.getSelectedIndex();
		String maHVTimKiem = this.textFieldMHV.getText();
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		int soLuongDong = model_table.getRowCount();
		
		Set<Integer> idHocVienCanXoa = new TreeSet<Integer>();
		if(trinhDo >= 0 && maHVTimKiem.length() > 0) {
			HocVan hocVandachon = HocVan.getHocVanById(trinhDo);
			for (int i = 0; i < soLuongDong; i++) {
				String tenTrinhDo = model_table.getValueAt(i, 8)+"";
				String id=model_table.getValueAt(i, 1)+"";
				if(!tenTrinhDo.equals(hocVandachon.getTenHocVan()) || !id.equals(maHVTimKiem)){
					idHocVienCanXoa.add(Integer.valueOf(id));
				}
			}
		}
		for (Integer idCanXoa : idHocVienCanXoa) {
			System.out.print(idCanXoa);
			soLuongDong = model_table.getRowCount();
			for (int i = 0; i < soLuongDong; i++) {
				String idTrongTable = model_table.getValueAt(i, 1)+"";
				System.out.println("idTrongTable: "+idTrongTable);
				if(idTrongTable.equals(idCanXoa.toString())){
					System.out.println("Đã xóa: "+i);
					try {
						model_table.removeRow(i);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
	}
	
	
	public void thucHienTaiLaiDuLieu() {
		while (true) {
			DefaultTableModel model_table = (DefaultTableModel) table.getModel();
			int soLuongDong = model_table.getRowCount();
			if(soLuongDong==0)
				break;
			else
				try {
					model_table.removeRow(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		for (HocVien hv : this.model.getDsHocVien()) {
			this.ThemHocVienvaoTable(hv);
		}
	}
	public void thoatKhoiChuongTrinh() {
		int luaChon = JOptionPane.showConfirmDialog(
			    this,
			    "Bạn có muốn thoải khỏi chương trình?",
			    "Exit",
			    JOptionPane.YES_NO_OPTION);
		if (luaChon == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
    private void saveDataToFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
                for (int i = 0; i < table.getColumnCount(); i++) {
                    writer.write(table.getColumnName(i));
                    if (i < table.getColumnCount() - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();
                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        writer.write(table.getValueAt(i, j).toString());
                        if (j < table.getColumnCount() - 1) {
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
	public void openFile(File file) {
		ArrayList ds = new ArrayList();
		try {
			this.model.setTenFile(file.getAbsolutePath());
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			HocVien hv = null;
			while((hv = (HocVien) ois.readObject())!=null) {
				ds.add(hv);
			}
			ois.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.model.setDsHocVien(ds);;
	}
	public void thuchienOpenFile() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			openFile(file);
			thucHienTaiLaiDuLieu();
		} 	
	}

	public void thuchienQuayLai() {
	    LuaChon luaChon = new LuaChon();
	    luaChon.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    luaChon.setSize(600, 400);
	    luaChon.setVisible(true);
	    dispose();
	}

}
	
