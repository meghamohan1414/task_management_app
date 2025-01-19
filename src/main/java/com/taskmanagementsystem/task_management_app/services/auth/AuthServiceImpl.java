package com.taskmanagementsystem.task_management_app.services.auth;

import com.taskmanagementsystem.task_management_app.dto.SignUpReq;
import com.taskmanagementsystem.task_management_app.dto.UserDTO;
import com.taskmanagementsystem.task_management_app.entities.User;
import com.taskmanagementsystem.task_management_app.enums.UserRoles;
import com.taskmanagementsystem.task_management_app.repositories.UserRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;

    public AuthServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostConstruct
    private void createAdminAccount() {
        Optional<User> adminUser = userRepo.findByEmailAndRole("admin@test.com", UserRoles.ADMIN);
        if(adminUser.isEmpty()){
            User newAdminUser = new User();
            newAdminUser.setName("admin");
            newAdminUser.setEmail("admin@test.com");
            newAdminUser.setPassword(new BCryptPasswordEncoder().encode(""));
            newAdminUser.setUserRoles(UserRoles.ADMIN);
            userRepo.save(newAdminUser);
        }
    }

    @Override
    public Boolean hasUserWithEmail(String email) {
        return userRepo.findFirstByEmail(email).isPresent();
    }

    @Override
    public UserDTO signUp(SignUpReq signUpReq) {
        User user = new User();
        user.setEmail(signUpReq.getEmail());
        user.setName(signUpReq.getName());
        user.setUserRoles(UserRoles.EMPLOYEE);
        user.setPassword(new BCryptPasswordEncoder().encode(signUpReq.getPassword()));

        return userRepo.save(user).getUserDTO();
    }
}
