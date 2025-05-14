package com.travelSafe.buses;

import java.util.List;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfiguration {

  @Bean
  public CacheManager cacheManager() {
    ConcurrentMapCacheManager manager = new ConcurrentMapCacheManager();
    manager.setAllowNullValues(false);
    manager.setCacheNames(List.of("tripCache"));
    return manager;
  }

  @CacheEvict(value = "tripCache", allEntries = true)
  @Scheduled(fixedDelay = 10000, initialDelay = 0)
  public void evictingCache() {
    System.out.println("Evicting tripCache cache.");
  }
}