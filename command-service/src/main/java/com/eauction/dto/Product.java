package com.eauction.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {

    private long id;
    private String productName;
    private String shortPrdDesc;
    private String prdDesc;
    private String prdCategory;
    private double startPrice;
    private LocalDate bidEndDate;
    private long seller_id;
}
