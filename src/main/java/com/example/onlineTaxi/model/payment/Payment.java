package com.example.onlineTaxi.model.payment;

import com.example.onlineTaxi.enums.PaymentMethod;
import com.example.onlineTaxi.enums.PaymentStatus;
import com.example.onlineTaxi.model.order.OrderEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private Double paymentAmount;
    private Date modifiedTime;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @OneToOne
    @JoinColumn(name = "ride_order_id", referencedColumnName = "id")
    @JsonIgnore
    private OrderEntity orderEntity;

    private Long orderEntityId;

}
