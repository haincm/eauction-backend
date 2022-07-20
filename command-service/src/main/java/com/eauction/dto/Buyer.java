package com.eauction.dto;

import com.eauction.model.BuyerProduct;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;



@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Buyer implements Serializable {
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



