package com.test.travelplanner.model.entity.user;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Table(name = "menu")
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Integer menuId;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_url")
    private String menuUrl;

    @Column(name = "pid")
    private Integer pid;
}