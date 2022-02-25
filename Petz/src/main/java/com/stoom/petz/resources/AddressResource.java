package com.stoom.petz.resources;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stoom.petz.maps.service.MapService;
import com.stoom.petz.models.Address;
import com.stoom.petz.repository.AddressRepository;

@RestController
@RequestMapping(value = "/address")
public class AddressResource {

	@Autowired
	AddressRepository addressRepository;

	@GetMapping("/")
	public List<Address> listAddress() {
		return addressRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Address> findById(@PathVariable(value = "id") long id) {
		return addressRepository.findById(id);
	}

	@PostMapping("/add")
	public Address addAddress(@Valid @RequestBody Address address) {
		if(address.getLatitude() == null || address.getLongitude() == null)
			MapService.getInstance().returnAddressWithGeoPosition(address);
		return addressRepository.save(address);
	}

	@PutMapping("/update")
	public Address updateAddress(@Valid @RequestBody Address address) {
		if(address.getLatitude() == null || address.getLongitude() == null)
			MapService.getInstance().returnAddressWithGeoPosition(address);
		return addressRepository.save(address);
	}

	@DeleteMapping("/delete")
	public void deleteAddress(@Valid @RequestBody Address address) {
		addressRepository.delete(address);
	}

}
