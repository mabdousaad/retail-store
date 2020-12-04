package com.zerog.retail.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TX_BILL")
public class BillEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer billId;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity owner;

	@OneToMany(mappedBy = "parentBill")
	private List<BillItemEntity> billItems;

	private Date issueDate;

	private Date paymentDate;

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public UserEntity getOwner() {
		return owner;
	}

	public void setOwner(UserEntity owner) {
		this.owner = owner;
	}

	public List<BillItemEntity> getBillItems() {
		return billItems;
	}

	public void setBillItems(List<BillItemEntity> billItems) {
		this.billItems = billItems;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public String toString() {
		return "BillEntity [billId=" + billId + ", owner=" + owner + ", billItems=" + billItems + ", issueDate="
				+ issueDate + ", paymentDate=" + paymentDate + "]";
	}

}
