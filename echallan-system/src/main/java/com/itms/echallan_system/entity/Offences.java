package com.itms.echallan_system.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "offences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Offences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String offence_code;

    private String description;

    private BigDecimal challan_amount;

    private String status;


}
