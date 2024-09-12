package com.example.onlineTaxi.microservices;
import com.example.onlineTaxi.config.RabbitMQConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TransactionProducer {

    private static final String QUEUE_NAME = "transaction_queue";

    public void sendTransaction(String transactionData) throws Exception {
        Connection connection = RabbitMQConnection.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.basicPublish("", QUEUE_NAME, null, transactionData.getBytes());
        System.out.println("Transaction sent: " + transactionData + " at: "+ new Date());

        channel.close();
        connection.close();
    }
}

