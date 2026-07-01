package com.mol211.deliveryrice.direction.persistence;

import com.mol211.deliveryrice.direction.model.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
    List<Direction> findByUserId(Long id);

    Optional<Direction> findByUserIdAndDefaultAddressTrue(Long id);

    Optional<Direction> findByIdAndUserId(Long id, Long id1);
}
