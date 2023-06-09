package com.example.FlipCommerce.repository;

import com.example.FlipCommerce.model.Seller;
import com.example.FlipCommerce.service.SellerService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Integer> {

   Seller findByEmailId(String emailId);

}
