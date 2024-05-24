package quantrivien.models;

import java.time.LocalDate;

public class ThongBao {
	private int id;
	private String tieudeString;
	private String noidungString;
	private String NguoiGui;
	private LocalDate thoigianDate;
	private String NguoiNhan;

	public ThongBao() {
		// TODO Auto-generated constructor stub
	}
	public ThongBao(String tieudeString, String noidungString, LocalDate thoigianDate) {
		super();
		this.tieudeString = tieudeString;
		this.noidungString = noidungString;
		this.thoigianDate = thoigianDate;
	}
	public ThongBao(int id, String tieudeString, String noidungString, String nguoiGui, LocalDate thoigianDate,
			String nguoiNhan) {
		super();
		this.id = id;
		this.tieudeString = tieudeString;
		this.noidungString = noidungString;
		NguoiGui = nguoiGui;
		this.thoigianDate = thoigianDate;
		NguoiNhan = nguoiNhan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTieudeString() {
		return tieudeString;
	}

	public void setTieudeString(String tieudeString) {
		this.tieudeString = tieudeString;
	}

	public String getNoidungString() {
		return noidungString;
	}

	public void setNoidungString(String noidungString) {
		this.noidungString = noidungString;
	}

	public String getNguoiGui() {
		return NguoiGui;
	}

	public void setNguoiGui(String nguoiGui) {
		NguoiGui = nguoiGui;
	}

	public LocalDate getThoigianDate() {
		return thoigianDate;
	}

	public void setThoigianDate(LocalDate thoigianDate) {
		this.thoigianDate = thoigianDate;
	}

	public String getNguoiNhan() {
		return NguoiNhan;
	}

	public void setNguoiNhan(String nguoiNhan) {
		NguoiNhan = nguoiNhan;
	}
	

}
