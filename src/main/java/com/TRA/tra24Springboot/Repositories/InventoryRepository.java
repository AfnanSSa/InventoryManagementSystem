package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryRepository extends JpaRepository <Inventory, Integer> {

    //Query to get inventory by ID
    @Query("SELECT i FROM Inventory i WHERE i.id =:inventoryID")
    Inventory getInventoryById(@Param("inventoryID") Integer inventoryID);

}
