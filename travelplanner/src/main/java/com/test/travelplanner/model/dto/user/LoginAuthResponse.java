package com.test.travelplanner.model.dto.user;

import com.test.travelplanner.model.entity.user.UserRole;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginAuthResponse {
    private String token;  
    private String user;
    private UserRole role;
}