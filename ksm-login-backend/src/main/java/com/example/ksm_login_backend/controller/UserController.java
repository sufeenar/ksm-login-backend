package com.example.ksm_login_backend.controller;

 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ksm_login_backend.contract.CitizenDTO;
import com.example.ksm_login_backend.contract.CitizenLoginRequest;
import com.example.ksm_login_backend.contract.CitizenResponse;
import com.example.ksm_login_backend.contract.KYCRequest;
import com.example.ksm_login_backend.contract.UserDTO;
import com.example.ksm_login_backend.service.UserService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:5174")
@RestController
@AllArgsConstructor
public class UserController implements SecureSwaggerController {
     
	public UserService userService;
    @PostMapping("SaveUser")
    public String loginsave(@RequestBody UserDTO request) {
        
        
        return userService.savelogin(request);
    }
    
    
   // ✅ Endpoint to save/register citizen
   @PostMapping("/save-citizen")
   public CitizenResponse saveCitizen(@RequestBody CitizenDTO request) {
       return userService.saveCitizen(request);
   }
    
   @PostMapping("KYCVerify")
   public String kycverify(@RequestBody KYCRequest request) {
      return  userService.saveKYC(request);
   } 
  @PostMapping("login")
   public String citizenLogin(@RequestBody CitizenLoginRequest request) {
     
       
       return userService.citizenLogin(request);
   }
   
}


