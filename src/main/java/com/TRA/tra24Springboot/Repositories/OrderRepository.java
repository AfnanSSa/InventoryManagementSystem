package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository <Order, Integer> {

    //Query to get order by ID 
    @Query("SELECT o FROM Order o WHERE o.id =:orderID")
    Order getOrderById(@Param("orderID") Integer id);

}
