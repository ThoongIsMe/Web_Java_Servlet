package quantrivien.models;
import java.io.Serializable;

public class DichVu implements Serializable {
    private static final long serialVersionUID = 1L;
    private String maDV;
    private String tenDV;
    private int tinhTrang;
    private String tinhTrangString;
    
    public DichVu(String maDV, String tenDV, String tinhTrangString) {
        super();
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.tinhTrangString = tinhTrangString;
    }
    
    public DichVu(String maDV, String tenDV, int tinhTrang) {
        super();
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.tinhTrang = tinhTrang;
    }
    
    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }   
    
    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
    
    public String getTinhTrangString() {
        return tinhTrangString;
    }

    public void setTinhTrangString(String tinhTrangString) {
        this.tinhTrangString = tinhTrangString;
    }
}