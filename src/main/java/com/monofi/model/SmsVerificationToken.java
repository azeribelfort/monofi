package com.monofi.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sms_verification_token")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsVerificationToken {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token",nullable = false)
    private Integer number;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "expired_at")
    private LocalDateTime expiredAt;
    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;

    @ManyToOne
    @JoinColumn(nullable = false,
            name = "user_id")
    private User user;

    @Column(name = "phone_number")
    private String phoneNumber;
}
