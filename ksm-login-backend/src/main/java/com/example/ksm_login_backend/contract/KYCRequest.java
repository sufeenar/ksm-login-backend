package com.example.ksm_login_backend.contract;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter

public class KYCRequest {
    private String aadhaarNo;
    private String phoneNumber;
    private String otp;
    private String email;
    private Boolean  countryType;
}
