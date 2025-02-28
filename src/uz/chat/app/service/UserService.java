package uz.chat.app.service;

import uz.chat.app.dto.LoginDto;
import uz.chat.app.dto.RegisterDto;
import uz.chat.app.model.User;
import uz.chat.app.repository.UserRepository;

import java.util.List;
import java.util.UUID;

public class UserService {
    public boolean register(RegisterDto registerDto) {
        User user = new User(
                UUID.randomUUID(),
                registerDto.firstName(),
                registerDto.lastName(),
                registerDto.email(),
                registerDto.password(),
                registerDto.registrationDate()
        );

        return UserRepository.saveUser(user);
    }

    public User login(LoginDto loginDto) {
        List<User> users = UserRepository.readUsers();

        for (User user : users) {
            if (user.getEmail().equals(loginDto.email()) && user.getPassword().equals(loginDto.password())) {
                return user;
            }
        }
        return null;
    }
}
