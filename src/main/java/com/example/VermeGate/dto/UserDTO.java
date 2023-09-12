package com.example.VermeGate.dto;

import com.example.VermeGate.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {

    private Long id ;
    private  String name ;
    private String email;
    private String password;
    private UserRole userRole;


}
