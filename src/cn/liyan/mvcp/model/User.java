package cn.liyan.mvcp.model;

import java.util.Date;
/**
 * 
 * 用户类
 * @author Administrator
 *
 */
public class User {
	private int id;
	private String username;
	private String password;
	private String phoneNo;
	private String address;
	private Date regDate;
	/**
	 * 无参构造
	 * 
	 */
	public User() {
		super();
	}
	public User(int id, String username, String password, String phoneNo, String address, Date regDate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.phoneNo = phoneNo;
		this.address = address;
		this.regDate = regDate;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", phoneNo=" + phoneNo
				+ ", address=" + address + ", regDate=" + regDate + "]";
	}
	

}
