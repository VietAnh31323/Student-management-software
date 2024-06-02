package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class HocVan implements Serializable{
	private int maHocVan;
	private String tenHocVan;
	public HocVan(int maHocVan, String tenHocVan) {
		super();
		this.maHocVan = maHocVan;
		this.tenHocVan = tenHocVan;
	}
	public int getMaHocVan() {
		return maHocVan;
	}
	public void setMaHocVan(int maHocVan) {
		this.maHocVan = maHocVan;
	}
	public String getTenHocVan() {
		return tenHocVan;
	}
	public void setTenHocVan(String tenHocVan) {
		this.tenHocVan = tenHocVan;
	}
	@Override
	public String toString() {
		return "HocVan [maHocVan=" + maHocVan + ", tenHocVan=" + tenHocVan + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(maHocVan, tenHocVan);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HocVan other = (HocVan) obj;
		return maHocVan == other.maHocVan && Objects.equals(tenHocVan, other.tenHocVan);
	}
	public static ArrayList<HocVan> getDSHocVan(){
		String[] arr_HocVan = {
				"A1",
				"A2",
				"A3",
				"A4",
				"B1"};
		ArrayList<HocVan> listHocVan = new ArrayList<HocVan>();
		int i=0;
		for (String tenHocVan : arr_HocVan) {
			HocVan t = new HocVan(i,tenHocVan);
			i++;
			listHocVan.add(t);
		}
		return listHocVan;
	}
	public static HocVan getHocVanById(int trinhDo) {
		return HocVan.getDSHocVan().get(trinhDo);
	}
	public static HocVan getHocVanByTen(String tenHocVan) {
		ArrayList<HocVan> listHocVan = HocVan.getDSHocVan();
		for (HocVan hocVan : listHocVan) {
			if(hocVan.tenHocVan.equals(tenHocVan))
				return hocVan;
		}
		return null;
	}
}
