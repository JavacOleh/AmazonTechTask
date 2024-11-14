package me.amazon.model.json.dates;

import lombok.Data;
import me.amazon.model.json.common.OrderedProductSales;

@Data
public class SalesByDate {
    private OrderedProductSales orderedProductSales;
    private OrderedProductSales orderedProductSalesB2B;
    private Integer unitsOrdered;
    private Integer unitsOrderedB2B;
    private Integer totalOrderItems;
    private Integer totalOrderItemsB2B;
    private AverageSalesPerOrderItem averageSalesPerOrderItem;
    private AverageSalesPerOrderItem averageSalesPerOrderItemB2B;
    private Double averageUnitsPerOrderItem;
    private Double averageUnitsPerOrderItemB2B;
    private AverageSellingPrice averageSellingPrice;
    private AverageSellingPrice averageSellingPriceB2B;
    private Integer unitsRefunded;
    private Double refundRate;
    private Integer claimsGranted;
    private ClaimsAmount claimsAmount;
    private ShippedProductSales shippedProductSales;
    private Integer unitsShipped;
    private Integer ordersShipped;
}