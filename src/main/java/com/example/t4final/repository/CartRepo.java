package com.example.t4final.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.t4final.model.Cart;

public interface CartRepo extends JpaRepository<Cart,Integer> {

}
