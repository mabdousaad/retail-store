package com.zerog.retail.repositories;

import org.springframework.data.repository.CrudRepository;

import com.zerog.retail.entities.BillEntity;

public interface BillRepository extends CrudRepository<BillEntity, Integer> {
	

}
