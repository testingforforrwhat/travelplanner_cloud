package com.test.travelplanner.model.entity.user;

import jakarta.persistence.*;
import lombok.Data;

// PermissionOperate
@Entity
@Table(name = "permission_operate")
@Data
public class PermissionOperate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "permission_id")
    private Integer permissionId;

    @Column(name = "operate_id")
    private Integer operateId;
}
