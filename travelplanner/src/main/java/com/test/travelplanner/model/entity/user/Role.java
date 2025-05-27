package com.test.travelplanner.model.entity.user;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "role")
@Data
@EqualsAndHashCode(exclude = {"rolePermissions"})
@ToString(exclude = {"rolePermissions"})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<RolePermission> rolePermissions;
}