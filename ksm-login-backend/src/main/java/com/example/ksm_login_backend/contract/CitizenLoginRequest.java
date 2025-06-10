package com.example.ksm_login_backend.contract;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CitizenLoginRequest {
    private  String phoneNumber;
    private String email;
    private Boolean  countryType;
    private String otp;
}
