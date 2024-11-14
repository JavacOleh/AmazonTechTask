package me.amazon.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.amazon.model.json.asins.SalesTrafficReportAsin;
import me.amazon.model.json.dates.SalesTrafficReportDate;

import java.util.List;

@Data
public class ReportWrapper {
    @JsonProperty("salesAndTrafficByDate")
    private List<SalesTrafficReportDate> salesAndTrafficByDate;

    @JsonProperty("salesAndTrafficByAsin")
    private List<SalesTrafficReportAsin> salesAndTrafficByAsin;
}
