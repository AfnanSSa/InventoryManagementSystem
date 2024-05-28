package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.InventoryDTO;
import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Services.InventoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class  InventoryController {
    @Autowired
    InventoryServices inventoryServices;


    //method for receiving new stock
    @PostMapping("receive")
    public Inventory receiveNewStock(Inventory inventory){
        return inventoryServices.receiveNewStock(inventory);
    }

    //method for returns
/*    @PutMapping("return")
    //method to returns
    public Inventory returns(@RequestParam Integer id) {
        return inventoryServices.returns(id);
    }*/

    //method of write-offs
    @PutMapping("write")
    public Inventory writeOff(@RequestParam Integer id){
        return inventoryServices.writeOff(id);
    }

    @GetMapping("get")
    //method to get the inventory
    public List<InventoryDTO> get(){
        return inventoryServices.getInventory();
    }

    @GetMapping("getById")
    public Inventory getInventoryById(@RequestParam Integer inventoryID){
        return inventoryServices.getInventoryById(inventoryID);
    }

    @GetMapping("getByAvailability")
    public List<Inventory> getInventoryByAvailability(@RequestParam Boolean isActive){
        return inventoryServices.getInventoryByAvailability(isActive);
    }

    @GetMapping("getByLocation")
    public List<Inventory> getInventoryByLocation(@RequestParam String location){
        return inventoryServices.getInventoryByLocation(location);
    }

}