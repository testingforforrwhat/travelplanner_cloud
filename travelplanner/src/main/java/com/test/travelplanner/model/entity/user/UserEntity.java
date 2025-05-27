package com.test.travelplanner.model.entity.user;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * The @Entity annotation indicates that this class is an entity class,
 * and during project startup, a table will be automatically generated based on this class.
 */
@Entity
@Table(name = "users")
@Data
public class UserEntity implements UserDetails {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    private String password_hash;

    @Getter
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Size(max = 100)
    @NotNull
    @Column(name = "email")
    private String email;

    @Column(name = "phone", unique = true, length = 20)
    private String phone;

    @Size(max = 255)
    @Column(name = "avatar_url")
    private String avatarUrl;

    @ColumnDefault("true")
    @Column(name = "is_active")
    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status = UserStatus.ACTIVE;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    @Column(name = "login_count", nullable = false)
    private Integer loginCount = 0;

    // @Column(name = "registration_time", nullable = false)
    private LocalDateTime registrationTime;

    @Column(name = "registration_ip", length = 45)
    private String registrationIp;

    // 一对一关系：用户详情
    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserDetail userDetail;

    @Enumerated(EnumType.STRING)
    private UserRole userRoles;
    
    public UserEntity() {
    }

    public UserEntity(Long id, String username, String password_hash, UserRole role, String email) {
        this.id = id;
        this.username = username;
        this.password_hash = password_hash;
        this.role = role;
        this.email = email;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }


    @Override
    public String getPassword() {
        return password_hash;
    }


    @Override
    public String getUsername() {
        return username;
    }

}
