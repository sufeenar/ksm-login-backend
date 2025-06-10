package com.example.ksm_login_backend.model;
 
import java.sql.Timestamp;
import java.util.UUID;

 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Entity
@Getter
@Setter
@Table(name="k_smart_user")
public class User {
    
@Id
@GeneratedValue (strategy=GenerationType.UUID)
 
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
