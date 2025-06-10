package com.example.ksm_login_backend.service;


import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.ksm_login_backend.contract.CitizenDTO;
import com.example.ksm_login_backend.contract.CitizenLoginRequest;
import com.example.ksm_login_backend.contract.CitizenResponse;
import com.example.ksm_login_backend.contract.KYCRequest;
import com.example.ksm_login_backend.contract.UserDTO;
import com.example.ksm_login_backend.model.User;
import com.example.ksm_login_backend.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    // ✅ Hardcoded OTP for demo
    private final String OTP = "12345";

    //✅ Save user during registration
    public String savelogin(UserDTO request) {
        User user = modelMapper.map(request, User.class);
        userRepository.save(user);
        return "success";
    }
    public CitizenResponse saveCitizen(CitizenDTO request) {
        boolean isIndia = Boolean.TRUE.equals(request.getCountryType());

        String userId = isIndia ? request.getPhoneNumber() : request.getEmail().toLowerCase().trim();
    
        Optional<User> userOpt = isIndia
            ? userRepository.findByPhoneNumber(userId)
            : userRepository.findByEmail(userId);
    
        if (userOpt.isPresent()) {
            return new CitizenResponse("User already registered", userId);
        }
    
        if (!OTP.equals(request.getOtp())) {
            return new CitizenResponse("Invalid OTP. Access denied.", null);
        }
    
        if (!isIndia) {
            request.setEmail(userId); // Normalize
        }
    
        User user = modelMapper.map(request, User.class);
        userRepository.save(user);
    
        return new CitizenResponse("User Account Created", userId);
    }
   

    // ✅ KYC Aadhaar Verification
public String saveKYC(KYCRequest request) {
    String aadhaarNo = request.getAadhaarNo();
    String otp = request.getOtp();

    // Step 1: Basic validation
    if (aadhaarNo == null || !aadhaarNo.matches("\\d{12}")) {
        return "Invalid Aadhaar format";
    }
    
    if (!OTP.equals(otp)) {
        return "Invalid OTP";
    }
    
    // Lookup user
    Optional<User> userOptional = Optional.empty();
    if (request.getPhoneNumber() != null && !request.getPhoneNumber().isBlank()) {
        userOptional = userRepository.findByPhoneNumber(request.getPhoneNumber());
    } else if (request.getEmail() != null && !request.getEmail().isBlank()) {
        userOptional = userRepository.findByEmail(request.getEmail());
    } else {
        return "Phone number or Email is required for KYC";
    }
    
    if (userOptional.isEmpty()) return "User not found for KYC";
    
    User user = userOptional.get();
    
    // Already KYC verified for this user
    if (user.getAadhaarNo() != null && !user.getAadhaarNo().isBlank()) {
        return "This user's Aadhaar is already set";
    }
    
    // Aadhaar used by someone else
    Optional<User> existingAadhaar = userRepository.findByAadhaarNo(aadhaarNo);
    if (existingAadhaar.isPresent() && !existingAadhaar.get().getId().equals(user.getId())) {
        return "Aadhaar already verified with another user";
    }
    
    // Save KYC
    user.setAadhaarNo(aadhaarNo);
    user.setIsDocumentVerified(true);
    user.setIsKycVerified(true);
    user.setIsActive(true);
    userRepository.save(user);
    
    return "KYC Verification Successful";

}
//login 

public String citizenLogin(CitizenLoginRequest request) {
   
boolean isIndia = Boolean.TRUE.equals(request.getCountryType());
    String userId = isIndia ? request.getPhoneNumber() : request.getEmail();
   
    if (userId == null || userId.trim().isEmpty()) {
        return isIndia ? "Phone number required" : "Email required";
    }

    // Find user
   // Step 2: Find user
   Optional<User> userOptional = isIndia
   ? userRepository.findByPhoneNumber(userId.trim())
   : userRepository.findByEmail(userId.trim().toLowerCase());

    if (userOptional.isEmpty()) {
        return "User not found";
    }
     // Step 3: If OTP is NOT present → assume SEND OTP request
     if (request.getOtp() == null || request.getOtp().trim().isEmpty()) {
        // You can optionally generate & send OTP here
        return "OTP sent successfully";
    }


    User user = userOptional.get();

    // Check if KYC is verified
    if (!Boolean.TRUE.equals(user.getIsDocumentVerified())) {
        return "KYC not verified";
    }
// Step 1: No OTP provided -> Send OTP step
if (request.getOtp() == null || request.getOtp().trim().isEmpty()) {
    // Here you can generate and send OTP via SMS/Email
    // otpService.sendOtp(user); // Optional integration
    return "OTP sent";
}
    // // OTP check
    if (!OTP.equals(request.getOtp())) {
        return "Invalid OTP";
    }
     

    return "Login allowed";
}
}
   