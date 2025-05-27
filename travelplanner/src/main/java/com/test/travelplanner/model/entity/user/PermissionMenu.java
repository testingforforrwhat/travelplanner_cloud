package com.test.travelplanner.model.entity.user;

import jakarta.persistence.*;
import lombok.Data;

// PermissionMenu
@Entity
@Table(name = "permission_menu")
@Data
public class PermissionMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "permission_id")
    private Integer permissionId;

    @Column(name = "menu_id")
    private Integer menuId;
}
