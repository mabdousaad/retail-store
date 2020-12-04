package com.zerog.retail;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zerog.retail.application.RetailStore;
import com.zerog.retail.entities.BillEntity;
import com.zerog.retail.entities.BillItemEntity;
import com.zerog.retail.entities.BillItemEntity.BillItemType;
import com.zerog.retail.entities.UserEntity;
import com.zerog.retail.entities.UserEntity.UserType;
import com.zerog.retail.repositories.BillRepository;
import com.zerog.retail.services.BillService;
import com.zerog.retail.services.impl.BillServiceImpl;

@SpringBootTest(classes = RetailStore.class)
public class BillServiceTest {

	@Autowired
	private BillService billService;

	@InjectMocks
	private BillService billServiceMock = new BillServiceImpl();

	@Mock
	private BillRepository billRepository;

	@Test
	public void test_findAndCalculate() {
		BillEntity bill = new BillEntity();
		bill.setBillId(1);
		UserEntity owner = new UserEntity();
		owner.setUserId(1);
		owner.setEmail("mabdousaad@gmail.com");
		owner.setUsername("mabdousaad");
		owner.setUserType(UserType.EMPLOYEE);
		owner.setJoinDate(new Date());
		bill.setOwner(owner);
		bill.setBillItems(new ArrayList<BillItemEntity>());
		BillItemEntity item1 = new BillItemEntity();
		item1.setBillItemId(1);
		item1.setBillItemType(BillItemType.GROCERY);
		item1.setItemPrice(300d);
		item1.setParentBill(bill);
		bill.getBillItems().add(item1);
		owner.setBills(new ArrayList<BillEntity>());
		owner.getBills().add(bill);
		Mockito.when(billRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(bill));

		Double discountedPrice = billServiceMock.findAndCalculateBillPrice(1);

		assertEquals(285d, discountedPrice);
	}

	@Test
	public void test_employee_discount_grocery() {
		BillEntity bill = new BillEntity();
		bill.setBillId(1);
		UserEntity owner = new UserEntity();
		owner.setUserId(1);
		owner.setEmail("mabdousaad@gmail.com");
		owner.setUsername("mabdousaad");
		owner.setUserType(UserType.EMPLOYEE);
		owner.setJoinDate(new Date());
		bill.setOwner(owner);
		bill.setBillItems(new ArrayList<BillItemEntity>());
		BillItemEntity item1 = new BillItemEntity();
		item1.setBillItemId(1);
		item1.setBillItemType(BillItemType.GROCERY);
		item1.setItemPrice(300d);
		item1.setParentBill(bill);
		bill.getBillItems().add(item1);
		owner.setBills(new ArrayList<BillEntity>());
		owner.getBills().add(bill);

		Double discountedPrice = billService.getDiscountedPrice(bill);
		assertEquals(285d, discountedPrice);
	}

	@Test
	public void test_employee_discount_not_grocery() {
		BillEntity bill = new BillEntity();
		bill.setBillId(1);
		UserEntity owner = new UserEntity();
		owner.setUserId(1);
		owner.setEmail("mabdousaad@gmail.com");
		owner.setUsername("mabdousaad");
		owner.setUserType(UserType.EMPLOYEE);
		owner.setJoinDate(new Date());
		bill.setOwner(owner);
		bill.setBillItems(new ArrayList<BillItemEntity>());
		BillItemEntity item1 = new BillItemEntity();
		item1.setBillItemId(1);
		item1.setBillItemType(BillItemType.ELECTRONICS);
		item1.setItemPrice(300d);
		item1.setParentBill(bill);
		bill.getBillItems().add(item1);
		owner.setBills(new ArrayList<BillEntity>());
		owner.getBills().add(bill);

		Double discountedPrice = billService.getDiscountedPrice(bill);
		assertEquals(195d, discountedPrice);
	}

	@Test
	public void test_affiliate_discount_grocery() {
		BillEntity bill = new BillEntity();
		bill.setBillId(1);
		UserEntity owner = new UserEntity();
		owner.setUserId(1);
		owner.setEmail("mabdousaad@gmail.com");
		owner.setUsername("mabdousaad");
		owner.setUserType(UserType.AFFILIATE);
		owner.setJoinDate(new Date());
		bill.setOwner(owner);
		bill.setBillItems(new ArrayList<BillItemEntity>());
		BillItemEntity item1 = new BillItemEntity();
		item1.setBillItemId(1);
		item1.setBillItemType(BillItemType.GROCERY);
		item1.setItemPrice(300d);
		item1.setParentBill(bill);
		bill.getBillItems().add(item1);
		owner.setBills(new ArrayList<BillEntity>());
		owner.getBills().add(bill);

		Double discountedPrice = billService.getDiscountedPrice(bill);
		assertEquals(285d, discountedPrice);
	}

