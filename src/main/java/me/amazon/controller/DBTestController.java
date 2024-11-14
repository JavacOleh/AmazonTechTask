package me.amazon.controller;

import me.amazon.service.DataBaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class DBTestController {

    private final DataBaseService DataBaseService;

    public DBTestController(DataBaseService DataBaseService) {
        this.DataBaseService = DataBaseService;
    }

    @GetMapping("/redis")
    public String testRedisConnection() {
        return DataBaseService.testRedisConnection();
    }
    @GetMapping("/mongo")
    public String testMongoConnection() {
        return DataBaseService.testMongoConnection();
    }
}
