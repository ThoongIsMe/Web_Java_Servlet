package quantrivien.models;
import java.io.Serializable;
import java.time.LocalDate;

public class SinhVien implements Serializable {
    private static final long serialVersionUID = 1L;
    private String maSV;
    private String tenSV;
    private LocalDate ngaySinh;
    private String ngaySinhString;
    private String noiSinh;
    private String gioiTinh;
    private String danToc;
    private String cccd;
    private String sdt;
    private String email;
    private String diaChi;
    private String khoa;
    private String nienKhoa;
    private int namNhapHoc;
    private int namKetThuc;
    private String ctDaoTao;
    private String lop;   
    private String tinhTrang;   
    private String anhThe;   
    
    public SinhVien() {
        super();
    }
    
    public SinhVien(String tenSV, String maSV) {
        super();
        this.tenSV = tenSV;
        this.maSV = maSV;
    }
    
    public SinhVien(String tenSV, String ngaySinhString, String cccd, String nienKhoa, String lop) {
        super();
        this.tenSV = tenSV;
        this.ngaySinhString = ngaySinhString;
        this.cccd = cccd;
        this.nienKhoa = nienKhoa;
        this.lop = lop;
    }
    
    public SinhVien(String maSV, String tenSV, LocalDate ngaySinh, String noiSinh,
            String gioiTinh, String danToc, String cccd, String sdt,
            String email, String diaChi, String khoa, String nienKhoa,
            int namNhapHoc, int namKetThuc, String ctDaoTao, String lop,
            String tinhTrang, String anhThe) {
		this.maSV = maSV;
		this.tenSV = tenSV;
		this.ngaySinh = ngaySinh;
		this.noiSinh = noiSinh;
		this.gioiTinh = gioiTinh;
		this.danToc = danToc;
		this.cccd = cccd;
		this.sdt = sdt;
		this.email = email;
		this.diaChi = diaChi;
		this.khoa = khoa;
		this.nienKhoa = nienKhoa;
		this.namNhapHoc = namNhapHoc;
		this.namKetThuc = namKetThuc;
		this.ctDaoTao = ctDaoTao;
		this.lop = lop;
		this.tinhTrang = tinhTrang;
		this.anhThe = anhThe;
	}

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }
    
    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    
    public String getNgaySinhString() {
        return ngaySinhString;
    }

    public void setNgaySinhString(String ngaySinhString) {
        this.ngaySinhString = ngaySinhString;
    }

    public String getNoiSinh() {
        return noiSinh;
    }

    public void setNoiSinh(String noiSinh) {
        this.noiSinh = noiSinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getNienKhoa() {
        return nienKhoa;
    }

    public void setNienKhoa(String nienKhoa) {
        this.nienKhoa = nienKhoa;
    }

    public int getNamNhapHoc() {
        return namNhapHoc;
    }

    public void setNamNhapHoc(int namNhapHoc) {
        this.namNhapHoc = namNhapHoc;
    }

    public int getNamKetThuc() {
        return namKetThuc;
    }

    public void setNamKetThuc(int namKetThuc) {
        this.namKetThuc = namKetThuc;
    }

    public String getCtDaoTao() {
        return ctDaoTao;
    }

    public void setCtDaoTao(String ctDaoTao) {
        this.ctDaoTao = ctDaoTao;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getAnhThe() {
        return anhThe;
    }

    public void setAnhThe(String anhThe) {
        this.anhThe = anhThe;
    }
}