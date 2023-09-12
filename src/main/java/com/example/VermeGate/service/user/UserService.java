package com.example.VermeGate.service.user;

import com.example.VermeGate.dto.SignupDTO;
import com.example.VermeGate.dto.UserDTO;

public interface UserService {
    UserDTO createUser(SignupDTO signupDTO);

    boolean hasUserWithEmail(String email);
}
