package com.example.onlineTaxi.service;

import com.example.onlineTaxi.model.User.normalUser.NormalUser;
import com.example.onlineTaxi.model.driver.Driver;
import com.example.onlineTaxi.model.order.Order;

import java.util.List;

public interface SuperUserService {

    // CRUD for normalUser

    public NormalUser addUser();

    public NormalUser getUser(Long id);
    public List<NormalUser> getUsers();

    public NormalUser updateUser();

    public NormalUser deleteUser();


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
