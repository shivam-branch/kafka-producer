package com.get_user_service.service;

import com.get_user_service.dto.UserDto;
import com.get_user_service.entity.User;
import com.get_user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserDto> getUser(){
        List<User> userList = userRepository.findAll();
        return userList.stream().map(x -> UserDto
                .builder()
                .key(x.getKey())
                .name(x.getName())
                .status(x.getStatus())
                .build()).collect(Collectors.toList());

    }

    public UserDto postUser(UserDto userDto){
        User u = User.builder()
                .key(UUID.randomUUID().toString())
                .name(userDto.getName())
                .status(userDto.getStatus())
                .build();
        User user = userRepository.save(u);
        return UserDto.builder()
                .key(user.getKey())
                .name(user.getName())
                .status(user.getStatus())
                .build();

    }
}
