package com.example.onlineTaxi.repository;

import com.example.onlineTaxi.model.driver.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Query(value = "SELECT * FROM driver " +
            "WHERE ST_Distance(location, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)) <= :maxDistance " +
            "AND status = 'ACTIVE' " + // Add this line to filter for active drivers
            "ORDER BY ST_Distance(location, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)) ASC",
            nativeQuery = true)
    List<Driver> findClosestActiveDrivers(@Param("latitude") double latitude,
                                          @Param("longitude") double longitude,
                                          @Param("maxDistance") double maxDistance);

}
