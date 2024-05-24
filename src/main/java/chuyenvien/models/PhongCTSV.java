package chuyenvien.models;

import java.time.LocalDate;

public class PhongCTSV {
	private String MaCV;
	private String TenCV;
	private String Email;
	private String SoDienThoai;
	private String DiaChi;
	private LocalDate NgayBatDauLam;
	private int TinhTrang;
	private int quyenhan;
	
	public PhongCTSV(String maCV, String tenCV, String email, String soDienThoai, String diaChi,
			LocalDate ngayBatDauLam, int tinhTrang, int quyenhan) {
		super();
		MaCV = maCV;
		TenCV = tenCV;
		Email = email;
		SoDienThoai = soDienThoai;
		DiaChi = diaChi;
		NgayBatDauLam = ngayBatDauLam;
		TinhTrang = tinhTrang;
		this.quyenhan = quyenhan;
	}



	public int getQuyenhan() {
		return quyenhan;
	}



	public void setQuyenhan(int quyenhan) {
		this.quyenhan = quyenhan;
	}



	public PhongCTSV() {
		// TODO Auto-generated constructor stub
	}

	

	public String getMaCV() {
		return MaCV;
	}

	public void setMaCV(String maCV) {
		MaCV = maCV;
	}

	public String getTenCV() {
		return TenCV;
	}

	public void setTenCV(String tenCV) {
		TenCV = tenCV;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getSoDienThoai() {
		return SoDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		SoDienThoai = soDienThoai;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public LocalDate getNgayBatDauLam() {
		return NgayBatDauLam;
	}

	public void setNgayBatDauLam(LocalDate ngayBatDauLam) {
		NgayBatDauLam = ngayBatDauLam;
	}

	public int getTinhTrang() {
		return TinhTrang;
	}

	public void setTinhTrang(int tinhTrang) {
		TinhTrang = tinhTrang;
	}
	

}
