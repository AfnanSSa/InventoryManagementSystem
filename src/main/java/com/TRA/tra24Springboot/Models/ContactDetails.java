package com.TRA.tra24Springboot.Models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class ContactDetails extends BaseEntity {

    String email;
    String phoneNumber;
    String faxNumber;
    String address;
    String postalCode;

}
