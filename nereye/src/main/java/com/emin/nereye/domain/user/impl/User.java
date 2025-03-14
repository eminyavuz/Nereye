package com.emin.nereye.domain.user.impl;

import com.emin.nereye.enumeration.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name")
    private String user_name;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "role")
    private Role role;

    public User(String first_name,
                String last_name,
                String password,
                String phone_number,
                String email,
                String user_name,
                Role role
    ) {
        this.email = email;
        this.role = role;
        this.last_name = last_name;
        this.first_name = first_name;
        this.password = password;
        this.phone_number = phone_number;
        this.user_name = user_name;
    }


}
