package com.eauctionquery.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Info {
    private String email;
    private double bidAmount;
    private long productId;

    public Info() {
    }

    public Info(String email, double bidAmount, long productId) {
        this.email = email;
        this.bidAmount = bidAmount;
        this.productId = productId;
    }
}
