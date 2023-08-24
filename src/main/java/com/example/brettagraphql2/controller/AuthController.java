package com.example.brettagraphql2.controller;

import com.example.brettagraphql2.model.Role;
import com.example.brettagraphql2.model.UserEntity;
import com.example.brettagraphql2.model.input.AuthEntity;
import com.example.brettagraphql2.model.input.UserInput;
import com.example.brettagraphql2.repository.RoleRepository;
import com.example.brettagraphql2.repository.UserRepository;
import com.example.brettagraphql2.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.util.Collections;

@Controller
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;


    @QueryMapping
    public Iterable<UserEntity> users() {
        return this.userRepository.findAll();
    }

    @MutationMapping
    public AuthEntity login(@Argument(name = "input") UserInput userInput){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        userInput.getUsername(),
                        userInput.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);
        return new AuthEntity(token);
    }

    @MutationMapping
    public ResponseEntity<String> register(@Argument(name = "input") UserInput userInput) {
        if (userRepository.existsByUsername(userInput.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(userInput.getUsername());
        user.setPassword(passwordEncoder.encode(userInput.getPassword()));
        Role roles = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(roles));
        this.userRepository.save(user);
        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

}
