package com.monofi.model;

import com.monofi.model.enums.PaymentState;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "payment")
@Getter
@Setter
@Builder
@ToString
public class Payment {

    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentState paymentState;
}
