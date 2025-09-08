package com.emin.nereye.domain.user.api;

import com.emin.nereye.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private int id;
    private String user_name;
    private String password;
    private String email;
    private String phone_number;
    private String first_name;
    private String last_name;
    private Role role;
}
