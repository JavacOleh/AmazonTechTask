package me.amazon.model.json.common;

import lombok.Data;

@Data
public class OrderedProductSales {
    private Double amount;
    private String currencyCode;
}