package com.mol211.deliveryrice.direction.service;

import com.mol211.deliveryrice.direction.dto.DirectionRequest;
import com.mol211.deliveryrice.direction.dto.DirectionResponse;
import com.mol211.deliveryrice.direction.mapper.DirectionMapper;
import com.mol211.deliveryrice.direction.model.Direction;
import com.mol211.deliveryrice.direction.persistence.DirectionRepository;
import com.mol211.deliveryrice.exception.NotFoundException;
import com.mol211.deliveryrice.user.model.User;
import com.mol211.deliveryrice.user.persistence.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DirectionServiceImpl implements DirectionService{
    private final UserRepository userRepository;
    private final DirectionRepository directionRepository;

    public DirectionServiceImpl(UserRepository userRepository, DirectionRepository directionRepository) {
        this.userRepository = userRepository;
        this.directionRepository = directionRepository;
    }

    private User getAuthenticatedUser(String mail) {
        return userRepository.findByMail(mail)
                .orElseThrow(()->new NotFoundException("No se encontro usuario con ese mail"));
    }

    @Override
    public List<DirectionResponse> getMyDirections(String mail) {
        User user = getAuthenticatedUser(mail);
        return directionRepository.findByUserId(user.getId())
                .stream()
                .map(DirectionMapper::toResponse)
                .toList();
    }

    @Override
    public DirectionResponse getDefaultDirection(String mail) {
        User user = getAuthenticatedUser(mail);
        Direction direction = directionRepository.findByUserIdAndDefaultAddressTrue(user.getId())
                .orElseThrow(()->new NotFoundException("No se encontró dirección principal para ese usuario"));
        return DirectionMapper.toResponse(direction);
    }

    @Override
    public DirectionResponse createDirection(DirectionRequest request, String mail) {
        User user = getAuthenticatedUser(mail);
        var directionOptional = directionRepository.findByUserIdAndDefaultAddressTrue(user.getId());
        Direction direction = DirectionMapper.toEntity(request);
        if (directionOptional.isEmpty()){
            direction.setDefaultAddress(true);
        }
        direction.setUser(user);
        var directionSaved = directionRepository.save(direction);
        return DirectionMapper.toResponse(directionSaved);
    }

    @Override
    public DirectionResponse setDefaultDirection(Long id, String mail) {
        User user = getAuthenticatedUser(mail);
        var directionOptional = directionRepository.findByUserIdAndDefaultAddressTrue(user.getId());
        if(directionOptional.isPresent()){
            var direction = directionOptional.get();
            direction.setDefaultAddress(false);
            directionRepository.save(direction);
        }
        Direction direction = directionRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(()-> new NotFoundException("No se encontro la dirección a modificar de ese usuario"));
        direction.setDefaultAddress(true);
        var directionUpdated = directionRepository.save(direction);
        return DirectionMapper.toResponse(directionUpdated);
    }

    @Override
    public DirectionResponse updateDirection(Long id, DirectionRequest request, String mail) {
        User user = getAuthenticatedUser(mail);
        Direction direction = directionRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(()-> new NotFoundException("No se encontro la dirección a modificar para ese usuario"));
        DirectionMapper.updateDirectionFromRequest(direction, request);
        return DirectionMapper.toResponse(directionRepository.save(direction));

    }
}
