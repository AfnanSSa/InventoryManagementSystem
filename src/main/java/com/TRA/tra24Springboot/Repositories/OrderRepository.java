package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Models.OrderStatus;
import com.TRA.tra24Springboot.Models.PaymentStatus;
import com.TRA.tra24Springboot.Models.PaymentType;
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

    //Query to get order by status
    @Query("SELECT o FROM Order o WHERE o.status =:status")
    List<Order> getOrderByStatus(@Param("status") OrderStatus status);

    //Query to get order by payment status
    @Query("SELECT o FROM Order o WHERE o.paymentStatus =:paymentStatus")
    List<Order> getOrderByPaymentStatus(@Param("paymentStatus")PaymentStatus paymentStatus);

    @Query("SELECT o FROM Order o WHERE o.paymentType =:paymentType")
    List<Order> getOrderByPaymentType(@Param("paymentType") PaymentType paymentType);

}
