package me.amazon.model.json.asins;

import lombok.Data;
import me.amazon.model.json.common.OrderedProductSales;

@Data
public class SalesByAsin {
    private Integer unitsOrdered;
    private Integer unitsOrderedB2B;
    private OrderedProductSales orderedProductSales;
    private OrderedProductSales orderedProductSalesB2B;
    private Integer totalOrderItems;
    private Integer totalOrderItemsB2B;
}