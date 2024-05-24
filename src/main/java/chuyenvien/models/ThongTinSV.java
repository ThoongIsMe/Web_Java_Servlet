package chuyenvien.models;

import java.time.LocalDate;

public class ThongTinSV {

	private int MaSV;
	private String hoteString;
	private LocalDate NgaySinh;
	private String NoiSinh;
	private String GioiTinh;
	private String DanToc;
	private String SoCCCD;
	private String SDT;
	private String Email;
	private String DiaChiSV;

	private String KhoaHoc;
	private String NienKhoa;
	private String NamNhapHoc;
	private String NamHetHanHoc;

	private String CTDaoTao;
	private String LopSV;
	private int TinhTrang;
	private String AnhTheLink;
	////
	private TaiKhoan taiKhoan;

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public ThongTinSV(String email, TaiKhoan taiKhoan) {
		super();
		Email = email;
		this.taiKhoan = taiKhoan;
	}

	////
	public ThongTinSV() {
		// TODO Auto-generated constructor stub
	}

	public ThongTinSV(int maSV, String hoteString, LocalDate ngaySinh, String noiSinh, String gioiTinh, String danToc,
			String soCCCD, String sDT, String email, String diaChiSV, String khoaHoc, String nienKhoa,
			String namNhapHoc, String namHetHanHoc, String cTDaoTao, String lopSV, int tinhTrang, String anhTheLink) {
		super();
		MaSV = maSV;
		this.hoteString = hoteString;
		NgaySinh = ngaySinh;
		NoiSinh = noiSinh;
		GioiTinh = gioiTinh;
		DanToc = danToc;
		SoCCCD = soCCCD;
		SDT = sDT;
		Email = email;
		DiaChiSV = diaChiSV;
		KhoaHoc = khoaHoc;
		NienKhoa = nienKhoa;
		NamNhapHoc = namNhapHoc;
		NamHetHanHoc = namHetHanHoc;
		CTDaoTao = cTDaoTao;
		LopSV = lopSV;
		TinhTrang = tinhTrang;
		AnhTheLink = anhTheLink;
	}

	public int getMaSV() {
		return MaSV;
	}

	public void setMaSV(int maSV) {
		MaSV = maSV;
	}

	public String getHoteString() {
		return hoteString;
	}

	public void setHoteString(String hoteString) {
		this.hoteString = hoteString;
	}

	public LocalDate getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		NgaySinh = ngaySinh;
	}

	public String getNoiSinh() {
		return NoiSinh;
	}

	public void setNoiSinh(String noiSinh) {
		NoiSinh = noiSinh;
	}

	public String getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public String getDanToc() {
		return DanToc;
	}

	public void setDanToc(String danToc) {
		DanToc = danToc;
	}

	public String getSoCCCD() {
		return SoCCCD;
	}

	public void setSoCCCD(String soCCCD) {
		SoCCCD = soCCCD;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getDiaChiSV() {
		return DiaChiSV;
	}

	public void setDiaChiSV(String diaChiSV) {
		DiaChiSV = diaChiSV;
	}

	public String getKhoaHoc() {
		return KhoaHoc;
	}

	public void setKhoaHoc(String khoaHoc) {
		KhoaHoc = khoaHoc;
	}

	public String getNienKhoa() {
		return NienKhoa;
	}

	public void setNienKhoa(String nienKhoa) {
		NienKhoa = nienKhoa;
	}

	public String getNamNhapHoc() {
		return NamNhapHoc;
	}

	public void setNamNhapHoc(String namNhapHoc) {
		NamNhapHoc = namNhapHoc;
	}

	public String getNamHetHanHoc() {
		return NamHetHanHoc;
	}

	public void setNamHetHanHoc(String namHetHanHoc) {
		NamHetHanHoc = namHetHanHoc;
	}

	public String getCTDaoTao() {
		return CTDaoTao;
	}

	public void setCTDaoTao(String cTDaoTao) {
		CTDaoTao = cTDaoTao;
	}

	public String getLopSV() {
		return LopSV;
	}

	public void setLopSV(String lopSV) {
		LopSV = lopSV;
	}

	public int getTinhTrang() {
		return TinhTrang;
	}

	public void setTinhTrang(int tinhTrang) {
		TinhTrang = tinhTrang;
	}

	public String getAnhTheLink() {
		return AnhTheLink;
	}

	public void setAnhTheLink(String anhTheLink) {
		AnhTheLink = anhTheLink;
	}

}
