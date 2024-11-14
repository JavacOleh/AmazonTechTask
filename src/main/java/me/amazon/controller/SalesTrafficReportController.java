package me.amazon.controller;


import me.amazon.model.mongo.SalesTrafficReportMongoAsins;
import me.amazon.model.mongo.SalesTrafficReportMongoDates;
import me.amazon.service.SalesTrafficReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class SalesTrafficReportController {


    private final SalesTrafficReportService salesTrafficReportService;

    @Autowired
    public SalesTrafficReportController(SalesTrafficReportService salesTrafficReportService) {
        this.salesTrafficReportService = salesTrafficReportService;
    }

    // Запрос статистики по диапазону дат
    @GetMapping("/byDates")
    public ResponseEntity<List<SalesTrafficReportMongoDates>> getStatisticsByDate(
            @RequestParam String startDate, @RequestParam String endDate) {
        List<SalesTrafficReportMongoDates> reports = salesTrafficReportService.getStatisticsBetweenDate(startDate, endDate);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/byDate")
    public ResponseEntity<SalesTrafficReportMongoDates> getStatisticsByDate(
            @RequestParam String date) {
        SalesTrafficReportMongoDates report = salesTrafficReportService.getStatisticsByDate(date);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/byAsin")
    public ResponseEntity<SalesTrafficReportMongoAsins> getStatisticsByAsin(@RequestParam String asin) {
        SalesTrafficReportMongoAsins report = salesTrafficReportService.getStatisticsByAsin(asin);
        return ResponseEntity.ok(report);
    }

    @PostMapping("/update-statistics")
    public ResponseEntity<String> updateStatistics() {
        salesTrafficReportService.updateStatisticsFromFile();
        return ResponseEntity.ok("Statistics updated successfully.");
    }
}
