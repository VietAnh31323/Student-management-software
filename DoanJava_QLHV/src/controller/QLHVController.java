package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

import javax.swing.Action;
import javax.swing.JOptionPane;

import model.HocVan;
import model.HocVien;
import view.QLHVView;

public class QLHVController implements Action{
	public QLHVView view;
	public QLHVController(QLHVView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
//		JOptionPane.showMessageDialog(view, "Bạn vừa nhấn vào: "+actionCommand);
		if(actionCommand.equals("Thêm")) {
			this.view.xoaForm();
			this.view.model.setLuaChon("Thêm");
		}else if(actionCommand.equals("Lưu")) {
			try {
				var hv = this.view.getHocVienFromForm();
				if(this.view.model.KiemTraTonTai(hv)) {
					try {
						this.view.thuchienCapNhatHocVien(hv);
						this.view.themhoacCapNhatHocVien(hv);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						System.out.println("Error at cap nhat hoc vien");
						e1.printStackTrace();
					}					
				}else {
					this.view.thuchienThemHocVien();	
				}				
				
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
		}else if(actionCommand.equals("Cập Nhật")) {
			this.view.hienthiThongtinThiSinhdachon();
		}else if(actionCommand.equals("Xóa")) {
			this.view.thuchienXoa();
		}else if(actionCommand.equals("Hủy Bỏ")) {
			this.view.xoaForm();
		}else if(actionCommand.equals("Tìm Kiếm")) {
			this.view.thucHienTimKiem();
		}else if(actionCommand.equals("Hủy Tìm Kiếm")) {
			this.view.thucHienTaiLaiDuLieu();
		}else if(actionCommand.equals("Exit")) {
			this.view.thoatKhoiChuongTrinh();
		}else if(actionCommand.equals("Open")) {
			this.view.thuchienOpenFile();
		}else if(actionCommand.equals("Quay Lại")) {
			this.view.thuchienQuayLai();
		}
	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEnabled(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

}
