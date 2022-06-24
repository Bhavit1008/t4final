package com.example.t4final.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.example.t4final.model.Cart;
import com.example.t4final.model.CartProduct;
import com.example.t4final.model.Category;
import com.example.t4final.model.Product;
import com.example.t4final.model.User;
import com.example.t4final.repository.CartProductRepo;
import com.example.t4final.repository.CartRepo;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public")
public class PublicController {
	
	@Autowired
	CartProductRepo cartProductRepo;
	
	@Autowired
	CartRepo cartRepo;
	
	@GetMapping("/product/search")
	public ResponseEntity<List<Product>> searchProduct(@RequestParam(name = "keyword")String keyword){
		if(keyword.isEmpty() || keyword == null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST); 
		}
		Product p1 = new Product("tablet",1200.0,new User(),new Category(),new ArrayList<>());
		Category category = new Category();
		category.setCategoryName("medicine");
		Product p2 = new Product("medicines",1200.0,new User(),category,new ArrayList<>());
		List<Product> list = List.of(p1,p2);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	/*
	 * our api
	 *
	 * */
	
	@RequestMapping(value = "/consumer/cart", method=RequestMethod.GET)
	public ResponseEntity<Cart> consumerAuthEndPoint() {
		List<Cart> cp = cartRepo.findAll();
		return new ResponseEntity<>(cp.get(0),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/seller/product", method=RequestMethod.GET)
	public ResponseEntity<String> sellerAuthEndPoint() {
		return new ResponseEntity<>("",HttpStatus.UNAUTHORIZED);
	}
 }
