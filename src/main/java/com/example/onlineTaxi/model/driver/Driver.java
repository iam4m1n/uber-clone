package com.example.onlineTaxi.model.driver;

import com.example.onlineTaxi.enums.DriverStatus;
import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.model.order.OrderEntity;
import com.example.onlineTaxi.model.vehicle.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String username;
    private String password;
    private String phoneNumber;
    private String fullName;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private DriverStatus status;
    private Boolean isLicenseValid;
    private Date licenseExpireTime;
    private Double rating;

    @Column(columnDefinition = "geometry(Point, 4326)")
    @JsonIgnore
    private Point location;


    // One-to-One with Vehicle
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;


    // One-to-Many with Order
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<OrderEntity> orderEntities = new ArrayList<>();



}
