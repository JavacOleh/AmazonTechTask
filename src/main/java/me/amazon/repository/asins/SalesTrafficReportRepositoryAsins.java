package me.amazon.repository.asins;

import me.amazon.model.mongo.SalesTrafficReportMongoAsins;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalesTrafficReportRepositoryAsins extends MongoRepository<SalesTrafficReportMongoAsins, String> {
    SalesTrafficReportMongoAsins findFirstByParentAsin(String parentAsin);

}
