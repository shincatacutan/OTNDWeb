package com.uhg.optum.ssmo.otnd.entity;


public class BusinessProcess {
	private int id;
	private String name;
	private String code;
	private Employee admin;
	private Employee spocName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Employee getAdmin() {
		return admin;
	}
	public void setAdmin(Employee admin) {
		this.admin = admin;
	}
	public Employee getSpocName() {
		return spocName;
	}
	public void setSpocName(Employee spocName) {
		this.spocName = spocName;
	}

}
