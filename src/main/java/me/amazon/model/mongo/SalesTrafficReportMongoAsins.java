package me.amazon.model.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.amazon.model.json.asins.SalesTrafficReportAsin;
import me.amazon.model.json.asins.SalesByAsin;
import me.amazon.model.json.asins.TrafficByAsin;
import org.springframework.data.annotation.Id;

@Data
public class SalesTrafficReportMongoAsins {
    @Id
    private String id;

    @JsonProperty("parentAsin")
    private String parentAsin;

    @JsonProperty("salesByAsin")
    private SalesByAsin salesByAsin;

    @JsonProperty("trafficByAsin")
    private TrafficByAsin trafficByAsin;

    public static SalesTrafficReportMongoAsins convertToMongoReportAsins(SalesTrafficReportAsin salesAndTrafficByAsin) {
        SalesTrafficReportMongoAsins report = new SalesTrafficReportMongoAsins();
        report.setParentAsin(salesAndTrafficByAsin.getParentAsin());
        report.setSalesByAsin(salesAndTrafficByAsin.getSalesByAsin());
        report.setTrafficByAsin(salesAndTrafficByAsin.getTrafficByAsin());
        return report;
    }
}
