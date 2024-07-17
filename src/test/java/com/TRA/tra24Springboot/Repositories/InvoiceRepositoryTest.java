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
    private Date dueDate = DateHelperUtils.addDays(date, 1);
    Integer invoiceId;

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
                .dueDate(dueDate)
                .paymentDate(date)
                .build();
        invoice.setCreatedDate(date);
        invoiceId = invoiceRepository.save(invoice).getId();
    }

    @Test
    void getInvoiceById(){
        Invoice invoiceById = invoiceRepository.findById(invoiceId).orElse(null);
        assertThat(invoiceById).isNotNull();
        assertThat(invoiceById.getId()).isEqualTo(invoiceId);
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
        List<Invoice> invoicesDueDates = invoiceRepository.getInvoiceByDueDate(dueDate);
        assertThat(invoicesDueDates).isNotNull();
        assertThat(invoicesDueDates.size()).isEqualTo(1);
        assertThat(invoicesDueDates.get(0).getDueDate()).isEqualTo(dueDate);
    }

    @Test
    void getInvoicesByDueDateBetween() {
        List<Invoice> invoicesDueDateBetween = invoiceRepository.getInvoicesByDueDateBetween(date, dueDate);
        assertThat(invoicesDueDateBetween).isNotNull();
        assertThat(invoicesDueDateBetween.size()).isEqualTo(1);
    }

     @Test
    void getOverdueInvoices() {
        List<Invoice> overDueInvoices = invoiceRepository.getOverdueInvoices(date);
        assertThat(overDueInvoices).isNotNull();
        assertThat(overDueInvoices.size()).isEqualTo(0);
    }

   @Test
    void getInvoicesCreatedBetween() {
       List<Invoice> invoicesCreatedBetweenDates = invoiceRepository.getInvoicesCreatedBetween(date, DateHelperUtils.addDays(date,3));
       assertThat(invoicesCreatedBetweenDates).isNotNull();
       assertThat(invoicesCreatedBetweenDates.size()).isEqualTo(1);
    }

 @Test
    void getPaidInvoicesBetween() {
     List<Invoice> invoicesPaidBetweenDates = invoiceRepository.getPaidInvoicesBetween(date, DateHelperUtils.addDays(date,3));
     assertThat(invoicesPaidBetweenDates).isNotNull();
     assertThat(invoicesPaidBetweenDates.size()).isEqualTo(1);
    }
}