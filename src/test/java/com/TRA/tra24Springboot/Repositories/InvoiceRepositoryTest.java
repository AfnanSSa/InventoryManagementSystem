package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Utils.DateHelperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class InvoiceRepositoryTest {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDetailsRepository productDetailsRepository;

    private UUID sku;
    private Date date = new Date();

    @BeforeEach
    void setupInvoice() {

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

        Invoice invoice = Invoice.builder()
                .products(List.of(product))
                .totalAmount(203.3)
                .dueDate(DateHelperUtils.addDays(date, 15))
                .paymentDate(date)
                .build();
        invoice.setCreatedDate(date);
        invoiceRepository.save(invoice);
    }

    @Test
    void getInvoiceByCreatedDate() {
        List<Invoice> invoicesCreatedDates = invoiceRepository.getInvoiceByCreatedDate(date);
        assertThat(invoicesCreatedDates).isNotNull();
        assertThat(invoicesCreatedDates.size()).isEqualTo(1);
        assertThat(invoicesCreatedDates.get(0).getCreatedDate()).isEqualTo(date);
    }

   @Test
    void getInvoiceByDueDate() {
        List<Invoice> invoicesDueDates = invoiceRepository.getInvoiceByDueDate(DateHelperUtils.addDays(date, 15));
        assertThat(invoicesDueDates).isNotNull();
        assertThat(invoicesDueDates.size()).isEqualTo(1);
        assertThat(invoicesDueDates.get(0).getDueDate()).isEqualTo(DateHelperUtils.addDays(date, 15));
    }

 /*    @Test
    void getInvoicesByDueDateBetween() {
    }

    @Test
    void getOverdueInvoices() {
    }

    @Test
    void getInvoicesCreatedBetween() {
    }

    @Test
    void getPaidInvoicesBetween() {
    }*/
}