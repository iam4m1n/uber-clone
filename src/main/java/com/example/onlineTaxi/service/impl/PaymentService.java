package com.example.onlineTaxi.service.impl;


import com.example.onlineTaxi.enums.PaymentMethod;
import com.example.onlineTaxi.enums.PaymentStatus;
import com.example.onlineTaxi.enums.TravelStatus;
import com.example.onlineTaxi.microservices.MscService;
import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.model.order.OrderEntity;
import com.example.onlineTaxi.model.payment.Payment;
import com.example.onlineTaxi.repository.OrderRepository;
import com.example.onlineTaxi.repository.PaymentRepository;
import com.example.onlineTaxi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final MscService mscService;

    private RedissonClient redissonClient;


    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElseThrow();
    }




    public Payment processPayment(Long orderId, String paymentMethod) {

//
//        RLock lock = redissonClient.getLock("payment_lock_" + orderId);
//
//        try {
//            // Try to acquire the lock, with a maximum wait time of 10,000 ms (10 seconds)
//            if (lock.tryLock(10000)) {  // Only wait time in milliseconds
//                try {
//                    // Critical section (payment processing logic)
//                    System.out.println("Processing payment for order: " + orderId);
//                    // Your payment processing logic goes here
//                } finally {
//                    // Ensure the lock is released after processing
//                    lock.unlock();
//                }
//            } else {
//                // Handle the case when the lock couldn't be acquired
//                System.out.println("Could not acquire lock for order: " + orderId);
//            }
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            // Handle the interruption
//        }
//
//
//
//
//








        OrderEntity order = orderRepository.findById(orderId).orElseThrow();

        UserEntity userEntity = userRepository.findById(order.getUser().getId()).orElseThrow();

        if (order.getPayment() == null)
            order.setPayment(Payment.builder().Id(orderId).paymentAmount(order.getCost()).paymentMethod(PaymentMethod.valueOf(paymentMethod)).modifiedTime(new Date()).status(PaymentStatus.PENDING).build());

        if (order.getStatus().equals(TravelStatus.Ongoing) && !(order.getPayment().getStatus().equals(PaymentStatus.COMPLETED) && userEntity.getBalance() >= order.getCost())){

            userEntity.setBalance(userEntity.getBalance() - order.getCost());

            order.getPayment().setStatus(PaymentStatus.COMPLETED);
            order.getPayment().setPaymentMethod(PaymentMethod.valueOf(paymentMethod));

            orderRepository.save(order);

            userRepository.save(userEntity);

            mscService.sendTransaction("Order with id " + order.getId() + " has been paid by userId: " + userEntity.getId());
            mscService.sendNotification("for Driver: the traveler paid the payment!");

            logger.info("Successfully paid!!!");
        }else {

            order.getPayment().setStatus(PaymentStatus.FAILED);
            logger.error("unsuccessful paid!!!!!!!!!!!!!!!!!!!1");

        }


        Payment payment = Payment.builder()
                .paymentAmount(order.getCost())
                .modifiedTime(new Date())
                .paymentMethod(order.getPayment().getPaymentMethod())
                .paymentAmount(order.getCost())
                .status(order.getPayment().getStatus())
                .orderEntityId(order.getId())
                .build();

        paymentRepository.save(payment);

        return payment;

    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }


    public String showWallet(Long id){
        UserEntity user = userRepository.findById(id).orElseThrow();
        return "Your balance is: " + user.getBalance();
    }
}
