package com.zerog.retail.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TX_BILL_ITEM")
public class BillItemEntity {

	public enum BillItemType {
		GROCERY(1, "Grocery"), ELECTRONICS(2, "Electronics"), HEALTH(3, "Health"), TOYS(4, "Toys");

		private final Integer billItemTypeId;
		private final String billItemTypeName;

		private BillItemType(Integer billItemTypeId, String billItemTypeName) {
			this.billItemTypeId = billItemTypeId;
			this.billItemTypeName = billItemTypeName;
		}

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer billItemId;

	@NotNull
	@Column(nullable = false)
	private Double itemPrice;

	@ManyToOne
	@JoinColumn(name = "bill_id", nullable = false)
	private BillEntity parentBill;

	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private BillItemType billItemType;

	public Integer getBillItemId() {
		return billItemId;
	}

	public void setBillItemId(Integer billItemId) {
		this.billItemId = billItemId;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public BillEntity getParentBill() {
		return parentBill;
	}

	public void setParentBill(BillEntity parentBill) {
		this.parentBill = parentBill;
	}

	public BillItemType getBillItemType() {
		return billItemType;
	}

	public void setBillItemType(BillItemType billItemType) {
		this.billItemType = billItemType;
	}

	@Override
	public String toString() {
		return "BillItemEntity [billItemId=" + billItemId + ", itemPrice=" + itemPrice + ", parentBill=" + parentBill
				+ ", billItemType=" + billItemType + "]";
	}
	
	

}
