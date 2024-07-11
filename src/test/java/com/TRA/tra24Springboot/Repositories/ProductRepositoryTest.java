package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    private UUID sku;
    @BeforeEach
    void setupProduct(){
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

        productRepository.save(product);
    }
/*    @Test
    void testGetProductByID() {
        Product product = productRepository.getProductByID();
    }*/

    @Test
    void getProductByName() {
        List<Product> product = productRepository.getProductByName("Screen");
        assertThat(product).isNotNull();
        assertThat(product.size()).isEqualTo(1);
    }

    @Test
    void getProductByCountryOfOrigin() {
        List<Product> productsFromCountry = productRepository.getProductByCountryOfOrigin("Japan");
        assertThat(productsFromCountry).isNotNull();
        assertThat(productsFromCountry.size()).isEqualTo(1);
        assertThat(productsFromCountry.get(0).getProductDetails().getCountryOfOrigin()).isEqualTo("Japan");
    }

    @Test
    void getProductBySize() {
        List<Product> productsOfSize = productRepository.getProductBySize("24 inches");
        assertThat(productsOfSize).isNotNull();
        assertThat(productsOfSize.size()).isEqualTo(1);
        assertThat(productsOfSize.get(0).getProductDetails().getSize()).isEqualTo("24 inches");
    }

    @Test
    void getProductByColor() {
        List<Product> productsOfColor = productRepository.getProductByColor("Black");
        assertThat(productsOfColor).isNotNull();
        assertThat(productsOfColor.size()).isEqualTo(1);
        assertThat(productsOfColor.get(0).getProductDetails().getColor()).isEqualTo("Black");
    }

    @Test
    void getProductBySKU() {
        Product product = productRepository.getProductBySKU(sku);
        assertThat(product).isNotNull();
        assertThat(product.getSku()).isEqualTo(sku);
        assertThat(product.getProductDetails().getName()).isEqualTo("Screen");
    }

    /*@Test
    void getProductByCategory() {
    }

    @Test
    void getProductByPrice() {
    }

    @Test
    void getProductByAvailability() {
    }

    @Test
    void getProductByQuantity() {
    }*/
}