	@Test
	public void test_affiliate_discount_not_grocery() {
		BillEntity bill = new BillEntity();
		bill.setBillId(1);
		UserEntity owner = new UserEntity();
		owner.setUserId(1);
		owner.setEmail("mabdousaad@gmail.com");
		owner.setUsername("mabdousaad");
		owner.setUserType(UserType.AFFILIATE);
		owner.setJoinDate(new Date());
		bill.setOwner(owner);
		bill.setBillItems(new ArrayList<BillItemEntity>());
		BillItemEntity item1 = new BillItemEntity();
		item1.setBillItemId(1);
		item1.setBillItemType(BillItemType.ELECTRONICS);
		item1.setItemPrice(300d);
		item1.setParentBill(bill);
		bill.getBillItems().add(item1);
		owner.setBills(new ArrayList<BillEntity>());
		owner.getBills().add(bill);

		Double discountedPrice = billService.getDiscountedPrice(bill);
		assertEquals(255d, discountedPrice);
	}

	@Test
	public void test_customer_discount_grocery_olderthantwoyears() {
		BillEntity bill = new BillEntity();
		bill.setBillId(1);
		UserEntity owner = new UserEntity();
		owner.setUserId(1);
		owner.setEmail("mabdousaad@gmail.com");
		owner.setUsername("mabdousaad");
		owner.setUserType(UserType.CUSTOMER);
		owner.setJoinDate(Date.from(LocalDate.parse("2016-02-14").atStartOfDay(ZoneId.systemDefault()).toInstant()));
		bill.setOwner(owner);
		bill.setBillItems(new ArrayList<BillItemEntity>());
		BillItemEntity item1 = new BillItemEntity();
		item1.setBillItemId(1);
		item1.setBillItemType(BillItemType.GROCERY);
		item1.setItemPrice(300d);
		item1.setParentBill(bill);
		bill.getBillItems().add(item1);
		owner.setBills(new ArrayList<BillEntity>());
		owner.getBills().add(bill);

		Double discountedPrice = billService.getDiscountedPrice(bill);
		assertEquals(285d, discountedPrice);
	}

	@Test
	public void test_customer_discount_not_grocery_olderthantwoyears() {
		BillEntity bill = new BillEntity();
		bill.setBillId(1);
		UserEntity owner = new UserEntity();
		owner.setUserId(1);
		owner.setEmail("mabdousaad@gmail.com");
		owner.setUsername("mabdousaad");
		owner.setUserType(UserType.CUSTOMER);
		owner.setJoinDate(Date.from(LocalDate.parse("2016-02-14").atStartOfDay(ZoneId.systemDefault()).toInstant()));
		bill.setOwner(owner);
		bill.setBillItems(new ArrayList<BillItemEntity>());
		BillItemEntity item1 = new BillItemEntity();
		item1.setBillItemId(1);
		item1.setBillItemType(BillItemType.ELECTRONICS);
		item1.setItemPrice(300d);
		item1.setParentBill(bill);
		bill.getBillItems().add(item1);
		owner.setBills(new ArrayList<BillEntity>());
		owner.getBills().add(bill);

		Double discountedPrice = billService.getDiscountedPrice(bill);
		assertEquals(270d, discountedPrice);
	}

	@Test
	public void test_customer_discount_not_grocery_newerthantwoyears() {
		BillEntity bill = new BillEntity();
		bill.setBillId(1);
		UserEntity owner = new UserEntity();
		owner.setUserId(1);
		owner.setEmail("mabdousaad@gmail.com");
		owner.setUsername("mabdousaad");
		owner.setUserType(UserType.CUSTOMER);
		owner.setJoinDate(new Date());
		bill.setOwner(owner);
		bill.setBillItems(new ArrayList<BillItemEntity>());
		BillItemEntity item1 = new BillItemEntity();
		item1.setBillItemId(1);
		item1.setBillItemType(BillItemType.ELECTRONICS);
		item1.setItemPrice(300d);
		item1.setParentBill(bill);
		bill.getBillItems().add(item1);
		owner.setBills(new ArrayList<BillEntity>());
		owner.getBills().add(bill);

		Double discountedPrice = billService.getDiscountedPrice(bill);
		assertEquals(285d, discountedPrice);
	}

	@Test
	public void test_customer_discount_grocery_newerthantwoyears() {
		BillEntity bill = new BillEntity();
		bill.setBillId(1);
		UserEntity owner = new UserEntity();
		owner.setUserId(1);
		owner.setEmail("mabdousaad@gmail.com");
		owner.setUsername("mabdousaad");
		owner.setUserType(UserType.CUSTOMER);
		owner.setJoinDate(new Date());
		bill.setOwner(owner);
		bill.setBillItems(new ArrayList<BillItemEntity>());
		BillItemEntity item1 = new BillItemEntity();
		item1.setBillItemId(1);
		item1.setBillItemType(BillItemType.GROCERY);
		item1.setItemPrice(300d);
		item1.setParentBill(bill);
		bill.getBillItems().add(item1);
		owner.setBills(new ArrayList<BillEntity>());
		owner.getBills().add(bill);

		Double discountedPrice = billService.getDiscountedPrice(bill);
		assertEquals(285d, discountedPrice);
	}

}
