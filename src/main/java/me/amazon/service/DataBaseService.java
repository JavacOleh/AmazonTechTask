package me.amazon.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import me.amazon.model.ReportWrapper;
import me.amazon.model.json.asins.SalesTrafficReportAsin;
import me.amazon.model.json.dates.SalesTrafficReportDate;
import me.amazon.model.mongo.SalesTrafficReportMongoAsins;
import me.amazon.model.mongo.SalesTrafficReportMongoDates;
import me.amazon.repository.asins.SalesTrafficReportRepositoryAsins;
import me.amazon.repository.dates.SalesTrafficReportRepositoryDates;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataBaseService {
    private RedisTemplate<String, Object> redisTemplate;
    private SalesTrafficReportRepositoryAsins repositoryAsins;
    private SalesTrafficReportRepositoryDates repositoryDates;
    private ObjectMapper objectMapper;
    private MongoTemplate mongoTemplate;

    public DataBaseService(RedisTemplate<String, Object> redisTemplate,
                           SalesTrafficReportRepositoryAsins repositoryAsins,
                           SalesTrafficReportRepositoryDates repositoryDates,
                           ObjectMapper objectMapper,
                           MongoTemplate mongoTemplate) {
        this.redisTemplate = redisTemplate;
        this.repositoryAsins = repositoryAsins;
        this.repositoryDates = repositoryDates;
        this.objectMapper = objectMapper;
        this.mongoTemplate = mongoTemplate;
    }

    public String testRedisConnection() {
        try {
            String pingResponse = redisTemplate.getConnectionFactory().getConnection().ping();
            return "Redis test connection successful: " + pingResponse;
        } catch (Exception e) {
            return "Error connecting to Redis: " + e.getMessage();
        }
    }

    public String testMongoConnection() {
        try {
            Document result = mongoTemplate.executeCommand("{ ping: 1 }");
            return "MongoDB test connection successful: " + result.toJson();
        } catch (Exception e) {
            return "Error connecting to MongoDB: " + e.getMessage();
        }
    }
    public void loadDataFromJson() {
        try {
            ReportWrapper reportWrapper = objectMapper.readValue(
                    new File("src/main/resources/test_report.json"),
                    ReportWrapper.class
            );

            List<SalesTrafficReportMongoAsins> reportsAsins = new ArrayList<>();
            for (SalesTrafficReportAsin salesAndTrafficByAsin : reportWrapper.getSalesAndTrafficByAsin()) {
                reportsAsins.add(SalesTrafficReportMongoAsins.convertToMongoReportAsins(salesAndTrafficByAsin));
            }

            List<SalesTrafficReportMongoDates> reportsDates = new ArrayList<>();
            for (SalesTrafficReportDate salesAndTrafficByDate : reportWrapper.getSalesAndTrafficByDate()) {
                reportsDates.add(SalesTrafficReportMongoDates.convertToMongoReportDates(salesAndTrafficByDate));
            }

            List<String> collectionNames = mongoTemplate.getDb().listCollectionNames().into(new ArrayList<>());
            if (collectionNames.contains("sales_and_traffic_reports_dates"))
                mongoTemplate.getDb().getCollection("sales_and_traffic_reports_dates").drop();

            if (collectionNames.contains("sales_and_traffic_reports_asins"))
                mongoTemplate.getDb().getCollection("sales_and_traffic_reports_asins").drop();


            repositoryAsins.saveAll(reportsAsins);
            repositoryDates.saveAll(reportsDates);

            System.out.println("Data successfully loaded into MongoDB");
        } catch (IOException e) {
            System.err.println("Error reading the JSON file: " + e.getMessage());
        }
    }
    @PostConstruct
    public void init() {
        loadDataFromJson();
    }
}
