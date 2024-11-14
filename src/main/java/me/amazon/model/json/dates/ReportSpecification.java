package me.amazon.model.json.dates;

import lombok.Data;
import java.util.List;

@Data
public class ReportSpecification {
    private String reportType;
    private ReportOptions reportOptions;
    private String dataStartTime;
    private String dataEndTime;
    private List<String> marketplaceIds;
}