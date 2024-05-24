package quantrivien.models;
import java.io.Serializable;
import java.time.LocalDate;

public class YeuCau implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String maSV;
    private LocalDate thoiGianXin;
    private String thoiGianXinString;
    private LocalDate thoiGianDuyet;
    private String lyDo;
    private String trangThai;
    private String tenDV;
    private String maCV;
    private int sl;
    private LocalDate thoiGianNhan;
    
    public YeuCau(String lyDo, String maSV) {
        super();
        this.lyDo = lyDo;
        this.maSV = maSV;
    }
    
    public YeuCau(int id, String maSV, String thoiGianXinString, String lyDo ,String trangThai, String tenDV, int sl) {
        super();
        this.id = id;
        this.maSV = maSV;
        this.thoiGianXinString = thoiGianXinString;
        this.lyDo = lyDo;
        this.trangThai = trangThai;
        this.tenDV = tenDV;
        this.sl = sl;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public LocalDate getThoiGianXin() {
        return thoiGianXin;
    }

    public void setThoiGianXin(LocalDate thoiGianXin) {
        this.thoiGianXin = thoiGianXin;
    }
    
    public String getThoiGianXinString() {
        return thoiGianXinString;
    }

    public void setThoiGianXin(String thoiGianXinString) {
        this.thoiGianXinString = thoiGianXinString;
    }
    
    public LocalDate getThoiGianDuyet() {
        return thoiGianDuyet;
    }

    public void setThoiGianDuyet(LocalDate thoiGianDuyet) {
        this.thoiGianDuyet = thoiGianDuyet;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public String getMaCV() {
        return maCV;
    }

    public void setMaCV(String maCV) {
        this.maCV = maCV;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public LocalDate getThoiGianNhan() {
        return thoiGianNhan;
    }

    public void setThoiGianNhan(LocalDate thoiGianNhan) {
        this.thoiGianNhan = thoiGianNhan;
    }
}