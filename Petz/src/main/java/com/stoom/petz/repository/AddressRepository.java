package com.stoom.petz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stoom.petz.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
