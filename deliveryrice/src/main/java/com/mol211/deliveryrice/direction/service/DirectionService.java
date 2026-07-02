package com.mol211.deliveryrice.direction.service;

import com.mol211.deliveryrice.direction.dto.DirectionRequest;
import com.mol211.deliveryrice.direction.dto.DirectionResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface DirectionService {
    List<DirectionResponse> getMyDirections(String name);

    DirectionResponse getDefaultDirection(String name);

    DirectionResponse createDirection(@Valid DirectionRequest request, String name);

    DirectionResponse setDefaultDirection(Long id, String name);

    DirectionResponse updateDirection(Long id, @Valid DirectionRequest request, String name);

//    void deleteDirection(Long id, String mail);
}
