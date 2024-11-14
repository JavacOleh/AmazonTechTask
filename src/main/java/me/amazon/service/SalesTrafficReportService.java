package me.amazon.service;

import me.amazon.model.mongo.SalesTrafficReportMongoAsins;
import me.amazon.model.mongo.SalesTrafficReportMongoDates;
import me.amazon.repository.asins.SalesTrafficReportRepositoryAsins;
import me.amazon.repository.dates.SalesTrafficReportRepositoryDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesTrafficReportService {


    private final SalesTrafficReportRepositoryAsins repositoryAsins;

    private final SalesTrafficReportRepositoryDates repositoryDates;

    private final CacheService cacheService;

    private final DataBaseService dataBaseService;

    private static final String CACHE_KEY_PREFIX_ASIN = "asin:";
    private static final String CACHE_KEY_PREFIX_DATE = "date:";

    @Autowired
    public SalesTrafficReportService(SalesTrafficReportRepositoryAsins repositoryAsins, SalesTrafficReportRepositoryDates repositoryDates, CacheService cacheService, DataBaseService dataBaseService) {
        this.repositoryAsins = repositoryAsins;
        this.repositoryDates = repositoryDates;
        this.cacheService = cacheService;
        this.dataBaseService = dataBaseService;
    }

    public List<SalesTrafficReportMongoDates> getStatisticsBetweenDate(String startDate, String endDate) {
        String cacheKey = CACHE_KEY_PREFIX_DATE + startDate + "-" + endDate;

        List<SalesTrafficReportMongoDates> cachedData = cacheService.getFromCache(cacheKey, List.class);

        if (cachedData != null) return cachedData;


        List<SalesTrafficReportMongoDates> data = repositoryDates.findByDateBetween(startDate, endDate);

        if (data != null && !data.isEmpty()) cacheService.saveToCache(cacheKey, data);

        return data;
    }

    public SalesTrafficReportMongoDates getStatisticsByDate(String date) {
        String cacheKey = CACHE_KEY_PREFIX_DATE + date;

        SalesTrafficReportMongoDates cachedData = cacheService.getFromCache(cacheKey, SalesTrafficReportMongoDates.class);  // Используем класс SalesTrafficReportMongoDates.class

        if (cachedData != null) return cachedData;

        SalesTrafficReportMongoDates data = repositoryDates.findFirstByDate(date);

        if (data != null) cacheService.saveToCache(cacheKey, data);


        return data;
    }

    public SalesTrafficReportMongoAsins getStatisticsByAsin(String parentAsin) {
        String cacheKey = CACHE_KEY_PREFIX_ASIN + parentAsin;

        SalesTrafficReportMongoAsins cachedData = cacheService.getFromCache(cacheKey, SalesTrafficReportMongoAsins.class);  // Используем SalesTrafficReportMongoAsins.class

        if (cachedData != null) return cachedData;

        SalesTrafficReportMongoAsins data = repositoryAsins.findFirstByParentAsin(parentAsin);

        if (data != null) cacheService.saveToCache(cacheKey, data);

        return data;
    }

    @Scheduled(fixedRate = 86400000)
    public void updateStatisticsFromFile() {
        dataBaseService.loadDataFromJson();
    }
}
