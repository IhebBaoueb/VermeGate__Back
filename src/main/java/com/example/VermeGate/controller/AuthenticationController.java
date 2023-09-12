package com.example.VermeGate.controller;

import com.example.VermeGate.dto.AuthenticationRequest;
import com.example.VermeGate.dto.AuthenticationResponse;
import com.example.VermeGate.entities.User;
import com.example.VermeGate.repository.UserRepository;
import com.example.VermeGate.service.user.UserService;
import com.example.VermeGate.utils.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;

@RestController
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil ;

    public  static final  String TOKEN_PREFIX ="Bearer";
    public static final String HEADER_STRING= "Authorization";


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/authenticate")
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest , HttpServletResponse response) throws BadCredentialsException,DisabledException, UsernameNotFoundException,IOException, JSONException, ServletException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        }  catch (BadCredentialsException badCredentialsException) {
            throw new BadCredentialsException("Incorrect username or password !!! ");
        }
        catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "User is not activated");
            return ;
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        User user = userRepository.findFirstByEmail(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(authenticationRequest.getUsername());

        response.getWriter().write(new JSONObject()
                .put("userId",user.getId())
                .put("role",user.getUserRole())
                .toString()
        );
        response.addHeader("Access-Control-Expose-Headers","Authorization");
        response.setHeader("Access-Control-Allow-Headers","Authorization,X-PINGOTHER,Origin,X-Requested-With,Content-Type,Accept,X-Custom-header");
        response.setHeader(HEADER_STRING,TOKEN_PREFIX+jwt);


    }
}
