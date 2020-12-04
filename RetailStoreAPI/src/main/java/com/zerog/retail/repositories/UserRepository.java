package com.zerog.retail.repositories;

import org.springframework.data.repository.CrudRepository;

import com.zerog.retail.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

}
