package com.mol211.deliveryrice.direction.mapper;

import com.mol211.deliveryrice.direction.dto.DirectionRequest;
import com.mol211.deliveryrice.direction.dto.DirectionResponse;
import com.mol211.deliveryrice.direction.model.Direction;

public class DirectionMapper {
    public static Direction toEntity(DirectionRequest request) {
        return Direction.builder()
                .street(request.street())
                .postalCode(request.postalCode())
                .city(request.city())
                .additionalInfo(request.additionalInfo())
                .build();
    }
    public static DirectionResponse toResponse(Direction direction) {
        return new DirectionResponse(
                direction.getId(),
                direction.getStreet(),
                direction.getPostalCode(),
                direction.getCity(),
                direction.getAdditionalInfo(),
                direction.isDefaultAddress(),
                direction.getCreatedAt()
        );
    }
    public static void updateDirectionFromRequest(Direction direction, DirectionRequest request) {
        direction.setStreet(request.street());
        direction.setCity(request.city());
        direction.setPostalCode(request.postalCode());
        direction.setAdditionalInfo(request.additionalInfo());
    }
}
