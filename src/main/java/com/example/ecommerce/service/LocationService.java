package com.example.ecommerce.service;

import com.example.ecommerce.model.Location;
import com.example.ecommerce.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;


    public Location addLocation(Location location){
        return locationRepository.addLocation(location);
    }

    public List<Location> getAllLocations(){
        return locationRepository.getAllLocation();
    }

    public Location getLocationById(int id){
        return locationRepository.getLocationById(id);
    }

}
