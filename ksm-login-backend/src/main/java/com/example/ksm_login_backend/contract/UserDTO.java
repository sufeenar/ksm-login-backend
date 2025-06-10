package com.example.ksm_login_backend.contract;
 
import java.sql.Timestamp;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
public class UserDTO {
    private UUID id;
    private  String phoneNumber;
    private Integer tenantId;
    private String name;
    private Boolean isActive;
    private Boolean isKycVerified;
    private String aadhaarNo;
    private Boolean isFirstLogin;
    private String email;
    private String userType;
    private String  whatsapp_number;
    private Long regNo;
    private Timestamp createdAt;
    private  Boolean isDocumentVerified;
    private Timestamp  updatedAt;
    private String dob;
    private  String gender;
    private Boolean  countryType;
}
