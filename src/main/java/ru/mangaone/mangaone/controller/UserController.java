package ru.mangaone.mangaone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mangaone.mangaone.domain.User;
import ru.mangaone.mangaone.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("{id}")
    public List<User> showUsers(@PathVariable Long id ) {
        return userRepository.findUserById(id);
    }
}
