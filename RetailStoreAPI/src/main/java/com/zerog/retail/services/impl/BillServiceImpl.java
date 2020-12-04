package com.zerog.retail.services.impl;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerog.retail.constants.Constants;
import com.zerog.retail.entities.BillEntity;
import com.zerog.retail.entities.BillItemEntity;
import com.zerog.retail.entities.BillItemEntity.BillItemType;
import com.zerog.retail.entities.UserEntity;
import com.zerog.retail.repositories.BillRepository;
import com.zerog.retail.services.BillService;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillRepository billRepository;

	public Double findAndCalculateBillPrice(Integer billId) {
		Optional<BillEntity> bill = billRepository.findById(billId);
		if(bill.isPresent()) {
			return getDiscountedPrice(bill.get());
		}
		return 0d;
	}

	public Double getDiscountedPrice(BillEntity bill) {

		Double applicableDiscountPrice = 0d;
		Double totalPrice = 0d;
		Double absoluteDiscount = 0d;
		Double payablePrice = 0d;
		try {
			for (BillItemEntity item : bill.getBillItems()) {
				if (!item.getBillItemType().equals(BillItemType.GROCERY)) {
					applicableDiscountPrice += item.getItemPrice();
				}
				totalPrice += item.getItemPrice();
			}
			if (totalPrice > 0) {
				absoluteDiscount = Math.floor(totalPrice / 100)
						* Constants.PRICE_DISCOUNTS.ABSOLUTE_DISCOUNT_MULTIPLIER;
				payablePrice = totalPrice - absoluteDiscount
						- (applicableDiscountPrice * getUserDiscountPercentage(bill.getOwner()));
			}
		} catch (Exception ex) {
			return totalPrice;
		}
		return payablePrice;
	}

	public Double getUserDiscountPercentage(UserEntity user) {
		try {
			switch (user.getUserType()) {
			case EMPLOYEE:
				return Constants.PRICE_DISCOUNTS.EMPLOYEE_DISCOUNT;
			case AFFILIATE:
				return Constants.PRICE_DISCOUNTS.AFFILIATE_DISCOUNT;
			case CUSTOMER:
				return getUserJoiningDurationInYears(user.getJoinDate()) >= 2
						? Constants.PRICE_DISCOUNTS.LOYAL_CUSTOMER_DISCOUNT
						: 0d;
			default:
				return 0d;
			}
		} catch (Exception ex) {
			return 0d;
		}
	}

	public Integer getUserJoiningDurationInYears(Date joinDate) {
		try {
			return Math.abs(
					Period.between(joinDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now())
							.getYears());
		} catch (Exception ex) {
			return 0;
		}

	}
}
