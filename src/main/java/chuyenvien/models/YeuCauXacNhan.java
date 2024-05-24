package chuyenvien.models;

public class YeuCauXacNhan {

	private CacLoaiGiay cacLoaiGiay;
	private Giayxacnhan giayxacnhan;
	
	
	private ThongTinSV thongTinSV;
	

	
	
	
	
	
	public YeuCauXacNhan(CacLoaiGiay cacLoaiGiay, Giayxacnhan giayxacnhan, ThongTinSV thongTinSV) {
		super();
		this.cacLoaiGiay = cacLoaiGiay;
		this.giayxacnhan = giayxacnhan;
		this.thongTinSV = thongTinSV;
	}
	
	public ThongTinSV getThongTinSV() {
		return thongTinSV;
	}
	public void setThongTinSV(ThongTinSV thongTinSV) {
		this.thongTinSV = thongTinSV;
	}
	
	
	
	
	
	
	
	
	
	
	public YeuCauXacNhan() {
		super();
	}
	public YeuCauXacNhan(CacLoaiGiay cacLoaiGiay, Giayxacnhan giayxacnhan) {
		super();
		this.cacLoaiGiay = cacLoaiGiay;
		this.giayxacnhan = giayxacnhan;
	}
	public CacLoaiGiay getCacLoaiGiay() {
		return cacLoaiGiay;
	}
	public void setCacLoaiGiay(CacLoaiGiay cacLoaiGiay) {
		this.cacLoaiGiay = cacLoaiGiay;
	}
	public Giayxacnhan getGiayxacnhan() {
		return giayxacnhan;
	}
	public void setGiayxacnhan(Giayxacnhan giayxacnhan) {
		this.giayxacnhan = giayxacnhan;
	}
	
	
	
}
