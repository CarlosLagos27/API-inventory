package com.inventory.controllers;

import com.inventory.models.dtos.UserDTO;
import com.inventory.models.dtos.requestDTOs.UserRequestDTO;
import com.inventory.interfaces.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "Endpoints for user user login")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    @Operation(summary = "Register", description = "Register a new user")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRequestDTO userRequest){
        UserDTO userDTO = userService.createUser(userRequest.getUsername(), userRequest.getPassword(), userRequest.getRole());
        return ResponseEntity.ok(userDTO);
    }
}