package com.taskmanagementsystem.task_management_app.repositories;

import com.taskmanagementsystem.task_management_app.entities.User;
import com.taskmanagementsystem.task_management_app.enums.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Query("select u from User u where u.email = :email and u.userRoles = :userRoles")
    Optional<User> findByEmailAndRole(@Param("email") String mail, @Param("userRoles")UserRoles userRoles);

    Optional<User> findFirstByEmail(String email);
}
