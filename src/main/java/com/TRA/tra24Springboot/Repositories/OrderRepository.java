package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository <Order, Integer> {

    //Query to get order by ID
    @Query("SELECT o FROM Order o WHERE o.id =:orderID")
    Order getOrderById(@Param("orderID") Integer id);

    //Query to get order by category name
    @Query("SELECT o FROM Order o WHERE o.categoryName =:categoryName")
    List<Order> getOrderByCategoryName(@Param("categoryName") String categoryName);

}
