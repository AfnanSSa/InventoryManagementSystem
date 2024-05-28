package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.PaymentType;
import com.TRA.tra24Springboot.Models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SupplierRepository extends JpaRepository <Supplier, Integer> {

    //Query to get supplier by ID
    @Query("SELECT s FROM Supplier s WHERE s.id =:supplierID")
    Supplier getSupplierById(@Param("supplierID") Integer supplierID);

    //Query to get supplier by company name
    @Query("SELECT s FROM Supplier s WHERE s.companyName =:companyName")
    List<Supplier> getSupplierByCompanyName(@Param("companyName") String companyName);

    //Query to get suppliers by country
    @Query("SELECT s FROM Supplier s WHERE s.country =:country")
    List<Supplier> getSupplierByCountry(@Param("country") String country);

    //Query to get supplier by minimum order quantity
    @Query("SELECT s FROM Supplier s WHERE s.minimumOrderQuantity =:minimumOrderQuantity")
    List<Supplier> getSupplierByMinimumOrderQty (@Param("minimumOrderQuantity") Integer minimumOrderQuantity);

    //Query to get supplier by shipping method
    @Query("SELECT s FROM Supplier s WHERE s.shippingMethods =:shippingMethods")
    List<Supplier> getSupplierByShippingMethod(@Param("shippingMethods") String shippingMethods);

    //Query to get supplier by payment method
    @Query("SELECT s FROM Supplier s WHERE s.paymentMethods =:paymentMethods")
    List<Supplier> getSupplierByPaymentMethod(@Param("paymentMethods") PaymentType paymentMethods);

}
