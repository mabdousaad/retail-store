package com.zerog.retail.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ADM_USERS")
public class UserEntity {

	public enum UserType {
		EMPLOYEE(1, "Employee"), AFFILIATE(2, "Affiliate"), CUSTOMER(3, "Customer");

		private final Integer userTypeId;
		private final String userTypeName;

		private UserType(Integer userTypeId, String userTypeName) {
			this.userTypeId = userTypeId;
			this.userTypeName = userTypeName;
		}

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;

	@NotNull
	@Column(nullable = false, unique = true)
	private String username;

	@NotNull
	@Column(nullable = false, unique = true)
	private String email;

	@NotNull
	@Column(nullable = false)
	private Date joinDate;

	@OneToMany(mappedBy = "owner")
	private List<BillEntity> bills;

	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserType userType;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public List<BillEntity> getBills() {
		return bills;
	}

	public void setBills(List<BillEntity> bills) {
		this.bills = bills;
	}

}
