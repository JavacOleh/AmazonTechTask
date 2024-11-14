package me.amazon.model.json.dates;

import lombok.Data;

@Data
public class ClaimsAmount {
    private Double amount;
    private String currencyCode;
}