package com.inventory.interfaces;

import com.inventory.models.dtos.responseDTOs.LoginResponseDTO;
import com.inventory.models.dtos.requestDTOs.LoginRequestDTO;

public interface IAuthService {
    LoginResponseDTO authenticateUser(LoginRequestDTO loginRequest);
}