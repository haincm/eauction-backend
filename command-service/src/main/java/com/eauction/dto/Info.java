package com.eauction.dto;

import lombok.Data;

@Data
public class Info {
    private String email;
    private double bidAmount;
    private long productId;

    public Info(String email, double bidAmount, long productId) {
        this.email = email;
        this.bidAmount = bidAmount;
        this.productId = productId;
    }
}
