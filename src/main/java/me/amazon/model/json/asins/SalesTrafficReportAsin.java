package me.amazon.model.json.asins;

import lombok.Data;

@Data
public class SalesTrafficReportAsin {
    private String parentAsin;
    private SalesByAsin salesByAsin;
    private TrafficByAsin trafficByAsin;
}