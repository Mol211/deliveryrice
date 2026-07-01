package com.mol211.deliveryrice.direction.persistence;

import com.mol211.deliveryrice.direction.model.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
}
