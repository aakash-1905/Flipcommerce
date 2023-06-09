package com.example.FlipCommerce.repository;

import com.example.FlipCommerce.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,Integer> {


    @Query(value = "SELECT MAX(total_value) FROM order_info",nativeQuery = true)
    int getMaxOrderValue();

    @Query(value = "SELECT * FROM order_info where order_info.total_value = :max",nativeQuery = true)
    List<OrderEntity> getAllOrderWithMaxOrderValue(int max);

    @Query(value = "SELECT * FROM order_info ORDER BY total_value DESC;",nativeQuery = true)
    List<OrderEntity> getAllOrderDescendingByOrderValue();
}
