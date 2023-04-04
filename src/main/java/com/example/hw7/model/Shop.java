package com.example.hw7.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Shop {

    private Long id;
    private String city;
    private String street;
    private String shopName;
    private int employeesCount;
    private boolean hasWebsite;
}
