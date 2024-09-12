package com.example.onlineTaxi.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;

public class RabbitMQConnection {

    @Value("${spring.rabbitmq.host}")
    private static String rabbitMQHost;

    @Value("${spring.rabbitmq.username}")
    private static String rabbitMQUsername;

    @Value("${spring.rabbitmq.password}")
    private static String rabbitMQPassword;

    @Value("${spring.rabbitmq.port}")
    private static int rabbitMQPort;



    public static Connection getConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
//        factory.setHost(rabbitMQHost);
//        factory.setUsername(rabbitMQUsername);
//        factory.setPassword(rabbitMQPassword);
        return factory.newConnection();
    }
}
