package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Repositories.ProductDetailsRepository;
import com.TRA.tra24Springboot.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServices {
    @Autowired
    ProductRepository productRepository; //reference to Product repo
    @Autowired
    ProductDetailsRepository productDetailsRepository; //reference to Product Details repo
    ProductDTO productDTO;

    public Product add(Product product) {

        ProductDetails productDetails = new ProductDetails();
        productDetails.setName("Apple");
        productDetails.setColor("Green");
        productDetails.setSize("Small");
        productDetails.setPrice(10d);
        productDetails.setCountryOfOrigin("USA");
        productDetails.setDescription("Apple Product");
        productDetails = productDetailsRepository.save(productDetails);

        product.setProductDetails(productDetails);
        product.setSku(UUID.randomUUID());
        product.setCategory("Electronics");
        product.setQuantity(1);

        product.setIsActive(Boolean.TRUE);
        product.setCreatedDate(new Date());

        return productRepository.save(product);
    }

    public String delete(Integer id) throws Exception {
        try {
            Product product = productRepository.getById(id);
            if (product == null) {
                throw new Exception("Product with ID: " + id + " is not found");
            }
            product.setIsActive(Boolean.FALSE);
            System.out.println(product.toString());
            productRepository.save(product);
            return "Product Deleted Successfully";

        } catch (Exception e) {
            throw new Exception("Failed to delete product: " + e.getMessage());
        }
    }

    public String updateProductQuantity(Integer id, Integer quantity) {
        Product product = productRepository.getById(id);
        product.setQuantity(quantity);
        product.setUpdatedDate(new Date());

        productRepository.save(product);
        return "Updated Successfully";
    }

    public List<ProductDTO> getProduct() {
        List<Product> products = productRepository.findAll();
        return productDTO.convertToDTOList(products);
    }

    public Product getProductByID(Integer productID) {
        return productRepository.getProductByID(productID);
    }

    public List<Product> getProductByName(String productName) {
        return productRepository.getProductByName(productName);
    }

    public List<Product> getProductByCountryOfOrigin(String country) {
        return productRepository.getProductByCountryOfOrigin(country);
    }

    public List<Product> getProductBySize(String size) {
        return productRepository.getProductBySize(size);
    }

    public List<Product> getProductByColor(String color) {
        return productRepository.getProductByColor(color);
    }

    public Product getProductBySKU(UUID sku) {
        return productRepository.getProductBySKU(sku);
    }

    public List<Product> getProductByCategory(String category) {
        return productRepository.getProductByCategory(category);
    }

    public List<Product> getProductByPrice(Double price) {
        return productRepository.getProductByPrice(price);
    }

    public List<Product> getProductByAvailability(Boolean isActive) {
        return productRepository.getProductByAvailability(isActive);
    }

    public List<Product> getProductByQuantity(Integer quantity) {
        return productRepository.getProductByQuantity(quantity);
    }

    public List<Product> getLowStockProducts() {
        List<Product> products = productRepository.findAll();
        List<Product> lowStockProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getQuantity() < 5) {
                lowStockProducts.add(product);
            }
        }
        return lowStockProducts;
    }
}
