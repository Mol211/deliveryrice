package com.mol211.deliveryrice.direction.controller;

import com.mol211.deliveryrice.direction.dto.DirectionRequest;
import com.mol211.deliveryrice.direction.dto.DirectionResponse;
import com.mol211.deliveryrice.direction.service.DirectionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/directions")
public class DirectionController {
    private final DirectionService directionService;

    public DirectionController(DirectionService directionService) {
        this.directionService = directionService;
    }

    @GetMapping
    public ResponseEntity<List<DirectionResponse>> getMyDirections(Authentication authentication) {
        return ResponseEntity.ok(directionService.getMyDirections(authentication.getName()));
    }
    @GetMapping("/default")
    public ResponseEntity<DirectionResponse> getDefaultDirection(Authentication authentication) {
        return ResponseEntity.ok(directionService.getDefaultDirection(authentication.getName()));
    }
    @PostMapping
    public ResponseEntity<DirectionResponse> createDirection(
            @Valid @RequestBody DirectionRequest request,
            Authentication authentication
            ) {
        DirectionResponse response = directionService.createDirection(request, authentication.getName());
        return ResponseEntity.created(URI.create("/api/v1/directions/"+response.id())).body(response);
    }
    @PatchMapping("/{id}/default")
    public ResponseEntity<DirectionResponse> setDefaultDirection(
            @PathVariable Long id,
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                directionService.setDefaultDirection(id, authentication.getName())
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<DirectionResponse> updateDirection(
            @PathVariable Long id,
            @Valid @RequestBody DirectionRequest request,
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                directionService.updateDirection(id, request, authentication.getName())
        );
    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void>deleteDirection(
//            @PathVariable Long id,
//            Authentication authentication
//    ) {
//        directionService.deleteDirection(id, authentication.getName());
//        return ResponseEntity.noContent().build();
//    }



}
