package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.InventoryDTO;
import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Services.InventoryServices;
import com.TRA.tra24Springboot.Services.ReportService;
import com.TRA.tra24Springboot.Services.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class  InventoryController {
    @Autowired
    InventoryServices inventoryServices;
    @Autowired
    ReportService reportService;
    @Autowired
    SlackService slackService;


    //method for receiving new stock
    @PostMapping("receive")
    public Inventory receiveNewStock(Inventory inventory){
        return inventoryServices.receiveNewStock(inventory);
    }

    //method of write-offs
    @PutMapping("write")
    public Inventory writeOff(@RequestParam Integer id){
        return inventoryServices.writeOff(id);
    }

    @Scheduled(cron = "0 0 * * * ?")
    @GetMapping("get")
    //method to get the inventory
    public ResponseEntity<?> get() throws Exception{
        try {
            List<InventoryDTO> inventories = inventoryServices.getInventory();
            StringBuilder inventoryReport = new StringBuilder();

            inventoryReport.append("Hourly Inventory Report");
            for (InventoryDTO inventory : inventories) {
                inventoryReport
                        .append(".....................\n")
                        .append("Inventory Location: ")
                        .append(inventory.getLocation()).append("\n")
                        .append("Admin: ")
                        .append(inventory.getAdmin()).append("\n")
                        .append("Phone number: ")
                        .append(inventory.getPhoneNumber()).append("\n");

            }

            slackService.sendMessage("#afnan", inventoryReport.toString());
            inventoryReport.setLength(0);

            reportService.createSchoolReport();
            return new ResponseEntity<>(inventories, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Retrieving inventories failed! " +
                    e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @GetMapping("getByAdmin")
    public List<Inventory> getInventoryByAdminName(@RequestParam String admin){
        return inventoryServices.getInventoryByAdminName(admin);
    }

}