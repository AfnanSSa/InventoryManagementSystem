package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepositories extends JpaRepository <Supplier, Integer> {
}
