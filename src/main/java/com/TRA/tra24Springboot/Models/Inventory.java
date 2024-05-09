package com.TRA.tra24Springboot.Models;

import lombok.Data;

import java.util.List;

@Data
public class Inventory extends BaseEntity {

    List<Product> products;
    String location;
    String admin;
    List<String> workers;
    String supplier;
    String phoneNumber;
    String openingHours;
    String closingHours;



}
