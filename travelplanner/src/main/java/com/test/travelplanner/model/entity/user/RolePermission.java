package com.test.travelplanner.model.entity.user;

import jakarta.persistence.*;
import lombok.Data;

// RolePermission
@Entity
@Table(name = "role_permission")
@Data
public class RolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id")
    private Permission permission;

    @Column(name = "role_id", insertable = false, updatable = false)
    private Integer roleId;

    @Column(name = "permission_id", insertable = false, updatable = false)
    private Integer permissionId;
}

