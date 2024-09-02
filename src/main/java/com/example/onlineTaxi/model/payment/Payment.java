package com.example.onlineTaxi.model.payment;

import com.example.onlineTaxi.enums.PaymentMethod;
import com.example.onlineTaxi.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    private Long Id;
    private Double paymentAmount;
    private LocalDateTime modifiedTime;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;



//    private NormalUser user;
//    private Order order;
}
