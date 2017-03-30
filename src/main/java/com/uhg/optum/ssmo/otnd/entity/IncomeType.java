package com.uhg.optum.ssmo.otnd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Income_Type")
public class IncomeType {

	public IncomeType() {
		super();
	}

	public IncomeType(String id) {
		this.id = id;
	}

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "desc_")
	private String desc;
	@Column(name = "type_")
	private String type;
	@Column(name = "input_type")
	private String inputType;
	@Column(name = "full_desc")
	private String fullDesc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getFullDesc() {
		return fullDesc;
	}

	public void setFullDesc(String fullDesc) {
		this.fullDesc = fullDesc;
	}

}
