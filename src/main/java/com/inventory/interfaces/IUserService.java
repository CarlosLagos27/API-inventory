package com.inventory.interfaces;

import com.inventory.models.dtos.UserDTO;

public interface IUserService {
    UserDTO createUser(String username, String password, String role);  // Method for user creation
}