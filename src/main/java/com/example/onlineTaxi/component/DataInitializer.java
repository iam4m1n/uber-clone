package com.example.onlineTaxi.component;

import com.example.onlineTaxi.enums.Colors;
import com.example.onlineTaxi.enums.DriverStatus;
import com.example.onlineTaxi.enums.Role;
import com.example.onlineTaxi.enums.VehicleType;
import com.example.onlineTaxi.microservices.MscService;
import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.model.driver.Driver;
import com.example.onlineTaxi.model.vehicle.Vehicle;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Component;

import com.example.onlineTaxi.repository.DriverRepository;
import com.example.onlineTaxi.repository.PaymentRepository;
import com.example.onlineTaxi.repository.VehicleRepository;
import com.example.onlineTaxi.repository.UserRepository;
import com.example.onlineTaxi.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;

    private final DriverRepository driverRepository;

    private final VehicleRepository vehicleRepository;

    private final OrderRepository orderRepository;

    private final PaymentRepository paymentRepository;

    private final GeometryFactory geometryFactory = new GeometryFactory();

    private final MscService mscService;


    @PostConstruct
    public void init() {

        List<UserEntity> users = generateRandomUsers();

        userRepository.saveAll(users);



        List<Driver> drivers = generateRandomDrivers();

        driverRepository.saveAll(drivers);


        List<Vehicle> vehicles = generateRandomVehicles();

        vehicleRepository.saveAll(vehicles);



    }


    public static List<UserEntity> generateRandomUsers() {
        // Arrays for each field with sample data
        String[] usernames = {"john_doe", "jane_smith", "alice_jones", "bob_brown", "carol_white", "david_black", "eve_green", "frank_yellow", "grace_red", "hank_blue"};
        String[] fullNames = {"John Doe", "Jane Smith", "Alice Jones", "Bob Brown", "Carol White", "David Black", "Eve Green", "Frank Yellow", "Grace Red", "Hank Blue"};
        String[] phoneNumbers = {"123-456-7890", "234-567-8901", "345-678-9012", "456-789-0123", "567-890-1234", "678-901-2345", "789-012-3456", "890-123-4567", "901-234-5678", "012-345-6789"};
        String[] passwords = {"password1", "password2", "password3", "password4", "password5", "password6", "password7", "password8", "password9", "password10"};
        Integer[] ages = {22, 25, 30, 28, 35, 40, 33, 27, 29, 31};
        Role[] roles = Role.values();  // Assuming you have a Role enum with values USER, ADMIN

        double[] randomBalances = {123.45, 678.90, 345.67, 987.12, 456.78, 234.56, 789.01, 567.89, 890.12, 912.34};


        List<UserEntity> users = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            UserEntity user = UserEntity.builder()
                    .username(usernames[i])
                    .fullName(fullNames[i])
                    .phoneNumber(phoneNumbers[i])
                    .password(passwords[i])
                    .age(ages[i])
                    .balance(randomBalances[i])
                    .role(roles[random.nextInt(roles.length)])
                    .build();
            users.add(user);
        }

        return users;
    }

    public static List<Driver> generateRandomDrivers() {
        // Arrays for each field with sample data
        String[] usernames = {"john_driver", "jane_driver", "alice_driver", "bob_driver", "carol_driver"};
        String[] fullNames = {"John Driver", "Jane Driver", "Alice Driver", "Bob Driver", "Carol Driver"};
        String[] phoneNumbers = {"111-222-3333", "222-333-4444", "333-444-5555", "444-555-6666", "555-666-7777"};
        String[] passwords = {"driverpass1", "driverpass2", "driverpass3", "driverpass4", "driverpass5"};
        Integer[] ages = {30, 35, 40, 45, 50};
        DriverStatus statuses = DriverStatus.ACTIVE;  // Assuming you have a DriverStatus enum
        Boolean[] licenseValidity = {true, false};
        Double[] ratings = {4.5, 4.7, 4.2, 4.8, 4.6};

        // New York City bounding box
        double minLat = 40.4774;
        double maxLat = 40.9176;
        double minLon = -74.2591;
        double maxLon = -73.7004;


        double[][] nycCoordinates = {
                {40.730610, -73.935242}, // Manhattan
                {40.650002, -73.949997}, // Brooklyn
                {40.729979, -73.851524}, // Queens
                {40.579021, -74.151535}, // Staten Island
                {40.837048, -73.865433}, // The Bronx
                {40.748817, -73.985428}, // Empire State Building
                {40.712776, -74.005974}, // Lower Manhattan
                {40.706192, -74.009160}, // Wall Street
                {40.758896, -73.985130}, // Times Square
                {40.785091, -73.968285}  // Central Park
        };


        List<Driver> drivers = new ArrayList<>();
        Random random = new Random();
        GeometryFactory geometryFactory = new GeometryFactory(); // Geometry factory for creating Point objects

        for (int i = 0; i < 5; i++) {
//            double latitude = minLat + (maxLat - minLat) * random.nextDouble();
//            double longitude = minLon + (maxLon - minLon) * random.nextDouble();
//            Point location = geometryFactory.createPoint(new org.locationtech.jts.geom.Coordinate(longitude, latitude));

            Point location = geometryFactory.createPoint(new Coordinate(nycCoordinates[i][1], nycCoordinates[i][0]));


            Driver driver = Driver.builder()
                    .username(usernames[i])
                    .fullName(fullNames[i])
                    .phoneNumber(phoneNumbers[i])
                    .password(passwords[i])
                    .age(ages[i])
                    .status(statuses)
                    .isLicenseValid(licenseValidity[random.nextInt(licenseValidity.length)])
                    .licenseExpireTime(new Date(System.currentTimeMillis() +  1000 * 60 * 60 * 24 * 365 *random.nextInt(5) + 1))  // License expires within 1 to 5 years
                    .rating(ratings[i])
                    .location(location)
                    .build();
            drivers.add(driver);
        }

        return drivers;
    }


    public static List<Vehicle> generateRandomVehicles() {
        // Arrays for each field with sample data
        String[] companies = {"Toyota", "Honda", "Ford", "Chevrolet", "BMW"};
        String[] models = {"Camry", "Accord", "Mustang", "Impala", "X5"};
        Integer[] years = {2018, 2019, 2020, 2021, 2022};
        Colors[] colors = Colors.values();  // Assuming you have a Colors enum
        VehicleType[] vehicleTypes = VehicleType.values();  // Assuming you have a VehicleType enum

        List<Vehicle> vehicles = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            Vehicle vehicle = Vehicle.builder()
                    .company(companies[random.nextInt(companies.length)])
                    .model(models[random.nextInt(models.length)])
                    .year(years[random.nextInt(years.length)])
                    .color(colors[random.nextInt(colors.length)])
                    .vehicleType(vehicleTypes[random.nextInt(vehicleTypes.length)])
                    .build();
            vehicles.add(vehicle);
        }

        return vehicles;
    }


    @PostConstruct
    public void rebbit(){
        mscService.sendNotification("Main app is starting up .......");

        mscService.sendTransaction("Main app is starting up .......");
    }
}