package com.zerog.retail.services;

import java.util.Date;

import com.zerog.retail.entities.BillEntity;
import com.zerog.retail.entities.UserEntity;


public interface BillService {
	
	public Double findAndCalculateBillPrice(Integer billId);

	public Double getDiscountedPrice(BillEntity bill);

	public Double getUserDiscountPercentage(UserEntity user);

	public Integer getUserJoiningDurationInYears(Date joinDate);

}
