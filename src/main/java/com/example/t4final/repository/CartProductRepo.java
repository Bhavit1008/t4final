package com.example.t4final.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.t4final.model.CartProduct;

public interface CartProductRepo extends JpaRepository<CartProduct,Integer>{

}
