package com.example.ksm_login_backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ksm_login_backend.model.User;
 

@Repository

public interface UserRepository  extends JpaRepository<User, UUID>  {

   

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByEmail(String email);

    Optional<User> findByAadhaarNo(String aadhaar);

   
 

}
