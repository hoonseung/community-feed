package com.backend.user.application;

import com.backend.user.application.dto.CreateUserRequestDto;
import com.backend.user.application.dto.GetUserResponseDto;
import com.backend.user.application.interfaces.UserRepository;
import com.backend.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto) {
        return userRepository.save(dto.toUser());
    }

    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    public GetUserResponseDto getUserProfile(Long id){
        return GetUserResponseDto.from(getUser(id));
    }
}
