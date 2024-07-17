package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Utils.DateHelperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductDetailsRepository productDetailsRepository;
    private UUID sku;
    private Date date;
    Integer orderId;

    @BeforeEach
    void setupOrder(){
        ProductDetails productDetails = ProductDetails.builder()
                .name("Screen")
                .color("Black")
                .countryOfOrigin("Japan")
                .price(200.0)
                .size("24 inches")
                .build();
        productDetailsRepository.save(productDetails);

        sku = UUID.randomUUID();

        Product product = Product.builder()
                .productDetails(productDetails)
                .category("Electronics")
                .quantity(50)
                .sku(sku)
                .build();
        product.setIsActive(Boolean.TRUE);
        productRepository.save(product);

        date = new Date();
        Order order = Order.builder()
                .productsOrdered(List.of(product))
                .categoryName("Electronics")
                .paymentType(PaymentType.BANK_TRANSFER)
                .status(OrderStatus.COMPLETED)
                .paymentStatus(PaymentStatus.PAID)
                .orderDate(new Date())
                .dueDate(DateHelperUtils.addDays(date, 7))
                .build();
        orderId = orderRepository.save(order).getId();
    }
    
    @Test
    void getOrderById(){
        Order orderById = orderRepository.findById(orderId).orElse(null);
        assertThat(orderById).isNotNull();
        assertThat(orderById.getId()).isEqualTo(orderId);
    }

    @Test
    void getOrderByCategoryName() {
        List<Order> orderCategory = orderRepository.getOrderByCategoryName("Electronics");
        assertThat(orderCategory).isNotNull();
        assertThat(orderCategory.size()).isEqualTo(1);
        assertThat(orderCategory.get(0).getCategoryName()).isEqualTo("Electronics");
    }

    @Test
    void getOrderByStatus() {
        List<Order> orderStatus = orderRepository.getOrderByStatus(OrderStatus.COMPLETED);
        assertThat(orderStatus).isNotNull();
        assertThat(orderStatus.size()).isEqualTo(1);
        assertThat(orderStatus.get(0).getStatus()).isEqualTo(OrderStatus.COMPLETED);
    }

    @Test
    void getOrderByPaymentStatus() {
        List<Order> orderPaymentStatus = orderRepository.getOrderByPaymentStatus(PaymentStatus.PAID);
        assertThat(orderPaymentStatus).isNotNull();
        assertThat(orderPaymentStatus.size()).isEqualTo(1);
        assertThat(orderPaymentStatus.get(0).getPaymentStatus()).isEqualTo(PaymentStatus.PAID);
    }

    @Test
    void getOrderByPaymentType() {
        List<Order> orderPaymentType = orderRepository.getOrderByPaymentType(PaymentType.BANK_TRANSFER);
        assertThat(orderPaymentType).isNotNull();
        assertThat(orderPaymentType.size()).isEqualTo(1);
        assertThat(orderPaymentType.get(0).getPaymentType()).isEqualTo(PaymentType.BANK_TRANSFER);
    }
}