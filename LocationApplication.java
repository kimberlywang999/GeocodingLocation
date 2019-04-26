package com.example.location;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;


@SpringBootApplication
public class LocationApplication implements CommandLineRunner {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LocationApplication.class);

    @Autowired
    private LocationRepository locationRepository;

    public static void main(String[] args) {

        SpringApplication.run(LocationApplication.class, args);
    }

    @Override
    public void run(String... args){
           //reset repository -> locationRepository.deleteAll();

           locationRepository.findByID(1);
           try {

               locationRepository.addLocation(new Location(2, 70, -70));

               Location locationDemo = new Location();
               locationDemo = locationRepository.updateById(new Location(2, 40.45, -70.25));

               //locationRepository.deleteById(5);
               locationRepository.deleteLocationByID(new Location(2, 40.45, -70.25));

               GeoLocationResponse response = new GeoLocationResponse();
               response = locationRepository.findAllByLocation(40.45, -70.25);
               //response.map(result -> result.formattedAddress)

           } catch (Exception e) {
               LOGGER.error(e.getMessage(), e);
           }



    }

}
