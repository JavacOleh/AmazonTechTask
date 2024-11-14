package me.amazon.model.json.dates;

import lombok.Data;

@Data
public class SalesTrafficReportDate {
    private String date;
    private SalesByDate salesByDate;
    private TrafficByDate trafficByDate;
}