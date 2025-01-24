package com.taskmanagementsystem.task_management_app.controller.auth;

import com.taskmanagementsystem.task_management_app.dto.AuthReq;
import com.taskmanagementsystem.task_management_app.dto.AuthResponse;
import com.taskmanagementsystem.task_management_app.dto.SignUpReq;
import com.taskmanagementsystem.task_management_app.dto.UserDTO;
import com.taskmanagementsystem.task_management_app.entities.User;
import com.taskmanagementsystem.task_management_app.repositories.UserRepo;
import com.taskmanagementsystem.task_management_app.services.auth.AuthService;
import com.taskmanagementsystem.task_management_app.services.jwt.UserService;
import com.taskmanagementsystem.task_management_app.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final UserRepo userRepo;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpReq signUpReq){
        if(authService.hasUserWithEmail(signUpReq.getEmail()))
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User with email already exists");
        Optional<UserDTO> userDTO = Optional.of(authService.signUp(signUpReq));
        if(userDTO.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO.get());

    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthReq authReq) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authReq.getEmail(),
                    authReq.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect Username or Password");
        }
        UserDetails userDetails=userService.userDetailsService().loadUserByUsername(authReq.getEmail());
        Optional<User> user = userRepo.findFirstByEmail(authReq.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthResponse response = new AuthResponse();
        if(user.isPresent()) {
            response.setJwt(jwt);
            response.setUserRoles(user.get().getUserRoles());
            response.setUserId(user.get().getId());
        }
        return response;
    }
}
