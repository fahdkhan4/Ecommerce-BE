package com.example.ecommerce.controller;

import com.example.ecommerce.model.Location;
import com.example.ecommerce.service.LocationService;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/location")
public class LocationController
{


    @Autowired
    UserService userService;

    @PatchMapping("/update/{userId}")
    public Location updateLocation(@RequestBody  Location location,@PathVariable int userId){
        if(Objects.isNull(location.getId())){
            throw new IllegalArgumentException("Invalid Argument");
        }
        return userService.updateLocation(location,userId);
    }


}
