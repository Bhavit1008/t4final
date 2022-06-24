package com.example.t4final.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.t4final.model.CartProduct;
import com.example.t4final.repository.CartProductRepo;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	
	@Autowired
	CartProductRepo cartProductRepo;
	
	@RequestMapping(value = "/all", method=RequestMethod.GET)
	public String allAccess() {
		return "Public Content.";
	}
	
	@PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_CONSUMER')")
	@RequestMapping(value = "/seller", method=RequestMethod.GET)
	public String userAccess() {
		return "Seller content.";
	}
	
	@PreAuthorize("hasRole('ROLE_CONSUMER')")
	@RequestMapping(value = "/consumer", method=RequestMethod.GET)
	public ResponseEntity<List<CartProduct>> moderatorAccess() {
		List<CartProduct> cp = cartProductRepo.findAll();
		return new ResponseEntity<>(cp,HttpStatus.OK);
	}
	

	
}
