package com.example.FlipCommerce.repository;

import com.example.FlipCommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findByEmailId(String emailId);

//    @Query(value = "select * from customer where customer.gender = 'FEMALE' AND customer.age BETWEEN :fromAge AND :toAge",nativeQuery = true)
//    List<Customer> getFemaleCustomerAgeBetween(int fromAge, int toAge);
}
