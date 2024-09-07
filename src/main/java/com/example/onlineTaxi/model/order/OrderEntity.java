package com.example.onlineTaxi.model.order;

import com.example.onlineTaxi.enums.TravelStatus;
import com.example.onlineTaxi.enums.VehicleType;
import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.model.driver.Driver;
import com.example.onlineTaxi.model.payment.Payment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private Date modifiedTime;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @Enumerated(EnumType.STRING)
    private TravelStatus status;


    // Many-to-One with UserEntity
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // Many-to-One with Driver
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    // One-to-One with Payment
    @OneToOne(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    private Payment payment;

    @Column(columnDefinition = "geometry(Point, 4326)")
    @JsonIgnore
    private Point startPoint;

    @Column(columnDefinition = "geometry(Point, 4326)")
    @JsonIgnore
    private Point endPoint;



    private double tripTimeByMinutes;

    private double distance;

    private double cost;




}
