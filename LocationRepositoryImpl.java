package com.example.location;

import com.example.location.LocationDomainRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.*;
import com.mongodb.MongoClient;
import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.ConnectionString;
import com.mongodb.Cursor;
import com.mongodb.ConnectionString;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;

import org.springframework.stereotype.Component;


public class LocationRepositoryImpl implements LocationDomainRepository {

    @Autowired
    private LocationRepository locationRepository;

    public static final String dbName = "locationDB";
    public static final String locationEntity = "Location";

    public MongoCollection<org.bson.Document> getMongoDBCollection() {
           MongoClient mongoClient = new MongoClient("localhost", 27017);
           MongoDatabase db = mongoClient.getDatabase(this.dbName);
           //MongoCollection<Document> document = db.getCollection(locationEntity);
           return db.getCollection(this.locationEntity);
    }

    @Override
    public Boolean addLocation(Location location) throws IOException, Exception {
           MongoCollection<Document> locationCollection = getMongoDBCollection();

           locationCollection.insertOne(Document.parse((new ObjectMapper().writeValueAsString(location)) ));
           Boolean status = false;
           return status;
    }

    @Override
    public Location updateById(Location location) throws IOException, Exception{
           MongoCollection<Document> locationCollection = getMongoDBCollection();
           Bson filter = Filters.eq("id", location.getId());
           Bson newDocument = new Document("$set", Document.parse((new ObjectMapper().writeValueAsString(location)) ));

           UpdateResult result = locationCollection.updateOne(filter,
                                                              newDocument,
                                                              (new UpdateOptions()).upsert(true));
            //Document newDoc = new Document("id", location.getId())
//                                .append("lat", location.getLat()).append("lng", location.getLng());
//         locationCollection.replaceOne(Filters.eq("id", location.getId()),
//                                         newDoc,
//                                        (new UpdateOptions()).upsert(true));


        return location;

    }

    @Override
    public Boolean deleteLocationByID(Location location) throws IOException, Exception {
           MongoCollection<Document> locationCollection = getMongoDBCollection();

           Bson filter = Filters.eq("id", location.getId());
           DeleteResult result = locationCollection.deleteOne(filter);
           return result.getDeletedCount() == 1 ? true : false;
    }

    @Override
    public GeoLocationResponse findAllByLocation(double lan, double lng) throws Exception {

           ArrayList locations = new ArrayList();

           GeocodingImpl geoCoding = new GeocodingImpl();
           GeoLocationResponse response = new GeoLocationResponse();

           //respone = Set<String> addresses;
           response = geoCoding.reverseGeocode(40.7627, -73.9875);


        return response;
    }


//    public <S extends T> List<S> saveAll(Iterable<S> entities) {
//        Assert.notNull(entities, "The given Iterable of entities not be null!");
//        Streamable<S> source = Streamable.of(entities);
//        boolean allNew = source.stream().allMatch((it) -> {
//            return this.entityInformation.isNew(it);
//        });
//        if (allNew) {
//            List<S> result = (List)source.stream().collect(Collectors.toList());
//            return new ArrayList(this.mongoOperations.insert(result, this.entityInformation.getCollectionName()));
//        } else {
//            return (List)source.stream().map(this::save).collect(Collectors.toList());
//        }
//    }
}


