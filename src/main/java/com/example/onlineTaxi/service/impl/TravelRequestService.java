package com.example.onlineTaxi.service.impl;

import com.example.onlineTaxi.enums.PaymentMethod;
import com.example.onlineTaxi.enums.PaymentStatus;
import com.example.onlineTaxi.enums.TravelStatus;
import com.example.onlineTaxi.enums.VehicleType;
import com.example.onlineTaxi.model.CustomPoint;
import com.example.onlineTaxi.model.TravelRequest;
import com.example.onlineTaxi.model.Users.User.UserDTO;
import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.model.Users.UserMapper;
import com.example.onlineTaxi.model.driver.Driver;
import com.example.onlineTaxi.model.driver.DriverDTO;
import com.example.onlineTaxi.model.driver.DriverMapper;
import com.example.onlineTaxi.model.order.OrderDTO;
import com.example.onlineTaxi.model.order.OrderEntity;
import com.example.onlineTaxi.model.order.OrderMapper;
import com.example.onlineTaxi.model.payment.Payment;
import com.example.onlineTaxi.model.payment.PaymentDTO;
import com.example.onlineTaxi.repository.DriverRepository;
import com.example.onlineTaxi.repository.OrderRepository;
import com.example.onlineTaxi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TravelRequestService {


    private final DriverRepository driverRepository;

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final DriverMapper driverMapper;

    private final UserMapper userMapper;
    private final GeometryFactory geometryFactory = new GeometryFactory();

    private static final Logger logger = LoggerFactory.getLogger(TravelRequestService.class);


    private static final double BASE_FARE = 2.50; // Base fare
    private static final double COST_PER_KM = 0.75; // Cost per kilometer
    private static final double COST_PER_MINUTE = 0.50; // Cost per minute
    private static final double TAX_RATE = 0.1; // 10% tax
    private static final double SURGE_MULTIPLIER = 1.0; // Optional surge multiplier




    public OrderDTO createOrderForUser(
            UserEntity user,
            double userLat,
            double userLon,
            double startLat,
            double startLon,
            double endLat,
            double endLon,
            String type

    ) {
        Point userLocation = createPoint(userLat, userLon);

        List<Driver> nearbyDrivers = driverRepository.findClosestActiveDrivers(userLocation.getY(), userLocation.getX(), 100000); // 100 km in meters

        if (nearbyDrivers.isEmpty()) {
            throw new RuntimeException("No drivers available within 10 km");
        }

        Driver selectedDriver = nearbyDrivers.get(0); // Simple logic to select the first driver

//        DriverDTO selectedDriverDto =  DriverMapper.driverToDriverDto(selectedDriver);

        OrderEntity order = new OrderEntity();
        // Set other order fields as needed
        order.setModifiedTime(new Date());
        order.setType(VehicleType.valueOf(type));
        order.setStartPoint(createPoint(startLat, startLon));
        order.setEndPoint(createPoint(endLat, endLon));


        order.setUser(user);
        order.setDriver(selectedDriver);
        order.setStatus(TravelStatus.Ongoing);
        // Set payment details, etc.



        double distance = convertDegreesToMeters(selectedDriver.getLocation().distance(userLocation))/1000;

        double avgSpeed = 50;

        double timeMinutes = distance/avgSpeed * 60;

        double cost = calculateRideCost(distance, timeMinutes, order.getType().name());

        order.setCost(cost);

        order.setDistance(distance);

        order.setTripTimeByMinutes(timeMinutes);



        orderRepository.save(order);



        return OrderMapper.orderToOrderDto(order, userLocation, distance, cost, timeMinutes);

    }

    private Point createPoint(double latitude, double longitude) {
        Coordinate coordinate = new Coordinate(longitude, latitude);
        Point point = geometryFactory.createPoint(coordinate);
        point.setSRID(4326);
        return point;
    }

    public void setCoordinates(TravelRequest travelRequest, double startLat, double startLon, double endLat, double endLon) {
        Point startPoint = createPoint(startLat, startLon);
        Point endPoint = createPoint(endLat, endLon);

        travelRequest.setStartPoint(startPoint);
        travelRequest.setEndPoint(endPoint);
    }

    public static double convertDegreesToMeters(double degrees) {
        // Approximate conversion: 1 degree = ~111,320 meters (at the equator)
        return degrees * 111320;
    }

    public double calculateRideCost(double distanceInKm, double timeInMinutes, String vehicleType) {
        // Base cost calculation (distance and time)
        double totalCost = BASE_FARE + (distanceInKm * COST_PER_KM) + (timeInMinutes * COST_PER_MINUTE);

        // Apply surge pricing (optional, can be dynamic)
        totalCost *= SURGE_MULTIPLIER;

        // Adjust for vehicle type (optional for different rates based on vehicle)
        if (vehicleType.equalsIgnoreCase("LuxuryCar")) {
            totalCost *= 1.5; // Luxury cars cost 50% more
        }

        // Add tax to the total cost
        totalCost += totalCost * TAX_RATE;

        return totalCost;
    }

    public List<Driver> getAllDrivers(UserEntity user, double userLat, double userLon, double startLat, double startLon, double endLat, double endLon) {
        Point userLocation = createPoint(userLat, userLon);

//        List<Driver> nearbyDrivers = driverRepository.findClosestActiveDrivers(userLocation.getY(), userLocation.getX(), 10000); // 10 km in meters
        List<Driver> nearbyDrivers = driverRepository.findAll(); // 10 km in meters

        if (nearbyDrivers.isEmpty()) {
            throw new RuntimeException("No drivers available within 10 km");
        }

        System.out.println("laaaaaaaaaaaaaaaaaaaaaaaaaaa\n\n\n\n\n\n\n\n\n");

        return nearbyDrivers;
    }


    public void deleteTravel(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}