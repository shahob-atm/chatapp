package uz.chat.app.controller;

import uz.chat.app.dto.LoginDto;
import uz.chat.app.dto.RegisterDto;
import uz.chat.app.model.User;
import uz.chat.app.service.UserService;

public class UserController {
    private final UserService userService = new UserService();

    public boolean register(RegisterDto registerDto) {
        return userService.register(registerDto);
    }

    public User login(LoginDto loginDto) {
        return userService.login(loginDto);
    }
}
