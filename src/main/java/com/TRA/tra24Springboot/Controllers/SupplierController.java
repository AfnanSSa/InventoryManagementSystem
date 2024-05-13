package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
