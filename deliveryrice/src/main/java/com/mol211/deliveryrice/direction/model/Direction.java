package com.mol211.deliveryrice.direction.model;

import com.mol211.deliveryrice.user.model.User;
import jakarta.persistence.*;

@Entity
@Table(name="directions")
public class Direction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    User user;
}
