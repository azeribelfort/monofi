package com.monofi.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "verification")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VerificationToken {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token",nullable = false)
    private String token;
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
}
