package com.example.onlineTaxi.model;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelRequest {

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point startPoint;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point endPoint;

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }
}
