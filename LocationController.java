package com.example.location;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value= "/api/location")
public class LocationController {

//    @Autowired
//    LocationService locationSrv;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @PostMapping(value = "/create")
//    public String create(@RequestBody List<Location> loc) {
//        locationSrv.saveAll(loc);
//        return "locations saved";
//    }
//
//
//    @GetMapping(value = "/findAll")
//    public Collection<Location> findAll() {
//        return locationSrv.findAll();
//    }
//
//    @GetMapping(value = "/findByID/{id}")
//    public Location findByID(@PathVariable(value = "id") int id) {
//        return locationSrv.findByID(id);
//    }


}


