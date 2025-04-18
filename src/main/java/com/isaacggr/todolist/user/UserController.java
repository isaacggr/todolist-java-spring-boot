package com.isaacggr.todolist.user;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Modificadores de acceso em java
 * public
 * private
 * protected
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"https://todolist-frontend-dt9a.onrender.com", "https://todolist-frontend.onrender.com", "https://isaacggr.github.io"})
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        var passwordHashred = BCrypt.withDefaults()
            .hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashred);
        userModel.setCreatedAt(LocalDateTime.now());
        
        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.ok(userCreated);
    }
}
