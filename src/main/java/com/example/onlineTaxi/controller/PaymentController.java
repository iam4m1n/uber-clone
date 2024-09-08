package com.example.onlineTaxi.controller;

import com.example.onlineTaxi.model.payment.Payment;
import com.example.onlineTaxi.service.impl.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/get")
    public ResponseEntity<Payment> getPayment(@RequestParam Long id){
        return new ResponseEntity<>(paymentService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Payment>> getAllPayments(){
        return new ResponseEntity<>(paymentService.findAll(), HttpStatus.OK);
    }


    // update and create
    @PostMapping("/pay")
    public Payment payment(@RequestParam Long id,
                           @RequestParam String paymentMethod
    ){
        return paymentService.processPayment(id, paymentMethod);
    }


}
