package com.inventory.services;

import com.inventory.models.dtos.UserDTO;
import com.inventory.exceptions.custom.DuplicateResourceException;
import com.inventory.interfaces.IUserService;
import com.inventory.models.User;
import com.inventory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(String username, String password, String role) {
        // Check if the username already exists
        if (userRepository.findByUsername(username).isPresent()) {
            throw new DuplicateResourceException("Username already exists");
        }

        // Create and save the user
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));  // Encrypt the password
        user.setRole(role);
        userRepository.save(user);

        // Return the UserDTO
        return new UserDTO(user.getUsername(), user.getRole());
    }
}