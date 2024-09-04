package com.example.apisecuritybasic.models;

import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="role_name",nullable = false)
    private String roleName;

    public Role(long id, String role_name) {
        this.id = id;
        this.roleName = role_name;
    }

    public Role() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
