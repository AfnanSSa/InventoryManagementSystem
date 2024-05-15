package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.SupplierDTO;
import com.TRA.tra24Springboot.Models.Supplier;
import com.TRA.tra24Springboot.Services.SupplierServices;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String update(@RequestParam Integer id, @RequestParam Integer quantity) {
        return supplierServices.updateMinimumOrderQuantity(id, quantity);
    }


    //method to remove supplier
    @PostMapping("remove")
    public String remove(@RequestParam Integer id) {
        return supplierServices.remove(id);
    }

    //method to get suppliers
    @GetMapping("get")
    public List<SupplierDTO> getAll(){
        return supplierServices.getSuppliers();
    }
}
