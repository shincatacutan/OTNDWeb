package com.uhg.optum.ssmo.otnd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
@Table(name = "Payroll_Details")
public class PayrollDetails {


	public PayrollDetails() {
		super();
	}

	public PayrollDetails(PayrollPeriod payrollPeriod) {
		super();
		this.payrollPeriod = payrollPeriod;
	}

	public PayrollDetails( Employee empId, IncomeType incomeType,
			String prodHrsAmt, String remarks, LocalDate createDate,
			PayrollPeriod payrollPeriod) {
		super();
		this.empId = empId;
		this.incomeType = incomeType;
		this.prodHrsAmt = prodHrsAmt;
		this.remarks = remarks;
		this.createDate = createDate;
		this.payrollPeriod = payrollPeriod;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@OneToOne()
	@JoinColumn(name = "empID")
	private Employee empId;

	@OneToOne()
	@JoinColumn(name = "incomeId")
	private IncomeType incomeType;

	@Column(name = "prodHrsAmt")
	private String prodHrsAmt;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "createDate")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate createDate;

	@OneToOne()
	@JoinColumn(name = "payID")
	private PayrollPeriod payrollPeriod;

	@Column(name = "approvalStatus")
	private String status;

	@Column(name = "approver")
	private String approver;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getEmpId() {
		return empId;
	}

	public void setEmpId(Employee empId) {
		this.empId = empId;
	}

	public IncomeType getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(IncomeType incomeType) {
		this.incomeType = incomeType;
	}

	public String getProdHrsAmt() {
		return prodHrsAmt;
	}

	public void setProdHrsAmt(String prodHrsAmt) {
		this.prodHrsAmt = prodHrsAmt;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public PayrollPeriod getPayrollPeriod() {
		return payrollPeriod;
	}

	public void setPayrollPeriod(PayrollPeriod payrollPeriod) {
		this.payrollPeriod = payrollPeriod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

}
