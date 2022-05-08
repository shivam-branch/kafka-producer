package com.get_user_service.controller;

import com.get_user_service.dto.UserDto;
import com.get_user_service.service.KafKaProducerService;
import com.get_user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private final UserService userService;
    private final KafKaProducerService producerService;

    @Autowired
    public UserController(UserService userService, KafKaProducerService producerService) {
        this.userService = userService;
        this.producerService = producerService;
    }

    @GetMapping()
    public List<UserDto> getMapping() {
        return userService.getUser();
    }

    @PostMapping
    public String postMapping(@RequestBody UserDto user) {
         return producerService.sendUser(user);
    }
}
