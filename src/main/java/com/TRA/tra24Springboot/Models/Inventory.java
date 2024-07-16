package com.TRA.tra24Springboot.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inventory extends BaseEntity {

    @OneToMany
    List<Product> products;
    String location;
    String admin;
    List<String> workers;
    @OneToMany
    List<Supplier> supplier;
    String phoneNumber;
    String openingHours;
    String closingHours;


}
