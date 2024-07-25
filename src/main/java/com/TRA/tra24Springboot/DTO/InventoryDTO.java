package com.TRA.tra24Springboot.DTO;

import com.TRA.tra24Springboot.Models.Inventory;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InventoryDTO {
    List<SupplierDTO> suppliers;
    String location;
    String phoneNumber;
    String admin;

    public static InventoryDTO convertToDTO(Inventory inventory){
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setAdmin(inventory.getAdmin());
        inventoryDTO.setPhoneNumber(inventory.getPhoneNumber());
        inventoryDTO.setLocation(inventory.getLocation());
        inventoryDTO.setSuppliers(SupplierDTO.convertToDTOList(inventory.getSupplier()));

        return inventoryDTO;
    }

    public static List<InventoryDTO> convertToDTOList(List<Inventory> inventories){
        List<InventoryDTO> inventoryDTOs = new ArrayList<>();
        for (Inventory inventory : inventories){
            inventoryDTOs.add(convertToDTO(inventory));
        }
        return inventoryDTOs;
    }
}
