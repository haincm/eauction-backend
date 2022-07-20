package com.eauctionquery.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Document
public class BidProduct {
    @Id
    private String _id;
    private Product product;
    private double bidAmount;
    private Buyer buyer;
  //  private Seller seller;

}
