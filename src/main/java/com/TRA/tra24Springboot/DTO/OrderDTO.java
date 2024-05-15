package com.TRA.tra24Springboot.DTO;

import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Models.OrderStatus;
import com.TRA.tra24Springboot.Models.PaymentStatus;
import com.TRA.tra24Springboot.Models.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    List<ProductDTO> productDTO;
    Integer orderID;
    OrderStatus orderStatus;
    PaymentStatus paymentStatus;
    Date orderDate;

   public static OrderDTO convertToDTO(Order order) {
       OrderDTO orderDTO = new OrderDTO();
       orderDTO.setOrderID(order.getId());
       orderDTO.setOrderDate(order.getOrderDate());
       orderDTO.setOrderStatus(order.getStatus());
       orderDTO.setPaymentStatus(order.getPaymentStatus());
       orderDTO.setProductDTO(ProductDTO.convertToDTOList(order.getProductsOrdered()));

       return orderDTO;
   }

   public static List<OrderDTO> convertToDTOList(List<Order> orders){
       List<OrderDTO> orderDTOS = new ArrayList<>();
       for (Order order:orders){
           orderDTOS.add(convertToDTO(order));
       }
       return orderDTOS;
   }
}
