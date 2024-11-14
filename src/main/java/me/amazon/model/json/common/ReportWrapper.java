package me.amazon.model.json.common;

import lombok.Data;
import me.amazon.model.json.dates.SalesTrafficReportDate;
import me.amazon.model.json.dates.ReportSpecification;

import java.util.List;

@Data
public class ReportWrapper {
    private ReportSpecification reportSpecification;
    private List<SalesTrafficReportDate> salesAndTrafficByDate;
}