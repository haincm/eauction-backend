package com.eauction.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name="buyer")
public class Buyer implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @NotNull
    @Size(min = 5, max = 30, message = "First Name must be between 5 and 30 characters")
    private String firstName;
    @NotNull
    @Size(min = 3, max = 30, message = "Last Name must be between 5 and 30 characters")
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String pin;
    @NotBlank(message = "mobileNumber is required")
    @Size(min = 10, max = 10)
    public String phone;
    //@Column(unique=true)
    @Email(message = "Email should be valid")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "buyerId", referencedColumnName = "id")
    private Set<BuyerProduct> buyers=new HashSet<BuyerProduct>();

}



