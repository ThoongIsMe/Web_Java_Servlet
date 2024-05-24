package chuyenvien.models;

public class TaiKhoan {
	private String usernameString;
	private String pasString;
	private String roleString;
	private int TinhTrang;
	
	public TaiKhoan() {
		// TODO Auto-generated constructor stub
	}

	public TaiKhoan(String usernameString, String pasString, String roleString, int tinhTrang) {
		super();
		this.usernameString = usernameString;
		this.pasString = pasString;
		this.roleString = roleString;
		TinhTrang = tinhTrang;
	}

	public TaiKhoan(String usernameString, String pasString, String roleString) {
		super();
		this.usernameString = usernameString;
		this.pasString = pasString;
		this.roleString = roleString;

	}
	public String getUsernameString() {
		return usernameString;
	}

	public void setUsernameString(String usernameString) {
		this.usernameString = usernameString;
	}

	public String getPasString() {
		return pasString;
	}

	public void setPasString(String pasString) {
		this.pasString = pasString;
	}

	public String getRoleString() {
		return roleString;
	}

	public void setRoleString(String roleString) {
		this.roleString = roleString;
	}

	public int getTinhTrang() {
		return TinhTrang;
	}

	public void setTinhTrang(int tinhTrang) {
		TinhTrang = tinhTrang;
	}
	
	

}
