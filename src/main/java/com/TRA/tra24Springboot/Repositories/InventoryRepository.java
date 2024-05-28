package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends JpaRepository <Inventory, Integer> {

    //Query to get inventory by ID
    @Query("SELECT i FROM Inventory i WHERE i.id =:inventoryID")
    Inventory getInventoryById(@Param("inventoryID") Integer inventoryID);

    //Query to git inventory by availability
    @Query("SELECT i FROM Inventory i WHERE i.isActive =:isActive")
    List<Inventory> getInventoryByAvailability(@Param("isActive") Boolean isActive);

    //Query to get inventory by location
    @Query("SELECT i FROM Inventory i WHERE i.location =:location")
    List<Inventory> getInventoryByLocation(@Param("location") String location);


}
