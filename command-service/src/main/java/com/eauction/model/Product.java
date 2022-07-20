package com.eauction.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name="product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @NotNull
    @Size(min = 5, max = 30, message = "Product Name must be between 5 and 30 characters")
    private String productName;
    private String shortPrdDesc;
    private String prdDesc;
    private String prdCategory;
    private double startPrice;
    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bidEndDate;
   // @ManyToOne
 //   @JoinColumn(name="seller_id", nullable = false, updatable = true, insertable = true, referencedColumnName = "id")
//    private Seller seller;
   // private long seller_id;
}
