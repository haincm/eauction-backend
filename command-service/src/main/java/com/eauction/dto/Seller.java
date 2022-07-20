package com.eauction.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;



@Getter
@Setter

public class Seller implements Serializable {

    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String pin;
    public String phone;
    private String email;
}


