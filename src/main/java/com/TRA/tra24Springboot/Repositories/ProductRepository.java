package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository <Product, Integer>{

    //Query to get product by ID
    @Query("SELECT p FROM Product p WHERE p.id =:productID")
    Product getProductByID(@Param("productID") Integer productID);

    //Query to get product by name
    @Query("SELECT p FROM Product p WHERE p.productDetails.name =:product")
    List<Product> getProductByName (@Param("product") String product);


    //Query to get product by country of origin
    @Query ("SELECT p FROM Product p WHERE p.productDetails.countryOfOrigin =:country")
    List<Product> getProductByCountryOfOrigin(@Param("country") String countryOfOrigin);

    //Query to get product by size
    @Query("SELECT p FROM Product p WHERE p.productDetails.size =:size")
    List<Product> getProductBySize(@Param("size") String size);


    //Query to get product by color
    @Query("SELECT p FROM Product p WHERE p.productDetails.color =:color")
    List<Product> getProductByColor(@Param("color") String color);

    //Query to get product by SKU
    @Query("SELECT p FROM Product p WHERE p.sku =:sku")
    Product getProductBySKU(@Param("sku") UUID sku);

    //Query to get product by category
    @Query("SELECT p FROM Product p WHERE p.category =:category")
    List<Product> getProductByCategory(@Param("category") String category);

    //Query to get product by price
    @Query("SELECT p FROM Product p WHERE p.productDetails.price =:price")
    List<Product> getProductByPrice(@Param("price") Double price);





}
