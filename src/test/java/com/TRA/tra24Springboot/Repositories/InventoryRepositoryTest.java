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

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class InventoryRepositoryTest {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductDetailsRepository productDetailsRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ContactDetailsRepository contactDetailsRepository;
    private UUID sku;
    private Date date;
    @BeforeEach
    void setupInventory(){
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
        orderRepository.save(order);

        ContactDetails contactDetails = ContactDetails.builder()
                .email("supplier@info.com")
                .address("Tokyo, Chiyoda City, Marunouchi, 1 Chome−1")
                .phoneNumber("+81 3-1234-5678")
                .postalCode("〒100-0005")
                .faxNumber("+81 3-1234-5678")
                .build();
        contactDetailsRepository.save(contactDetails);

        Supplier supplier = Supplier.builder()
                .companyName("Sharp Corporation")
                .country("Japan")
                .expectedProducts(List.of(product))
                .orders(List.of(order))
                .contactDetails(contactDetails)
                .paymentMethods(PaymentType.BANK_TRANSFER)
                .shippingMethods("Freight Shipping")
                .minimumOrderQuantity(1)
                .nextDeliveryTime(DateHelperUtils.addDays(date, 20))
                .build();
        supplierRepository.save(supplier);

        Inventory inventory = Inventory.builder()
                .products(List.of(product))
                .supplier(List.of(supplier))
                .admin("Afnan")
                .location("Muscat")
                .phoneNumber("12345678")
                .workers(Arrays.asList("Tayba", "Noura", "Balqees", "Shaimaa"))
                .openingHours("9 AM")
                .closingHours("9 PM")
                .build();
        inventory.setIsActive(Boolean.TRUE);
        inventoryRepository.save(inventory);
    }

    @Test
    void getInventoryByAvailability() {
        List<Inventory> inventoryAvailability = inventoryRepository.getInventoryByAvailability(Boolean.TRUE);
        assertThat(inventoryAvailability).isNotNull();
        assertThat(inventoryAvailability.size()).isEqualTo(1);
        assertThat(inventoryAvailability.get(0).getIsActive()).isEqualTo(Boolean.TRUE);
    }

/*    @Test
    void getInventoryByLocation() {
    }

    @Test
    void getInventoryByAdminName() {
    }*/
}