package me.amazon.model.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.amazon.model.json.dates.SalesTrafficReportDate;
import me.amazon.model.json.dates.SalesByDate;
import me.amazon.model.json.dates.TrafficByDate;
import org.springframework.data.annotation.Id;

@Data
public class SalesTrafficReportMongoDates {
    @Id
    private String id;

    @JsonProperty("date")
    private String date;

    @JsonProperty("dataStartTime")
    private String dataStartTime;

    @JsonProperty("dataEndTime")
    private String dataEndTime;

    @JsonProperty("salesByDate")
    private SalesByDate salesByDate;

    @JsonProperty("trafficByDate")
    private TrafficByDate trafficByDate;

    public static SalesTrafficReportMongoDates convertToMongoReportDates(SalesTrafficReportDate salesAndTrafficByDate) {
        SalesTrafficReportMongoDates report = new SalesTrafficReportMongoDates();
        report.setDate(salesAndTrafficByDate.getDate());
        report.setSalesByDate(salesAndTrafficByDate.getSalesByDate());
        report.setTrafficByDate(salesAndTrafficByDate.getTrafficByDate());
        return report;
    }
}
