package com.mol211.deliveryrice.order.model;

import com.mol211.deliveryrice.user.model.User;
import jakarta.persistence.*;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    User user;
}
