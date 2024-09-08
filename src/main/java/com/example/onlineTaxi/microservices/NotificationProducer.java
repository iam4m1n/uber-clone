package com.example.onlineTaxi.microservices;
import com.example.onlineTaxi.config.RabbitMQConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.stereotype.Component;

@Component
public class NotificationProducer {

    private static final String QUEUE_NAME = "notification_queue";

    public void sendNotification(String message) throws Exception {
        Connection connection = RabbitMQConnection. getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("Notification sent: " + message);

        channel.close();
        connection.close();
    }
}
