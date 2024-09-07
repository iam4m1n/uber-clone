package com.example.onlineTaxi.service;

import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.model.driver.Driver;
import com.example.onlineTaxi.model.order.OrderEntity;

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

    public OrderEntity getOrder(Long id);
    public List<OrderEntity> getOrders();

    public OrderEntity updateOrder();

    public OrderEntity deleteOrder();

}
