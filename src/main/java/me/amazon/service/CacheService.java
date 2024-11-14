package me.amazon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CacheService {
    private RedisTemplate<String, String> redisTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    public CacheService(RedisTemplate<String, String> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public <T> T getFromCache(String key, Class<T> clazz) {
        try {
            String json = redisTemplate.opsForValue().get(key);
            if (json == null || json.isEmpty()) {
                return null;  // Возвращаем null, если данных нет в кеше
            }
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            // Логирование ошибки и возвращение null
            System.err.println("Ошибка при извлечении данных из кэша Redis: " + e.getMessage());
            return null;
        }
    }

    public <T> void saveToCache(String key, T value) {
        try {
            String json = objectMapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, json);
        } catch (Exception e) {
            // Логирование ошибки при сохранении данных
            System.err.println("Ошибка при сохранении данных в кэш Redis: " + e.getMessage());
        }
    }
}
