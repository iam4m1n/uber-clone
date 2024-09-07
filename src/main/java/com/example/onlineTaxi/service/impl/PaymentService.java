package com.example.onlineTaxi.service.impl;


import com.example.onlineTaxi.enums.PaymentMethod;
import com.example.onlineTaxi.enums.PaymentStatus;
import com.example.onlineTaxi.enums.TravelStatus;
import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.model.order.OrderEntity;
import com.example.onlineTaxi.model.payment.Payment;
import com.example.onlineTaxi.repository.OrderRepository;
import com.example.onlineTaxi.repository.PaymentRepository;
import com.example.onlineTaxi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

    private static final Logger logger = LoggerFactory.getLogger(TravelRequestService.class);






    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElseThrow();
    }




    public Payment payThePaymentByOrderId(Long id, String paymentMethod) {

        OrderEntity order = orderRepository.findById(id).orElseThrow();

        UserEntity userEntity = userRepository.findById(order.getUser().getId()).orElseThrow();

        if (order.getPayment() == null)
            order.setPayment(Payment.builder().Id(id).paymentAmount(order.getCost()).paymentMethod(PaymentMethod.valueOf(paymentMethod)).modifiedTime(new Date()).status(PaymentStatus.PENDING).build());

        if (order.getStatus().equals(TravelStatus.Ongoing) && !(order.getPayment().getStatus().equals(PaymentStatus.COMPLETED) && userEntity.getBalance() >= order.getCost())){

            userEntity.setBalance(userEntity.getBalance() - order.getCost());

            order.getPayment().setStatus(PaymentStatus.COMPLETED);
            order.getPayment().setPaymentMethod(PaymentMethod.valueOf(paymentMethod));

            orderRepository.save(order);

            userRepository.save(userEntity);

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
