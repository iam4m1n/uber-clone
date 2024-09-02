package com.example.onlineTaxi.controller;


import com.example.onlineTaxi.model.order.OrderDTO;
import com.example.onlineTaxi.model.payment.PaymentDTO;
import com.example.onlineTaxi.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInfo(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(userService.getInfo(id), HttpStatus.OK);
    }

    @GetMapping("/orderHistory/{id}")
    public ResponseEntity<?> getOrderHistory(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(userService.getOrderHistory(id), HttpStatus.OK);
    }

    @GetMapping("/paymentHistory/{id}")
    public ResponseEntity<?> getPaymentHistory(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(userService.getPaymentHistory(id), HttpStatus.OK);
    }
    @PostMapping("/order")
    public ResponseEntity<?> addOrder(OrderDTO orderDTO){
        return new ResponseEntity<>(userService.addOrder(orderDTO), HttpStatus.CREATED);
    }

    @PostMapping("/payment")
    public ResponseEntity<?> payment(PaymentDTO paymentDTO){
        return new ResponseEntity<>(userService.payment(paymentDTO), HttpStatus.CREATED);
    }


    // test adviser
//    @GetMapping("/adviser")
//    public ResponseEntity<String> test(){
//        throw new NullPointerException("No user found!!!");
//
//    }


}
