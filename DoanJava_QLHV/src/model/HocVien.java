package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class HocVien implements Serializable{
	private String maKhoaHoc;
	private int maHocVien;
	private String tenHocVien;
	private HocVan trinhDo;
	private String diaChi;
	private Date ngaySinh;
	private boolean gioiTinh;
	private String email;
	private String lop;
	private String phone;
	private float diemKT1;
	public HocVien(String maKhoaHoc, int maHocVien, String tenHocVien, HocVan trinhDo, String diaChi, Date ngaySinh,
			boolean gioiTinh, String email, String lop, String phone, float diemKT1) {
		this.maKhoaHoc = maKhoaHoc;
		this.maHocVien = maHocVien;
		this.tenHocVien = tenHocVien;
		this.trinhDo = trinhDo;
		this.diaChi = diaChi;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.email = email;
		this.lop = lop;
		this.phone = phone;
		this.diemKT1 = diemKT1;
	}
	public String getMaKhoaHoc() {
		return maKhoaHoc;
	}
	public void setMaKhoaHoc(String maKhoaHoc) {
		this.maKhoaHoc = maKhoaHoc;
	}
	public int getMaHocVien() {
		return maHocVien;
	}
	public void setMaHocVien(int maHocVien) {
		this.maHocVien = maHocVien;
	}
	public String getTenHocVien() {
		return tenHocVien;
	}
	public void setTenHocVien(String tenHocVien) {
		this.tenHocVien = tenHocVien;
	}
	public HocVan getTrinhDo() {
		return trinhDo;
	}
	public void setTrinhDo(HocVan trinhDo) {
		this.trinhDo = trinhDo;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLop() {
		return lop;
	}
	public void setLop(String lop) {
		this.lop = lop;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public float getDiemKT1() {
		return diemKT1;
	}
	public void setDiemKT1(float diemKT1) {
		this.diemKT1 = diemKT1;
	}

	@Override
	public String toString() {
		return "HocVien [maKhoaHoc=" + maKhoaHoc + ", maHocVien=" + maHocVien + ", tenHocVien=" + tenHocVien
				+ ", trinhDo=" + trinhDo + ", diaChi=" + diaChi + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh
				+ ", email=" + email + ", lop=" + lop + ", phone=" + phone + ", diemKT1=" + diemKT1 + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(diaChi, diemKT1, email, gioiTinh, lop, maHocVien, maKhoaHoc, ngaySinh,
				phone, tenHocVien, trinhDo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HocVien other = (HocVien) obj;
		return Objects.equals(diaChi, other.diaChi)
				&& Float.floatToIntBits(diemKT1) == Float.floatToIntBits(other.diemKT1)
				&& Objects.equals(email, other.email) && gioiTinh == other.gioiTinh && Objects.equals(lop, other.lop)
				&& maHocVien == other.maHocVien && Objects.equals(maKhoaHoc, other.maKhoaHoc)
				&& Objects.equals(ngaySinh, other.ngaySinh) && phone == other.phone
				&& Objects.equals(tenHocVien, other.tenHocVien) && Objects.equals(trinhDo, other.trinhDo);
	}
	
	
	
}
