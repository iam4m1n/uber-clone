package com.example.onlineTaxi.model.Users.User;


import com.example.onlineTaxi.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Collection;
import java.util.Collections;
import java.util.List;


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

    private Role role;



//    private ArrayList<Order> orders;
//    private ArrayList<Payment> payments;
//    private ArrayList<Driver> drivers;

}
