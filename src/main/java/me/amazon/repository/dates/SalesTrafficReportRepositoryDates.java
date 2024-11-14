package me.amazon.repository.dates;

import me.amazon.model.mongo.SalesTrafficReportMongoDates;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SalesTrafficReportRepositoryDates extends MongoRepository<SalesTrafficReportMongoDates, String> {
    List<SalesTrafficReportMongoDates> findByDateBetween(String startDate, String endDate);
    SalesTrafficReportMongoDates findFirstByDate(String date);
}
