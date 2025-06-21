package com.travel.safe.buses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BusesApplication {

  public static void main(String[] args) {
    SpringApplication.run(BusesApplication.class, args);
  }
}