package com.eauction.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)

public class BidProduct {

    private Product product;
    private double bidAmount;
    private Buyer buyer;
  //  private Seller seller;

}
