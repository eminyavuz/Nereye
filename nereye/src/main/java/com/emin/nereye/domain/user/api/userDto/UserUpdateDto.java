package com.emin.nereye.domain.user.api.userDto;

import com.emin.nereye.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
    private String user_name;
    private String password;
    private String email;
    private String phone_number;
    private String first_name;
    private String last_name;
    private Role role;

}
