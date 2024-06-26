package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.SupplierDTO;
import com.TRA.tra24Springboot.Models.PaymentType;
import com.TRA.tra24Springboot.Models.Supplier;
import com.TRA.tra24Springboot.Services.SupplierServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierServices supplierServices;

    @PostMapping("add")
    //method to add supplier
    public Supplier addSupplier(Supplier supplier) {
        return supplierServices.add(supplier);
    }

    //method to update the supplier
    @PutMapping("update")
    public <T> ResponseEntity<T> update(@RequestParam Integer id, @RequestParam Integer quantity) {
        try {
            String result = supplierServices.updateMinimumOrderQuantity(id, quantity);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            return (ResponseEntity<T>) new ResponseEntity<>("Updating failed!"+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //method to remove supplier
    @PostMapping("remove")
    public <T> ResponseEntity<T> remove(@RequestParam Integer id) {
        try {
            String result = supplierServices.remove(id);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return (ResponseEntity<T>) new ResponseEntity<>("Removing Failed!" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //method to get suppliers
    @GetMapping("get")
    public List<SupplierDTO> getAll(){
        return supplierServices.getSuppliers();
    }

    @GetMapping("getById")
    public Supplier getSupplierBy(@RequestParam Integer supplierID){
        return supplierServices.getSupplierById(supplierID);
    }

    @GetMapping("getByCompanyName")
    public List<Supplier> getSupplierByCompanyName(@RequestParam String companyName){
        return supplierServices.getSupplierByCompanyName(companyName);
    }

    @GetMapping("getByCountry")
    public List<Supplier> getSupplierByCountry(@RequestParam String country){
        return supplierServices.getSupplierByCountry(country);
    }

    @GetMapping("getByMini")
    public List<Supplier> getSupplierByMinimumQty(@RequestParam Integer minimum){
        return supplierServices.getSupplierByMinimumQty(minimum);
    }

    @GetMapping("getByMethod")
    public List<Supplier> getSupplierByShippingMethod (@RequestParam String shippingMethod){
        return supplierServices.getSupplierByShippingMethod(shippingMethod);
    }

    @GetMapping("getByPayment")
    public List<Supplier> getSupplierByPaymentMethod(@RequestParam PaymentType paymentMethods){
        return supplierServices.getSupplierByPaymentMethod(paymentMethods);
    }
}
