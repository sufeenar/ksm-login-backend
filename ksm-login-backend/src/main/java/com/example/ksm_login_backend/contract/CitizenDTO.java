package com.example.ksm_login_backend.contract;

 

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class CitizenDTO {
    //  private UUID id;
@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private  String phoneNumber;
    @Email(message = "Email should be valid")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email should be valid")
    private String email;
    private Boolean  countryType;
    private String otp;
    
}
