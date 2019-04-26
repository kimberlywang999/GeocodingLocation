package com.example.location;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.io.IOException;
import java.util.List;

public interface LocationDomainRepository {

    public Boolean addLocation(Location location) throws IOException, Exception;
    public Location updateById(Location location) throws IOException, Exception;
    public Boolean deleteLocationByID(Location location) throws IOException, Exception;
    //public List<Location> findAllByLocation(double lan, double lng) throws Exception;
    public GeoLocationResponse findAllByLocation(double lan, double lng) throws Exception;

}
