package com.zerog.retail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zerog.retail.repositories.BillRepository;
import com.zerog.retail.services.BillService;

@RestController
@RequestMapping("bill")
public class BillsController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BillService billService;

	@RequestMapping(value = "price/{billId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getBillPrice(@PathVariable(name = "billId") Integer billId) {
		try {
			Double payablePrice = billService.findAndCalculateBillPrice(billId);
			return ResponseEntity.ok("Payable price is: " + payablePrice);

		} catch (Throwable thr) {
			logger.error("Error occured while retrieving bill Payable price", thr);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occured, please try again later");
		}
	}

}
