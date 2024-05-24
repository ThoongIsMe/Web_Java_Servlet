package chuyenvien.models;

import java.time.LocalDate;

public class Giayxacnhan {
	private int id;
	private int MaSV;
	private LocalDate ThoiGianXin;
	private LocalDate ThoiGianNhan;
	private String LyDo;
	private int TrangThai;
	private String MaLoaiGiay;
	private String MaCV;
	private LocalDate ThoiGianDuyet;
	private int SL;


	public Giayxacnhan() {
	}

	public Giayxacnhan(int id, int maSV, LocalDate thoiGianXin, LocalDate thoiGianDuyet, String lyDo, int trangThai,
			String maLoaiGiay, String maCV, LocalDate thoiGianNhan, int sL) {
		this.id = id;
		this.MaSV = maSV;
		this.ThoiGianXin = thoiGianXin;
		this.ThoiGianNhan = thoiGianNhan;
		this.LyDo = lyDo;
		this.TrangThai = trangThai;
		this.MaLoaiGiay = maLoaiGiay;
		this.MaCV = maCV;
		this.ThoiGianDuyet = thoiGianDuyet;
		this.SL = sL;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaSV() {
		return this.MaSV;
	}

	public void setMaSV(int maSV) {
		this.MaSV = maSV;
	}

	public LocalDate getThoiGianXin() {
		return this.ThoiGianXin;
	}

	
	public void setThoiGianXin(LocalDate thoiGianXin) {
		this.ThoiGianXin = thoiGianXin;
	}

	public LocalDate getThoiGianNhan() {
		return this.ThoiGianNhan;
	}

	public void setThoiGianNhan(LocalDate thoiGianNhan) {
		this.ThoiGianNhan = thoiGianNhan;
	}

	public String getLyDo() {
		return this.LyDo;
	}

	public void setLyDo(String lyDo) {
		this.LyDo = lyDo;
	}

	public int getTrangThai() {
		return this.TrangThai;
	}

	public void setTrangThai(int trangThai) {
		this.TrangThai = trangThai;
	}

	public String getMaLoaiGiay() {
		return this.MaLoaiGiay;
	}

	public void setMaLoaiGiay(String maLoaiGiay) {
		this.MaLoaiGiay = maLoaiGiay;
	}

	public String getMaCV() {
		return MaCV;
	}

	public void setMaCV(String maCV) {
		MaCV = maCV;
	}

	public LocalDate getThoiGianDuyet() {
		return this.ThoiGianDuyet;
	}

	public void setThoiGianDuyet(LocalDate thoiGianDuyet) {
		this.ThoiGianDuyet = thoiGianDuyet;
	}

	public int getSL() {
		return this.SL;
	}

	public void setSL(int sL) {
		this.SL = sL;
	}
}
