package com.example.location;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Iterator;
import java.util.List;

import static javafx.scene.input.KeyCode.T;


public interface LocationRepository extends MongoRepository<Location, Integer>, LocationDomainRepository {

        public Location findByID(int id);
        //public List<Location> findAll(double lat, double lng);
        public Location save(Location location);
        //public Iterator<Location> saveAll(Iterator<Location> locations);

}
