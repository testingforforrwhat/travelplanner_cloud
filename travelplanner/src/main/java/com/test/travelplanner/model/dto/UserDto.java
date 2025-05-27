package com.test.travelplanner.model.dto;


import com.test.travelplanner.model.entity.user.UserEntity;
import com.test.travelplanner.model.entity.user.UserRole;

public record UserDto(
       Long id,
       String username,
       UserRole role
) {
   public UserDto(UserEntity entity) {
       this(
               entity.getId(),
               entity.getUsername(),
               entity.getRole()
       );
   }
}
