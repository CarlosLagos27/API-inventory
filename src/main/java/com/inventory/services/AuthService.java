package com.inventory.services;

import com.inventory.models.dtos.requestDTOs.LoginRequestDTO;
import com.inventory.models.dtos.responseDTOs.LoginResponseDTO;
import com.inventory.interfaces.IAuthService;
import com.inventory.models.User;
import com.inventory.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String SECRET_KEY = "my_very_long_and_secure_jwt_secret_key";  // Replace with a secure key

    @Override
    public LoginResponseDTO authenticateUser(LoginRequestDTO loginRequest) {
        // Find the user by username
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Verify the password
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // Generate JWT token
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("authorities", List.of(user.getRole()))  // Set authorities as a List<String>
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // 10 hours validity
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();

        // Return the token in the response
        return new LoginResponseDTO(token);
    }
}