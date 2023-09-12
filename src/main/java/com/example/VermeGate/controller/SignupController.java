package com.example.VermeGate.controller;

import com.example.VermeGate.dto.SignupDTO;
import com.example.VermeGate.dto.UserDTO;
import com.example.VermeGate.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/sign-up")
    public ResponseEntity<?> signupUser(@RequestBody SignupDTO signupDTO) {
        if (userService.hasUserWithEmail(signupDTO.getEmail())) {
            return new ResponseEntity<>("User already exist", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDTO createUser = userService.createUser(signupDTO);
        if (createUser == null) {
            return new ResponseEntity<>("user not created , Come later !" , HttpStatus.BAD_REQUEST);
        }

        return  new ResponseEntity<>(createUser,HttpStatus.CREATED);

    }
}

