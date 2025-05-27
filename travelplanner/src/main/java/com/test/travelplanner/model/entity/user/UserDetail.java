package com.test.travelplanner.model.entity.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_detail")
@Data
public class UserDetail {
    
    @Id
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "real_name", length = 50)
    private String realName;
    
    @Column(name = "nickname", length = 50)
    private String nickname;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    
    @Column(name = "birth_date")
    private LocalDate birthDate;
    
    @Column(name = "age")
    private Integer age;
    
    @Column(name = "avatar_url", length = 500)
    private String avatarUrl;
    
    @Column(name = "id_card_number", length = 20)
    private String idCardNumber;
    
    @Column(name = "address", length = 500)
    private String address;
    
    @Column(name = "province", length = 50)
    private String province;
    
    @Column(name = "city", length = 50)
    private String city;
    
    @Column(name = "district", length = 50)
    private String district;
    
    @Column(name = "postal_code", length = 10)
    private String postalCode;
    
    @Column(name = "company", length = 100)
    private String company;
    
    @Column(name = "position", length = 100)
    private String position;
    
    @Column(name = "industry", length = 50)
    private String industry;
    
    @Column(name = "education", length = 50)
    private String education;
    
    @Column(name = "school", length = 100)
    private String school;
    
    @Column(name = "major", length = 50)
    private String major;
    
    @Column(name = "personal_website", length = 200)
    private String personalWebsite;
    
    @Column(name = "wechat", length = 50)
    private String wechat;
    
    @Column(name = "qq", length = 20)
    private String qq;
    
    @Column(name = "emergency_contact", length = 50)
    private String emergencyContact;
    
    @Column(name = "emergency_phone", length = 20)
    private String emergencyPhone;
    
    @Column(name = "bio", length = 1000)
    private String bio;
    
    @Column(name = "interests", length = 500)
    private String interests;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // 一对一关系：用户注册信息
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    

}