package com.mol211.deliveryrice.direction.model;

import com.mol211.deliveryrice.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="directions")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Direction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false, name = "postal_code")
    private String postalCode;
    @Column(nullable = false)
    private String city;
    @Column(name="additional_info")
    private String additionalInfo;
    @Column(name="default_address")
    private boolean defaultAddress;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}
