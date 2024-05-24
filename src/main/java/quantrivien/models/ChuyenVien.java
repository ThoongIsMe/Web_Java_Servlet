package quantrivien.models;
import java.io.Serializable;
import java.time.LocalDate;

public class ChuyenVien implements Serializable {
    private static final long serialVersionUID = 1L;
    private String maCV;
    private String tenCV;
    private String email;
    private String sdt;
    private String diaChi;
    private LocalDate ngayCongTac;
    private String tinhTrang;
    private String quyenHan;
    
    private String role;
    
    public ChuyenVien(String tenSV, String maSV, String quyenHan) {
        super();
        this.tenCV = tenSV;
        this.maCV = maSV;
        this.quyenHan = quyenHan;
    }
    
    public ChuyenVien(String maCV, String tenCV, String email, String sdt, String diaChi, LocalDate ngayCongTac, String tinhTrang, String quyenHan) {
        this.maCV = maCV;
        this.tenCV = tenCV;
        this.email = email;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.ngayCongTac = ngayCongTac;
        this.tinhTrang = tinhTrang;
        this.quyenHan = quyenHan;
    }

	public String getMaCV() {
        return maCV;
    }

    public void setMaSV(String maCV) {
        this.maCV = maCV;
    }
    public String getTenCV() {
        return tenCV;
    }

    public void setTenSV(String tenCV) {
        this.tenCV = tenCV;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public LocalDate getNgayCongTac() {
        return ngayCongTac;
    }

    public void setNgayCongTac(LocalDate ngayCongTac) {
        this.ngayCongTac = ngayCongTac;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
    
    public String getQuyenHan() {
        return quyenHan;
    }

    public void setQuyenHan(String quyenHan) {
        this.quyenHan = quyenHan;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}