package com.example.onlineTaxi.model.Users.User;


import com.example.onlineTaxi.enums.Role;
import com.example.onlineTaxi.model.driver.Driver;
import com.example.onlineTaxi.model.order.OrderEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;


import java.util.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String fullName;
    private String phoneNumber;
    private String password;
    private Integer age;

    private double balance;

    @Enumerated(EnumType.STRING)
    private Role role;



    // One-to-Many with Order
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<OrderEntity> orderEntities = new ArrayList<>();


}
