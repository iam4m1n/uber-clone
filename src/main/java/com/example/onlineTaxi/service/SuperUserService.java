package com.example.onlineTaxi.service;

import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.model.driver.Driver;
import com.example.onlineTaxi.model.order.Order;

import java.util.List;

public interface SuperUserService {

    // CRUD for normalUser

    public UserEntity addUser();

    public UserEntity getUser(Long id);
    public List<UserEntity> getUsers();

    public UserEntity updateUser();

    public UserEntity deleteUser();


    // CRUD for Driver
    public Driver addDriver();

    public Driver getDriver(Long id);
    public List<Driver> getDrivers();


    public Driver updateDriver();

    public Driver deleteDriver();


    // CRUD for Order

    public Order getOrder(Long id);
    public List<Order> getOrders();

    public Order updateOrder();

    public Order deleteOrder();

}
