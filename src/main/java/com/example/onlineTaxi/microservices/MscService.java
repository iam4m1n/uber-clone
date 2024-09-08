package com.example.onlineTaxi.microservices;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MscService {

    private final NotificationProducer notificationProducer;

    private final TransactionProducer transactionProducer;

    public String sendNotification(String message) {
        try {
            notificationProducer.sendNotification(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Notification Sent: " + message;
    }


    public String sendTransaction(String transactionData) {
        try {
            transactionProducer.sendTransaction(transactionData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Transaction Sent: " + transactionData;
    }
}
