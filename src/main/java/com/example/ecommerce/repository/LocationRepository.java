package com.example.ecommerce.repository;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LocationRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Location addLocation(Location location){
        jdbcTemplate.update(
                "INSERT INTO location (city,country,address) " +
                        "VALUES (?,?,?,?,?,?)",
                location.getCity(),
                location.getCountry(),
                location.getAddress()
        );

        return getLocationById(getLatestLocationId().get());
    }

    public List<Location> getAllLocation(){
        return jdbcTemplate.query("SELECT * FROM location", rs -> {
            List<Location> locations = new ArrayList<>();

            while (rs.next()) {
                locations.add(new Location(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                ));
            }
            return locations;
        });
    }

    public Location getLocationById(int id){
        String query = "Select * from location where id=?";
        Object[] arg = {id};
        return jdbcTemplate.query(query,arg,rs->{
            Location location = null;
            if(rs.next()) {
                location = new Location(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }
            return location;
        });
    }

    public Optional<Integer> getLatestLocationId(){
        String query = "SELECT * FROM location ORDER BY id DESC LIMIT 1";

        return jdbcTemplate.query(query, rs -> {
            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }
            return Optional.empty();
        });
    }
}
