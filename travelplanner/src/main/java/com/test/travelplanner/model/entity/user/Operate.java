package com.test.travelplanner.model.entity.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "operate")
@Data
public class Operate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operate_id")
    private Integer operateId;

    @Column(name = "operate_name")
    private String operateName;

    @Column(name = "operate_url")
    private String operateUrl;
}