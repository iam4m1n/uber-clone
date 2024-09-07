package com.example.onlineTaxi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomPoint {

    private double lat;

    private double lon;


    public CustomPoint(Point location) {
        this.lon = location.getX();
        this.lat = location.getY();
    }


}
