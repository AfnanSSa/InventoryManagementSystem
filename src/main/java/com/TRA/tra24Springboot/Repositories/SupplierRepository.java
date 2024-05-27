package com.TRA.tra24Springboot.Repositories;

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


}
