package model;

import java.util.ArrayList;

public class QLHVModel {
	private ArrayList<HocVien> dsHocVien;
	private String luaChon;
	private String tenFile;
	public QLHVModel() {
		this.dsHocVien = new ArrayList<HocVien>();
		this.luaChon = "";
		this.tenFile = "";
	}

	public QLHVModel(ArrayList<HocVien> dsHocVien) {
		this.dsHocVien = dsHocVien;
	}

	public ArrayList<HocVien> getDsHocVien() {
		return dsHocVien;
	}

	public void setDsHocVien(ArrayList<HocVien> dsHocVien) {
		this.dsHocVien = dsHocVien;
	}
	
	public void insert(HocVien hocVien) {
		this.dsHocVien.add(hocVien);
	}
	public void delete(HocVien hocVien) {
		this.dsHocVien.remove(hocVien);
	}
	public void deleteByMaHV(int maHV) {
		this.dsHocVien.removeIf(hv -> hv.getMaHocVien() == maHV);
	}
	public void update(HocVien hocVien) {
		this.dsHocVien.remove(hocVien);
		this.dsHocVien.add(hocVien);
	}

	public String getLuaChon() {
		return luaChon;
	}

	public void setLuaChon(String luaChon) {
		this.luaChon = luaChon;
	}

	public boolean KiemTraTonTai(HocVien hv) {
		for (HocVien hocVien : dsHocVien) {
			if(hocVien.getMaHocVien() == hv.getMaHocVien())
				return true;
		}
		return false;
	}

	public String getTenFile() {
		return tenFile;
	}

	public void setTenFile(String tenFile) {
		this.tenFile = tenFile;
	}
	
}
